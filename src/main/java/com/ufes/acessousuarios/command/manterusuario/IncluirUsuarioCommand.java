package com.ufes.acessousuarios.command.manterusuario;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;

public class IncluirUsuarioCommand extends ManterUsuarioCommand {
    private Usuario usuario;
    public IncluirUsuarioCommand(ManterUsuarioPresenter presenter) {
        super(presenter);
    }

    @Override
    public void executar() {
        usuario = presenter.lerFormulario();
        this.validar(usuario.getSenha());
        this.service.criarUsuario(usuario);
    }
}
