package com.curso.service;

import com.curso.domains.ContaBancaria;
import com.curso.domains.Lancamento;
import com.curso.domains.MovimentoConta;
import com.curso.domains.Pagamento;
import com.curso.dto.PagamentoInputDTO;
import com.curso.dto.PagamentoOutputDTO;
import com.curso.mapper.PagamentoMapper;
import com.curso.repositorio.ContaBancariaRepositorio;
import com.curso.repositorio.LancamentoRepositorio;
import com.curso.repositorio.MovimentoContaRepositorio;
import com.curso.repositorio.PagamentoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepositorio pagamentoRepositorio;
    private final LancamentoRepositorio lancamentoRepositorio;
    private final ContaBancariaRepositorio contaBancariaRepositorio;
    private final MovimentoContaRepositorio movimentoContaRepositorio;
    private final PagamentoMapper pagamentoMapper;

    public PagamentoService(PagamentoRepositorio pagamentoRepositorio,
                            LancamentoRepositorio lancamentoRepositorio,
                            ContaBancariaRepositorio contaBancariaRepositorio,
                            MovimentoContaRepositorio movimentoContaRepositorio,
                            PagamentoMapper pagamentoMapper) {
        this.pagamentoRepositorio = pagamentoRepositorio;
        this.lancamentoRepositorio = lancamentoRepositorio;
        this.contaBancariaRepositorio = contaBancariaRepositorio;
        this.movimentoContaRepositorio = movimentoContaRepositorio;
        this.pagamentoMapper = pagamentoMapper;
    }

    @Transactional(readOnly = true)
    public List<PagamentoOutputDTO> listarPorLancamento(Long lancamentoId) {
        Lancamento lancamento = lancamentoRepositorio.findById(lancamentoId)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));

        List<Pagamento> pagamentos = pagamentoRepositorio.findByLancamento(lancamento);

        return pagamentos.stream()
                .map(pagamentoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public PagamentoOutputDTO buscarPorId(Long lancamentoId, Long pagamentoId) {
        Pagamento pagamento = pagamentoRepositorio.findById(pagamentoId)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        if (!pagamento.getLancamento().getId().equals(lancamentoId)) {
            throw new RuntimeException("Pagamento não pertence ao lançamento informado");
        }

        return pagamentoMapper.toDTO(pagamento);
    }

    @Transactional
    public PagamentoOutputDTO criar(Long lancamentoId, PagamentoInputDTO input) {
        Lancamento lancamento = lancamentoRepositorio.findById(lancamentoId)
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado"));

        ContaBancaria contaOrigem = contaBancariaRepositorio.findById(input.getContaOrigemId())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));

        Pagamento pagamento = pagamentoMapper.toEntity(input, lancamento, contaOrigem);
        pagamento = pagamentoRepositorio.save(pagamento);

        MovimentoConta movimento = new MovimentoConta();
        movimento.setConta(contaOrigem);
        movimento.setDataMovimento(input.getDataPagamento());
        movimento.setTipo(MovimentoConta.TipoTransacao.DEBITO);
        movimento.setValor(input.getValorPago());
        movimento.setHistorico("Pagamento do lançamento " + lancamento.getDescricao());
        movimento.setReferenciaId(pagamento.getId());
        movimento.setReferenciaTipo("PAGAMENTO");

        movimentoContaRepositorio.save(movimento);

        BigDecimal valorPago = input.getValorPago();
        lancamento.aplicarBaixa(valorPago);
        lancamentoRepositorio.save(lancamento);

        return pagamentoMapper.toDTO(pagamento);
    }

    @Transactional
    public PagamentoOutputDTO atualizar(Long lancamentoId, Long pagamentoId, PagamentoInputDTO input) {
        Pagamento pagamento = pagamentoRepositorio.findById(pagamentoId)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        Lancamento lancamento = pagamento.getLancamento();
        if (!lancamento.getId().equals(lancamentoId)) {
            throw new RuntimeException("Pagamento não pertence ao lançamento informado");
        }

        BigDecimal valorAnterior = pagamento.getValorPago();
        ContaBancaria contaOrigem = contaBancariaRepositorio.findById(input.getContaOrigemId())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));

        pagamentoMapper.copyToEntity(input, pagamento, lancamento, contaOrigem);
        pagamento = pagamentoRepositorio.save(pagamento);

        BigDecimal novoValor = pagamento.getValorPago();
        BigDecimal novaBaixa = lancamento.getValorBaixado()
                .subtract(valorAnterior)
                .add(novoValor);

        lancamento.setValorBaixado(novaBaixa);
        lancamento.validarStatusAutomatico();
        lancamentoRepositorio.save(lancamento);


        return pagamentoMapper.toDTO(pagamento);
    }

    @Transactional
    public void excluir(Long lancamentoId, Long pagamentoId) {
        Pagamento pagamento = pagamentoRepositorio.findById(pagamentoId)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        Lancamento lancamento = pagamento.getLancamento();
        if (!lancamento.getId().equals(lancamentoId)) {
            throw new RuntimeException("Pagamento não pertence ao lançamento informado");
        }

        BigDecimal valorPago = pagamento.getValorPago();
        BigDecimal novaBaixa = lancamento.getValorBaixado().subtract(valorPago);
        if (novaBaixa.compareTo(BigDecimal.ZERO) < 0) {
            novaBaixa = BigDecimal.ZERO;
        }
        lancamento.setValorBaixado(novaBaixa);
        lancamento.validarStatusAutomatico();
        lancamentoRepositorio.save(lancamento);

        pagamentoRepositorio.delete(pagamento);
    }
}
