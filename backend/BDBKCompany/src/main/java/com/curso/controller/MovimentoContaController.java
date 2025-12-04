package com.curso.controller;

import com.curso.dto.MovimentoContaOutputDTO;
import com.curso.service.MovimentoContaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contas/{contaId}/movimentos")
public class MovimentoContaController {

    private final MovimentoContaService movimentoContaService;

    public MovimentoContaController(MovimentoContaService movimentoContaService) {
        this.movimentoContaService = movimentoContaService;
    }

    @GetMapping
    public List<MovimentoContaOutputDTO> listar(@PathVariable Long contaId) {
        return movimentoContaService.listarPorConta(contaId);
    }

    @GetMapping("/{id}")
    public MovimentoContaOutputDTO buscarPorId(@PathVariable Long contaId,
                                               @PathVariable Long id) {
        return movimentoContaService.buscarPorId(contaId, id);
    }
}

