package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;

public class EditarUsuarioState extends ManterUsuarioState {
    public EditarUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
    }
    
    @Override
    public void cancelar() {
        this.presenter.setState(new EditarUsuarioState(presenter));
    }
}
