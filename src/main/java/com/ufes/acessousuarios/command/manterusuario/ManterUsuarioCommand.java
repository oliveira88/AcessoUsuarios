package com.ufes.acessousuarios.command.manterusuario;

import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;

public abstract class ManterUsuarioCommand implements ICommand {
    protected Usuario usuario;
    protected UsuarioService usuarioService;

    public ManterUsuarioCommand(Usuario usuario) {
        this.usuario = usuario;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
    }
    
    public abstract void executar();
    
}
