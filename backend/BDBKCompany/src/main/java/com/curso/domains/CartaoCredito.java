package com.curso.domains;

import com.curso.domains.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "cartao_credito")
public class CartaoCredito extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotBlank(message = "Bandeira é obrigatória")
    @Size(max = 50, message = "Bandeira deve ter no máximo 50 caracteres")
    @Column(name = "bandeira", nullable = false, length = 50)
    private String bandeira;

    @NotBlank(message = "Emissor é obrigatório")
    @Size(max = 100, message = "Emissor deve ter no máximo 100 caracteres")
    @Column(name = "emissor", nullable = false, length = 100)
    private String emissor;

    @Size(max = 100, message = "Apelido deve ter no máximo 100 caracteres")
    @Column(name = "apelido", length = 100)
    private String apelido;

    @NotNull(message = "Dia de fechamento da fatura é obrigatório")
    @Column(name = "fechamento_fatura_dia", nullable = false)
    private Integer fechamentoFaturaDia;

    @NotNull(message = "Dia de vencimento da fatura é obrigatório")
    @Column(name = "vencimento_fatura_dia", nullable = false)
    private Integer vencimentoFaturaDia;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public CartaoCredito() {}

    public CartaoCredito(Usuario usuario, String bandeira, String emissor,
                         String apelido, Integer fechamentoFaturaDia,
                         Integer vencimentoFaturaDia) {

        this.usuario = usuario;
        this.bandeira = bandeira;
        this.emissor = emissor;
        this.apelido = apelido;
        this.fechamentoFaturaDia = fechamentoFaturaDia;
        this.vencimentoFaturaDia = vencimentoFaturaDia;
        this.ativo = true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartaoCredito)) return false;
        CartaoCredito that = (CartaoCredito) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
