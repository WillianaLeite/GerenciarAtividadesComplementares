package Models;

import DAO.AtividadeDAO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class Atividade implements InterfaceManter{
    
    private int id;
    private String nome;
    private Categoria categoria;
    private int quantHoras;
    private int totalAproveitado;
    
    public Atividade(){
        super();
    }
    
    public Atividade(String nome, Categoria categoria, int quantHoras){
        if(categoria != null && quantHoras > 0){
            this.nome = nome;
            this.categoria = categoria;
            this.quantHoras = quantHoras;
        }
    }

    public int getTotalAproveitado() {
        return this.totalAproveitado;
    }

    public void setTotalAproveitado(int totalAproveitado) {
        this.totalAproveitado = totalAproveitado;
    }
    
    
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAtividade() {
        return this.nome;
    }

    public void setNomeAtividade(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getQuantHoras() {
        return quantHoras;
    }

    public void setQuantHoras(int quantHoras) {
        this.quantHoras = quantHoras;
    }

    
   @Override
    public void inserir() throws ClassNotFoundException, SQLException {
        if(this.categoria != null && this.nome != null && this.quantHoras > 0){
            
           if (this.id == 0) {
                AtividadeDAO.getInstancia().inserir(this);
            } else {
                this.alterar();
            }
        }
    }

    @Override
    public void alterar() throws ClassNotFoundException, SQLException {
        if(this.categoria != null && this.nome != null && this.quantHoras > 0){
            AtividadeDAO.getInstancia().alterar(this);
        }
    }

    @Override
    public void buscar(int codigo) throws ClassNotFoundException, SQLException {
        if(codigo > 0){
            this.id = codigo;
            AtividadeDAO.getInstancia().buscar(this);
        }
    }

    @Override
    public void excluir() throws ClassNotFoundException, SQLException {
        AtividadeDAO.getInstancia().excluir(this);
    }

    public void buscarPorNome(String nome) throws ClassNotFoundException, SQLException  {
        if(nome != null){
            this.nome = nome;
            AtividadeDAO.getInstancia().buscarPorNome(this);
        }
    }
    
    public ArrayList<Atividade> buscarTodos() throws SQLException, ClassNotFoundException{
        return AtividadeDAO.getInstancia().buscaTodos();
    }
}
