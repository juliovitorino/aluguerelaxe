
package br.com.jcv.backend.robot;

public class ExemploRobot extends Robot {

	public ExemploRobot() {
		super("Geny");
	}
	
	public void executar() {
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO);
		for (int i = 0; i < 1000; i++){
			if (this.getStatus().equals(CicloVidaRobot.ROBOT_ABORTADO)) {
				System.out.println(this.getNome() + " :: ABORTADO!");
				break;
			}
			System.out.println(this.getNome() + " - " + i);
		}
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO);
	}

}

