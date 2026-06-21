package com.sunnydream.envio.service;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sunnydream.envio.model.Envio;
import com.sunnydream.envio.repository.EnvioRepository;

@ExtendWith(MockitoExtension.class)
public class EnvioServiceTest {

    @Mock
    private EnvioRepository repository;

    @InjectMocks
    private EnvioService service;

    @Test
    void testGuardarEnvioExitoso() {
        Envio envio = new Envio();
        envio.setIdPedido(100L);
        envio.setDireccion("Av. Siempreviva 742");
        envio.setEstado("PROCESANDO");
        envio.setFechaEnvio(LocalDateTime.now());

        when(repository.save(any(Envio.class))).thenAnswer(invocation -> {
            Envio e = invocation.getArgument(0);
            e.setId(1L);
            return e;
        });

        Envio resultado = service.guardar(envio);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Av. Siempreviva 742", resultado.getDireccion());
        verify(repository, times(1)).save(any(Envio.class));
    }

    @Test
    void testGuardarEnvioErroneoPorDireccionVacia() {
        Envio envio = new Envio();
        envio.setIdPedido(100L);
        envio.setDireccion("");

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            service.guardar(envio);
        });

        assertEquals("La direccion de envio no puede estar vacia", excepcion.getMessage());
        verify(repository, never()).save(any(Envio.class));
    }
}
