
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailPreReservaVisitante extends Email {
	
	public EmailPreReservaVisitante(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_VALIDAR_PRE_RESERVA_VISITANTE);
		this.conteudo = conteudo;
	}
	
	

}

