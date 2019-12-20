package Controllers;

import Models.Aluno;
import Models.Configuracao;
import Models.Categoria;
import Views.FrmRelatorio;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import Models.Categoria;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;

/**
 *
 * @author willi
 */
public class ControllerRelatorio {
   
    private FrmRelatorio view;
    private Configuracao model;
    private Categoria tipoCategoria = null;
    
    public ControllerRelatorio(FrmRelatorio view,Configuracao model){
        this.view = view;
        this.model = model;
    }
    
    public void evento(ActionEvent evt) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException{
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }
    } 
     
    public void eventoBotao(ActionEvent evt) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
        
        if (((JButton) evt.getSource()).getText().equals("OK")) {
             
            try {
                   Aluno aluno = new Aluno();
                   aluno.buscar(Integer.parseInt(this.view.getMatricula().trim()));
                   this.view.preencheCamposAlteracao(aluno);
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar o aluno. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
        
        }
        
        
            if (((JButton) evt.getSource()).getText().equals("Gerar PDF")) {
                if (this.view.validaCampos()) {

                    this.model.gerarRelatorio(this.view.getMatricula(), this.tipoCategoria);

                }            
            }

        if (((JButton) evt.getSource()).getText().equals("Cancelar")){
            
            this.view.limpaCampos();
            this.view.fechaTela();
        
        }
    
    }

    public void evento(){
        this.model.recerberEmails();
    }
    
    
    public void evento(String dest){
        
        this.model.enviarEmail(dest);
        
    }
    
    
    public void evento(ItemEvent evt) throws SQLException, ClassNotFoundException {
        
        String categoriaSelecionado = this.view.getCategoriaSelecionada().getSelectedItem()+"";
        
        ArrayList<Categoria> categorias = this.model.buscarTodasCategorias();
        
        if(!categoriaSelecionado.equals("Geral")){
        
            if(categorias != null){ // Tem cursos cadastrados no banco

                for (Categoria categoria: categorias){//saber se o curso escolhido existe no banco

                    if(categoria.getNomeCategoria().equals(categoriaSelecionado)){//se tem no banco, então atualiza a variável
                        this.view.setCategoriaEscolhido(categoriaSelecionado);
                        this.tipoCategoria =categoria;
                    }

                }
                
                
                
            }else {//não tem cursos cadastrados no banco
                this.view.mostraMensagem("Você precisa inserir as categorias no sistema!");
                this.view.limpaCampos();        
            }
        }else if(categoriaSelecionado.equals("Geral")){ // então precisa criar o curso
           tipoCategoria = null;
        }
        
        
    }
 
    
    
    public void preencherCamposCombo(){
        try {
            ArrayList<Categoria> categorias = this.model.buscarTodasCategorias();
            
            if(categorias.size() > 0){
                
                this.view.getCategoriaSelecionada().removeAllItems();
                
                for (Categoria categoria : categorias){
                
                    this.view.getCategoriaSelecionada().addItem(categoria.getNomeCategoria());
                    
                }
                
                this.view.getCategoriaSelecionada().addItem("Geral");
            }
            
            this.view.getCategoriaSelecionada().setSelectedIndex(-1);
            
        } catch (SQLException ex) {
            this.view.mostraMensagem("O sistema não conseguiu trazer os nomes dos cursos nos bancos!");
        } catch (ClassNotFoundException ex) {
            this.view.mostraMensagem("O sistema não conseguiu encontrar a classe!");
        }    
        
    }
    
}
