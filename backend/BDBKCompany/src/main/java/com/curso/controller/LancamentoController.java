package com.curso.controller;

import com.curso.dto.LancamentoInputDTO;
import com.curso.dto.LancamentoOutputDTO;
import com.curso.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lancamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @GetMapping
    public List<LancamentoOutputDTO> listar(Pageable pageable) {
        return lancamentoService.listarPorUsuario(1L, pageable);
    }

    @GetMapping("/{id}")
    public LancamentoOutputDTO buscarPorId(@PathVariable Long id) {
        return lancamentoService.buscarPorId(id, 1L);
    }

    @PostMapping
    public LancamentoOutputDTO criar(@RequestBody @Valid LancamentoInputDTO input) {
        return lancamentoService.criar(input, 1L);
    }

    @PutMapping("/{id}")
    public LancamentoOutputDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid LancamentoInputDTO input
    ) {
        return lancamentoService.atualizar(id, input, 1L);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        lancamentoService.excluir(id, 1L);
    }
}
