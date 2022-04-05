
package br.com.jcv.backend.robot.client;

import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.robot.RobotFactory;

public class MultiRobot {

	public static void main(String[] args) {
		Robot r1 = RobotFactory.getInstanceGenericRobot();
		Robot r2 = RobotFactory.getInstanceGenericRobot();
		
		GenericRobotThread grt1 = new GenericRobotThread(r1);
		GenericRobotThread grt2 = new GenericRobotThread(r2);
		grt1.start();
		grt2.start();
		
		//r2.setStatus(CicloVidaRobot.ROBOT_ABORTADO);

	}

}

