package com.ufes.acessousuarios.command.manterusuario;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import com.ufes.acessousuarios.service.UsuarioService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IncluirUsuarioCommand extends ManterUsuarioCommand {
    public IncluirUsuarioCommand(Usuario usuario) {
        super(usuario);
    }

    @Override
    public void executar() {
        try {
            this.service.criarUsuario(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(IncluirUsuarioCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
