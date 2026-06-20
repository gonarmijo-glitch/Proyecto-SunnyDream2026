package com.sunnydream.service_venta.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sunnydream.service_venta.dto.InventarioDTO;
import com.sunnydream.service_venta.dto.VentaRequest;
import com.sunnydream.service_venta.model.Venta;
import com.sunnydream.service_venta.repository.VentaRepository;

@Service
public class VentaService {

    private final VentaRepository repository;
    private final WebClient webClient;

    public VentaService(VentaRepository repository,
                        WebClient.Builder builder,
                        @Value("${inventario.url}") String inventarioUrl) {
        this.repository = repository;
        this.webClient = builder.baseUrl(inventarioUrl).build();
    }

    public List<Venta> listar() {
        return repository.findAll();
    }

    public Venta registrar(VentaRequest request) {
        if (request.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a cero");
        }

        InventarioDTO producto = webClient.get()
                .uri("/producto/{productoId}", request.getProductoId())
                .retrieve()
                .bodyToMono(InventarioDTO.class)
                .block();

        if (producto == null) {
            throw new RuntimeException("No se pudo obtener el producto desde inventario");
        }

        if (producto.getStock() < request.getCantidad()) {
            throw new RuntimeException("No hay stock suficiente");
        }

        webClient.put()
                .uri("/descontar/{productoId}/{cantidad}", request.getProductoId(), request.getCantidad())
                .retrieve()
                .bodyToMono(InventarioDTO.class)
                .block();

        Venta venta = new Venta();
        venta.setClienteId(request.getClienteId());
        venta.setProductoId(producto.getProductoId());
        venta.setNombreProducto(producto.getNombreProducto());
        venta.setCantidad(request.getCantidad());
        venta.setPrecioUnitario(producto.getPrecio());
        venta.setTotal(producto.getPrecio() * request.getCantidad());
        venta.setFecha(LocalDate.now());

        return repository.save(venta);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}