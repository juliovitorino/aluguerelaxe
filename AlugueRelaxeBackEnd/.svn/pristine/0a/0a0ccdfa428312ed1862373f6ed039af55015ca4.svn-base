package br.com.jcv.backend;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ImovelPublicidadeTest extends TestCase {

	private static Logger logger =  Logger.getLogger(ImovelPublicidadeTest.class);
	
	public void testCriarPublicidadePD() {
		try {
			//---------------------------------------------------
			// Obtem o prazo de dias concedidos por variavel
			//---------------------------------------------------
			int qtdDiasPrazoSorteio = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PRAZO_DIAS_PUBLICIDADE_PD));
			long idPlano = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.CODIGO_PUBLICIDADE_GRATUITA_PD));
			
			ImovelService is = new ImovelServiceImpl();
			//---------------------------------------------------
			// Obtem a data de inicio da publicidade e calcula
			// a data final de publicidade
			//---------------------------------------------------
			DateManagerBase dataInicioPP = DateManager.getDateManagerInstance(is.obterDataInicioPublicidadePD());
			DateManagerBase dataFinalPP = DateManager.getDateManagerInstance(dataInicioPP.add(qtdDiasPrazoSorteio));
			
			ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(2);
			logger.info("Imovel #" + ifcdto.imovel.id + " - " + ifcdto.imovel.descricaoTituloAnuncio);
			
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
				pub.publicidade.dataInicio = dataInicioPP.getDate();
				pub.publicidade.dataFim = dataFinalPP.getDate();
				pub.publicidade.tipoPublicidade = "PD";
				
				//---------------------------------------------------
				// Cria o registro em publicidade
				//---------------------------------------------------
				PublicidadeImovelDTO pubCriado = is.criarPublicidade(pub, idPlano);

				//---------------------------------------------------
				// Libera o registro em publicidade
				//---------------------------------------------------
				is.liberarPublicidade(pub);

				
			} else {
				logger.info("Imovel #" + ifcdto.imovel.id + " - sem imagens do tipo XG" );
			}
			
			assertTrue(1 == 1);

		} catch (AlugueRelaxeException e) {
			fail(e.getMessage());
		}
		
	}

	
	public void testCriarPublicidadePP() {
		try {
			//---------------------------------------------------
			// Obtem o prazo de dias concedidos por variavel
			//---------------------------------------------------
			int qtdDiasPrazoSorteio = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PRAZO_DIAS_PUBLICIDADE_PP));
			long idPlano = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.CODIGO_PUBLICIDADE_GRATUITA_PP));
			
			ImovelService is = new ImovelServiceImpl();
			//---------------------------------------------------
			// Obtem a data de inicio da publicidade e calcula
			// a data final de publicidade
			//---------------------------------------------------
			DateManagerBase dataInicioPP = DateManager.getDateManagerInstance(is.obterDataInicioPublicidadePP());
			DateManagerBase dataFinalPP = DateManager.getDateManagerInstance(dataInicioPP.add(qtdDiasPrazoSorteio));
			
			ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(304);
			logger.info("Imovel #" + ifcdto.imovel.id + " - " + ifcdto.imovel.descricaoTituloAnuncio);
			
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
				pub.publicidade.dataInicio = dataInicioPP.getDate();
				pub.publicidade.dataFim = dataFinalPP.getDate();
				pub.publicidade.tipoPublicidade = "PP";
				
				//---------------------------------------------------
				// Cria o registro em publicidade
				//---------------------------------------------------
				PublicidadeImovelDTO pubCriado = is.criarPublicidade(pub, idPlano);

				//---------------------------------------------------
				// Libera o registro em publicidade
				//---------------------------------------------------
				is.liberarPublicidade(pub);

				
			} else {
				logger.info("Imovel #" + ifcdto.imovel.id + " - sem imagens do tipo XG" );
			}
			
			assertTrue(1 == 1);

		} catch (AlugueRelaxeException e) {
			fail(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void testListarImovelPublicidadeSuperBanner() {
		try {
			ImovelService<ImovelPublicidadeDTO> ips = new ImovelServiceImpl();
			List<? extends ImovelBaseDTO> publicidades = ips.listImovelSuperBanner();

			if (publicidades == null){
				assertNull(publicidades);
			} else {
				if (publicidades.size() > 0) {
					logger.info("Existe(m) " + publicidades.size() + " publicidade(s) para listar.");
					assertTrue(publicidades.size() > 0);
				}
			}
			
		} catch(AlugueRelaxeException are) {
			fail(are.getMessage());
		
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}

	@SuppressWarnings("unchecked")
	public void testListarImovelPublicidadePaginaPrincipal() {
		try {
			ImovelService<ImovelPublicidadeDTO> ips = new ImovelServiceImpl();
			List<? extends ImovelBaseDTO> publicidades = ips.listImovelPublicidadePaginaPrincipal();

			if (publicidades == null){
				assertNull(publicidades);
			} else {
				if (publicidades.size() > 0) {
					logger.info("Existe(m) " + publicidades.size() + " publicidade(s) para listar.");
					assertTrue(publicidades.size() > 0);
				}
			}
			
		} catch(AlugueRelaxeException are) {
			fail(are.getMessage());
		
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	@SuppressWarnings("unchecked")
	public void testListarImovelPublicidadePaginaDestaque() {
		try {
			ImovelService<ImovelPublicidadeDTO> ips = new ImovelServiceImpl();
			List<? extends ImovelBaseDTO> publicidades = ips.listImovelPublicidadePaginaDestaque();

			if (publicidades.size() > 0) {
				logger.info("Existe(m) " + publicidades.size() + " destaque(s) para listar.");
			} else {
				logger.info("NÃ£o existem destaques para listar.");
			}
			assertTrue(publicidades.size() >= 0);
			
		} catch(AlugueRelaxeException are) {
			fail(are.getMessage());
		
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
}
