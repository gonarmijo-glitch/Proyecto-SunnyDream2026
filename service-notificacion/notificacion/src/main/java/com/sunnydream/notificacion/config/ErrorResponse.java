package com.sunnydream.notificacion.config;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String mensaje;
    private int codigo;
    private LocalDateTime fecha;

}
