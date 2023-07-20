package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.view.ManterUsuarioView;

public abstract class ManterUsuarioState {
    protected ManterUsuarioPresenter presenter;
    protected ManterUsuarioView view;
    protected Usuario usuario;
    protected UsuarioService usuarioService;

    public ManterUsuarioState(ManterUsuarioPresenter presenter, Usuario usuario) {
        this.presenter = presenter;
        this.view = this.presenter.getView();
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        this.usuario = usuario;
        this.initComponents();
    }

    public void salvar() {
        throw new RuntimeException("Não é possível executar salvar.");
    }
    public void editar() {
        throw new RuntimeException("Não é possível executar editar.");
    }
    public void excluir() {
        throw new RuntimeException("Não é possível executar excluir.");
    }
    public void cancelar() {
        throw new RuntimeException("Não é possível executar cancelar.");
    }
    public void fechar() {
        this.view.dispose();
    }
    
    protected void initComponents() {
        
        this.view.getBtnCancelar().setEnabled(false);
        this.view.getBtnConfirmar().setEnabled(false);
        this.view.getBtnEditar().setEnabled(false);
        this.view.getBtnExcluir().setEnabled(false);
        this.view.getBtnFechar().setVisible(true);
        this.view.getChkAdmin().setVisible(false);
        this.view.getChkAdmin().setEnabled(false);
        this.view.getChkAtivo().setVisible(false);
        this.view.getChkAtivo().setEnabled(false);
        this.view.getLblDataCadastro().setVisible(false);
        this.view.getTxtNome().setEnabled(false);
        this.view.getTxtSenha().setEnabled(false);
        this.view.getTxtLogin().setEnabled(false);
    }
}
