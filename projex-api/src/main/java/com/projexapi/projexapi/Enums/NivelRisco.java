package com.projexapi.projexapi.Enums;

public enum NivelRisco {
    B("Baixo"),

    M("Médio"),

    A("Alto");

    private String descricao;
    private NivelRisco(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return descricao;
    }
}
