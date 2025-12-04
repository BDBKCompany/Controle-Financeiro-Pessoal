package com.curso.dto;

import com.curso.Enum.StatusLancamento;
import com.curso.Enum.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class LancamentoOutputDTO {

    private Long id;

    private Long usuarioId;

    private TipoLancamento tipo;

    private String descricao;

    private BigDecimal valor;

    private BigDecimal valorBaixado;

    private StatusLancamento status;

    private LocalDate dataVencimento;

    private LocalDate dataCompetencia;

    private Long entidadeId;
    private String entidadeNome;

    private Long centroCustoId;
    private String centroCustoNome;

    private Long contaBancariaId;
    private String contaBancariaNome;

    private Long cartaoCreditoId;
    private String cartaoCreditoNome;

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

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorBaixado() {
        return valorBaixado;
    }

    public void setValorBaixado(BigDecimal valorBaixado) {
        this.valorBaixado = valorBaixado;
    }

    public StatusLancamento getStatus() {
        return status;
    }

    public void setStatus(StatusLancamento status) {
        this.status = status;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataCompetencia() {
        return dataCompetencia;
    }

    public void setDataCompetencia(LocalDate dataCompetencia) {
        this.dataCompetencia = dataCompetencia;
    }

    public Long getEntidadeId() {
        return entidadeId;
    }

    public void setEntidadeId(Long entidadeId) {
        this.entidadeId = entidadeId;
    }

    public String getEntidadeNome() {
        return entidadeNome;
    }

    public void setEntidadeNome(String entidadeNome) {
        this.entidadeNome = entidadeNome;
    }

    public Long getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Long centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public String getCentroCustoNome() {
        return centroCustoNome;
    }

    public void setCentroCustoNome(String centroCustoNome) {
        this.centroCustoNome = centroCustoNome;
    }

    public Long getContaBancariaId() {
        return contaBancariaId;
    }

    public void setContaBancariaId(Long contaBancariaId) {
        this.contaBancariaId = contaBancariaId;
    }

    public String getContaBancariaNome() {
        return contaBancariaNome;
    }

    public void setContaBancariaNome(String contaBancariaNome) {
        this.contaBancariaNome = contaBancariaNome;
    }

    public Long getCartaoCreditoId() {
        return cartaoCreditoId;
    }

    public void setCartaoCreditoId(Long cartaoCreditoId) {
        this.cartaoCreditoId = cartaoCreditoId;
    }

    public String getCartaoCreditoNome() {
        return cartaoCreditoNome;
    }

    public void setCartaoCreditoNome(String cartaoCreditoNome) {
        this.cartaoCreditoNome = cartaoCreditoNome;
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
