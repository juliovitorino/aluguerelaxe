
package br.com.jcv.backend.robot;

/**
 * <h2>RobotFactory</h2>
 * <p>Fábrica para criação de Robots</p>
 * <p>Cada robot tem uma ação específica e podem trabalhar de forma colaborativa.</p>
 *
 * @author elmt
 *
 */
public class RobotFactory {
	
	private RobotFactory() {}

	public static Robot getInstanceGenericRobot() {
		return new ExemploRobot();
	}

	/**
	 * Obtém uma instância de Carly para notificar ao potencial cliente que sua
	 * tentativa de cadastro no site teve algum problema e que gostaríamos do
	 * contato com ele para verificar o que aconteceu.
	 *
	 * @return Robot
	 */
	public static Robot getInstanceCarly() {
		return new CarlyRobot();
	}

	/**
	 * Obtém uma instância de Derby para notificar ao administrador sobre os 
	 * contatos com os anunciantes que ainda não tiveram seu conteúdo validado
	 *
	 * @return Robot
	 */
	public static Robot getInstanceDerby() {
		return new DerbyRobot();
	}
	
	/**
	 * Obtém uma instância de Milly para enviar os emails que tiveram seus
	 * conteúdos validados pelo administrador sobre os 
	 * contatos com os anunciantes e encontram-se com status liberado.
	 *
	 * @return Robot
	 */
	public static Robot getInstanceMilly() {
		return new MillyRobot();
	}

	/**
	 * Obtém uma instância de Jessy para enviar os emails que estão com as
	 * contas pendentes de ativação.
	 *
	 * @return Robot
	 */
	public static Robot getInstanceJessy() {
		return new JessyRobot();
	}
}

