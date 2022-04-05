
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateCadastroClienteImcompleto extends Template {


	public TemplateCadastroClienteImcompleto(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-cadastro-imcompleto.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TCCI_TAG_DATA_CADASTRO)) {
				this.adicionarParametro(TemplateConstantes.TCCI_TAG_DATA_CADASTRO, conteudo.get(TemplateConstantes.TCCI_TAG_DATA_CADASTRO));
			}
		}
	}
}

