package com.sunnydream.service_inventario.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sunnydream.service_inventario.model.Inventario;
import com.sunnydream.service_inventario.repository.InventarioRepository;




@Service
public class InventarioService {
    private final InventarioRepository repository;

     public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    public List<Inventario> listarInventarios() {
        return repository.findAll();
    }

    public Inventario buscarPorProducto(Long productoId) {

        Inventario inventario = repository.findByProductoId(productoId);

        if (inventario == null) {
            throw new RuntimeException("Producto no encontrado en inventario");
        }

        return inventario;
    }

    public Inventario guardarInventario(Inventario inventario) {
        inventario.setId(null);
        return repository.save(inventario);
    }

    public Inventario descontarStock(Long productoId, int cantidad) {

        Inventario inventario = buscarPorProducto(productoId);

        if (cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a cero");
        }

        if (inventario.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        inventario.setStock(inventario.getStock() - cantidad);

        return repository.save(inventario);
    }

    public void eliminarInventario(Long id) {
        repository.deleteById(id);
    }
 //No se permite stock negativo.
 //El precio debe ser mayor a cero.
 //No se puede descontar más stock del disponible.

}
