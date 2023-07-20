package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.Util.ValidadorSenha;
import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.command.manterusuario.IncluirUsuarioCommand;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.main.LogadoState;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class IncluirUsuarioState extends ManterUsuarioState {
    private ICommand command;
    
    public IncluirUsuarioState(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter, usuario);
        
    }
    
    @Override
     public void initComponents(){
        super.initComponents();
        this.view.setTitle("Cadastrar Usuário");
        this.view.getTxtNome().setEnabled(true);
        this.view.getTxtSenha().setEnabled(true);
        this.view.getTxtLogin().setEnabled(true);
        this.view.getBtnConfirmar().setEnabled(true);
        this.view.getBtnFechar().setVisible(false);
        
        this.view.getBtnCancelar().setVisible(false);
        this.view.getBtnEditar().setVisible(false);
        this.view.getBtnExcluir().setVisible(false);
        this.view.getBtnFechar().setVisible(true);
        
        if(usuario != null){
            this.view.getBtnFechar().setVisible(true);
            this.view.getChkAdmin().setVisible(true);
            this.view.getChkAdmin().setEnabled(true);
        }
    }
     
    @Override
    public void salvar() {
      if(!validarCampos()){
         JOptionPane.showMessageDialog(presenter.getView(), "Informe todo os campos para realizar o cadastro", "Atenção!",JOptionPane.INFORMATION_MESSAGE);   
      }
      this.usuario = this.lerFormulario();
      ValidadorSenha.validar(this.usuario.getSenha());
      new IncluirUsuarioCommand(this.usuario, presenter.getMainPresenter()).executar();
      fechar();
    }
    
    public Usuario lerFormulario() {
        boolean isAdmin = UsuarioServiceFactory.getInstance().getService().getUsuarios().isEmpty() || this.view.getChkAdmin().isSelected();
        var usuarioLogado = UsuarioServiceFactory.getInstance().getService().getUsuarioLogado();
        String login = this.view.getTxtLogin().getText();
        String senha = new String(this.view.getTxtSenha().getPassword());
        String nome = this.view.getTxtNome().getText();
        if(usuarioLogado != null) {
            usuario = new Usuario(login, senha, nome, isAdmin, true, LocalDateTime.now());
        } else {
            usuario = new Usuario(login, senha, nome, isAdmin, isAdmin, LocalDateTime.now());
        }
        return usuario;
    }
    
    
    public Boolean validarCampos(){
        if(this.view.getTxtLogin().getText() == null || this.view.getTxtLogin().getText().isBlank()){
            return false;
        }
        if(this.view.getTxtSenha().getPassword() == null || this.view.getTxtSenha().getPassword().length <= 0){
            return false;
        }
        if(this.view.getTxtNome().getText() == null || this.view.getTxtNome().getText().isBlank()){
            return false;
        }
       return true;
    }

}
