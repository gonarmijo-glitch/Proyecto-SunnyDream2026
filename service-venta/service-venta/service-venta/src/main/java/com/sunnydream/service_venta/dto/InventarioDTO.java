package com.sunnydream.service_venta.dto;

import lombok.Data;

@Data
public class InventarioDTO {

    private Long productoId;
    private String nombreProducto;
    private int stock;
    private int precio;

}