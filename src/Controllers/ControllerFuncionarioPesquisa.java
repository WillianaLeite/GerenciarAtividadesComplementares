package Controllers;

import Models.Configuracao;
import Models.Funcionario;
import Models.InterfaceObserver;
import Views.FrmFuncionarioPesquisa;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author willi
 */
public class ControllerFuncionarioPesquisa implements InterfaceObserver{
    
    private Configuracao model;
    private FrmFuncionarioPesquisa view;
    
    public ControllerFuncionarioPesquisa(FrmFuncionarioPesquisa view, Configuracao model){
        this.view = view;
        this.model = model;
        this.model.incluir(this);//pedindo pra ser um observer
    }
    
    public void eventoBotao(ActionEvent evt) {
       if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.alterarFuncionario(Integer.parseInt(this.view.getId()), this.view.getContato(), this.view.getNome(), this.view.getLogin(), this.view.getSenha());
                    this.view.mostraMensagem("Funcionario salvo com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar o funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

       if (((JButton) evt.getSource()).getText().equals("Excluir")) {
            if (!this.view.getId().equals("")) {
                try {
                    this.model.excluirFuncionario(Integer.parseInt(this.view.getId()));
                    this.view.mostraMensagem("Funcionario excluído com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível excluir o funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Cancelar")) {
            this.view.limpaCampos();
            this.view.fechaTela();
        }

        if (((JButton) evt.getSource()).getText().equals("OK")) {
            if (this.view.getPesquisa().equals("todos")) {
                try {
                    this.model.buscaFuncionarios();
                   
                    
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar os funcionarios. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            } else {
                try {
                    this.model.buscarNomeFuncionario(this.view.getPesquisa());
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mostraMensagem("Não foi possível buscar o funcionario. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }
  
    }
    
    public void evento(ActionEvent evt) {
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }

        this.model.avisarObservers();
    }  

     public void evento(MouseEvent evt) {
      int linha = this.view.getTableFuncionario().getSelectedRow();
                        
        if (linha >= 0) {
            try {
                Funcionario funcionario = new Funcionario();
                funcionario.buscar(Integer.parseInt(this.view.getTableFuncionario().getValueAt(linha, 0).toString()));
                this.view.preencheCamposAlteracao(funcionario);

            } catch (ClassNotFoundException | SQLException ex) {
                this.view.mostraMensagem("Não foi possível selecionar funcionario. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
                }
            } 
     
     }
    
    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);//deixando de ser um observer
    }

    @Override
    public void alterar() {
        ArrayList<Funcionario> funcionarios = this.model.getFuncionarios();
        if (funcionarios != null) {
            this.view.limpaTableFuncionarios();

            for (Funcionario funcionario : funcionarios) {
                String[] novaLinha = {String.valueOf(funcionario.getId()), funcionario.getNome(), funcionario.getContato()};
                ((DefaultTableModel) this.view.getTableFuncionario().getModel()).addRow(novaLinha);
            }
        }

        this.model.setFuncionarios(new ArrayList<Funcionario>());
    
    }

    
}
