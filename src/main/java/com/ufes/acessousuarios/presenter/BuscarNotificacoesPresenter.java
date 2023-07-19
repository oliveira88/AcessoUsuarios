package com.ufes.acessousuarios.presenter;

import com.ufes.acessousuarios.service.NotificacaoService;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;
import com.ufes.acessousuarios.service.UsuarioService;
import com.ufes.acessousuarios.service.UsuarioServiceFactory;
import com.ufes.acessousuarios.view.BuscarNotificacoesView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BuscarNotificacoesPresenter {
    private final BuscarNotificacoesView view;
    private final MainPresenter mainPresenter;
    private final UsuarioService usuarioService;
    private NotificacaoService notificacaoService;
    private final DefaultTableModel tableNotificacao;
    
    public BuscarNotificacoesPresenter(MainPresenter mainPresenter) {
        this.view = new BuscarNotificacoesView();
        tableNotificacao = (DefaultTableModel) view.getTabelaNotificacao().getModel();
        this.mainPresenter = mainPresenter;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        this.notificacaoService = NotificacaoServiceFactory.getInstance().getService();
        loadNotificacoes();
        registerPane();
        this.view.setVisible(true);
    }
    
    private void registerPane() {
        this.mainPresenter.addDesktopPane(view);
    }
    
    private void loadNotificacoes() {
        this.clearTable();
            for(var notificacao : notificacaoService.obterNotificacoes()) {
                tableNotificacao.addRow(new Object[] {
                    notificacao.getTitulo(),
                    "TODO!",
                    false
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
}
