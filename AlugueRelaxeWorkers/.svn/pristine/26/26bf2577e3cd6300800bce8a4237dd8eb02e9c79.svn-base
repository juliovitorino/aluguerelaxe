package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.assinantes.AssinantesDTO;
import br.com.jcv.backend.assinantes.AssinantesServiceImpl;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.AssinantesService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;

/**
*
* <h2>VerificadorAssinantesPendentes</h2>
* <p>Verificador de assinantes com status pendente</p>
* <p>Como funciona:</p>
* <p>Será obtido uma lista de todos os assinantes com ASSI_IN_STATUS = "P" da campanha ativa</p>
* <p>Cada cliente terá sua data de cadastro verificada com base no seguinte critério: Se a data AGORA for maior que sua data de cadastro, enviamos e-mail
* avisando que ele precisa confirmar seu cadastro, juntamente com o link de ativação</p>
* @author julio
*/
public class VerificadorAssinantesPendentes extends Robot {

	public static final String ROBOT_NOME = "ASSINANTES_PENDENTES";
	private static final String STATUS_PENDENTE = "P";

	public VerificadorAssinantesPendentes() {
		super(ROBOT_NOME, "Verificador de assinantes pendentes");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando verificação de assinantes pendentes");
		try {
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
		
			// Obtem a lista de assinantes em status "P"
			@SuppressWarnings("unchecked")
			List<AssinantesDTO> lstPendentes = is.listarRegistros(STATUS_PENDENTE);
			
			for( AssinantesDTO dto : lstPendentes){
				DateManagerBase dataAtual = DateManagerBase.getDateManagerInstance();
				if (dataAtual.getTimeInMillis() > dto.dataCadastro.getTime()) {
					is.enviarEmailAtivacaoAssinatura(dto);
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Lista de assinantes status pendente processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
