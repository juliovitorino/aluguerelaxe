package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.assinantes.AssinantesDTO;
import br.com.jcv.backend.assinantes.AssinantesServiceImpl;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.interfaces.services.AssinantesService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
*
* <h2>ExpurgadorAssinantesPendentes</h2>
* <p>Expurgador de assinantes com status pendente</p>
* <p>Como funciona:</p>
* <p>Será obtido uma lista de todos os assinantes com CLIE_IN_STATUS = "P"</p>
* <p>Cada cliente terá sua data de cadastro verificada com base no seguinte critério: Se a data AGORA + "N" dias (definido no VARIAVEL) for maior que sua data de cadastro, apagamos seu cadastro
* e avisamos que seu cadastro foi eliminado da base de dados.</p>
* @author julio
*/
public class ExpurgadorAssinantesPendentes extends Robot {

	public static final String ROBOT_NOME = "EXPURGADOR_CLIENTES";
	private static final String STATUS_PENDENTE = "P";


	public ExpurgadorAssinantesPendentes() {
		super(ROBOT_NOME, "Expurgador de assinantes pendentes");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando verificação de assinantes pendentes para expurgo");
		try {
			AssinantesService<AssinantesDTO> is = new AssinantesServiceImpl();
		
			// Obtem a lista de assinantes em status "P" da campanha ativa
			List<AssinantesDTO> lstPendentes = is.listarRegistros(STATUS_PENDENTE);
			for( AssinantesDTO dto : lstPendentes){
				DateManagerBase dataAtual = DateManagerBase.getDateManagerInstance();
				DateManagerBase dataExpurgo = DateManagerBase.getDateManagerInstance(dto.dataCadastro.getTime());
				dataExpurgo.add(Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPO_CONTA_NOVA_PENDENTE)));
				if (dataAtual.getTimeInMillis() > dataExpurgo.getTimeInMillis()) {
					is.excluirRegistro(dto);
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Lista de expurgo de assinantes status pendente processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
