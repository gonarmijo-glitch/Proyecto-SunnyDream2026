package com.sunnydream.service_auth.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sunnydream.service_auth.dto.AuthRequest;
import com.sunnydream.service_auth.model.Rol;
import com.sunnydream.service_auth.model.Usuario;
import com.sunnydream.service_auth.repository.RolRepository;
import com.sunnydream.service_auth.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void testRegistrarUsuarioExitoso() {
        AuthRequest request = new AuthRequest();
        request.setNombreUsuario("testuser");
        request.setContrasena("password123");
        request.setCorreo("test@sunnydream.com");

        when(usuarioRepository.findByNombreUsuario(request.getNombreUsuario())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("passwordCifrado");

        Rol rolFalso = new Rol();
        rolFalso.setNombreRol("CLIENTE");
        when(rolRepository.findByNombreRol("CLIENTE")).thenReturn(Optional.of(rolFalso));


        String resultado = authService.registrar(request);

        assertEquals("Usuario Registrado", resultado);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}
