package com.ufes.acessousuarios.model;

import java.time.LocalDate;

public class NotificacaoUsuario {
    private Long id;
    private Long destinatarioId;
    private Long remetenteId;
    private Long notificacaoId;
    private Boolean lida;
    private LocalDate dataEnvio;
    private LocalDate dataVisualizacao;
    
    
    public Long getId(){
        return id;
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

    public LocalDate getDataVisualizacao() {
        return dataVisualizacao;
    }

    public void setDataVisualizacao(LocalDate dataVisualizacao) {
        this.dataVisualizacao = dataVisualizacao;
    }

 

    public Long getNotificacaoId() {
        return notificacaoId;
    }

    public void setNotificacaoId(Long notificacaoId) {
        this.notificacaoId = notificacaoId;
    }

    public Boolean getLida() {
        return lida;
    }

    public void setLida(Boolean lida) {
        this.lida = lida;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    
    
    
    
}
