package testeCRUD;

import Models.Aluno;
import Models.Curso;
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
public class testeAlunoCRUD {

    private Aluno aluno;

    public testeAlunoCRUD() {
        aluno = new Aluno();
    }

    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {

        aluno.excluir();

    }

    @Test
    public void testeInserir() throws ClassNotFoundException, SQLException {

        int matricula = 112233;
        String nome = "Ana";
        String advertencia = "não tem advertencia";
        int quantHoras = 0;

        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        Curso aux = new Curso("CIVIL", 192);
        aux.inserir();
        aluno.setCurso(aux);
        aluno.setAdvertencia(advertencia);
        aluno.setSituacao(true);
        aluno.setQuantHoras(quantHoras);
        aluno.inserir();

        Aluno retorno = new Aluno();
        retorno.buscar(matricula);

        assertEquals(matricula, retorno.getMatricula());
        assertEquals(nome, retorno.getNome());
        assertEquals(advertencia, retorno.getAdvertencia());
        assertEquals(true, retorno.isSituacao());
        assertEquals(quantHoras, retorno.getQuantHoras());

    }

    @Test 
    public void testeAlterar() throws ClassNotFoundException, SQLException {
        
        int matricula = 112233;
        String nome = "Ana";
        String advertencia = "não tem advertencia";
        int quantHoras = 0;
        
        aluno.setMatricula(332211);
        aluno.setNome("williana");
        Curso curso2 = new Curso("CC", 288);
        curso2.inserir();
        aluno.setCurso(curso2);
        aluno.setAdvertencia("tem sim");
        aluno.setSituacao(false);
        aluno.setQuantHoras(100);
        
        aluno.alterar();
        
        assertNotSame(matricula, aluno.getMatricula());
        assertNotSame(nome, aluno.getNome()); 
        assertNotSame(advertencia, aluno.getAdvertencia());
        assertNotSame(quantHoras, aluno.getQuantHoras());
        
    }
    
    @Test
    public void testeExcluir() throws ClassNotFoundException, SQLException{
            
        int matricula = 112233;
        aluno.excluir();
        aluno.buscar(matricula);
        assertNull(aluno.getNome());
       
    }

}
