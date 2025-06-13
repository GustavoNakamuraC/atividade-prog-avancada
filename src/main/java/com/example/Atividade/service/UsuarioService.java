package com.example.Atividade.service;

import com.example.Atividade.domain.Usuario;
import com.example.Atividade.infrastructure.repository.UsuarioRepository;
import com.example.Atividade.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository repository;

    public Usuario cadastrar(Usuario usuario){
        log.info("Iniciando cadastro. Usuario: {}", usuario.toString());

        Usuario usuarioSalvo = UsuarioMapper.entityParaDomain(
                repository.save(UsuarioMapper.domainParaEntity(usuario)));


        log.info("Cadastro realizado com sucesso. Usuario");
        return usuarioSalvo;
    }
}
