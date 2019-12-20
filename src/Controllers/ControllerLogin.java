package Controllers;

import Models.Configuracao;
import Models.InterfaceObserver;
import Views.FrmLogin;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JButton;

/**
 *
 * @author willi
 */
public class ControllerLogin implements InterfaceObserver {

    private Configuracao model;
    private FrmLogin view;

    public ControllerLogin(FrmLogin view, Configuracao model) {
        this.model = model;
        this.view = view;
    }

    public void evento(ActionEvent evt) {
        if ((evt.getSource() instanceof JButton)) {
            this.eventoBotao(evt);
        }

        this.model.avisarObservers();
    }

    private void eventoBotao(ActionEvent evt) {
        if (((JButton) evt.getSource()).getText().equals("Entrar")) {
            if (this.view.validaCampos()) {
                try {
                    this.model.validaFuncionario(this.view.getUsuario(), this.view.getSenha());
                    if (this.model.getFuncionario() != null) {
                        if (this.model.getFuncionario().getId() == 0) {
                            this.view.mensagem("Usuário ou senha não encontrados.");
                            return; // caso o usuario ou senha não forem encontrados não faça mais nada apenas saia do metodo
                        }
                        if (this.view.getTelaAAcessar() != null) {
                            this.view.getTelaPrincipal().getJdpPrincipal().add(this.view.getTelaAAcessar());
                            this.view.getTelaPrincipal().colocarFormularioCentro(this.view.getTelaAAcessar());
                            this.view.getTelaAAcessar().setVisible(true);
                        }
                        
                        this.view.setTelaAAcessar(null);
                        this.view.setTelaPrincipal(null);
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    this.view.mensagem("Não foi possível realizar a validação de usuário. Mensagem retornada: " + ex.getMessage());
                }
            }
        }
        if (((JButton) evt.getSource()).getText().equals("Sair")) {
            this.view.fechaTela();
        }
    }

    @Override
    public void alterar() {
        if (this.model.getFuncionario() != null) {
            if (this.model.getFuncionario().getId() > 0) {
                this.view.fechaTela();
            } else {
                this.model.setFuncionario(null);
            }
        }
    }

}
