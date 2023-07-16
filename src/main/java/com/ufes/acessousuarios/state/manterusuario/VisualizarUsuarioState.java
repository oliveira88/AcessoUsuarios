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
        initCampos();
        initComponents();
        loadCampos();
        registerListeners();
    }
    
    @Override
    public void editar() {
        this.presenter.setState(new EditarUsuarioState(presenter,usuario));
    }
    
    
    private void initComponents(){
       this.presenter.getView().setTitle("Visualizar Usuário");
       this.presenter.getView().getBtnConfirmar().setEnabled(false);
       this.presenter.getView().getBtnEditar().setVisible(true);
       this.presenter.getView().getBtnExcluir().setVisible(true);
       this.presenter.getView().getChkAdmin().setVisible(true);
       this.presenter.getView().getLblDataCadastro().setVisible(true);
    }
    
    private void initCampos(){
      this.presenter.getView().getChkAdmin().setEnabled(false);
      this.presenter.getView().getTxtNome().setEnabled(false);
      this.presenter.getView().getTxtSenha().setEnabled(false);
      this.presenter.getView().getTxtLogin().setEnabled(false);
    }
    
    private void loadCampos(){
        try {
            this.presenter.getView().setSelected(this.usuario.isAdmin());
            this.presenter.getView().getTxtNome().setText(this.usuario.getNome());
            this.presenter.getView().getTxtLogin().setText(this.usuario.getLogin());
            this.presenter.getView().getTxtSenha().setText(this.usuario.getSenha());
            this.presenter.getView().getLabelCadastroData().setText(DateManipulation.localDateToString(this.usuario.getDataCriacao()));
            
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VisualizarUsuarioState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      private void registerListeners() {
        this.presenter.getView().getBtnEditar().addActionListener((e) -> {
            try {
                this.editar();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(presenter.getView(), "Erro para Salvar o usuário " + ex, "Erro!", JOptionPane.ERROR_MESSAGE);
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
    
   


}
