
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailContaAtivada extends Email {
	
	public EmailContaAtivada(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_CONTA_ATIVADA);
		this.conteudo = conteudo;
	}
	
	

}

