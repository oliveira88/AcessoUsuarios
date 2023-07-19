package com.ufes.acessousuarios.command.manterusuario;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import com.ufes.acessousuarios.service.UsuarioService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditarUsuarioCommand extends ManterUsuarioCommand {
    public EditarUsuarioCommand(Usuario usuario) {
        super(usuario);
    }

    @Override
    public void executar() {
        this.service.atualizar(usuario);
    }
}
