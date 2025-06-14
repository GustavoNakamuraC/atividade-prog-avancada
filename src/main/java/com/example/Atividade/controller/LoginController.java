package com.example.Atividade.controller;

import com.example.Atividade.controller.dto.LoginRequest;
import com.example.Atividade.controller.dto.LoginResponse;
import com.example.Atividade.infrastructure.security.TokenGenerator;
import com.example.Atividade.infrastructure.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authManager;
    private final TokenGenerator gerador;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getSenha());

        authManager.authenticate(authToken);

        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String jwt = gerador.gerarToken(user);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
