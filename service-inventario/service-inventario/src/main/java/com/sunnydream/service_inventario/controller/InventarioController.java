package com.sunnydream.service_inventario.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.sunnydream.service_inventario.model.Inventario;
import com.sunnydream.service_inventario.service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/inventarios")

@Tag(
    name = "Inventarios",
    description = "Gestión de inventarios"
)
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @Operation(summary = "Listar inventarios")
    @ApiResponse(responseCode = "200", description = "Inventarios encontrados")
    @GetMapping
    public List<Inventario> listar() {

        return service.listarInventarios();
    }

    @Operation(summary = "Buscar inventario por producto")
    @ApiResponse(responseCode = "200", description = "Inventario encontrado")
    @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<Inventario> buscarPorProducto(
            @PathVariable Long productoId) {

        return ResponseEntity.ok(
                service.buscarPorProducto(productoId));
    }

    @Operation(summary = "Crear inventario")
    @ApiResponse(responseCode = "201", description = "Inventario creado")
    @PostMapping
    public ResponseEntity<Inventario> guardar(
            @Valid @RequestBody Inventario inventario) {

        return new ResponseEntity<>(
                service.guardarInventario(inventario),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Descontar stock")
    @ApiResponse(responseCode = "200", description = "Stock actualizado")
    @PutMapping("/descontar/{productoId}/{cantidad}")
    public ResponseEntity<Inventario> descontar(
            @PathVariable Long productoId,
            @PathVariable int cantidad) {

        return ResponseEntity.ok(
                service.descontarStock(productoId, cantidad));
    }

    @Operation(summary = "Eliminar inventario")
    @ApiResponse(responseCode = "204", description = "Inventario eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminarInventario(id);

        return ResponseEntity.noContent().build();
    }
}