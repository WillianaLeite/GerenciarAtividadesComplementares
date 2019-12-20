package Controllers;

import Models.Configuracao;
import Models.Curso;
import Models.InterfaceObserver;
import Views.FrmAlunoCadastro;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author willi
 */
public class ControllerAlunoCadastrar implements InterfaceObserver{
    private Configuracao model;
    private FrmAlunoCadastro view;
    
    public ControllerAlunoCadastrar(FrmAlunoCadastro view, Configuracao model){
        this.view = view;
        this.model = model;
        this.model.incluir(this);
        //this.model.avisarObservers();
    }

    public void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.salvarAluno(Integer.parseInt(this.view.getMatricula()), this.view.getNome(), this.view.getCursoEscolhido(), Integer.parseInt(this.view.getLimiteHoras()), this.view.isSituacao(), this.view.getAdvertencia());
                    this.view.mostraMensagem("Aluno cadastrado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar o aluno. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Cancelar")) {
            this.view.limpaCampos();
            this.view.fechaTela();
           // this.view.getPrincipal().setVisible(true);//volta para a tela inicial
        }
  
    }
    
    public void evento(ActionEvent evt) {
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }

        this.model.avisarObservers();
    }  
    
    public void preencherCamposCombo(){
        try {
            ArrayList<Curso> cursos = this.model.buscarTodosCursos();
            
            if(cursos.size() > 0){
                
                this.view.getCursoSelecionado().removeAllItems();
                
                for (Curso curso : cursos){
                
                    this.view.getCursoSelecionado().addItem(curso.getNome());
                    
                }
                
                this.view.getCursoSelecionado().addItem("Outros");
            }
            
            this.view.getCursoSelecionado().setSelectedIndex(-1);
            
        } catch (SQLException ex) {
            this.view.mostraMensagem("O sistema não conseguiu trazer os nomes dos cursos nos bancos!");
        } catch (ClassNotFoundException ex) {
            this.view.mostraMensagem("O sistema não conseguiu encontrar a classe!");
        }    
        
    }
    
    public void evento(ItemEvent evt) throws SQLException, ClassNotFoundException {
        
        String cursoSelecionado = this.view.getCursoSelecionado().getSelectedItem()+"";
        
        ArrayList<Curso> cursos = this.model.buscarTodosCursos();
        
        if(!cursoSelecionado.equals("Outros")){
        
            if(cursos != null){ // Tem cursos cadastrados no banco

                for (Curso curso: cursos){//saber se o curso escolhido existe no banco

                    if(curso.getNome().equals(cursoSelecionado)){//se tem no banco, então atualiza a variável
                        this.view.setCursoEscolhido(cursoSelecionado);
                        this.view.setNomeCurso(curso.getNome());
                        this.view.setLimiteHoras(curso.getMaximoHorasComplementares()+"");

                    }

                }
                
                
                
            }else {//não tem cursos cadastrados no banco
                this.view.mostraMensagem("Você precisa inserir os cursos no sistema!");
                this.view.limpaCampos();        
            }
        }else if(cursoSelecionado.equals("Outros")){ // então precisa criar o curso
           // this.model.avisarObservers();
            this.model.incluir(this);
            this.view.getNomeCursoo().setEnabled(true);
            this.view.getLimiteCurso().setEnabled(true);
            //this.view.getCursoSelecionado().setEnabled(false);
            this.model.salvarCurso(this.view.getNomeCurso(), Integer.parseInt(this.view.getLimiteHoras()));
            this.model.avisarObservers();
            this.model.excluir(this);
        }
        
        
    }

    
    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);//deixando de ser um observer
    }
    
    @Override
    public void alterar() {
        try {
            ArrayList<Curso> cursos = this.model.buscarTodosCursos();
            
            if(cursos.size() > 0){
                
                this.view.getCursoSelecionado().removeAllItems();
                
                for (Curso curso : cursos){
                
                    this.view.getCursoSelecionado().addItem(curso.getNome());
                    
                }
                
                this.view.getCursoSelecionado().addItem("Outros");
            }
            
            this.view.getCursoSelecionado().setSelectedIndex(-1);
            
        } catch (SQLException ex) {
            this.view.mostraMensagem("O sistema não conseguiu trazer os nomes dos cursos nos bancos!");
        } catch (ClassNotFoundException ex) {
            this.view.mostraMensagem("O sistema não conseguiu encontrar a classe!");
        }    
    
    } 
}
