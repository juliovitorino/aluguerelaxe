package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;

/**
*
* <h2>AtualizadorColaboradorInconsistente</h2>
* <p>Verifica de publicidades do tipo PA se estao dentro do prazo e pagas</p>
* @author julio
*/
public class AtualizadorColaboradorInconsistente extends Robot {

	public static final String ROBOT_NOME = "ATUALIZADOR_IMOVEL_COLABORADOR_INCONSISTENTE";

	public AtualizadorColaboradorInconsistente() {
		super(ROBOT_NOME, "Atualizador de imoveis colaboradores inconsistentes");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando Atualizador de imoveis colaboradores inconsistentes");
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		
			// Obtem a lista de imoveis colaboradores inconsistentes
			List<Long> lst = is.listarColaboradorInconsistente();
			if (lst != null){
				for( long id : lst){
					// Retorna estado de colaboracao para sua base natural
					is.normalizarImovelColaborador(id);
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Atualizador de imoveis colaboradores inconsistentes processada com sucesso.");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
