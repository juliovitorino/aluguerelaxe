
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailAvaliacaoReserva extends Email {
	
	public EmailAvaliacaoReserva(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_AVALIACAO_RESERVA);
		this.conteudo = conteudo;
	}
	
	

}