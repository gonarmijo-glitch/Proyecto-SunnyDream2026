package com.sunnydream.pago.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunnydream.pago.model.Pago;
import com.sunnydream.pago.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public Pago crearPago(Pago pago) {
        
        if (pago.getMetodoPago() == null || pago.getMetodoPago().isBlank()) {
            throw new IllegalArgumentException("Método de pago no puede ser vacío");
        }

        
        if (pago.getEstado() == null || pago.getEstado().isBlank()) {
            pago.setEstado("PENDIENTE");
        }

        return pagoRepository.save(pago);
    }

    public Optional<Pago> obtenerPagoPorId(Long idPago) {
        return pagoRepository.findById(idPago);
    }

    public List<Pago> obtenerPagosPorVenta(Long idVenta) {
        return pagoRepository.findByIdVenta(idVenta);
    }

    public List<Pago> obtenerPagosPorCliente(Long idCliente) {
        return pagoRepository.findByIdCliente(idCliente);
    }

    public Pago actualizarEstado(Long idPago, String nuevoEstado) {
        Pago pago = pagoRepository.findById(idPago)
                .orElseThrow(() -> new IllegalArgumentException("Pago no encontrado con id: " + idPago));

        pago.setEstado(nuevoEstado.toUpperCase());
        return pagoRepository.save(pago);
    }

    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }
}
