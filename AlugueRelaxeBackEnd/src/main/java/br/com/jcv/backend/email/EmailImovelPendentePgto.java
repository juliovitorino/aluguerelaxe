
package br.com.jcv.backend.email;

import br.com.jcv.backend.template.TemplateConstantes;

import java.util.Map;

public class EmailImovelPendentePgto extends Email {
	
	public EmailImovelPendentePgto(Map<String,String> conteudo) {
		super(TemplateConstantes.TEMPLATE_IMOVEL_PENDENTE_PGTO);
		this.conteudo = conteudo;
	}
}

