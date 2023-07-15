package com.ufes.acessousuarios.connection;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
    public static Connection getConnection() {
        Connection conn = null;
        try {
             Dotenv env = Dotenv.configure()
                    .directory("./")
                    .filename(".env")
                    .load();
            return DriverManager.getConnection(env.get("URL"));
        } catch (SQLException ex) {
            throw new RuntimeException("Falha ao conectar com banco de dados", ex.getCause());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Falha ao fechar conexao", ex.getCause());
        }
    }
    
    public static void closeConnection(Connection connection, Statement statement) {
        closeConnection(connection);
        try {
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Falha ao fechar conexao", ex.getCause());
        }
    }
    
    public static void closeConnection(Connection con, Statement statement, ResultSet resultSet) {
        closeConnection(con, statement);
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Falha ao fechar conexao", ex.getCause());
        }
    }
}
