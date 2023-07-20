package com.ufes.acessousuarios.dao;


public class UsuarioDaoFactory {
    
    private static UsuarioDaoFactory instancia;
    private IUsuarioDAO usuarioDao;
    
    private UsuarioDaoFactory(){
        usuarioDao = new UsuarioDAO();
    } 
    
    public static UsuarioDaoFactory getInstance() {
        if (instancia == null) {
            instancia = new UsuarioDaoFactory();
        }
        return instancia;
    }

    public IUsuarioDAO getUsuarioDao() {
        return usuarioDao;
    }
    
    public static void reset() {
        instancia = null;
    }
}
