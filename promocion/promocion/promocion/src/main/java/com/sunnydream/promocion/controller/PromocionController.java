package com.sunnydream.promocion.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunnydream.promocion.model.Promocion;
import com.sunnydream.promocion.service.PromocionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/promociones")

@Tag(
    name = "Promociones",
    description = "Gestión de promociones"
)
public class PromocionController {

    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    @Operation(summary = "Listar promociones")
    @ApiResponse(responseCode = "200", description = "Promociones encontradas")
    @GetMapping
    public List<Promocion> listar() {
        return promocionService.listarPromociones();
    }

    @Operation(summary = "Buscar promoción por ID")
    @ApiResponse(responseCode = "200", description = "Promoción encontrada")
    @ApiResponse(responseCode = "404", description = "Promoción no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Promocion> obtener(@PathVariable Long id) {

        return ResponseEntity.ok(
                promocionService.buscarPromocionPorId(id));
    }

    @Operation(summary = "Crear promoción")
    @ApiResponse(responseCode = "201", description = "Promoción creada")
    @PostMapping
    public ResponseEntity<Promocion> crear(
            @Valid @RequestBody Promocion promocion) {

        return new ResponseEntity<>(
                promocionService.guardarPromocion(promocion),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar promoción")
    @ApiResponse(responseCode = "200", description = "Promoción actualizada")
    @ApiResponse(responseCode = "404", description = "Promoción no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Promocion> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Promocion promocion) {

        return ResponseEntity.ok(
                promocionService.actualizarPromocion(id, promocion));
    }

    @Operation(summary = "Eliminar promoción")
    @ApiResponse(responseCode = "204", description = "Promoción eliminada")
    @ApiResponse(responseCode = "404", description = "Promoción no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        promocionService.eliminarPromocion(id);

        return ResponseEntity.noContent().build();
    }
}
