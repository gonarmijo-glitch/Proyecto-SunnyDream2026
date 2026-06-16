package com.sunnydream.promocion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunnydream.promocion.model.Promocion;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Long> {

    Optional<Promocion> findByCodigo(String codigo);

}
