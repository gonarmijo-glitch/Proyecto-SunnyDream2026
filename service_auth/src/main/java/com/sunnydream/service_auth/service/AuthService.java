package com.sunnydream.service_auth.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunnydream.service_auth.dto.AuthRequest;
import com.sunnydream.service_auth.model.Rol;
import com.sunnydream.service_auth.model.Usuario;
import com.sunnydream.service_auth.repository.RolRepository;
import com.sunnydream.service_auth.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Value("${jwt.secret}")
    private String secreto;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registrar(AuthRequest request) {
        if (usuarioRepository.findByNombreUsuario(request.getNombreUsuario()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe.");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUsuario(request.getNombreUsuario());
        nuevoUsuario.setCorreo(request.getCorreo());
        nuevoUsuario.setContrasena(passwordEncoder.encode(request.getContrasena()));

        // Aplicamos la lógica comercial de SunnyDream usando CLIENTE
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            Rol rolPorDefecto = rolRepository.findByNombreRol("CLIENTE")
                    .orElseThrow(() -> new RuntimeException("Error: El rol CLIENTE no existe en la DB."));
            nuevoUsuario.getRoles().add(rolPorDefecto);
        } else {
            for (String nombreRol : request.getRoles()) {
                Rol rolEncontrado = rolRepository.findByNombreRol(nombreRol.toUpperCase())
                        .orElseThrow(() -> new RuntimeException("Error: El rol " + nombreRol + " no existe en la DB."));
                nuevoUsuario.getRoles().add(rolEncontrado);
            }
        }

        usuarioRepository.save(nuevoUsuario);
        return "Usuario Registrado";
    }

    @Transactional(readOnly = true)
    public String login(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        List<String> rolesList = usuario.getRoles().stream()
                .map(rol -> rol.getNombreRol())
                .collect(Collectors.toList());

        java.util.Date ahora = new java.util.Date();
        java.util.Date expiracion = new java.util.Date(ahora.getTime() + 86400000);

        return Jwts.builder()
                .setSubject(usuario.getNombreUsuario())
                .claim("roles", rolesList)
                .setIssuedAt(ahora)
                .setExpiration(expiracion)
                .signWith(Keys.hmacShaKeyFor(secreto.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }
}