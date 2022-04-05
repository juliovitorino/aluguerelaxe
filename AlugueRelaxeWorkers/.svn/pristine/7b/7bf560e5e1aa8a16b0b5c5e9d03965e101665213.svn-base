package br.com.jcv.backend.workers.main;

import br.com.jcv.backend.factory.HibernateSessionFactory;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.workers.Spammer;

public class ClientSpammer{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Verifica os parametros
		if (args.length == 0){
			System.out.println("Sintaxe:");
			System.out.println("ClienteSpammer <path do XML de configuracao>");
			System.exit(1);
		}
		
        HibernateSessionFactory.getSessionFactory();   

        Robot robot = new Spammer();
        robot.addParametros(Spammer.CHAVE_ARQUIVO, args[0]);
        robot.executar();
        
	}

}


