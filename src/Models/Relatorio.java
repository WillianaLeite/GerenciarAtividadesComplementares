/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author willi
 */
public abstract class Relatorio {
    
    Font fTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            
    Font fCorpo = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
            
    Font fLista = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
    
    public static Document documento;
    
    public Relatorio() throws FileNotFoundException, DocumentException{
        Relatorio.documento = new Document();
        PdfWriter.getInstance(Relatorio.documento, new FileOutputStream("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Relatorios\\relatorio.pdf"));
    }
    
    public abstract void gerarRelatorio(Aluno aluno);
    
    public void cabecalhoRelatorio(Aluno aluno){
    
           try {

           // PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Relatorios\\relatorio.pdf"));

            //Relatorio.documento.open();

            Image imagemLogoUFC = Image.getInstance("C:/Users/willi/OneDrive/Documentos/NetBeansProjects/GerenciarAtividadeComplementar-master/src/Imagem/a.png");

            imagemLogoUFC.setAlignment(Element.ALIGN_CENTER);

            documento.add(imagemLogoUFC);

            Paragraph titulo = new Paragraph("RELATÓRIO - ATIVIDADES COMPLEMENTARES", this.fTitulo);

            titulo.setAlignment(Element.ALIGN_CENTER);
            
            titulo.setSpacingAfter((float) 20.00);

            documento.add(titulo);

            Paragraph matricula = new Paragraph("Matricula: ", this.fCorpo);

            matricula.add("" + aluno.getMatricula());

            matricula.setAlignment(Element.ALIGN_LEFT);

            matricula.setSpacingAfter((float) 10.00);

            documento.add(matricula);

            Paragraph nome = new Paragraph("Nome: ", fCorpo);

            nome.add("" + aluno.getNome());

            nome.setAlignment(Element.ALIGN_LEFT);

            nome.setSpacingAfter((float) 10.00);

            documento.add(nome);
            
            Paragraph curso = new Paragraph("Curso: ", fCorpo);
            
            curso.add("" + aluno.getCurso().getNome());
            
            curso.setAlignment(Element.ALIGN_LEFT);
            
            curso.setSpacingAfter((float)10.00);
            
            documento.add(curso);
            
            //documento.close();
           
        }catch (DocumentException de) {
           
            System.err.println(de.getMessage());
        
        } catch (IOException ioe) {
        
            System.err.println(ioe.getMessage());
        
        }
    
    
    }
 
    


    // NA HORA DE INSTANCIAR : templateRelatorio tp = new (relatorio geral ou especifico);
    // ai eh so chamar tp.gerarRelatorio();
    
}
