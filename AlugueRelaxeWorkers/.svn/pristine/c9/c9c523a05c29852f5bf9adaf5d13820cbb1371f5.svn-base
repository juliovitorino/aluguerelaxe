package br.com.jcv.backend.workers;

import java.util.List;

import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.imovel.publicidade.PublicidadeDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.sorteio.Sorteio;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;
import br.com.jcv.backend.workers.interfaces.IOfertaPublicidade;


/**
*
* <h2>FillerGapPP</h2>
* <p>Preenche buracos nos dias de publidade PP</p>
* @author julio
*/
public class FillerGapPD extends Sorteio {
	
	public void executar() {
	
		try {
			//-----------------------------------------------------------------------------------------
			// Verifica a grade de publicidade PP para D+1 se está vazia
			// Se tiver algum imóvel sendo publicado em D+1, não executa procedimentos de preenchimento
			//-----------------------------------------------------------------------------------------
			ImovelService is = new ImovelServiceImpl();
			DateManagerBase dataGap = DateManager.getDateManagerInstance();
			int qtdGrade = is.contarGradePublicidade(dataGap.add(1), "PD");
			
			if (qtdGrade == 0){
				//-----------------------------------------------------------------------------------------
				// Busca maximo de imoveis que podem ser publicidados gratuitamente
				//-----------------------------------------------------------------------------------------
				int qtdDiasPrazoSorteio = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PRAZO_DIAS_PUBLICIDADE_PD));
				long idPlano = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.CODIGO_PUBLICIDADE_GRATUITA_PD));
				DateManagerBase dataFinalPP = DateManager.getDateManagerInstance(dataGap.getDate());
				dataFinalPP.add(qtdDiasPrazoSorteio);

				//-----------------------------------------------------------------------------------------
				// Busca lista de imoveis que nao passaram ainda no PP
				//-----------------------------------------------------------------------------------------
				List<ImovelFichaCompletaDTO> lst = this.getImoveisSorteados();
				
				//-----------------------------------------------------------------------------------------
				// Obtem instancia de faturamento
				//-----------------------------------------------------------------------------------------
				ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
				
				for (ImovelFichaCompletaDTO ifcdto : lst){
					
					//---------------------------------------------------
					// Verifica se o imovel tem imagens
					// (NÃO SERÃO CONTEMPLADOS IMOVEIS SEM IMAGENS)
					//---------------------------------------------------
					if (ifcdto.imagensImovelXG != null){
						//---------------------------------------------------
						// Popula a estrutura de publicidade
						//---------------------------------------------------
						PublicidadeImovelDTO pub = new PublicidadeImovelDTO();
						pub.fichaImovel = ifcdto;
						pub.publicidade = new PublicidadeDTO();
						pub.publicidade.dataInicio = dataGap.getDate();
						pub.publicidade.dataFim = dataFinalPP.getDate();
						pub.publicidade.tipoPublicidade = "PD";
						
						//---------------------------------------------------
						// Cria o registro em publicidade
						//---------------------------------------------------
						PublicidadeImovelDTO pubCriado = is.criarPublicidade(pub, idPlano, true);

						//---------------------------------------------------
						// Libera o registro em publicidade
						//---------------------------------------------------
						is.liberarPublicidade(pub);
						
						//---------------------------------------------------
						// Executa a liquidacao da fatura de publicidade
						//---------------------------------------------------
						ImovelPlanoFaturaDTO ipfdto = ipfs.pesquisarUltimaFatura(ifcdto.imovel.id, "D");
						ipfdto = ipfs.registrarPagtoPublicidade(ipfdto.id);

						//-----------------------------------------------------
						// Envia Notificação ao Proprietario do imovel sorteado
						//-----------------------------------------------------
						enviarNotificacaoSorteio(pub); 
						
						//-----------------------------------------------------
						// Aproveita para fazer Oferta publicidade
						//-----------------------------------------------------
						IOfertaPublicidade iop = new OfertaPublicidadePD();
						iop.execute(pub,"PD");
					}
					//---------------------------------------------------
					// Atualiza status de sorteio para JA SORTEADO = "S"
					//---------------------------------------------------
					is.atualizarStatusSorteio(ifcdto, "S");
				}
			}

		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<ImovelFichaCompletaDTO> getImoveisSorteados() throws AlugueRelaxeException {
		List<ImovelFichaCompletaDTO> lst = null;
		
		int maxImoveisSorteio = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.MAX_IMOVEIS_SORTEIO_PERMITIDO));

		try {
				ImovelService is = new ImovelServiceImpl();
				
				List<Long> lstimv = is.listarImoveisSorteados();
				lst = this.getSortudos(lstimv, maxImoveisSorteio);
				
		} catch(AlugueRelaxeException e) {
			throw e;
		}
		
		return lst;
	}
	
}
