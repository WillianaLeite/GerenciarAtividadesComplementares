package DAO;

import Conexao.Conexao;
import Models.Aluno;
import Models.Atividade;
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
public class FuncionarioDAO {
    
    //TESTADA!
   
    private Conexao dao = Conexao.getInstanciaDaConexao();
    private static FuncionarioDAO instancia;
    
    public static FuncionarioDAO getInstancia() {
        if (instancia == null) {
            instancia = new FuncionarioDAO();
        }
        
        return instancia;
    }
    
    public void inserir(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `funcionario`(`id`, `nome`, `contato`, `login`, `senha`) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getContato());
            stmt.setString(4, funcionario.getLogin());
            stmt.setString(5, funcionario.getSenha());
           
            stmt.executeUpdate();
            
            funcionario.setId(this.find());
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void alterar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `funcionario` SET `nome` = ?,`contato` = ?,`login` = ?, `senha` = ? WHERE `id` = ?");
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getContato());
            stmt.setString(3, funcionario.getLogin());
            stmt.setString(4, funcionario.getSenha());
            stmt.setInt(5, funcionario.getId());
           
            stmt.executeUpdate();
            
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void excluir(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM `FUNCIONARIO` WHERE `ID` = ?");
            stmt.setInt(1, funcionario.getId());
            stmt.executeUpdate();
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void buscar(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `nome`, `contato`, `login`, `senha` FROM `funcionario` WHERE `id` = ?");
            stmt.setInt(1, funcionario.getId());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                funcionario.setNome(result.getString("nome"));
                funcionario.setContato(result.getString("contato"));
                funcionario.setLogin(result.getString("login"));
                funcionario.setSenha(result.getString("senha"));
        
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }
    
    
    public void buscarPorNome(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`,`nome`,`contato`, `login`, `senha` FROM `funcionario` WHERE `nome` = ?");
            stmt.setString(1, funcionario.getNome());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                funcionario.setId(result.getInt("id"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setContato(result.getString("contato"));
                funcionario.setLogin(result.getString("login"));
                funcionario.setLogin(result.getString("senha"));
             
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }
    
    
    public ArrayList<Funcionario> buscaTodos() throws SQLException, ClassNotFoundException {
        
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM FUNCIONARIO ORDER BY ID");
            result = stmt.executeQuery();
            
            while (result.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(result.getInt("id"));
                funcionario.setContato(result.getString("contato"));
                funcionario.setLogin(result.getString("login"));
                funcionario.setSenha(result.getString("senha"));
                funcionario.setNome(result.getString("nome"));
                funcionarios.add(funcionario);
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
            return funcionarios;
        }
    }
   
    public void validaFuncionario(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`, `nome`, `contato`, `login`, `senha` FROM `funcionario` WHERE"
                    + " `login` = ? AND `senha` = ? ");
            stmt.setString(1, funcionario.getLogin());
            stmt.setString(2, funcionario.getSenha());
            result = stmt.executeQuery();

            while (result.next()) {
                funcionario.setId(result.getInt("id"));
                funcionario.setContato(result.getString("contato"));
                funcionario.setLogin(result.getString("login"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setSenha(result.getString("senha"));
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }

    
    
    private int find() throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int resultado = 0;
        
        try {
            //AJEITAR NOME DO BANCO
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'funcionario' AND table_schema = 'bancogerenciamentoatividadecomplementar'");
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
    * TESTE 
    */
    
    
   /* public static void main(String args[]) throws ClassNotFoundException, SQLException{
    
        Funcionario funcionario = new Funcionario("euDeNovo", "3522-4902", "123", "456");
    
        funcionario.inserir();
        
        funcionario.alterar();

        funcionario.buscar(1);
      
        funcionario.excluir();
        
    }
    */
    
}
