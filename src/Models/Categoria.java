package Models;

import DAO.CategoriaDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author willi
 * @modificado Beatriz Oliveira
 */
public class Categoria implements InterfaceManter{

    private int id;
    private String nome;
    private String descricao;
    private int limiteHoras;
    private Curso curso;
    
    public Categoria(){
        super();
    }
    
    public Categoria(String nome, int limiteHoras, Curso curso){
        this.setNomeCategoria(nome);
        this.setLimiteHoras(limiteHoras);
        this.setCurso(curso);
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }

    public String getNomeCategoria() {
        return nome;
    }

    public void setNomeCategoria(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLimiteHoras() {
        return limiteHoras;
    }

    public void setLimiteHoras(int limiteHoras) {
        this.limiteHoras = limiteHoras;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
  
    @Override
    public void inserir() throws ClassNotFoundException, SQLException {
        if(this.limiteHoras > 0 && this.nome != null && this.curso != null){
            if (this.id == 0) {
                CategoriaDAO.getInstancia().inserir(this);
            } else {
                this.alterar();
            }
        }
    }

    @Override
    public void alterar() throws ClassNotFoundException, SQLException {
        if(this.limiteHoras > 0 && this.nome != null && this.curso != null){
            CategoriaDAO.getInstancia().alterar(this);
        }
    }

    @Override
    public void buscar(int codigo) throws ClassNotFoundException, SQLException {
        if(codigo > 0){
            this.id = codigo;
            CategoriaDAO.getInstancia().buscar(this);
        }
    }
    
    public void buscarPorNome(String nome)throws ClassNotFoundException, SQLException{
        if(nome != null){
            this.nome = nome;
            CategoriaDAO.getInstancia().buscarPorNome(this);
        }
    }

    @Override
    public void excluir() throws ClassNotFoundException, SQLException {
        CategoriaDAO.getInstancia().excluir(this);
    }

    
    public ArrayList<Categoria> buscarTodasCategorias() throws SQLException, ClassNotFoundException{
        
        return CategoriaDAO.getInstancia().buscaTodos();
    
    } 
    
}
