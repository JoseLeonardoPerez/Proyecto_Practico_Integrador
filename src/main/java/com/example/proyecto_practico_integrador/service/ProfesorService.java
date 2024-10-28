package com.example.proyecto_practico_integrador.service;


import com.example.proyecto_practico_integrador.model.Profesor;
import com.example.proyecto_practico_integrador.repository.ProfesorRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {
    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> obtenerTodos() {
        return (List<Profesor>) profesorRepository.findAll();
    }

    public Optional<Profesor> obtenerPorId(Long id) {
        return profesorRepository.findById(id);
    }

    public Profesor guardar(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void eliminar(Long id) {
        profesorRepository.deleteById(id);
    }
}
