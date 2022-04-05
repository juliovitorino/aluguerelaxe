package br.com.jcv.backend.template;

import java.util.Map;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * Template para inscricao de promocao
 * @author Julio
 *
 */
public class TemplateThreadPendenciaAprovacao extends Template {

	public TemplateThreadPendenciaAprovacao(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-thread-pendencia-aprovacao.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TAGC_DESCRICAO_PLANO)) { 
				this.adicionarParametro(TemplateConstantes.TAGC_DESCRICAO_PLANO, conteudo.get(TemplateConstantes.TAGC_DESCRICAO_PLANO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DATA_DUVIDA)) {
				this.adicionarParametro(TemplateConstantes.TAGC_DATA_DUVIDA, conteudo.get(TemplateConstantes.TAGC_DATA_DUVIDA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_THREAD_EDITADA)) {
				this.adicionarParametro(TemplateConstantes.TAGC_THREAD_EDITADA, conteudo.get(TemplateConstantes.TAGC_THREAD_EDITADA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_THREAD_ID)) {
				this.adicionarParametro(TemplateConstantes.TAGC_THREAD_ID, conteudo.get(TemplateConstantes.TAGC_THREAD_ID));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_THREAD)) {
				this.adicionarParametro(TemplateConstantes.TAGC_THREAD, conteudo.get(TemplateConstantes.TAGC_THREAD));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_NOME)) {
				this.adicionarParametro(TemplateConstantes.TAGC_NOME, conteudo.get(TemplateConstantes.TAGC_NOME));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL, conteudo.get(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO)) {
				this.adicionarParametro(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO, conteudo.get(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_UF)) {
				this.adicionarParametro(TemplateConstantes.TAGC_UF, conteudo.get(TemplateConstantes.TAGC_UF));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DATA_VISITA)) {
				this.adicionarParametro(TemplateConstantes.TAGC_DATA_VISITA, conteudo.get(TemplateConstantes.TAGC_DATA_VISITA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_CIDADE)) {
				this.adicionarParametro(TemplateConstantes.TAGC_CIDADE, conteudo.get(TemplateConstantes.TAGC_CIDADE));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_LINK_ACOMPANHAMENTO_PORTAL)) {
				this.adicionarParametro(TemplateConstantes.TAGC_LINK_ACOMPANHAMENTO_PORTAL, conteudo.get(TemplateConstantes.TAGC_LINK_ACOMPANHAMENTO_PORTAL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_LINK_APROVAR)) {
				this.adicionarParametro(TemplateConstantes.TAGC_LINK_APROVAR, conteudo.get(TemplateConstantes.TAGC_LINK_APROVAR));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_LINK_APROVAR_EDITADO)) {
				this.adicionarParametro(TemplateConstantes.TAGC_LINK_APROVAR_EDITADO, conteudo.get(TemplateConstantes.TAGC_LINK_APROVAR_EDITADO));
			}
		}
	}

}

