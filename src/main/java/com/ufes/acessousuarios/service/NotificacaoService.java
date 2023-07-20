package com.ufes.acessousuarios.service;

import com.ufes.acessousuarios.dao.INotificacaoDAO;
import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.model.Usuario;
import com.ufes.acessousuarios.observer.Observable;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoService extends Observable {
    private INotificacaoDAO notificacaoDAO;
    private List<Notificacao> notificacoes;
    private final UsuarioService usuarioService;
    
    public NotificacaoService(INotificacaoDAO dao) {
        this.notificacaoDAO = dao;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        this.notificacoes = new ArrayList<>();
        this.obterNotificacoes();
    }
    
  
    public List<Notificacao> obterNotificacoes() {
        this.notificacoes = notificacaoDAO.obterTodas(usuarioService.getUsuarioLogado());
        notifyObservers();
        return this.notificacoes;
    }
    
    public List<Notificacao> obterNotificacoesEnviadas(Usuario usuario) {
        return notificacaoDAO.obterEnviadas(usuario);
    }

    public List<Notificacao> obterNotificacoesPorUsuario(Usuario usuario) {
        return notificacaoDAO.obterTodas(usuario);
    }
    
    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }
    
    public void enviar(Notificacao notificacao, Usuario destinatario) {
        this.notificacaoDAO.enviar(notificacao, usuarioService.getUsuarioLogado(), destinatario);
        obterNotificacoes();
    }
    
    public Notificacao obterPorId(long id) {
        return this.notificacaoDAO.obterPorId(id);
    }
    
    public void lerNotificacao(Usuario usuario, Long idNotificacao) {
        this.notificacaoDAO.lerNotificacao(usuario, idNotificacao);
        obterNotificacoes();
    }
}
