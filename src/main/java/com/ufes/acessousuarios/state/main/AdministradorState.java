package com.ufes.acessousuarios.state.main;

import com.ufes.acessousuarios.presenter.MainPresenter;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;

public class AdministradorState extends MainPresenterState {
    public AdministradorState(MainPresenter mainPresenter) {
        super(mainPresenter);
        this.initComponentes();
    }
       
    @Override
    public void initComponentes() {
        this.view.setTitle("Admin");
        this.view.getBarraMenu().setVisible(true);      
        this.view.getjLabelTipo().setVisible(true);
        this.view.getjPanel1().setVisible(true);
        this.view.getjLabelUsuario().setVisible(true);
        this.view.getNameUsuario().setVisible(true);
        this.view.getTipoUsuario().setVisible(true);
        this.view.getBtnNotificacoes().setVisible(true);
        
        this.view.getNameUsuario().setText(UsuarioServiceFactory.getInstance().getService().getUsuarioLogado().getNome());
        this.view.getTipoUsuario().setText("Administrador");
                          
    }
}
