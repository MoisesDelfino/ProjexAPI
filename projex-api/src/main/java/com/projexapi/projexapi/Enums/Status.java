package com.projexapi.projexapi.Enums;

public enum Status{
    A("Aberto"),


    I("Iniciado"),

    P("Pausado"),

    C("Cancelado"),

    F("Finalizado");

    private String descricao;
    private Status(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
