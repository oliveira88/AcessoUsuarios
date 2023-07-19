package com.ufes.acessousuarios.state.manternotificacao;

import com.ufes.acessousuarios.command.manternotificacao.EnviarNotificacaoCommand;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterNotificacaoPresenter;

public class EnviarNotificacaoState extends ManterNotificacaoState {
    private final Usuario destinatario;
    public EnviarNotificacaoState(ManterNotificacaoPresenter presenter, Usuario destinatario) {
        super(presenter);
        this.destinatario = destinatario;
        this.view.getTxtDestinatario().setText(destinatario.getNome());
    }

    @Override
    public void enviar() {
        new EnviarNotificacaoCommand(presenter, destinatario).executar();
    }
    
     @Override
    protected void initComponents() {
        this.view.setTitle("Enviar Notificação");
        this.view.getTxtRemetente().setEnabled(false);
        this.view.getTxtDestinatario().setEditable(false);
        this.view.getTxtRemetente().setText(this.usuarioService.getUsuarioLogado().getNome());
    }
}
