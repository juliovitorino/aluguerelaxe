package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.filasms.FilaSMSServiceImpl;
import br.com.jcv.backend.interfaces.services.FilaSMSService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
*
* <h2>GerenciadorSenderGatewaySMSPrior</h2>
* <p>Gerenciador de envio de mensagens de SMS em FILA_SMS do tipo Gateway para envio por servico contratado SMS Gateway
* com ALTA PRIORIDADE (NIVEL 1). Este processo usara crontab de execucao a cada 1 minuto. </p>
* @author julio
*/
public class GerenciadorSenderGatewaySMSPrior extends Robot {

	public static final String ROBOT_NOME = "GERENCIADOR_SENDER_GATEWAY_PRIOR";

	public GerenciadorSenderGatewaySMSPrior() {
		super(ROBOT_NOME, "Gerenciador SMS Sender Gateway Prioritario");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando Gerenciador SMS Sender Gateway Prioritario");
		try {
		
			if (VariavelCache.getInstance().getValor(VariavelConstantes.PERMITIR_ENVIO_SMS_GATEWAY).equals(Constantes.ON)){
				//------------------------------------------------------------------------------
				// Obtem todas as mensagens SMS nao enviadas para fila de job
				// do tipo SMS Gateway
				//------------------------------------------------------------------------------
				FilaSMSService fsmss = new FilaSMSServiceImpl();
				List<FilaSMSDTO> lstsmspendente = fsmss.listarSMSPendentes("G", Constantes.SMS_PRIORIDADE_ALTA);
				
				//------------------------------------------------------------------------------
				// grava cada mensagem para o servico de SMS Gateway Factory implementado
				// pelo Robot SenderGatewaySMS
				//------------------------------------------------------------------------------
			
				if ((lstsmspendente != null) && (lstsmspendente.size() > 0)) {
					for (FilaSMSDTO dto : lstsmspendente) {
						SenderGatewaySMS gateway = new SenderGatewaySMS();
						gateway.execute(dto);
						fsmss.atualizarDataEnvioGatewaySMS(dto);
						this.addContador();
					}
				}
			}

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Gerenciador SMS Sender Gateway com sucesso. Total de " + this.getContador() + " SMS enviados");
		
	}
}
