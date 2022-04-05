package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;

/**
*
* <h2>VerificadorClientesPendentes</h2>
* <p>Verificador de clientes com status pendente</p>
* <p>Como funciona:</p>
* <p>Será obtido uma lista de todos os clientes com CLIE_IN_STATUS = "P"</p>
* <p>Cada cliente terá sua data de cadastro verificada com base no seguinte critério: Se a data AGORA for maior que sua data de cadastro, enviamos e-mail
* avisando que ele precisa confirmar seu cadastro, juntamente com o link de ativação</p>
* @author julio
*/
public class VerificadorClientesPendentes extends Robot {

	public static final String ROBOT_NOME = "CLIENTES_PENDENTES";
	private static final String STATUS_PENDENTE = "P";

	public VerificadorClientesPendentes() {
		super(ROBOT_NOME, "Verificador de clientes pendentes");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando verificação de clientes pendentes");
		try {
			ClienteService<ClienteDTO> is = new ClienteServiceImpl();
		
			// Obtem a lista de clientes em status "P"
			@SuppressWarnings("unchecked")
			List<Long> lstClientesPendentes = is.listarRegistros(STATUS_PENDENTE);
			
			for( Long id : lstClientesPendentes){
				ClienteDTO dto = new ClienteDTO();
				dto.id = id;
				dto = is.pesquisarRegistro(dto);
				DateManagerBase dataAtual = DateManagerBase.getDateManagerInstance();
				if (dataAtual.getTimeInMillis() > dto.dataCadastro.getTime()) {
					is.enviarEmailAtivacaoConta(dto);
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Lista de clientes status pendente processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
