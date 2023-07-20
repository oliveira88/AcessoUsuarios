package com.ufes.acessousuarios.command.manterusuario;

import com.ufes.acessousuarios.model.Usuario;

public class EditarUsuarioCommand extends ManterUsuarioCommand {
    public EditarUsuarioCommand(Usuario usuario) {
        super(usuario);
    }

    @Override
    public void executar() {
        this.usuarioService.atualizar(usuario);
    }
}
