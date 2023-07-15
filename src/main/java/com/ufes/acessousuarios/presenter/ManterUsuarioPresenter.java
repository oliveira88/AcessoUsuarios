package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.state.manterusuario.IncluirUsuarioState;
import com.ufes.acessousuarios.state.manterusuario.ManterUsuarioState;
import com.ufes.acessousuarios.state.manterusuario.VisualizarUsuarioState;
import com.ufes.acessousuarios.view.ManterUsuarioView;
import java.awt.HeadlessException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class ManterUsuarioPresenter {
    private final ManterUsuarioView view;
    private final MainPresenter mainPresenter;
    private final UsuarioService usuarioService;
    private Usuario usuario;
    private ManterUsuarioState state;
    
    public ManterUsuarioPresenter(MainPresenter mainPresenter, Usuario usuario) {
        this.view = new ManterUsuarioView();
        this.mainPresenter = mainPresenter;
        this.usuarioService = UsuarioService.getInstancia();
        this.usuario = usuario;
        
        this.registerListeners();
        this.registerPane();
        
        this.setState(new VisualizarUsuarioState(this));
        this.view.setVisible(true);
    }
    
    public ManterUsuarioPresenter(MainPresenter mainPresenter) {
        this.view = new ManterUsuarioView();
        this.mainPresenter = mainPresenter;
        this.usuarioService = UsuarioService.getInstancia();
        
        this.registerListeners();
        this.registerPane();
        this.setState(new IncluirUsuarioState(this));
        this.view.setVisible(true);
    }

    private void registerListeners() {
        this.view.getBtnExcluir().addActionListener((e) -> {
            try {
                this.state.excluir();
                JOptionPane.showMessageDialog(view, "Usuário excluído com sucesso", "Sucesso!",JOptionPane.INFORMATION_MESSAGE);   
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao excluir o usuário " + ex, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.view.getBtnConfirmar().addActionListener((e) -> {
            try {
                this.state.salvar();
                JOptionPane.showMessageDialog(view, "Usuário cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar o usuário " + ex, "Erro!", JOptionPane.ERROR_MESSAGE);

            }
        });
        
         this.view.getBtnCancelar().addActionListener((e) -> {
            this.state.cancelar();
        });
        this.view.getBtnEditar().addActionListener((e) -> {
            this.state.editar();
        });
        
    }
    private void registerPane() {
        this.mainPresenter.addDesktopPane(view);
    }
    
    public Usuario lerFormulario() {
        String login = this.view.getTxtLogin().getText();
        String senha = new String(this.view.getTxtSenha().getPassword());
        String nome = this.view.getTxtNome().getText();
        usuario = new Usuario(login, senha, nome, true, LocalDateTime.now());
        return usuario;
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
    
}
