package com.curso.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaOutputDTO {

    private Long id;
    private Long usuarioId;
    private Long contaOrigemId;
    private Long contaDestinoId;

    private String observacao;
    private String contaOrigemNumero;
    private String contaDestinoNumero;

    private LocalDate data;
    private BigDecimal valor;

    public TransferenciaOutputDTO() {
    }

    public TransferenciaOutputDTO(Long id, Long usuarioId, Long contaOrigemId, Long contaDestinoId, String observacao, String contaOrigemNumero, String contaDestinoNumero, LocalDate data, BigDecimal valor) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.contaOrigemId = contaOrigemId;
        this.contaDestinoId = contaDestinoId;
        this.observacao = observacao;
        this.contaOrigemNumero = contaOrigemNumero;
        this.contaDestinoNumero = contaDestinoNumero;
        this.data = data;
        this.valor = valor;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getContaOrigemNumero() {
        return contaOrigemNumero;
    }

    public void setContaOrigemNumero(String contaOrigemNumero) {
        this.contaOrigemNumero = contaOrigemNumero;
    }

    public String getContaDestinoNumero() {
        return contaDestinoNumero;
    }

    public void setContaDestinoNumero(String contaDestinoNumero) {
        this.contaDestinoNumero = contaDestinoNumero;
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
}

