package com.example.proyecto_practico_integrador.mappers;

import com.example.proyecto_practico_integrador.DTOs.CursoOutputDTO;
import com.example.proyecto_practico_integrador.model.Curso;

public class CursoMapper {

    /*public static String crearAPartirDe(Curso curso) {*/
    public static CursoOutputDTO crearAPartirDe(Curso curso){
        return CursoOutputDTO.builder()
             /*   .builder()
                .build()
                .getNombre();*/
                .id(curso.getId())
                .nombre(curso.getNombre())
                .duracion(curso.getDuracion())
                .profesorNombre(curso.getProfesor() != null ? curso.getProfesor().getNombre() : null)
                .build();

    }
}
