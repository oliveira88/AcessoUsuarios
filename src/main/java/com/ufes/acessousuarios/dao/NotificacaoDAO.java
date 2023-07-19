package com.ufes.acessousuarios.dao;

import com.ufes.acessousuarios.connection.SQLite;
import com.ufes.acessousuarios.model.Notificacao;
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

public class NotificacaoDAO implements INotificacaoDAO {

    public NotificacaoDAO() {
        criarTabelaNotificacao();
        criarTabelaNotificacaoUsuario();
    }
    
    private void criarTabelaNotificacao(){
        Connection con = null;
        Statement st = null;
        var sql = "CREATE TABLE IF NOT EXISTS Notificacao("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "titulo VARCHAR(200) NOT NULL,"
                + "mensagem VARCHAR(300) NOT NULL UNIQUE,"
                + "destinatario_id INTEGER NOT NULL,"                                
                + "remetente_id INTEGER NOT NULL,"
                + "aprovacao BOOLEAN DEFAULT false NOT NULL,"
                + "visualizada BOOLEAN DEFAULT false NOT NULL,"
                + "data_envio DATE NOT NULL,"
                + "data_visualizacao DATE"
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

//    @Override
//    public Long criar(Notificacao notificacao) throws RuntimeException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//      
//        var sql = ""
//                .concat("\n INSERT INTO Notificacao(")
//                .concat("\n            titulo,")
//                .concat("\n            mensagem,")
//                .concat("\n            destinatario_id,")
//                .concat("\n            remetente_id,")
//                .concat("\n            notificacao_id,")
//                .concat("\n            data_envio)")
//                .concat("\n VALUES(?,?,?,?);");
//        try {
//            con = SQLite.getConnection();
//            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, notificacao.getTitulo());
//            ps.setString(2, notificacao.getMensagem());
//            rs = ps.getGeneratedKeys();
//            
//            if(rs.next()) {
//                return rs.getLong(1);
//            } else {
//                throw new RuntimeException("Não foi possível inserir a notificação");
//            }
//        } catch (SQLException ex) {
//           throw new RuntimeException("Erro ao criar notificação.\n" + ex.getMessage());
//        } finally {
//            SQLite.closeConnection(con, ps);
//        }
//    }

    @Override
    public Notificacao obterPorId(long id) throws RuntimeException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        var sql = "SELECT id, titulo, mensagem FROM Notificacao "
                + "WHERE id = ?";
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
               throw new RuntimeException("Notificação não encontrada");
            }
            return new Notificacao(rs.getLong(1), rs.getString(2), rs.getString(3));
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
        var sql = "SELECT n.id, n.titulo, n.mensagem FROM Notificacao n "
                + "INNER JOIN Notificacao_Usuario nu ON nu.notificacao_id = n.id "
                + "WHERE nu.destinatario_id = ? ";

        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, usuario.getId());
            rs = ps.executeQuery();
            if (!rs.next()) {
               return notificacoes;
            }
            do {
                Long id = rs.getLong(1);
                String titulo = rs.getString(2);
                String mensagem = rs.getString(3);
                notificacoes.add(new Notificacao(id, titulo, mensagem));
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
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
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
                .concat("\n UPDATE Notificacao_Usuario ")
                .concat("\n SET     visualizada = 1,")
                .concat("\n         data_visualizacao = ?")
                .concat("\n WHERE notificacao_id = ?")
                .concat("\n AND destinatario_id = ?");
        
        try {
            con = SQLite.getConnection();
            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(2, idNotificacao);
            ps.setLong(3, usuario.getId());
            ps.executeUpdate();
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao ler notificação.\n" + ex.getMessage());
       } finally {
           SQLite.closeConnection(con, ps);
       }
    }
}
