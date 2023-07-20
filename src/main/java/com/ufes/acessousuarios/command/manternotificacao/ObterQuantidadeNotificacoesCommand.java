package com.ufes.acessousuarios.command.manternotificacao;

import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.presenter.MainPresenter;
import com.ufes.acessousuarios.service.NotificacaoService;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;

public class ObterQuantidadeNotificacoesCommand implements ICommand {
    private final MainPresenter mainPresenter;
    private final NotificacaoService notificacaoService;

    public ObterQuantidadeNotificacoesCommand(MainPresenter presenter) {
        this.mainPresenter = presenter;
        notificacaoService = NotificacaoServiceFactory.getInstance().getService();
    }

    @Override
    public void executar() {
        var notificacoes = notificacaoService.getNotificacoes();
        mainPresenter.getView().getBtnNotificacoes().setText("Notificações: " + notificacoes.size());
    }

}
