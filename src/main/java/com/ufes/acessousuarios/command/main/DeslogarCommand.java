package com.ufes.acessousuarios.command.main;

import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.presenter.LoginPresenter;
import com.ufes.acessousuarios.presenter.MainPresenter;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.main.NaoLogadoState;

public class DeslogarCommand implements ICommand {
    private MainPresenter presenter;

    public DeslogarCommand(MainPresenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void executar() {
        resetarServices();
        presenter.fecharTodasJanelas();
        presenter.setState(new NaoLogadoState(presenter));
        new LoginPresenter(presenter);
    }
    
    private void resetarServices() {
        UsuarioServiceFactory.reset();
        NotificacaoServiceFactory.reset();
    }
}
