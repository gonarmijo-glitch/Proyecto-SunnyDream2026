package com.sunnydream.promocion.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "promocion")
@AllArgsConstructor
@NoArgsConstructor
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromocion;

    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @DecimalMin(value = "0.0", message = "El descuento no puede ser negativo")
    private double descuento;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

}
