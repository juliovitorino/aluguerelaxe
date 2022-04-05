
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateNotificaPlanoPendente extends Template {


	public TemplateNotificaPlanoPendente(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-notifica-plano-renovado-pendente.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TAGC_NOME_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TAGC_NOME_CLIENTE, conteudo.get(TemplateConstantes.TAGC_NOME_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_ID_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TAGC_ID_IMOVEL, conteudo.get(TemplateConstantes.TAGC_ID_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_TITULO_ANUNCIO)) {
				this.adicionarParametro(TemplateConstantes.TAGC_TITULO_ANUNCIO, conteudo.get(TemplateConstantes.TAGC_TITULO_ANUNCIO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DATA_VENCIMENTO_ANUNCIO)) {
				this.adicionarParametro(TemplateConstantes.TAGC_DATA_VENCIMENTO_ANUNCIO, conteudo.get(TemplateConstantes.TAGC_DATA_VENCIMENTO_ANUNCIO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_CD_IMOVEL_PLANO_FATURA)) {
				this.adicionarParametro(TemplateConstantes.TAGC_CD_IMOVEL_PLANO_FATURA, conteudo.get(TemplateConstantes.TAGC_CD_IMOVEL_PLANO_FATURA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DESCRICAO_PLANO)) {
				this.adicionarParametro(TemplateConstantes.TAGC_DESCRICAO_PLANO, conteudo.get(TemplateConstantes.TAGC_DESCRICAO_PLANO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_VALOR_PLANO)) {
				this.adicionarParametro(TemplateConstantes.TAGC_VALOR_PLANO, conteudo.get(TemplateConstantes.TAGC_VALOR_PLANO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DATA_CADASTRO)) {
				this.adicionarParametro(TemplateConstantes.TAGC_DATA_CADASTRO, conteudo.get(TemplateConstantes.TAGC_DATA_CADASTRO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_DATA_PAGAMENTO)) {
				this.adicionarParametro(TemplateConstantes.TAGC_DATA_PAGAMENTO, conteudo.get(TemplateConstantes.TAGC_DATA_PAGAMENTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAGC_TELEFONES_CENTRAL_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TAGC_TELEFONES_CENTRAL_RESERVA, conteudo.get(TemplateConstantes.TAGC_TELEFONES_CENTRAL_RESERVA));
			}
		}
	}
}


