package br.com.jcv.backend.workers;

import br.com.jcv.backend.chain.AbstractChainOfResponsability;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.html.properta.PropertaFichaImovelChain;
import br.com.jcv.backend.html.properta.PropertaIndexChainPT;
import br.com.jcv.backend.html.properta.PropertaPaginasEstaticasCofR;
import br.com.jcv.backend.html.properta.PropertaPropriedadesClienteChain;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;

public class GeradorPaginasOfflineProperta extends Robot {

	public static final String ROBOT_NOME = "GERADOR_PAGINAS_PROPERTA";


	public GeradorPaginasOfflineProperta() {
		super(ROBOT_NOME, "GERADOR DE PAGINAS PROPERTA");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando gerador de paginas properta");
		
		AbstractChainOfResponsability<DTOPadrao> cofr = new PropertaPaginasEstaticasCofR();
		cofr.setContext(null);
		cofr.addChain(new PropertaIndexChainPT());
		cofr.addChain(new PropertaFichaImovelChain());
		cofr.addChain(new PropertaPropriedadesClienteChain());
		
		cofr.execute();
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Gerador de paginas properta processado com sucesso.");
	
		
	}

}
