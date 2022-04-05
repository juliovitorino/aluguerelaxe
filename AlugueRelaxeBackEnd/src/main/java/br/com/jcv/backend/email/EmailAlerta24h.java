package br.com.jcv.backend.email;

import java.util.Map;

import br.com.jcv.backend.template.TemplateConstantes;

public class EmailAlerta24h extends Email {
	
	public EmailAlerta24h(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_ALERTA_24H);
		this.conteudo = conteudo;
	}
	
	
}
