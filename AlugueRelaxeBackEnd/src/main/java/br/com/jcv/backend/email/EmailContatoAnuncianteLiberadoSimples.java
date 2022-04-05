
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailContatoAnuncianteLiberadoSimples extends Email {
	
	public EmailContatoAnuncianteLiberadoSimples(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_CONTATO_ANUNCIANTE_LIBERADO_SIMPLES);
		this.conteudo = conteudo;
	}
	
	

}

