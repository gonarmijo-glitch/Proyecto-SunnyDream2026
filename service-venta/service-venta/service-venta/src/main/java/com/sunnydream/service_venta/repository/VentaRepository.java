package com.sunnydream.service_venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunnydream.service_venta.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    
    List<Venta> findByEstado(String estado);
}