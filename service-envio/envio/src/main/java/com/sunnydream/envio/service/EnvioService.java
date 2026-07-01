package com.sunnydream.envio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunnydream.envio.model.Envio;
import com.sunnydream.envio.repository.EnvioRepository;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }

    public Optional<Envio> buscarPorId(Long id) {
        return envioRepository.findById(id);
    }

    public Envio guardar(Envio envio) {
        envio.setId(id:null);
        if (envio.getDireccion() == null || envio.getDireccion().trim().isEmpty()) {
            throw new IllegalArgumentException("La direccion de envio no puede estar vacia");
        }
        
        if (envio.getFechaEnvio() == null) {
            envio.setFechaEnvio(LocalDateTime.now());
        }
        
        if (envio.getEstado() == null) {
            envio.setEstado("PROCESANDO");
        }
        
        return envioRepository.save(envio);
    }
}
