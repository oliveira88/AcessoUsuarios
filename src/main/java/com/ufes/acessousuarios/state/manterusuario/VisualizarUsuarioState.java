package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;

public class VisualizarUsuarioState extends ManterUsuarioState {

    public VisualizarUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
    }
    
    @Override
    public void editar() {
        this.presenter.setState(new EditarUsuarioState(presenter));
    }
}
