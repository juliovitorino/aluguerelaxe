
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailCadastroClienteImcompleto extends Email {
	
	public EmailCadastroClienteImcompleto(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_CADASTRO_CLIENTE_IMCOMPLETO);
		this.conteudo = conteudo;
	}
	
	

}

