package com.curso.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimentoContaInputDTO {

    @NotNull(message = "Conta é obrigatória")
    private Long contaId;

    @NotNull(message = "Data do movimento é obrigatória")
    private LocalDate dataMovimento;

    @NotNull(message = "Tipo é obrigatório")
    private String tipo; // ex.: "CREDITO" / "DEBITO" ou equivalente ao seu enum

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    private String historico;

    private Long referenciaId;

    private String referenciaTipo;

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public LocalDate getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(LocalDate dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
}
