package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.observer.IObserverLogin;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.main.AdministradorState;
import com.ufes.acessousuarios.state.main.MainPresenterState;
import com.ufes.acessousuarios.state.main.NaoLogadoState;
import com.ufes.acessousuarios.state.manterusuario.EditarSenhaUsuarioState;
import com.ufes.acessousuarios.state.manterusuario.EditarUsuarioState;
import com.ufes.acessousuarios.state.manterusuario.IncluirUsuarioState;
import com.ufes.acessousuarios.state.manterusuario.VisualizarUsuarioState;
import com.ufes.acessousuarios.view.MainView;
import java.awt.Component;
import javax.swing.JOptionPane;

public class MainPresenter implements IObserverLogin{
    private final MainView view;
    private MainPresenterState state;
    private final UsuarioService usuarioService;
    private ManterUsuarioPresenter manterPresenter; 
    private Usuario usuario;
    //TODO: Lembrar de instanicar as daos nas sua servoce
    
    public MainPresenter() {
        this.view = new MainView();
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        this.configMenus();
        this.setState(new NaoLogadoState(this));
        
        this.initComponente();
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
              new BuscarUsuariosPresenter(this);
        });
        this.view.getMenuCadastrar().addActionListener((e) -> {
            manterPresenter = new ManterUsuarioPresenter(this);
             manterPresenter.setState(new IncluirUsuarioState(manterPresenter, usuario));
        });
        this.view.getMenuLog().addActionListener((e) -> {
            showDialog("Abrir log");
        });
    }
    
    private void showDialog(String message) {
        JOptionPane.showMessageDialog(getView(), message, "Primeiro Acesso!", JOptionPane.INFORMATION_MESSAGE);
    }
        
    private void initComponente(){
        if(this.usuarioService.getUsuarios().isEmpty()) {
            manterPresenter = new ManterUsuarioPresenter(this);
            manterPresenter.setState(new IncluirUsuarioState(manterPresenter, usuario));
        } else {
            new LoginPresenter(this);
        }
    }
    
    @Override
    public void update(Usuario usuario) {
        if(usuario.isAdmin()){
            this.setState(new AdministradorState(this));
        }
    }
 

}
