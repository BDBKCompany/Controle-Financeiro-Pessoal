package com.curso.controller;

import com.curso.dto.PagamentoInputDTO;
import com.curso.dto.PagamentoOutputDTO;
import com.curso.domains.Pagamento;
import com.curso.service.ContaBancariaService;
import com.curso.service.LancamentoService;
import com.curso.service.PagamentoService;
import com.curso.mapper.PagamentoMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lancamentos/{lancamentoId}/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final LancamentoService lancamentoService;
    private final ContaBancariaService contaBancariaService;
    private final PagamentoMapper pagamentoMapper;

    public PagamentoController(PagamentoService pagamentoService,
                               LancamentoService lancamentoService,
                               ContaBancariaService contaBancariaService,
                               PagamentoMapper pagamentoMapper) {
        this.pagamentoService = pagamentoService;
        this.lancamentoService = lancamentoService;
        this.contaBancariaService = contaBancariaService;
        this.pagamentoMapper = pagamentoMapper;
    }

    @GetMapping
    public List<PagamentoOutputDTO> listar(@PathVariable Long lancamentoId) {
        List<Pagamento> pagamentos = pagamentoService.listarPorLancamento(lancamentoId);
        return pagamentoMapper.toDTOList(pagamentos);
    }

    @GetMapping("/{id}")
    public PagamentoOutputDTO buscarPorId(@PathVariable Long lancamentoId, @PathVariable Long id) {
        Pagamento pagamento = pagamentoService.buscarPorId(id, lancamentoId);
        return pagamentoMapper.toDTO(pagamento);
    }

    @PostMapping
    public PagamentoOutputDTO criar(@PathVariable Long lancamentoId,
                                    @RequestBody @Valid PagamentoInputDTO input) {
        Pagamento pagamento = pagamentoMapper.toEntity(input);
        Pagamento salvo = pagamentoService.criar(pagamento, lancamentoId, input.getContaOrigemId());
        return pagamentoMapper.toDTO(salvo);
    }

    @PutMapping("/{id}")
    public PagamentoOutputDTO atualizar(@PathVariable Long lancamentoId,
                                        @PathVariable Long id,
                                        @RequestBody @Valid PagamentoInputDTO input) {
        Pagamento existente = pagamentoService.buscarPorId(id, lancamentoId);
        pagamentoMapper.copyToEntity(input, existente);
        Pagamento salvo = pagamentoService.atualizar(existente, input.getContaOrigemId());
        return pagamentoMapper.toDTO(salvo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long lancamentoId, @PathVariable Long id) {
        pagamentoService.excluir(id, lancamentoId);
    }
}
