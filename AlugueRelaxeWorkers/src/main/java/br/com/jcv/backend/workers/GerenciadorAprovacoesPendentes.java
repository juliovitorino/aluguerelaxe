package br.com.jcv.backend.workers;

import br.com.jcv.backend.aprovacoespendentes.AprovacoesPendentesCOR;
import br.com.jcv.backend.aprovacoespendentes.ContatoAnuncianteTotalPendenteSMSChain;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;

/**
*
* <h2>GerenciadorAprovacoesPendentes</h2>
* <p>Gerenciador de aprovacoes pendentes de aviso</p>
* @author julio
*/
public class GerenciadorAprovacoesPendentes extends Robot {

	public static final String ROBOT_NOME = "GERENCIADOR_APROVACOES_PENDENTES";


	public GerenciadorAprovacoesPendentes() {
		super(ROBOT_NOME, "Gerenciador Aprovacoes pendentes");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando gerenciador de aprovacoes pendentes");
		try {
		
			// Cria a organizacao de chamadas (Chains)
			AprovacoesPendentesCOR adcor = new AprovacoesPendentesCOR();
			//adcor.setContext(dto);
			adcor.addChain(new ContatoAnuncianteTotalPendenteSMSChain());
			//adcor.addChain(new DepoimentoPendenteModeracaoSMSChain());
			adcor.execute();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Gerenciador de aprovacoes pendentes processado com sucesso.");
		
	}
}
