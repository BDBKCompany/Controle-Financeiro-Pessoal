package com.curso.service;

import com.curso.domains.ContaBancaria;
import com.curso.domains.Lancamento;
import com.curso.domains.MovimentoConta;
import com.curso.domains.Pagamento;
import com.curso.dto.PagamentoInputDTO;
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
    private final PagamentoMapper pagamentoMapper;
    private final LancamentoRepositorio lancamentoRepositorio;
    private final ContaBancariaRepositorio contaBancariaRepositorio;
    private final MovimentoContaRepositorio movimentoContaRepositorio;

    public PagamentoService(PagamentoRepositorio pagamentoRepositorio,
                            PagamentoMapper pagamentoMapper,
                            LancamentoRepositorio lancamentoRepositorio,
                            ContaBancariaRepositorio contaBancariaRepositorio,
                            MovimentoContaRepositorio movimentoContaRepositorio) {
        this.pagamentoRepositorio = pagamentoRepositorio;
        this.pagamentoMapper = pagamentoMapper;
        this.lancamentoRepositorio = lancamentoRepositorio;
        this.contaBancariaRepositorio = contaBancariaRepositorio;
        this.movimentoContaRepositorio = movimentoContaRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Pagamento> listarPorLancamento(Long lancamentoId) {
        return pagamentoRepositorio.findByLancamentoId(lancamentoId);
    }

    @Transactional(readOnly = true)
    public Pagamento buscarPorId(Long id) {
        return pagamentoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com id: " + id));
    }

    @Transactional
    public Pagamento criar(PagamentoInputDTO input) {
        Lancamento lancamento = lancamentoRepositorio.findById(input.getLancamentoId())
                .orElseThrow(() -> new RuntimeException("Lançamento não encontrado com id: " + input.getLancamentoId()));

        if (!"PAGAR".equals(lancamento.getTipo().name())) {
            throw new RuntimeException("Só é permitido criar pagamento para lançamentos do tipo PAGAR");
        }

        ContaBancaria conta = contaBancariaRepositorio.findById(input.getContaOrigemId())
                .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada com id: " + input.getContaOrigemId()));

        if (!Boolean.TRUE.equals(conta.getAtiva())) {
            throw new RuntimeException("Conta bancária inativa");
        }

        Pagamento pagamento = pagamentoMapper.toEntity(input);
        pagamento.setLancamento(lancamento);
        pagamento.setContaOrigem(conta);
        Pagamento salvo = pagamentoRepositorio.save(pagamento);

        BigDecimal valorBaixado = lancamento.getValorBaixado() != null ? lancamento.getValorBaixado() : BigDecimal.ZERO;
        lancamento.setValorBaixado(valorBaixado.add(input.getValorPago()));
        if (lancamento.getValorBaixado().compareTo(lancamento.getValor()) >= 0) {
            lancamento.setStatus(Lancamento.StatusLancamento.BAIXADO);
        } else {
            lancamento.setStatus(Lancamento.StatusLancamento.PARCIAL);
        }
        lancamentoRepositorio.save(lancamento);

        MovimentoConta movimento = new MovimentoConta();
        movimento.setConta(conta);
        movimento.setDataMovimento(input.getDataPagamento());
        movimento.setTipo(MovimentoConta.TipoTransacao.DEBITO);
        movimento.setValor(input.getValorPago());
        movimento.setHistorico("Pagamento do lançamento " + lancamento.getId());
        movimento.setReferenciaId(salvo.getId());
        movimento.setReferenciaTipo("PAGAMENTO");
        movimentoContaRepositorio.save(movimento);

        return salvo;
    }

    @Transactional
    public Pagamento atualizar(Long id, PagamentoInputDTO input) {
        Pagamento existente = buscarPorId(id);

        Lancamento lancamento = existente.getLancamento();
        ContaBancaria conta = existente.getContaOrigem();

        pagamentoMapper.copyToEntity(input, existente);

        if (!conta.getId().equals(input.getContaOrigemId())) {
            conta = contaBancariaRepositorio.findById(input.getContaOrigemId())
                    .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada"));
            if (!Boolean.TRUE.equals(conta.getAtiva())) {
                throw new RuntimeException("Conta bancária inativa");
            }
            existente.setContaOrigem(conta);
        }

        Pagamento salvo = pagamentoRepositorio.save(existente);

        MovimentoConta movimento = movimentoContaRepositorio.findByReferenciaTipoAndReferenciaId("PAGAMENTO", id)
                .orElseThrow(() -> new RuntimeException("Movimento financeiro não encontrado para este pagamento"));

        movimento.setConta(conta);
        movimento.setDataMovimento(input.getDataPagamento());
        movimento.setValor(input.getValorPago());
        movimento.setHistorico("Pagamento do lançamento " + lancamento.getId());
        movimentoContaRepositorio.save(movimento);

        BigDecimal valorBaixado = lancamento.getValorBaixado().subtract(existente.getValorPago()).add(input.getValorPago());
        lancamento.setValorBaixado(valorBaixado);
        if (valorBaixado.compareTo(lancamento.getValor()) >= 0) {
            lancamento.setStatus(Lancamento.StatusLancamento.BAIXADO);
        } else {
            lancamento.setStatus(Lancamento.StatusLancamento.PARCIAL);
        }
        lancamentoRepositorio.save(lancamento);

        return salvo;
    }

    @Transactional
    public void excluir(Long id) {
        Pagamento pagamento = buscarPorId(id);
        Lancamento lancamento = pagamento.getLancamento();

        lancamento.setValorBaixado(lancamento.getValorBaixado().subtract(pagamento.getValorPago()));
        if (lancamento.getValorBaixado().compareTo(BigDecimal.ZERO) <= 0) {
            lancamento.setStatus(Lancamento.StatusLancamento.PENDENTE);
        } else {
            lancamento.setStatus(Lancamento.StatusLancamento.PARCIAL);
        }
        lancamentoRepositorio.save(lancamento);

        MovimentoConta movimento = movimentoContaRepositorio.findByReferenciaTipoAndReferenciaId("PAGAMENTO", id)
                .orElseThrow(() -> new RuntimeException("Movimento financeiro não encontrado para este pagamento"));
        movimentoContaRepositorio.delete(movimento);

        pagamentoRepositorio.delete(pagamento);
    }
}
