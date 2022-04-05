
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailNotificaInativacao extends Email {
	
	public EmailNotificaInativacao(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_NOTIFICACAO_INATIVACAO);
		this.conteudo = conteudo;
	}
	
	

}

