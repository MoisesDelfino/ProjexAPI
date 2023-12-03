package com.projexapi.projexapi.Enums;

public enum Fase {

    I("Iniciação"),
    P("Planejamento"),
    EX("Execução"),
    M("Monitoramento"),
    C("Controle"),
    EN("Encerramento");

    private String descricao;
    private Fase(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return descricao;
    }
}
