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
public class AtividadeDAO {
    
    //TESTADO!
    
    private Conexao dao = Conexao.getInstanciaDaConexao();
    private static AtividadeDAO instancia;
    
    public static AtividadeDAO getInstancia() {
        if (instancia == null) {
            instancia = new AtividadeDAO();
        }
        
        return instancia;
    }
    
    public void inserir(Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `atividade`(`id`, `id_categoria`, `quant_horas`, `nome`) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, atividade.getId());
            stmt.setInt(2, atividade.getCategoria().getId());
            stmt.setInt(3, atividade.getQuantHoras());
            stmt.setString(4, atividade.getNomeAtividade());
            
            stmt.executeUpdate();
            
            atividade.setId(this.find());
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void alterar(Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `atividade` SET `id_categoria` = ?,`quant_horas` = ?,`nome` = ? WHERE `id` = ?");
            stmt.setInt(1, atividade.getCategoria().getId());
            stmt.setInt(2, atividade.getQuantHoras());
            stmt.setString(3, atividade.getNomeAtividade());
            stmt.setInt(4, atividade.getId());
            stmt.executeUpdate();
            
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void excluir(Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM `ATIVIDADE` WHERE `atividade`.`id` = ?");
            stmt.setInt(1, atividade.getId());
            stmt.executeUpdate();
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void buscar(Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id_categoria`, `quant_horas`, `nome` FROM `atividade` WHERE `id` = ?");
            stmt.setInt(1, atividade.getId());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                Categoria categoria = new Categoria();
                categoria.buscar(result.getInt("id_categoria"));
                
                atividade.setCategoria(categoria);
                atividade.setQuantHoras(result.getInt("quant_horas"));
                atividade.setNomeAtividade(result.getString("nome"));
                
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }
    
    public ArrayList<Atividade> buscaTodos() throws SQLException, ClassNotFoundException {
        
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Atividade> atividades = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM ATIVIDADE ORDER BY ID");
            result = stmt.executeQuery();
            
            while (result.next()) {
                Atividade atividade = new Atividade();
                atividade.setId(result.getInt("id"));
                Categoria categoria = new Categoria();
                categoria.buscar(result.getInt("id_categoria"));
                atividade.setCategoria(categoria);
                atividade.setQuantHoras(result.getInt("quant_horas"));
                atividade.setNomeAtividade(result.getString("nome"));
                atividades.add(atividade);
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
            return atividades;
        }
    }
    
    
    private int find() throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int resultado = 0;
        
        try {
            
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'atividade' AND table_schema = 'bancogerenciamentoatividadecomplementar'");
            result = stmt.executeQuery();
            
            while (result.next()) {
                resultado = result.getInt("id");
            }
            
        } finally {
            Conexao.fecharConexao(conexao, stmt);
            return resultado - 1;
        }
    }
    
    
    public Atividade buscarPorNome(Atividade atividade) throws SQLException,  ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`,`nome`,`quant_horas`,`id_categoria` FROM `atividade` WHERE `nome` = ?");
            stmt.setString(1, atividade.getNomeAtividade());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                atividade.setId(result.getInt("id"));
                Categoria categoria = new Categoria();
                categoria.buscar(result.getInt("id_categoria"));
                atividade.setCategoria(categoria);
                atividade.setNomeAtividade(result.getString("nome"));
                atividade.setQuantHoras(result.getInt("quant_horas"));
                
        
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
        return atividade;

    }
    
            
    }
    
    
    
    /*
    * TESTE
    */
    
/*  public static void main(String args[]) throws ClassNotFoundException, SQLException{
        
        Curso curso = new Curso("MECANICA", 100);
        
        curso.inserir();
        
        Categoria categoria = new Categoria("Entretenimento", 42, curso);
        
        categoria.inserir();
        
        Atividade atividade = new Atividade("cinema", categoria, 24);
        
        atividade.inserir();
    
        atividade.alterar();
        
        atividade.buscar(5);
        
        atividade.excluir();
        
    }
  */     

    
    
