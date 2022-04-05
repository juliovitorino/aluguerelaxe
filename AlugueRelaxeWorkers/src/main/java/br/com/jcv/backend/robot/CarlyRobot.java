
package br.com.jcv.backend.robot;

/**
 * <h2>SallyRobot</h2>
 *<p>Robot verificadora de cadastros de clientes n�o ativados</p>
 * @author elmt
 *
 */
public class CarlyRobot extends Robot {

	public CarlyRobot() {
		super("Carly");
		this.setFuncao("Verificadora de contas de clientes n�o ativadas");
	}

	public void executar() {
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO);
		
		// Solicita ao ImovelService a lista de im�veis sem imagens
		
		
		// Itera a lista e enviando e-mail ao anunciante
		
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO);
	}

}

