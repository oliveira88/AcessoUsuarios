package com.ufes.acessousuarios.command.manternotificacao;

import com.ufes.acessousuarios.command.ICommand;
import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.presenter.ManterNotificacaoPresenter;
import com.ufes.acessousuarios.service.NotificacaoService;
import com.ufes.acessousuarios.service.NotificacaoServiceFactory;
import javax.swing.JOptionPane;

public class EnviarNotificacaoCommand implements ICommand {
    private final ManterNotificacaoPresenter presenter;
    private final NotificacaoService notificacaoService;
    private final Usuario destinatario;
    public EnviarNotificacaoCommand(ManterNotificacaoPresenter presenter, Usuario destinatario) {
        this.presenter = presenter;
        this.destinatario = destinatario;
        this.notificacaoService = NotificacaoServiceFactory.getInstance().getService();
    }

    @Override
    public void executar() {
        if(!validarCampos()) {
            JOptionPane.showMessageDialog(presenter.getView(), "Informe todo os campos enviar uma notificação", "Atenção!", JOptionPane.INFORMATION_MESSAGE);   
            return;
        }
        
        var notificacao = lerFormulario();
        this.notificacaoService.enviar(notificacao, destinatario);
        JOptionPane.showMessageDialog(
            presenter.getView(), 
            "Notificação enviada com sucesso!", 
            "Sucesso!", 
            JOptionPane.INFORMATION_MESSAGE
        );
        presenter.getState().fechar();
    }
    
     public Boolean validarCampos(){
       return !(presenter.getView().getTxtTitulo().getText() == null || 
               presenter.getView().getTxtTitulo().getText().isBlank() ||
               presenter.getView().getTxtMensagem().getText() == null ||
               presenter.getView().getTxtMensagem().getText().isBlank());
    }
     
    public Notificacao lerFormulario() {
        var titulo = presenter.getView().getTxtTitulo().getText();
        var mensagem = presenter.getView().getTxtMensagem().getText();
        return new Notificacao(titulo, mensagem);
    }

    
}
