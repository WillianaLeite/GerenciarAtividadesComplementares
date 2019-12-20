/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeCRUD;

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
public class testeCursoCRUD {
    
    private Curso curso;
    
    public testeCursoCRUD() {
        curso = new Curso();
    }
    
    
    @After
    public void tearDown() throws ClassNotFoundException, SQLException {
        curso.excluir();
    }

   @Test
   public void testeInserir () throws ClassNotFoundException, SQLException {
       
       int id = 3;
       String nome = "Producao";
       int maximoHorasComplementares = 190;
   
       curso.setId(id);
       curso.setNome(nome);
       curso.setMaximoHorasComplementares(maximoHorasComplementares);
       curso.inserir();
       
       Curso retorno = new Curso();
       retorno.buscar(id);
       
       assertEquals(id, retorno.getId());
       assertEquals(nome, retorno.getNome());
       assertEquals(maximoHorasComplementares, retorno.getMaximoHorasComplementares());
       
       
   }

   @Test
   public void testeAlterar ()  throws ClassNotFoundException, SQLException {
        
       //int id = 3;
       String nome = "Producao";
       int maximoHorasComplementares = 190;
   
     //  curso.setId(4);
       curso.setNome("Software");
       curso.setMaximoHorasComplementares(288);
       
       curso.alterar();
       
       
       assertNotSame(nome, curso.getNome()); 
       assertNotSame(maximoHorasComplementares, curso.getMaximoHorasComplementares());
       
   }


   @Test 
   public void testeBuscarPorNome()  throws ClassNotFoundException, SQLException {
   
       String resultCorreto = "ES";
       
       Curso curso = new Curso();
       curso.buscarPorNome("ES");
       
       assertEquals(resultCorreto, curso.getNome());
   
   }
   
   @Test
   public void testeExcluir ()   throws ClassNotFoundException, SQLException {
   
       int id = 3;
       curso.setId(id);
       curso.excluir();
       curso.buscar(id);
       
       assertNull(curso.getNome());
       
   }

}
