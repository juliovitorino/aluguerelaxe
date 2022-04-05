
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateConteudoDepoimento extends Template {


	public TemplateConteudoDepoimento(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-conteudo-depoimento.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TCD_TAG_NOME_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCD_TAG_NOME_PROPOSTO, conteudo.get(TemplateConstantes.TCD_TAG_NOME_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCD_TAG_DEPOIMENTO)) {
				this.adicionarParametro(TemplateConstantes.TCD_TAG_DEPOIMENTO, conteudo.get(TemplateConstantes.TCD_TAG_DEPOIMENTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCD_TAG_LINK_LIBERAR_DEPOIMENTO)) {
				this.adicionarParametro(TemplateConstantes.TCD_TAG_LINK_LIBERAR_DEPOIMENTO, conteudo.get(TemplateConstantes.TCD_TAG_LINK_LIBERAR_DEPOIMENTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCD_TAG_LINK_REPROVAR_DEPOIMENTO)) {
				this.adicionarParametro(TemplateConstantes.TCD_TAG_LINK_REPROVAR_DEPOIMENTO, conteudo.get(TemplateConstantes.TCD_TAG_LINK_REPROVAR_DEPOIMENTO));
			}

		
		}
	}
}

