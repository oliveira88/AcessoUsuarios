package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.main.MainPresenterState;
import com.ufes.acessousuarios.state.main.NaoLogadoState;
import com.ufes.acessousuarios.view.MainView;
import java.awt.Component;
import javax.swing.JOptionPane;
import com.ufes.acessousuarios.observer.IObserver;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;

public final class MainPresenter implements IObserver {
    private final MainView view;
    private MainPresenterState state;
    private final UsuarioService usuarioService;
    //TODO: Lembrar de instanicar as daos nas sua servoce
    
    public MainPresenter() {
        this.view = new MainView();
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        this.usuarioService.addObserver(this);
        this.configMenus();
        this.setState(new NaoLogadoState(this));
        
        this.initComponente();
        this.view.setVisible(true);
    }
    
    private void initComponente(){
        if(this.usuarioService.getUsuarios().isEmpty()) {
            new ManterUsuarioPresenter(this);
        } else {
            new LoginPresenter(this);
        }
    }
    
    public void addDesktopPane(Component component) {
        this.view.getDesktopPane().add(component);
    }
    
    public void configMenus() {
        this.view.getMenuBuscar().addActionListener((e) -> {
              this.state.buscarUsuarios();
        });
        this.view.getMenuCadastrar().addActionListener((e) -> {
            this.state.cadastrarUsuario();
        });
        this.view.getMenuLog().addActionListener((e) -> {
            this.state.configurarLog();
            showDialog("Abrir log");
        });
        this.view.getMenuSair().addActionListener((e) -> {
            this.state.logout();
            showDialog("Deslogar");
        });
        this.view.getBtnNotificacoes().addActionListener((e) -> {
            this.state.buscarNotificacoes();
        });
        
    }
    
    public void fecharTodasJanelas(){
        for(var frame : view.getDesktopPane().getAllFrames()) {
            frame.dispose();
        }
    }
    
    private void showDialog(String message) {
        JOptionPane.showMessageDialog(getView(), message, "TODO!", JOptionPane.INFORMATION_MESSAGE);
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

    @Override
    public void update() {
        this.state.obterQuantidadeNotificacoes();
    }
}
