package com.example.Atividade.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Role role;
}
