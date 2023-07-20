package com.ufes.acessousuarios.dao;

public class NotificacaoDaoFactory {
    private static NotificacaoDaoFactory instancia;
    private INotificacaoDAO notificacaoDao;
    
    private NotificacaoDaoFactory(){
        notificacaoDao = new NotificacaoDAO();
    } 
    
    public static NotificacaoDaoFactory getInstance() {
        if (instancia == null) {
            instancia = new NotificacaoDaoFactory();
        }
        return instancia;
    }

    public INotificacaoDAO getNotificacaoDAO() {
        return notificacaoDao;
    }
    
    public static void reset() {
        instancia = null;
    }
}
