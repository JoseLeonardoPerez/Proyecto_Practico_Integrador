package com.example.proyecto_practico_integrador.DTOs;

import lombok.Data;
@Data
public class CursoinputDTO {
    private Long id;
    private String nombre;
    private int duracion;
    private Long profesorId;
}
