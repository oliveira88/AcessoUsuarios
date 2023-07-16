package com.ufes.acessousuarios.dao;

public class NotificacaoUsuarioDAOFactory {
     private static NotificacaoUsuarioDAOFactory instancia;
    private INotificacaoUsuarioDAO notificacoUsuarioDao;
    
    private NotificacaoUsuarioDAOFactory(){
        notificacoUsuarioDao = new NotificacaoUsuarioDAO();
    } 
    
    public static NotificacaoUsuarioDAOFactory getInstance() {
        if (instancia == null) {
            instancia = new NotificacaoUsuarioDAOFactory();
        }
        return instancia;
    }

    public INotificacaoUsuarioDAO getNotificacaoUsuarioDAO() {
        return notificacoUsuarioDao;
    }
}
