package com.curso.dto;

import com.curso.Enum.TipoLancamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoInputDTO {

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "Tipo é obrigatório")
    private TipoLancamento tipo;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres")
    private String descricao;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    @NotNull(message = "Data de vencimento é obrigatória")
    private LocalDate dataVencimento;

    private LocalDate dataCompetencia;

    private Long entidadeId;

    private Long centroCustoId;

    private Long contaBancariaId;

    private Long cartaoCreditoId;

    private String observacao;

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

    public Long getCentroCustoId() {
        return centroCustoId;
    }

    public void setCentroCustoId(Long centroCustoId) {
        this.centroCustoId = centroCustoId;
    }

    public Long getContaBancariaId() {
        return contaBancariaId;
    }

    public void setContaBancariaId(Long contaBancariaId) {
        this.contaBancariaId = contaBancariaId;
    }

    public Long getCartaoCreditoId() {
        return cartaoCreditoId;
    }

    public void setCartaoCreditoId(Long cartaoCreditoId) {
        this.cartaoCreditoId = cartaoCreditoId;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
