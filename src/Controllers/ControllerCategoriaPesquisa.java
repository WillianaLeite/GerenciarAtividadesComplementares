/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Configuracao;
import Views.FrmCategoriaPesquisa;
import Models.Categoria;
import Models.InterfaceObserver;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Beatriz Oliveiira
 */
public class ControllerCategoriaPesquisa implements InterfaceObserver {

    private Configuracao model;
    private FrmCategoriaPesquisa view;
    
    public ControllerCategoriaPesquisa(FrmCategoriaPesquisa view, Configuracao model){
        this.view = view;
        this.model = model;
        this.model.incluir(this);//pedindo pra ser um observer
    }
    
    public void eventoBotao(ActionEvent evt) {
       if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.alterarCategoria(Integer.parseInt(this.view.getId()), this.view.getNomeCurso(), this.view.getCategoria(), Integer.parseInt(this.view.getLimiteHoras()), this.view.getDescricao());
                    this.view.mostraMensagem("Categoria salva com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar a categoria. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

       if (((JButton) evt.getSource()).getText().equals("Excluir")) {
            if (!this.view.getId().equals("")) {
                try {
                    this.model.excluirCategoria(Integer.parseInt(this.view.getId()));
                    this.view.mostraMensagem("Categoria excluída com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível excluir a categoria. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Cancelar")) {
            this.view.limpaCampos();
            this.view.fechaTela();
        }

        if (((JButton) evt.getSource()).getText().equals("OK")) {
            if(this.view.validaPesquisa()){
                if (this.view.getPesquisaCategoria().equals("todos") || this.view.getPesquisaCategoria().equals("Todos")
                        || this.view.getPesquisaCategoria().equals("TODOS")) {
                    try {
                        this.model.buscaCategoria();
                    } catch (SQLException | ClassNotFoundException ex) {
                        this.view.mostraMensagem("Não foi possível buscar todas as categorias. Mensagem retornada: " + ex.getMessage());
                        this.view.limpaCampos();
                    }
                } else {
                    try {
                        this.model.buscarCategoriaPorNome(this.view.getPesquisaCategoria());
                    } catch (ClassNotFoundException | SQLException ex) {
                        this.view.mostraMensagem("Não foi possível buscar a categoria. Mensagem retornada: " + ex.getMessage());
                        this.view.limpaCampos();
                    }
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
      int linha = this.view.getTableCategoria().getSelectedRow();
                        
        if (linha >= 0) {
            try {
                Categoria categoria = new Categoria();
                categoria.buscar(Integer.parseInt(this.view.getTableCategoria().getValueAt(linha, 0).toString()));
                this.view.preencheCamposAlteracao(categoria);

            } catch (ClassNotFoundException | SQLException ex) {
                this.view.mostraMensagem("Não foi possível selecionar categoria. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
                }
            } 
     
     }
    
    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);//deixando de ser um observer
    }

    @Override
    public void alterar() {
        ArrayList<Categoria> categorias = this.model.getCategorias();
        this.view.limpaTableAtividade();
        if (categorias != null) {
            for (Categoria categoria: categorias) {
                String[] novaLinha = {String.valueOf(categoria.getId()), categoria.getNomeCategoria(), 
                    String.valueOf(categoria.getCurso().getNome()), String.valueOf(categoria.getLimiteHoras()), categoria.getDescricao()};
                ((DefaultTableModel) this.view.getTableCategoria().getModel()).addRow(novaLinha);
            }
        }

        this.model.setCategoria(new ArrayList<>());
    
    }
}
