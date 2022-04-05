
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateConteudoUpload extends Template {


	public TemplateConteudoUpload(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-conteudo-upload.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TCU_TAG_LINK_REPROVAR_UPLOAD)) {
				this.adicionarParametro(TemplateConstantes.TCU_TAG_LINK_REPROVAR_UPLOAD, conteudo.get(TemplateConstantes.TCU_TAG_LINK_REPROVAR_UPLOAD));
			}
			if (conteudo.containsKey(TemplateConstantes.TCU_TAG_URL_UPLOAD_VERIFICAR)) {
				this.adicionarParametro(TemplateConstantes.TCU_TAG_URL_UPLOAD_VERIFICAR, conteudo.get(TemplateConstantes.TCU_TAG_URL_UPLOAD_VERIFICAR));
			}
		}
	}
}

