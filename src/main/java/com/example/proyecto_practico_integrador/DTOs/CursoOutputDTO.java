package com.example.proyecto_practico_integrador.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CursoOutputDTO {
    private Long id;
    private String nombre;
    private int duracion;
    private String profesorNombre;

}
