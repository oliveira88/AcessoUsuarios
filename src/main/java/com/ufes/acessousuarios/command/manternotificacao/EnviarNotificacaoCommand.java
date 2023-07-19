package com.ufes.acessousuarios.command.manternotificacao;

import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterNotificacaoPresenter;
import com.ufes.acessousuarios.service.NotificacaoService;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;

public class EnviarNotificacaoCommand implements ICommand {
    private final ManterNotificacaoPresenter presenter;
    private final NotificacaoService notificacaoService;
    private final Usuario destinatario;
    public EnviarNotificacaoCommand(ManterNotificacaoPresenter presenter, Usuario destinatario) {
        this.presenter = presenter;
        this.destinatario = destinatario;
        this.notificacaoService = NotificacaoServiceFactory.getInstance().getService();
    }

    @Override
    public void executar() {
        var titulo = presenter.getView().getTxtTitulo().getText();
        var mensagem = presenter.getView().getTxtMensagem().getText();
        var notificacaoId = this.notificacaoService.criar(new Notificacao(titulo, mensagem));
        this.notificacaoService.enviar(new Notificacao(notificacaoId, titulo, mensagem), destinatario);
    }
}
