package br.com.jcv.backend.email;

import java.util.Map;

import br.com.jcv.backend.template.TemplateConstantes;

public class EmailThreadComentarioAnunciante extends Email {
	
	public EmailThreadComentarioAnunciante(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_THREAD_COMENTARIO_ANUNCIANTE);
		this.conteudo = conteudo;
	}
	
	
}
