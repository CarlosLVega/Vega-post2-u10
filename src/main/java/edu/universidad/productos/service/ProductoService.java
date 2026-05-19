package edu.universidad.productos.service;

import edu.universidad.productos.model.Producto;
import edu.universidad.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    public Producto buscar(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto procesarProducto(String n, Double precio, Integer stock) {
        if (n == null || n.equals("")) {
            throw new IllegalArgumentException("nombre requerido");
        }
        if (precio == null || precio <= 0) {
            throw new IllegalArgumentException("precio invalido");
        }
        if (precio > 999999) {
            throw new IllegalArgumentException("precio muy alto");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("stock invalido");
        }

        Producto producto = new Producto();
        producto.setNombre(n);
        producto.setPrecio(precio);
        producto.setStock(stock);
        return repo.save(producto);
    }
}
