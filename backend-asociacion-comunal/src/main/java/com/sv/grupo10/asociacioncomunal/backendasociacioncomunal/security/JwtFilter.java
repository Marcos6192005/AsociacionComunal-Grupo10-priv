package com.sv.grupo10.asociacioncomunal.backendasociacioncomunal.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extraerToken(request);

        if (token != null && jwtProvider.validarToken(token)) {
            String correo = jwtProvider.getCorreo(token);
            String rol = jwtProvider.getRol(token);

            if (rol == null || rol.isBlank()) {
                rol = Roles.VECINO;
            }

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol));

            // Junta roles keep ROLE_ADMINISTRACION so /api/admin/** still matches.
            if (Roles.esRolDirectiva(rol) && !Roles.ADMINISTRACION.equals(Roles.normalizar(rol))) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + Roles.ADMINISTRACION));
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    correo,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private String extraerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
