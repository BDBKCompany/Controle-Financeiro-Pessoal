package com.curso.Enum;

import java.util.Objects;

public enum MeioPagamento {
    CONTA(0,"CONTA") ,
    CARTAO(1,"CARTAO") ,
    DINHEIRO(2,"DINHEIRO") ,
    PIX(3,"PIX");

    private Integer id;
    private String descricao;
    MeioPagamento(Integer id,String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }


    public static MeioPagamento fromId(Integer id, String descricao) {
        if (id == null || descricao == null) {
            return null;
        }
        for (MeioPagamento meio : values()) {
            if (Objects.equals(meio.getId(), id) && meio.getDescricao().equals(descricao)) {
                return meio;
            }
        }
        throw new IllegalArgumentException("ID inválido para MeioPagamento: " + id);

    }

    @Override
    public String toString() {
        return descricao;
}
}