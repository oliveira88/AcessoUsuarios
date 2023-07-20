package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.observer.IObserver;
import com.ufes.acessousuarios.service.NotificacaoService;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.state.manternotificacao.ManterNotificacaoState;
import com.ufes.acessousuarios.state.manternotificacao.VisualizarNotificacaoState;
import com.ufes.acessousuarios.view.BuscarNotificacoesView;
import java.awt.event.MouseAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BuscarNotificacoesPresenter implements IObserver  {
    private final BuscarNotificacoesView view;
    private final MainPresenter mainPresenter;
    private final UsuarioService usuarioService;
    private final NotificacaoService notificacaoService;
    private final DefaultTableModel tableNotificacao;

    public BuscarNotificacoesPresenter(MainPresenter mainPresenter) {
        this.view = new BuscarNotificacoesView();
        tableNotificacao = (DefaultTableModel) view.getTabelaNotificacao().getModel();
        this.mainPresenter = mainPresenter;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        this.notificacaoService = NotificacaoServiceFactory.getInstance().getService();
        this.notificacaoService.addObserver(this);
        loadNotificacoes();
        registerPane();
        configMenus();
        selectedChangeView();
        this.view.setVisible(true);
    }
    
    private void registerPane() {
        this.mainPresenter.addDesktopPane(view);
    }
    
    private void loadNotificacoes() {
        this.clearTable();
            for(var notificacao : notificacaoService.getNotificacoes()) {
                var remetente = usuarioService.getUsuario(notificacao.getRemetenteId());
                tableNotificacao.addRow(new Object[] {
                    notificacao.getTitulo(),
                    remetente.getNome(),
                    notificacao.getVisualizada()
                });
            }
        
    }
    
    private void clearTable() {
        if (tableNotificacao.getRowCount() > 0) {
            for (int i = tableNotificacao.getRowCount() - 1; i > -1; i--) {
                tableNotificacao.removeRow(i);
            }
        }
    }
    
    private void configMenus() {
        this.view.getBtnFechar().addActionListener((e) -> {
            view.dispose();
        });
       
        this.view.getBtnVisualizar().addActionListener((e) -> {
            try {
                var notificacao = getNotificacaoSelected();
               if(notificacao != null){
                    var manterNotificacao = new ManterNotificacaoPresenter(mainPresenter, notificacao);
                    manterNotificacao.getState().visualizar();
               }
           } catch (Exception ex) {
               Logger.getLogger(BuscarUsuariosPresenter.class.getName()).log(Level.SEVERE, null, ex);
           }
        });
        
         view.getTabelaNotificacao().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (view.getTabelaNotificacao().getSelectedRow() > -1) {
                    selectedChangeView();
                }
            }
        });
    }
    
    private Notificacao getNotificacaoSelected() throws Exception {
        int rowIndex = view.getTabelaNotificacao().getSelectedRow();
        return notificacaoService.obterNotificacoes().get(rowIndex);
    }
    
     private boolean checkSeTemElementoSelecionado() {
        return view.getTabelaNotificacao().getSelectedRow() >= 0;
    }
          
    
    private void selectedChangeView() {
        if (checkSeTemElementoSelecionado()) {
            view.getBtnVisualizar().setEnabled(true);
        } else {
           view.getBtnVisualizar().setEnabled(false);
        }
    }


    @Override
    public void update() {
        this.loadNotificacoes();
    }
}
