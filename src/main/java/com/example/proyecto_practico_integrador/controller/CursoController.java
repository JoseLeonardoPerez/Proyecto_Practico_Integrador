package com.example.proyecto_practico_integrador.controller;


import com.example.proyecto_practico_integrador.DTOs.CursoOutputDTO;
import com.example.proyecto_practico_integrador.DTOs.CursoinputDTO;
import com.example.proyecto_practico_integrador.mappers.CursoMapper;
import com.example.proyecto_practico_integrador.model.Curso;
import com.example.proyecto_practico_integrador.exceptions.NotFoundException;
import com.example.proyecto_practico_integrador.model.Profesor;
import com.example.proyecto_practico_integrador.service.CursoService;
import com.example.proyecto_practico_integrador.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private final CursoService cursoService;
    @Autowired
    private final ProfesorService profesorService;

    public CursoController(CursoService cursoService, ProfesorService profesorService) {
        this.cursoService = cursoService;
        this.profesorService = profesorService;
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
    /*public ResponseEntity<CursoOutputDTO> obtenerPorId(@PathVariable Long id) {
        Curso curso = cursoService.obtenerPorId(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        CursoOutputDTO cursoOutputDTO = CursoMapper.crearAPartirDe(curso);
        return ResponseEntity.ok(cursoOutputDTO);
    }*/

    @PostMapping
    public ResponseEntity<CursoOutputDTO> crear(@RequestBody CursoinputDTO cursoInputDTO) {
        if (cursoInputDTO.getProfesorId() == null) {
            throw new NotFoundException("ID de profesor no puede ser nulo");
        }
        // Busca el profesor por ID
        Profesor profesor = profesorService.obtenerPorId(cursoInputDTO.getProfesorId())
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado"));

        Curso curso = new Curso();
        curso.setNombre(cursoInputDTO.getNombre());
        curso.setDuracion(cursoInputDTO.getDuracion());
        curso.setProfesor(profesor); // Establece el profesor

        // Guarda el curso
        Curso cursoGuardado = cursoService.guardar(curso);

        // Mapea a DTO y devuelve respuesta
        CursoOutputDTO cursoOutputDTO = CursoMapper.crearAPartirDe(cursoGuardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoOutputDTO);
    }
    /*public Curso crear(@RequestBody Curso curso) {
        return cursoService.guardar(curso);
    }*/

    /*@PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoService.guardar(curso);
    }*/
    @PutMapping("/{id}")
   /* public ResponseEntity<Curso> actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        Optional<Curso> cursoExistente = cursoService.obtenerPorId(id);

        if (cursoExistente.isPresent()) {
            curso.setId(id); // Establece el ID del curso
            Curso cursoActualizado = cursoService.guardar(curso);
            //System.out.println("Curso actualizado: " + cursoActualizado);
           // return ResponseEntity.ok(cursoService.guardar(curso)); // Actualiza el curso
              return ResponseEntity.ok(cursoActualizado);

        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 si no existe
        }
    }*/
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @RequestBody CursoinputDTO cursoInputDTO) {
        Optional<Curso> cursoExistente = cursoService.obtenerPorId(id);

        if (cursoExistente.isPresent()) {
            Curso curso = cursoExistente.get(); // Obtener el curso existente
            curso.setNombre(cursoInputDTO.getNombre());
            curso.setDuracion(cursoInputDTO.getDuracion());

            // Buscar el profesor por ID si se proporciona
            if (cursoInputDTO.getProfesorId() != null) {
                Profesor profesor = profesorService.obtenerPorId(cursoInputDTO.getProfesorId())
                        .orElseThrow(() -> new NotFoundException("Profesor no encontrado"));
                curso.setProfesor(profesor); // Establecer el profesor
            }

            Curso cursoActualizado = cursoService.guardar(curso); // Guardar el curso actualizado
            return ResponseEntity.ok(cursoActualizado); // Retornar el curso actualizado
        } else {
            return ResponseEntity.notFound().build(); // Retornar 404 si no se encuentra
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