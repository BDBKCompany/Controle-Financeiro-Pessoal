package com.curso.controller;

import com.curso.dto.CentroCustoInputDTO;
import com.curso.dto.CentroCustoOutputDTO;
import com.curso.mapper.CentroCustoMapper;
import com.curso.domains.CentroCusto;
import com.curso.service.CentroCustoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/centros-custo")
public class CentroCustoController {

    private final CentroCustoService centroCustoService;
    private final CentroCustoMapper centroCustoMapper;

    public CentroCustoController(CentroCustoService centroCustoService, CentroCustoMapper centroCustoMapper) {
        this.centroCustoService = centroCustoService;
        this.centroCustoMapper = centroCustoMapper;
    }

    @GetMapping
    public List<CentroCustoOutputDTO> listar(@RequestParam(defaultValue = "true") boolean ativo, Pageable pageable) {
        List<CentroCusto> centros = centroCustoService.listarAtivosPorUsuario(1L, pageable);
        return centroCustoMapper.toDTOList(centros);
    }

    @GetMapping("/{id}")
    public CentroCustoOutputDTO buscarPorId(@PathVariable Long id) {
        CentroCusto centro = centroCustoService.buscarPorId(id, 1L);
        return centroCustoMapper.toDTO(centro);
    }

    @PostMapping
    public CentroCustoOutputDTO criar(@RequestBody @Valid CentroCustoInputDTO input) {
        CentroCusto centro = centroCustoMapper.toEntity(input);
        CentroCusto salvo = centroCustoService.criar(input, 1L);
        return centroCustoMapper.toDTO(salvo);
    }

    @PutMapping("/{id}")
    public CentroCustoOutputDTO atualizar(@PathVariable Long id, @RequestBody @Valid CentroCustoInputDTO input) {
        CentroCusto centroAtualizado = centroCustoMapper.toEntity(input);
        CentroCusto salvo = centroCustoService.atualizar(id, input, 1L);
        return centroCustoMapper.toDTO(salvo);
    }

    @DeleteMapping("/{id}")
    public void inativar(@PathVariable Long id) {
        centroCustoService.inativar(id, 1L);
    }
}