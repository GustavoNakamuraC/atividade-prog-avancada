package com.example.Atividade.controller;

import com.example.Atividade.controller.dto.UsuarioDto;
import com.example.Atividade.mapper.UsuarioMapper;
import com.example.Atividade.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody UsuarioDto usuarioNovo){
        UsuarioDto usuarioDto = UsuarioMapper.domainParaDto(
                service.cadastrar(UsuarioMapper.dtoParaDomain(usuarioNovo)));

        return ResponseEntity.created(
                UriComponentsBuilder
                        .newInstance()
                        .path("/usuarios/{id}")
                        .buildAndExpand(usuarioDto.getId())
                        .toUri()
        ).body(usuarioDto);
    }

    @GetMapping("/todos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioDto>> listarTodos() {
        List<UsuarioDto> usuarios = service.listarTodos().stream().map(UsuarioMapper::domainParaDto).toList();

        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody UsuarioDto dto) {
        UsuarioDto atualizado = UsuarioMapper.domainParaDto(service.atualizar(id, UsuarioMapper.dtoParaDomain(dto)));

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
