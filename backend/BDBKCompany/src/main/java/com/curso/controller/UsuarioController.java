package com.curso.controller;

import com.curso.dto.UsuarioInputDTO;
import com.curso.dto.UsuarioOutputDTO;
import com.curso.mapper.UsuarioMapper;
import com.curso.domains.Usuario;
import com.curso.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService,
                             UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public List<UsuarioOutputDTO> listar() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        return usuarioMapper.toDTOList(usuarios);
    }

    @GetMapping("/{id}")
    public UsuarioOutputDTO buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return usuarioMapper.toDTO(usuario);
    }

    @PostMapping
    public UsuarioOutputDTO criar(@RequestBody @Valid UsuarioInputDTO input) {
        Usuario usuario = usuarioMapper.toEntity(input);
        Usuario salvo = usuarioService.criar(usuario);
        return usuarioMapper.toDTO(salvo);
    }

    @PutMapping("/{id}")
    public UsuarioOutputDTO atualizar(@PathVariable Long id,
                                      @RequestBody @Valid UsuarioInputDTO input) {

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome(input.getNome());
        usuarioAtualizado.setEmail(input.getEmail());

        Usuario salvo = usuarioService.atualizar(id, usuarioAtualizado);
        return usuarioMapper.toDTO(salvo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
    }
}
