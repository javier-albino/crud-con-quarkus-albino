package com.albino.model;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trabajador extends PanacheEntity {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id autoincremental
    private Long id; 

    public String nombre;
    public String email;

    @ManyToOne
    @JoinColumn(name = "trabajo_id")
    @JsonIgnore //  evitar serialización circular 
    public Trabajo trabajo;

    // Constructor vacío
    public Trabajador() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }
}
