package com.ufes.acessousuarios.command.manterusuario;

import com.ufes.acessousuarios.model.Usuario;

public class ExcluirUsuarioCommand extends ManterUsuarioCommand {
    public ExcluirUsuarioCommand(Usuario usuario) {
        super(usuario);
    }

    @Override
    public void executar() {
        this.usuarioService.excluir(usuario);
    }

}
