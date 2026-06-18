package com.sunnydream.cliente.service;

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

import com.sunnydream.cliente.model.Cliente;
import com.sunnydream.cliente.repository.ClienteRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

  @Mock
    private ClienteRepository clienteRepository;
    
    @InjectMocks
    private ClienteService clienteService;

    @DisplayName("Deberia guardar un cliente correctamente")
    @Test
    void guardarClienteTest(){
        
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");
        cliente.setApellido("Mendoza");

        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> {
            Cliente c = invocation.getArgument(0);
            c.setId(1L); 
            return c;
        });
        
        Cliente resultado = clienteService.guardar(cliente);

        assertNotNull(resultado); 
        assertEquals(1L, resultado.getId()); 
        assertEquals("Carlos", resultado.getNombre()); 
        
        verify(clienteRepository, times(1)).save(cliente);
    }  
}
