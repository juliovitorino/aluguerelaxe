
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailConteudoUpload extends Email {
	
	public EmailConteudoUpload(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_CONTEUDO_UPLOAD);
		this.conteudo = conteudo;
	}
	
	

}

