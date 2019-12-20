/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Configuracao;
import Models.InterfaceObserver;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;
import Views.FrmAtividadeCadastro;

/**
 *
 * @author Beatriz Oliveiira
 */
public class ControllerAtividadeCadastrar implements InterfaceObserver{

    private Configuracao model;
    private FrmAtividadeCadastro view;
    
    public ControllerAtividadeCadastrar(FrmAtividadeCadastro view, Configuracao model){
        this.view = view;
        this.model = model;
    }
    
    public void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.salvarAtividade(this.view.getNomeAtividade(), this.view.getNomeCategoria(), 
                            Integer.parseInt(this.view.getQuantHoras()));
                    this.view.mostraMensagem("Atividade cadastrada com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar a atividade. Mensagem retornada: " + ex.getMessage());
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

    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);//deixando de ser um observer
    }
    
    @Override
    public void alterar() {
    
    } 
    
}
