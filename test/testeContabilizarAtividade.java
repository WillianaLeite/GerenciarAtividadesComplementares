/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Models.Aluno;
import Models.Atividade;
import Models.Categoria;
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
public class testeContabilizarAtividade {
    
    public testeContabilizarAtividade() {
    }
    
    @Test
    public void testeHorasAproveitadas() throws SQLException, ClassNotFoundException {
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        Categoria categoria = new Categoria();
        Atividade atividade = new Atividade();
        
        aluno.setMatricula(303030);
        categoria.setCurso(curso);
        aluno.setCurso(curso);
        curso.setMaximoHorasComplementares(288);
        aluno.setQuantHoras(100);
        categoria.setLimiteHoras(96);
        atividade.setQuantHoras(100);
        atividade.setCategoria(categoria);
        
        curso.inserir();
        aluno.inserir();
        categoria.inserir();
        atividade.inserir();
        
        int resultCorreto = 96;
        aluno.addAtividade(atividade);
        aluno.contabilizarAtividade(atividade);
        int retorno = aluno.buscarHorasAproveitadas(atividade);
        
        
        assertEquals(resultCorreto, retorno);
        
        aluno.excluir();
        curso.excluir();
        categoria.excluir();
        atividade.excluir();
    }

    @Test
    public void testeContabilizarHoras() throws ClassNotFoundException, SQLException{
        
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        Categoria categoria = new Categoria();
        Atividade atividade = new Atividade();
        
        aluno.setMatricula(303030);
        categoria.setCurso(curso);
        aluno.setCurso(curso);
        curso.setMaximoHorasComplementares(288);
        aluno.setQuantHoras(100);
        categoria.setLimiteHoras(96);
        atividade.setQuantHoras(100);
        atividade.setCategoria(categoria);
        aluno.contabilizarAtividade(atividade);
        
        int resultCorreto = 196;
        int retorno = aluno.getQuantHoras();
        
        assertEquals(resultCorreto, retorno);
        
    }
 
    @Test
    public void testeExcederLimite() throws SQLException, ClassNotFoundException{
        
        Aluno aluno = new Aluno();
        Curso curso = new Curso();
        Categoria categoria = new Categoria();
        Atividade atividade = new Atividade();
        
        aluno.setMatricula(303030);
        categoria.setCurso(curso);
        aluno.setCurso(curso);
        curso.setMaximoHorasComplementares(288);
        aluno.setQuantHoras(288);
        categoria.setLimiteHoras(96);
        atividade.setQuantHoras(100);
        atividade.setCategoria(categoria);
       
        int resultCorreto = 288;
        int retorno = aluno.getQuantHoras();
        
        
        assertEquals(resultCorreto, retorno);
   
    }
    
    
}
