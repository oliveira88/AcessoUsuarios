package com.ufes.acessousuarios.dao;

import com.ufes.acessousuarios.Util.AcessoException;
import com.ufes.acessousuarios.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDAO {
    Usuario login(String login, String senha) throws RuntimeException, AcessoException;
    void atualizarSenha(Usuario usuario) throws RuntimeException;
    void criar(Usuario usuario) throws SQLException;
    void deletar(long id) throws RuntimeException;
    void atualizar(Usuario usuario) throws RuntimeException;
    Usuario obterPorId(Long id) throws RuntimeException;
    List<Usuario> obterPorNome(String nome) throws RuntimeException;
    List<Usuario> obterTodos() throws RuntimeException;  
    void autorizarUsuario(long id) throws RuntimeException;
    void recusarUsuario(long id) throws RuntimeException;
}
