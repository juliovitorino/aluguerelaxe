
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateContatoAnuniciantePendente extends Template {


	public TemplateContatoAnuniciantePendente(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-contato-anunciante-pendente.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_DATA_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_DATA_CONTATO, conteudo.get(TemplateConstantes.TCAP_TAG_DATA_CONTATO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_ID_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_ID_IMOVEL, conteudo.get(TemplateConstantes.TCAP_TAG_ID_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_ID_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_ID_IMOVEL, conteudo.get(TemplateConstantes.TCAP_TAG_ID_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_ADULTOS_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_ADULTOS_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_ADULTOS_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_CRIANCAS_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_CRIANCAS_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_CRIANCAS_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_DDD_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_DDD_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_DDD_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_EMAIL_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_EMAIL_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_EMAIL_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_FONE_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_FONE_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_FONE_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_INFO_ADICIONAL_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_INFO_ADICIONAL_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_INFO_ADICIONAL_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_CIDADE_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_CIDADE_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_CIDADE_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_NOME_ANUNCIANTE)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_NOME_ANUNCIANTE, conteudo.get(TemplateConstantes.TCAP_TAG_NOME_ANUNCIANTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_UF_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_UF_PROPOSTO, conteudo.get(TemplateConstantes.TCAP_TAG_UF_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_LINK_LIBERAR_PARA_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_LINK_LIBERAR_PARA_CONTATO, conteudo.get(TemplateConstantes.TCAP_TAG_LINK_LIBERAR_PARA_CONTATO));
			}
			if (conteudo.containsKey(TemplateConstantes.TCAP_TAG_LINK_REPROVAR_PARA_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TCAP_TAG_LINK_REPROVAR_PARA_CONTATO, conteudo.get(TemplateConstantes.TCAP_TAG_LINK_REPROVAR_PARA_CONTATO));
			}
		}
	}
}

