package com.sunnydream.service_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunnydream.service_inventario.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    Inventario findByProductoId(Long productoId);
}
