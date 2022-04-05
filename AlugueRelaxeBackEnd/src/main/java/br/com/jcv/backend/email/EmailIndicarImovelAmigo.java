
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailIndicarImovelAmigo extends Email {
	
	public EmailIndicarImovelAmigo(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_INDICAR_AMIGO);
		this.conteudo = conteudo;
	}
	
	

}

