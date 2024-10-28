package com.example.proyecto_practico_integrador.repository;


import com.example.proyecto_practico_integrador.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByProfesorId(Long profesorId);
}
