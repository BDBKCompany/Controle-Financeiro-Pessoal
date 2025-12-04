package com.curso.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaInputDTO {

    @NotNull(message = "Conta de origem é obrigatória")
    private Long contaOrigemId;

    @NotNull(message = "Conta de destino é obrigatória")
    private Long contaDestinoId;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    private String observacao;

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
