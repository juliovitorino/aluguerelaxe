package br.com.jcv.backend.workers;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.sorteio.ISorteio;

public class SorteadorFillerGapPP extends Robot {

	public static final String ROBOT_NOME = "SORTEADOR_FILLER_GAP_PP";

	public SorteadorFillerGapPP() {
		super(ROBOT_NOME, "Sorteador Para Filler Gap PP");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO, "Preparando para iniciar Sorteador Para Filler Gap PP");
		//ImovelService is = new ImovelServiceImpl();
		try {
		
			ISorteio spd = new FillerGapPP();
			spd.setRobot(this);
			spd.executar();
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO, "Sorteador Para Filler Gap PP encerrado com sucesso. " + spd.getSorteados() + " imoveis sorteados");


		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
