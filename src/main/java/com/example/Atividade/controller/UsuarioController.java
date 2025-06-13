package com.example.Atividade.controller;

import com.example.Atividade.controller.dto.UsuarioDto;
import com.example.Atividade.mapper.UsuarioMapper;
import com.example.Atividade.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
}
