
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateNovoCodigoAcesso extends Template {


	public TemplateNovoCodigoAcesso(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-novo-codigo-acesso.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TNCA_TAG_CODIGO_ACESSO)) {
				this.adicionarParametro(TemplateConstantes.TNCA_TAG_CODIGO_ACESSO, conteudo.get(TemplateConstantes.TNCA_TAG_CODIGO_ACESSO));
			}
		}
	}
}

