package com.ufes.acessousuarios.dao;

import com.ufes.acessousuarios.connection.SQLite;
import com.ufes.acessousuarios.model.Notificacao;
import com.ufes.acessousuarios.model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDAO implements INotificacaoDAO {

    public NotificacaoDAO() {
        criarTabelaNotificacao();
    }
    
    private void criarTabelaNotificacao(){
        Connection con = null;
        Statement st = null;
        var sql = "CREATE TABLE IF NOT EXISTS Notificacao("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "titulo VARCHAR(200) NOT NULL,"
                + "mensagem VARCHAR(300) NOT NULL,"
                + "destinatario_id INTEGER NOT NULL,"                                
                + "remetente_id INTEGER NOT NULL,"
                + "aprovacao BOOLEAN DEFAULT false NOT NULL,"
                + "visualizada BOOLEAN DEFAULT false NOT NULL,"
                + "data_envio DATE NOT NULL,"
                + "data_visualizacao DATE DEFAULT NULL"
                + " );";
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
    public Notificacao obterPorId(long id) throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        var sql = "SELECT id, titulo, mensagem, destinatario_id, remetente_id, "
                + "aprovacao, visualizada, data_envio, data_visualizacao FROM Notificacao "
                + "WHERE id = ?";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
               throw new RuntimeException("Notificação não encontrada");
            }
            var id_retorno = rs.getLong(1);
            var titulo = rs.getString(2);
            var mensagem = rs.getString(3);
            var destinatario_id = rs.getLong(4);
            var remetente_id = rs.getLong(5);
            var aprovacao = rs.getBoolean(6);
            var visualizada = rs.getBoolean(7);
            var data_envio = rs.getDate(8).toLocalDate();
            var data_visualizacao = rs.getDate(9).toLocalDate();
            var notificacao = new Notificacao(
                        id_retorno, titulo, mensagem,
                        destinatario_id, remetente_id, aprovacao,
                        visualizada, data_envio, data_visualizacao);
            return notificacao;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar notificação.\n"+ ex.getMessage());
        } finally {
            SQLite.closeConnection(con, ps, rs);
        }
    }

    @Override
    public List<Notificacao> obterTodas(Usuario usuario) throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Notificacao> notificacoes = new ArrayList<>();
        var sql = "SELECT * FROM Notificacao "
                + "WHERE destinatario_id = ? ";

        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, usuario.getId());
            rs = ps.executeQuery();
            if (!rs.next()) {
               return notificacoes;
            }
            do {
                var id_retorno = rs.getLong(1);
                var titulo = rs.getString(2);
                var mensagem = rs.getString(3);
                var destinatario_id = rs.getLong(4);
                var remetente_id = rs.getLong(5);
                var aprovacao = rs.getBoolean(6);
                var visualizada = rs.getBoolean(7);
                var data_envio = rs.getDate(8).toLocalDate();
                LocalDate data_visualizacao = null;
                if(rs.getDate(9) != null) {
                    data_visualizacao = rs.getDate(9).toLocalDate();
                }
                notificacoes.add(
                    new Notificacao(
                        id_retorno, titulo, mensagem,
                        destinatario_id, remetente_id, aprovacao,
                        visualizada, data_envio, data_visualizacao
                    )
                );
            } while(rs.next());
            
            return notificacoes;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar notificações.\n"+ ex.getMessage());
        } finally {
            SQLite.closeConnection(con, ps, rs);
        }
    }

    @Override
    public void enviar(Notificacao notificacao, Usuario remetente, Usuario destinatario) throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;

        var sql = ""
                .concat("\n INSERT INTO Notificacao(")
                .concat("\n            titulo,")
                .concat("\n            mensagem,")
                .concat("\n            destinatario_id,")
                .concat("\n            remetente_id,")
                .concat("\n            data_envio)")
                .concat("\n VALUES(?,?,?,?,?);");
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, notificacao.getTitulo());
            ps.setString(2, notificacao.getMensagem());

            ps.setLong(3, destinatario.getId());
            ps.setLong(4, remetente.getId());
            ps.setDate(5, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            ps.close(); 
        } catch (SQLException ex) {
           throw new RuntimeException("Erro ao enviar notificação.\n" + ex.getMessage());
        } finally {
            SQLite.closeConnection(con, ps);
        }
    }

    @Override
    public void lerNotificacao(Usuario usuario, Long idNotificacao) throws RuntimeException {
       Connection con = null;
       PreparedStatement ps = null;
        var sql = ""
                .concat("\n UPDATE Notificacao ")
                .concat("\n SET     visualizada = 1,")
                .concat("\n         data_visualizacao = ?")
                .concat("\n WHERE   id = ?")
                .concat("\n AND destinatario_id = ?");
        
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setLong(2, idNotificacao);
            ps.setLong(3, usuario.getId());
            ps.executeUpdate();
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao ler notificação.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps);
       }
    }

    @Override
    public List<Notificacao> obterEnviadas(Usuario usuario) throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Notificacao> notificacoes = new ArrayList<>();
        var sql = "SELECT * FROM Notificacao "
                + "WHERE remetente_id = ? ";

        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, usuario.getId());
            rs = ps.executeQuery();
            if (!rs.next()) {
               return notificacoes;
            }
            do {
                var id_retorno = rs.getLong(1);
                var titulo = rs.getString(2);
                var mensagem = rs.getString(3);
                var destinatario_id = rs.getLong(4);
                var remetente_id = rs.getLong(5);
                var aprovacao = rs.getBoolean(6);
                var visualizada = rs.getBoolean(7);
                var data_envio = rs.getDate(8).toLocalDate();
                LocalDate data_visualizacao = null;
                if(rs.getDate(9) != null) {
                    data_visualizacao = rs.getDate(9).toLocalDate();
                }
                notificacoes.add(
                    new Notificacao(
                        id_retorno, titulo, mensagem,
                        destinatario_id, remetente_id, aprovacao,
                        visualizada, data_envio, data_visualizacao
                    )
                );
            } while(rs.next());
            
            return notificacoes;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar notificações.\n"+ ex.getMessage());
        } finally {
            SQLite.closeConnection(con, ps, rs);
        }
    }
}
