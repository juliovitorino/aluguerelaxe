package br.com.jcv.backend.workers;

import java.util.HashMap;
import java.util.Map;

import br.com.jcv.backend.alerta24h.AlertaDTO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.filasms.FilaSMSServiceImpl;
import br.com.jcv.backend.interfaces.services.FilaSMSService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;
import br.com.jcv.backend.workers.interfaces.IAlerta24H;

/**
*
* <h2>Alerta24HSenderSMS</h2>
* <p>Enviador de alertas para usuarios Alerta 24H</p>
* @author julio
*/
public class Alerta24HSenderSMS extends Robot implements IAlerta24H  {

	public static final String ROBOT_NOME = "SENDER_ALERTA_24H_SMS";


	public Alerta24HSenderSMS() {
		super(ROBOT_NOME, "EMISSOR NO SMS DO ALTERA 24H");
	}
	
	public void executar() {
		// Nada a fazer
	}
	
	public void executar(AlertaDTO dto) {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Enviando SMS para FILA_SMS");
		
		try {
		
			if (VariavelCache.getInstance().getValor(VariavelConstantes.PERMITIR_ENVIO_SMS_GATEWAY).equals("ON")){
				if ((dto.ifcdto.cliente.telefones != null) && (dto.ifcdto.cliente.telefones.size()>0)){
					
					final Map<String,String> parametros = new HashMap<String,String>();
					parametros.put(Constantes.P1, dto.ifcdto.imovel.endereco.cidade);
					parametros.put(Constantes.P2, dto.ifcdto.imovel.endereco.uf);
					parametros.put(Constantes.P3, dto.ifcdto.cliente.email);
					parametros.put(Constantes.P4, dto.contato.proposto.toUpperCase());
					FilaSMSDTO fsmsdto = new FilaSMSDTO();
					fsmsdto.msg = MensagemCache.getInstance().getMensagem(MSGCODE.ALERTA_24H_SMS, parametros);
					
					for (TelefoneDTO tel : dto.ifcdto.cliente.telefones){
						boolean lCelular = tel.indTipoTelefone.equals("C");
						if (lCelular){
							fsmsdto.celular = tel.ddd.concat(tel.telefone); 
							
							// Obtem uma interface para FilaSMS
							FilaSMSService fsmss = new FilaSMSServiceImpl();
							fsmss.gravarRegistro(fsmsdto);
						}
					}
				}
			}

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"SMS para " + dto.ifcdto.cliente.nome + "(" + dto.ifcdto.cliente.email + ") inserido na FILA_SMS com sucesso.");
		
	}


}
