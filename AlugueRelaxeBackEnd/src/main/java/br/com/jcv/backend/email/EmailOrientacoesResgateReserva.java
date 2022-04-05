
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailOrientacoesResgateReserva extends Email {
	
	public EmailOrientacoesResgateReserva(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_ORIENTACOES_RESGATE_RESERVA);
		this.conteudo = conteudo;
	}
	
	

}