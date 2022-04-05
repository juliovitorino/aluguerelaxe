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
public class TemplatePromocaoAtivada extends Template {

	public TemplatePromocaoAtivada(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-promocao-ativada-com-sucesso.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TPAS_TAG_DATA_SORTEIO_PROMOCAO)) {
				this.adicionarParametro(TemplateConstantes.TPAS_TAG_DATA_SORTEIO_PROMOCAO, conteudo.get(TemplateConstantes.TPAS_TAG_DATA_SORTEIO_PROMOCAO));
			}
			if (conteudo.containsKey(TemplateConstantes.TPAS_TAG_LINK_REGULAMENTO)) {
				this.adicionarParametro(TemplateConstantes.TPAS_TAG_LINK_REGULAMENTO, conteudo.get(TemplateConstantes.TPAS_TAG_LINK_REGULAMENTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TPAS_TAG_NOVO_ASSINANTE_AMIGO)) {
				this.adicionarParametro(TemplateConstantes.TPAS_TAG_NOVO_ASSINANTE_AMIGO, conteudo.get(TemplateConstantes.TPAS_TAG_NOVO_ASSINANTE_AMIGO));
			}
			if (conteudo.containsKey(TemplateConstantes.TPAS_TAG_NUMERO_DA_SORTE_1)) {
				this.adicionarParametro(TemplateConstantes.TPAS_TAG_NUMERO_DA_SORTE_1, conteudo.get(TemplateConstantes.TPAS_TAG_NUMERO_DA_SORTE_1));
			}
			if (conteudo.containsKey(TemplateConstantes.TPAS_TAG_PROMOCAO_ATIVA)) {
				this.adicionarParametro(TemplateConstantes.TPAS_TAG_PROMOCAO_ATIVA, conteudo.get(TemplateConstantes.TPAS_TAG_PROMOCAO_ATIVA));
			}
		}
	}


}

