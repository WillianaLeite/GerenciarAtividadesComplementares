/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Configuracao;
import Models.Atividade;
import Models.InterfaceObserver;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import Views.FrmAtividadePesquisa;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beatriz Oliveiira
 */
public class ControllerAtividadePesquisa implements InterfaceObserver {

    private Configuracao model;
    private FrmAtividadePesquisa view;

    public ControllerAtividadePesquisa(FrmAtividadePesquisa view, Configuracao model) {
        this.view = view;
        this.model = model;
        this.model.incluir(this);//pedindo pra ser um observer
    }

    public void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Salvar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.alterarAtividade(Integer.parseInt(this.view.getId()), this.view.getNomeAtividade(), this.view.getCategoria(), Integer.parseInt(this.view.getQuantHoras()));
                    this.view.mostraMensagem("Atividade salva com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível salvar a atividade. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Excluir")) {
            if (!this.view.getId().equals("")) {
                try {
                    this.model.excluirAtividade(Integer.parseInt(this.view.getId()));
                    this.view.mostraMensagem("Atividade excluída com sucesso.");
                    this.view.limpaCampos();
                } catch (ClassNotFoundException | SQLException ex) {
                    this.view.mostraMensagem("Não foi possível excluir a atividade. Mensagem retornada: " + ex.getMessage());
                    this.view.limpaCampos();
                }
            }
        }

        if (((JButton) evt.getSource()).getText().equals("Cancelar")) {
            this.view.limpaCampos();
            this.view.fechaTela();
        }

        if (((JButton) evt.getSource()).getText().equals("OK")) {
            if (this.view.validaPesquisa()) {
                if (this.view.getPesquisaAtividade().equals("todos") || this.view.getPesquisaAtividade().equals("Todos")
                        || this.view.getPesquisaAtividade().equals("TODOS")) {
                    try {
                        this.model.buscaAtividade();

                    } catch (SQLException | ClassNotFoundException ex) {
                        this.view.mostraMensagem("Não foi possível buscar todas as atividades. Mensagem retornada: " + ex.getMessage());
                        this.view.limpaCampos();
                    }
                } else {
                    try {
                        this.model.buscarAtividadePorNome(this.view.getPesquisaAtividade());
                    } catch (ClassNotFoundException | SQLException ex) {
                        this.view.mostraMensagem("Não foi possível buscar a atividade. Mensagem retornada: " + ex.getMessage());
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
        int linha = this.view.getTableAtividade().getSelectedRow();

        if (linha >= 0) {
            try {
                Atividade atividade = new Atividade();
                atividade.buscar(Integer.parseInt(this.view.getTableAtividade().getValueAt(linha, 0).toString()));
                this.view.preencheCamposAlteracao(atividade);

            } catch (ClassNotFoundException | SQLException ex) {
                this.view.mostraMensagem("Não foi possível selecionar atividade. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
            }
        }

    }

    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);//deixando de ser um observer
    }

    @Override
    public void alterar() {
        ArrayList<Atividade> atividades = this.model.getAtividades();
        this.view.limpaTableAtividade();
        if (atividades != null) {
            for (Atividade atividade : atividades) {
                String[] novaLinha = {String.valueOf(atividade.getId()), atividade.getNomeAtividade(),
                    String.valueOf(atividade.getCategoria().getNomeCategoria()), String.valueOf(atividade.getQuantHoras())};
                ((DefaultTableModel) this.view.getTableAtividade().getModel()).addRow(novaLinha);
            }
        }

        this.model.setAtividade(new ArrayList<>());

    }

}
