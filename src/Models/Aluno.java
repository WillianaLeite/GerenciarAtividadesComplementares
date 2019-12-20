 package Models;

import DAO.AlunoDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.RomanList;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author willi
 */
public class Aluno extends Document implements InterfaceManter{

    private int matricula;
    private String nome;
    private Curso curso;
    private boolean situacao;//saber se está ativo ou não na faculdade
    private int quantHoras;//quantas horas acumuladas o aluno tem
    private int pontuacaoPAC;//ainda não sei o que é!
    private String advertencia;
    private ArrayList<Atividade> atividades = new ArrayList<Atividade>();//preencher fazendo uma consulta de todas as atividades que tem do mesmo aluno na tabela Debitar Hora

    
    public Aluno(){
        super();
    }
    
    public Aluno(int matricula, String nome, Curso curso, boolean situacao){
        
        this.setMatricula(matricula);
        this.setNome(nome);
        this.setCurso(curso);
        this.setSituacao(situacao);
    
    }
    
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        if (matricula > 0) {
            this.matricula = matricula;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public int getQuantHoras() throws SQLException, ClassNotFoundException {
        
        int x = 0;
        ArrayList<Atividade> atividades = this.buscarAtividades();
        for(Atividade atividadees: atividades){
            x += atividadees.getTotalAproveitado();
        }
        
        this.quantHoras = x;
        return this.quantHoras;
    }

    public void setQuantHoras(int quantHoras) {
        this.quantHoras += quantHoras;
    }

    public int getPontuacaoPAC() {
        return pontuacaoPAC;
    }

    public void setPontuacaoPAC(int pontuacaoPAC) {
        this.pontuacaoPAC = pontuacaoPAC;
    }

    public String getAdvertencia() {
        return advertencia;
    }

    public void setAdvertencia(String advertencia) {
        this.advertencia = advertencia;
    }

    public void setAtividades(ArrayList<Atividade> atividades) {
        this.atividades = atividades;
    }
    
    public ArrayList<Atividade> getAtividades() {
        return this.atividades;
    }
    
    public int addAtividade(Atividade atividade) throws SQLException, ClassNotFoundException {
        
        /*Retorna o total aproveitado dessa atividade*/
        
        if (atividade != null) {
            int somaAtividadePorCategoria = this.buscarHorasPorCategoria(atividade);
            if(atividade.getQuantHoras() > atividade.getCategoria().getLimiteHoras()){
                atividade.setTotalAproveitado(0);
                this.atividades.add(atividade);
                //return atividade.getCategoria().getLimiteHoras();
                return 0;
            }
            else{
                if(somaAtividadePorCategoria == 0 || somaAtividadePorCategoria + atividade.getQuantHoras() <= atividade.getCategoria().getLimiteHoras()){
                    atividade.setTotalAproveitado(atividade.getQuantHoras());
                    this.atividades.add(atividade);
                    return atividade.getQuantHoras();
                }
                if (atividade.getCategoria().getLimiteHoras() - somaAtividadePorCategoria >= 0){
                    atividade.setTotalAproveitado(atividade.getCategoria().getLimiteHoras() - somaAtividadePorCategoria);
                    this.atividades.add(atividade);
                    return (atividade.getCategoria().getLimiteHoras() - somaAtividadePorCategoria);
                }
            
            }
                
            this.atividades.add(atividade);
           }
        return -1;
    }
    
    
    public void contabilizarAtividade(Atividade atividade) throws SQLException, ClassNotFoundException{
        if (this != null && atividade != null) {
            int somaAtividadePorCategoria = this.buscarHorasPorCategoria(atividade);
            if (somaAtividadePorCategoria < atividade.getCategoria().getLimiteHoras()) {                
                if (somaAtividadePorCategoria + atividade.getQuantHoras() <= atividade.getCategoria().getLimiteHoras()) {
                    this.setQuantHoras(this.getQuantHoras());                    
                } else {
                    this.setQuantHoras(atividade.getCategoria().getLimiteHoras());
                }
                AlunoDAO.getInstancia().alterar(this);
            }            
        }   
    
    }
    
   

    public static final Font BOLD_UNDERLINED = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public static final Font NORMAL = new Font(Font.FontFamily.TIMES_ROMAN, 12);
   /*
    public void emitirRelatorio() throws SQLException, ClassNotFoundException{
        Document documento = new Document();

        try {

            PdfWriter.getInstance(documento, new FileOutputStream("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Relatorios\\relatorio.pdf"));

            documento.open();

            Font fTitulo = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            
            Font fCorpo = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
            
            Font fLista = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD);
                      
            Image imagemLogoUFC = Image.getInstance("C:/Users/willi/OneDrive/Documentos/NetBeansProjects/GerenciarAtividadeComplementar-master/src/Imagem/a.png");

            imagemLogoUFC.setAlignment(Element.ALIGN_CENTER);

            documento.add(imagemLogoUFC);

            Paragraph titulo = new Paragraph("RELATÓRIO - ATIVIDADES COMPLEMENTARES", fTitulo);

            titulo.setAlignment(Element.ALIGN_CENTER);
            
            titulo.setSpacingAfter((float) 20.00);

            documento.add(titulo);

            Paragraph matricula = new Paragraph("Matricula: ", fCorpo);

            matricula.add("" + this.matricula);

            matricula.setAlignment(Element.ALIGN_LEFT);

            matricula.setSpacingAfter((float) 10.00);

            documento.add(matricula);

            Paragraph nome = new Paragraph("Nome: ", fCorpo);

            nome.add("" + this.nome);

            nome.setAlignment(Element.ALIGN_LEFT);

            nome.setSpacingAfter((float) 10.00);

            documento.add(nome);
            
            Paragraph curso = new Paragraph("Curso: ", fCorpo);
            
            curso.add("" + this.getCurso().getNome());
            
            curso.setAlignment(Element.ALIGN_LEFT);
            
            curso.setSpacingAfter((float)10.00);
            
            documento.add(curso);
           
            
            Paragraph categoria = new Paragraph("Categorias: ", fCorpo);
            
            documento.add(categoria);
            
            List listaCategorias = new RomanList();
            
            ListItem categoria1 = new ListItem("Atividades de iniciação à docência", fLista);
            
            listaCategorias.add(categoria1);
            
            ListItem categoria2 = new ListItem("Atividades de iniciação à pesquisa", fLista);
            
            listaCategorias.add(categoria2);
            
            ListItem categoria3 = new ListItem("Atividades de extensão", fLista);
            
            listaCategorias.add(categoria3);
            
            ListItem categoria4 = new ListItem("Atividades artístico-culturais e esportivas", fLista);
            
            listaCategorias.add(categoria4);
            
            ListItem categoria5 = new ListItem("Atividades de participação e/ou organização de eventos", fLista);
            
            listaCategorias.add(categoria5);
            
            ListItem categoria6 = new ListItem("Experiências ligadas à formação profissional e/ou correlatas", fLista);
            
            listaCategorias.add(categoria6);
            
            ListItem categoria7 = new ListItem("Produção Técnica e/ou Científica", fLista);
            
            listaCategorias.add(categoria7);
            
            ListItem categoria8 = new ListItem("Vivências de gestão", fLista);
            
            listaCategorias.add(categoria8);
            
            ListItem categoria9 = new ListItem("Outras atividades, estabelecidas de acordo com o Art. 3º. dessa Resolução", fLista);
        
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
            
            ArrayList<Atividade> atividades = this.buscarAtividades();
                    
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

            total.addCell(this.getQuantHoras()+"");
            
            documento.add(total);
            
       
        } catch (DocumentException de) {
           
            System.err.println(de.getMessage());
        
        } catch (IOException ioe) {
        
            System.err.println(ioe.getMessage());
        
        }
        
        documento.close();

        /*
          
          quando clicar no botao de gerar pdf
          
         *//*
        File file = new File("C:\\Users\\willi\\OneDrive\\Documentos\\2018.2\\Processos de Software\\Parte 1\\Trabalho\\Relatorios\\relatorio.pdf");

        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {

        }
    
    }*/
     
    @Override
    public void inserir() throws ClassNotFoundException, SQLException {
        if(this.matricula > 0 && this.nome != null && this.curso != null && this.situacao != false){
            
               AlunoDAO.getInstancia().inserir(this);
           
        }
    }

    @Override
    public void alterar() throws ClassNotFoundException, SQLException {
        if(this.matricula > 0 && this.nome != null && this.curso != null && this.situacao != false){
            AlunoDAO.getInstancia().alterar(this);
        }
    }

    @Override
    public void buscar(int codigo) throws ClassNotFoundException, SQLException {
        if(codigo > 0){
            this.matricula = codigo;
            AlunoDAO.getInstancia().buscar(this);
        }
    }

    @Override
    public void excluir() throws ClassNotFoundException, SQLException {
        AlunoDAO.getInstancia().excluir(this);
    }

    public void inserirAlunoAtividade(Atividade atividade,  int horas_Aproveitadas) throws SQLException, ClassNotFoundException {
        if(this != null && atividade != null){
            AlunoDAO.getInstancia().inserirAlunoAtividade(this, atividade, horas_Aproveitadas);
        }
    }
    
    public void alterarAlunoAtividade(Atividade atividade, int horas_Aproveitadas) throws SQLException, ClassNotFoundException{
        if(this != null && atividade != null){
            AlunoDAO.getInstancia().alterarAlunoAtividade(this, atividade, horas_Aproveitadas);
        }
    }
    
    public void excluirAlunoAtividade(Atividade atividade) throws SQLException, ClassNotFoundException{
        if(this != null && atividade != null){
            AlunoDAO.getInstancia().excluirAlunoAtividade(this, atividade);
        }
    }
    
    public int buscarHorasPorCategoria(Atividade atividade) throws SQLException, ClassNotFoundException{
        if(this != null && atividade != null){
            return AlunoDAO.getInstancia().buscarHorasPorCategoria(this, atividade);
        }
        return -1;
    }
    
    public ArrayList<Atividade> buscarAtividades() throws SQLException, ClassNotFoundException{
        if(this != null){
             AlunoDAO.getInstancia().buscarAtividades(this);
        }
        return this.getAtividades();
    }
    
    public void buscarAlunoAtividade(Atividade atividade) throws SQLException, ClassNotFoundException{
        if(this != null && atividade != null){
            AlunoDAO.getInstancia().buscarAlunoAtividade(this, atividade);
        }
    }
 
    public int buscarHorasAproveitadas(Atividade atividade) throws SQLException, ClassNotFoundException {
        if(this != null && atividade != null){
            return AlunoDAO.getInstancia().buscarHorasAproveitadas(this, atividade);
        }
        return -1;
    }
    
}
