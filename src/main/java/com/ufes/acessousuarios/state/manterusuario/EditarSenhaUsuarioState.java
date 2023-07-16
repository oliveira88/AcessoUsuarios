package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;

public class EditarSenhaUsuarioState extends ManterUsuarioState {
    public EditarSenhaUsuarioState(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter, usuario);
    }
}
