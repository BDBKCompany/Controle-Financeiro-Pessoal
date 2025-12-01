package com.curso.domains;

import com.curso.domains.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "centro_custo", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "codigo"})
})
public class CentroCusto extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Size(max = 20)
    @Column(name = "codigo", length = 20)
    private String codigo;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @OneToMany(mappedBy = "centroCusto", fetch = FetchType.LAZY)
    private List<Lancamento> lancamentos = new ArrayList<>();

    public CentroCusto() {}

    public CentroCusto(Usuario usuario, String nome, String codigo) {
        this.usuario = usuario;
        this.nome = nome;
        this.codigo = codigo;
        this.ativo = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public List<Lancamento> getLancamentos() { return lancamentos; }
    public void setLancamentos(List<Lancamento> lancamentos) { this.lancamentos = lancamentos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CentroCusto)) return false;
        CentroCusto that = (CentroCusto) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CentroCusto{id=" + id + ", nome='" + nome + "', ativo=" + ativo + "}";
    }
}
