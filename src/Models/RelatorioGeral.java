/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.RomanList;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willi
 */
public class RelatorioGeral extends Relatorio{

    public RelatorioGeral() throws FileNotFoundException, DocumentException{
        super();
        //PdfWriter.getInstance(Relatorio.documento, new FileOutputStream("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Relatorios\\relatorio.pdf"));
        Relatorio.documento.open();
// Relatorio.documento.open();
    }
    
    
    @Override
    public void gerarRelatorio(Aluno aluno) {
        try {
            cabecalhoRelatorio(aluno);
            
            //Relatorio.documento.open();
            
            
            
            //Relatorio.documento.open();
            
            Paragraph categoria = new Paragraph("Categorias: ", this.fCorpo);
            
            documento.add(categoria);
            
            List listaCategorias = new RomanList();
            
            ListItem categoria1 = new ListItem("Atividades de iniciação à docência", this.fLista);
            
            listaCategorias.add(categoria1);
            
            ListItem categoria2 = new ListItem("Atividades de iniciação à pesquisa", this.fLista);
            
            listaCategorias.add(categoria2);
            
            ListItem categoria3 = new ListItem("Atividades de extensão", this.fLista);
            
            listaCategorias.add(categoria3);
            
            ListItem categoria4 = new ListItem("Atividades artístico-culturais e esportivas", this.fLista);
            
            listaCategorias.add(categoria4);
            
            ListItem categoria5 = new ListItem("Atividades de participação e/ou organização de eventos", this.fLista);
            
            listaCategorias.add(categoria5);
            
            ListItem categoria6 = new ListItem("Experiências ligadas à formação profissional e/ou correlatas", this.fLista);
            
            listaCategorias.add(categoria6);
            
            ListItem categoria7 = new ListItem("Produção Técnica e/ou Científica", this.fLista);
            
            listaCategorias.add(categoria7);
            
            ListItem categoria8 = new ListItem("Vivências de gestão", this.fLista);
            
            listaCategorias.add(categoria8);
            
            ListItem categoria9 = new ListItem("Outras atividades, estabelecidas de acordo com o Art. 3º. dessa Resolução", this.fLista);
        
            listaCategorias.add(categoria9);
                      
            documento.add(listaCategorias);
            
            
            
            //AGORA É A TABELA!
            
            PdfPTable tableAtividades;
            PdfPCell cell1 = new PdfPCell(new Paragraph("Atividade"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Categoria"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Limite"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Total Aproveitado"));
            cell1.setColspan(1);
            float[] widths = {0.1f, 0.1f, 0.05f, 0.75f};
                                widths[0] = 10f;
                                widths[1] = 10f;
                                widths[2] = 10f;
                                widths[3] = 10f;
                                tableAtividades = new PdfPTable(4);
                                tableAtividades.setWidths(widths);
            cell2.setColspan(1);
            cell3.setColspan(1);
            cell4.setColspan(1);
            cell1.setRowspan(2);
            cell2.setRowspan(2);
            cell3.setRowspan(2);
            cell4.setRowspan(2);
            tableAtividades.addCell(cell1);
            tableAtividades.addCell(cell2);
            tableAtividades.addCell(cell3);
            tableAtividades.addCell(cell4);
            tableAtividades.setSpacingBefore(20);
            tableAtividades.setWidthPercentage(100);
            
            ArrayList<Atividade> atividades = aluno.buscarAtividades();
                    
            for(Atividade atividade: atividades){
                
                tableAtividades.addCell(atividade.getNomeAtividade());
                tableAtividades.addCell(atividade.getCategoria().getNomeCategoria());
                tableAtividades.addCell(atividade.getCategoria().getLimiteHoras()+"");
                //int horasAproveitadas = this.buscarHorasAproveitadas(atividade);
                //if (horasAproveitadas >= 0){//retorna -1 quando ou o aluno é null, ou a atividade passada é null
                    tableAtividades.addCell(atividade.getTotalAproveitado()+"");//horasAproveitadas+"");
                //}
                
            }
            
            
            documento.add(tableAtividades);   
            
            PdfPTable total = new PdfPTable(1);
            
            total.setSpacingBefore(10);

            total.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            total.setWidthPercentage(25);
            
            PdfPCell cellTotal = new PdfPCell(new Paragraph("Total Acumulado"));
    
            total.addCell(cellTotal);
            
            cellTotal.setColspan(1);

            total.addCell(aluno.getQuantHoras()+"");
            
            documento.add(total);
            
            Relatorio.documento.close();
            
            
            File file = new File("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Relatorios\\relatorio.pdf");

            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {

            }
            
        } catch (DocumentException ex) {
            Logger.getLogger(RelatorioGeral.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioGeral.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatorioGeral.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
