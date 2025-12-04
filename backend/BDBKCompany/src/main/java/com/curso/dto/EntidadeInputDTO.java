package com.curso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EntidadeInputDTO {

    @NotNull(message = "Usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 150, message = "Nome deve ter no máximo 150 caracteres")
    private String nome;

    @Size(max = 30, message = "Documento deve ter no máximo 30 caracteres")
    private String documento;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 30, message = "Tipo deve ter no máximo 30 caracteres")
    private String tipo;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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
}
