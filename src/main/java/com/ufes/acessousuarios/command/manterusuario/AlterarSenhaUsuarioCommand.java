package com.ufes.acessousuarios.command.manterusuario;

import com.ufes.acessousuarios.Util.ValidadorSenha;
import com.ufes.acessousuarios.model.Usuario;

public class AlterarSenhaUsuarioCommand  extends ManterUsuarioCommand {
    public AlterarSenhaUsuarioCommand(Usuario usuario, String novaSenha) {
        super(usuario);
        this.usuario.setSenha(novaSenha);
    }

    @Override
    public void executar() {
        ValidadorSenha.validar(this.usuario.getSenha());
        usuarioService.atualizar(usuario);
    }

}
