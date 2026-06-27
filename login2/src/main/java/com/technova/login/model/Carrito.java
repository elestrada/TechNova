package com.technova.login.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "producto_id", nullable = false)
    private Long productoId;

    private Integer cantidad = 1;

    @Column(name = "fecha_agregado")
    private java.time.LocalDateTime fechaAgregado;

    // Constructores
    public Carrito() {}

    public Carrito(Long usuarioId, Long productoId, Integer cantidad) {
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public java.time.LocalDateTime getFechaAgregado() { return fechaAgregado; }
    public void setFechaAgregado(java.time.LocalDateTime fechaAgregado) { this.fechaAgregado = fechaAgregado; }
}