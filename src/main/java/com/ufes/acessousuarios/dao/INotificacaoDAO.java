package com.ufes.acessousuarios.dao;

import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.model.Usuario;
import java.util.List;

public interface INotificacaoDAO {
//    Long criar(Notificacao notificacao) throws RuntimeException;;
    void enviar(Notificacao notificacao, Usuario remetente, Usuario destinatario) throws RuntimeException;
    Notificacao obterPorId(long id) throws RuntimeException;
    List<Notificacao> obterTodas(Usuario usuario) throws RuntimeException;
    List<Notificacao> obterEnviadas(Usuario usuario) throws RuntimeException;
    void lerNotificacao(Usuario usuario, Long idNotificacao) throws RuntimeException;
}
