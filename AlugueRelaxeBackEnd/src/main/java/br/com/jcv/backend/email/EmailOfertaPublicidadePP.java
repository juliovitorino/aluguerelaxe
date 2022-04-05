
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailOfertaPublicidadePP extends Email {
	
	public EmailOfertaPublicidadePP(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_OFERTA_PUBLICIDADE_PP);
		this.conteudo = conteudo;
	}

}

