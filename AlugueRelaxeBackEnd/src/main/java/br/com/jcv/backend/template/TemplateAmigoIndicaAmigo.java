package br.com.jcv.backend.template;

import java.util.Map;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * Template para inscricao de promocao
 * @author Julio
 *
 */
public class TemplateAmigoIndicaAmigo extends Template {

	public TemplateAmigoIndicaAmigo(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-promocao-amigo-indica-amigo.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TPAIA_TAG_LINK_ATIVA_PROMOCAO)) {
				this.adicionarParametro(TemplateConstantes.TPAIA_TAG_LINK_ATIVA_PROMOCAO, conteudo.get(TemplateConstantes.TPAIA_TAG_LINK_ATIVA_PROMOCAO));
			}
			if (conteudo.containsKey(TemplateConstantes.TPAIA_TAG_LINK_REGULAMENTO)) {
				this.adicionarParametro(TemplateConstantes.TPAIA_TAG_LINK_REGULAMENTO, conteudo.get(TemplateConstantes.TPAIA_TAG_LINK_REGULAMENTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_AMIGO)) {
				this.adicionarParametro(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_AMIGO, conteudo.get(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_AMIGO));
			}
			if (conteudo.containsKey(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO)) {
				this.adicionarParametro(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO, conteudo.get(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO));
			}
			if (conteudo.containsKey(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO_EMAIL)) {
				this.adicionarParametro(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO_EMAIL, conteudo.get(TemplateConstantes.TPAIA_TAG_NOVO_ASSINANTE_PROMOCAO_EMAIL));
			}
			if (conteudo.containsKey(TemplateConstantes.TPAIA_TAG_PROMOCAO_ATIVA)) {
				this.adicionarParametro(TemplateConstantes.TPAIA_TAG_PROMOCAO_ATIVA, conteudo.get(TemplateConstantes.TPAIA_TAG_PROMOCAO_ATIVA));
			}
		}
	}


}

