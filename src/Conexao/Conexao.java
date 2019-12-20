
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willi
 * @objetivo: Essa classe tem o objetivo de estabelecer e gerenciar a conexão com o banco de dados
 */

public class Conexao{
    
    private static Conexao instanciaDaConexao;
    private final String DATABASE = "bancogerenciamentoatividadecomplementar";
    private final String HOST = "localhost";
    private final String DRIVE = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE + "?useTimezone=true&serverTimezone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "";
 
    
    public static Conexao getInstanciaDaConexao() {
        if (instanciaDaConexao == null) {
            instanciaDaConexao = new Conexao();
        }
        return instanciaDaConexao;
    }

    public Connection getConexao() {
        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fecharConexao(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt) {
        fecharConexao(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
        fecharConexao(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
