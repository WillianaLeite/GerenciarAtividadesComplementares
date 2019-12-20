package Views;
import Controllers.ControllerContabilizarAtividade;
import Models.Aluno;
import Models.Atividade;
import Models.Configuracao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author willi
 */
public class FrmContabilizarAtividade extends javax.swing.JInternalFrame {

    private Configuracao model;
    private ControllerContabilizarAtividade controller;
    private FrmTelaPrincipal principal;
    private String atividadeEscolhida;
    
    public FrmContabilizarAtividade() {
        initComponents();
    }
    public FrmContabilizarAtividade(FrmTelaPrincipal principal,Configuracao model) {
        this();
        this.model = model;
        this.controller = new ControllerContabilizarAtividade(this, model);
        this.principal = principal;
        this.controller.preencherCamposCombo();
        this.getAtividadeSelecionada().setSelectedIndex(-1);
        this.categoria.setText("");
        this.quantHoraAtividade.setText("");
    }
    
     public void mostraMensagem(String mensagem) {
        if (mensagem != null) {
            JOptionPane.showMessageDialog(this, mensagem);
        }
    }
     
    public void fechaTela() {
        this.dispose();
    } 
     
    public void limpaCampos() {
        this.quantHoraAtividade.setText("");
        this.categoria.setText("");
        this.getAtividadeSelecionada().setSelectedIndex(-1);
        this.btnNovaAtividade.setEnabled(true);
        this.btnContabilizar.setEnabled(false);
        this.btnExcluir.setEnabled(false);
    }
    
    public void limpaCamposAtividade() {
        this.quantHoraAtividade.setText("");
        this.categoria.setText("");
        this.btnNovaAtividade.setEnabled(true);
        this.btnContabilizar.setEnabled(false);
        this.btnExcluir.setEnabled(false);
    }
    
    public void limpaTableAtividadesDoAluno() {
        DefaultTableModel tabela = (DefaultTableModel) this.tblAtividadesDoAluno.getModel();
        tabela.setNumRows(0);
    }
    
    public boolean validaCamposPesquisaAluno() {
        if (this.pesquisaAluno.getText().trim().equals("")) {
            this.mostraMensagem("Informe o nome do aluno.");
            this.pesquisaAluno.requestFocus();
            return false;
        }
        
       return true;
    }
    
    
    public void preencheCamposAtividade(Atividade atividade) {
       this.quantHoraAtividade.setText(String.valueOf(atividade.getQuantHoras()));
       this.categoria.setText(String.valueOf(atividade.getCategoria().getNomeCategoria()));
       this.btnContabilizar.setEnabled(true);
       this.btnExcluir.setEnabled(true);
       this.btnCancelar.setEnabled(true);
       this.btnNovaAtividade.setEnabled(false);
    }
    
    
    public void preencheCamposSelecionadoAtividade(Atividade atividade) {
       
       this.atividadeSelecionada.setEnabled(false);
       this.btnNovaAtividade.setEnabled(false);
       this.categoria.setEnabled(false);
       this.categoria.setText(String.valueOf(atividade.getCategoria().getNomeCategoria()));
       this.quantHoraAtividade.setEnabled(false);
       this.quantHoraAtividade.setText(String.valueOf(atividade.getQuantHoras()));
       this.btnContabilizar.setEnabled(false);
       this.btnExcluir.setEnabled(true);
       this.btnCancelar.setEnabled(true);
       this.btnNovaAtividade.setEnabled(false);
    }
    
    public void preencheCamposAluno(Aluno aluno){
        this.matricula.setText(String.valueOf(aluno.getMatricula()));
        this.nomeAluno.setText(String.valueOf(aluno.getNome()));
        this.btnCancelar.setEnabled(false);
        this.btnContabilizar.setEnabled(false);
        this.btnExcluir.setEnabled(false);
    }
    

    public FrmTelaPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(FrmTelaPrincipal principal) {
        this.principal = principal;
    }

    public JTable getTblAtividadesDoAluno() {
        return tblAtividadesDoAluno;
    }

    public void setTblAtividadesDoAluno(JTable tblAtividadesDoAluno) {
        this.tblAtividadesDoAluno = tblAtividadesDoAluno;
    }  
    
    public String getPesquisaAluno(){
        return this.pesquisaAluno.getText();
    }
    public void setPesquisaAluno(String pesquisaAluno){
        this.pesquisaAluno.setText(pesquisaAluno);
    }
    
 
    
    public String getTotalHoras(){
        return this.totalHoras.getText();
    }
    public void setTotalHoras(String totalHoras){
        this.totalHoras.setText(totalHoras);
    }
    
    public String getCategoria(){
        return this.categoria.getText();
    }
    public void setCategoria(String categoria){
        this.categoria.setText(categoria);
    }
    
    
    public String getMatricula(){
        return this.matricula.getText();
    }
    
    public void setMatricula(int matricula){
        this.matricula.setText(""+matricula);
    }
    
    public String getNomeAluno(){
        return this.nomeAluno.getText();
    }
    
    public void setNomeAluno(String nome){
        this.nomeAluno.setText(nome);
    }

    public JComboBox<String> getAtividadeSelecionada() {
        return this.atividadeSelecionada;
    }

    public String getAtividadeEscolhida() {
        return this.atividadeEscolhida;
    }

    public void setAtividadeEscolhida(String atividadeEscolhida) {
        this.atividadeEscolhida = atividadeEscolhida;
    }

    public String getQuantHoraAtividade() {
        return quantHoraAtividade.getText();
    }

    public void setQuantHoraAtividade(String quantHoraAtividade) {
        this.quantHoraAtividade.setText(quantHoraAtividade);
    }

