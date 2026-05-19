package edu.universidad.productos.controller;

import edu.universidad.productos.model.Producto;
import edu.universidad.productos.service.ProductoService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductoControllerTest {

    private final ProductoServiceStub productoService = new ProductoServiceStub();
    private final ProductoController productoController = new ProductoController(productoService);

    @Test
    void listarRetornaProductosDelServicio() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Mouse");
        producto.setPrecio(80000.0);
        producto.setStock(4);
        productoService.productos = List.of(producto);

        List<Producto> productos = productoController.listar();

        assertThat(productos).containsExactly(producto);
    }

    @Test
    void buscarRetornaProductoPorId() {
        Producto producto = new Producto();
        producto.setId(2L);
        productoService.productoBuscado = producto;

        Producto encontrado = productoController.buscar(2L);

        assertThat(encontrado.getId()).isEqualTo(2L);
    }

    @Test
    void crearDelegaDatosAlServicio() {
        Producto entrada = new Producto();
        entrada.setNombre("Pantalla");
        entrada.setPrecio(900000.0);
        entrada.setStock(3);
        productoService.productoCreado = entrada;

        Producto creado = productoController.crear(entrada);

        assertThat(creado).isEqualTo(entrada);
        assertThat(productoService.nombreRecibido).isEqualTo("Pantalla");
        assertThat(productoService.precioRecibido).isEqualTo(900000.0);
        assertThat(productoService.stockRecibido).isEqualTo(3);
    }

    private static class ProductoServiceStub extends ProductoService {

        private List<Producto> productos = new ArrayList<>();
        private Producto productoBuscado;
        private Producto productoCreado;
        private String nombreRecibido;
        private Double precioRecibido;
        private Integer stockRecibido;

        ProductoServiceStub() {
            super(null);
        }

        @Override
        public List<Producto> listar() {
            return productos;
        }

        @Override
        public Producto buscar(Long id) {
            return productoBuscado;
        }

        @Override
        public Producto procesarProducto(String nombre, Double precio, Integer stock) {
            this.nombreRecibido = nombre;
            this.precioRecibido = precio;
            this.stockRecibido = stock;
            return productoCreado;
        }
    }
}
