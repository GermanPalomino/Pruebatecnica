package com.pruebatecnica.libros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
    name = "libro",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "nombre")
    }
)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del libro es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nombre;

    @Size(max = 300, message = "La descripción no puede superar 300 caracteres")
    private String descripcion;

    @NotBlank(message = "El autor es obligatorio")
    @Size(max = 150, message = "El autor no puede superar 150 caracteres")
    @Column(nullable = false, length = 150)
    private String autor;

    @NotNull(message = "La fecha de publicación es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate fechaPublicacion;

    @NotNull(message = "El número de ejemplares es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 ejemplar")
    private Integer numeroEjemplares;

    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.01", message = "El costo debe ser mayor a 0")
    private BigDecimal costo;

    public Libro() {}

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getNumeroEjemplares() {
        return numeroEjemplares;
    }

    public void setNumeroEjemplares(Integer numeroEjemplares) {
        this.numeroEjemplares = numeroEjemplares;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
}