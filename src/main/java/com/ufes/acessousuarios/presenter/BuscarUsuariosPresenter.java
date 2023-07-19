package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.Util.DateManipulation;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.observer.IObserver;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.manternotificacao.EnviarNotificacaoState;
import com.ufes.acessousuarios.state.manternotificacao.VisualizarNotificacaoState;
import com.ufes.acessousuarios.state.manterusuario.VisualizarUsuarioState;
import com.ufes.acessousuarios.view.BuscarUsuariosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class BuscarUsuariosPresenter implements IObserver {
    
    private final BuscarUsuariosView view;
    private final MainPresenter mainPresenter;
    private final UsuarioService usuarioService;
    
    private DefaultTableModel tableUsuario;


    public BuscarUsuariosPresenter(MainPresenter mainPresenter) {
        this.view = new BuscarUsuariosView();
        this.mainPresenter = mainPresenter;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        
        this.constructTableModel();
        this.loadUsuarios();
        registerPane();
        this.initListeners();
        selectedChangeView();
        this.view.setVisible(true);
    
    }
    
    private void registerPane() {
        this.mainPresenter.addDesktopPane(view);
    }
    
    private void constructTableModel() {
        tableUsuario = new DefaultTableModel(
                new Object[][][][]{},
                new String[]{"Código","Nome", "Data do Cadastro", "Notificações enviadas", "Notificações lidas"}
        );

        view.getjTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableUsuario .setNumRows(0);
        view.getjTable().setModel(tableUsuario);
    }
    private void loadUsuarios() {
        clearTable();
        for (Usuario usuario : usuarioService.getUsuarios()) {
            tableUsuario.addRow(new Object[]{
                usuario.getId(),
                usuario.getNome(),
                DateManipulation.localDateToString(usuario.getDataCriacao()),
                0,
                0
            });
        }
    }
    
    private void clearTable() {
        if (tableUsuario.getRowCount() > 0) {
            for (int i = tableUsuario.getRowCount() - 1; i > -1; i--) {
                tableUsuario.removeRow(i);
            }
        }
    }
            
    private boolean checkSeTemElementoSelecionado() {
        return view.getjTable().getSelectedRow() >= 0;
    }
          
    
    private void selectedChangeView() {
        if (checkSeTemElementoSelecionado()) {
            view.getBtnEnviarNotificacao().setEnabled(true);
            view.getBtnVisaulizarUsuario().setEnabled(true);
        } else {
            view.getBtnEnviarNotificacao().setEnabled(false);
            view.getBtnVisaulizarUsuario().setEnabled(false);
        }
    }

     private void initListeners() {
        view.getBtnFechar().addActionListener((e) -> {
            view.dispose();
        });
     
        view.getjTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (view.getjTable().getSelectedRow() > -1) {
                    selectedChangeView();
                }
            }
        });
    
       view.getBtnVisaulizarUsuario().addActionListener((e) -> {
           var manter = new ManterUsuarioPresenter(mainPresenter);
           try {
               if(getUsuarioSelected() != null){
                   manter.setState(new VisualizarUsuarioState(manter, getUsuarioSelected()));
               }
           } catch (Exception ex) {
               Logger.getLogger(BuscarUsuariosPresenter.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
       
       view.getBtnEnviarNotificacao().addActionListener((e) -> {
           try {
               if(getUsuarioSelected() != null){
                    new ManterNotificacaoPresenter(mainPresenter, getUsuarioSelected());
               }
           } catch (Exception ex) {
               Logger.getLogger(BuscarUsuariosPresenter.class.getName()).log(Level.SEVERE, null, ex);
           }
       });
    }
    
    private Usuario getUsuarioSelected() throws Exception {
        if(getUsuarioIdSelected() != null){
            return usuarioService.obterPorId(getUsuarioIdSelected());
        }
        return null;
    }
    
    private Long getUsuarioIdSelected() {
        int rowIndex = view.getjTable().getSelectedRow();
        return Long.valueOf(view.getjTable().getValueAt(rowIndex, 0).toString());
    }

    @Override
    public void update() {
        this.loadUsuarios();
    }
     
}
