package org.stars.crudPRODUCTO.usecase;

import org.stars.crudPRODUCTO.dto.api.ApiResponse;
import org.stars.crudPRODUCTO.dto.producto.ActualizarProductoRequest;
import org.stars.crudPRODUCTO.dto.producto.ProductoResponse;
import org.stars.crudPRODUCTO.dto.producto.RegistrarProductoRequest;

import java.util.List;

public interface MantenerProductoUseCase {

    ApiResponse<List<ProductoResponse>> listarProductos();
    ApiResponse<List<String>> listarCategorias();
    ApiResponse<ProductoResponse> obtenerProductoPorId(int idProducto);
    ApiResponse<Integer> registrarProducto(RegistrarProductoRequest request);
    ApiResponse<Integer> cambiarEstadoProductoPorId(int idProducto);
    ApiResponse<Integer> actualizarProductoPorId(int idProducto, ActualizarProductoRequest request);
}
