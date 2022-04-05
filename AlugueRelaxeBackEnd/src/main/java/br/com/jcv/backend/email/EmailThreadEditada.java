
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailThreadEditada extends Email {
	
	public EmailThreadEditada(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_THREAD_EDITADA);
		this.conteudo = conteudo;
	}
}

