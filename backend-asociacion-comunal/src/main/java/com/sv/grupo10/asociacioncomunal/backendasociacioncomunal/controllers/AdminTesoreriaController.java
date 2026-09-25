package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.controllers;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.BalanceDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.CuotaRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.MovimientoRequestDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Cuota;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Movimiento;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.TesoreriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/admin/tesoreria")
public class AdminTesoreriaController {

    private final TesoreriaService tesoreriaService;

    public AdminTesoreriaController(TesoreriaService tesoreriaService) {
        this.tesoreriaService = tesoreriaService;
    }

    @GetMapping("/movimientos")
    public ResponseEntity<?> listarMovimientos(Authentication authentication) {
        try {
            return ResponseEntity.ok(tesoreriaService.listarMovimientos(authentication.getName()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @PostMapping("/movimientos")
    public ResponseEntity<?> registrarMovimiento(
            @RequestBody MovimientoRequestDTO request,
            Authentication authentication
    ) {
        try {
            Movimiento creado = tesoreriaService.registrarMovimiento(authentication.getName(), request);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<BalanceDTO> balance() {
        return ResponseEntity.ok(tesoreriaService.calcularBalance());
    }

    @GetMapping("/cuotas")
    public ResponseEntity<?> listarCuotas(Authentication authentication) {
        try {
            return ResponseEntity.ok(tesoreriaService.listarCuotas(authentication.getName()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @PostMapping("/cuotas")
    public ResponseEntity<?> crearCuota(
            @RequestBody CuotaRequestDTO request,
            Authentication authentication
    ) {
        try {
            Cuota creada = tesoreriaService.crearCuota(authentication.getName(), request);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }

    @PutMapping("/cuotas/{id}/pagar")
    public ResponseEntity<?> marcarPagada(
            @PathVariable String id,
            Authentication authentication
    ) {
        try {
            Cuota pagada = tesoreriaService.marcarCuotaPagada(authentication.getName(), id);
            return ResponseEntity.ok(pagada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("mensaje", e.getMessage()));
        }
    }
}
