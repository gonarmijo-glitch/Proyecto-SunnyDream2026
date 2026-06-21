package com.sunnydream.envio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunnydream.envio.model.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long>{

}
