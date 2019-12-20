package DAO;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Models.*;
import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class AlunoDAO {
    
    //TESTADA!
    
    private Conexao dao = Conexao.getInstanciaDaConexao();
    private static AlunoDAO instancia;
    
    public static AlunoDAO getInstancia() {
        if (instancia == null) {
            instancia = new AlunoDAO();
        }
        
        return instancia;
    }
    
    public void inserir(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `aluno`(`matricula`, `nome`, `id_curso`, `situacao`, `quant_horas`, `PAC`, `Advertencias`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, aluno.getCurso().getId());
            stmt.setBoolean(4, aluno.isSituacao());
            stmt.setInt(5, aluno.getQuantHoras());
            stmt.setInt(6, aluno.getPontuacaoPAC());
            stmt.setString(7, aluno.getAdvertencia());
            
            stmt.executeUpdate();
            
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void alterar(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `aluno` SET `nome` = ?,`id_curso` = ?,`situacao` = ?, `quant_horas` = ?, `PAC` = ?, `Advertencias` = ? WHERE `matricula` = ?");
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getCurso().getId());
            stmt.setBoolean(3, aluno.isSituacao());
            stmt.setInt(4, aluno.getQuantHoras());
            stmt.setInt(5, aluno.getPontuacaoPAC());
            stmt.setString(6, aluno.getAdvertencia());
            stmt.setInt(7, aluno.getMatricula());
            stmt.executeUpdate();
            
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void excluir(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM `ALUNO` WHERE `MATRICULA` = ?");
            stmt.setInt(1, aluno.getMatricula());
            stmt.executeUpdate();
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void buscar(Aluno aluno) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `nome`, `id_curso`, `situacao`, `quant_horas`,`PAC`, `Advertencias` FROM `aluno` WHERE `matricula` = ?");
            stmt.setInt(1, aluno.getMatricula());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                Curso curso = new Curso();
                curso.buscar(result.getInt("id_curso"));
                aluno.setCurso(curso);
                
                
                aluno.setAdvertencia(result.getString("Advertencias"));
                aluno.setQuantHoras(result.getInt("quant_horas"));
                aluno.setPontuacaoPAC(result.getInt("PAC"));
                aluno.setNome(result.getString("nome"));
                aluno.setSituacao(result.getBoolean("situacao"));
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }
    
    public ArrayList<Aluno> buscaTodos() throws SQLException, ClassNotFoundException {
        
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Aluno> alunos = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM ALUNO ORDER BY MATRICULA");
            result = stmt.executeQuery();
            
            while (result.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(result.getInt("matricula"));
                aluno.setNome(result.getString("nome"));
                
                Curso curso = new Curso();
                curso.buscar(result.getInt("id_curso"));
                
                aluno.setCurso(curso);
                aluno.setSituacao(result.getBoolean("situacao"));
                aluno.setQuantHoras(result.getInt("quant_horas"));
                aluno.setPontuacaoPAC(result.getInt("PAC"));
                aluno.setAdvertencia(result.getString("Advertencias"));
                alunos.add(aluno);
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
            return alunos;
        }
    }
    
    // MÉTODOS DA TABELA AUXILIAR
    
    public void inserirAlunoAtividade(Aluno aluno, Atividade atividade, int horas_Aproveitadas) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO `aluno_atividade`(`id_aluno`, `id_atividade`, `horas_Aproveitadas`) VALUES (?, ?, ?)");
            stmt.setInt(1, aluno.getMatricula());
            stmt.setInt(2, atividade.getId());
            stmt.setInt(3, horas_Aproveitadas);
            
            stmt.executeUpdate();
            aluno.addAtividade(atividade);
           atividade.setId(this.find());
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void alterarAlunoAtividade(Aluno aluno, Atividade atividade, int horas_Aproveitadas) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE `aluno_atividade` SET `id_aluno` = ?,`id_atividade` = ?, `horas_Aproveitadas` = ? WHERE `id_aluno` = ? and `id_atividade` = ?");
            
            stmt.setInt(1, aluno.getMatricula());
            stmt.setInt(2, atividade.getId());
            stmt.setInt(3, horas_Aproveitadas);
            stmt.setInt(4, aluno.getMatricula());
            stmt.setInt(5, atividade.getId());
            
            stmt.executeUpdate();
            
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public void excluirAlunoAtividade(Aluno aluno, Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM `aluno_atividade` WHERE `ID_ALUNO` = ? AND `ID_ATIVIDADE = ?`");
            stmt.setInt(1, aluno.getMatricula());
            stmt.setInt(2, atividade.getId());
            stmt.executeUpdate();
        } finally {
            Conexao.fecharConexao(conexao, stmt);
        }
    }
    
    public int buscarHorasPorCategoria(Aluno aluno, Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        int soma = 0;//soma de todas as atividades da mesma categoria da atividade passada
        try {
            stmt = conexao.prepareStatement("Select sum(aluno_atividade.horas_Aproveitadas) from `aluno_atividade` Join atividade on atividade.id = aluno_atividade.id_atividade Where `id_aluno` = ? and `id_categoria` = ?");
            stmt.setInt(1, aluno.getMatricula());
           // System.out.println(atividade.getCategoria().getId());
            stmt.setInt(2, atividade.getCategoria().getId());//FAZER UM BUSCAR DA DAO DA CATEGORIA
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                soma += result.getInt("sum(aluno_atividade.horas_Aproveitadas)");
               
            }
            return soma;
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }
    
    public ArrayList<Atividade> buscarAtividades(Aluno aluno) throws SQLException, ClassNotFoundException {
        
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        ArrayList<Atividade> atividades = new ArrayList<Atividade>();
        try {
            stmt = conexao.prepareStatement("SELECT `id_atividade`,`horas_Aproveitadas` FROM aluno_atividade WHERE id_aluno = ?");
            stmt.setInt(1, aluno.getMatricula());
            result = stmt.executeQuery();
            aluno.setAtividades(new ArrayList<Atividade>());
            while (result.next()) {
                
                Atividade atividade1 = new Atividade();
                atividade1.buscar(result.getInt("aluno_atividade.id_atividade"));
               
                atividade1.setTotalAproveitado(result.getInt("aluno_atividade.horas_Aproveitadas"));//aluno.addAtividade(atividade1));
                atividades.add(atividade1);
                
            }
            
            aluno.setAtividades(atividades);
            
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
        return atividades;

    }
    
    public void buscarAlunoAtividade(Aluno aluno, Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`, `id_aluno`, `id_atividade`, `horas_Aproveitadas` FROM `aluno_atividade` WHERE `id_aluno` = ? and `id_atividade` = ?");
            stmt.setInt(1, aluno.getMatricula());
            stmt.setInt(2, atividade.getId());
            result = stmt.executeQuery();
            
            while (result.next()) {
                
                Aluno aluno1 = new Aluno();
                aluno1.buscar(result.getInt("id_aluno"));
                aluno = aluno1;
                
                Atividade atividade1 = new Atividade();
                atividade.buscar(result.getInt("id_atividade"));
                atividade = atividade1;
                
            }
        } finally {
            Conexao.fecharConexao(conexao, stmt, result);
        }
    }
    
    
    
    
    public int buscarHorasAproveitadas(Aluno aluno, Atividade atividade) throws SQLException, ClassNotFoundException {
        Connection conexao = dao.getConexao();
        PreparedStatement stmt = null;
        ResultSet result = null;
        try {
            stmt = conexao.prepareStatement("SELECT `id`, `id_aluno`, `id_atividade`, `horas_Aproveitadas` FROM `aluno_atividade` WHERE `id_aluno` = ? and `id_atividade` = ?");
            stmt.setInt(1, aluno.getMatricula());
            stmt.setInt(2, atividade.getId());
            result = stmt.executeQuery();
            int horas = 0;
            
            while (result.next()) {
                horas = result.getInt("horas_Aproveitadas");
            }
            
            return horas;
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
            
            stmt = conexao.prepareStatement("SELECT AUTO_INCREMENT as id FROM information_schema.tables WHERE table_name = 'aluno_atividade' AND table_schema = 'bancogerenciamentoatividadecomplementar'");
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
    
 /*   public static void main(String args[]) throws ClassNotFoundException, SQLException{
    
        Curso curso = new Curso("CIVIL", 192);

        curso.inserir();
    
        System.out.println("curso: "+curso.getNome());

        Aluno aluno = new Aluno(390240, "Willi", curso, true);

        aluno.inserir();
    
        System.out.println("aluno: "+aluno.getNome());

        Categoria categoria = new Categoria("Esportes", 24, curso);

        categoria.inserir();

        System.out.println("nome categoria: "+categoria.getNome());

        System.out.println("id categoria: "+categoria.getId());

        Atividade atividade = new Atividade("volei", categoria, 24);

        atividade.inserir();

        System.out.println("nome atividade: "+atividade.getNome());

        System.out.println("id atividade: "+atividade.getId());

        System.out.println("categoria atividade: "+atividade.getCategoria().getId());
        
        aluno.inserirAlunoAtividade(atividade);
        
        aluno.buscarAlunoAtividade(atividade);
        
        System.out.println("Aluno da tabela auxiliar: "+aluno.getNome());
        
        aluno.buscarAtividades();
        
        int x = aluno.buscarHorasPorCategoria(atividade);
        
        System.out.println("Horas por atividade: "+x);
                
    }
    */
    
   /* public static void main(String args[]) throws SQLException, ClassNotFoundException{
    
        Aluno aluno = new Aluno();
        
        aluno.setMatricula(390239);
        
        ArrayList<Atividade> atividades = aluno.buscarAtividades();
        
        ArrayList<Atividade> atividadesdoget= aluno.getAtividades();

        System.out.println("O array tem tamanho: "+atividades.size());
        
        System.out.println("O array do get tem tamanho: "+atividadesdoget.size());
        
        for(int i = 0; i < atividades.size(); i++){
        
            System.out.println("a atividade não tem nome!");
            
            System.out.println(atividades.get(i).getNomeAtividade());
            
        }
        
        for(int i = 0; i < atividadesdoget.size(); i++){
        
            System.out.println("atividades do get");
            
            System.out.println(atividadesdoget.get(i).getNomeAtividade());
            
        }
        
        
        if(atividades == null){
            System.out.println("Atividades é null");
        } else{
            System.out.println("Atividades não é null");
        }
        
    }*/
    
  /*  public static void main(String args[])throws SQLException, ClassNotFoundException{
    
    
        Aluno aluno = new Aluno();
        aluno.buscar(390239);
        aluno.mostrarAtividades();
        
    }
*/
}
