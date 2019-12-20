package Views;

import Models.Configuracao;
import Models.Funcionario;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author willi
 * @author beatriz oliveira
 */
public class FrmTelaPrincipal extends javax.swing.JFrame {

    private static Configuracao model;

    public FrmTelaPrincipal() {
        initComponents();
        this.setExtendedState(FrmTelaPrincipal.MAXIMIZED_BOTH);
        this.jdpPrincipal.setBackground(Color.white);
    }
    
    public JDesktopPane getJdpPrincipal(){
        return this.jdpPrincipal;
    }

    public FrmTelaPrincipal(Configuracao model) throws ClassNotFoundException, SQLException {
        this();
        this.model = model;
        FrmLogin frmLogin = new FrmLogin(this.model);
        this.jdpPrincipal.add(frmLogin);
        this.colocarFormularioCentro(frmLogin);
        frmLogin.setVisible(true);
    }

    public boolean verificaLogin() {
        if (this.model.getFuncionario() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void colocarFormularioCentro(JInternalFrame frame) {
        Dimension desktopSize = this.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }

    public void mostraMensagem(String mensagem) {
        if (mensagem != null) {
            JOptionPane.showMessageDialog(this, mensagem);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        /*ImageIcon icon = new ImageIcon(getClass().getResource("/Imagem/logo.png"));
        final Image image = icon.getImage();*/
        jdpPrincipal = new javax.swing.JDesktopPane(){
            /*public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
            */
        };
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCadastrar = new javax.swing.JMenu();
        itemCadastrarFuncionario = new javax.swing.JMenuItem();
        itemCadastrarAluno = new javax.swing.JMenuItem();
        itemCadastrarCategoria = new javax.swing.JMenuItem();
        itemCadastrarAtividade = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemPesquisarUsuario = new javax.swing.JMenuItem();
        itemPesquisarAluno = new javax.swing.JMenuItem();
        itemPesquisarCategoria = new javax.swing.JMenuItem();
        itemPesquisarAtividade = new javax.swing.JMenuItem();
        menuContabilizarAtividade = new javax.swing.JMenu();
        menuRelatorio = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();

        jMenuItem7.setText("jMenuItem7");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        jMenuItem6.setText("jMenuItem6");

        jMenuItem10.setText("jMenuItem10");

        jMenuItem11.setText("jMenuItem11");

        jMenuItem8.setText("jMenuItem8");

        jMenuItem12.setText("jMenuItem12");

        jMenuItem13.setText("jMenuItem13");

        jMenuItem14.setText("jMenuItem14");

        jMenuItem17.setText("jMenuItem17");

        jMenuItem18.setText("jMenuItem18");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jdpPrincipal.setPreferredSize(new java.awt.Dimension(680, 445));

        javax.swing.GroupLayout jdpPrincipalLayout = new javax.swing.GroupLayout(jdpPrincipal);
        jdpPrincipal.setLayout(jdpPrincipalLayout);
        jdpPrincipalLayout.setHorizontalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        jdpPrincipalLayout.setVerticalGroup(
            jdpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        menuCadastrar.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Imagem\\user-add3.png")); // NOI18N
        menuCadastrar.setText("Cadastrar");
        menuCadastrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuCadastrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        itemCadastrarFuncionario.setText("Usuário");
        itemCadastrarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCadastrarFuncionarioActionPerformed(evt);
            }
        });
        menuCadastrar.add(itemCadastrarFuncionario);

        itemCadastrarAluno.setText("Aluno");
        itemCadastrarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCadastrarAlunoActionPerformed(evt);
            }
        });
        menuCadastrar.add(itemCadastrarAluno);

        itemCadastrarCategoria.setText("Categoria");
        itemCadastrarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCadastrarCategoriaActionPerformed(evt);
            }
        });
        menuCadastrar.add(itemCadastrarCategoria);

        itemCadastrarAtividade.setText("Atividade");
        itemCadastrarAtividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCadastrarAtividadeActionPerformed(evt);
            }
        });
        menuCadastrar.add(itemCadastrarAtividade);

        jMenuBar1.add(menuCadastrar);

        jMenu2.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Imagem\\search.png")); // NOI18N
        jMenu2.setText("Pesquisar");
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        itemPesquisarUsuario.setText("Usuário");
        itemPesquisarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPesquisarUsuarioActionPerformed(evt);
            }
        });
        jMenu2.add(itemPesquisarUsuario);

        itemPesquisarAluno.setText("Aluno");
        itemPesquisarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPesquisarAlunoActionPerformed(evt);
            }
        });
        jMenu2.add(itemPesquisarAluno);

        itemPesquisarCategoria.setText("Categoria");
        itemPesquisarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPesquisarCategoriaActionPerformed(evt);
            }
        });
        jMenu2.add(itemPesquisarCategoria);

        itemPesquisarAtividade.setText("Atividade");
        itemPesquisarAtividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPesquisarAtividadeActionPerformed(evt);
            }
        });
        jMenu2.add(itemPesquisarAtividade);

        jMenuBar1.add(jMenu2);

        menuContabilizarAtividade.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Imagem\\budget.png")); // NOI18N
        menuContabilizarAtividade.setText("Cont. Atividade");
        menuContabilizarAtividade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuContabilizarAtividade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuContabilizarAtividade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuContabilizarAtividadeMouseClicked(evt);
            }
        });
        menuContabilizarAtividade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuContabilizarAtividadeActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuContabilizarAtividade);

        menuRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Imagem\\report.png")); // NOI18N
        menuRelatorio.setText("Relatorio");
        menuRelatorio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuRelatorio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuRelatorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuRelatorioMouseClicked(evt);
            }
        });
        menuRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRelatorioActionPerformed(evt);
            }
        });
        jMenuBar1.add(menuRelatorio);

        jMenu1.setIcon(new javax.swing.ImageIcon("C:\\Users\\willi\\OneDrive\\Documentos\\NetBeansProjects\\GerenciarAtividadeComplementar-master\\src\\Imagem\\inbox.png")); // NOI18N
        jMenu1.setText("Baixar Emails");
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jMenu1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        setBounds(0, 0, 719, 551);
    }// </editor-fold>//GEN-END:initComponents

    private void itemCadastrarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCadastrarFuncionarioActionPerformed
        this.jdpPrincipal.removeAll();
        if (this.verificaLogin()) {
            FrmFuncionarioCadastro frmFuncionarioCadastro = new FrmFuncionarioCadastro(this.model, this);
            this.jdpPrincipal.add(frmFuncionarioCadastro);
            this.colocarFormularioCentro(frmFuncionarioCadastro);
            frmFuncionarioCadastro.setVisible(true);
        } else {
           /* FrmLogin frmLogin = new FrmLogin(this.model);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
        */
           this.mostraMensagem("Usuário não está logado no sistema!");
        }
    }//GEN-LAST:event_itemCadastrarFuncionarioActionPerformed

    private void itemCadastrarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCadastrarAlunoActionPerformed
        this.jdpPrincipal.removeAll();
        if (this.verificaLogin()) {
            FrmAlunoCadastro frmAlunoCadastro = new FrmAlunoCadastro(this.model, this);
            this.jdpPrincipal.add(frmAlunoCadastro);
            this.colocarFormularioCentro(frmAlunoCadastro);
            frmAlunoCadastro.setVisible(true);
        } else {
            /*FrmLogin frmLogin = new FrmLogin(this.model);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
*/       this.mostraMensagem("Usuário não está logado no sistema!");   
        }
    }//GEN-LAST:event_itemCadastrarAlunoActionPerformed

    private void itemCadastrarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCadastrarCategoriaActionPerformed
        this.jdpPrincipal.removeAll();
        FrmCategoriaCadastro frmCategoriaCadastro = new FrmCategoriaCadastro(this.model, this);
        if (this.verificaLogin()) {
            this.jdpPrincipal.add(frmCategoriaCadastro);
            this.colocarFormularioCentro(frmCategoriaCadastro);
            frmCategoriaCadastro.setVisible(true);
        } else {
  /*          FrmLogin frmLogin = new FrmLogin(this.model);
            frmLogin.setTelaAAcessar(frmCategoriaCadastro);
            frmLogin.setTelaPrincipal(this);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
            
    */  this.mostraMensagem("Usuário não está logado no sistema!");  
        }
    }//GEN-LAST:event_itemCadastrarCategoriaActionPerformed

    private void itemPesquisarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPesquisarUsuarioActionPerformed
        this.jdpPrincipal.removeAll();
        if (this.verificaLogin()) {
            FrmFuncionarioPesquisa frmFuncionarioPesquisa = new FrmFuncionarioPesquisa(this.model);
            this.jdpPrincipal.add(frmFuncionarioPesquisa);
            this.colocarFormularioCentro(frmFuncionarioPesquisa);
            frmFuncionarioPesquisa.setVisible(true);
        } else {
      /*      FrmLogin frmLogin = new FrmLogin(this.model);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
        */this.mostraMensagem("Usuário não está logado no sistema!");
        }
    }//GEN-LAST:event_itemPesquisarUsuarioActionPerformed

    private void itemPesquisarAtividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPesquisarAtividadeActionPerformed
        this.jdpPrincipal.removeAll();
            FrmAtividadePesquisa frmAtividadePesquisa = new FrmAtividadePesquisa(this.model);
        if (this.verificaLogin()) {
            this.jdpPrincipal.add(frmAtividadePesquisa);
            this.colocarFormularioCentro(frmAtividadePesquisa);
            frmAtividadePesquisa.setVisible(true);
        } else {
          /*  FrmLogin frmLogin = new FrmLogin(this.model);
            frmLogin.setTelaAAcessar(frmAtividadePesquisa);
            frmLogin.setTelaPrincipal(this);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
*/      this.mostraMensagem("Usuário não está logado no sistema!");
        }
    }//GEN-LAST:event_itemPesquisarAtividadeActionPerformed

    private void itemCadastrarAtividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCadastrarAtividadeActionPerformed
        this.jdpPrincipal.removeAll();
        FrmAtividadeCadastro frmAtividadeCadastro = new FrmAtividadeCadastro(this.model);
        if (this.verificaLogin()) {
            this.jdpPrincipal.add(frmAtividadeCadastro);
            this.colocarFormularioCentro(frmAtividadeCadastro);
            frmAtividadeCadastro.setVisible(true);
        } else {
  /*          FrmLogin frmLogin = new FrmLogin(this.model);
            frmLogin.setTelaAAcessar(frmAtividadeCadastro);
            frmLogin.setTelaPrincipal(this);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
    */  this.mostraMensagem("Usuário não está logado no sistema!");  
        }
    }//GEN-LAST:event_itemCadastrarAtividadeActionPerformed

    private void itemPesquisarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPesquisarAlunoActionPerformed
        this.jdpPrincipal.removeAll();
        if (this.verificaLogin()) {
            FrmAlunoPesquisa frmAlunoPesquisa = new FrmAlunoPesquisa(this.model);
            this.jdpPrincipal.add(frmAlunoPesquisa);
            this.colocarFormularioCentro(frmAlunoPesquisa);
            frmAlunoPesquisa.setVisible(true);
        } else {
      /*      FrmLogin frmLogin = new FrmLogin(this.model);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
*/          this.mostraMensagem("Usuário não está logado no sistema!");
        }
    }//GEN-LAST:event_itemPesquisarAlunoActionPerformed

    private void itemPesquisarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPesquisarCategoriaActionPerformed
        this.jdpPrincipal.removeAll();
        FrmCategoriaPesquisa frmCategoriaPesquisa = new FrmCategoriaPesquisa(this.model);
        if (this.verificaLogin()) {
            this.jdpPrincipal.add(frmCategoriaPesquisa);
            this.colocarFormularioCentro(frmCategoriaPesquisa);
            frmCategoriaPesquisa.setVisible(true);
        } else {
  /*          FrmLogin frmLogin = new FrmLogin(this.model);
            frmLogin.setTelaAAcessar(frmCategoriaPesquisa);
            frmLogin.setTelaPrincipal(this);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
*/          this.mostraMensagem("Usuário não está logado no sistema!");
        }
    }//GEN-LAST:event_itemPesquisarCategoriaActionPerformed

    private void menuContabilizarAtividadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuContabilizarAtividadeActionPerformed
        this.jdpPrincipal.removeAll();
        if (this.verificaLogin()) {
            FrmContabilizarAtividade frmContabilizarAtividade = new FrmContabilizarAtividade(this, this.model);
            this.jdpPrincipal.add(frmContabilizarAtividade);
            this.colocarFormularioCentro(frmContabilizarAtividade);
            frmContabilizarAtividade.setVisible(true);
        } else {
  /*          FrmLogin frmLogin = new FrmLogin(this.model);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
*/          this.mostraMensagem("Usuário não está logado no sistema!");
        }
    }//GEN-LAST:event_menuContabilizarAtividadeActionPerformed

    private void menuRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRelatorioActionPerformed
        this.jdpPrincipal.removeAll();
        if (this.verificaLogin()) {
            FrmRelatorio frmRelatorio = new FrmRelatorio(this.model);
            this.jdpPrincipal.add(frmRelatorio);
            this.colocarFormularioCentro(frmRelatorio);
            frmRelatorio.setVisible(true);
        } else {
  /*          FrmLogin frmLogin = new FrmLogin(this.model);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
*/          this.mostraMensagem("Usuário não está logado no sistema!");
        }
    }//GEN-LAST:event_menuRelatorioActionPerformed

    private void menuContabilizarAtividadeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuContabilizarAtividadeMouseClicked
        this.jdpPrincipal.removeAll();
        if (this.verificaLogin()) {
            FrmContabilizarAtividade frmContabilizarAtividade = new FrmContabilizarAtividade(this, this.model);
            this.jdpPrincipal.add(frmContabilizarAtividade);
            this.colocarFormularioCentro(frmContabilizarAtividade);
            frmContabilizarAtividade.setVisible(true);
        } else {
  /*          FrmLogin frmLogin = new FrmLogin(this.model);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
*/
        }
    }//GEN-LAST:event_menuContabilizarAtividadeMouseClicked

    private void menuRelatorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuRelatorioMouseClicked

        this.jdpPrincipal.removeAll();
        if (this.verificaLogin()) {
            FrmRelatorio frmRelatorio = new FrmRelatorio(this.model);
            this.jdpPrincipal.add(frmRelatorio);
            this.colocarFormularioCentro(frmRelatorio);
            frmRelatorio.setVisible(true);
        } else {
  /*          FrmLogin frmLogin = new FrmLogin(this.model);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
*/
        }

    }//GEN-LAST:event_menuRelatorioMouseClicked

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        
        this.jdpPrincipal.removeAll();
        if (this.verificaLogin()) {
            FrmRelatorio frmRelatorio = new FrmRelatorio(this.model);
            
            frmRelatorio.receberMensagens();
            
            //this.jdpPrincipal.add(frmRelatorio);
            //this.colocarFormularioCentro(frmRelatorio);
            //frmRelatorio.setVisible(true);
        } else {
  /*          FrmLogin frmLogin = new FrmLogin(this.model);
            this.jdpPrincipal.add(frmLogin);
            this.colocarFormularioCentro(frmLogin);
            frmLogin.setVisible(true);
*/
        }
        
    }//GEN-LAST:event_jMenu1MouseClicked

    public void iniciaFormulario() {
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmTelaPrincipal().setVisible(true);
            }
        });*/
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmTelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmTelaPrincipal(new Configuracao()).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrmTelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FrmTelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemCadastrarAluno;
    private javax.swing.JMenuItem itemCadastrarAtividade;
    private javax.swing.JMenuItem itemCadastrarCategoria;
    private javax.swing.JMenuItem itemCadastrarFuncionario;
    private javax.swing.JMenuItem itemPesquisarAluno;
    private javax.swing.JMenuItem itemPesquisarAtividade;
    private javax.swing.JMenuItem itemPesquisarCategoria;
    private javax.swing.JMenuItem itemPesquisarUsuario;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JMenu menuCadastrar;
    private javax.swing.JMenu menuContabilizarAtividade;
    private javax.swing.JMenu menuRelatorio;
    // End of variables declaration//GEN-END:variables
}
