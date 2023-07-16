package com.ufes.acessousuarios.command.manterusuario;

import com.pss.senha.validacao.ValidadorSenha;
import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.service.UsuarioService;

public abstract class ManterUsuarioCommand implements ICommand {
    protected UsuarioService service;
    protected Usuario usuario;

    public ManterUsuarioCommand(UsuarioService service, Usuario usuario) {
        this.service = service;
        this.usuario = usuario;
    }
    
    public abstract void executar();
    
}
