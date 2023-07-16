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
        initComponents();
        initCampos();
        registerListeners();
    }
        
    public void initComponents(){
       this.presenter.getView().setTitle("Editar Usuário");
       this.presenter.getView().getBtnConfirmar().setEnabled(true);
       this.presenter.getView().getBtnEditar().setVisible(false);
       this.presenter.getView().getBtnExcluir().setVisible(false);
       this.presenter.getView().getChkAdmin().setVisible(true);
       this.presenter.getView().getLblDataCadastro().setVisible(true);
    }
    
    private void initCampos(){
      this.presenter.getView().getChkAdmin().setEnabled(true);
      this.presenter.getView().getTxtNome().setEnabled(true);
      this.presenter.getView().getTxtSenha().setEnabled(true);
      this.presenter.getView().getTxtLogin().setEnabled(true);
    }
    
    public Usuario lerFormulario() {
        String login = this.presenter.getView().getTxtLogin().getText();
        String senha = new String(this.presenter.getView().getTxtSenha().getPassword());
        String nome = this.presenter.getView().getTxtNome().getText();
        usuario = new Usuario(usuario.getId(),login, senha, nome, true, LocalDateTime.now());
        return usuario;
    }
    
    
    public Boolean validarCampos(){
        if(this.presenter.getView().getTxtLogin().getText() == null || this.presenter.getView().getTxtLogin().getText().isBlank()){
            return false;
        }
        if(this.presenter.getView().getTxtSenha().getPassword() == null || this.presenter.getView().getTxtSenha().getPassword().length <= 0){
            return false;
        }
          if(this.presenter.getView().getTxtNome().getText() == null || this.presenter.getView().getTxtNome().getText().isBlank()){
            return false;
        }
       return true;
    }
    
    private void registerListeners() {
        this.presenter.getView().getBtnConfirmar().addActionListener((e) -> {
            try {
                this.editar();
                JOptionPane.showMessageDialog(presenter.getView(), "Usuário editar com sucesso", "Sucesso!",JOptionPane.INFORMATION_MESSAGE);   
                this.presenter.setState(new VisualizarUsuarioState(presenter, usuario));
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(presenter.getView(), "Erro para editar o usuário " + ex, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.presenter.getView().getBtnCancelar().addActionListener((e) -> {
         try {
                this.cancelar();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(presenter.getView(), "Erro para Salvar o usuário " + ex, "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    @Override
    public void editar() {
      if(!validarCampos()){
         JOptionPane.showMessageDialog(presenter.getView(), "Informe todo os campos para realizar o cadastro", "Atenção!",JOptionPane.INFORMATION_MESSAGE);   
      }
      this.usuario = this.lerFormulario();
      ValidadorSenha.validar(this.usuario.getSenha());
      command = new EditarUsuarioCommand(presenter.getUsuarioService(),this.usuario);
      this.command.executar();
    }
    
    
    
}
