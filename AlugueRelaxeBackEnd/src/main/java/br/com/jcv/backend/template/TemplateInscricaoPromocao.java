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
public class TemplateInscricaoPromocao extends Template {

	public TemplateInscricaoPromocao(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-novo-assinante-promocao.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TNAP_TAG_LINK_ATIVA_PROMOCAO)) {
				this.adicionarParametro(TemplateConstantes.TNAP_TAG_LINK_ATIVA_PROMOCAO, conteudo.get(TemplateConstantes.TNAP_TAG_LINK_ATIVA_PROMOCAO));
			}
			if (conteudo.containsKey(TemplateConstantes.TNAP_TAG_LINK_REGULAMENTO)) {
				this.adicionarParametro(TemplateConstantes.TNAP_TAG_LINK_REGULAMENTO, conteudo.get(TemplateConstantes.TNAP_TAG_LINK_REGULAMENTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TNAP_TAG_NOVO_ASSINANTE_PROMOCAO)) {
				this.adicionarParametro(TemplateConstantes.TNAP_TAG_NOVO_ASSINANTE_PROMOCAO, conteudo.get(TemplateConstantes.TNAP_TAG_NOVO_ASSINANTE_PROMOCAO));
			}
			if (conteudo.containsKey(TemplateConstantes.TNAP_TAG_PROMOCAO_ATIVA)) {
				this.adicionarParametro(TemplateConstantes.TNAP_TAG_PROMOCAO_ATIVA, conteudo.get(TemplateConstantes.TNAP_TAG_PROMOCAO_ATIVA));
			}
		}
	}


}
