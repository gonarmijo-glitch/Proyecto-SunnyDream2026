package com.sunnydream.service_venta.dto;

import lombok.Data;

@Data
public class VentaRequest {
    private Long productoId;
    private int cantidad;
    private Long clienteId;
}