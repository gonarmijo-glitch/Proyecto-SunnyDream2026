package com.sunnydream.envio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunnydream.envio.model.Envio;
import com.sunnydream.envio.service.EnvioService;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> listar() {
        return envioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtener(@PathVariable Long id) {
        return envioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(
            @PathVariable Long id,
            @RequestBody Envio envio) {

        return ResponseEntity.ok(envioService.actualizar(id, envio));
    }

    @ApiResponse(responseCode = "204", description = "Envio eliminado")
    @ApiResponse(responseCode = "404", description = "Envio no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        envioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Envio> crear(@RequestBody Envio envio) {
        return ResponseEntity.ok(envioService.guardar(envio));
    }
}
