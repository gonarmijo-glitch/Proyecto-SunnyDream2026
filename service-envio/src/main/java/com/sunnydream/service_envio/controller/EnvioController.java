package com.sunnydream.service_envio.controller;

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

import com.sunnydream.service_envio.model.Envio;
import com.sunnydream.service_envio.service.EnvioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    private final EnvioService service;

    public EnvioController(EnvioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Envio> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/venta/{ventaId}")
    public List<Envio> buscarPorVenta(@PathVariable Long ventaId) {
        return service.buscarPorVenta(ventaId);
    }

    @PostMapping
    public Envio guardar(@RequestBody Envio envio) {
        return service.guardar(envio);
    }

    @PutMapping("/{id}")
    public Envio actualizar(@PathVariable Long id, @RequestBody Envio envio) {
        return service.actualizar(id, envio);
    }

    @PutMapping("/{id}/estado")
    public Envio actualizarEstado(@PathVariable Long id, @RequestBody Envio envio) {
        return service.actualizarEstado(id, envio.getEstado());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
