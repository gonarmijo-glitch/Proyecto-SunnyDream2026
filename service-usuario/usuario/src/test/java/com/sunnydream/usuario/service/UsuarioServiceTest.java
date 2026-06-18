package com.sunnydream.usuario.service;

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

import com.sunnydream.usuario.model.Usuario;
import com.sunnydream.usuario.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks
    private UsuarioService usuarioService;

    @DisplayName("Deberia guardar un usuario correctamente")
    @Test
    void guardarUsuarioTest(){
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword("secret123");
        usuario.setRol("ADMINISTRADOR");

        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        Usuario resultado = usuarioService.guardar(usuario);

        
        assertNotNull(resultado); 
        assertEquals(1L, resultado.getId()); 
        assertEquals("admin", resultado.getUsername()); 
        assertEquals("ADMINISTRADOR", resultado.getRol());
        
        verify(usuarioRepository, times(1)).save(usuario);
    }

}
