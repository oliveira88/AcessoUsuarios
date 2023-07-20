package com.ufes.acessousuarios.state.main;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.LoginPresenter;
import com.ufes.acessousuarios.presenter.MainPresenter;
import com.ufes.acessousuarios.view.MainView;

public abstract class MainPresenterState {
    protected MainPresenter presenter;
    protected MainView view;
    protected LoginPresenter loginPresenter; 
    private Usuario usuario;
    
    public MainPresenterState(MainPresenter mainPresenter) {
        this.presenter = mainPresenter;
        this.view = this.presenter.getView();
        this.initComponentes();
    }
    
    public void cadastrarUsuario() {
        throw new RuntimeException("Não é possível executar o cadastro.");
    }
    public void buscarUsuarios() {
        throw new RuntimeException("Não é possível executar a busca de usuarios.");
    }
    public void logout() {
        throw new RuntimeException("Não é possível fazer o logout.");
    }
    public void configurarLog() {
        throw new RuntimeException("Não é possível executar a configuração de log.");
    }
    public void buscarNotificacoes() {
        throw new RuntimeException("Não é possível executar a busca de notificações.");
    }
    public void obterQuantidadeNotificacoes() {
        throw new RuntimeException("Não é possível obter a quantidade de notificações.");
    }
    public void alterarSenhar() {
        throw new RuntimeException("Não é possível executar alterar senha.");
    }

    public void initComponentes() {
        this.view.getjLabelTipo().setVisible(false);
        this.view.getjPanel1().setVisible(false);
        this.view.getjLabelUsuario().setVisible(false);
        this.view.getNameUsuario().setVisible(false);
        this.view.getTipoUsuario().setVisible(false);
        this.view.getBtnNotificacoes().setVisible(false);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    
    
}
