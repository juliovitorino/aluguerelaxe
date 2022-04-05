
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailThreadPendenciaAprovacao extends Email {
	
	public EmailThreadPendenciaAprovacao(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_THREAD_PENDENCIA_APROVACAO);
		this.conteudo = conteudo;
	}
	
	

}

