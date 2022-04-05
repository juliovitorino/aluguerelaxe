package br.com.jcv.webservices.cache;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelBaseDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * <h2>PublicidadeCache</h2>
 * <p>Singleton para manter um cache de publicidade</p>
 * <p>Seu funcionamento sera da seguinte forma:<br/>
 * Ao ser criado, a instância invocara o backend solicitando as publicidades disponiveis
 * e as mantera em memoria, acelerando seu acesso posterior.<br/>Para permitir a atualizacao
 * do cache, uma data base sera mantida e ao ser modificada o cache reinvocara o backend
 * refazendo o processo.</p>
 * @author Julio Vitorino
 * @since August, 18th 2011
 */
public class PublicidadeCache {

	private String dataControle = null;
	private List<ImovelPublicidadeDTO> cachelst = null;
	private static PublicidadeCache instance = null;
	
	private static final Logger logger = Logger.getLogger(PublicidadeCache.class.getName());


	/* Construtor privado */
	private PublicidadeCache() {
		initCache();
	}
	
	public static PublicidadeCache getInstance() {
		if (instance == null) {
			synchronized (PublicidadeCache.class) {
				if (instance == null) {
					instance = new PublicidadeCache();
				}
			}
		}
		return instance;
	}
	
	public List<ImovelPublicidadeDTO> getPublicidadeCache() {
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
					logger.info("* Atualizando o cache de Primeira Pagina *");
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
			List<? extends ImovelBaseDTO> publicidades = ips.listImovelPublicidadePaginaPrincipal();
			
			// atualiza a data de controle
			DateManagerBase dm = DateManagerBase.getDateManagerInstance();
			this.dataControle = dm.getDateCustom("dd/MM/yyyy");

			// Repassa a lista do backend para o cache
			if ((publicidades != null) && (publicidades.size() > 0)) {
				this.cachelst = new ArrayList<ImovelPublicidadeDTO>();
				
				
				logger.info(publicidades.size() + " Imóvel(eis) para publicidade");
				for (ImovelBaseDTO ibdto : publicidades) {
					Map<String,String> args = new HashMap<String,String>();
					args.put("${clienteid}", String.valueOf(ibdto.idCliente));
					args.put("${imovelid}", String.valueOf(ibdto.id));
					args.put("${imagemupload}", ((ImovelPublicidadeDTO)ibdto).nomeImagemVideo);
					String root_fotos = StringUtil.replaceString(VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD), args);

					ImovelPublicidadeDTO ipvo = new ImovelPublicidadeDTO();
					ipvo.id = ibdto.id;
					ipvo.qtdeVisitas = ibdto.qtdeVisitas;
					//ipvo.nomeImagemVideo = root_fotos + ibdto.idCliente + "/" + ipvo.id + "/" + ((ImovelPublicidadeDTO)ibdto).nomeImagemVideo;
					ipvo.nomeImagemVideo = root_fotos;
					ipvo.descricaoUF = ((ImovelPublicidadeDTO)ibdto).descricaoUF;
					ipvo.descricaoGeral = ibdto.descricaoGeral;
					ipvo.idCliente = ibdto.idCliente;
					ipvo.dataCadastro = ibdto.dataCadastro;
					if (ibdto.endereco != null){
						ipvo.endereco = new EnderecoDTO();
						ipvo.endereco.cidade = ibdto.endereco.cidade;
						ipvo.endereco.uf = ibdto.endereco.uf; 
					}
					if(((ImovelPublicidadeDTO)ibdto).imagemPreferida != null){
						ipvo.imagemPreferida = new ImovelImagemVideoDTO();
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
				logger.info("* Cache de Publicidade de Primeira Pagina Iniciado *");
				logger.info("****************************************************");
			} else {
				this.cachelst = null;
				logger.info("Não foram encontradas publicidades para primeira página.");
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