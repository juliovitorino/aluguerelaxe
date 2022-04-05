
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateNotiticacaoGaleriaFotosVazia extends Template {


	public TemplateNotiticacaoGaleriaFotosVazia(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-notificacao-galeria-fotos-vazia.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TNGFV_TAG_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TNGFV_TAG_CLIENTE, conteudo.get(TemplateConstantes.TNGFV_TAG_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TNGFV_TAG_ID_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TNGFV_TAG_ID_CLIENTE, conteudo.get(TemplateConstantes.TNGFV_TAG_ID_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TNGFV_TAG_TITULO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TNGFV_TAG_TITULO_IMOVEL, conteudo.get(TemplateConstantes.TNGFV_TAG_TITULO_IMOVEL));
			}
		}
	}
}

