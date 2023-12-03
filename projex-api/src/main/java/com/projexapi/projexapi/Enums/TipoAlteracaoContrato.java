package com.projexapi.projexapi.Enums;

public enum TipoAlteracaoContrato {
    A("Aditivo"),


    S("Supressão");


    private String descricao;
    private TipoAlteracaoContrato(String descricao) {
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
