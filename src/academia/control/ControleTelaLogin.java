package academia.control;

import academia.HibernateUtil;
import academia.model.Pessoa;
import academia.view.TelaAluno;
import academia.view.TelaFuncionairo;
import academia.view.TelaInstrutor;
import academia.view.TelaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vinicius
 */
public class ControleTelaLogin implements ActionListener {

    private TelaLogin telaLogin;

    private int tipoTela = 0;

    public ControleTelaLogin(TelaLogin tela) {
        this.telaLogin = tela;
    }
   
    public void Logar(String cpf, String senha) {
        
        Criteria crit = HibernateUtil.getSessionFactory().openSession().createCriteria(Pessoa.class);
        crit.add(Restrictions.eq("cpf", cpf));
        Pessoa p = Pessoa.pessoaDAO().getEntityByCriteria(crit);

        if (p == null) {
            JOptionPane.showMessageDialog(telaLogin, "CPf ou SENHA incorreto");
        } else if (p.getSenha().equals(senha)) {
            tela(p.getTipo());
        } else {
            JOptionPane.showMessageDialog(telaLogin, "CPf ou SENHA incorreto");
        }
        
    }

    public void tela(int tipo) {

        switch (tipo) {
            case 1:
                new TelaAluno();
                telaLogin.setVisible(false);
                break;
            case 2:
                new TelaFuncionairo();
                telaLogin.setVisible(false);
                break;
            case 3:
                new TelaInstrutor();
                telaLogin.setVisible(false);
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == telaLogin.getLimparButton()) {
            telaLogin.setLoginField(null);
            telaLogin.setSenhaField(null);
        }

        if (e.getSource() == telaLogin.getSairButton()) {
            System.exit(0);
        }

        if (e.getSource() == telaLogin.getLogarButton()) {
           Logar(telaLogin.getLoginField(), telaLogin.getSenhaField());
        }

    }

}