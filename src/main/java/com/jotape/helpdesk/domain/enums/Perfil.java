package com.jotape.helpdesk.domain.enums;

import javax.persistence.criteria.CriteriaBuilder;

public enum Perfil {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(0, "ROLE_TECNICO");

    private Integer codigo;
    private String Decricao;

    Perfil(Integer codigo, String decricao) {
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
    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil inválido");
    }
}
