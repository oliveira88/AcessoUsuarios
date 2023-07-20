package com.ufes.acessousuarios.state.manternotificacao;

import com.ufes.acessousuarios.command.manternotificacao.VisualizarNotificacaoCommand;
import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterNotificacaoPresenter;

public class VisualizarNotificacaoState extends ManterNotificacaoState {
    private final Notificacao notificacao;
    public VisualizarNotificacaoState(ManterNotificacaoPresenter presenter, Notificacao notificacao) {
        super(presenter);
        this.notificacao = notificacao;
        var remetente = this.usuarioService.getUsuario(notificacao.getRemetenteId());
        var destinatario = this.usuarioService.getUsuario(notificacao.getDestinatarioId());
        this.view.getTxtRemetente().setText(remetente.getNome());
        this.view.getTxtDestinatario().setText(destinatario.getNome());
        this.view.getTxtTitulo().setText(notificacao.getTitulo());
        this.view.getTxtMensagem().setText(notificacao.getMensagem());
        if(notificacao.getAprovacao() && this.usuarioService.getUsuarioLogado().isAdmin()) {
            this.view.getBtnAprovar().setVisible(true);        
            this.view.getBtnRecusar().setVisible(true); 
        }
    }
    
    @Override
    public void fechar() {
        super.fechar();
    }

    @Override
    public void aprovar() {
        super.aprovar();
    }

    @Override
    public void recusar() {
        super.recusar();
    }

    @Override
    public void visualizar() {
        new VisualizarNotificacaoCommand(presenter, notificacao).executar();
    }
    
    @Override
    protected void initComponents() {
        super.initComponents();
        this.view.setTitle("Visualizar Notificação");
        this.view.getTxtTitulo().setEditable(false);
        this.view.getTxtMensagem().setEditable(false);
        this.view.getBtnEnviar().setVisible(false);
    }
}
