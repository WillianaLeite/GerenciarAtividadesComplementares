package DAO;

import Conexao.Conexao;
import Models.Aluno;
import Models.Atividade;
import Models.Categoria;
import Models.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class CategoriaDAO {
    
    //TESTADA!
      
    private Conexao dao = Conexao.getInstanciaDaConexao();
    private static CategoriaDAO instancia;
    
    public static CategoriaDAO getInstancia() {
        if (instancia == null) {
            instancia = new CategoriaDAO();
        }
        
        return instancia;
    }
    
    public void inserir(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `categoria`(`id`, `nome`, `limite_horas`, `id_curso`, `descricao`) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, categoria.getId());
            stmt.setString(2, categoria.getNomeCategoria());
            stmt.setInt(3, categoria.getLimiteHoras());
            stmt.setInt(4, categoria.getCurso().getId());
            stmt.setString(5, categoria.getDescricao());
            
            stmt.executeUpdate();
            
            
            categoria.setId(this.find());
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void alterar(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `categoria` SET `nome` = ?, `limite_horas` = ?, `id_curso` = ?, `descricao` = ? WHERE `id` = ?");
            stmt.setString(1, categoria.getNomeCategoria());
            stmt.setInt(2, categoria.getLimiteHoras());
            stmt.setInt(3, categoria.getCurso().getId());
            stmt.setString(4, categoria.getDescricao());
            stmt.setInt(5, categoria.getId());
            stmt.executeUpdate();
            
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void excluir(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM `CATEGORIA` WHERE `ID` = ?");
            stmt.setInt(1, categoria.getId());
            stmt.executeUpdate();
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void buscar(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `nome`, `limite_horas`, `id_curso`, `descricao` FROM `categoria` WHERE `id` = ?");
            stmt.setInt(1, categoria.getId());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                categoria.setNomeCategoria(result.getString("nome"));
                categoria.setLimiteHoras(result.getInt("limite_horas"));
                
                Curso curso = new Curso();
                curso.buscar(result.getInt("id_curso"));
                categoria.setCurso(curso);
                
                categoria.setDescricao(result.getString("descricao"));
                
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }
    
    public void buscarPorNome(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`,`nome`,`limite_horas`,`descricao`,`id_curso` FROM `categoria` WHERE `nome` = ?");
            stmt.setString(1, categoria.getNomeCategoria());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                categoria.setId(result.getInt("id"));
                categoria.setNomeCategoria(result.getString("nome"));
                categoria.setLimiteHoras(result.getInt("limite_horas"));
                Curso curso = new Curso();
                curso.buscar(result.getInt("id_curso"));
                categoria.setCurso(curso);
                categoria.setDescricao(result.getString("descricao"));
                
        
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
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'categoria' AND table_schema = 'bancogerenciamentoatividadecomplementar'");
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
        
        Curso curso = new Curso("MECANICA", 100);
        
        curso.inserir();
        
        Categoria categoria = new Categoria("Docencia", 96, curso);
        
        categoria.inserir();
    
        categoria.alterar();
        
        categoria.buscar(3);
       
        categoria.excluir();
    }
        
    */

    public ArrayList<Categoria> buscaTodos() throws SQLException, ClassNotFoundException {
        
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Categoria> categorias = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM CATEGORIA ORDER BY ID");
            result = stmt.executeQuery();
            
            while (result.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(result.getInt("id"));
                Curso curso = new Curso();
                curso.buscar(result.getInt("id_curso"));
                categoria.setCurso(curso);
                categoria.setLimiteHoras(result.getInt("limite_horas"));
                categoria.setNomeCategoria(result.getString("nome"));
                 categoria.setDescricao(result.getString("descricao"));
                categorias.add(categoria);
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
            return categorias;
        }
    }
    
}
