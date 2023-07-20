package com.ufes.acessousuarios.command.manternotificacao;

import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.observer.IObserver;
import com.ufes.acessousuarios.presenter.ManterNotificacaoPresenter;
import com.ufes.acessousuarios.service.NotificacaoService;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.manternotificacao.ManterNotificacaoState;

public class VisualizarNotificacaoCommand implements ICommand {
    private final ManterNotificacaoPresenter presenter;
    private final NotificacaoService notificacaoService;
    private final UsuarioService usuarioService;
    private final Notificacao notificacao;

    public VisualizarNotificacaoCommand(ManterNotificacaoPresenter presenter, Notificacao notificacao) {
        this.presenter = presenter;
        this.notificacao = notificacao;
        this.notificacaoService = NotificacaoServiceFactory.getInstance().getService();
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
    }

    @Override
    public void executar() {
        this.notificacaoService.lerNotificacao(this.usuarioService.getUsuarioLogado(), notificacao.getId());
    }
}
