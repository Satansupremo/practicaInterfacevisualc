package org.stars.crudPRODUCTO.entity;

import jakarta.persistence.*;
import lombok.*;
import org.stars.crudPRODUCTO.enums.CategoriaProducto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name = "tb_producto")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "stock")
    private int stock;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriaProducto categoria;
    @Column(name = "estado")
    private boolean estado;
}
