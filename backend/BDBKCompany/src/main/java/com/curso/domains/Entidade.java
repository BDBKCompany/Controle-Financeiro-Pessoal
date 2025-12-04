package com.curso.domains;

import com.curso.domains.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "entidade")
public class Entidade extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Entidade pertence a um usuário (dono dos lançamentos)
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotBlank(message = "Nome da entidade é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Size(max = 30, message = "Documento deve ter no máximo 30 caracteres")
    @Column(name = "documento", length = 30)
    private String documento;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 30, message = "Tipo deve ter no máximo 30 caracteres")
    @Column(name = "tipo", nullable = false, length = 30)
    private String tipo; // Ex: "PESSOA_FISICA", "PESSOA_JURIDICA", "LOJA", "OUTRO"

    public Entidade() {
    }

    public Entidade(Usuario usuario, String nome, String documento, String tipo) {
        this.usuario = usuario;
        this.nome = nome;
        this.documento = documento;
        this.tipo = tipo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entidade)) return false;
        Entidade other = (Entidade) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

