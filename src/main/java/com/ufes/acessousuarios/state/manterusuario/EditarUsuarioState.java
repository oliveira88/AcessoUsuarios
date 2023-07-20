package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.Util.ValidadorSenha;
import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.command.manterusuario.EditarUsuarioCommand;
import com.ufes.acessousuarios.command.manterusuario.IncluirUsuarioCommand;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class EditarUsuarioState extends ManterUsuarioState {
    private ICommand command;

    public EditarUsuarioState(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter, usuario);
        initCampos();
    }
        
    @Override
    public void initComponents(){
        super.initComponents();
        this.view.setTitle("Editar Usuário");
        this.view.getChkAdmin().setEnabled(true);
        this.view.getTxtNome().setEnabled(true);
        this.view.getTxtSenha().setEnabled(true);
        this.view.getTxtLogin().setEnabled(true);
        this.view.getBtnConfirmar().setEnabled(true);
        this.view.getBtnCancelar().setEnabled(true);
        this.view.getChkAdmin().setVisible(true);
        this.view.getLblDataCadastro().setVisible(true);
    }
    
    private void initCampos(){
      this.view.getChkAdmin().setEnabled(true);
      this.view.getTxtNome().setEnabled(true);
      this.view.getTxtSenha().setEnabled(true);
      this.view.getTxtLogin().setEnabled(true);
    }
    
    public Usuario lerFormulario() {
        String login = this.view.getTxtLogin().getText();
        String senha = new String(this.view.getTxtSenha().getPassword());
        String nome = this.view.getTxtNome().getText();
        usuario = new Usuario(usuario.getId(),login, senha, nome, true, LocalDateTime.now());
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
    
    @Override
    public void salvar() {
      if(!validarCampos()){
         JOptionPane.showMessageDialog(view, "Informe todo os campos para realizar o cadastro", "Atenção!",JOptionPane.INFORMATION_MESSAGE);
         return;
      }
      this.usuario = this.lerFormulario();
      ValidadorSenha.validar(this.usuario.getSenha());
      command = new EditarUsuarioCommand(this.usuario);
      this.command.executar();
      JOptionPane.showMessageDialog(view, "Salvo com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
      fechar();
    }
    
    
    
    @Override
    public void cancelar() {
        presenter.setState(new VisualizarUsuarioState(presenter, usuario));
    }
    
}
