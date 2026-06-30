package com.sunnydream.service_auth.dto;

import java.util.List;

import lombok.Data;

@Data
public class AuthRequest {
    private String nombreUsuario;
    private String contrasena;
    private String correo;
    private List<String> roles;
}