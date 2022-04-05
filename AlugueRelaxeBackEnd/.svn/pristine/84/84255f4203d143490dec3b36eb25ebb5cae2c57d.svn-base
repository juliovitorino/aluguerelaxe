package br.com.jcv.backend.html.properta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.jcv.backend.buscapagina.EnvelopePaginacaoDTO;
import br.com.jcv.backend.chain.Chain;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.template.Template;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class PropertaPaginacaoChain extends AbstractPropertaChain implements
		Chain<EnvelopePaginacaoDTO<ImovelFichaCompletaDTO>> {
		
		
	public void execute(EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> context) {
		List<ImovelFichaCompletaDTO> lst = null;
		try {
		
			// Recupera a lista de imoveis do envelope
			lst = context.lst;
			
			DateManagerBase agora = DateManagerBase.getDateManagerInstance(System.currentTimeMillis());
			
			String pathPagina = VariavelCache.getInstance().getValor(VariavelConstantes.PATH_PROPERTA_PAGINACAO);
			pathPagina = StringUtil.replaceStringNew(pathPagina, "${ANO}", agora.getDateCustom("yyyy"));
			pathPagina = StringUtil.replaceStringNew(pathPagina, "${MES}", agora.getDateCustom("MM"));
			pathPagina = StringUtil.replaceStringNew(pathPagina, "${DIA}", agora.getDateCustom("dd"));
			pathPagina = StringUtil.replaceStringNew(pathPagina, "${JSID}", context.jsid);
			pathPagina = StringUtil.replaceStringNew(pathPagina, TemplateConstantes.TAGC_LANGUAGE, VariavelCache.getInstance().getValor(VariavelConstantes.LANGUAGE_PT_BR));
			pathPagina = StringUtil.replaceStringNew(pathPagina, "${PAGINA}",String.valueOf(context.pagina));
			
			//------------------------------------------------------
			// Cria a pasta de HTMLs estaticos se houver necessidade
			//------------------------------------------------------
			File fromFile = new File(pathPagina);
			String parent = fromFile.getParent();
			
			File dir = new File(parent);
			if (!dir.exists()) {

				if (dir.mkdirs()) {
					// TODO Colocar logger
				} else  {
					throw new IOException("Não foi possível criar diretório de ficha estatica " + parent);
				}
			}
					
			if ((lst != null) && (lst.size() > 0)){
				StringBuilder htmlColecaoImovel = new StringBuilder();
				
				for ( ImovelFichaCompletaDTO ifcdto : lst){
					htmlColecaoImovel.append(this.getHTMLImovelItem(ifcdto));
				}
				
				//------------------------------------------------------
				// Monta para com conteudo para substituicao de tags
				//------------------------------------------------------
				
				Map<String,String> conteudo = new HashMap<String, String>();
				conteudo.put(TemplateConstantes.TAGC_URL_HOME_PROPERTA, VariavelCache.getInstance().getValor(VariavelConstantes.URL_HOME_PROPERTA));
				conteudo.put(TemplateConstantes.TAGC_PROPERTA_HEADER_TOP, super.getHTMLPropertaHeaderTop(Constantes.LANGUAGE_PT_BR));
				conteudo.put(TemplateConstantes.TAGC_PROPERTA_FOOTER_WRAPPER, super.getHTMLPropertaFooterWrapper(Constantes.LANGUAGE_PT_BR));
				conteudo.put(TemplateConstantes.TAGC_PROPERTA_FOOTER_JAVASCRIPT, super.getHTMLPropertaFooterJS());
				conteudo.put(TemplateConstantes.TAGC_SECAO_HEAD, super.getHTMLPropertaSecaoHead());
				conteudo.put(TemplateConstantes.TAGC_TITULO_HTML, "Definir o titulo");
				conteudo.put(TemplateConstantes.TAGC_COLECAO_PROPRIEDADES, htmlColecaoImovel.toString());
				conteudo.put(TemplateConstantes.TAGC_NAVEGADOR_PAGINAS, this.getHTMLPaginacao(context));
				

				// Obtem o template
				Template template = TemplateFactory.getInstance(conteudo, TemplateConstantes.TEMPLATE_PROPERTA_PAGINACAO);
				
				FileOutputStream fos = new FileOutputStream(pathPagina);     
				fos.write(template.getHtmlTemplate().getBytes());     
				fos.close();
				
				// Endereco da URL para roteamento
				/*
				String urlPagina = VariavelCache.getInstance().getValor(VariavelConstantes.URL_PROPERTA_PAGINACAO);
				urlPagina = StringUtil.replaceStringNew(urlPagina, "${ANO}", agora.getDateCustom("yyyy"));
				urlPagina = StringUtil.replaceStringNew(urlPagina, "${MES}", agora.getDateCustom("MM"));
				urlPagina = StringUtil.replaceStringNew(urlPagina, "${DIA}", agora.getDateCustom("dd"));
				urlPagina = StringUtil.replaceStringNew(urlPagina, "${JSID}", context.jsid);
				urlPagina = StringUtil.replaceStringNew(urlPagina, TemplateConstantes.TAGC_LANGUAGE, VariavelCache.getInstance().getValor(VariavelConstantes.LANGUAGE_PT_BR));
				urlPagina = StringUtil.replaceStringNew(urlPagina, "${PAGINA}",String.valueOf(context.pagina));

				context.urlPaginacao = urlPagina;
				*/
				
				// Vai para o HTML gerado novinho em filha
				context.urlPaginacao = this.getUrlRoteamento(context.pagina, context.jsid);
				
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getHTMLImovelItem(ImovelFichaCompletaDTO ifcdto){
		final String IMOVEL_ITEM = 
		"<div class=\"property span9\">" +
			"<div class=\"row\">" +
				"<div class=\"span3\">" +
					"<div class=\"image\">" +
						"<div class=\"content\">" +
							"<a href=\"${LINK_IMOVEL}\">" +
								"<img width=\"570\" height=\"425\"" +
									 "src=\"${FOTOS_ITEM}\"" +
									 "class=\"thumbnail-image\" alt=\"15\">" +
							"</a>" +
						"</div>" +
						"<!-- /.content -->" +
					"</div>" +
					"<!-- /.image -->" +
				"</div>" +

			   "<div class=\"body span6\">" +
					"<div class=\"title-price row\">" +
						"<div class=\"title span4\">" +
							"<h2><a href=\"${LINK_IMOVEL}\">${TITULO_ANUNCIO}</a></h2>" +
						"</div>" +
						"<!-- /.title -->" +
/*
						"<div class=\"price\">" +
							"87,000 €" +
						"</div>" +
						"<!-- /.price -->" +
*/						
					"</div>" +
					"<!-- /.title -->" +

					"<div class=\"location\">${CIDADE} - ${UF}</div>" +
					"<!-- /.location -->" +

					"<div class=\"body\">" +
						"<p>${DESCRICAO_GERAL_IMOVEL}</p>" +
					"</div>" +
					"<!-- /.body -->" +

					"<div class=\"property-info clearfix\">" +
						"<div class=\"area\">" +
							"<i class=\"icon icon-normal-cursor-scale-up\"></i>" +
							"800m<sup>2</sup>" +
						"</div>" +
						"<!-- /.area -->" +

						"<div class=\"bedrooms\">" +
							"<i class=\"icon icon-normal-bed\"></i>" +
							"${QUARTOS}" +
						"</div>" +
						"<!-- /.bedrooms -->" +

					   "<div class=\"bathrooms\">" +
							"<i class=\"icon icon-normal-shower\"></i>" +
							"${BANHEIROS}" +
						"</div>" +
						"<!-- /.bathrooms -->" +

						"<div class=\"more-info\">" +
							"<a href=\"${LINK_IMOVEL}\">Mais Info<i class=\"icon icon-normal-right-arrow-circle\"></i></a>" +
						"</div>" +
					"</div>" +
					"<!-- /.info -->" +
				"</div>" +
				"<!-- /.body -->" +
			"</div>" +
			"<!-- /.row -->" +
		"</div>" +
		"<!-- /.property -->";
		
		String html = new String(IMOVEL_ITEM);
		html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_FOTOS_ITEM, this.getUrlFotoPreview(ifcdto));
		html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_BANHEIROS, String.valueOf(ifcdto.imovel.qtdeBanheiros));
		html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_QUARTOS, String.valueOf(ifcdto.imovel.qtdeQuartos));
		html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
		html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, ifcdto.imovel.descricaoGeral);
		html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_CIDADE, ifcdto.imovel.endereco.cidade);
		html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_UF, ifcdto.imovel.endereco.uf);
		html = StringUtil.replaceStringNew(html, TemplateConstantes.TAGC_LINK_IMOVEL, this.getUrlFichaImovel(ifcdto));
		return html;
	}

	private String getUrlRoteamento(int pagina, String jsid){
		DateManagerBase agora = DateManagerBase.getDateManagerInstance();
		String urlPagina = "";
		try {
			urlPagina = VariavelCache.getInstance().getValor(VariavelConstantes.URL_PROPERTA_PAGINACAO);
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${ANO}", agora.getDateCustom("yyyy"));
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${MES}", agora.getDateCustom("MM"));
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${DIA}", agora.getDateCustom("dd"));
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${JSID}", jsid);
			urlPagina = StringUtil.replaceStringNew(urlPagina, TemplateConstantes.TAGC_LANGUAGE, VariavelCache.getInstance().getValor(VariavelConstantes.LANGUAGE_PT_BR));
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${PAGINA}",String.valueOf(pagina));
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlPagina;
	}
	
	private String getUrlRoteamento(EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> context){
		DateManagerBase agora = DateManagerBase.getDateManagerInstance();
		String urlPagina = "";
		try {
			urlPagina = VariavelCache.getInstance().getValor(VariavelConstantes.URL_PROPERTA_PAGINACAO);
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${ANO}", agora.getDateCustom("yyyy"));
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${MES}", agora.getDateCustom("MM"));
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${DIA}", agora.getDateCustom("dd"));
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${JSID}", context.jsid);
			urlPagina = StringUtil.replaceStringNew(urlPagina, TemplateConstantes.TAGC_LANGUAGE, VariavelCache.getInstance().getValor(VariavelConstantes.LANGUAGE_PT_BR));
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${PAGINA}",String.valueOf(context.pagina));
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlPagina;
	}
	
	private String getUrlRoteamento(int pagina){
		String urlPagina = "";
		try {
			urlPagina = VariavelCache.getInstance().getValor(VariavelConstantes.URL_PROPERTA_PAGINACAO_BUSCA);
			urlPagina = StringUtil.replaceStringNew(urlPagina, "${PAGINA}",String.valueOf(pagina));
		} catch (AlugueRelaxeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlPagina;
	}
	
	
	private String getHTMLPaginacao(EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> context, int old){
	
		final String HTML_PRIMEIRO = "<li><a href=\"${URL_NAVEGADOR}\">Primeiro</a></li>";
		final String HTML_ANTERIOR = "<li><a href=\"${URL_NAVEGADOR}\">Anterior</a></li>";
		final String HTML_PROXIMO = "<li><a href=\"${URL_NAVEGADOR}\">Proximo</a></li>";
		final String HTML_ULTIMO = "<li><a href=\"${URL_NAVEGADOR}\">Ultimo</a></li>";
		final String HTML_BOTAO_PAGINA_ITEM = "<li><a href=\"${URL_NAVEGADOR}\">${PAGINA_ITEM}</a></li>";
		final String HTML_BOTAO_PAGINA_ITEM_ATIVO = "<li class=\"active\"><a href=\"${URL_NAVEGADOR}\">${PAGINA_ITEM}</a></li>";
		
		final String HTML_NAVEGADOR =
		"<div class=\"pagination pagination-centered\">"+
			"<ul class=\"unstyled\">"+
				"${BOTOES_NAVEGACAO}"+
			"</ul>"+
		"</div>";
		
		String html = "";
		// Somente cria navegador para mais de 1 pagina
		
		if (context.totalPaginas > 1){
			html = new String(HTML_NAVEGADOR);
			String btn = "";
			int b = 0;
			StringBuilder btnNav = new StringBuilder();
			for (int i = context.pagina; i < context.totalPaginas; i++, b++){
				// Somente apresenta 5 botoes
				if (b > 4) {
					break;
				}
				
				// Controle dos botoes iniciais (FIRST e PREVIOUS)
				btn = new String(HTML_PRIMEIRO);
				btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(1));
				btnNav.append(btn);

				if (context.pagina > 1){
					btn = new String(HTML_ANTERIOR);
					btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(context.pagina - 1));
					btnNav.append(btn);
				}
				
				btn = new String(i == context.pagina ? HTML_BOTAO_PAGINA_ITEM_ATIVO : HTML_BOTAO_PAGINA_ITEM);
				btn = StringUtil.replaceStringNew(btn, "${PAGINA_ITEM}",String.valueOf(i));
				btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(i));
				btnNav.append(btn);
				
			}

			if (context.pagina > 1){
				btn = new String(HTML_PROXIMO);
				btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(context.pagina + 1));
				btnNav.append(btn);
			}

			btn = new String(HTML_ULTIMO);
			btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(context.totalPaginas));
			btnNav.append(btn);


			html = StringUtil.replaceStringNew(html, "${BOTOES_NAVEGACAO}",btnNav.toString());
		}
		
		return html;
	}
	
	
	private String getHTMLPaginacao(EnvelopePaginacaoDTO<ImovelFichaCompletaDTO> context){
	
		final String HTML_PRIMEIRO = "<li><a href=\"${URL_NAVEGADOR}\">Primeiro</a></li>";
		final String HTML_ANTERIOR = "<li><a href=\"${URL_NAVEGADOR}\">Anterior</a></li>";
		final String HTML_PROXIMO = "<li><a href=\"${URL_NAVEGADOR}\">Proximo</a></li>";
		final String HTML_ULTIMO = "<li><a href=\"${URL_NAVEGADOR}\">Ultimo</a></li>";
		final String HTML_BOTAO_PAGINA_ITEM = "<li><a href=\"${URL_NAVEGADOR}\">${PAGINA_ITEM}</a></li>";
		final String HTML_BOTAO_PAGINA_ITEM_ATIVO = "<li class=\"active\"><a href=\"${URL_NAVEGADOR}\">${PAGINA_ITEM}</a></li>";
		
		final String HTML_NAVEGADOR =
		"<div class=\"pagination pagination-centered\">"+
			"<ul class=\"unstyled\">"+
				"${BOTOES_NAVEGACAO}"+
			"</ul>"+
		"</div>";
		
		String html = "";
		// Somente cria navegador para mais de 1 pagina
		
		if (context.totalPaginas > 1){
			html = new String(HTML_NAVEGADOR);
			String btn = "";
			int b = 0;
			StringBuilder btnNav = new StringBuilder();
			
			for (int i = context.pagina; i <= context.totalPaginas; i++, b++){
				// Somente apresenta 5 botoes
				if (b > 4) {
					break;
				}
				
				// Controle dos botoes iniciais (FIRST e PREVIOUS)

				if ((context.pagina > 1) && (b == 0)){
					btn = new String(HTML_PRIMEIRO);
					btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(1));
					btnNav.append(btn);
					
					btn = new String(HTML_ANTERIOR);
					btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(context.pagina - 1));
					btnNav.append(btn);
				}
				
				btn = new String(i == context.pagina ? HTML_BOTAO_PAGINA_ITEM_ATIVO : HTML_BOTAO_PAGINA_ITEM);
				btn = StringUtil.replaceStringNew(btn, "${PAGINA_ITEM}",String.valueOf(i));
				btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(i));
				btnNav.append(btn);
				
			}

			if (context.pagina < context.totalPaginas){
				btn = new String(HTML_PROXIMO);
				btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(context.pagina + 1));
				btnNav.append(btn);

				btn = new String(HTML_ULTIMO);
				btn = StringUtil.replaceStringNew(btn, "${URL_NAVEGADOR}",this.getUrlRoteamento(context.totalPaginas));
				btnNav.append(btn);
			}

			html = StringUtil.replaceStringNew(html, "${BOTOES_NAVEGACAO}",btnNav.toString());
		}
		
		return html;
	}
	
}
