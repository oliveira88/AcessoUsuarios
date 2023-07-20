package com.ufes.acessousuarios.state.manternotificacao;

import com.ufes.acessousuarios.presenter.ManterNotificacaoPresenter;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.view.ManterNotificacaoView;

public abstract class ManterNotificacaoState {
    protected ManterNotificacaoPresenter presenter;
    protected ManterNotificacaoView view;
    protected final UsuarioService usuarioService;

    public ManterNotificacaoState(ManterNotificacaoPresenter presenter) {
        this.presenter = presenter;
        this.view = this.presenter.getView();
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        initComponents();
    }
    
    public void enviar() {
        throw new RuntimeException("Não é possível executar enviar.");
    }
    public void aprovar() {
        throw new RuntimeException("Não é possível executar aprovar.");
    }
    public void recusar() {
        throw new RuntimeException("Não é possível executar recusar.");
    }
    public void visualizar() {
        throw new RuntimeException("Não é possível executar visualizar.");
    }
    public void fechar() {
        this.view.dispose();
    }
    
    protected void initComponents() {
        this.view.getTxtRemetente().setEnabled(false);
        this.view.getTxtDestinatario().setEnabled(false);
        this.view.getBtnAprovar().setVisible(false);        
        this.view.getBtnRecusar().setVisible(false);        
    }
}
