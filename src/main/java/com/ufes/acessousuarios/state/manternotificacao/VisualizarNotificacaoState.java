package com.ufes.acessousuarios.state.manternotificacao;

import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterNotificacaoPresenter;

public class VisualizarNotificacaoState extends ManterNotificacaoState {
    private final Usuario remetente;
    private final Usuario destinatario;
    public VisualizarNotificacaoState(ManterNotificacaoPresenter presenter, Notificacao notificacao) {
        super(presenter);
        remetente = this.usuarioService.getUsuario(notificacao.getRemetenteId());
        destinatario = this.usuarioService.getUsuario(notificacao.getDestinatarioId());
        initComponents();
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
    protected void initComponents() {
        this.view.setTitle("Visualizar Notificação");
        this.view.getTxtRemetente().setEnabled(false);
        this.view.getTxtDestinatario().setEditable(false);
        this.view.getTxtRemetente().setText(remetente.getNome());
        this.view.getTxtDestinatario().setText(destinatario.getNome());
    }
    
    
}
