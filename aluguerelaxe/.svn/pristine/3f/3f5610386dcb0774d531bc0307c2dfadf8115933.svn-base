package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.destaques.DestaquesRPC;
import br.com.jcv.aluguerelaxe.client.publicidade.ImovelPublicidadeVO;
import br.com.jcv.aluguerelaxe.server.cache.PublicidadeDestaqueCache;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DestaquesRPCImpl extends RemoteServiceServlet implements
		DestaquesRPC {
	/**
	 * <h2>logger</h2>
	 * <p>Variável responsável pelo log</p> 
	 */
	private static final Logger logger = Logger.getLogger(DestaquesRPCImpl.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = -8396209251968154827L;

	public List<ImovelPublicidadeVO> getPainelDestaques() {
		return PublicidadeDestaqueCache.getInstance(this.getServletContext()).getPublicidadeCache();
	}

	@Deprecated
	public List<ImovelPublicidadeVO> getPainelDestaques(int k) {
		List<ImovelPublicidadeVO> retorno = null;
		try {
			// Obtem a lista de imoveis que estao disponiveis para publicidade
			ImovelService<ImovelBaseDTO> ips = new ImovelServiceImpl<ImovelPublicidadeDTO>();
			List<? extends ImovelBaseDTO> publicidades = ips.listImovelPublicidadePaginaDestaque();
			
			// Repassa a lista do backend para camada de visao
			if (publicidades.size() > 0) {
				retorno = new ArrayList<ImovelPublicidadeVO>();
				
				// Obtem o caminho root_fotos das imagens dentro do web.xml
				ServletContext context = getServletContext();
				String root_fotos = context.getInitParameter(Constantes.ROOT_FOTOS);
				logger.info("Path base para imagens:" + root_fotos);
				
				logger.info(publicidades.size() + " Imóvel(eis) para publicidade");
				for (ImovelBaseDTO ibdto : publicidades) {

					ImovelPublicidadeVO ipvo = new ImovelPublicidadeVO();
					ipvo.id = ibdto.id;
					ipvo.qtdeVisitas = ibdto.qtdeVisitas;
					ipvo.nomeImagemVideo = root_fotos + ibdto.idCliente + "/" + ipvo.id + "/" + ((ImovelPublicidadeDTO)ibdto).nomeImagemVideo;
					ipvo.descricaoUF = ((ImovelPublicidadeDTO)ibdto).descricaoUF;
					ipvo.descricaoGeral = ibdto.descricaoGeral;
					ipvo.idCliente = ibdto.idCliente;
					ipvo.dataCadastro = ibdto.dataCadastro;
					retorno.add(ipvo);
					
					logger.debug("             id:" + ipvo.id);
					logger.debug("    qtdeVisitas:" + ipvo.qtdeVisitas);
					logger.debug("nomeImagemVideo:" + ipvo.nomeImagemVideo);
					logger.debug("    descricaoUF:" + ipvo.descricaoUF);
					logger.debug(" descricaoGeral:" + ipvo.descricaoGeral);
					logger.debug("      idCliente:" + ipvo.idCliente);
					logger.debug("   dataCadastro:" + ipvo.dataCadastro);
				}
				
			} else {
				logger.info("Não foram encontradas publicidades para destaque.");
			}
		} catch (AlugueRelaxeException are){
			logger.error(are.getMessage());
			are.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return retorno;
	}

}
