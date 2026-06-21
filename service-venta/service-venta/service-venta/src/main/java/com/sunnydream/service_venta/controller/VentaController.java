package com.sunnydream.service_venta.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunnydream.service_venta.model.Venta;
import com.sunnydream.service_venta.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/ventas")

@Tag(
    name = "Ventas",
    description = "Gestión de ventas"
)
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @Operation(summary = "Listar ventas")
    @ApiResponse(responseCode = "200", description = "Ventas encontradas")
    @GetMapping
    public List<Venta> listar() {
        return ventaService.listarVentas();
    }

    @Operation(summary = "Buscar venta por ID")
    @ApiResponse(responseCode = "200", description = "Venta encontrada")
    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtener(@PathVariable Long id) {

        return ResponseEntity.ok(
                ventaService.buscarVentaPorId(id));
    }

    @Operation(summary = "Crear venta")
    @ApiResponse(responseCode = "201", description = "Venta creada")
    @PostMapping
    public ResponseEntity<Venta> crear(
            @Valid @RequestBody Venta venta) {

        return new ResponseEntity<>(
                ventaService.guardarVenta(venta),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar venta")
    @ApiResponse(responseCode = "200", description = "Venta actualizada")
    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Venta venta) {

        return ResponseEntity.ok(
                ventaService.actualizarVenta(id, venta));
    }

    @Operation(summary = "Eliminar venta")
    @ApiResponse(responseCode = "204", description = "Venta eliminada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        ventaService.eliminarVenta(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener ventas por estado")
    @ApiResponse(responseCode = "200", description = "Ventas encontradas")
    @GetMapping("/estado/{estado}")
    public List<Venta> obtenerPorEstado(
            @PathVariable String estado) {

        return ventaService.obtenerVentasPorEstado(estado);
    }
}