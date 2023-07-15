package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.state.main.MainPresenterState;
import com.ufes.acessousuarios.state.main.NaoLogadoState;
import com.ufes.acessousuarios.view.MainView;
import java.awt.Component;
import javax.swing.JOptionPane;

public class MainPresenter {
    private final MainView view;
    private MainPresenterState state;
    private final UsuarioService usuarioService;
    public MainPresenter() {
        this.view = new MainView();
        this.usuarioService = UsuarioService.getInstancia();
        this.configMenus();
        this.setState(new NaoLogadoState(this));
        if(this.usuarioService.getUsuarios().isEmpty()) {
            new ManterUsuarioPresenter(this);
        } else {
            new LoginPresenter(this);
        }
        this.view.setVisible(true);
    }

    public MainView getView() {
        return view;
    }

    public MainPresenterState getState() {
        return state;
    }

    public void setState(MainPresenterState state) {
        this.state = state;
    }
    
    public void addDesktopPane(Component component) {
        this.view.getDesktopPane().add(component);
    }
    
    public void configMenus() {
        this.view.getMenuBuscar().addActionListener((e) -> {
            System.out.println("Abrir buscar");
            showDialog("Abrir buscar");
        });
        this.view.getMenuCadastrar().addActionListener((e) -> {
            showDialog("Abrir cadastrar");
        });
        this.view.getMenuLog().addActionListener((e) -> {
            showDialog("Abrir log");
        });
    }
    
    private void showDialog(String message) {
        JOptionPane.showMessageDialog(getView(), message, "Primeiro Acesso!", JOptionPane.INFORMATION_MESSAGE);
    }
}