    public JButton getBtnNovaAtividade() {
        return btnNovaAtividade;
    }
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pesquisaAluno = new javax.swing.JTextField();
        btnOkAluno = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAtividadesDoAluno = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        totalHoras = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        btnNovaAtividade = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        quantHoraAtividade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        categoria = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnContabilizar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        matricula = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        nomeAluno = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        atividadeSelecionada = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Contabilizar Atividade");

        jLabel1.setText("Pesquisar Matricula:");

        btnOkAluno.setText("OK");
        btnOkAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkAlunoActionPerformed(evt);
            }
        });

        tblAtividadesDoAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Atividade", "Qtd. Horas", "Categoria", "Limite", "Aproveitado"
            }
        ));
        tblAtividadesDoAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAtividadesDoAlunoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAtividadesDoAluno);
        if (tblAtividadesDoAluno.getColumnModel().getColumnCount() > 0) {
            tblAtividadesDoAluno.getColumnModel().getColumn(1).setPreferredWidth(20);
            tblAtividadesDoAluno.getColumnModel().getColumn(3).setPreferredWidth(6);
            tblAtividadesDoAluno.getColumnModel().getColumn(4).setPreferredWidth(16);
        }

        jLabel2.setText("Total:");

        totalHoras.setEnabled(false);
        totalHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalHorasActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("Pesquisar Atividade");

        btnNovaAtividade.setText("Nova Atividade");
        btnNovaAtividade.setEnabled(false);
        btnNovaAtividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaAtividadeActionPerformed(evt);
            }
        });

        jLabel5.setText("Quant. Horas:");

        quantHoraAtividade.setEnabled(false);

        jLabel6.setText("Categoria:");

        categoria.setEnabled(false);

        btnExcluir.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\Desktop\\Icones\\cancel.png")); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\Desktop\\Icones\\back.png")); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnContabilizar.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\Desktop\\Icones\\calc (2).png")); // NOI18N
        btnContabilizar.setText("Contabilizar");
        btnContabilizar.setEnabled(false);
        btnContabilizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContabilizarActionPerformed(evt);
            }
        });

        jLabel7.setText("Matricula:");

        matricula.setEnabled(false);

        jLabel8.setText("Nome:");

        nomeAluno.setEnabled(false);

        jLabel9.setText("Selecione a Atividade:");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel10.setText("Pesquisar Aluno");

        atividadeSelecionada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                atividadeSelecionadaItemStateChanged(evt);
            }
        });
        atividadeSelecionada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atividadeSelecionadaMouseClicked(evt);
            }
        });
        atividadeSelecionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atividadeSelecionadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pesquisaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnOkAluno))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(totalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnExcluir)
                        .addGap(50, 50, 50)
                        .addComponent(btnCancelar)
                        .addGap(49, 49, 49)
                        .addComponent(btnContabilizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6)
                        .addGap(331, 331, 331)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(atividadeSelecionada, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(quantHoraAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNovaAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(pesquisaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOkAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jLabel9)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(atividadeSelecionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovaAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantHoraAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContabilizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkAlunoActionPerformed
        this.controller.eventoBotaoOkAluno(evt);
        //Habilitar os botões agora
        this.btnNovaAtividade.setEnabled(true);       
    }//GEN-LAST:event_btnOkAlunoActionPerformed

    private void totalHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalHorasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalHorasActionPerformed

    private void btnContabilizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContabilizarActionPerformed
        try {
            this.controller.evento(evt);
        } catch (SQLException ex) {
            Logger.getLogger(FrmContabilizarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmContabilizarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnContabilizarActionPerformed

    private void tblAtividadesDoAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAtividadesDoAlunoMouseClicked
        this.controller.evento(evt);
    }//GEN-LAST:event_tblAtividadesDoAlunoMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        try {
            this.controller.eventoBotao(evt);
        } catch (SQLException ex) {
            Logger.getLogger(FrmContabilizarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmContabilizarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       try {
            this.controller.eventoBotao(evt);
        } catch (SQLException ex) {
            Logger.getLogger(FrmContabilizarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmContabilizarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNovaAtividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaAtividadeActionPerformed
         try {
            this.controller.eventoBotao(evt);
        } catch (SQLException ex) {
            Logger.getLogger(FrmContabilizarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmContabilizarAtividade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNovaAtividadeActionPerformed

    private void atividadeSelecionadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atividadeSelecionadaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_atividadeSelecionadaMouseClicked

    private void atividadeSelecionadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_atividadeSelecionadaItemStateChanged
        try {
            this.controller.eventoCombo(evt);
            this.btnContabilizar.setEnabled(true);
            this.btnExcluir.setEnabled(true);
            this.btnCancelar.setEnabled(true);
        } catch (SQLException ex) {
            this.mostraMensagem("Não foi possível selecionar a atividade");
        } catch (ClassNotFoundException ex) {
            this.mostraMensagem("Não foi possível selecionar a atividade");
        }
    }//GEN-LAST:event_atividadeSelecionadaItemStateChanged

    private void atividadeSelecionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atividadeSelecionadaActionPerformed
        
    }//GEN-LAST:event_atividadeSelecionadaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> atividadeSelecionada;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnContabilizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovaAtividade;
    private javax.swing.JButton btnOkAluno;
    private javax.swing.JTextField categoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField matricula;
    private javax.swing.JTextField nomeAluno;
    private javax.swing.JTextField pesquisaAluno;
    private javax.swing.JTextField quantHoraAtividade;
    private javax.swing.JTable tblAtividadesDoAluno;
    private javax.swing.JTextField totalHoras;
    // End of variables declaration//GEN-END:variables
}
