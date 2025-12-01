package com.curso.controller;

import com.curso.dto.ContaBancariaInputDTO;
import com.curso.dto.ContaBancariaOutputDTO;
import com.curso.mapper.ContaBancariaMapper;
import com.curso.domains.ContaBancaria;
import com.curso.domains.Usuario;
import com.curso.service.ContaBancariaService;
import com.curso.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas-bancarias")
public class ContaBancariaController {

    private final ContaBancariaService contaBancariaService;
    private final UsuarioService usuarioService;
    private final ContaBancariaMapper contaBancariaMapper;

    public ContaBancariaController(ContaBancariaService contaBancariaService,
                                   UsuarioService usuarioService,
                                   ContaBancariaMapper contaBancariaMapper) {
        this.contaBancariaService = contaBancariaService;
        this.usuarioService = usuarioService;
        this.contaBancariaMapper = contaBancariaMapper;
    }

    @GetMapping
    public List<ContaBancariaOutputDTO> listarTodas() {
        List<ContaBancaria> contas = contaBancariaService.listarTodas();
        return contaBancariaMapper.toDTOList(contas);
    }

    @GetMapping("/{id}")
    public ContaBancariaOutputDTO buscarPorId(@PathVariable Long id) {
        ContaBancaria conta = contaBancariaService.buscarPorId(id);
        return contaBancariaMapper.toDTO(conta);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ContaBancariaOutputDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        List<ContaBancaria> contas = contaBancariaService.listarPorUsuario(usuarioId);
        return contaBancariaMapper.toDTOList(contas);
    }

    @PostMapping
    public ContaBancariaOutputDTO criar(@RequestBody @Valid ContaBancariaInputDTO input) {
        Usuario usuario = usuarioService.buscarPorId(input.getUsuarioId());
        ContaBancaria conta = contaBancariaMapper.toEntity(input, usuario);
        ContaBancaria salva = contaBancariaService.criar(conta);
        return contaBancariaMapper.toDTO(salva);
    }

    @PutMapping("/{id}")
    public ContaBancariaOutputDTO atualizar(@PathVariable Long id,
                                            @RequestBody @Valid ContaBancariaInputDTO input) {

        ContaBancaria existente = contaBancariaService.buscarPorId(id);
        Usuario usuario = usuarioService.buscarPorId(input.getUsuarioId());

        contaBancariaMapper.copyToEntity(input, existente, usuario);

        ContaBancaria salva = contaBancariaService.atualizar(existente);
        return contaBancariaMapper.toDTO(salva);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        contaBancariaService.excluir(id);
    }
}