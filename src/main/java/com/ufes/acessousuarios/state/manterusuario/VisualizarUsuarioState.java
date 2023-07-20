package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.Util.DateManipulation;
import com.ufes.acessousuarios.command.manterusuario.ExcluirUsuarioCommand;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import javax.swing.JOptionPane;

public class VisualizarUsuarioState extends ManterUsuarioState {

    public VisualizarUsuarioState(ManterUsuarioPresenter presenter,Usuario usuario) {
        super(presenter, usuario);
        loadCampos();
    }
    
    @Override
    protected void initComponents(){
       super.initComponents();
       this.view.setTitle("Visualizar Usuário");
       this.view.getBtnEditar().setVisible(true);
       this.view.getBtnEditar().setEnabled(true);
       this.view.getBtnCancelar().setVisible(true);
       this.view.getBtnCancelar().setEnabled(false);
       this.view.getBtnExcluir().setVisible(true);
       this.view.getBtnExcluir().setEnabled(true);
       this.view.getBtnConfirmar().setEnabled(false);
       this.view.getBtnConfirmar().setEnabled(false);
       this.view.getLblDataCadastro().setVisible(true);
       this.view.getChkAdmin().setVisible(true);
       this.view.getChkAtivo().setVisible(true);
    }
    
    @Override
    public void editar() {
        this.presenter.setState(new EditarUsuarioState(presenter, usuario));
    }

    @Override
    public void excluir() {
        new ExcluirUsuarioCommand(usuario).executar();
        JOptionPane.showMessageDialog(view, "Usuário excluído com sucesso", "Sucesso!",JOptionPane.INFORMATION_MESSAGE);       
        fechar();
    }
    
    
    
    private void loadCampos(){
        this.view.getChkAtivo().setSelected(this.usuario.isAtivo());
        this.view.getChkAdmin().setSelected(this.usuario.isAdmin());
        this.view.getTxtNome().setText(this.usuario.getNome());
        this.view.getTxtLogin().setText(this.usuario.getLogin());
        this.view.getTxtSenha().setText(this.usuario.getSenha());
        this.view.getLabelCadastroData().setText(DateManipulation.localDateToString(this.usuario.getDataCriacao()));
    }
}