package org.stars.crudPRODUCTO.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.stars.crudPRODUCTO.data.DataLoader;
import org.stars.crudPRODUCTO.dto.api.ApiResponse;
import org.stars.crudPRODUCTO.dto.producto.ActualizarProductoRequest;
import org.stars.crudPRODUCTO.dto.producto.ProductoResponse;
import org.stars.crudPRODUCTO.dto.producto.RegistrarProductoRequest;
import org.stars.crudPRODUCTO.entity.ProductoEntity;
import org.stars.crudPRODUCTO.enums.CategoriaProducto;
import org.stars.crudPRODUCTO.repository.ProductoRepository;
import org.stars.crudPRODUCTO.usecase.MantenerProductoUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MantenerProductoService implements MantenerProductoUseCase {

    private final ProductoRepository productoRepository;
    private final DataLoader dataLoader;

    @Override
    public ApiResponse<List<ProductoResponse>> listarProductos() {
        var response = cargarProductosResponse();
        return ApiResponse.exito(response, "La lista se obtuvo correctamente");
    }

    @Override
    public ApiResponse<List<String>> listarCategorias() {
        List<String> response = new ArrayList<>();
        for(CategoriaProducto item: CategoriaProducto.values()){
            response.add(item.name());
        }

        return ApiResponse.exito(response, "La lista de categorias se obtuvo correctamente");
    }

    @Override
    public ApiResponse<ProductoResponse> obtenerProductoPorId(int idProducto) {
        return cargarProductoResponse(idProducto)
                .map(producto -> ApiResponse.exito(producto, "Producto obtenido correctamente"))
                .orElseGet(() -> ApiResponse.error("El producto no existe"));
    }

    @Override
    @Transactional
    public ApiResponse<Integer> registrarProducto(RegistrarProductoRequest request) {
        if (request == null) {
            return ApiResponse.error("No existe el requerimiento para registrar el producto");
        }

        if (productoRepository.count() >= 30) {
            productoRepository.deleteAllInBatch();
            dataLoader.cargarDatosIniciales();
        }

        ProductoEntity producto = ProductoEntity.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .stock(request.getStock())
                .categoria(request.getCategoria())
                .estado(true)
                .build();

        ProductoEntity productoEntity = productoRepository.save(producto);
        if (productoEntity.getIdProducto() <= 0) {
            return ApiResponse.error("No se pudo registrar el producto");
        }

        return ApiResponse.exito(productoEntity.getIdProducto(), "El producto se ha registrado correctamente");
    }


    @Override
    @Transactional
    public ApiResponse<Integer> cambiarEstadoProductoPorId(int idProducto) {

        ProductoEntity productoEntity = productoRepository.findById(idProducto).orElse(null);
        if(productoEntity == null){
            return ApiResponse.error("El producto seleccionado no existe");
        }

        productoEntity.setEstado(!productoEntity.isEstado());
        return ApiResponse.exito(productoEntity.getIdProducto(), "Se ha cambiado el estado del producto");
    }

    @Override
    public ApiResponse<Integer> actualizarProductoPorId(int idProducto, ActualizarProductoRequest request) {
        ProductoEntity productoEntity = productoRepository.findById(idProducto).orElse(null);
        if(productoEntity == null){
            return ApiResponse.error("El producto seleccionado no existe");
        }
        productoEntity.setNombre(request.getNombre());
        productoEntity.setDescripcion(request.getDescripcion());
        productoEntity.setStock(request.getStock());
        productoEntity.setCategoria(request.getCategoria());
        return null;
    }

    // metodos complementarios

    private Optional<ProductoResponse> cargarProductoResponse(int idProducto){
        return productoRepository.findById(idProducto)
                .map(this::mapearProductoResponse);
    }

    private List<ProductoResponse> cargarProductosResponse(){
        return productoRepository.findAll()
                .stream()
                .map(this::mapearProductoResponse)
                .toList();
    }

    private ProductoResponse mapearProductoResponse(ProductoEntity producto){
        return new ProductoResponse(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getCategoria().name(),
                producto.getStock(),
                producto.isEstado()
        );
    }
}
