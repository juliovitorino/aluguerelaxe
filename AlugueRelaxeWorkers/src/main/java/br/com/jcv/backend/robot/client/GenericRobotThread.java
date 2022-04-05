
package br.com.jcv.backend.robot.client;

import br.com.jcv.backend.robot.Robot;

public class GenericRobotThread extends Thread {
	
	Robot robo = null;
	
	public GenericRobotThread(Robot robo) {
		this.robo = robo;
	}
	
	public void run() {
		robo.executar();
	}

}

