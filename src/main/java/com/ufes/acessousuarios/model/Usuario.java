package com.ufes.acessousuarios.model;

import java.time.LocalDateTime;

public class Usuario {
    private long id;
    private String login;
    private String senha;
    private String nome;
    private boolean admin;
    private boolean ativo;
    private LocalDateTime dataCriacao;

    public Usuario(long id, String login, String senha, String nome, boolean admin, boolean ativo, LocalDateTime dataCriacao) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.admin = admin;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
    }

    public Usuario(String login, String senha, String nome, boolean admin, LocalDateTime dataCriacao) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.admin = admin;
        this.dataCriacao = dataCriacao;
    }
    
     public Usuario(String login, String senha, String nome, boolean admin, boolean ativo, LocalDateTime dataCriacao) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.admin = admin;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
    }
    
    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
    
     public String getNome() {
        return nome;
    }

    public boolean isAdmin() {
        return admin;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
