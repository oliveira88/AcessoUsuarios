package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import com.ufes.acessousuarios.view.ManterUsuarioView;

public abstract class ManterUsuarioState {
    protected ManterUsuarioPresenter presenter;
    protected ManterUsuarioView view;

    public ManterUsuarioState(ManterUsuarioPresenter presenter) {
        this.presenter = presenter;
        this.view = this.presenter.getView();
    }

    public void salvar() {
        throw new RuntimeException("Não é possível executar salvar.");
    }
    public void editar() {
        throw new RuntimeException("Não é possível executar editar.");
    }
    public void excluir() {
        throw new RuntimeException("Não é possível executar excluir.");
    }
    public void cancelar() {
        throw new RuntimeException("Não é possível executar cancelar.");
    }
}
