package academia;

import academia.control.Conect;
import academia.model.Pessoa;
import academia.view.TelaFuncionairo;
import academia.view.TelaLogin;

/**
 *
 * @author vinicius
 */
public class Academia {

    public static Pessoa pessoaLogada;
    
    public static void main(String[] args) {
        Conect c = new Conect();
        c.start();
//        new TelaFuncionairo();
        new TelaLogin();
    }

}
