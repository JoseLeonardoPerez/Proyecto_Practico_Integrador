package com.example.proyecto_practico_integrador.repository;

import com.example.proyecto_practico_integrador.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
}