package edu.universidad.productos.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductoTest {

    @Test
    void gettersYSettersMantienenDatosDelProducto() {
        Producto producto = new Producto();

        producto.setId(7L);
        producto.setNombre("Portatil");
        producto.setPrecio(2500000.0);
        producto.setStock(2);

        assertThat(producto.getId()).isEqualTo(7L);
        assertThat(producto.getNombre()).isEqualTo("Portatil");
        assertThat(producto.getPrecio()).isEqualTo(2500000.0);
        assertThat(producto.getStock()).isEqualTo(2);
    }
}
