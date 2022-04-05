package br.com.jcv.backend.workers;

import br.com.jcv.backend.atalho.AtalhoFichaImovelChain;
import br.com.jcv.backend.atalho.AtalhoFichaImovelCofR;
import br.com.jcv.backend.chain.AbstractChainOfResponsability;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;

public class GeradorAtalhosFichaImovel extends Robot {

	public static final String ROBOT_NOME = "GERADOR_ATALHOS_FICHA_IMOVEL";


	public GeradorAtalhosFichaImovel() {
		super(ROBOT_NOME, "GERADOR DE ATALHOS FICHA IMOVEL");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando gerador de atalhos");
		
		AbstractChainOfResponsability<DTOPadrao> cofr = new AtalhoFichaImovelCofR();
		cofr.setContext(null);
		cofr.addChain(new AtalhoFichaImovelChain());
		
		cofr.execute();
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Gerador de atalhos processado com sucesso.");
	
		
	}

}
