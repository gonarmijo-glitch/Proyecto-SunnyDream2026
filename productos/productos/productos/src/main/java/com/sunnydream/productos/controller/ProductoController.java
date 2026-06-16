package com.sunnydream.productos.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sunnydream.productos.model.Producto;
import com.sunnydream.productos.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/productos")

@Tag(
    name = "Productos",
    description = "Gestión de productos"
)
public class ProductoController {
     private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Listar productos")
    @ApiResponse(responseCode = "200", description = "Productos encontrados")
    @GetMapping
    public List<Producto> listar() {
        return productoService.listarProductos();
    }

    @Operation(summary = "Buscar producto por ID")
    @ApiResponse(responseCode = "200", description = "Producto encontrado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                productoService.buscarProductoPorId(id));
    }

    @Operation(summary = "Crear producto")
    @ApiResponse(responseCode = "201", description = "Producto creado")
    @PostMapping
    public ResponseEntity<Producto> crear(
            @Valid @RequestBody Producto producto){

        return new ResponseEntity<>(
                productoService.guardarProducto(producto),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar producto")
    @ApiResponse(responseCode = "200", description = "Producto actualizado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Producto producto){

        return ResponseEntity.ok(
                productoService.actualizarProducto(id, producto));
    }

    @Operation(summary = "Eliminar producto")
    @ApiResponse(responseCode = "204", description = "Producto eliminado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener productos con stock bajo")
    @ApiResponse(responseCode = "200", description = "Productos encontrados")
    @GetMapping("/stock-bajo")
    public List<Producto> stockBajo() {

        return productoService.obtenerProductosConStockBajo();
    }

}
