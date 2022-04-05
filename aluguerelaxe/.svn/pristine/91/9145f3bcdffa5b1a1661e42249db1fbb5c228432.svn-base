package br.com.jcv.aluguerelaxe.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.publicidade.ImovelPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.superpanel.SuperPanelRPC;
import br.com.jcv.aluguerelaxe.client.superpanel.superbanner.SuperBannerVO;
import br.com.jcv.aluguerelaxe.server.cache.SuperBannerCache;
import br.com.jcv.aluguerelaxe.server.datatransferobject.DTOImovelFichaCompletaImpl;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SuperPanelRPCImpl extends RemoteServiceServlet implements
		SuperPanelRPC {

	private static final long serialVersionUID = 5770651993628634978L;
	
	public SuperBannerVO getSuperBannerPP() throws AlugueRelaxeFrontException {
		SuperBannerVO vo = null;
		
		//----------------------------------------------------------
		// Obtem o caminho root_fotos das imagens dentro do web.xml
		//----------------------------------------------------------
		ServletContext context = getServletContext();
		String root_fotos = context.getInitParameter(br.com.jcv.aluguerelaxe.client.Constantes.ROOT_FOTOS);

		try {
			// Obtem indicador de SuperBanner
			vo = new SuperBannerVO();
			vo.indSuperBanner = (VariavelCache.getInstance().getValor(VariavelConstantes.INDICADOR_SUPER_BANNER_PP).equals(Constantes.ON));
			vo.html = getTemplatePromocaoPadraoSuperPanel();
			vo.lstipvo = getImovelSuperBanner();
			vo.lstifcvo = getImovelFichaCompleta(vo.lstipvo, root_fotos);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		return vo;
	}

	private List<ImovelFichaCompletaVO> getImovelFichaCompleta(
			List<ImovelPublicidadeVO> lstipvo, String path) {

		List<ImovelFichaCompletaVO> lstifcvo = null;
		try {
			if (lstipvo != null){
				lstifcvo = new ArrayList<ImovelFichaCompletaVO>();
				ImovelService<?> is = new ImovelServiceImpl<ImovelDTO>();
				for(ImovelPublicidadeVO vo : lstipvo){
					DTOImovelFichaCompletaImpl dtoTransf = new DTOImovelFichaCompletaImpl();
					dtoTransf.setRootPath(path);
					
					ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(vo.id);
					ImovelFichaCompletaVO ifcvo = dtoTransf.getDataTransferObject(ifcdto);
					lstifcvo.add(ifcvo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lstifcvo;
	}

	private List<ImovelPublicidadeVO> getImovelSuperBanner() throws AlugueRelaxeFrontException {
		return SuperBannerCache.getInstance(this.getServletContext()).getSuperBannerCache();
	}
	
	private String getTemplatePromocaoPadraoSuperPanel()
			throws AlugueRelaxeFrontException {
		Template promocaoPadraoTemplate = null;
		try {
			int id = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PROMOCAO)); 
			promocaoPadraoTemplate = TemplateFactory.getInstance(id, null);
//			promocaoPadraoTemplate = TemplateFactory.getInstance(TemplateConstantes.TEMPLATE_PROMOCAO_PADRAO, null);
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		return promocaoPadraoTemplate.getHtmlTemplate();
	}

	public SuperBannerVO getSuperBannerDD() throws AlugueRelaxeFrontException {
		SuperBannerVO vo = new SuperBannerVO();
		try {
			Template desktopTemplate = TemplateFactory.getInstance(TemplateConstantes.TEMPLATE_DESKTOP, null);
			vo.indSuperBanner = (VariavelCache.getInstance().getValor(VariavelConstantes.INDICADOR_SUPER_BANNER_DD).equals(Constantes.ON));
			vo.html = desktopTemplate.getHtmlTemplate();
		} catch (AlugueRelaxeException e) {
			if (e.getObjeto() instanceof List){
				throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem(), (List)e.getObjeto()); 
			}
			throw new AlugueRelaxeFrontException(e.getCodigoErro() + " - " + e.getMensagem());
		}
		return vo;
	}

}
