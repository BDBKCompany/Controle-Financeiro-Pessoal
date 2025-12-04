package com.curso.service;

import com.curso.domains.ContaBancaria;
import com.curso.domains.MovimentoConta;
import com.curso.dto.MovimentoContaOutputDTO;
import com.curso.mapper.MovimentoContaMapper;
import com.curso.repositorio.ContaBancariaRepositorio;
import com.curso.repositorio.MovimentoContaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentoContaService {

    private final MovimentoContaRepositorio movimentoContaRepositorio;
    private final ContaBancariaRepositorio contaBancariaRepositorio;
    private final MovimentoContaMapper movimentoContaMapper;

    public MovimentoContaService(MovimentoContaRepositorio movimentoContaRepositorio,
                                 ContaBancariaRepositorio contaBancariaRepositorio,
                                 MovimentoContaMapper movimentoContaMapper) {
        this.movimentoContaRepositorio = movimentoContaRepositorio;
        this.contaBancariaRepositorio = contaBancariaRepositorio;
        this.movimentoContaMapper = movimentoContaMapper;
    }

    @Transactional(readOnly = true)
    public List<MovimentoContaOutputDTO> listarPorConta(Long contaId) {
        ContaBancaria conta = contaBancariaRepositorio.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        List<MovimentoConta> movimentos =
                movimentoContaRepositorio.findByContaOrderByDataMovimentoDesc(conta);

        return movimentos.stream()
                .map(movimentoContaMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MovimentoContaOutputDTO buscarPorId(Long contaId, Long movimentoId) {
        MovimentoConta movimento = movimentoContaRepositorio.findById(movimentoId)
                .orElseThrow(() -> new RuntimeException("Movimento não encontrado"));

        if (!movimento.getConta().getId().equals(contaId)) {
            throw new RuntimeException("Movimento não pertence à conta informada");
        }

        return movimentoContaMapper.toDTO(movimento);
    }
}

