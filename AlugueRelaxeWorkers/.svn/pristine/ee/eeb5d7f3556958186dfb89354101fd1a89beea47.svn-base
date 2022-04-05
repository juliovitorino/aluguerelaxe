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
* <h2>RenovadorSorteioPublicidadePP</h2>
* <p>Renovador Sorteio Publicidade PP</p>
* <p>Como funciona:</p>
* <p>Será obtido sum(IMOV_IN_SORTEIO) dos imoveis ativos, se for igual a zero realiza procedimento de renovação. 
*  Obteremos uma lista dos imoveis ativos para condicao de sorteio.
* </p>
* @author julio
*/
public class RenovadorSorteioPublicidadePP extends Robot {

	public static final String ROBOT_NOME = "RENOVADOR_SORTEIO_PUBLICIDADE_PP";

	public RenovadorSorteioPublicidadePP() {
		super(ROBOT_NOME, "Renovador Sorteio Publicidade PP");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando Renovador Sorteio Publicidade PP");
		try {
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			
			// Obtem a quantidade de imoveis nao sorteados do tipo PP
			long qtdeSorteio = is.contarImoveisSorteio(Constantes.IND_TIPO_PUBLICIDADE_PP);
		
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
							is.atualizarStatusSorteio(ifcdto, Constantes.SIM);
						} else {
							is.atualizarStatusSorteio(ifcdto, Constantes.NAO);
						}
						
						// Incrementa contator
						processados++;
				
					}
				}
			
			}
			
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Renovador Sorteio Publicidade PP processado com sucesso. Total de (" + processados + ") processado(s)");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
