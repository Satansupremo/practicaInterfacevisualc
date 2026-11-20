package org.stars.crudPRODUCTO.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stars.crudPRODUCTO.dto.api.ApiResponse;
import org.stars.crudPRODUCTO.dto.producto.ActualizarProductoRequest;
import org.stars.crudPRODUCTO.dto.producto.ProductoResponse;
import org.stars.crudPRODUCTO.dto.producto.RegistrarProductoRequest;
import org.stars.crudPRODUCTO.usecase.MantenerProductoUseCase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final MantenerProductoUseCase mantenerProductoUseCase;

    @GetMapping("/index")
    public String index(){
        return "Im here with your mother !!";
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "timestamp", LocalDateTime.now()
        ));
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<List<ProductoResponse>>> listarProductos(){
        return lanzarResponse(mantenerProductoUseCase.listarProductos(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/categoria")
    public ResponseEntity<ApiResponse<List<String>>> listarCategorias(){
        return lanzarResponse(mantenerProductoUseCase.listarCategorias(), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse<Integer>> registrarProducto(@Valid @RequestBody RegistrarProductoRequest request){
        return lanzarResponse(mantenerProductoUseCase.registrarProducto(request), HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/cambiar-estado/{idProducto}")
    public ResponseEntity<ApiResponse<Integer>> cambiarEstadoProducto(@PathVariable int idProducto){
        return lanzarResponse(mantenerProductoUseCase.cambiarEstadoProductoPorId(idProducto), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("actualizar/{idProducto}")
    public ResponseEntity<ApiResponse<Integer>> actualizarProducto(@PathVariable int idProducto, @Valid @RequestBody ActualizarProductoRequest request){
        return lanzarResponse(mantenerProductoUseCase.actualizarProductoPorId(idProducto, request), HttpStatus.BAD_REQUEST);
    }

    private <T> ResponseEntity<ApiResponse<T>> lanzarResponse(ApiResponse<T> response , HttpStatus statusError){
        HttpStatus status = response.esExito() ? HttpStatus.OK : statusError;
        return ResponseEntity
                .status(status)
                .body(response);
    }
}
