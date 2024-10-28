package com.example.proyecto_practico_integrador.mappers;

import com.example.proyecto_practico_integrador.DTOs.CursoOutputDTO;
import com.example.proyecto_practico_integrador.model.Curso;

public class CursoMapper {

    public static String crearAPartirDe(Curso curso) {
        return CursoOutputDTO
                .builder()
                .build()
                .getNombre();

    }
}
