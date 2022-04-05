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
public class TemplateImovelPendentePgto extends Template {

	public TemplateImovelPendentePgto(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-imovel-pendente-pagamento.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TIPP_TAG_NOME_ANUNCIANTE_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIPP_TAG_NOME_ANUNCIANTE_IMOVEL, conteudo.get(TemplateConstantes.TIPP_TAG_NOME_ANUNCIANTE_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TIPP_TAG_TITULO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIPP_TAG_TITULO_IMOVEL, conteudo.get(TemplateConstantes.TIPP_TAG_TITULO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TIPP_TAG_DATA_CADASTRO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIPP_TAG_DATA_CADASTRO_IMOVEL, conteudo.get(TemplateConstantes.TIPP_TAG_DATA_CADASTRO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TIPP_TAG_NOME_PLANO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIPP_TAG_NOME_PLANO_IMOVEL, conteudo.get(TemplateConstantes.TIPP_TAG_NOME_PLANO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TIPP_TAG_DESCRICAO_PLANO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIPP_TAG_DESCRICAO_PLANO_IMOVEL, conteudo.get(TemplateConstantes.TIPP_TAG_DESCRICAO_PLANO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TIPP_TAG_VALOR_PLANO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIPP_TAG_VALOR_PLANO_IMOVEL, conteudo.get(TemplateConstantes.TIPP_TAG_VALOR_PLANO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TIPP_TAG_DATA_VALIDADE_ANUNCIO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TIPP_TAG_DATA_VALIDADE_ANUNCIO_IMOVEL, conteudo.get(TemplateConstantes.TIPP_TAG_DATA_VALIDADE_ANUNCIO_IMOVEL));
			}
		}
	}
}
