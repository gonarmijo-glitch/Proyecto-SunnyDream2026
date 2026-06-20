package com.sunnydream.service_inventario.controller.service;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sunnydream.service_inventario.model.Inventario;
import com.sunnydream.service_inventario.service.InventarioService;

public class InventarioController {
 private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Inventario> listar() {
        return service.listar();
    }

    @PostMapping
    public Inventario guardar(@RequestBody Inventario inventario) {
        return service.guardar(inventario);
    }

    @GetMapping("/producto/{productoId}")
    public Inventario buscarPorProducto(@PathVariable Long productoId) {
        return service.buscarPorProducto(productoId);
    }

    @PutMapping("/descontar/{productoId}/{cantidad}")
    public Inventario descontar(@PathVariable Long productoId, @PathVariable int cantidad) {
        return service.descontarStock(productoId, cantidad);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
