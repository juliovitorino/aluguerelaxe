package br.com.jcv.backend.template;

import java.util.Map;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * Template para Oferecimento de imoveis
 * @author Julio
 *
 */
public class TemplateOferecimento extends Template {

	public TemplateOferecimento(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-oferecimento.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TAGC_ID_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TAGC_ID_IMOVEL, conteudo.get(TemplateConstantes.TAGC_ID_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_NOME_VISITANTE)) {
				this.adicionarParametro(TemplateConstantes.TAGC_NOME_VISITANTE, conteudo.get(TemplateConstantes.TAGC_NOME_VISITANTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_TITULO_ANUNCIO)) {
				this.adicionarParametro(TemplateConstantes.TAGC_TITULO_ANUNCIO, conteudo.get(TemplateConstantes.TAGC_TITULO_ANUNCIO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_UF)) {
				this.adicionarParametro(TemplateConstantes.TAGC_UF, conteudo.get(TemplateConstantes.TAGC_UF));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_CIDADE)) {
				this.adicionarParametro(TemplateConstantes.TAGC_CIDADE, conteudo.get(TemplateConstantes.TAGC_CIDADE));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_LINK_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TAGC_LINK_IMOVEL, conteudo.get(TemplateConstantes.TAGC_LINK_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DATA_VISITA)) {
				this.adicionarParametro(TemplateConstantes.TAGC_DATA_VISITA, conteudo.get(TemplateConstantes.TAGC_DATA_VISITA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, conteudo.get(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_IMAGEM_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TAGC_IMAGEM_IMOVEL, conteudo.get(TemplateConstantes.TAGC_IMAGEM_IMOVEL));
			}
		}
	}


}
