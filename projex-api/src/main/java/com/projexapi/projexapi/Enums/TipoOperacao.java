package com.projexapi.projexapi.Enums;

public enum TipoOperacao{
    C("Create"),


    U("Update"),

    D("Delete");



    private String descricao;
    private TipoOperacao(String descricao) {
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
