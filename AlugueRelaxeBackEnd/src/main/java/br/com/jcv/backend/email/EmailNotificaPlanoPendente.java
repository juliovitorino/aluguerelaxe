
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailNotificaPlanoPendente extends Email {
	
	public EmailNotificaPlanoPendente(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_NOTIFICA_PLANO_RENOVADO_PENDENTE);
		this.conteudo = conteudo;
	}
	
	

}

