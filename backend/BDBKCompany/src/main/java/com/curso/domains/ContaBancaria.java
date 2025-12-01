package com.curso.domains;

import com.curso.domains.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class ContaBancaria extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotBlank(message = "Instituição é obrigatória")
    @Column(name = "instituicao", length = 150, nullable = false)
    private String instituicao;

    @NotBlank(message = "Agência é obrigatória")
    @Column(name = "agencia", length = 20, nullable = false)
    private String agencia;

    @NotBlank(message = "Número da conta é obrigatório")
    @Column(name = "numero_conta", length = 30, nullable = false)
    private String numero;

    @Column(name = "apelido", length = 100)
    private String apelido;

    @NotNull(message = "Saldo inicial é obrigatório")
    @Column(name = "saldo_inicial", precision = 15, scale = 2, nullable = false)
    private BigDecimal saldoInicial;

    @NotNull(message = "Data do saldo inicial é obrigatória")
    @Column(name = "data_saldo_inicial", nullable = false)
    private LocalDate dataSaldoInicial;

    @Column(name = "ativa", nullable = false)
    private Boolean ativa = true;


    public ContaBancaria() {}

    public ContaBancaria(Usuario usuario, String instituicao, String agencia, String numero,
                         String apelido, BigDecimal saldoInicial, LocalDate dataSaldoInicial) {

        this.usuario = usuario;
        this.instituicao = instituicao;
        this.agencia = agencia;
        this.numero = numero;
        this.apelido = apelido;
        this.saldoInicial = saldoInicial;
        this.dataSaldoInicial = dataSaldoInicial;
        this.ativa = true;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaBancaria)) return false;
        ContaBancaria other = (ContaBancaria) o;
        return id != null && id.equals(other.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
