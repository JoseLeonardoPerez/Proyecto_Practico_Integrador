package com.example.proyecto_practico_integrador.controller;

import com.example.proyecto_practico_integrador.model.Profesor;
import com.example.proyecto_practico_integrador.service.ProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public List<Profesor> obtenerTodos() {
        return profesorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Profesor> obtenerPorId(@PathVariable Long id) {
        return profesorService.obtenerPorId(id);
    }

    @PostMapping
    public Profesor crear(@RequestBody Profesor profesor) {
        return profesorService.guardar(profesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizar(@PathVariable Long id, @RequestBody Profesor profesor) {
        Optional<Profesor> profesorExistente = profesorService.obtenerPorId(id);

        if (profesorExistente.isPresent()) {
            profesor.setId(id); // Establece el ID del profesor

            Profesor actualizado = profesorService.guardar(profesor); // Guardar el profesor
            return ResponseEntity.ok(actualizado); // Retornar el profesor actualizado
        } else {
            return ResponseEntity.notFound().build(); // Retornar 404 si no se encuentra
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Profesor> profesorExistente = profesorService.obtenerPorId(id);

        if (profesorExistente.isPresent()) {
            profesorService.eliminar(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
