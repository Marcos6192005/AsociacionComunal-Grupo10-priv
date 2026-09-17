package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminProyectoController {

    @GetMapping("/proyectos")
    public ResponseEntity<String> getProyectos(){
        return ResponseEntity.ok("[ACCESO PRIVADO] Panel de admin, solo usuarios autenticados");
    }
}
