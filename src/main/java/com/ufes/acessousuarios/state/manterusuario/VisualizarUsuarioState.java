package com.ufes.acessousuarios.state.manterusuario;

import com.ufes.acessousuarios.Util.DateManipulation;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterUsuarioPresenter;
import java.beans.PropertyVetoException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
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
       this.view.getBtnConfirmar().setEnabled(false);
       this.view.getBtnEditar().setEnabled(true);
       this.view.getBtnExcluir().setEnabled(true);
       this.view.getLblDataCadastro().setVisible(true);
    }
    
    @Override
    public void editar() {
        this.presenter.setState(new EditarUsuarioState(presenter, usuario));
    }
    
    private void loadCampos(){
        try {
            this.view.setSelected(this.usuario.isAdmin());
            this.view.getTxtNome().setText(this.usuario.getNome());
            this.view.getTxtLogin().setText(this.usuario.getLogin());
            this.view.getTxtSenha().setText(this.usuario.getSenha());
            this.view.getLabelCadastroData().setText(DateManipulation.localDateToString(this.usuario.getDataCriacao()));
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VisualizarUsuarioState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}