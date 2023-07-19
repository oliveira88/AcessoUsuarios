package com.ufes.acessousuarios.service;

import com.ufes.acessousuarios.dao.INotificacaoDAO;
import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoService {
    private INotificacaoDAO notificacaoDAO;
    private List<Notificacao> notificacoes;
    private final UsuarioService usuarioService;
    
    public NotificacaoService(INotificacaoDAO dao) {
        this.notificacaoDAO = dao;
        this.usuarioService = UsuarioServiceFactory.getInstance().getService();
        this.notificacoes = new ArrayList<>();
        this.initNotificacoes();
    }
    
    private void initNotificacoes() {
        this.notificacoes = notificacaoDAO.obterTodas(usuarioService.getUsuarioLogado());
    }
    
    public List<Notificacao> obterNotificacoes() {
        return notificacoes;
    }
    
    public long criar(Notificacao notificacao) {
        return this.notificacaoDAO.criar(notificacao);
    }
    
    public void enviar(Notificacao notificacao, Usuario destinatario) {
        this.notificacaoDAO.enviar(notificacao, usuarioService.getUsuarioLogado(), destinatario);
    }
    
    public Notificacao obterPorId(long id) {
        var a = this.notificacaoDAO.obterPorId(id);
        return new Notificacao(a.getId(), a.getTitulo(), a.getMensagem());
    }
    
    public void lerNotificacao(Usuario usuario, Long idNotificacao) {
        this.notificacaoDAO.lerNotificacao(usuario, idNotificacao);
    }
}
