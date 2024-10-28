package com.example.proyecto_practico_integrador.controller;


import com.example.proyecto_practico_integrador.model.Curso;
import com.example.proyecto_practico_integrador.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursoService.obtenerTodos();
    }

    @GetMapping("/profesor/{profesorId}")
    public List<Curso> obtenerPorProfesorId(@PathVariable Long profesorId) {
        return cursoService.obtenerPorProfesorId(profesorId);
    }

    @GetMapping("/{id}")
    public Optional<Curso> obtenerPorId(@PathVariable Long id) {
        return cursoService.obtenerPorId(id);
    }

    @PostMapping
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.guardar(curso);
    }

    /*@PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoService.guardar(curso);
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        Optional<Curso> cursoExistente = cursoService.obtenerPorId(id);

        if (cursoExistente.isPresent()) {
            curso.setId(id); // Establece el ID del curso
            Curso cursoActualizado = cursoService.guardar(curso);
            System.out.println("Curso actualizado: " + cursoActualizado);
           // return ResponseEntity.ok(cursoService.guardar(curso)); // Actualiza el curso
              return ResponseEntity.ok(cursoActualizado);

        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 si no existe
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Curso> cursoExistente = cursoService.obtenerPorId(id);
     //   if (cursoService.obtenerPorId(id).isPresent()) {
         if (cursoExistente.isPresent()) {
            cursoService.eliminar(id);
            //return ResponseEntity.ok().build(); // 200 OK
             System.out.println("Curso eliminado con ID: " + id);
             return ResponseEntity.noContent().build();
        } else {
             System.out.println("Curso no encontrado con ID: " + id);
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}