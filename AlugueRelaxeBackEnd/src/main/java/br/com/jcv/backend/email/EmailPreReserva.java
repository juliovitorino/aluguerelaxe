
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailPreReserva extends Email {
	
	public EmailPreReserva(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_VALIDAR_PRE_RESERVA);
		this.conteudo = conteudo;
	}
	
	

}

