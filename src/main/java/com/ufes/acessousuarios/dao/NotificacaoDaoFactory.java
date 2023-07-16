package com.ufes.acessousuarios.dao;

public class NotificacaoDaoFactory {
    private static NotificacaoDaoFactory instancia;
    private INotificacaoDAO usuarioDao;
    
    private NotificacaoDaoFactory(){
        usuarioDao = new NotificacaoDAO();
    } 
    
    public static NotificacaoDaoFactory getInstance() {
        if (instancia == null) {
            instancia = new NotificacaoDaoFactory();
        }
        return instancia;
    }

    public INotificacaoDAO getNotificacaoDAO() {
        return usuarioDao;
    }
}
