package br.com.jcv.backend.sorteio;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.robot.Robot;

public interface ISorteio {
	void executar() throws AlugueRelaxeException;
	void setRobot(Robot robot);
	Robot getRobot();
	void setSorteados(int n);
	int getSorteados();
}