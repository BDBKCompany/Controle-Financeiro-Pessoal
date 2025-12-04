package com.curso.domains;

import com.curso.Enum.MeioPagamento;
import com.curso.Enum.StatusLancamento;
import com.curso.Enum.TipoLancamento;
import com.curso.domains.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "lancamento")
public class Lancamento extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoLancamento tipo;

    @Size(max = 200)
    @Column(name = "descricao", length = 200)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_id")
    private Entidade entidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_custo_id")
    private CentroCusto centroCusto;

    @NotNull
    @Column(name = "valor", nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @NotNull
    @Column(name = "data_competencia", nullable = false)
    private LocalDate dataCompetencia;

    @NotNull
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "meio_pagamento", length = 20)
    private MeioPagamento meioPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private ContaBancaria contaBancaria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartao_id")
    private CartaoCredito cartaoCredito;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatusLancamento status = StatusLancamento.PENDENTE;

    @Column(name = "valor_baixado", precision = 15, scale = 2, nullable = false)
    private BigDecimal valorBaixado = BigDecimal.ZERO;

    @OneToMany(mappedBy = "lancamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @OneToMany(mappedBy = "lancamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recebimento> recebimentos = new ArrayList<>();

    public Lancamento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataCompetencia() {
        return dataCompetencia;
    }

    public void setDataCompetencia(LocalDate dataCompetencia) {
        this.dataCompetencia = dataCompetencia;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public MeioPagamento getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(MeioPagamento meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public StatusLancamento getStatus() {
        return status;
    }

    public void setStatus(StatusLancamento status) {
        this.status = status;
    }

    public BigDecimal getValorBaixado() {
        return valorBaixado;
    }

    public void setValorBaixado(BigDecimal valorBaixado) {
        this.valorBaixado = (valorBaixado == null ? BigDecimal.ZERO : valorBaixado);
        atualizarStatusAutomatico();
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public List<Recebimento> getRecebimentos() {
        return recebimentos;
    }

    public void setRecebimentos(List<Recebimento> recebimentos) {
        this.recebimentos = recebimentos;
    }

    public void adicionarPagamento(Pagamento pagamento) {
        if (pagamento == null) return;
        pagamento.setLancamento(this);
        pagamentos.add(pagamento);

        if (pagamento.getValorPago() != null) {
            this.valorBaixado = this.valorBaixado.add(pagamento.getValorPago());
            atualizarStatusAutomatico();
        }
    }

    public void removerPagamento(Pagamento pagamento) {
        if (pagamento == null) return;

        if (pagamentos.remove(pagamento)) {
            if (pagamento.getValorPago() != null) {
                this.valorBaixado = this.valorBaixado.subtract(pagamento.getValorPago());
                if (this.valorBaixado.compareTo(BigDecimal.ZERO) < 0) {
                    this.valorBaixado = BigDecimal.ZERO;
                }
                atualizarStatusAutomatico();
            }
            pagamento.setLancamento(null);
        }
    }

    public void adicionarRecebimento(Recebimento recebimento) {
        if (recebimento == null) return;
        recebimento.setLancamento(this);
        recebimentos.add(recebimento);

        if (recebimento.getValorRecebido() != null) {
            this.valorBaixado = this.valorBaixado.add(recebimento.getValorRecebido());
            atualizarStatusAutomatico();
        }
    }

    public void removerRecebimento(Recebimento recebimento) {
        if (recebimento == null) return;

        if (recebimentos.remove(recebimento)) {
            if (recebimento.getValorRecebido() != null) {
                this.valorBaixado = this.valorBaixado.subtract(recebimento.getValorRecebido());
                if (this.valorBaixado.compareTo(BigDecimal.ZERO) < 0) {
                    this.valorBaixado = BigDecimal.ZERO;
                }
                atualizarStatusAutomatico();
            }
            recebimento.setLancamento(null);
        }
    }

    public void aplicarBaixa(BigDecimal valorBaixa) {
        if (valorBaixa == null || valorBaixa.compareTo(BigDecimal.ZERO) <= 0) return;
        this.valorBaixado = this.valorBaixado.add(valorBaixa);
        atualizarStatusAutomatico();
    }

    /**
     * Método público para ser chamado pelo service (como em LancamentoService).
     */
    public void validarStatusAutomatico() {
        atualizarStatusAutomatico();
    }

    /**
     * Lógica interna para atualizar o status com base no valor baixado.
     */
    private void atualizarStatusAutomatico() {
        if (this.valor == null) {
            return;
        }

        if (this.valorBaixado == null || this.valorBaixado.compareTo(BigDecimal.ZERO) <= 0) {
            this.status = StatusLancamento.PENDENTE;
            this.valorBaixado = (this.valorBaixado == null ? BigDecimal.ZERO : this.valorBaixado);
            return;
        }

        int cmp = this.valorBaixado.compareTo(this.valor);

        if (cmp < 0) {
            this.status = StatusLancamento.PARCIAL;
        } else { // cmp >= 0
            this.status = StatusLancamento.BAIXADO;
            this.valorBaixado = this.valor; // garante que não passa do valor original
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lancamento)) return false;
        Lancamento that = (Lancamento) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
