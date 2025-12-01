package com.curso.controller;

import com.curso.dto.CentroCustoInputDTO;
import com.curso.dto.CentroCustoOutputDTO;
import com.curso.service.CentroCustoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/centros-custo")
public class CentroCustoController {

    private final CentroCustoService centroCustoService;

    public CentroCustoController(CentroCustoService centroCustoService) {
        this.centroCustoService = centroCustoService;
    }

    @GetMapping
    public Page<CentroCustoOutputDTO> listar(@RequestHeader("X-Usuario-Id") Long usuarioId,
                                             @RequestParam(defaultValue = "true") boolean ativo,
                                             Pageable pageable) {
        return centroCustoService.listarAtivosPorUsuario(usuarioId, pageable);
    }

    @GetMapping("/{id}")
    public CentroCustoOutputDTO buscarPorId(@RequestHeader("X-Usuario-Id") Long usuarioId, @PathVariable Long id) {
        return centroCustoService.buscarPorId(id, usuarioId);
    }

    @PostMapping
    public CentroCustoOutputDTO criar(@RequestHeader("X-Usuario-Id") Long usuarioId,
                                      @RequestBody @Valid CentroCustoInputDTO input) {
        return centroCustoService.criar(input, usuarioId);
    }

    @PutMapping("/{id}")
    public CentroCustoOutputDTO atualizar(@RequestHeader("X-Usuario-Id") Long usuarioId,
                                          @PathVariable Long id,
                                          @RequestBody @Valid CentroCustoInputDTO input) {
        return centroCustoService.atualizar(id, input, usuarioId);
    }

    @DeleteMapping("/{id}")
    public void inativar(@RequestHeader("X-Usuario-Id") Long usuarioId, @PathVariable Long id) {
        centroCustoService.inativar(id, usuarioId);
    }
}
