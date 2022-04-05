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
public class TemplatePgtoPagSeguro extends Template {

	public TemplatePgtoPagSeguro(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-pgto-pagseguro.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TAGC_EMAIL_ALUGUE_RELAXE)) { 
				this.adicionarParametro(TemplateConstantes.TAGC_EMAIL_ALUGUE_RELAXE, conteudo.get(TemplateConstantes.TAGC_EMAIL_ALUGUE_RELAXE));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_CODIGO_PLANO)) { 
				this.adicionarParametro(TemplateConstantes.TAGC_CODIGO_PLANO, conteudo.get(TemplateConstantes.TAGC_CODIGO_PLANO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DESCRICAO_DO_PLANO)) { 
				this.adicionarParametro(TemplateConstantes.TAGC_DESCRICAO_DO_PLANO, conteudo.get(TemplateConstantes.TAGC_DESCRICAO_DO_PLANO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_VALOR_TOTAL)) { 
				this.adicionarParametro(TemplateConstantes.TAGC_VALOR_TOTAL, conteudo.get(TemplateConstantes.TAGC_VALOR_TOTAL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_ID_FATURA)) { 
				this.adicionarParametro(TemplateConstantes.TAGC_ID_FATURA, conteudo.get(TemplateConstantes.TAGC_ID_FATURA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_NOME_CLIENTE)) { 
				this.adicionarParametro(TemplateConstantes.TAGC_NOME_CLIENTE, conteudo.get(TemplateConstantes.TAGC_NOME_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_EMAIL_CLIENTE)) { 
				this.adicionarParametro(TemplateConstantes.TAGC_EMAIL_CLIENTE, conteudo.get(TemplateConstantes.TAGC_EMAIL_CLIENTE));
			}
		}
	}

}

