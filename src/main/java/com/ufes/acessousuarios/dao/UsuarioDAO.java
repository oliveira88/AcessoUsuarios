package com.ufes.acessousuarios.dao;

import com.ufes.acessousuarios.Util.AcessoException;
import com.ufes.acessousuarios.connection.SQLite;
import com.ufes.acessousuarios.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {

    public UsuarioDAO() {
        criarTabelaUsuario();
    }
    
    private void criarTabelaUsuario(){
        Connection con = null;
        Statement st = null;
        var sql = "CREATE TABLE IF NOT EXISTS Usuario("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome VARCHAR(100) NOT NULL,"
                + "login VARCHAR(50) NOT NULL UNIQUE,"
                + "senha VARCHAR(100) NOT NULL,"
                + "admin BOOLEAN DEFAULT false NOT NULL,"
                + "ativo BOOLEAN DEFAULT false NOT NULL,"
                + "data_criacao DATE NOT NULL);";
        try {
            con = SQLite.getConnection();
            st = con.createStatement();
            st.execute(sql);
            st.close();
        } catch (SQLException ex) {
             throw new RuntimeException("Erro na criação da tabela de Usuario.\n"+ ex.getMessage());
        } finally {
           SQLite.closeConnection(con);
       }
        
    }

    @Override
    public Usuario login(String login, String senha) throws RuntimeException, AcessoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        var sql = "SELECT id, nome, admin, ativo, data_criacao FROM Usuario "
                + "WHERE login = ? "
                + "AND senha = ?;";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            if (!rs.next()) {
               throw new RuntimeException("Usuário não encontrado");
            }
            long id = rs.getLong(1);
            String nome = rs.getString(2);
            boolean isAdmin = rs.getBoolean(3);
            boolean isAtivo = rs.getBoolean(4);
            if(!isAtivo) {
                throw new AcessoException("Aguardando aprovação dos administradores");
            }
            LocalDateTime dataCriacao = rs.getTimestamp(5).toLocalDateTime();
            return new Usuario(id, login, senha, nome, isAdmin, isAtivo, dataCriacao);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar usuário.\n"+ ex.getMessage());
        } finally {
            SQLite.closeConnection(con, ps, rs);
        }
    } 

    @Override
    public void atualizarSenha(Usuario usuario) throws RuntimeException {
       Connection con = null;
       PreparedStatement ps = null;
       var sql = "UPDATE Usuario "
                   + "SET senha = ?"
                   + "WHERE id = ?";
       try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getSenha());
            ps.setLong(2, usuario.getId());
            ps.executeUpdate();
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao atualizar usuário.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps);
       }
    }

    @Override
    public void criar(Usuario usuario) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        var sql = "INSERT INTO Usuario(nome, login, senha, admin, ativo, data_criacao)"
                + "VALUES (?, ? ,? , ?, ?, ?);";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setBoolean(4, usuario.isAdmin());
            ps.setBoolean(5, usuario.isAtivo());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
           throw new RuntimeException("Erro ao criar usuário.\n" + ex.getMessage());
        } finally {
            SQLite.closeConnection(con, ps);
        }
    }

    @Override
    public void deletar(long id) throws RuntimeException {
       Connection con = null;
       PreparedStatement ps = null;
       var sql = "DELETE FROM Usuario WHERE id = ?;";
       try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao excluir usuário.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps);
       }
    }

    @Override
    public void atualizar(Usuario usuario) throws RuntimeException {
       Connection con = null;
       PreparedStatement ps = null;
        var sql = "UPDATE Usuario "
                + "SET login = ?,"
                + " senha = ?,"
                + " nome = ?,"
                + " admin = ?,"
                + " ativo = ? "
                + "WHERE id = ?";
       try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getNome());
            ps.setBoolean(4, usuario.isAdmin());
            ps.setBoolean(5, usuario.isAtivo());
            ps.setLong(6, usuario.getId());
            ps.executeUpdate();
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao atualizar usuário.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps);
       }
    }

    @Override
    public Usuario obterPorId(Long id) throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        var sql = "SELECT * FROM Usuario WHERE id = ?";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            
            if(!rs.next()) {
                throw new RuntimeException("Usuário não encontrado.\n");
            }
            Long id_result = rs.getLong(1);
            String nome = rs.getString(3);
            String login = rs.getString(4);
            String senha = rs.getString(2);
            boolean isAdmin = rs.getBoolean(5);
            boolean isAtivo = rs.getBoolean(6);
            LocalDateTime dataCriacao = rs.getTimestamp(7).toLocalDateTime();
            return new Usuario(id_result, nome, login, senha, isAdmin, isAtivo, dataCriacao);
       } catch (SQLException ex) {
            throw new RuntimeException("Erro ao obter usuário.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps, rs);
       }
    }

    @Override
    public List<Usuario> obterPorNome(String nome) throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        var sql = "SELECT * FROM Usuario WHERE id = ?";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();
            
            if(!rs.next()) {
                throw new RuntimeException("Usuário não encontrado.\n");
            }
            do{
                Long id = rs.getLong(1);
                String nome_result = rs.getString(2);
                String login = rs.getString(3);
                String senha = rs.getString(4);
                boolean isAdmin = rs.getBoolean(5);
                boolean isAtivo = rs.getBoolean(6);
                LocalDateTime dataCriacao = rs.getTimestamp(7).toLocalDateTime();
                usuarios.add(new Usuario(id, nome_result, login, senha, isAdmin, isAtivo, dataCriacao));
            } while(rs.next());
            
            return usuarios;
       } catch (SQLException ex) {
            throw new RuntimeException("Erro ao obter usuário.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps, rs);
       }        
    }

    @Override
    public List<Usuario> obterTodos() throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        var sql = "SELECT * FROM Usuario";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Long id = rs.getLong(1);
                String nome_result = rs.getString(3);
                String login = rs.getString(4);
                String senha = rs.getString(2);
                boolean isAdmin = rs.getBoolean(5);
                boolean isAtivo = rs.getBoolean(6);
                LocalDateTime dataCriacao = rs.getTimestamp(7).toLocalDateTime();
                usuarios.add(new Usuario(id, nome_result, login, senha, isAdmin, isAtivo, dataCriacao));
            }
            
            return usuarios;
       } catch (SQLException ex) {
            throw new RuntimeException("Erro ao obter usuários.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps, rs);
       } 
    }

    @Override
    public void autorizarUsuario(long id) throws RuntimeException {
       Connection con = null;
       PreparedStatement ps = null;
        var sql = "UPDATE Usuario "
                + "SET ativo = 1 WHERE id = ?";
       try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao autorizar usuário.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps);
       }
    }
    
    @Override
    public void recusarUsuario(long id) throws RuntimeException {
        Connection con = null;
       PreparedStatement ps = null;
        var sql = "UPDATE Usuario "
                + "SET ativo = 0 WHERE id = ?";
       try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao recusar usuário.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps);
       }
    }
    
    
}
