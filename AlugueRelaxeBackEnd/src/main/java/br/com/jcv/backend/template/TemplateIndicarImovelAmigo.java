
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateIndicarImovelAmigo extends Template {


	public TemplateIndicarImovelAmigo(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-indicar-amigo.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TIIA_TAG_ID_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIIA_TAG_ID_IMOVEL, conteudo.get(TemplateConstantes.TIIA_TAG_ID_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TIIA_TAG_LINK_DO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIIA_TAG_LINK_DO_IMOVEL, conteudo.get(TemplateConstantes.TIIA_TAG_LINK_DO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TIIA_TAG_NOME_AMIGO)) {
				this.adicionarParametro(TemplateConstantes.TIIA_TAG_NOME_AMIGO, conteudo.get(TemplateConstantes.TIIA_TAG_NOME_AMIGO));
			}
			if (conteudo.containsKey(TemplateConstantes.TIIA_TAG_SEU_AMIGO)) {
				this.adicionarParametro(TemplateConstantes.TIIA_TAG_SEU_AMIGO, conteudo.get(TemplateConstantes.TIIA_TAG_SEU_AMIGO));
			}
			if (conteudo.containsKey(TemplateConstantes.TIIA_TAG_TITULO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIIA_TAG_TITULO_IMOVEL, conteudo.get(TemplateConstantes.TIIA_TAG_TITULO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TIIA_TAG_MENSAGEM)) {
				this.adicionarParametro(TemplateConstantes.TIIA_TAG_MENSAGEM, conteudo.get(TemplateConstantes.TIIA_TAG_MENSAGEM));
			}
		}
	}
}

