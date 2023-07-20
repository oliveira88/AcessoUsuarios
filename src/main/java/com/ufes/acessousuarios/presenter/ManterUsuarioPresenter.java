package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.service.NotificacaoService;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.manterusuario.IncluirUsuarioState;
import com.ufes.acessousuarios.state.manterusuario.ManterUsuarioState;
import com.ufes.acessousuarios.view.ManterUsuarioView;
import javax.swing.JOptionPane;

public final class ManterUsuarioPresenter {
    private final ManterUsuarioView view;
    private final MainPresenter mainPresenter;
    private final UsuarioService usuarioService;
    private ManterUsuarioState state;
       
    public ManterUsuarioPresenter(MainPresenter mainPresenter) {
        this.view = new ManterUsuarioView();
        this.mainPresenter = mainPresenter;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        setState(new IncluirUsuarioState(this, this.usuarioService.getUsuarioLogado()));
        this.registerListeners();
        this.registerPane();
        this.view.setVisible(true);
    }

    private void registerListeners() {
        view.getBtnConfirmar().addActionListener((e) -> {
            try {
                this.state.salvar();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao salvar o usuário " + ex, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        view.getBtnEditar().addActionListener((e) -> {
            try {
                this.state.editar();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao salvar o usuário " + ex, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        view.getBtnCancelar().addActionListener((e) -> {
            try {
                this.state.cancelar();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao cancelar" + ex, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.view.getBtnFechar().addActionListener((e) -> {
            try {
                this.state.fechar();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao fechar " + ex, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.view.getBtnExcluir().addActionListener((e) -> {
            try {
                this.state.excluir();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao excluir " + ex, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    private void registerPane() {
        this.mainPresenter.addDesktopPane(view);
    }
   
    public ManterUsuarioView getView() {
        return view;
    }

    public ManterUsuarioState getState() {
        return state;
    }

    public void setState(ManterUsuarioState state) {
        this.state = state;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
    
}
