package com.sunnydream.pago.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunnydream.pago.model.Pago;
import com.sunnydream.pago.service.PagoService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago){
        Pago nuevo = pagoService.crearPago(pago);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{idPago}")
    public ResponseEntity<Pago> obtenerPago(@PathVariable("idPago") Long idPago) {
        return pagoService.obtenerPagoPorId(idPago)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<Pago>> obtenerPagosPorVenta(@PathVariable("idVenta") Long idVenta) {
        List<Pago> lista = pagoService.obtenerPagosPorVenta(idVenta);
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Pago>> obtenerPagosPorCliente(@PathVariable("idCliente") Long idCliente) {
        List<Pago> lista = pagoService.obtenerPagosPorCliente(idCliente);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{idPago}/estado")
    public ResponseEntity<Pago> actualizarEstado(
            @PathVariable("idPago") Long idPago,
            @RequestParam("estado") String estado
    ) {
        Pago actualizado = pagoService.actualizarEstado(idPago, estado);
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listarTodos() {
        return ResponseEntity.ok(pagoService.listarTodos());
    }
}
