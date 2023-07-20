package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.Util.DateManipulation;
import com.ufes.acessousuarios.command.manterusuario.AlterarSenhaUsuarioCommand;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import javax.swing.JOptionPane;

public class EditarSenhaUsuarioState extends ManterUsuarioState {
    public EditarSenhaUsuarioState(ManterUsuarioPresenter presenter, Usuario usuario) {
        super(presenter, usuario);
        loadCampos();
    }

    @Override
    protected void initComponents(){
       super.initComponents();
       this.view.setTitle("Alterar senha");
       this.view.getBtnCancelar().setVisible(true);
       this.view.getBtnCancelar().setEnabled(true);
       this.view.getBtnConfirmar().setEnabled(true);
       this.view.getBtnConfirmar().setEnabled(true);
       this.view.getLblDataCadastro().setVisible(true);
       this.view.getTxtSenha().setEnabled(true);
    }
    
    private void loadCampos(){
        this.view.getChkAtivo().setSelected(this.usuario.isAtivo());
        this.view.getChkAdmin().setSelected(this.usuario.isAdmin());
        this.view.getTxtNome().setText(this.usuario.getNome());
        this.view.getTxtLogin().setText(this.usuario.getLogin());
        this.view.getTxtSenha().setText(this.usuario.getSenha());
        this.view.getLabelCadastroData().setText(DateManipulation.localDateToString(this.usuario.getDataCriacao()));
    }
    
    @Override
    public void salvar() {
        var senha = new String(this.view.getTxtSenha().getPassword());
        new AlterarSenhaUsuarioCommand(usuario, senha).executar();
        JOptionPane.showMessageDialog(view, "Senha alterada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        fechar();
    }
    
    @Override
    public void cancelar() {
        fechar();
    }

}
