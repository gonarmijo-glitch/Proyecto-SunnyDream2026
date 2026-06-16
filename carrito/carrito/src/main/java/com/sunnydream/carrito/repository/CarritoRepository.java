package com.sunnydream.carrito.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunnydream.carrito.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{
    
    List<Carrito> findByEstado(String estado);

}
