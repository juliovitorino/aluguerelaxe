package br.com.jcv.backend.sorteio;

import java.util.List;

import org.apache.log4j.Logger;

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
import br.com.jcv.backend.robot.CicloVidaRobot;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;
import br.com.jcv.backend.workers.interfaces.IOfertaPublicidade;

/**
 * <p>Classe responsavel por aplicar a rotina de sorteio dos imoveis para
 * publicidade na primeira pagina.</p>
 * <p>segue o criterio de sorteio:</p>
 * <p>Busca a variavel configurada MAX_IMOVEL_SORTEIO_PUBL_PD</p>
 * <p>Busca a variavel configurada MAX_DIAS_PRAZO_SORTEIO</p>
 * <p>Obtem a partir de quando sera a data de concessao que é obtida a partir da maxima data na tabela PUBLICIDADE + 1</p>
 * <p>Obtem a lista "m" de imoveis que tem o flag marcado com "N". Onde "m" é o conteúdo da variavel configurada MAX_IMOVEL_SORTEIO_PUBL_PP.</p>
 * <p>Cria se necessário uma linha na tabela PLANO_IMOVEL com o registro de plano de publicidade PP.<p>
 * <p>Cria uma linha na tabela IMOVEL_FATURA com o registro de plano de publicidade PP sem nenhum custo para o anunciante.<p>
 * <p>Envia uma notificação para o anunciante sobre o sorteio de seu imóvel.</p>
 */
public class SorteioPublicidadePD extends Sorteio {

	private static Logger logger = Logger.getLogger(SorteioPublicidadePD.class); 
	private static final String TIPO_PUBLICIDADE = "PD";

	public void executar() throws AlugueRelaxeException{
	
		List<ImovelFichaCompletaDTO> lst = this.getImoveisSorteados();
		if (lst != null) {
			this.setSorteados(lst.size());
			try {
				//---------------------------------------------------
				// Obtem o prazo de dias concedidos por variavel
				//---------------------------------------------------
				int qtdDiasPrazoSorteio = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PRAZO_DIAS_PUBLICIDADE_PD));
				long idPlano = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.CODIGO_PUBLICIDADE_GRATUITA_PD));

				ImovelService is = new ImovelServiceImpl();
				ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
				//---------------------------------------------------
				// Obtem a data de inicio da publicidade e calcula
				// a data final de publicidade
				//---------------------------------------------------
				DateManagerBase dataInicioPD = DateManager.getDateManagerInstance(is.obterDataInicioPublicidadePD());
				DateManagerBase d = DateManager.getDateManagerInstance(dataInicioPD.getDate());
				DateManagerBase dataFinalPD = DateManager.getDateManagerInstance(d.add(qtdDiasPrazoSorteio));

				//---------------------------------------------------
				// Somente faz o processamento se a diferenca entre
				// a data de hoje e a data de inicio da proxima propaganda
				// for menor  que 30 dias 
				//---------------------------------------------------
				DateManagerBase hoje = DateManager.getDateManagerInstance();
				DateManagerBase t = DateManager.getDateManagerInstance();
				
				if (t.dataDiff(hoje.getDate(),dataInicioPD.getDate()) < 30){
				
					for (ImovelFichaCompletaDTO ifcdto : lst){

						logger.info("Imovel #" + ifcdto.imovel.id + " - " + ifcdto.imovel.descricaoTituloAnuncio);
						this.getRobot().setStatus(CicloVidaRobot.ROBOT_TRABALHANDO, "Imovel #" + ifcdto.imovel.id + " - " + ifcdto.imovel.descricaoTituloAnuncio);

						//---------------------------------------------------
						// Verifica se o imovel tem imagens
						// (NÃO SERÃO CONTEMPLADOS IMOVEIS SEM IMAGENS)
						//---------------------------------------------------
						if (ifcdto.imagensImovelTB != null){
							//---------------------------------------------------
							// Popula a estrutura de publicidade
							//---------------------------------------------------
							PublicidadeImovelDTO pub = new PublicidadeImovelDTO();
							pub.fichaImovel = ifcdto;
							pub.publicidade = new PublicidadeDTO();
							pub.publicidade.dataInicio = dataInicioPD.getDate();
							pub.publicidade.dataFim = dataFinalPD.getDate();
							pub.publicidade.tipoPublicidade = TIPO_PUBLICIDADE;
							
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
							//IOfertaPublicidade iop = new OfertaPublicidadePD();
							//iop.execute(pub);
							
						} else {
							logger.info("Imovel #" + ifcdto.imovel.id + " - sem imagens do tipo XG" );
							this.getRobot().setStatus(CicloVidaRobot.ROBOT_TRABALHANDO, "Imovel #" + ifcdto.imovel.id + " - sem imagens do tipo XG");
						}
						//---------------------------------------------------
						// Atualiza status de sorteio para JA SORTEADO = "S"
						//---------------------------------------------------
						is.atualizarStatusSorteioPD(ifcdto, "S");
							
					}
					

				}
			} catch (AlugueRelaxeException e) {
				throw e;
			}
		} else {
			this.setSorteados(0);
			logger.info(">>> SEM IMOVEIS PARA SORTEIO DE PUBLICIDADE" );
			this.getRobot().setStatus(CicloVidaRobot.ROBOT_TRABALHANDO, ">>> SEM IMOVEIS PARA SORTEIO DE PUBLICIDADE");
		}

	}

	private List<ImovelFichaCompletaDTO> getImoveisSorteados() throws AlugueRelaxeException {
		List<ImovelFichaCompletaDTO> lst = null;
		
		int maxImoveisSorteio = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.MAX_IMOVEIS_SORTEIO_PERMITIDO_PD));

		try {
				ImovelService is = new ImovelServiceImpl();
				
				List<Long> lstimv = is.listarImoveisSorteadosPD();
				lst = this.getSortudos(lstimv, maxImoveisSorteio);

		} catch(AlugueRelaxeException e) {
			throw e;
		}
		
		return lst;
	}


}
