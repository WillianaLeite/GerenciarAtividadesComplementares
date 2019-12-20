package testeCRUD;

import Models.Funcionario;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author willi
 */
public class testeFuncionarioCRUD {
    
    private Funcionario funcionario;
    
    
    public testeFuncionarioCRUD() {
        funcionario = new Funcionario();
    }
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        funcionario.excluir();
    }
    
    
    @Test
    public void testeInserir() throws ClassNotFoundException, SQLException {
        
        int id = 5;
        String nome = "admin";
        String contato = "088656565";
        String login = "admin";
        String senha = "123";
    
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setContato(contato);
        funcionario.setLogin(login);
        funcionario.setSenha(senha);
        funcionario.inserir();
        
        Funcionario retorno = new Funcionario();
        retorno.buscar(id);
        
        
        assertEquals(id, retorno.getId());
        assertEquals(nome, retorno.getNome());
        assertEquals(contato, retorno.getContato());
        assertEquals(login, retorno.getLogin());
        assertEquals(senha, retorno.getSenha());
        
        
    }
    
    public void testeAlterar() throws ClassNotFoundException, SQLException {
        
        int id = 5;
        String nome = "admin";
        String contato = "088656565";
        String login = "admin";
        String senha = "123";
        
    //    funcionario.setId(7);
        funcionario.setNome("Novo admin");
        funcionario.setContato("4002-8922");
        funcionario.setLogin("admin2");
        funcionario.setSenha("123456");
        
        funcionario.alterar();
        
        
      //  assertNotSame(id, funcionario.getId());
        assertNotSame(nome, funcionario.getNome()); 
        assertNotSame(contato, funcionario.getContato());
        assertNotSame(login, funcionario.getLogin());
        assertNotSame(senha, funcionario.getSenha());
        
    }
    
    public void testeExcluir () throws ClassNotFoundException, SQLException{
    
        int id = 5;
        funcionario.excluir();
        funcionario.buscar(id);
        assertNull(funcionario.getNome());
        
    }
}
