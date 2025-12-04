package com.curso.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoInputDTO {

    @NotNull(message = "Data de pagamento é obrigatória")
    private LocalDate dataPagamento;

    @NotNull(message = "Valor pago é obrigatório")
    private BigDecimal valorPago;

    @NotNull(message = "Conta de origem é obrigatória")
    private Long contaOrigemId;

    private String observacao;

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Long getContaOrigemId() {
        return contaOrigemId;
    }

    public void setContaOrigemId(Long contaOrigemId) {
        this.contaOrigemId = contaOrigemId;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
