package com.sunnydream.promocion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunnydream.promocion.model.Promocion;
import com.sunnydream.promocion.repository.PromocionRepository;

@Service
public class PromocionService {
    @Autowired
    private PromocionRepository promocionRepository;

    public List<Promocion> listarPromociones() {
        return promocionRepository.findAll();
    }

    public Promocion buscarPromocionPorId(Long id) {
        return promocionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Promoción no encontrada con ID: " + id));
    }

    public Promocion guardarPromocion(Promocion promocion) {
        return promocionRepository.save(promocion);
    }

    public Promocion actualizarPromocion(Long id, Promocion promocionActualizada) {

    Promocion promocion = promocionRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Promoción no encontrada con ID: " + id));

    promocion.setCodigo(promocionActualizada.getCodigo());
    promocion.setDescuento(promocionActualizada.getDescuento());
    promocion.setFechaInicio(promocionActualizada.getFechaInicio());
    promocion.setFechaFin(promocionActualizada.getFechaFin());

    return promocionRepository.save(promocion);
}

    public void eliminarPromocion(Long id) {

    Promocion promocion = promocionRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Promoción no encontrada con ID: " + id));
     promocionRepository.delete(promocion);
    }

}
