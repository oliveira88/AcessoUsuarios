
package com.ufes.acessousuarios.service;

import com.ufes.acessousuarios.dao.IUsuarioDAO;
import com.ufes.acessousuarios.dao.UsuarioDaoFactory;


public class UsuarioServiceFactory {
    
    private static UsuarioServiceFactory instancia;
    private UsuarioService service;
    private final IUsuarioDAO dao;
    
    private UsuarioServiceFactory(){
        dao = UsuarioDaoFactory.getInstance().getUsuarioDao();
        service = new UsuarioService(dao);
    }
    
    public static UsuarioServiceFactory getInstance(){
        if (instancia == null) {
            instancia = new UsuarioServiceFactory();
        }
        return instancia;
    }

    public UsuarioService getService() {
        return service;
    }
    
    
    
}
