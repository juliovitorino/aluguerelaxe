package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.robot.Robot;

/**
*
* <h2>RenovadorSorteioPublicidadePD</h2>
* <p>Renovador Sorteio Publicidade PD</p>
* <p>Como funciona:</p>
* <p>Será obtido sum(IMOV_IN_SORTEIO) dos imoveis ativos, se for igual a zero realiza procedimento de renovação. 
*  Obteremos uma lista dos imoveis ativos para condicao de sorteio.
* </p>
* @author julio
*/
public class RenovadorSorteioPublicidadePD extends Robot {

	public static final String ROBOT_NOME = "RENOVADOR_SORTEIO_PUBLICIDADE_PD";

	public RenovadorSorteioPublicidadePD() {
		super(ROBOT_NOME, "Renovador Sorteio Publicidade PD");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando Renovador Sorteio Publicidade PD");
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			
			// Obtem a quantidade de imoveis nao sorteados do tipo PD
			long qtdeSorteio = is.contarImoveisSorteio(Constantes.IND_TIPO_PUBLICIDADE_PD);
		
			int processados = 0;
			if(qtdeSorteio == 0) {
				// Obtem a lista imoveis ativos
				List<Long> lst = is.listarImoveis(Constantes.IMOVEL_STATUS_ATIVO);
				if (lst != null){
					for( Long id : lst){
						// Obtem a ficha completa do imovel
						ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(id);
						
						// Se o imovel nao tem imagens, ja marcamos como sorteado. Azar o dele!
						if (ifcdto.imagensImovelTB == null){
							is.atualizarStatusSorteioPD(ifcdto, Constantes.SIM);
						} else {
							is.atualizarStatusSorteioPD(ifcdto, Constantes.NAO);
						}
						
						// Incrementa contator
						processados++;
				
					}
				}
			
			}
			
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Renovador Sorteio Publicidade PD processado com sucesso. Total de (" + processados + ") processado(s)");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
