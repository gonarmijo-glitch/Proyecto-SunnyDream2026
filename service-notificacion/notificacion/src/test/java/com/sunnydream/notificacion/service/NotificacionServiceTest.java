package com.sunnydream.notificacion.service;

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

import com.sunnydream.notificacion.model.Notificacion;
import com.sunnydream.notificacion.repository.NotificacionRepository;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionService service;

    @Test
    void testGuardarNotificacionExitosa() {
        Notificacion notificacion = new Notificacion();
        notificacion.setIdCliente(10L);
        notificacion.setMensaje("Bienvenido al sistema");
        notificacion.setTipo("EMAIL");
        notificacion.setFechaEnvio(LocalDateTime.now());

        when(repository.save(any(Notificacion.class))).thenAnswer(invocation -> {
            Notificacion n = invocation.getArgument(0);
            n.setId(1L);
            return n;
        });

        Notificacion resultado = service.guardar(notificacion);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(10L, resultado.getIdCliente());
        assertEquals("Bienvenido al sistema", resultado.getMensaje());
        assertEquals("EMAIL", resultado.getTipo());
        assertNotNull(resultado.getFechaEnvio());
        verify(repository, times(1)).save(any(Notificacion.class));
    }

    @Test
    void testGuardarNotificacionErroneaPorMensajeVacio() {
        Notificacion notificacion = new Notificacion();
        notificacion.setIdCliente(10L);
        notificacion.setMensaje("");
        notificacion.setTipo("EMAIL");

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            service.guardar(notificacion);
        });

        assertEquals("El cuerpo de la notificacion no puede estar vacio", excepcion.getMessage());
        verify(repository, never()).save(any(Notificacion.class));
    }
}