package com.sv.grupo10.asociacioncomunal.backendasociaoncomunal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String testConnection() {
        return "Servidor backend activo. No se encontraron errores. Java 21";
    }
}