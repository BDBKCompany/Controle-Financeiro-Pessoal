package com.curso.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class MovimentoContaInputDTO {

    @NotNull(message = "Id da conta é obrigatório")
    private Long contaId;

    @NotNull(message = "Id de referência é obrigatório")
    private Long referenciaId;

    @NotNull(message = "O valor não pode ser nulo")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "O tipo de referência é obrigatório")
    private ReferenciaTipo referenciaTipo;

    @NotNull(message = "O tipo de movimentação é obrigatório")
    private TipoTransacao tipo;

    @NotNull(message = "O histórico da movimentação é obrigatório")
    private String historico;

    public Long getContaId() { return contaId; }
    public void setContaId(Long contaId) { this.contaId = contaId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getHistorico() { return historico; }
    public void setHistorico(String historico) { this.historico = historico; }

    public Long getReferenciaId() { return referenciaId; }
    public void setReferenciaId(Long referenciaId) { this.referenciaId = referenciaId; }

    public String getReferenciaTipo() { return referenciaTipo; }
    public void setReferenciaTipo(String referenciaTipo) { this.referenciaTipo = referenciaTipo; }
}
