package com.technova.login.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")  // ← Esto es clave
    private Long id;
    
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;
    
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    
    private Boolean activo;
    
    public Usuario() {}
    
    public Usuario(String email, String contrasenaHash, Boolean activo) {
        this.email = email;
        this.contrasenaHash = contrasenaHash;
        this.activo = activo;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getContrasenaHash() { return contrasenaHash; }
    public void setContrasenaHash(String contrasenaHash) { this.contrasenaHash = contrasenaHash; }
    
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}