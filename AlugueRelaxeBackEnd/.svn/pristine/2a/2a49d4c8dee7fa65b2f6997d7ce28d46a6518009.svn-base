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
public class TemplateInstrucoesCaptarAutorizacao extends Template {

	public TemplateInstrucoesCaptarAutorizacao(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-instrucoes-captar-autorizacao.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_TELEFONE)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_TELEFONE, conteudo.get(TemplateConstantes.TICA_TAG_TELEFONE));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_EMAIL_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_EMAIL_CONTATO, conteudo.get(TemplateConstantes.TICA_TAG_EMAIL_CONTATO));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_NOME_PROPRIETARIO)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_NOME_PROPRIETARIO, conteudo.get(TemplateConstantes.TICA_TAG_NOME_PROPRIETARIO));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_PROPRIETARIO_TELEFONE)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_PROPRIETARIO_TELEFONE, conteudo.get(TemplateConstantes.TICA_TAG_PROPRIETARIO_TELEFONE));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_TITULO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_TITULO_IMOVEL, conteudo.get(TemplateConstantes.TICA_TAG_TITULO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_ENDERECO_COMPLETO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_ENDERECO_COMPLETO_IMOVEL, conteudo.get(TemplateConstantes.TICA_TAG_ENDERECO_COMPLETO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_NOME_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_NOME_CONTATO, conteudo.get(TemplateConstantes.TICA_TAG_NOME_CONTATO));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_CIDADE_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_CIDADE_CONTATO, conteudo.get(TemplateConstantes.TICA_TAG_CIDADE_CONTATO));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_UF_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_UF_CONTATO, conteudo.get(TemplateConstantes.TICA_TAG_UF_CONTATO));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_DATA_CHECKIN_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_DATA_CHECKIN_CONTATO, conteudo.get(TemplateConstantes.TICA_TAG_DATA_CHECKIN_CONTATO));
			}
			if (conteudo.containsKey(TemplateConstantes.TICA_TAG_DATA_CHECKOUT_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TICA_TAG_DATA_CHECKOUT_CONTATO, conteudo.get(TemplateConstantes.TICA_TAG_DATA_CHECKOUT_CONTATO));
			}
		}
	}

}

