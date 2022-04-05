
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailContatoAnuncianteLiberado extends Email {
	
	public EmailContatoAnuncianteLiberado(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_CONTATO_ANUNCIANTE_LIBERADO);
		this.conteudo = conteudo;
	}
	
	

}

