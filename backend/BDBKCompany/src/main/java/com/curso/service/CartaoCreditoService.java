package com.curso.service;

import com.curso.domains.CartaoCredito;
import com.curso.repositorio.CartaoCreditoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartaoCreditoService {

    private final CartaoCreditoRepositorio cartaoCreditoRepositorio;

    public CartaoCreditoService(CartaoCreditoRepositorio cartaoCreditoRepositorio) {
        this.cartaoCreditoRepositorio = cartaoCreditoRepositorio;
    }

    @Transactional(readOnly = true)
    public List<CartaoCredito> listarTodos() {
        return cartaoCreditoRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public CartaoCredito buscarPorId(Long id) {
        return cartaoCreditoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão de crédito não encontrado com id: " + id));
    }

    @Transactional(readOnly = true)
    public List<CartaoCredito> listarPorUsuario(Long usuarioId) {
        return cartaoCreditoRepositorio.findByUsuario_Id(usuarioId);
    }

    @Transactional
    public CartaoCredito criar(CartaoCredito cartao) {
        return cartaoCreditoRepositorio.save(cartao);
    }

    @Transactional
    public CartaoCredito atualizar(CartaoCredito cartao) {
        return cartaoCreditoRepositorio.save(cartao);
    }

    @Transactional
    public void excluir(Long id) {
        CartaoCredito existente = buscarPorId(id);
        cartaoCreditoRepositorio.delete(existente);
    }
}
