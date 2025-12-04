package com.curso.controller;

import com.curso.dto.TransferenciaInputDTO;
import com.curso.dto.TransferenciaOutputDTO;
import com.curso.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    public List<TransferenciaOutputDTO> listar(@RequestParam Long usuarioId) {
        return transferenciaService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/{id}")
    public TransferenciaOutputDTO buscarPorId(@PathVariable Long id,
                                              @RequestParam Long usuarioId) {
        return transferenciaService.buscarPorId(id, usuarioId);
    }

    @PostMapping
    public TransferenciaOutputDTO criar(@RequestParam Long usuarioId,
                                        @RequestBody @Valid TransferenciaInputDTO input) {
        return transferenciaService.criar(input, usuarioId);
    }

    @PutMapping("/{id}")
    public TransferenciaOutputDTO atualizar(@PathVariable Long id,
                                            @RequestParam Long usuarioId,
                                            @RequestBody @Valid TransferenciaInputDTO input) {
        return transferenciaService.atualizar(id, input, usuarioId);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id,
                        @RequestParam Long usuarioId) {
        transferenciaService.excluir(id, usuarioId);
    }
}

