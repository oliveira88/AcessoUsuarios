
package com.ufes.acessousuarios.service;

import com.ufes.acessousuarios.dao.IUsuarioDAO;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.observer.Observable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService extends Observable {
    private static UsuarioService instancia;
    private Usuario usuarioLogado;
    private IUsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;
  
    public UsuarioService(IUsuarioDAO dao) {
        this.usuarioDAO = dao;
        this.usuarios = new ArrayList<>();
        this.initUsuarios();
    }
    
    private void initUsuarios() {
        this.usuarios = usuarioDAO.obterTodos();
    }
    
    public Usuario realizarLogin(String login, String senha) {
        return usuarioDAO.login(login, senha);
    }
    
    public void criarUsuario(Usuario usuario) throws SQLException {
            usuarioDAO.criar(usuario);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    } 

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public Usuario obterPorId(Long id) throws RuntimeException {
        return usuarioDAO.obterPorId(id);
    }
    
    public void atualizar(Usuario usuario) throws RuntimeException{
        this.usuarioDAO.atualizar(usuario);
    }
     
    
}
