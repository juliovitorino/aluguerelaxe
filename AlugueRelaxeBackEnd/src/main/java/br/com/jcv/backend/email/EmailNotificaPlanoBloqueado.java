
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailNotificaPlanoBloqueado extends Email {
	
	public EmailNotificaPlanoBloqueado(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_NOTIFICA_PLANO_BLOQUEADO);
		this.conteudo = conteudo;
	}
	
	

}

