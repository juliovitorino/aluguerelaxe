package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateFaleConosco extends Template {


	public TemplateFaleConosco(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-fale-conosco.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TFC_TAG_NOME)) {
				this.adicionarParametro(TemplateConstantes.TFC_TAG_NOME, conteudo.get(TemplateConstantes.TFC_TAG_NOME));
			}
			if (conteudo.containsKey(TemplateConstantes.TFC_TAG_EMAIL)) {
				this.adicionarParametro(TemplateConstantes.TFC_TAG_EMAIL, conteudo.get(TemplateConstantes.TFC_TAG_EMAIL));
			}
			if (conteudo.containsKey(TemplateConstantes.TFC_TAG_ASSUNTO)) {
				this.adicionarParametro(TemplateConstantes.TFC_TAG_ASSUNTO, conteudo.get(TemplateConstantes.TFC_TAG_ASSUNTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TFC_TAG_TOPICO)) {
				this.adicionarParametro(TemplateConstantes.TFC_TAG_TOPICO, conteudo.get(TemplateConstantes.TFC_TAG_TOPICO));
			}
			if (conteudo.containsKey(TemplateConstantes.TFC_TAG_MENSAGEM)) {
				this.adicionarParametro(TemplateConstantes.TFC_TAG_MENSAGEM, conteudo.get(TemplateConstantes.TFC_TAG_MENSAGEM));
			}
		}
	}
}

