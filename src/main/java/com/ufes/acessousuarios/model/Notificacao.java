package com.ufes.acessousuarios.model;

import java.time.LocalDate;

public class Notificacao extends NotificacaoUsuario {
    private Long id;
    private String titulo;
    private String mensagem;
    private Long destinatarioId;
    private Long remetenteId;
    private Boolean aprovacao;
    private Boolean visualizada;
    private LocalDate dataEnvio;
    private LocalDate dataVisualizacao;

    public Notificacao(Long id, String titulo, String mensagem, Long destinatarioId, Long remetenteId, Boolean aprovacao, Boolean visualizada, LocalDate dataEnvio, LocalDate dataVisualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.destinatarioId = destinatarioId;
        this.remetenteId = remetenteId;
        this.aprovacao = aprovacao;
        this.visualizada = visualizada;
        this.dataEnvio = dataEnvio;
        this.dataVisualizacao = dataVisualizacao;
    }

    public Notificacao(String titulo, String mensagem, Long destinatarioId, Long remetenteId, LocalDate dataEnvio) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.destinatarioId = destinatarioId;
        this.remetenteId = remetenteId;
        this.dataEnvio = dataEnvio;
    }
    
    public Notificacao(Long id, String titulo, String mensagem) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
    
    public Notificacao(String titulo, String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
    
     public Long getId(){
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Long destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public Long getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(Long remetenteId) {
        this.remetenteId = remetenteId;
    }

    public Boolean getVisualizada() {
        return visualizada;
    }

    public void setVisualizada(Boolean visualizada) {
        this.visualizada = visualizada;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public LocalDate getDataVisualizacao() {
        return dataVisualizacao;
    }

    public void setDataVisualizacao(LocalDate dataVisualizacao) {
        this.dataVisualizacao = dataVisualizacao;
    }

    public Boolean getAprovacao() {
        return aprovacao;
    }

    public void setAprovacao(Boolean aprovacao) {
        this.aprovacao = aprovacao;
    }
}
