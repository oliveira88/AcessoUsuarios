package com.ufes.acessousuarios.state.main;

import com.ufes.acessousuarios.presenter.LoginPresenter;
import com.ufes.acessousuarios.presenter.MainPresenter;

public class NaoLogadoState extends MainPresenterState {

    public NaoLogadoState(MainPresenter mainPresenter) {
        super(mainPresenter);
        this.initComponentes();
        this.configBotoes();
    }
    private void configBotoes() {
        this.view.setTitle("Não logado");
        this.view.getBarraMenu().setVisible(false);
    }
}
