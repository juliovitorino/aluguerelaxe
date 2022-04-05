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
* <h2>ReiniciarFuraFila</h2>
* <p>Reiniciador de valor do fura-fila para os imoveis com planos pagos</p>
* <p>Como funciona:</p>
* <p>Será obtido uma lista de imoveis com planos pagos nao vencidos e o valor do campo IMOV_VL_TIPO_COLABORACAO < 0.
* Estes imoveis terao seus campos atualizados: IMOV_IN_TIPO_COLABORACAO = 'C', 
* IMOV_VL_TIPO_COLABORACAO = [VALOR DA FATURA PAGA].
* </p>
* @author julio
*/
public class RenovadorFuraFila extends Robot {

	public static final String ROBOT_NOME = "RENOVADOR_FURA_FILA";

	public RenovadorFuraFila() {
		super(ROBOT_NOME, "Renovador fura-fila");
	}
	
	public void executar() {
	
		this.setStatus(CicloVidaRobot.ROBOT_TRABALHANDO,"Iniciando renovador do fura-fila de planos pagos nao vencidos");
		try {
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		
			int processados = 0;
			
			// Obtem a lista imoveis com anuncios a vencer
			List<Long> lstPlanosAVencer = ipfs.listarPlanosPagosNaoVencidos();
			if (lstPlanosAVencer != null){
				for( Long id : lstPlanosAVencer){
					// Obtem a ficha completa do imovel
					ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(id);

					// Se valor de colaboracao estiver negativo, existe a necessidade de renovacao
					if ((ifcdto.tipoColaboracao != null) && (ifcdto.tipoColaboracao.valor < 0)) {
						// Obtem os detalhes da ultima fatura do anuncio
						ImovelPlanoFaturaDTO ipfdto = ipfs.pesquisarUltimaFatura(id, Constantes.TIPO_PLANO_NORMAL);
						
						// Renova o valor do fura-fila para o plano pago nao vencido
						is.negativarImovel(id, ipfdto.valorFatura);
					}
					
					// Envia o email para cliente
					// is.notificaRenovacaoFuraFila(ifcdto, ipfdto);
					
					// Incrementa contator
					processados++;
			
				}
			}
			this.setStatus(CicloVidaRobot.ROBOT_TERMINADO,"Renovador do fura-fila processado com sucesso. Total de (" + processados + ") processado(s)");

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
