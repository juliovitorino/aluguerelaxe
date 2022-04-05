package br.com.jcv.backend.workers;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.gatewaysms.GatewaySMSFactory;
import br.com.jcv.backend.gatewaysms.IGatewaySMS;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.FuncaoEspecial;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
*
* <h2>SenderGatewaySMS</h2>
* <p>Enviador de alertas para usuarios Alerta 24H</p>
* @author julio
*/
public class SenderGatewaySMS extends Robot implements FuncaoEspecial<FilaSMSDTO>  {

	public static final String ROBOT_NOME = "SENDER_GATEWAY_SMS";


	public SenderGatewaySMS() {
		super(ROBOT_NOME, "EMISSOR DE SMS VIA GATEWAY");
	}
	
	public void executar() {
		// Nada a fazer
	}
	
	public void execute(FilaSMSDTO dto) {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Enviando SMS via Gateway SMS");
		try {
			
			// Obtem o Gateway Ativo (Contratado)
			int idx = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.FACTORY_GATEWAY_SMS_ATIVO));
			IGatewaySMS gatewaysms = GatewaySMSFactory.getInstance(idx);
			gatewaysms.execute(dto);

			String provedor = VariavelCache.getInstance().getValor(VariavelConstantes.FACTORY_GATEWAY_SMS_ATIVO_EMPRESA);
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"SMS via Gateway (" + provedor + ") para " + 
							"(" + dto.celular + ") enviado. StatusCode:(" + dto.statusCode + ")");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
