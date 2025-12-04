package com.curso.controller;

import com.curso.dto.RecebimentoInputDTO;
import com.curso.dto.RecebimentoOutputDTO;
import com.curso.service.RecebimentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lancamentos/{lancamentoId}/recebimentos")
public class RecebimentoController {

    private final RecebimentoService recebimentoService;

    public RecebimentoController(RecebimentoService recebimentoService) {
        this.recebimentoService = recebimentoService;
    }

    @GetMapping
    public List<RecebimentoOutputDTO> listar(@PathVariable Long lancamentoId) {
        return recebimentoService.listarPorLancamento(lancamentoId);
    }

    @GetMapping("/{id}")
    public RecebimentoOutputDTO buscarPorId(@PathVariable Long lancamentoId,
                                            @PathVariable Long id) {
        return recebimentoService.buscarPorId(lancamentoId, id);
    }

    @PostMapping
    public RecebimentoOutputDTO criar(@PathVariable Long lancamentoId,
                                      @RequestBody @Valid RecebimentoInputDTO input) {
        return recebimentoService.criar(lancamentoId, input);
    }

    @PutMapping("/{id}")
    public RecebimentoOutputDTO atualizar(@PathVariable Long lancamentoId,
                                          @PathVariable Long id,
                                          @RequestBody @Valid RecebimentoInputDTO input) {
        return recebimentoService.atualizar(lancamentoId, id, input);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long lancamentoId,
                        @PathVariable Long id) {
        recebimentoService.excluir(lancamentoId, id);
    }
}

