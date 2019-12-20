package Controllers;

import Models.Configuracao;
import Models.InterfaceObserver;
import Views.FrmFuncionarioCadastro;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author willi
 */
public class ControllerFuncionarioCadastrar implements InterfaceObserver{
    
    private Configuracao model;
    private FrmFuncionarioCadastro view;
    
    public ControllerFuncionarioCadastrar(FrmFuncionarioCadastro view, Configuracao model){
        this.view = view;
        this.model = model;
        this.model.incluir(this);//pedindo pra ser um observer
    }
    
    public void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.salvaFuncionario(this.view.getContato(), this.view.getNome(), this.view.getLogin(), this.view.getSenha());
                    this.view.mostraMensagem("Usuário cadastrado com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar o usuário. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Cancelar")) {
            this.view.limpaCampos();
            this.view.setVisible(false);
            this.view.getPrincipal().setVisible(true);//volta para a tela inicial
        }
  
    }
    
    public void evento(ActionEvent evt) {
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }

        this.model.avisarObservers();
    }  

    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);//deixando de ser um observer
    }

    @Override
    public void alterar() {
        //voltar para a tela inicial
        //ja que foi cadastrado um novo funcionario tem que atualizar a view da tabela ou nao?
    }
   
    
}
