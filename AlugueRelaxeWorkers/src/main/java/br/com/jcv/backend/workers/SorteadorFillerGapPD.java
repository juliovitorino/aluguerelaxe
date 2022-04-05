package br.com.jcv.backend.workers;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.sorteio.ISorteio;
import br.com.jcv.backend.sorteio.SorteioPublicidadePD;

public class SorteadorFillerGapPD extends Robot {

	public static final String ROBOT_NOME = "SORTEADOR_FILLER_GAP_PD";

	public SorteadorFillerGapPD() {
		super(ROBOT_NOME, "Sorteador Para Filler Gap PD");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO, "Preparando para iniciar Sorteador Para Filler Gap PD");
		//ImovelService is = new ImovelServiceImpl();
		try {
		
			ISorteio spd = new FillerGapPD();
			spd.setRobot(this);
			spd.executar();
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO, "Sorteador Para Filler Gap PD encerrado com sucesso. " + spd.getSorteados() + " imoveis sorteados");


		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
