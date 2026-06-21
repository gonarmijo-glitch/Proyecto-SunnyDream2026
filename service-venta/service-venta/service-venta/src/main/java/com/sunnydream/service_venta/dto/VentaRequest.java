package com.sunnydream.service_venta.dto;

import lombok.Data;

@Data
public class VentaRequest {
    private Long idCliente;
    private Long productoId;
    private int cantidad;

}