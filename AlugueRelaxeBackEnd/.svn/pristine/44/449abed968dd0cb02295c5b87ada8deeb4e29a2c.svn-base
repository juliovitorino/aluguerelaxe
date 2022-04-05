package br.com.jcv.backend.html.properta;

import java.util.HashMap;
import java.util.Map;

import br.com.jcv.backend.chain.AbstractChain;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.I18n;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.mensagem.I18nCache;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public abstract class AbstractPropertaChain extends AbstractChain {
	
	public static final String INFOBOXPUSH = "infoBoxes.push(" +
	"'<div class=\"infobox clearfix\"><div class=\"close\"><img src=\"assets/img/close.png\" " +
	"alt=\"\"></div><div class=\"image\"><a href=\"${LINK_IMOVEL}\" ><img src=\"${FOTOS}\" " +
	"alt=\"${CIDADE} - ${UF}\" width=\"130px\"></a><div class=\"contract-type\">${OBJETIVO_IMOVEL}</div></div><div class=\"info\">"+
	"<div class=\"title\"><a href=\"${LINK_IMOVEL}\">${TITULO_ANUNCIO}</a></div><div class=\"location\">${CIDADE} - ${UF}</div>"+
	"<div class=\"property-info clearfix\"><div class=\"area\"><i class=\"icon icon-normal-cursor-scale-up\"></i>650m<sup>2</sup>"+
	"</div><div class=\"bedrooms\"><i class=\"icon icon-normal-bed\"></i>${QUARTOS}</div><div class=\"bathrooms\">"+
	"<i class=\"icon icon-normal-shower\"></i>${BANHEIROS}</div></div>"+
	"<div class=\"price\">(${MOEDA})${VALOR_DIARIA}</div><div class=\"link\"><a href=\"${LINK_IMOVEL}\">Ver mais detalhes</a>"+
	"</div></div></div>'" +
	");";

	protected StringBuilder sbinfoboxpush = new StringBuilder();
	protected StringBuilder sbcoord = new StringBuilder();
	protected StringBuilder sbtipos = new StringBuilder();
	protected double latcenter = 0;
	protected double longcenter = 0;
	
	public AbstractPropertaChain() {
		super();
	}
	
	protected void init(){
		sbinfoboxpush = new StringBuilder();
		sbcoord = new StringBuilder();
		sbtipos = new StringBuilder();
		latcenter = 0;
		longcenter = 0;
	}
	
	protected String getHTMLPropertaHeaderTop(String lang) {
		//---------------------------------------------------------
		// Obtem o template header default para as paginas Properta
		//---------------------------------------------------------
		Map<String,String> conteudo = new HashMap<String, String>();
		try {
			conteudo.put(TemplateConstantes.TAGC_URL_HOME_PROPERTA, VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA));
		} catch (AlugueRelaxeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//---------------------------------------------------------
		// Aplica I18N em algumas constantes
		//---------------------------------------------------------
		Template template = null;
		if (lang.equals(Constantes.LANGUAGE_PT_BR)){
			try {
				conteudo.put(TemplateConstantes.TAGC_I18N_REGISTRO, I18nCache.getInstance().getMensagem(I18n.PT_REGISTRO));
				conteudo.put(TemplateConstantes.TAGC_I18N_LOGIN, I18nCache.getInstance().getMensagem(I18n.PT_LOGIN));
				conteudo.put(TemplateConstantes.TAGC_I18N_CONTATO, I18nCache.getInstance().getMensagem(I18n.PT_CONTATO));
				template = TemplateFactory.getInstance(conteudo, TemplateConstantes.TEMPLATE_PROPERTA_HEADER_TOP);
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return template.getHtmlTemplate();
	}
	
	protected String getHTMLPropertaFooterWrapper(String lang) {
		//---------------------------------------------------------
		// Obtem o template footer default para as paginas Properta
		//---------------------------------------------------------
		Map<String,String> conteudo = new HashMap<String, String>();
		try {
			conteudo.put(TemplateConstantes.TAGC_URL_HOME_PROPERTA, VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA));
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//---------------------------------------------------------
		// Aplica I18N em algumas constantes
		//---------------------------------------------------------
		Template template = null;
		if (lang.equals(Constantes.LANGUAGE_PT_BR)){
			try {
				conteudo.put(TemplateConstantes.TAGC_I18N_CONTATO, I18nCache.getInstance().getMensagem(I18n.PT_CONTATO));
				template = TemplateFactory.getInstance(conteudo,TemplateConstantes.TEMPLATE_PROPERTA_FOOTER_WRAPPER);
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return template.getHtmlTemplate();
	}

	protected String getHTMLPropertaFooterJS() {
		//-------------------------------------------------------------
		// Obtem o template footer JS default para as paginas Properta
		//-------------------------------------------------------------
		Map<String,String> conteudo = new HashMap<String, String>();
		try {
			conteudo.put(TemplateConstantes.TAGC_URL_HOME_PROPERTA, VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA));
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Template template = null;;
		try {
			template = TemplateFactory.getInstance(conteudo,TemplateConstantes.TEMPLATE_PROPERTA_FOOTER_JS);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return template.getHtmlTemplate();
	}
	
	protected String getHTMLPropertaSecaoHead() {
		//--------------------------------------------------------------------------
		// Obtem o template da secao da tag <HEAD> default para as paginas Properta
		//--------------------------------------------------------------------------
		Map<String,String> conteudo = new HashMap<String, String>();
		try {
			conteudo.put(TemplateConstantes.TAGC_URL_HOME_PROPERTA, VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA));
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Template template = null;
		try {
			template = TemplateFactory.getInstance(conteudo,TemplateConstantes.TEMPLATE_PROPERTA_SECAO_HEAD);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return template.getHtmlTemplate();
	}

	protected void getHTMLInfoboxes(ImovelFichaCompletaDTO ifcdto) {
		if ((ifcdto.geolocalizacao.latitude != 0) && (ifcdto.geolocalizacao.longitude != 0)) {
			
			//-------------
			// InfoBoxes
			//-------------
			String strtmp = new String(INFOBOXPUSH);
			
			// Link da foto
			String linkfoto = null;
			try {
				linkfoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			linkfoto = StringUtil.replaceStringNew(linkfoto,"${clienteid}",String.valueOf(ifcdto.cliente.id));
			linkfoto = StringUtil.replaceStringNew(linkfoto,"${imovelid}",String.valueOf(ifcdto.imovel.id));
			if (ifcdto.imagensImovelXG == null){
				try {
					linkfoto = StringUtil.replaceStringNew(linkfoto,"${imagemupload}",VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/property-tmp-small.png");
				} catch (AlugueRelaxeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				linkfoto = StringUtil.replaceStringNew(linkfoto,"${imagemupload}",ifcdto.imagensImovelXG.get(0).nome);
			}
			
			String linkficha = ifcdto.urlFichaImovelEstatica;
			try {
				linkficha = StringUtil.replaceStringNew(linkficha,TemplateConstantes.TAGC_LANGUAGE,VariavelCache.getInstance().getValor(VariavelConstantes.LANGUAGE_PT_BR));
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Troca tags para infobox
			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_BANHEIROS, String.valueOf(ifcdto.imovel.qtdeBanheiros));
			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_QUARTOS, String.valueOf(ifcdto.imovel.qtdeQuartos));
			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_VALOR_DIARIA, String.valueOf(ifcdto.imovel.valorTarifaBasica));
			try {
				strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_MOEDA, VariavelCache.getInstance().getValor(VariavelConstantes.MOEDA_PT_BR ) );
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_FOTOS, linkfoto);
			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_OBJETIVO_IMOVEL, "Temporada");
			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_LINK_IMOVEL, linkficha);
			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_TITULO_ANUNCIO, StringUtil.normalizar2(ifcdto.imovel.descricaoTituloAnuncio));
//			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade);
			strtmp = StringUtil.replaceStringNew(strtmp,TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
			
			// adiciona cada imovel com coordenada ao infobox (InfoView no GoogleMaps)
			sbinfoboxpush.append(strtmp);
			
			// latitude e longitude de centralizacao
			if ((latcenter == 0) && (longcenter == 0)){
				latcenter = ifcdto.geolocalizacao.latitude;
				longcenter = ifcdto.geolocalizacao.longitude;
			}
			
			// Coordenadas no formato [38.951399, -76.958463], ...
			sbcoord.append("[");
			sbcoord.append(String.valueOf(ifcdto.geolocalizacao.latitude));
			sbcoord.append(",");
			sbcoord.append(String.valueOf(ifcdto.geolocalizacao.longitude));
			sbcoord.append("],");
			
			// Tipos no formato 'family-house', 'villa', 'cottage', 'single-home', ...
			
			//------------------------------------------------------
			// Inicializa o tipo de propriedade
			//------------------------------------------------------
			Map<String,String> tipoPropriedade = new HashMap<String, String>();
			tipoPropriedade.put("C", "'single-home',");
			tipoPropriedade.put("H", "'condo',");
			tipoPropriedade.put("X", "'cottage',");
			tipoPropriedade.put("F", "'apartment',");
			tipoPropriedade.put("Z", "'cottage',");
			tipoPropriedade.put("S", "'cottage',");
			tipoPropriedade.put("L", "'cottage',");
			tipoPropriedade.put("P", "'condo',");
			tipoPropriedade.put("A", "'apartment',");			
			sbtipos.append(tipoPropriedade.get(ifcdto.imovel.indicadorTipoPropriedade));
		}
	
	}

	protected String getMapTiposImoveis() {
		// Retiras as virgulas residuais
		String stipos = sbtipos.toString();
		
		if (stipos.length() > 0){
			stipos = stipos.substring(0,stipos.length() - 1);
		}
		
		return stipos;
		
	}
	
	protected String getMapCoordenadas() {
		// Retiras as virgulas residuais
		String scoord = sbcoord.toString();
		
		if (scoord.length() > 0){
			scoord = scoord.substring(0,scoord.length() - 1);
		} else {
			scoord = "[0,0]";
		}
		
		return scoord;
	}
	
	protected String getUrlFichaImovel(ImovelFichaCompletaDTO ifcdto){
		String url = "";
		try {
			url = VariavelCache.getInstance().getValor(VariavelConstantes.URL_FICHA_IMOVEL_PROPERTA);
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		url = StringUtil.replaceStringNew(url, "${TIPO}", ifcdto.imovel.indicadorTipoPropriedadeStr.toLowerCase());
		url = StringUtil.replaceStringNew(url, "${UF}", ifcdto.imovel.endereco.uf.toLowerCase());
		url = StringUtil.replaceStringNew(url, "${CIDADE}", String.valueOf(ifcdto.imovel.endereco.codigoUfCidadeItem));
		url = StringUtil.replaceStringNew(url, "${ID_IMOVEL}", String.valueOf(ifcdto.imovel.id));
		url = StringUtil.replaceStringNew(url, TemplateConstantes.TAGC_LANGUAGE, Constantes.LANGUAGE_PT_BR);
		url = url + "/" + ifcdto.imovel.idhash + ".html";	
		return url;
	}
	
	
	protected String getUrlFotoPreview(ImovelFichaCompletaDTO ifcdto) {
		String html = "";
		
		if(ifcdto.imagensImovelXG == null) {
			try {
				html = VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA) + "assets/img/property-tmp-small.png";
			} catch (AlugueRelaxeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
		
			if (ifcdto.imagensImovelXG.size() > 0){
				String linkfoto = "";
				try {
					linkfoto = VariavelCache.getInstance().getValor(VariavelConstantes.URL_VERIFICAR_IMAGEM_UPLOAD);
				} catch (AlugueRelaxeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				linkfoto = StringUtil.replaceStringNew(linkfoto,"${clienteid}",String.valueOf(ifcdto.cliente.id));
				linkfoto = StringUtil.replaceStringNew(linkfoto,"${imovelid}",String.valueOf(ifcdto.imovel.id));
				linkfoto = StringUtil.replaceStringNew(linkfoto,"${imagemupload}",ifcdto.imagensImovelXG.get(0).nome);

				html = linkfoto;
			}
		}
	
		return html;
	}

}