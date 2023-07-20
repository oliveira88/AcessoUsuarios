package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.Util.AcessoException;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.main.LogadoState;
import com.ufes.acessousuarios.view.LoginView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class LoginPresenter {
    private final LoginView view;
    private final MainPresenter mainPresenter;
    private final UsuarioService usuarioService;
    
    public LoginPresenter(MainPresenter mainPresenter) {
        this.view = new LoginView();
        this.mainPresenter = mainPresenter;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        this.registerListeners();
        this.registerPane();
        this.view.setVisible(true);
    }
    
    private void registerListeners() {
        this.view.getBtnEntrar().addActionListener((ActionEvent e) -> {
            try{
                this.entrar();
                JOptionPane.showMessageDialog(view, "Login efetuado com sucesso!");
            }catch(AcessoException ex){
                JOptionPane.showMessageDialog(view, ex.getMessage());
            } catch(Exception ex){
                JOptionPane.showMessageDialog(view, "Usuário não encontrado ou senha inválida!" + ex);
            }
        });
        this.view.getBtnCriar().addActionListener((e) -> {
            try {
                this.criar();
            } catch(Exception ex){
                JOptionPane.showMessageDialog(view, "Usuário não encontrado ou senha inválida!" + ex);
            }
        });
    }
    
    private void registerPane() {
        this.mainPresenter.addDesktopPane(view);
    }
    private void criar() {
        new ManterUsuarioPresenter(mainPresenter);
    }
    private void entrar() {
        String login = this.view.getTxtLogin().getText();
        String senha = new String(this.view.getTxtSenha().getPassword());
        var isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("-agentlib:jdwp");
//        if(isDebug) {;
//            login = "admin";
//            senha = "Admin@";
//        }
        Usuario user = usuarioService.realizarLogin(login, senha);
        usuarioService.setUsuarioLogado(user);
        mainPresenter.setState(new LogadoState(mainPresenter));
        this.fechar();
    }
    private void fechar() {
        this.view.dispose();
    }
}
