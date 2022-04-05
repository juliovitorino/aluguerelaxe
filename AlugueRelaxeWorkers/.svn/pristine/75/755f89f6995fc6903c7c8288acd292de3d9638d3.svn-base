package br.com.jcv.backend.workers;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.sorteio.ISorteio;
import br.com.jcv.backend.sorteio.SorteioPublicidadePP;

public class SorteadorPublicidadePP extends Robot {

	public static final String ROBOT_NOME = "SORTEADOR_PP";

	public SorteadorPublicidadePP() {
		super(ROBOT_NOME, "Sorteador de publicidades tipo PP");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Preparando para iniciar sorteio");
		ImovelService is = new ImovelServiceImpl();
		try {
		
			ISorteio spd = new SorteioPublicidadePP();
			spd.setRobot(this);
			spd.executar();
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Sorteio terminado com sucesso." + spd.getSorteados() + " imoveis sorteados.");


		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
