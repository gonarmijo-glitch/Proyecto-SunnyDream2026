package com.sunnydream.carrito.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunnydream.carrito.model.Carrito;
import com.sunnydream.carrito.service.CarritoService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/carritos")

@Tag(
    name = "Carritos",
    description = "Gestión de carritos"
)
public class CarritoController {
        private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @Operation(summary = "Listar carritos")
    @ApiResponse(responseCode = "200", description = "Carritos encontrados")
    @GetMapping
    public List<Carrito> listar() {
        return carritoService.listarCarritos();
    }

    @Operation(summary = "Buscar carrito por ID")
    @ApiResponse(responseCode = "200", description = "Carrito encontrado")
    @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Carrito> obtener(@PathVariable Long id) {

        return ResponseEntity.ok(
                carritoService.buscarCarritoPorId(id));
    }

    @Operation(summary = "Crear carrito")
    @ApiResponse(responseCode = "201", description = "Carrito creado")
    @PostMapping
    public ResponseEntity<Carrito> crear(
            @Valid @RequestBody Carrito carrito) {

        return new ResponseEntity<>(
                carritoService.guardarCarrito(carrito),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar carrito")
    @ApiResponse(responseCode = "200", description = "Carrito actualizado")
    @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Carrito carrito) {

        return ResponseEntity.ok(
                carritoService.actualizarCarrito(id, carrito));
    }

    @Operation(summary = "Eliminar carrito")
    @ApiResponse(responseCode = "204", description = "Carrito eliminado")
    @ApiResponse(responseCode = "404", description = "Carrito no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        carritoService.eliminarCarrito(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener carritos por estado")
    @ApiResponse(responseCode = "200", description = "Carritos encontrados")
    @GetMapping("/estado/{estado}")
    public List<Carrito> obtenerPorEstado(
            @PathVariable String estado) {

        return carritoService.obtenerCarritosPorEstado(estado);
    }

}
