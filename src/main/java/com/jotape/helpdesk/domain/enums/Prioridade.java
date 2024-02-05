package com.jotape.helpdesk.domain.enums;

public enum Prioridade {
    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(0, "ALTA");

    private Integer codigo;
    private String Decricao;

    Prioridade(Integer codigo, String decricao) {
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
    public static Prioridade toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }
        for(Prioridade x : Prioridade.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade inválido");
    }
}
