package com.curso.controller;

import com.curso.dto.PagamentoInputDTO;
import com.curso.dto.PagamentoOutputDTO;
import com.curso.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lancamentos/{lancamentoId}/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<PagamentoOutputDTO> listar(@PathVariable Long lancamentoId) {
        return pagamentoService.listarPorLancamento(lancamentoId);
    }

    @GetMapping("/{id}")
    public PagamentoOutputDTO buscarPorId(@PathVariable Long lancamentoId,
                                          @PathVariable Long id) {
        return pagamentoService.buscarPorId(lancamentoId, id);
    }

    @PostMapping
    public PagamentoOutputDTO criar(@PathVariable Long lancamentoId,
                                    @RequestBody @Valid PagamentoInputDTO input) {
        return pagamentoService.criar(lancamentoId, input);
    }

    @PutMapping("/{id}")
    public PagamentoOutputDTO atualizar(@PathVariable Long lancamentoId,
                                        @PathVariable Long id,
                                        @RequestBody @Valid PagamentoInputDTO input) {
        return pagamentoService.atualizar(lancamentoId, id, input);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long lancamentoId,
                        @PathVariable Long id) {
        pagamentoService.excluir(lancamentoId, id);
    }
}
