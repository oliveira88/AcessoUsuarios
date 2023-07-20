package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.observer.IObserver;
import com.ufes.acessousuarios.service.NotificacaoService;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;
import com.ufes.acessousuarios.state.manternotificacao.EnviarNotificacaoState;
import com.ufes.acessousuarios.state.manternotificacao.ManterNotificacaoState;
import com.ufes.acessousuarios.state.manternotificacao.VisualizarNotificacaoState;
import com.ufes.acessousuarios.view.ManterNotificacaoView;
import javax.swing.JOptionPane;

public class ManterNotificacaoPresenter {
    private final ManterNotificacaoView view;
    private final MainPresenter mainPresenter;
    private ManterNotificacaoState state;
    
    public ManterNotificacaoPresenter(MainPresenter mainPresenter, Usuario destinatario) {
        this.view = new ManterNotificacaoView();
        this.mainPresenter = mainPresenter;
        setState(new EnviarNotificacaoState(this, destinatario));
        registerPane();
        configMenus();
        this.view.setVisible(true);
    }
    
    public ManterNotificacaoPresenter(MainPresenter mainPresenter, Notificacao notificacao) {
        this.view = new ManterNotificacaoView();
        this.mainPresenter = mainPresenter;
        setState(new VisualizarNotificacaoState(this, notificacao));
        registerPane();
        configMenus();
        this.view.setVisible(true);
    }

    public ManterNotificacaoView getView() {
        return view;
    }
    
    private void registerPane() {
        this.mainPresenter.addDesktopPane(view);
    }
    
    public void configMenus() {
        this.view.getBtnEnviar().addActionListener((e) -> {
           try {
                this.state.enviar();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao enviar a notificação " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        this.view.getBtnFechar().addActionListener((e) -> {
            this.state.fechar();
        });
        
        this.view.getBtnAprovar().addActionListener((e) -> {
            this.state.aprovar();
        });
        this.view.getBtnRecusar().addActionListener((e) -> {
            this.state.recusar();
        });
    }

    public void setState(ManterNotificacaoState state) {
        this.state = state;
    }

    public ManterNotificacaoState getState() {
        return state;
    }
}
