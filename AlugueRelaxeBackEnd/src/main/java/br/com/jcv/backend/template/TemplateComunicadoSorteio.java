
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateComunicadoSorteio extends Template {


	public TemplateComunicadoSorteio(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-comunicado-publicidade.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TCS_TAG_DATA_FINAL_DE_PUBLICIDADE)) {
				this.adicionarParametro(TemplateConstantes.TCS_TAG_DATA_FINAL_DE_PUBLICIDADE, conteudo.get(TemplateConstantes.TCS_TAG_DATA_FINAL_DE_PUBLICIDADE));
			}
			if (conteudo.containsKey(TemplateConstantes.TCS_TAG_DATA_INICIO_PUBLICIDADE)) {
				this.adicionarParametro(TemplateConstantes.TCS_TAG_DATA_INICIO_PUBLICIDADE, conteudo.get(TemplateConstantes.TCS_TAG_DATA_INICIO_PUBLICIDADE));
			}
			if (conteudo.containsKey(TemplateConstantes.TCS_TAG_ID_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TCS_TAG_ID_IMOVEL, conteudo.get(TemplateConstantes.TCS_TAG_ID_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TCS_TAG_NOME_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TCS_TAG_NOME_CLIENTE, conteudo.get(TemplateConstantes.TCS_TAG_NOME_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TCS_TAG_SECAO_PUBLICIDADE)) {
				this.adicionarParametro(TemplateConstantes.TCS_TAG_SECAO_PUBLICIDADE, conteudo.get(TemplateConstantes.TCS_TAG_SECAO_PUBLICIDADE));
			}
			if (conteudo.containsKey(TemplateConstantes.TCS_TAG_TITULO_ANUNCIO)) {
				this.adicionarParametro(TemplateConstantes.TCS_TAG_TITULO_ANUNCIO, conteudo.get(TemplateConstantes.TCS_TAG_TITULO_ANUNCIO));
			}
		}
	}
}

