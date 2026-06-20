package com.sunnydream.service_notificacion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sunnydream.service_notificacion.model.Notificacion;
import com.sunnydream.service_notificacion.repository.NotificacionRepository;

@Service
public class NotificacionService {

    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public List<Notificacion> listar() {
        return repository.findAll();
    }

    public Optional<Notificacion> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Notificacion> buscarPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public List<Notificacion> buscarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<Notificacion> buscarPorVenta(Long ventaId) {
        return repository.findByVentaId(ventaId);
    }

    public Notificacion guardar(Notificacion notificacion) {
        validarNotificacion(notificacion);
        notificacion.setTipo(notificacion.getTipo().trim().toUpperCase());
        notificacion.setMensaje(notificacion.getMensaje().trim());
        return repository.save(notificacion);
    }

    public Notificacion actualizar(Long id, Notificacion notificacion) {
        notificacion.setId(id);
        return guardar(notificacion);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    private void validarNotificacion(Notificacion notificacion) {
        if (notificacion.getVentaId() == null || notificacion.getVentaId() <= 0) {
            throw new RuntimeException("La venta es obligatoria");
        }

        if ((notificacion.getClienteId() == null || notificacion.getClienteId() <= 0)
                && (notificacion.getUsuarioId() == null || notificacion.getUsuarioId() <= 0)) {
            throw new RuntimeException("Debe indicar cliente o usuario");
        }

        if (notificacion.getTipo() == null || notificacion.getTipo().isBlank()) {
            throw new RuntimeException("El tipo de notificacion es obligatorio");
        }

        if (notificacion.getMensaje() == null || notificacion.getMensaje().isBlank()) {
            throw new RuntimeException("El mensaje es obligatorio");
        }

        if (notificacion.getMensaje().length() > 100) {
            throw new RuntimeException("El mensaje no puede superar 100 caracteres");
        }
    }
}
