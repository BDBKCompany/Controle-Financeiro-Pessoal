package com.curso.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FaturaCartaoInputDTO {

    @NotNull(message = "Cartão é obrigatório")
    private Long cartaoCreditoId;

    @NotNull(message = "Período início é obrigatório")
    private LocalDate periodoInicio;

    @NotNull(message = "Período fim é obrigatório")
    private LocalDate periodoFim;

    @NotNull(message = "Valor total é obrigatório")
    private BigDecimal valorTotal;

    private Integer statusId;

    private LocalDate dataFechamento;

    private LocalDate dataVencimento;

    private Boolean ativo = true;

    public Long getCartaoCreditoId() {
        return cartaoCreditoId;
    }

    public void setCartaoCreditoId(Long cartaoCreditoId) {
        this.cartaoCreditoId = cartaoCreditoId;
    }

    public LocalDate getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(LocalDate periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public LocalDate getPeriodoFim() {
        return periodoFim;
    }

    public void setPeriodoFim(LocalDate periodoFim) {
        this.periodoFim = periodoFim;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
