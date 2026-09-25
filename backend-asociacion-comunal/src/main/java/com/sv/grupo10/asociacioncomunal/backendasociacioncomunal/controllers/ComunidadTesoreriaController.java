package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.controllers;

import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.dtos.BalanceDTO;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.models.entities.Cuota;
import com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.services.TesoreriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comunidad/tesoreria")
public class ComunidadTesoreriaController {

    private final TesoreriaService tesoreriaService;

    public ComunidadTesoreriaController(TesoreriaService tesoreriaService) {
        this.tesoreriaService = tesoreriaService;
    }

    @GetMapping("/mis-cuotas")
    public ResponseEntity<List<Cuota>> misCuotas(Authentication authentication) {
        return ResponseEntity.ok(tesoreriaService.listarCuotasPorVecino(authentication.getName()));
    }

    @GetMapping("/balance")
    public ResponseEntity<BalanceDTO> balancePublico() {
        return ResponseEntity.ok(tesoreriaService.calcularBalance());
    }
}
