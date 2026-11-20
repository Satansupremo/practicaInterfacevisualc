package org.stars.crudPRODUCTO.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private String categoria;
    private int stock;
    private boolean estado;
}
