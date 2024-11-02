package com.example.proyecto_practico_integrador.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = false)
    private Profesor profesor;

    private String nombre;
    private int duracion;

    public void setId(Long id) {
            this.id = id;
    }

    public Curso() {
    }

}
