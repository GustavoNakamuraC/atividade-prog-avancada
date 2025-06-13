package com.example.Atividade.mapper;

import com.example.Atividade.controller.dto.UsuarioDto;
import com.example.Atividade.domain.Usuario;
import com.example.Atividade.infrastructure.entity.UsuarioEntity;

public class UsuarioMapper {

    public static Usuario dtoParaDomain(UsuarioDto dto) {
        return Usuario.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .nome(dto.getNome())
                .senha(dto.getSenha())
                .build();
    }

    public static UsuarioDto domainParaDto(Usuario domain) {
        return UsuarioDto.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .nome(domain.getNome())
                .senha(domain.getSenha())
                .build();
    }

    public static UsuarioEntity domainParaEntity(Usuario domain) {
        return UsuarioEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .nome(domain.getNome())
                .senha(domain.getSenha())
                .build();
    }

    public static Usuario entityParaDomain(UsuarioEntity entity) {
        return Usuario.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .nome(entity.getNome())
                .senha(entity.getSenha())
                .build();
    }
}
