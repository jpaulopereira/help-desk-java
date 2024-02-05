package com.jotape.helpdesk.domain;


import com.jotape.helpdesk.domain.enums.Perfil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

//Classe abstrata = Não pode criar instâncias dessa classe
public abstract class Pessoa {
    //protected -> as classe filhas tem acesso ao atributo
    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;

    //new HashSet<>() == evita NullPointerException
    //Integer para armazenar os códigos do enum
    protected Set<Integer> perfils = new HashSet<>();

    //pega o momento atual em que a instância do obj foi criada
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa() {
        super();

        //Caso ao criar um novo cliente/técnico sem passar um parâmetro, será setado o Perfil.CLIENTE
        addPerfils(Perfil.CLIENTE);
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfils(Perfil.CLIENTE);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfils() {
        return perfils.stream().map(x->Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfils(Perfil perfils) {
        this.perfils.add(perfils.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
