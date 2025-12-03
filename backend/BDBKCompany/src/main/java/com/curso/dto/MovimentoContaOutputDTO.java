package com.curso.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimentoContaOutputDTO {

    private Long id;
    private Long contaId;
    private String tipo;
    private BigDecimal valor;
    private String historico;
    private LocalDateTime dataMovimento;
    private Long referenciaId;
    private String referenciaTipo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getContaId() { return contaId; }
    public void setContaId(Long contaId) { this.contaId = contaId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getHistorico() { return historico; }
    public void setHistorico(String historico) { this.historico = historico; }

    public LocalDateTime getDataMovimento() { return dataMovimento; }
    public void setDataMovimento(LocalDateTime dataMovimento) { this.dataMovimento = dataMovimento; }

    public Long getReferenciaId() { return referenciaId; }
    public void setReferenciaId(Long referenciaId) { this.referenciaId = referenciaId; }

    public String getReferenciaTipo() { return referenciaTipo; }
    public void setReferenciaTipo(String referenciaTipo) { this.referenciaTipo = referenciaTipo; }
}
