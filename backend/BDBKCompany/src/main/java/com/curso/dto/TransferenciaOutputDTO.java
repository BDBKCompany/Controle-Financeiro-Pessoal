package com.curso.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class TransferenciaOutputDTO {

    private Long id;

    private Long usuarioId;

    private Long contaOrigemId;
    private String contaOrigemNome;

    private Long contaDestinoId;
    private String contaDestinoNome;

    private LocalDate data;

    private BigDecimal valor;

    private String observacao;

    private OffsetDateTime criadoEm;
    private OffsetDateTime atualizadoEm;

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

    public String getContaOrigemNome() {
        return contaOrigemNome;
    }

    public void setContaOrigemNome(String contaOrigemNome) {
        this.contaOrigemNome = contaOrigemNome;
    }

    public Long getContaDestinoId() {
        return contaDestinoId;
    }

    public void setContaDestinoId(Long contaDestinoId) {
        this.contaDestinoId = contaDestinoId;
    }

    public String getContaDestinoNome() {
        return contaDestinoNome;
    }

    public void setContaDestinoNome(String contaDestinoNome) {
        this.contaDestinoNome = contaDestinoNome;
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

    public OffsetDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(OffsetDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public OffsetDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(OffsetDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
