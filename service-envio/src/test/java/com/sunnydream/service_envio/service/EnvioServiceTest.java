package com.sunnydream.service_envio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sunnydream.service_envio.model.Envio;
import com.sunnydream.service_envio.repository.EnvioRepository;

@ExtendWith(MockitoExtension.class)
class EnvioServiceTest {

    @Mock
    private EnvioRepository repository;

    @InjectMocks
    private EnvioService service;

    @Test
    void guardarEnvioValido() {
        Envio envio = new Envio(null, 1L, "Av. Siempre Viva 123", null);
        Envio envioGuardado = new Envio(1L, 1L, "Av. Siempre Viva 123", "PENDIENTE");

        when(repository.save(envio)).thenReturn(envioGuardado);

        Envio resultado = service.guardar(envio);

        assertEquals("PENDIENTE", resultado.getEstado());
        verify(repository).save(envio);
    }

    @Test
    void noPermiteDireccionVacia() {
        Envio envio = new Envio(null, 1L, "", "PENDIENTE");

        assertThrows(RuntimeException.class, () -> service.guardar(envio));
    }
}
