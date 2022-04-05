package br.com.jcv.backend.workers.main;

import br.com.jcv.backend.factory.HibernateSessionFactory;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.workers.AtualizadorColaboradorInconsistente;

public class ClientAtualizadorColaboradorInconsistente {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HibernateSessionFactory.getSessionFactory();     
		Robot sppp = new AtualizadorColaboradorInconsistente();
		sppp.executar();
	}

}
