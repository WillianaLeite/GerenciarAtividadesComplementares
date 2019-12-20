package Models;

import DAO.FuncionarioDAO;
import java.sql.SQLException;

/**
 *
 * @author willi
 */
public class Funcionario implements InterfaceManter{
  
    private int id;
    private String nome;
    private String contato;
    private String login;
    private String senha;

    public Funcionario(){
        super();
    }
    
    public Funcionario(String nome, String contato, String login, String senha){
        this.setNome(nome);
        this.setLogin(login);
        this.setContato(contato);
        this.setSenha(senha);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
     @Override
    public void inserir() throws ClassNotFoundException, SQLException {
        if(this.nome != null && this.contato != null && this.login != null && this.senha != null){
            FuncionarioDAO.getInstancia().inserir(this);
        }
    }

    @Override
    public void alterar() throws ClassNotFoundException, SQLException {
        if(this.nome != null && this.contato != null && this.login != null && this.senha != null){
            FuncionarioDAO.getInstancia().alterar(this);
        }
    }

    @Override
    public void buscar(int codigo) throws ClassNotFoundException, SQLException {
        if(codigo > 0){
            this.id = codigo;
            FuncionarioDAO.getInstancia().buscar(this);
        }
    }
    
    public void buscarPorNome(String nome) throws SQLException, ClassNotFoundException{
        if(nome != null){
            this.nome = nome;
            FuncionarioDAO.getInstancia().buscarPorNome(this);
        }
    }

    @Override
    public void excluir() throws ClassNotFoundException, SQLException {
        FuncionarioDAO.getInstancia().excluir(this);
    }
    
}
