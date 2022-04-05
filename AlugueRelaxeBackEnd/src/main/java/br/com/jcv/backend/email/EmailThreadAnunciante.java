package br.com.jcv.backend.email;

import java.util.Map;

import br.com.jcv.backend.template.TemplateConstantes;

public class EmailThreadAnunciante extends Email {
	
	public EmailThreadAnunciante(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_THREAD_ANUNCIANTE);
		this.conteudo = conteudo;
	}
	
	
}
