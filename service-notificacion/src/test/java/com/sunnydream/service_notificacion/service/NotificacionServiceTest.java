package com.sunnydream.service_notificacion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sunnydream.service_notificacion.model.Notificacion;
import com.sunnydream.service_notificacion.repository.NotificacionRepository;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceTest {

    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionService service;

    @Test
    void guardarNotificacionValida() {
        Notificacion notificacion = new Notificacion(null, 1L, null, 2L, "envio", "Pedido en camino");
        Notificacion guardada = new Notificacion(1L, 1L, null, 2L, "ENVIO", "Pedido en camino");

        when(repository.save(notificacion)).thenReturn(guardada);

        Notificacion resultado = service.guardar(notificacion);

        assertEquals("ENVIO", resultado.getTipo());
        verify(repository).save(notificacion);
    }

    @Test
    void noPermiteMensajeLargo() {
        String mensaje = "Este mensaje tiene mas de cien caracteres para validar que la regla de negocio simple funcione correctamente.";
        Notificacion notificacion = new Notificacion(null, 1L, null, 2L, "envio", mensaje);

        assertThrows(RuntimeException.class, () -> service.guardar(notificacion));
    }
}
