
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailNotificaPlanoRenovado extends Email {
	
	public EmailNotificaPlanoRenovado(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_NOTIFICA_PLANO_RENOVADO);
		this.conteudo = conteudo;
	}
	
	

}

