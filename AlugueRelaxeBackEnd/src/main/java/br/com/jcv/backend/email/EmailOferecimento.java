package br.com.jcv.backend.email;

import java.util.Map;

import br.com.jcv.backend.template.TemplateConstantes;

public class EmailOferecimento extends Email {
	
	public EmailOferecimento(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_OFERECIMENTO);
		this.conteudo = conteudo;
	}
	
	
}
