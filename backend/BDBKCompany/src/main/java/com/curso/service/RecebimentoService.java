package com.curso.service;

import com.curso.domains.ContaBancaria;
import com.curso.domains.Lancamento;
import com.curso.domains.MovimentoConta;
import com.curso.domains.Recebimento;
import com.curso.dto.RecebimentoInputDTO;
import com.curso.dto.RecebimentoOutputDTO;
import com.curso.mapper.RecebimentoMapper;
import com.curso.repositorio.ContaBancariaRepositorio;
import com.curso.repositorio.LancamentoRepositorio;
import com.curso.repositorio.MovimentoContaRepositorio;
import com.curso.repositorio.RecebimentoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RecebimentoService {

    private final RecebimentoRepositorio recebimentoRepositorio;
    private final LancamentoRepositorio lancamentoRepositorio;
    private final ContaBancariaRepositorio contaBancariaRepositorio;
    private final MovimentoContaRepositorio movimentoContaRepositorio;
    private final RecebimentoMapper recebimentoMapper;

    public RecebimentoService(RecebimentoRepositorio recebimentoRepositorio,
                              LancamentoRepositorio lancamentoRepositorio,
                              ContaBancariaRepositorio contaBancariaRepositorio,
                              MovimentoContaRepositorio movimentoContaRepositorio,
                              RecebimentoMapper recebimentoMapper) {
        this.recebimentoRepositorio = recebimentoRepositorio;
        this.lancamentoRepositorio = lancamentoRepositorio;
        this.contaBancariaRepositorio = contaBancariaRepositorio;
        this.movimentoContaRepositorio = movimentoContaRepositorio;
        this.recebimentoMapper = recebimentoMapper;
    }

    @Transactional(readOnly = true)
    public List<RecebimentoOutputDTO> listarPorLancamento(Long lancamentoId) {
        Lancamento lancamento = lancamentoRepositorio.findById(lancamentoId)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));

        List<Recebimento> recebimentos = recebimentoRepositorio.findByLancamento(lancamento);

        return recebimentos.stream()
                .map(recebimentoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public RecebimentoOutputDTO buscarPorId(Long lancamentoId, Long recebimentoId) {
        Recebimento recebimento = recebimentoRepositorio.findById(recebimentoId)
                .orElseThrow(() -> new RuntimeException("Recebimento não encontrado"));

        if (!recebimento.getLancamento().getId().equals(lancamentoId)) {
            throw new RuntimeException("Recebimento não pertence ao lançamento informado");
        }

        return recebimentoMapper.toDTO(recebimento);
    }

    @Transactional
    public RecebimentoOutputDTO criar(Long lancamentoId, RecebimentoInputDTO input) {
        Lancamento lancamento = lancamentoRepositorio.findById(lancamentoId)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));

        ContaBancaria contaDestino = null;
        if (input.getContaDestinoId() != null) {
            contaDestino = contaBancariaRepositorio.findById(input.getContaDestinoId())
                    .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));
        }

        Recebimento recebimento = recebimentoMapper.toEntity(input, lancamento, contaDestino);
        recebimento = recebimentoRepositorio.save(recebimento);

        if (contaDestino != null) {
            MovimentoConta movimento = new MovimentoConta();
            movimento.setConta(contaDestino);
            movimento.setDataMovimento(input.getDataRecebimento());
            movimento.setTipo(MovimentoConta.TipoTransacao.CREDITO);
            movimento.setValor(input.getValorRecebido());
            movimento.setHistorico("Recebimento do lançamento " + lancamento.getDescricao());
            movimento.setReferenciaId(recebimento.getId());
            movimento.setReferenciaTipo("RECEBIMENTO");

            movimentoContaRepositorio.save(movimento);
        }

        BigDecimal valorRecebido = input.getValorRecebido();
        lancamento.aplicarBaixa(valorRecebido);
        lancamentoRepositorio.save(lancamento);

        return recebimentoMapper.toDTO(recebimento);
    }

    @Transactional
    public RecebimentoOutputDTO atualizar(Long lancamentoId, Long recebimentoId, RecebimentoInputDTO input) {
        Recebimento recebimento = recebimentoRepositorio.findById(recebimentoId)
                .orElseThrow(() -> new RuntimeException("Recebimento não encontrado"));

        Lancamento lancamento = recebimento.getLancamento();
        if (!lancamento.getId().equals(lancamentoId)) {
            throw new RuntimeException("Recebimento não pertence ao lançamento informado");
        }

        BigDecimal valorAnterior = recebimento.getValorRecebido();

        ContaBancaria contaDestino = null;
        if (input.getContaDestinoId() != null) {
            contaDestino = contaBancariaRepositorio.findById(input.getContaDestinoId())
                    .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));
        }

        recebimentoMapper.copyToEntity(input, recebimento, lancamento, contaDestino);
        recebimento = recebimentoRepositorio.save(recebimento);
        BigDecimal novoValor = recebimento.getValorRecebido();
        BigDecimal novaBaixa = lancamento.getValorBaixado()
                .subtract(valorAnterior)
                .add(novoValor);

        if (novaBaixa.compareTo(BigDecimal.ZERO) < 0) {
            novaBaixa = BigDecimal.ZERO;
        }

        lancamento.setValorBaixado(novaBaixa);
        lancamento.validarStatusAutomatico();
        lancamentoRepositorio.save(lancamento);

        return recebimentoMapper.toDTO(recebimento);
    }

    @Transactional
    public void excluir(Long lancamentoId, Long recebimentoId) {
        Recebimento recebimento = recebimentoRepositorio.findById(recebimentoId)
                .orElseThrow(() -> new RuntimeException("Recebimento não encontrado"));

        Lancamento lancamento = recebimento.getLancamento();
        if (!lancamento.getId().equals(lancamentoId)) {
            throw new RuntimeException("Recebimento não pertence ao lançamento informado");
        }

        BigDecimal valorRecebido = recebimento.getValorRecebido();
        BigDecimal novaBaixa = lancamento.getValorBaixado().subtract(valorRecebido);

        if (novaBaixa.compareTo(BigDecimal.ZERO) < 0) {
            novaBaixa = BigDecimal.ZERO;
        }

        lancamento.setValorBaixado(novaBaixa);
        lancamento.validarStatusAutomatico();
        lancamentoRepositorio.save(lancamento);
        recebimentoRepositorio.delete(recebimento);
    }
}

