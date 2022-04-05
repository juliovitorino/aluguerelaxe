
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailDepositoTransferido extends Email {
	
	public EmailDepositoTransferido(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_DEPOSITO_TRANSFERIDO);
		this.conteudo = conteudo;
	}
	
	

}

