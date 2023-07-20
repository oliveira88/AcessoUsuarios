package com.ufes.acessousuarios.command.manternotificacao;

import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterNotificacaoPresenter;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;

public class RecusarUsuarioCommand implements ICommand {
private final UsuarioService usuarioService;
    private final ManterNotificacaoPresenter presenter;
    private final Usuario usuario;
    
    public RecusarUsuarioCommand(ManterNotificacaoPresenter presenter, Usuario usuario) {
        this.presenter = presenter;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        this.usuario =  usuario;
    }
    
    @Override
    public void executar() {
        usuarioService.recusar(usuario);
        
    }
}
