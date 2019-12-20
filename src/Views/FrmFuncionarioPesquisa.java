/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ControllerFuncionarioPesquisa;
import Models.Configuracao;
import Models.Funcionario;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author willi
 */
public class FrmFuncionarioPesquisa extends javax.swing.JInternalFrame {

    private Configuracao model;
    private ControllerFuncionarioPesquisa controller;
    private FrmTelaPrincipal principal;

    
    public FrmFuncionarioPesquisa() {
        initComponents();
    }

  
    public FrmFuncionarioPesquisa(Configuracao model){
        this();
        this.model = model;
        this.controller = new ControllerFuncionarioPesquisa(this, model);
        this.principal = principal;
    }
    
    public void mostraMensagem(String mensagem) {
        if (mensagem != null) {
            JOptionPane.showMessageDialog(this, mensagem);
        }
    }
     
    public boolean validaCampos() {
        
        if(this.pesquisaFuncionario.getText().trim().equals("")){
            this.mostraMensagem("Informe o nome do usuário, ou digite 'todos'para retornar todos os usuários. ");
            this.pesquisaFuncionario.requestFocus();
            return false;
        }
         
        if (this.nome.getText().trim().equals("")) {
            this.mostraMensagem("Informe o nome do usuário.");
            this.nome.requestFocus();
            return false;
        }
        
        if (this.contato.getText().trim().equals("")) {
            this.mostraMensagem("Informe o contato do usuário.");
            this.contato.requestFocus();
            return false;
        }
        
        if (this.login.getText().trim().equals("")) {
            this.mostraMensagem("Informe o login do usuário.");
            this.login.requestFocus();
            return false;
        }
        
        if (this.senha.getText().trim().equals("")) {
            this.mostraMensagem("Informe a senha do usuário.");
            this.senha.requestFocus();
            return false;
        }
        
        if (this.novaSenha.getText().trim().equals("")) {
            this.mostraMensagem("Informe a senha do usuário de novo, para confirmar.");
            this.novaSenha.requestFocus();
            return false;
        }
                
        return true;
    }

    public void limpaTableFuncionarios() {
        DefaultTableModel tabela = (DefaultTableModel) this.tableFuncionario.getModel();
        tabela.setNumRows(0);
    }

    public void preencheCamposAlteracao(Funcionario funcionario) {
        if (funcionario != null) {
            this.id.setText(String.valueOf(funcionario.getId()));
            this.contato.setText(funcionario.getContato());
            this.login.setText(funcionario.getLogin());
            this.nome.setText(funcionario.getNome());
            this.senha.setText(funcionario.getSenha());
            this.btnSalvar.setEnabled(true);
            this.btnExcluir.setEnabled(true);
            this.btnCancelar.setEnabled(true);
            
        }
    }
    
    public void limpaCampos() {
        this.id.setText("");
        this.novaSenha.setText("");
        this.contato.setText("");
        this.login.setText("");
        this.nome.setText("");
        this.pesquisaFuncionario.setText("");
        this.senha.setText("");

       
    }
    
     public void fechaTela() {
        this.dispose();
    }

    public FrmTelaPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(FrmTelaPrincipal principal) {
        this.principal = principal;
    }
    
    public String getContato() {
        return this.contato.getText().trim();
    }

    public String getNome() {
        return this.nome.getText().trim();
    }

    public String getLogin() {
        return this.login.getText().trim();
    }

    public String getSenha() {
        return this.senha.getText().trim();
    }
    
    public String getId(){
        return this.id.getText().trim();
    }
    
    public String getPesquisa(){
        return this.pesquisaFuncionario.getText().trim();
    }
    
    public JTable getTableFuncionario(){
        return this.tableFuncionario;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        txtPesquisarUsuario = new javax.swing.JLabel();
        pesquisaFuncionario = new javax.swing.JTextField();
        BtnOk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableFuncionario = new javax.swing.JTable();
        txtLogin = new javax.swing.JLabel();
        login = new javax.swing.JTextField();
        txtSenha = new javax.swing.JLabel();
        txtNovaSenha = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        labelId = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        txtNome = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        novaSenha = new javax.swing.JPasswordField();
        txtContato = new javax.swing.JLabel();
        contato = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        senha = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuário");

        titulo.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        titulo.setText("Pesquisar Usuário");

        txtPesquisarUsuario.setText("Pesquisar:");

        pesquisaFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisaFuncionarioActionPerformed(evt);
            }
        });

        BtnOk.setText("OK");
        BtnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOkActionPerformed(evt);
            }
        });

        tableFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Contato"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableFuncionario.setColumnSelectionAllowed(true);
        tableFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableFuncionarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableFuncionario);
        tableFuncionario.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        txtLogin.setText("Login:");

        login.setEnabled(false);

        txtSenha.setText("Senha:");

        txtNovaSenha.setText("Nova Senha:");

        btnSalvar.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\Desktop\\Icones\\accept.png")); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        labelId.setText("Id:");

        btnCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\Desktop\\Icones\\back.png")); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\Desktop\\Icones\\cancel.png")); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        txtNome.setText("Nome:");

        nome.setEnabled(false);

        novaSenha.setEnabled(false);
        novaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novaSenhaActionPerformed(evt);
            }
        });

        txtContato.setText("Contato:");

        contato.setEnabled(false);

        id.setEnabled(false);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(109, 109, 109)
                            .addComponent(titulo))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(labelId)
                            .addGap(64, 64, 64)
                            .addComponent(txtNome))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(txtLogin)
                            .addGap(179, 179, 179)
                            .addComponent(txtContato))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(btnExcluir)
                            .addGap(31, 31, 31)
                            .addComponent(btnCancelar)
                            .addGap(29, 29, 29)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSenha))
                                .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNovaSenha)
                                .addComponent(novaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(contato, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPesquisarUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pesquisaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnOk)))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtPesquisarUsuario))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pesquisaFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelId)
                    .addComponent(txtNome))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLogin)
                    .addComponent(txtContato))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha)
                    .addComponent(txtNovaSenha))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(novaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSalvar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisaFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisaFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisaFuncionarioActionPerformed

    private void BtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOkActionPerformed
        this.controller.evento(evt);
        this.id.setEnabled(true);
        this.nome.setEnabled(true);
        this.login.setEnabled(true);
        this.contato.setEnabled(true);
        this.senha.setEnabled(true);
        this.novaSenha.setEnabled(true);
    }//GEN-LAST:event_BtnOkActionPerformed

    private void tableFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableFuncionarioMouseClicked
        this.controller.evento(evt);
    }//GEN-LAST:event_tableFuncionarioMouseClicked

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        this.controller.evento(evt);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.controller.evento(evt);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
       this.controller.evento(evt);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void novaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novaSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_novaSenhaActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnOk;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField contato;
    private javax.swing.JTextField id;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelId;
    private javax.swing.JTextField login;
    private javax.swing.JTextField nome;
    private javax.swing.JPasswordField novaSenha;
    private javax.swing.JTextField pesquisaFuncionario;
    private javax.swing.JPasswordField senha;
    private javax.swing.JTable tableFuncionario;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel txtContato;
    private javax.swing.JLabel txtLogin;
    private javax.swing.JLabel txtNome;
    private javax.swing.JLabel txtNovaSenha;
    private javax.swing.JLabel txtPesquisarUsuario;
    private javax.swing.JLabel txtSenha;
    // End of variables declaration//GEN-END:variables
}
