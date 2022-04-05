package br.com.jcv.backend.workers.main;

import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.workers.SorteadorFillerGapPP;

public class ClientSorteadorFillerGapPP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Robot sppp = new SorteadorFillerGapPP();
		sppp.executar();
	}

}
