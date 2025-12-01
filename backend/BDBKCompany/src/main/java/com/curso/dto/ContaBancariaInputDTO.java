package com.curso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaBancariaInputDTO {

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "Instituição é obrigatória")
    @Size(max = 150, message = "Instituição deve ter no máximo 150 caracteres")
    private String instituicao;

    @NotBlank(message = "Agência é obrigatória")
    @Size(max = 20, message = "Agência deve ter no máximo 20 caracteres")
    private String agencia;

    @NotBlank(message = "Número da conta é obrigatório")
    @Size(max = 30, message = "Número da conta deve ter no máximo 30 caracteres")
    private String numero;

    @Size(max = 100, message = "Apelido deve ter no máximo 100 caracteres")
    private String apelido;

    @NotNull(message = "Saldo inicial é obrigatório")
    private BigDecimal saldoInicial;

    @NotNull(message = "Data do saldo inicial é obrigatória")
    private LocalDate dataSaldoInicial;

    private Boolean ativa = true;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public LocalDate getDataSaldoInicial() {
        return dataSaldoInicial;
    }

    public void setDataSaldoInicial(LocalDate dataSaldoInicial) {
        this.dataSaldoInicial = dataSaldoInicial;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }
}
