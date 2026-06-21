package com.sunnydream.service_venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunnydream.service_venta.model.Venta;
import com.sunnydream.service_venta.repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Venta buscarVentaPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada con ID: " + id));
    }

    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta actualizarVenta(Long id, Venta ventaActualizada) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada con ID: " + id));

        venta.setIdCliente(ventaActualizada.getIdCliente());
        venta.setFechaVenta(ventaActualizada.getFechaVenta());
        venta.setTotal(ventaActualizada.getTotal());
        venta.setEstado(ventaActualizada.getEstado());

        return ventaRepository.save(venta);
    }

    public void eliminarVenta(Long id) {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Venta no encontrada con ID: " + id));

        ventaRepository.delete(venta);
    }

    public List<Venta> obtenerVentasPorEstado(String estado) {
        return ventaRepository.findByEstado(estado);
    }
}