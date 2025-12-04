package com.curso.service;

import com.curso.domains.CartaoCredito;
import com.curso.domains.FaturaCartao;
import com.curso.repositorio.ResourceNotFoundException;
import com.curso.repositorio.FaturaCartaoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FaturaCartaoService {

    private final FaturaCartaoRepositorio faturaCartaoRepositorio;
    private final CartaoCreditoService cartaoCreditoService;

    public FaturaCartaoService(FaturaCartaoRepositorio faturaCartaoRepositorio,
                                CartaoCreditoService cartaoCreditoService) {
        this.faturaCartaoRepositorio = faturaCartaoRepositorio;
        this.cartaoCreditoService = cartaoCreditoService;
    }

    @Transactional(readOnly = true)
    public List<FaturaCartao> listarTodos() {
        return faturaCartaoRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public FaturaCartao buscarPorId(Long id) {
        return faturaCartaoRepositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fatura não encontrada com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<FaturaCartao> listarPorCartao(Long cartaoId) {
        return faturaCartaoRepositorio.findByCartaoCredito_Id(cartaoId);
    }

    @Transactional(readOnly = true)
    public List<FaturaCartao> listarPorUsuario(Long usuarioId) {
        return faturaCartaoRepositorio.findByCartaoCredito_Usuario_Id(usuarioId);
    }

    @Transactional
    public FaturaCartao criar(FaturaCartao fatura) {
        // Aqui pode-se adicionar validações de negócio antes de salvar
        CartaoCredito cartao = cartaoCreditoService.buscarPorId(fatura.getCartaoCredito().getId());
        fatura.setCartaoCredito(cartao);
        return faturaCartaoRepositorio.save(fatura);
    }

    @Transactional
    public FaturaCartao atualizar(FaturaCartao fatura) {
        // garante que existe
        buscarPorId(fatura.getId());
        CartaoCredito cartao = cartaoCreditoService.buscarPorId(fatura.getCartaoCredito().getId());
        fatura.setCartaoCredito(cartao);
        return faturaCartaoRepositorio.save(fatura);
    }

    @Transactional
    public void excluir(Long id) {
        FaturaCartao existente = buscarPorId(id);
        faturaCartaoRepositorio.delete(existente);
    }
}
