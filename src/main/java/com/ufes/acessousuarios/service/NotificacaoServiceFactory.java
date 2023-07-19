package com.ufes.acessousuarios.service;

import com.ufes.acessousuarios.dao.INotificacaoDAO;
import com.ufes.acessousuarios.dao.NotificacaoDaoFactory;

public class NotificacaoServiceFactory {
    private static NotificacaoServiceFactory instancia;
    private final NotificacaoService service;
    private final INotificacaoDAO dao;
    
    private NotificacaoServiceFactory(){
        dao = NotificacaoDaoFactory.getInstance().getNotificacaoDAO();
        service = new NotificacaoService(dao);
    }
    
    public static NotificacaoServiceFactory getInstance(){
        if (instancia == null) {
            instancia = new NotificacaoServiceFactory();
        }
        return instancia;
    }

    public NotificacaoService getService() {
        return service;
    }
}
