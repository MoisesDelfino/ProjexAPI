package com.projexapi.projexapi.Enums;

public enum Ativo {
    A("Ativo"),
    I("Inativo");

    private String descricao;
    private Ativo(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return descricao;
    }
}
