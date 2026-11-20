package org.stars.crudPRODUCTO.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.stars.crudPRODUCTO.entity.ProductoEntity;
import org.stars.crudPRODUCTO.enums.CategoriaProducto;
import org.stars.crudPRODUCTO.repository.ProductoRepository;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    @Override
    public void run(String @NonNull ... args) {
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales() {
        if (productoRepository.count() > 0) {
            return;
        }

        List<ProductoEntity> productosIniciales = List.of(
                // --- ELECTRÓNICA ---
                ProductoEntity.builder()
                        .nombre("Laptop Lenovo IdeaPad 5")
                        .descripcion("Laptop 15.6 pulgadas, Ryzen 7, 16GB RAM, 512GB SSD")
                        .stock(12)
                        .categoria(CategoriaProducto.ELECTRONICA)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Mouse Logitech MX Master 3S")
                        .descripcion("Mouse inalámbrico ergonómico recargable con sensor 8K DPI")
                        .stock(25)
                        .categoria(CategoriaProducto.ELECTRONICA)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Teclado Mecánico Keychron K2")
                        .descripcion("Teclado inalámbrico compacto 75% switches red retroiluminado")
                        .stock(8)
                        .categoria(CategoriaProducto.ELECTRONICA)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Monitor Dell UltraSharp 27")
                        .descripcion("Monitor IPS 4K UHD USB-C con calibración de color")
                        .stock(0)
                        .categoria(CategoriaProducto.ELECTRONICA)
                        .estado(false)
                        .build(),

                // --- VERDURA ---
                ProductoEntity.builder()
                        .nombre("Papa Amarilla Tumbay 1kg")
                        .descripcion("Papa amarilla fresca seleccionada ideal para puré o causa")
                        .stock(60)
                        .categoria(CategoriaProducto.VERDURA)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Cebolla Roja Arequipeña 1kg")
                        .descripcion("Cebolla roja fresca de calibre grande para aderezos y ceviche")
                        .stock(45)
                        .categoria(CategoriaProducto.VERDURA)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Tomate Italiano 1kg")
                        .descripcion("Tomate rojo maduro seleccionado para ensaladas y salsas")
                        .stock(30)
                        .categoria(CategoriaProducto.VERDURA)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Palta Hass Madura x Unidad")
                        .descripcion("Palta Hass cremosa de exportación lista para consumo")
                        .stock(0)
                        .categoria(CategoriaProducto.VERDURA)
                        .estado(false)
                        .build(),

                // --- CARNE ---
                ProductoEntity.builder()
                        .nombre("Pechuga de Pollo sin Hueso 1kg")
                        .descripcion("Filete de pechuga de pollo fresco envasado al vacío")
                        .stock(22)
                        .categoria(CategoriaProducto.CARNE)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Bife Angosto de Res 500g")
                        .descripcion("Corte premium de res madurado ideal para parrilla o sartén")
                        .stock(14)
                        .categoria(CategoriaProducto.CARNE)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Chuleta de Cerdo 1kg")
                        .descripcion("Chuleta de lomo de cerdo fresca cortada en porciones")
                        .stock(18)
                        .categoria(CategoriaProducto.CARNE)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Carne Molida Especial 500g")
                        .descripcion("Carne molida de res baja en grasa para guisos y hamburguesas")
                        .stock(5)
                        .categoria(CategoriaProducto.CARNE)
                        .estado(true)
                        .build(),

                // --- GOLOSINA ---
                ProductoEntity.builder()
                        .nombre("Chocolate Sublime Clásico 30g")
                        .descripcion("Chocolate con leche y maní tostado presentación individual")
                        .stock(120)
                        .categoria(CategoriaProducto.GOLOSINA)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Galletas Oreo Paquete x6")
                        .descripcion("Galletas de chocolate rellenas de crema sabor vainilla")
                        .stock(50)
                        .categoria(CategoriaProducto.GOLOSINA)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Gomitas Ambrosía Ositos 100g")
                        .descripcion("Gomitas masticables de sabores frutales surtidos")
                        .stock(35)
                        .categoria(CategoriaProducto.GOLOSINA)
                        .estado(true)
                        .build(),

                ProductoEntity.builder()
                        .nombre("Papas Lays Clásicas 160g")
                        .descripcion("Hojuelas de papa frita crujientes con sal tamaño familiar")
                        .stock(0)
                        .categoria(CategoriaProducto.GOLOSINA)
                        .estado(false)
                        .build()
        );

        productoRepository.saveAll(productosIniciales);
        log.info("DataLoader: {} productos cargados en H2 correctamente.", productosIniciales.size());
    }
}
