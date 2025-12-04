package com.curso.controller;

import com.curso.dto.EntidadeInputDTO;
import com.curso.dto.EntidadeOutputDTO;
import com.curso.domains.Entidade;
import com.curso.domains.Usuario;
import com.curso.mapper.EntidadeMapper;
import com.curso.service.EntidadeService;
import com.curso.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entidades")
public class EntidadeController {

    private final EntidadeService entidadeService;
    private final UsuarioService usuarioService;
    private final EntidadeMapper entidadeMapper;

    public EntidadeController(EntidadeService entidadeService,
                              UsuarioService usuarioService,
                              EntidadeMapper entidadeMapper) {
        this.entidadeService = entidadeService;
        this.usuarioService = usuarioService;
        this.entidadeMapper = entidadeMapper;
    }

    @GetMapping
    public List<EntidadeOutputDTO> listarTodas() {
        List<Entidade> entidades = entidadeService.listarTodas();
        return entidadeMapper.toDTOList(entidades);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<EntidadeOutputDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        List<Entidade> entidades = entidadeService.listarPorUsuario(usuarioId);
        return entidadeMapper.toDTOList(entidades);
    }

    @GetMapping("/{id}")
    public EntidadeOutputDTO buscarPorId(@PathVariable Long id) {
        Entidade entidade = entidadeService.buscarPorId(id);
        return entidadeMapper.toDTO(entidade);
    }

    @PostMapping
    public EntidadeOutputDTO criar(@RequestBody @Valid EntidadeInputDTO input) {
        Usuario usuario = usuarioService.buscarPorId(input.getUsuarioId());

        Entidade entidade = entidadeMapper.toEntity(input, usuario);
        Entidade salva = entidadeService.criar(entidade);

        return entidadeMapper.toDTO(salva);
    }

    @PutMapping("/{id}")
    public EntidadeOutputDTO atualizar(@PathVariable Long id,
                                       @RequestBody @Valid EntidadeInputDTO input) {

        Entidade existente = entidadeService.buscarPorId(id);
        Usuario usuario = usuarioService.buscarPorId(input.getUsuarioId());

        entidadeMapper.copyToEntity(input, existente, usuario);

        Entidade salva = entidadeService.atualizar(existente);

        return entidadeMapper.toDTO(salva);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        entidadeService.excluir(id);
    }
}

