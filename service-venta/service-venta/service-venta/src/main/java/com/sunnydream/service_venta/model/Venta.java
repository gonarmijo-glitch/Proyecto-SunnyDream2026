package com.sunnydream.service_venta.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long idCliente;

    private LocalDateTime fechaVenta;

    @Min(value = 1, message = "El total debe ser mayor a 0")
    private int total;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}