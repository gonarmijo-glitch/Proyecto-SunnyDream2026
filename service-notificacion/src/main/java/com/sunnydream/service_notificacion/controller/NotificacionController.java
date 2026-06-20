package com.sunnydream.service_notificacion.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunnydream.service_notificacion.model.Notificacion;
import com.sunnydream.service_notificacion.service.NotificacionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Notificacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Notificacion> buscarPorCliente(@PathVariable Long clienteId) {
        return service.buscarPorCliente(clienteId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Notificacion> buscarPorUsuario(@PathVariable Long usuarioId) {
        return service.buscarPorUsuario(usuarioId);
    }

    @GetMapping("/venta/{ventaId}")
    public List<Notificacion> buscarPorVenta(@PathVariable Long ventaId) {
        return service.buscarPorVenta(ventaId);
    }

    @PostMapping
    public Notificacion guardar(@RequestBody Notificacion notificacion) {
        return service.guardar(notificacion);
    }

    @PutMapping("/{id}")
    public Notificacion actualizar(@PathVariable Long id, @RequestBody Notificacion notificacion) {
        return service.actualizar(id, notificacion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
