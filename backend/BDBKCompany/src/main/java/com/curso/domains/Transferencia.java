package com.curso.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "usuario_id")
    private Long usuarioId;

    @NotNull
    @Column(name = "conta_origem_id")
    private Long contaOrigemId;

    @NotNull
    @Column(name = "conta_destino_id")
    private Long contaDestinoId;

    @NotNull
    private LocalDate data;

    @NotNull
    @Positive(message = "O valor da transferência deve ser maior que zero.")
    private BigDecimal valor;

    private String observacao;

    public Transferencia() {
    }

    public Transferencia(Long usuarioId, Long contaOrigemId, Long contaDestinoId, LocalDate data, BigDecimal valor, String observacao) {
        this.usuarioId = usuarioId;
        this.contaOrigemId = contaOrigemId;
        this.contaDestinoId = contaDestinoId;
        this.data = data;
        this.valor = valor;
        this.observacao = observacao;
        validarDominio();
    }

    @PrePersist
    @PreUpdate
    private void validarDominio() {
        if (contaOrigemId != null && contaDestinoId != null && contaOrigemId.equals(contaDestinoId)) {
            throw new IllegalStateException("A conta de origem deve ser diferente da conta de destino.");
        }
        if (valor != null && valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("O valor da transferência deve ser maior que zero.");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getContaOrigemId() {
        return contaOrigemId;
    }

    public void setContaOrigemId(Long contaOrigemId) {
        this.contaOrigemId = contaOrigemId;
    }

    public Long getContaDestinoId() {
        return contaDestinoId;
    }

    public void setContaDestinoId(Long contaDestinoId) {
        this.contaDestinoId = contaDestinoId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}