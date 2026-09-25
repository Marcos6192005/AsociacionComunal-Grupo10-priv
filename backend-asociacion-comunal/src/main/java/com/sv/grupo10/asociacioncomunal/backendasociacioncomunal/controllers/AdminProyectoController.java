package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.controllers;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.ProyectoDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Proyecto;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.ProyectoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminProyectoController {

    private final ProyectoService proyectoService;

    public AdminProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyecto>> listarProyectos() {
        return ResponseEntity.ok(proyectoService.listarProyectos());
    }

    @GetMapping("/proyectos/{id}")
    public ResponseEntity<Proyecto> obtenerProyecto(@PathVariable String id) {
        return proyectoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/proyectos")
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody ProyectoDTO proyectoDTO) {
        Proyecto creado = proyectoService.crearProyecto(proyectoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/proyectos/{id}")
    public ResponseEntity<Proyecto> actualizarProyecto(@PathVariable String id, @RequestBody ProyectoDTO proyectoDTO) {
        return proyectoService.actualizarProyecto(id, proyectoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/proyectos/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable String id) {
        boolean eliminado = proyectoService.eliminarProyecto(id);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
