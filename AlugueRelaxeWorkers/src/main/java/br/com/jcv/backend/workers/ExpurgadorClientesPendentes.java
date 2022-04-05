package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.ClienteServiceImpl;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.ClienteService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
*
* <h2>ExpurgadorClientesPendentes</h2>
* <p>Expurgador de clientes com status pendente</p>
* <p>Como funciona:</p>
* <p>Será obtido uma lista de todos os clientes com CLIE_IN_STATUS = "P"</p>
* <p>Cada cliente terá sua data de cadastro verificada com base no seguinte critério: Se a data AGORA + "N" dias (definido no VARIAVEL) for maior que sua data de cadastro, apagamos seu cadastro
* e avisamos que seu cadastro foi eliminado da base de dados.</p>
* @author julio
*/
public class ExpurgadorClientesPendentes extends Robot {

	public static final String ROBOT_NOME = "EXPURGADOR_CLIENTES";
	private static final String STATUS_PENDENTE = "P";


	public ExpurgadorClientesPendentes() {
		super(ROBOT_NOME, "Expurgador de clientes pendentes");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando verificação de clientes pendentes para expurgo");
		try {
			ClienteService<ClienteDTO> is = new ClienteServiceImpl();
		
			// Obtem a lista de clientes em status "P"
			List<Long> lstClientesPendentes = is.listarRegistros(STATUS_PENDENTE);
			
			for( Long id : lstClientesPendentes){
				ClienteDTO dto = new ClienteDTO();
				dto.id = id;
				dto = is.pesquisarRegistro(dto);
				DateManagerBase dataAtual = DateManagerBase.getDateManagerInstance();
				DateManagerBase dataExpurgo = DateManagerBase.getDateManagerInstance(dto.dataCadastro.getTime());
				dataExpurgo.add(Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPO_CONTA_NOVA_PENDENTE)));
				if (dataAtual.getTimeInMillis() > dataExpurgo.getTimeInMillis()) {
					is.excluirRegistro(dto);
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Lista de expurgo de clientes status pendente processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
