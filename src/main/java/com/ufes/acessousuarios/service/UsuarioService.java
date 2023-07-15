/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufes.acessousuarios.service;

import com.ufes.acessousuarios.dao.IUsuarioDAO;
import com.ufes.acessousuarios.dao.UsuarioDAO;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.observer.Observable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioService extends Observable {
    private static UsuarioService instancia;
    private IUsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;
    private UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
        this.usuarios = new ArrayList<>();
        this.initUsuarios();
    }
    
    public static UsuarioService getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioService();
        }
        return instancia;
    }
    
    private void initUsuarios() {
        this.usuarios = usuarioDAO.obterTodos();
    }
    
    public Usuario realizarLogin(String login, String senha) {
        return usuarioDAO.login(login, senha);
    }
    
    public void criarUsuario(Usuario usuario) {
        try {
            usuarioDAO.criar(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    } 
    
}
