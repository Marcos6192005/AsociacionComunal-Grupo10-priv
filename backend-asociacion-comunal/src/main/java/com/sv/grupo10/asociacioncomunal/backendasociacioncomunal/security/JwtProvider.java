package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.security;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    private final String SECRET_WORD = "AdescoSecretKeyProyectoProgra2UEES2026!!!";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_WORD.getBytes());

    private final long TIEMPO_EXPIRACION = 86400000;

    public String generarToken(String correo, String rol) {
        return Jwts.builder()
                .subject(correo)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION))
                .signWith(key)
                .compact();
    }

    public String getCorreo(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Boolean validarToken(String token){
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        }catch (Exception error){
            System.err.println("Token invalido o expirado..." + error.getMessage());
            return false;
        }
    }
}