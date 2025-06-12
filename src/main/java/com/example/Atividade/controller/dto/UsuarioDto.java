package com.example.Atividade.controller.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UsuarioDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;
}
