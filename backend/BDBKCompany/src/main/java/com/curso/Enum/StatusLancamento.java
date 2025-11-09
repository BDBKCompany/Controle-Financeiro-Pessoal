package com.curso.Enum;

public enum StatusLancamento {
    PENDENTE(0, "PENDENTE"),
    BAIXADO(1, "BAIXADO"),
    PARCIAL(2, "PARCIAL"),
    CANCELADO(3, "CANCELADO");
    private Integer id;
    private String descricao;
    StatusLancamento(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    public Integer getId() {
        return id;
    }
    public String getDescricao() {
        return descricao;
    }
    public static StatusLancamento toEnum(Integer id) {
        if (id == null) {
            return null;
        }
        for (StatusLancamento status : StatusLancamento.values()) {
            if (id.equals(status.getId())) {
                return status;
            }
        }

        throw new IllegalArgumentException("Status de lançamento inválido: " + id);
    }
}
