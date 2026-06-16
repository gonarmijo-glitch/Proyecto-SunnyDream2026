package com.sunnydream.carrito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunnydream.carrito.model.Carrito;
import com.sunnydream.carrito.repository.CarritoRepository;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listarCarritos() {
        return carritoRepository.findAll();
    }

    public Carrito buscarCarritoPorId(Long id) {
        return carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));
    }

    public Carrito guardarCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    public Carrito actualizarCarrito(Long id, Carrito carritoActualizado) {

        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));

        carrito.setIdCarrito(carritoActualizado.getIdCarrito());
        carrito.setIdCliente(carritoActualizado.getIdCliente());
        carrito.setFechaCreacion(carritoActualizado.getFechaCreacion());
        carrito.setEstado(carritoActualizado.getEstado());

        return carritoRepository.save(carrito);
    }

    public void eliminarCarrito(Long id) {

        Carrito carrito = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado con ID: " + id));

        carritoRepository.delete(carrito);
    }

    public List<Carrito> obtenerCarritosPorEstado(String estado) {
        return carritoRepository.findByEstado(estado);
    }

}
