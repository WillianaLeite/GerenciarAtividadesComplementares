package Views;

import Controllers.ControllerRelatorio;
import Models.Aluno;
import Models.Categoria;
import Models.Configuracao;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author willi
 */
public class FrmRelatorio extends javax.swing.JInternalFrame {

    private Configuracao model;
    private ControllerRelatorio controller;
    private String categoriaEscolhido;
    
    public FrmRelatorio() {
        initComponents();
    }

     public FrmRelatorio(Configuracao model){
        this();
        this.model = model;
        this.controller = new ControllerRelatorio(this, model);
        this.controller.preencherCamposCombo();
    }
    
     public void mostraMensagem(String mensagem) {
        if (mensagem != null) {
            JOptionPane.showMessageDialog(this, mensagem);
        }
    }
     
    public void receberMensagens(){
        
        this.controller.evento();
    }
    
    public void fechaTela() {
        this.dispose();
    }
    
    public void limpaCampos() {
        this.matricula.setText("");
        this.nome.setText("");
        this.curso.setText("");
    }
    
    
    public boolean validaCampos() {
    
        if(this.matricula.getText().trim().equals("") || this.matricula.getText().trim().equals("0")){
            this.mostraMensagem("Informe a matricula correta do aluno. ");
            this.matricula.requestFocus();
            return false;
        }
        
        return true;
    }
    
    public void preencheCamposAlteracao(Aluno aluno) {
        
        if (aluno != null) {
            this.nome.setText(aluno.getNome());
            this.curso.setText(aluno.getCurso().getNome());
            this.btnGerar.setEnabled(true);
            this.btnCancelar.setEnabled(true);
        }
        
    }
    
    public String getNome() {
        return nome.getText();
    }
    
    public String getEmail(){
        return this.emailtxt.getText();
    }
    
    public void setEmail(String email){
        this.emailtxt.setText(email);
    }

    public void setNome(String nome) {
        this.nome.setText(nome);
    }

    
    public JComboBox getCategoriaSelecionada() {
        return this.categoriaSelecionada;
    }

    public String getCategoriaEscolhido() {
        return this.categoriaEscolhido;
    }

    public void setCategoriaEscolhido(String cursoEscolhido) {
        this.categoriaEscolhido = cursoEscolhido;
    }
    /*public JTextField getNomeCategoria(){
        return this.categoriaSelecionado;
    }*/
    
    public String getMatricula(){
        return this.matricula.getText();
    }
    
    public void setMatricula(String matricula){
        this.matricula.setText(matricula);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        matricula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        curso = new javax.swing.JTextField();
        btnGerar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        categoriaSelecionada = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        escolheEnviar = new javax.swing.JCheckBox();
        emailtxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        enviar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Relatório");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("Gerar Relatório");

        jLabel2.setText("Matrícula:");

        jLabel3.setText("Nome:");

        nome.setEnabled(false);

        jLabel4.setText("Curso:");

        curso.setEnabled(false);

        btnGerar.setText("Gerar PDF");
        btnGerar.setEnabled(false);
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo:");

        categoriaSelecionada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                categoriaSelecionadaItemStateChanged(evt);
            }
        });
        categoriaSelecionada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaSelecionadaActionPerformed(evt);
            }
        });

        escolheEnviar.setText("Enviar por email");
        escolheEnviar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                escolheEnviarItemStateChanged(evt);
            }
        });
        escolheEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escolheEnviarActionPerformed(evt);
            }
        });

        emailtxt.setEnabled(false);

        jLabel6.setText("Digite email de destino:");

        enviar.setText("Enviar");
        enviar.setEnabled(false);
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(enviar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(escolheEnviar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(categoriaSelecionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGerar))
                            .addComponent(nome)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(curso)
                            .addComponent(jSeparator1)
                            .addComponent(emailtxt))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(matricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(categoriaSelecionada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGerar))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(escolheEnviar)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(7, 7, 7)
                .addComponent(emailtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(enviar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        try {
            this.controller.eventoBotao(evt);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FrmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FrmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGerarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            this.controller.eventoBotao(evt);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FrmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FrmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
       try {
            this.controller.eventoBotao(evt);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FrmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FrmRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void categoriaSelecionadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaSelecionadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaSelecionadaActionPerformed

    private void categoriaSelecionadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_categoriaSelecionadaItemStateChanged
        
        try {
            this.controller.evento(evt);
        } catch (SQLException ex) {
            this.mostraMensagem("Não foi possível selecionar o curso");
        } catch (ClassNotFoundException ex) {
            this.mostraMensagem("Não foi possível selecionar o curso");
        }
        
    }//GEN-LAST:event_categoriaSelecionadaItemStateChanged

    private void escolheEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escolheEnviarActionPerformed
        
    }//GEN-LAST:event_escolheEnviarActionPerformed

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        if(this.getEmail() != null){
            this.controller.evento(this.getEmail());
        }
        
    }//GEN-LAST:event_enviarActionPerformed

    private void escolheEnviarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_escolheEnviarItemStateChanged
        if(this.escolheEnviar.isSelected()){
            this.emailtxt.setEnabled(true);
            this.enviar.setEnabled(true);
        }
    }//GEN-LAST:event_escolheEnviarItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGerar;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<String> categoriaSelecionada;
    private javax.swing.JTextField curso;
    private javax.swing.JTextField emailtxt;
    private javax.swing.JButton enviar;
    private javax.swing.JCheckBox escolheEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField matricula;
    private javax.swing.JTextField nome;
    // End of variables declaration//GEN-END:variables
}
