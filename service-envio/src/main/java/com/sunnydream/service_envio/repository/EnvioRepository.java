package com.sunnydream.service_envio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunnydream.service_envio.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

    List<Envio> findByVentaId(Long ventaId);
}
