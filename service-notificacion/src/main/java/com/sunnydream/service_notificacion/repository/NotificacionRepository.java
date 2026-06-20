package com.sunnydream.service_notificacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunnydream.service_notificacion.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    List<Notificacion> findByClienteId(Long clienteId);

    List<Notificacion> findByUsuarioId(Long usuarioId);

    List<Notificacion> findByVentaId(Long ventaId);
}
