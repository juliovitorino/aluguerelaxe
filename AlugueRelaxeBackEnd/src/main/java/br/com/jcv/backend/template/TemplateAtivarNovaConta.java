
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateAtivarNovaConta extends Template {


	public TemplateAtivarNovaConta(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-ativar-nova-conta.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TANC_TAG_CGC_CPF_NOVO_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TANC_TAG_CGC_CPF_NOVO_CLIENTE, conteudo.get(TemplateConstantes.TANC_TAG_CGC_CPF_NOVO_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TANC_TAG_EMAIL_NOVO_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TANC_TAG_EMAIL_NOVO_CLIENTE, conteudo.get(TemplateConstantes.TANC_TAG_EMAIL_NOVO_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TANC_TAG_LINK_ATIVACAO_NOVO_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TANC_TAG_LINK_ATIVACAO_NOVO_CLIENTE, conteudo.get(TemplateConstantes.TANC_TAG_LINK_ATIVACAO_NOVO_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TANC_TAG_NOME_NOVO_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TANC_TAG_NOME_NOVO_CLIENTE, conteudo.get(TemplateConstantes.TANC_TAG_NOME_NOVO_CLIENTE));
			}
		}
	}
}

