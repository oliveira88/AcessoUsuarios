package com.ufes.acessousuarios.state.main;

import com.ufes.acessousuarios.presenter.MainPresenter;
import com.ufes.acessousuarios.view.MainView;

public abstract class MainPresenterState {
    protected MainPresenter presenter;
    protected MainView view;
    public MainPresenterState(MainPresenter mainPresenter) {
        this.presenter = mainPresenter;
        this.view = this.presenter.getView();
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
        throw new RuntimeException("Não é possível a configuração de log.");
    }
}
