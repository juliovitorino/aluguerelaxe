package br.com.jcv.aluguerelaxe.server.cache;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.publicidade.ImovelPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;

/**
 * <h2>SuperBannerCache</h2>
 * <p>Singleton para manter um cache de super banner</p>
 * <p>Seu funcionamento sera da seguinte forma:<br/>
 * Ao ser criado, a instância invocara o backend solicitando as publicidades disponiveis
 * e as mantera em memoria, acelerando seu acesso posterior.<br/>Para permitir a atualizacao
 * do cache, uma data base sera mantida e ao ser modificada o cache reinvocara o backend
 * refazendo o processo.</p>
 * @author Julio Vitorino
 * @since May, 27th 2013
 */
public class SuperBannerCache {

	private String dataControle = null;
	private List<ImovelPublicidadeVO> cachelst = null;
	private static SuperBannerCache instance = null;
	private ServletContext contexto = null;
	
	private static final Logger logger = Logger.getLogger(SuperBannerCache.class.getName());


	/* Construtor privado */
	private SuperBannerCache(ServletContext event) {
		this.contexto = event;
		initCache();
	}
	
	public static SuperBannerCache getInstance(ServletContext event) {
		if (instance == null) {
			synchronized (SuperBannerCache.class) {
				if (instance == null) {
					instance = new SuperBannerCache(event);
				}
			}
		}
		return instance;
	}
	
	public List<ImovelPublicidadeVO> getSuperBannerCache() {
		// verifica se o cache precisa ser atualizado antes de devolver
		DateManagerBase dm = DateManagerBase.getDateManagerInstance();
		String dataAtual = "";
		try {
			dataAtual = dm.getDateCustom("dd/MM/yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (! this.dataControle.equals(dataAtual)) {
			synchronized (this) {
				if (! this.dataControle.equals(dataAtual)) {
					logger.info("******************************************");
					logger.info("* Atualizando o cache de Super Banner    *");
					logger.info("******************************************");
					initCache();
				}
			}
		}

		return this.cachelst;
	}
	
	
	private void initCache() {
		try {
			// Obtem a lista de imoveis que estao disponiveis para publicidade
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			List<? extends ImovelBaseDTO> publicidades = ips.listImovelSuperBanner();
			
			// atualiza a data de controle
			DateManagerBase dm = DateManagerBase.getDateManagerInstance();
			this.dataControle = dm.getDateCustom("dd/MM/yyyy");

			// Repassa a lista do backend para o cache
			if ((publicidades != null) && (publicidades.size() > 0)) {
				this.cachelst = new ArrayList<ImovelPublicidadeVO>();
				
				// Obtem o caminho root_fotos das imagens dentro do web.xml
				ServletContext context = this.contexto;
				String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
				logger.info("Path base para imagens:" + root_fotos);
				
				logger.info(publicidades.size() + " Imóvel(eis) para publicidade de super banner");
				for (ImovelBaseDTO ibdto : publicidades) {

					ImovelPublicidadeVO ipvo = new ImovelPublicidadeVO();
					ipvo.id = ibdto.id;
					ipvo.qtdeVisitas = ibdto.qtdeVisitas;
					ipvo.nomeImagemVideo = root_fotos + ibdto.idCliente + "/" + ipvo.id + "/" + ((ImovelPublicidadeDTO)ibdto).nomeImagemVideo;
					ipvo.descricaoUF = ((ImovelPublicidadeDTO)ibdto).descricaoUF;
					ipvo.descricaoGeral = ibdto.descricaoGeral;
					ipvo.idCliente = ibdto.idCliente;
					ipvo.dataCadastro = ibdto.dataCadastro;
					if (ibdto.endereco != null){
						ipvo.endereco = new EnderecoVO();
						ipvo.endereco.cidade = ibdto.endereco.cidade;
						ipvo.endereco.uf = ibdto.endereco.uf; 
					}
					if(((ImovelPublicidadeDTO)ibdto).imagemPreferida != null){
						ipvo.imagemPreferida = new ImovelImagemVideoVO();
						ipvo.imagemPreferida.nome = root_fotos + ibdto.idCliente + "/" + ipvo.id + "/" + ((ImovelPublicidadeDTO)ibdto).imagemPreferida.nome;
						ipvo.imagemPreferida.tipo = ((ImovelPublicidadeDTO)ibdto).imagemPreferida.tipo;
					}
					cachelst.add(ipvo);
					
					logger.debug("             id:" + ipvo.id);
					logger.debug("    qtdeVisitas:" + ipvo.qtdeVisitas);
					logger.debug("nomeImagemVideo:" + ipvo.nomeImagemVideo);
					logger.debug("    descricaoUF:" + ipvo.descricaoUF);
					logger.debug(" descricaoGeral:" + ipvo.descricaoGeral);
					logger.debug("      idCliente:" + ipvo.idCliente);
					logger.debug("   dataCadastro:" + ipvo.dataCadastro);
				}
				
				logger.info("****************************************************");
				logger.info("* Cache de Publicidade de Super Banner Iniciado    *");
				logger.info("****************************************************");
			} else {
				this.cachelst = null;
				logger.info("Não foram encontradas publicidades para super banner.");
			}
		} catch (AlugueRelaxeException are){
			logger.error(are.getMessage());
			are.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	
	}
}