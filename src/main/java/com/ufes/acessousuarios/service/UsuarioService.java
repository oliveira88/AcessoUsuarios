
package com.ufes.acessousuarios.service;

import com.ufes.acessousuarios.Util.AcessoException;
import com.ufes.acessousuarios.dao.IUsuarioDAO;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.observer.Observable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService extends Observable {
    private Usuario usuarioLogado;
    private final IUsuarioDAO usuarioDAO;
    private List<Usuario> usuarios;
  
    public UsuarioService(IUsuarioDAO dao) {
        this.usuarioDAO = dao;
        this.usuarios = new ArrayList<>();
        this.initUsuarios();
    }
    
    private void initUsuarios() {
        this.usuarios = usuarioDAO.obterTodos();
        notifyObservers();
    }
    public List<Usuario> obterTodosUsuarios() {
        return usuarioDAO.obterTodos();
    }
        
    public Usuario realizarLogin(String login, String senha) throws AcessoException {
        return usuarioDAO.login(login, senha);
    }
    
    public void criarUsuario(Usuario usuario, boolean admin) throws SQLException {
        usuarioDAO.criar(usuario);
        if(!admin) {
            var todosUsuarios = obterTodosUsuarios();
            var ultimoUsuario = todosUsuarios.get(todosUsuarios.size() - 1);
             NotificacaoServiceFactory.getInstance(false).getService().enviarPedidoAcesso(ultimoUsuario);
        }
        initUsuarios();
    }
    
    
    
    public void excluir(Usuario usuario) {
        usuarioDAO.deletar(usuario.getId());
        initUsuarios();
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    } 

    public Usuario getUsuario(Long id) {
        return usuarioDAO.obterPorId(id);
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
        initUsuarios();
    }
    
    public void autorizar(Usuario usuario) {
        this.usuarioDAO.autorizarUsuario(usuario.getId());
        initUsuarios();
    }
    
    public void recusar(Usuario usuario) {
        this.usuarioDAO.recusarUsuario(usuario.getId());
        initUsuarios();
    }
}
