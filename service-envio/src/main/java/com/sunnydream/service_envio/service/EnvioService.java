package com.sunnydream.service_envio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sunnydream.service_envio.model.Envio;
import com.sunnydream.service_envio.repository.EnvioRepository;

@Service
public class EnvioService {

    private final EnvioRepository repository;

    public EnvioService(EnvioRepository repository) {
        this.repository = repository;
    }

    public List<Envio> listar() {
        return repository.findAll();
    }

    public Optional<Envio> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Envio> buscarPorVenta(Long ventaId) {
        return repository.findByVentaId(ventaId);
    }

    public Envio guardar(Envio envio) {
        validarEnvio(envio);
        envio.setEstado(normalizarEstado(envio.getEstado()));
        return repository.save(envio);
    }

    public Envio actualizar(Long id, Envio envio) {
        envio.setId(id);
        return guardar(envio);
    }

    public Envio actualizarEstado(Long id, String estado) {
        Envio envio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envio no encontrado"));

        envio.setEstado(normalizarEstado(estado));
        return repository.save(envio);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private void validarEnvio(Envio envio) {
        if (envio.getVentaId() == null || envio.getVentaId() <= 0) {
            throw new RuntimeException("La venta es obligatoria");
        }

        if (envio.getDireccionEntrega() == null || envio.getDireccionEntrega().isBlank()) {
            throw new RuntimeException("La direccion de entrega es obligatoria");
        }
    }

    private String normalizarEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            return "PENDIENTE";
        }

        String estadoNormalizado = estado.trim().toUpperCase();

        if (!estadoNormalizado.equals("PENDIENTE")
                && !estadoNormalizado.equals("EN CAMINO")
                && !estadoNormalizado.equals("ENTREGADO")
                && !estadoNormalizado.equals("CANCELADO")) {
            throw new RuntimeException("Estado de envio no valido");
        }

        return estadoNormalizado;
    }
}
