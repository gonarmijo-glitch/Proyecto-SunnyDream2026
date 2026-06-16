package com.sunnydream.pago.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sunnydream.pago.model.Pago;
import com.sunnydream.pago.repository.PagoRepository;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {
    @Mock
    private PagoRepository pagoRepository; 

    @InjectMocks
    private PagoService pagoService; 

    @DisplayName("Deberia guardar un pago correctamente")
    @Test
    void crearPagoTest(){

        Pago pago = new Pago();
        pago.setMetodoPago("TARJETA");
        pago.setMonto(15000.0);

        when(pagoRepository.save(any(Pago.class))).thenAnswer(invocation -> {
            Pago p = invocation.getArgument(0);
            p.setIdPago(1L);
            return p;
        });

        Pago resultado = pagoService.crearPago(pago);

        assertNotNull(resultado); 
        assertEquals(1L, resultado.getIdPago()); 
        assertEquals("TARJETA", resultado.getMetodoPago()); 
        
        verify(pagoRepository, times(1)).save(pago);
    }
}
