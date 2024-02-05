package com.jotape.helpdesk.domain.enums;

public enum Status {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(0, "ENCERRADO");

    private Integer codigo;
    private String Decricao;

    Status(Integer codigo, String decricao) {
        this.codigo = codigo;
        Decricao = decricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDecricao() {
        return Decricao;
    }

    //Staic pq não haverá necessidade de cria uma instância de perfil
    //para chamar o código me outra partes do projeto
    public static Status toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }
        for(Status x : Status.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status inválido");
    }
}
