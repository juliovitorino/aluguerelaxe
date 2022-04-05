
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateAvaliacaoAnuncio extends Template {


	public TemplateAvaliacaoAnuncio(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-avaliacao-imovel.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_NOME_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_NOME_CLIENTE, conteudo.get(TemplateConstantes.TAIA_TAG_NOME_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_ID_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_ID_IMOVEL, conteudo.get(TemplateConstantes.TAIA_TAG_ID_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_TITULO_ANUNCIO)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_TITULO_ANUNCIO, conteudo.get(TemplateConstantes.TAIA_TAG_TITULO_ANUNCIO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_TIT_AVAL_FOTOGRAFIA)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_TIT_AVAL_FOTOGRAFIA, conteudo.get(TemplateConstantes.TAIA_TAG_TIT_AVAL_FOTOGRAFIA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_NOTA_AVAL_FOTOGRAFIA)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_NOTA_AVAL_FOTOGRAFIA, conteudo.get(TemplateConstantes.TAIA_TAG_NOTA_AVAL_FOTOGRAFIA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_FOTOGRAFIA)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_FOTOGRAFIA, conteudo.get(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_FOTOGRAFIA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_TIT_AVAL_QUALIDADE_TEXTO)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_TIT_AVAL_QUALIDADE_TEXTO, conteudo.get(TemplateConstantes.TAIA_TAG_TIT_AVAL_QUALIDADE_TEXTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_NOTA_AVAL_QUALIDADE_TEXTO)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_NOTA_AVAL_QUALIDADE_TEXTO, conteudo.get(TemplateConstantes.TAIA_TAG_NOTA_AVAL_QUALIDADE_TEXTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_QUALIDADE_TEXTO)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_QUALIDADE_TEXTO, conteudo.get(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_QUALIDADE_TEXTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_TIT_AVAL_INFORMACAO_RELEVANTE)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_TIT_AVAL_INFORMACAO_RELEVANTE, conteudo.get(TemplateConstantes.TAIA_TAG_TIT_AVAL_INFORMACAO_RELEVANTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_NOTA_AVAL_INFORMACAO_RELEVANTE)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_NOTA_AVAL_INFORMACAO_RELEVANTE, conteudo.get(TemplateConstantes.TAIA_TAG_NOTA_AVAL_INFORMACAO_RELEVANTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_INFORMACAO_RELEVANTE)) {
				this.adicionarParametro(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_INFORMACAO_RELEVANTE, conteudo.get(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_INFORMACAO_RELEVANTE));
			}
		}
	}
}
