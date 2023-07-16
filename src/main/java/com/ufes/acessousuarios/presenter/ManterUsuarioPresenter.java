package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.manterusuario.ManterUsuarioState;
import com.ufes.acessousuarios.view.ManterUsuarioView;

public class ManterUsuarioPresenter {
    private final ManterUsuarioView view;
    private final MainPresenter mainPresenter;
    private final UsuarioService usuarioService;
    private ManterUsuarioState state;
       
    public ManterUsuarioPresenter(MainPresenter mainPresenter) {
        this.view = new ManterUsuarioView();
        this.mainPresenter = mainPresenter;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        
        this.registerPane();
        this.view.setVisible(true);
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
    
    
    
}
