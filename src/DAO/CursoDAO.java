package DAO;

import Conexao.Conexao;
import Models.Curso;
import Models.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class CursoDAO {
    
    //TESTADA!
    
    private Conexao dao = Conexao.getInstanciaDaConexao();
    private static CursoDAO instancia;
    
    public static CursoDAO getInstancia() {
        if (instancia == null) {
            instancia = new CursoDAO();
        }
        
        return instancia;
    }
    
    public void inserir(Curso curso) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `curso`(`id`, `nome`, `quant_horas_complementares`) VALUES (?, ?, ?)");
            stmt.setInt(1, curso.getId());
            stmt.setString(2, curso.getNome());
            stmt.setInt(3, curso.getMaximoHorasComplementares());
            
           
            stmt.executeUpdate();
            
            curso.setId(this.find());
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void alterar(Curso curso) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `curso` SET `nome` = ?, `quant_horas_complementares` = ? WHERE `id` = ?");
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getMaximoHorasComplementares());
            stmt.setInt(3, curso.getId());
           
            stmt.executeUpdate();
            
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void excluir(Curso curso) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM `CURSO` WHERE `ID` = ?");
            stmt.setInt(1, curso.getId());
            stmt.executeUpdate();
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void buscar(Curso curso) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `nome`, `quant_horas_complementares` FROM `curso` WHERE `id` = ?");
            stmt.setInt(1, curso.getId());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                curso.setNome(result.getString("nome"));
                curso.setMaximoHorasComplementares(result.getInt("quant_horas_complementares"));
                
        
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }
    
    public void buscarPorNome(Curso curso) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`,`nome`,`quant_horas_complementares` FROM `curso` WHERE `nome` = ?");
            stmt.setString(1, curso.getNome());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                curso.setId(result.getInt("id"));
                curso.setNome(result.getString("nome"));
                curso.setMaximoHorasComplementares(result.getInt("quant_horas_complementares"));
                
        
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }
   
    public ArrayList<Curso> buscaTodos() throws SQLException, ClassNotFoundException {
        
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Curso> cursos = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM CURSO ORDER BY ID");
            result = stmt.executeQuery();
            
            while (result.next()) {
                Curso curso = new Curso();
                curso.setId(result.getInt("id"));
                curso.setNome(result.getString("nome"));
                curso.setMaximoHorasComplementares(result.getInt("quant_horas_complementares"));
                
                cursos.add(curso);
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
            return cursos;
        }
    }
   
    
    private int find() throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int resultado = 0;
        
        try {
            //AJEITAR NOME DO BANCO
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'curso' AND table_schema = 'bancogerenciamentoatividadecomplementar'");
            result = stmt.executeQuery();
            
            while (result.next()) {
                resultado = result.getInt("id");
            }
            
        } finally {
            Conexao.fecharConexao(conexao, stmt);
            return resultado - 1;
        }
    }
    /*
    *   TESTE
    */
    
   /* public static void main(String args[]) throws ClassNotFoundException, SQLException{
    
        Curso curso = new Curso("ES", 288);
        
        curso.inserir();
        
        curso.alterar();
        
        curso.buscar(1);
        
        curso.excluir();
        
    }*/
}
