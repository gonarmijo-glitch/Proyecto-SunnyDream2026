package com.sunnydream.productos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.sunnydream.productos.model.Producto;
import com.sunnydream.productos.repository.ProductoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductoServiceTest {

    
    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarProductos() {

        Producto producto1 = new Producto(1L, "scrunchie", "accesorio", 1000, 20);
        Producto producto2 = new Producto(2L, "pop socket", "accesorio", 4000, 11);

        when(productoRepository.findAll())
                .thenReturn(Arrays.asList(producto1, producto2));

        List<Producto> productos = productoService.listarProductos();

        assertEquals(2, productos.size());
    }

    @Test
    void buscarProductoPorId() {

        Producto producto = new Producto(1L, "scrunchie", "accesorio", 1000, 20);

        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));

        Producto resultado = productoService.buscarProductoPorId(1L);

        assertEquals("scrunchie", resultado.getNombre());
    }

    @Test
    void buscarProductoPorIdNoExiste() {

        when(productoRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> productoService.buscarProductoPorId(99L));

        assertEquals(
                "Producto no encontrado con ID: 99",
                exception.getMessage());
    }

    @Test
    void guardarProducto() {

        Producto producto = new Producto(null, "scrunchie", "accesorio", 1000, 20);

        when(productoRepository.save(producto))
                .thenReturn(producto);

        Producto resultado = productoService.guardarProducto(producto);

        assertNotNull(resultado);
    }

    @Test
    void actualizarProducto() {

        Producto productoExistente =
                new Producto(1L, "scrunchie", "accesorio", 1000, 20);

        Producto productoActualizado =
                new Producto(1L, "scrunchie premium", "accesorio", 1500, 30);

        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(productoExistente));

        when(productoRepository.save(productoExistente))
                .thenReturn(productoExistente);

        Producto resultado =
                productoService.actualizarProducto(1L, productoActualizado);

        assertEquals("scrunchie premium", resultado.getNombre());
        assertEquals(1500, resultado.getPrecio());
        assertEquals(30, resultado.getStock());
    }

    @Test
    void eliminarProducto() {

        Producto producto =
                new Producto(1L, "scrunchie", "accesorio", 1000, 20);

        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));

        productoService.eliminarProducto(1L);

        verify(productoRepository, times(1)).delete(producto);
    }

    @Test
    void obtenerProductosConStockBajo() {

        Producto producto =
                new Producto(1L, "scrunchie", "accesorio", 1000, 5);

        when(productoRepository.findByStockLessThan(10))
                .thenReturn(List.of(producto));

        List<Producto> resultado =
                productoService.obtenerProductosConStockBajo();

        assertEquals(1, resultado.size());
    }

}
