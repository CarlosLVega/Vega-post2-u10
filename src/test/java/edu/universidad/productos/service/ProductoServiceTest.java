package edu.universidad.productos.service;

import edu.universidad.productos.model.Producto;
import edu.universidad.productos.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Test
    void procesarProductoGuardaProductoValido() {
        ProductoService productoService = new ProductoService(productoRepository);
        when(productoRepository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Producto producto = productoService.procesarProducto(" Teclado ", 120000.0, 10);

        assertThat(producto.getNombre()).isEqualTo("Teclado");
        assertThat(producto.getPrecio()).isEqualTo(120000.0);
        assertThat(producto.getStock()).isEqualTo(10);
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    void procesarProductoRechazaNombreVacio() {
        ProductoService productoService = new ProductoService(productoRepository);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.procesarProducto("   ", 120000.0, 10));
    }

    @Test
    void buscarLanzaExcepcionCuandoProductoNoExiste() {
        ProductoService productoService = new ProductoService(productoRepository);
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> productoService.buscar(99L));

        assertThat(exception.getMessage()).isEqualTo("Producto no encontrado: 99");
    }

    @Test
    void buscarRetornaProductoExistente() {
        ProductoService productoService = new ProductoService(productoRepository);
        Producto producto = new Producto();
        producto.setId(1L);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto encontrado = productoService.buscar(1L);

        assertThat(encontrado.getId()).isEqualTo(1L);
    }

    @Test
    void listarRetornaProductosDelRepositorio() {
        ProductoService productoService = new ProductoService(productoRepository);
        Producto producto = new Producto();
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        assertThat(productoService.listar()).containsExactly(producto);
    }

    @Test
    void procesarProductoRechazaPrecioNuloOCero() {
        ProductoService productoService = new ProductoService(productoRepository);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.procesarProducto("Mouse", 0.0, 10));
    }

    @Test
    void procesarProductoRechazaPrecioMayorAlMaximo() {
        ProductoService productoService = new ProductoService(productoRepository);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.procesarProducto("Mouse", 1000000.0, 10));
    }

    @Test
    void procesarProductoRechazaStockNegativo() {
        ProductoService productoService = new ProductoService(productoRepository);

        assertThrows(IllegalArgumentException.class,
                () -> productoService.procesarProducto("Mouse", 120000.0, -1));
    }
}
