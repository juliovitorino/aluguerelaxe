package br.com.jcv.backend.workers.main;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.HibernateSessionFactory;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;
import br.com.jcv.backend.workers.GerenciadorOferecimento;

public class ClientGerenciadorOferecimento {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HibernateSessionFactory.getSessionFactory(); 
		try {
			Boolean indCentralReserva = (VariavelCache.getInstance().getValor(VariavelConstantes.CENTRAL_RESERVAS_ATIVO).equals(Constantes.ON));
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Robot sppp = new GerenciadorOferecimento();
		sppp.executar();
	}

}
