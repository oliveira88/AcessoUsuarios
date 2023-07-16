package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import com.ufes.acessousuarios.view.ManterUsuarioView;

public abstract class ManterUsuarioState {
    protected ManterUsuarioPresenter presenter;
    protected Usuario usuario;

    public ManterUsuarioState(ManterUsuarioPresenter presenter, Usuario usuario) {
        this.presenter = presenter;
        this.usuario = usuario;
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
        this.presenter.getView().dispose();
    }
}
