package com.curso.domains;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="recebimento")
public class Recebimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lancamento_id", nullable = false)
    private Lancamento lancamento;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataRecebimento ;

    @NotNull
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorRecebido;

    private Integer contaDestino;

    private String observacao;

    public Recebimento() {
    }

    public Recebimento(Long id, Lancamento lancamento,
                       LocalDate dataRecebimento, BigDecimal valorRecebido,
                       Integer contaDestino, String observacao) {
        this.id = id;
        this.lancamento = lancamento;
        this.dataRecebimento = dataRecebimento;
        this.valorRecebido = valorRecebido;
        this.contaDestino = contaDestino;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(LocalDate dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public BigDecimal getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(BigDecimal valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public Integer getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Integer contaDestino) {
        this.contaDestino = contaDestino;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Recebimento{" +
                "id=" + id +
                ", lancamento=" + lancamento +
                ", dataRecebimento=" + dataRecebimento +
                ", valorRecebido=" + valorRecebido +
                ", contaDestino=" + contaDestino +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
