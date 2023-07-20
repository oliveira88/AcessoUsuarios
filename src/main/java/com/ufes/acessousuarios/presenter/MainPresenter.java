package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.main.MainPresenterState;
import com.ufes.acessousuarios.state.main.NaoLogadoState;
import com.ufes.acessousuarios.view.MainView;
import java.awt.Component;
import javax.swing.JOptionPane;
import com.ufes.acessousuarios.observer.IObserver;

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
            JOptionPane.showMessageDialog(view, "Não há registros de administrador cadastrado, crie agora um!", "Primeiro Acesso!", JOptionPane.INFORMATION_MESSAGE);
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
            showDialog("Você deslogou!");
        });
        this.view.getBtnNotificacoes().addActionListener((e) -> {
            this.state.buscarNotificacoes();
        });
        this.view.getMenuAlterarSenhar().addActionListener((e) -> {
            this.state.alterarSenhar();
        });
        
    }
    
    public void fecharTodasJanelas(){
        for(var frame : view.getDesktopPane().getAllFrames()) {
            frame.dispose();
        }
    }
    
    private void showDialog(String message) {
        JOptionPane.showMessageDialog(getView(), message, "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
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
        if(this.usuarioService.getUsuarioLogado() != null) {
            this.state.obterQuantidadeNotificacoes();
        }
    }
}
