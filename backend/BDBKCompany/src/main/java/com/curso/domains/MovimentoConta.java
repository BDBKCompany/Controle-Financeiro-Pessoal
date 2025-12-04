package com.curso.domains;

import com.curso.domains.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "movimento_conta")
public class MovimentoConta extends Auditable {

    public enum TipoTransacao {
        DEBITO,
        CREDITO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private ContaBancaria conta;

    @NotNull
    @Column(name = "data_movimento", nullable = false)
    private LocalDate dataMovimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 20, nullable = false)
    private TipoTransacao tipo;

    @NotNull
    @Column(name = "valor", precision = 15, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "historico", length = 500)
    private String historico;

    @Column(name = "referencia_id")
    private Long referenciaId;

    @Column(name = "referencia_tipo", length = 50)
    private String referenciaTipo;

    public MovimentoConta() {
    }

    public Long getId() {
        return id;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public Long getReferenciaId() {
        return referenciaId;
    }

    public void setReferenciaId(Long referenciaId) {
        this.referenciaId = referenciaId;
    }

    public String getReferenciaTipo() {
        return referenciaTipo;
    }

    public void setReferenciaTipo(String referenciaTipo) {
        this.referenciaTipo = referenciaTipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovimentoConta)) return false;
        MovimentoConta that = (MovimentoConta) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
