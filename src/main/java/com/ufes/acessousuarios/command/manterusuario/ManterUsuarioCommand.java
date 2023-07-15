package com.ufes.acessousuarios.command.manterusuario;

import com.pss.senha.validacao.ValidadorSenha;
import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.view.ManterUsuarioView;
import java.util.List;

public abstract class ManterUsuarioCommand implements ICommand {
    protected UsuarioService service;
    protected ManterUsuarioPresenter presenter;
    protected ManterUsuarioView view;

    public ManterUsuarioCommand(ManterUsuarioPresenter presenter) {
        this.presenter = presenter;
        this.view = this.presenter.getView();
        this.service = UsuarioService.getInstancia();
    }
    
    public abstract void executar();
    
    public void validar(String senha) {
        List<String> erros = new ValidadorSenha().validar(senha);
        if(!erros.isEmpty()) {
            throw new RuntimeException(erros.get(0));
        }
    }
}
