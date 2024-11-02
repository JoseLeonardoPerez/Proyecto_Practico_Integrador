package com.example.proyecto_practico_integrador.service;


import com.example.proyecto_practico_integrador.model.Curso;
import com.example.proyecto_practico_integrador.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CursoService {

    @Autowired
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> obtenerTodos() {
        return (List<Curso>) cursoRepository.findAll();
    }

    public List<Curso> obtenerPorProfesorId(Long profesorId) {
        return cursoRepository.findByProfesorId(profesorId);
    }

    public Optional<Curso> obtenerPorId(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        System.out.println("Curso encontrado: " + curso); // Agregar esta línea para depuración
        return curso;
    }

    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }
}
