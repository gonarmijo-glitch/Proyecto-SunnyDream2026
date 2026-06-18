package com.sunnydream.promocion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.sunnydream.promocion.model.Promocion;
import com.sunnydream.promocion.repository.PromocionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class PromocionServiceTest {

    @Mock
    private PromocionRepository promocionRepository;

    @InjectMocks
    private PromocionService promocionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarPromociones() {

        Promocion promo1 = new Promocion(
                1L,
                "BIENVENIDA15",
                10,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30));

        Promocion promo2 = new Promocion(
                2L,
                "SUNNYDREAM20",
                20,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(60));

        when(promocionRepository.findAll())
                .thenReturn(Arrays.asList(promo1, promo2));

        List<Promocion> promociones =
                promocionService.listarPromociones();

        assertEquals(2, promociones.size());
    }

    @Test
    void buscarPromocionPorId() {

        Promocion promocion = new Promocion(
                1L,
                "BIENVENIDA15",
                10,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30));

        when(promocionRepository.findById(1L))
                .thenReturn(Optional.of(promocion));

        Promocion resultado =
                promocionService.buscarPromocionPorId(1L);

        assertEquals("BIENVENIDA15", resultado.getCodigo());
    }

    @Test
    void guardarPromocion() {

        Promocion promocion = new Promocion(
                null,
                "BIENVENIDA15",
                10,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30));

        when(promocionRepository.save(promocion))
                .thenReturn(promocion);

        Promocion resultado =
                promocionService.guardarPromocion(promocion);

        assertNotNull(resultado);
    }

}
