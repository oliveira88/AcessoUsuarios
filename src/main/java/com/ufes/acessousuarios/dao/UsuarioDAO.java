package com.ufes.acessousuarios.dao;

import com.ufes.acessousuarios.connection.SQLite;
import com.ufes.acessousuarios.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                + "data_criacao DATE NOT NULL,"
                + "data_exclusao DATE);";
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
    public Usuario login(String login, String senha) throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        var sql = "SELECT"
                + "id,"
                + "nome,"
                + "admin,"
                + "data_criacao"
                + "FROM usuario"
                + "WHERE login = ?"
                + "AND senha = ?";
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
            LocalDateTime dataCriacao = rs.getTimestamp(4).toLocalDateTime();
            return new Usuario(id, login, senha, nome, isAdmin, dataCriacao);
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
       var sql = "UPDATE USUARIO "
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
        var sql = "INSERT INTO USUARIO(nome, login, senha, admin, data_criacao)"
                + "VALUES (?, ? ,? , ?, date('now'));";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setBoolean(4, usuario.isAdmin());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
           throw new RuntimeException("Erro ao atualizar usuário.\n" + ex.getMessage());
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
                + "SET senha = ?,"
                + "SET nome = ?,"
                + "SET admin = ?,"
                + "WHERE id = ?";
       try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getNome());
            ps.setBoolean(4, usuario.isAdmin());
            ps.setLong(5, usuario.getId());
            ps.executeUpdate();
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao atualizar usuário.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps);
       }
    }

    @Override
    public Usuario obterPorId(long id) throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        var sql = "SELECT * FROM USUARIO WHERE id = ?";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            
            if(!rs.next()) {
                throw new RuntimeException("Usuário não encontrado.\n");
            }
            Long id_result = rs.getLong(1);
            String nome = rs.getString(2);
            String login = rs.getString(3);
            String senha = rs.getString(4);
            boolean isAdmin = rs.getBoolean(5);
            LocalDateTime dataCriacao = rs.getTimestamp(6).toLocalDateTime();
            return new Usuario(id_result, nome, login, senha, isAdmin, dataCriacao);
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
        var sql = "SELECT * FROM USUARIO WHERE id = ?";
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
                LocalDateTime dataCriacao = rs.getTimestamp(6).toLocalDateTime();
                usuarios.add(new Usuario(id, nome_result, login, senha, isAdmin, dataCriacao));
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
        var sql = "SELECT * FROM USUARIO";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                Long id = rs.getLong(1);
                String nome_result = rs.getString(2);
                String login = rs.getString(3);
                String senha = rs.getString(4);
                boolean isAdmin = rs.getBoolean(5);
                LocalDateTime dataCriacao = rs.getTimestamp(6).toLocalDateTime();
                usuarios.add(new Usuario(id, nome_result, login, senha, isAdmin, dataCriacao));
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
       
}