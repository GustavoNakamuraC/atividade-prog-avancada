package com.example.Atividade.service;

import com.example.Atividade.controller.dto.UsuarioDto;
import com.example.Atividade.domain.Usuario;
import com.example.Atividade.infrastructure.repository.UsuarioRepository;
import com.example.Atividade.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Usuario> listarTodos() {
        log.info("Iniciando processo de listar todos os usuario.");

        List<Usuario> usuarioList = repository.findAll().stream().map(UsuarioMapper::entityParaDomain).toList();

        log.info("Listagem finalizada. Listagem: {}", usuarioList);
        return usuarioList;
    }

    public Usuario atualizar(Long id, Usuario usuarioNovo) {
        log.info("Iniciando atualização de dados do usuario. Id do usuario: {}, Novos dados: {}", id, usuarioNovo.toString());

        Usuario usuarioVelho = consultarPorId(id);
        usuarioVelho.alterarDados(usuarioNovo);

        Usuario usuarioSalvo = UsuarioMapper.entityParaDomain(repository.save(UsuarioMapper.domainParaEntity(usuarioVelho)));

        log.info("Usuario atualizado com sucesso. Usuario novo: {}", usuarioSalvo.toString());
        return usuarioSalvo;
    }

    private Usuario consultarPorId(Long id){
        Optional<Usuario> usuarioOptional = repository.findById(id).map(UsuarioMapper::entityParaDomain);

        if (usuarioOptional.isEmpty()){
            log.error("Usuario não encontrado.");
            throw new RuntimeException("Usuario não encontrado.");
        }

        return usuarioOptional.get();
    }

    public void deletar(Long id) {
        log.info("Iniciando processo de deleção do usuario. Id do usuario: {}", id);

        consultarPorId(id);
        repository.deleteById(id);

        log.info("Deletado com sucesso");
    }
}
