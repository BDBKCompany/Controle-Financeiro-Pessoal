package com.curso.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CartaoCreditoInputDTO {

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "Bandeira é obrigatória")
    @Size(max = 50, message = "Bandeira deve ter no máximo 50 caracteres")
    private String bandeira;

    @NotBlank(message = "Emissor é obrigatório")
    @Size(max = 100, message = "Emissor deve ter no máximo 100 caracteres")
    private String emissor;

    @Size(max = 100, message = "Apelido deve ter no máximo 100 caracteres")
    private String apelido;

    @NotNull(message = "Dia de fechamento da fatura é obrigatório")
    @Min(value = 1, message = "Dia de fechamento deve ser entre 1 e 31")
    @Max(value = 31, message = "Dia de fechamento deve ser entre 1 e 31")
    private Integer fechamentoFaturaDia;

    @NotNull(message = "Dia de vencimento da fatura é obrigatório")
    @Min(value = 1, message = "Dia de vencimento deve ser entre 1 e 31")
    @Max(value = 31, message = "Dia de vencimento deve ser entre 1 e 31")
    private Integer vencimentoFaturaDia;

    private Boolean ativo = true;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Integer getFechamentoFaturaDia() {
        return fechamentoFaturaDia;
    }

    public void setFechamentoFaturaDia(Integer fechamentoFaturaDia) {
        this.fechamentoFaturaDia = fechamentoFaturaDia;
    }

    public Integer getVencimentoFaturaDia() {
        return vencimentoFaturaDia;
    }

    public void setVencimentoFaturaDia(Integer vencimentoFaturaDia) {
        this.vencimentoFaturaDia = vencimentoFaturaDia;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
