package Controllers;

import Models.Aluno;
import Models.Atividade;
import Models.Categoria;
import Views.FrmContabilizarAtividade;
import Models.Configuracao;
import Models.Aluno;
import Models.InterfaceObserver;
import Views.FrmAtividadeCadastro;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

public class ControllerContabilizarAtividade implements InterfaceObserver {

    private FrmContabilizarAtividade view;
    private Configuracao model;
    private Aluno aluno;

    public ControllerContabilizarAtividade(FrmContabilizarAtividade view, Configuracao model) {
        this.view = view;
        this.model = model;
        this.model.incluir(this);//pedindo pra ser um observer
    }

    public void eventoBotao(ActionEvent evt) throws SQLException, ClassNotFoundException {

        if (((JButton) evt.getSource()).getText().equals("Nova Atividade")) {
            this.view.fechaTela();
            FrmAtividadeCadastro novaView = new FrmAtividadeCadastro(this.model);
            novaView.setVisible(true);
            this.view.getPrincipal().getJdpPrincipal().add(novaView);
            this.view.getPrincipal().colocarFormularioCentro(novaView);
        }

        if (((JButton) evt.getSource()).getText().equals("Contabilizar")) {
           // try {
                
                Atividade atividade = new Atividade();
                
                atividade.buscarPorNome(this.view.getAtividadeEscolhida());

                this.model.salvarAlunoAtividades(Integer.parseInt(this.view.getMatricula()), atividade.getId());

                this.view.limpaCampos();

                this.view.mostraMensagem("A atividade foi contabilizada com sucesso!");

            /*} catch (SQLException | ClassNotFoundException ex) {
                this.view.mostraMensagem("Não foi possível contabilizar a atividade do aluno. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
            }*/
        }

        if (((JButton) evt.getSource()).getText().equals("Cancelar")) {
            this.view.fechaTela();
        }

        if (((JButton) evt.getSource()).getText().equals("Excluir")) {

            try {

                Atividade atividade = new Atividade();
                
                atividade.buscarPorNome(this.view.getAtividadeEscolhida());
                
                this.model.excluirAlunoAtividade(Integer.parseInt(this.view.getMatricula()), atividade.getId());

            } catch (SQLException | ClassNotFoundException ex) {
                this.view.mostraMensagem("Não foi possível excluir a atividade feita pelo aluno. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
            }

        }
        this.model.avisarObservers();
    }

    public void eventoBotaoOkAluno(ActionEvent evt) {

        if (this.view.validaCamposPesquisaAluno()) {
            try {
                this.aluno = this.model.buscaAluno(Integer.parseInt(this.view.getPesquisaAluno()));
                this.view.preencheCamposAluno(aluno);

            } catch (SQLException | ClassNotFoundException ex) {
                this.view.mostraMensagem("Não foi possível pesquisar o aluno. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
            }
        }
        this.model.avisarObservers();
    }

    
    public void preencherCamposCombo(){
        try {
            ArrayList<Atividade> atividades = this.model.burcarTodasAtividades();
            
            if(atividades.size() > 0){
                
                this.view.getAtividadeSelecionada().removeAllItems();
                
                for (Atividade atividade : atividades){
                
                    this.view.getAtividadeSelecionada().addItem(atividade.getNomeAtividade());
                    
                }
                
                this.view.getAtividadeSelecionada().addItem("Nenhuma das opções");
            }
            
            this.view.getAtividadeSelecionada().setSelectedIndex(-1);
            
        } catch (SQLException ex) {
            this.view.mostraMensagem("O sistema não conseguiu trazer os nomes dos cursos nos bancos!");
        } catch (ClassNotFoundException ex) {
            this.view.mostraMensagem("O sistema não conseguiu encontrar a classe!");
        }    
        
    }
    
    public void evento(ActionEvent evt) throws SQLException, ClassNotFoundException {
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }

       // this.model.avisarObservers();
    }

    public void evento(MouseEvent evt) {
        int linha = this.view.getTblAtividadesDoAluno().getSelectedRow();

        if (linha >= 0) {
            try {
                Atividade atividade = new Atividade();
                atividade.buscarPorNome(this.view.getTblAtividadesDoAluno().getValueAt(linha, 0).toString());
                this.view.preencheCamposSelecionadoAtividade(atividade);

            } catch (ClassNotFoundException | SQLException ex) {
                this.view.mostraMensagem("Não foi possível selecionar atividade. Mensagem retornada: " + ex.getMessage());
                this.view.limpaCampos();
            }
        }

    }
    
    public void eventoCombo(ItemEvent evt) throws SQLException, ClassNotFoundException{
        
        this.view.getBtnNovaAtividade().setEnabled(false);
        
        String atividadeSelecionada = this.view.getAtividadeSelecionada().getSelectedItem()+"";
        
        ArrayList<Atividade> atividades = this.model.burcarTodasAtividades();
        
        if(!atividadeSelecionada.equals("Nenhuma das opções")){
        
            if(atividades != null){ // Tem atividades cadastrados no banco

                for (Atividade atividade: atividades){//saber se a atividade escolhida existe no banco

                    if(atividade.getNomeAtividade().equals(atividadeSelecionada)){//se tem no banco, então atualiza a variável
                        this.view.setAtividadeEscolhida(atividade.getNomeAtividade());
                        this.view.setAtividadeEscolhida(atividade.getNomeAtividade());
                        this.view.setCategoria(atividade.getCategoria().getNomeCategoria());
                        this.view.setQuantHoraAtividade(atividade.getQuantHoras()+"");

                    }

                }
                
                this.view.getBtnNovaAtividade().setEnabled(false);
                
            }else {//não tem cursos cadastrados no banco
                this.view.mostraMensagem("Você precisa inserir atividades no sistema!");
                this.view.limpaCamposAtividade();        
            }
        }else{ // então precisa criar o curso
            this.view.getBtnNovaAtividade().setEnabled(true);
            this.view.limpaCampos();
        }
    
    }
    
    public void evento(InternalFrameEvent evt) {
        this.model.excluir(this);//deixando de ser um observer
    }

    @Override
    public void alterar() {
        try {
            ArrayList<Atividade> atividades = this.model.buscarAtividadesDoAluno(this.aluno.getMatricula());
            System.out.println("quantidade de atividades retornadas da model: " + atividades.size());
            if (atividades != null) {
                this.view.limpaTableAtividadesDoAluno();
                int totalHoras = 0;
                Aluno aluno = new Aluno();
                aluno.buscar(Integer.parseInt(this.view.getPesquisaAluno()));
                
                aluno.setAtividades(atividades);
                
                for (Atividade atividadees : atividades) {
                    Categoria categoria = new Categoria();
                    categoria.buscar(atividadees.getCategoria().getId());
                    
                    totalHoras += aluno.buscarHorasAproveitadas(atividadees);
                    String[] novaLinha = {atividadees.getNomeAtividade(), String.valueOf(atividadees.getQuantHoras()), categoria.getNomeCategoria(), String.valueOf(categoria.getLimiteHoras()), String.valueOf(/*aluno.buscarHorasAproveitadas(atividadees)*/atividadees.getTotalAproveitado())};
                    ((DefaultTableModel) this.view.getTblAtividadesDoAluno().getModel()).addRow(novaLinha);
                }
                this.view.setTotalHoras(aluno.getQuantHoras()+"");
            }
           
            this.model.setAlunoAtividade(new ArrayList<Atividade>());
        } catch (Exception ex) {
          // this.view.mostraMensagem("Não foi possível atualizar as atividades dos alunos. Mensagem retornada: " + ex.getMessage());
        }

    }

}
