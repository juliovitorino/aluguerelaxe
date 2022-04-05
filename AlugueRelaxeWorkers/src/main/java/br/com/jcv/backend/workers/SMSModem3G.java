package br.com.jcv.backend.workers;

import java.io.FileOutputStream;
import java.util.List;

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
* <h2>SMSFiller</h2>
* <p>Recupera as mensagens de SMS em FILA_SMS para disponilizar no formato que o job sms-sender.sh necessita. Os registros
* em condicao sao assinalados pela FISM_IN_MODO = "M"</p>
* @author julio
*/
public class SMSModem3G extends Robot {

	public static final String ROBOT_NOME = "SMS_MODEM_3G";


	public SMSModem3G() {
		super(ROBOT_NOME, "SMS Modem 3G");
	}
	
	
	public void executar() {
		String path_fila_sms = null;
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando SMS Modem 3G");
		try {
			if (VariavelCache.getInstance().getValor(VariavelConstantes.PERMITIR_ENVIO_SMS_MODEM_3G).equals("ON")){
				//------------------------------------------------------------------------------
				// Obtem todas as mensagens SMS nao enviadas para fila de job
				//------------------------------------------------------------------------------
				FilaSMSService fsmss = new FilaSMSServiceImpl();
				List<FilaSMSDTO> lstsmspendente = fsmss.listarSMSPendentes("M");
				
				//------------------------------------------------------------------------------
				// obtem o caminho da fila do servidor
				//------------------------------------------------------------------------------
				path_fila_sms = VariavelCache.getInstance().getValor(VariavelConstantes.PATH_FILA_SERVIDOR_SMS);
				
				//------------------------------------------------------------------------------
				// grava cada mensagem no format de interface sms-sender.sh
				// Marca "S" na fila indicando que foi enfileirado para envio por sms-sender.sh
				//------------------------------------------------------------------------------
			
				if ((lstsmspendente != null) && (lstsmspendente.size() > 0)) {
					for (FilaSMSDTO dto : lstsmspendente) {
						this.gerarMensagem(path_fila_sms, dto);
						fsmss.atualizarStatusEnvioFila(dto, "S");
						this.addContador();
					}
				}
			}

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (VariavelCache.getInstance().getValor(VariavelConstantes.PERMITIR_ENVIO_SMS_MODEM_3G).equals("ON")){
				this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"SMS Modem 3G processado com sucesso. Total de " + this.getContador() + " SMS enfileirados na pasta " + path_fila_sms);
			} else {
				this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"SMS Modem 3G paralisado. Variavel PERMITIR_ENVIO_SMS_MODEM_3G = OFF");
			}
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void gerarMensagem(String path, FilaSMSDTO dto) throws AlugueRelaxeException{

		try {
			String arqsms = path.concat(String.valueOf(System.currentTimeMillis())).concat(".").concat(dto.celular);
			FileOutputStream fos = new FileOutputStream(arqsms);     
			fos.write(dto.msg.getBytes());     
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
