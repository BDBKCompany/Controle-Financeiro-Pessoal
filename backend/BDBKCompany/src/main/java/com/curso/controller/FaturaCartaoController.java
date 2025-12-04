package com.curso.controller;

import com.curso.dto.FaturaCartaoInputDTO;
import com.curso.dto.FaturaCartaoOutputDTO;
import com.curso.mapper.FaturaCartaoMapper;
import com.curso.domains.FaturaCartao;
import com.curso.domains.CartaoCredito;
import com.curso.service.FaturaCartaoService;
import com.curso.service.CartaoCreditoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faturas-cartao")
public class FaturaCartaoController {

    private final FaturaCartaoService faturaCartaoService;
    private final CartaoCreditoService cartaoCreditoService;
    private final FaturaCartaoMapper mapper;

    public FaturaCartaoController(FaturaCartaoService faturaCartaoService,
                                  CartaoCreditoService cartaoCreditoService,
                                  FaturaCartaoMapper mapper) {
        this.faturaCartaoService = faturaCartaoService;
        this.cartaoCreditoService = cartaoCreditoService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<FaturaCartaoOutputDTO> listarTodos() {
        List<FaturaCartao> faturas = faturaCartaoService.listarTodos();
        return mapper.toDTOList(faturas);
    }

    @GetMapping("/{id}")
    public FaturaCartaoOutputDTO buscarPorId(@PathVariable Long id) {
        FaturaCartao f = faturaCartaoService.buscarPorId(id);
        return mapper.toDTO(f);
    }

    @GetMapping("/cartao/{cartaoId}")
    public List<FaturaCartaoOutputDTO> listarPorCartao(@PathVariable Long cartaoId) {
        List<FaturaCartao> faturas = faturaCartaoService.listarPorCartao(cartaoId);
        return mapper.toDTOList(faturas);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<FaturaCartaoOutputDTO> listarPorUsuario(@PathVariable Long usuarioId) {
        List<FaturaCartao> faturas = faturaCartaoService.listarPorUsuario(usuarioId);
        return mapper.toDTOList(faturas);
    }

    @PostMapping
    public FaturaCartaoOutputDTO criar(@RequestBody @Valid FaturaCartaoInputDTO input) {
        CartaoCredito cartao = cartaoCreditoService.buscarPorId(input.getCartaoCreditoId());
        FaturaCartao entity = mapper.toEntity(input, cartao);
        FaturaCartao salvo = faturaCartaoService.criar(entity);
        return mapper.toDTO(salvo);
    }

    @PutMapping("/{id}")
    public FaturaCartaoOutputDTO atualizar(@PathVariable Long id,
                                           @RequestBody @Valid FaturaCartaoInputDTO input) {
        FaturaCartao existente = faturaCartaoService.buscarPorId(id);
        CartaoCredito cartao = cartaoCreditoService.buscarPorId(input.getCartaoCreditoId());
        mapper.copyToEntity(input, existente, cartao);
        FaturaCartao salvo = faturaCartaoService.atualizar(existente);
        return mapper.toDTO(salvo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        faturaCartaoService.excluir(id);
    }
}
