package Controllers;

import Models.Configuracao;
import Models.Aluno;
import Models.InterfaceObserver;
import Views.FrmAlunoPesquisa;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author willi
 */
public class ControllerAlunoPesquisa implements InterfaceObserver{
    
    private Configuracao model;
    private FrmAlunoPesquisa view;
    
    public ControllerAlunoPesquisa(FrmAlunoPesquisa view, Configuracao model){
        this.view = view;
        this.model = model;
        this.model.incluir(this);//pedindo pra ser um observer
    }

    public void evento(MouseEvent evt) {
      int linha = this.view.getTblAlunos().getSelectedRow();
                        
        if (linha >= 0) {
            try {
                Aluno aluno = new Aluno();
                aluno.buscar(Integer.parseInt(this.view.getTblAlunos().getValueAt(linha, 0).toString()));
                this.view.preencheCamposAlteracao(aluno);

            } catch (ClassNotFoundException | SQLException ex) {
                this.view.mostraMensagem("Não foi possível selecionar aluno. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
                }
            } 
     
     }
    
    
    public void evento(ActionEvent evt) {
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }

        this.model.avisarObservers();
    }
    
    
    public void eventoBotao(ActionEvent evt) {
       if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                     boolean situacao = false;
                    if (this.view.getSituacao().equals("Ativo")){
                        situacao = true;
                    } 
                    this.model.alterarAluno(Integer.parseInt(this.view.getMatricula()), this.view.getNome(), this.view.getNomeCurso(), Integer.parseInt(this.view.getQuantHoras()), situacao, this.view.getAdvertencia());
                    this.view.mostraMensagem("Aluno salvo com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar o aluno. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

       if (((JButton) evt.getSource()).getText().equals("Excluir")) {
            if (!this.view.getMatricula().equals("")) {
                try {
                    this.model.excluirFuncionario(Integer.parseInt(this.view.getMatricula()));
                    this.view.mostraMensagem("Aluno excluído com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível excluir o aluno. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Cancelar")) {
            this.view.limpaCampos();
            this.view.fechaTela();
        }

        if (((JButton) evt.getSource()).getText().equals("OK")) {
            if (this.view.getPesquisaAluno().equals("todos")) {
                try {
                    this.model.buscarAlunos();
                   
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar os alunos. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            } else {
                try {
                    this.model.buscarAluno(Integer.parseInt(this.view.getPesquisaAluno()));
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar o aluno. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
       // this.model.avisarObservers();
    }
    
    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);//deixando de ser um observer
    }
    
    @Override
    public void alterar() {
        ArrayList<Aluno> alunos = this.model.getAlunos();
        if (alunos != null) {
            this.view.limpaTableAlunos();

            for (Aluno aluno : alunos) {
                try {
                    String situacao;
                    if (aluno.isSituacao() == true){
                        situacao = "Ativo";
                    } else {
                        situacao = "Inativo";
                    }
                    String[] novaLinha = {String.valueOf(aluno.getMatricula()), aluno.getNome(), aluno.getCurso().getNome(), aluno.getAdvertencia(),String.valueOf(aluno.getQuantHoras())};
                    ((DefaultTableModel) this.view.getTblAlunos().getModel()).addRow(novaLinha);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAlunoPesquisa.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControllerAlunoPesquisa.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        this.model.setAlunos(new ArrayList<Aluno>());
    
    }
    
}
