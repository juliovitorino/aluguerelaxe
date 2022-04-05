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
public class TemplateAguadarPgtoPreReserva extends Template {

	public TemplateAguadarPgtoPreReserva(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-aviso-aguardando-pgto-pre-reserva.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_LINK_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_LINK_RESERVA, conteudo.get(TemplateConstantes.TVPR_TAG_LINK_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_CODIGO_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_CODIGO_RESERVA, conteudo.get(TemplateConstantes.TAPPR_TAG_CODIGO_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_CODIGO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_CODIGO_IMOVEL, conteudo.get(TemplateConstantes.TAPPR_TAG_CODIGO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_CODIGO_TRACKING)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_CODIGO_TRACKING, conteudo.get(TemplateConstantes.TAPPR_TAG_CODIGO_TRACKING));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_CPF_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_CPF_PROPOSTO, conteudo.get(TemplateConstantes.TAPPR_TAG_CPF_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_DATA_CADASTRO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_DATA_CADASTRO, conteudo.get(TemplateConstantes.TAPPR_TAG_DATA_CADASTRO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_DATA_CHECKIN)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_DATA_CHECKIN, conteudo.get(TemplateConstantes.TAPPR_TAG_DATA_CHECKIN));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_DATA_CHECKOUT)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_DATA_CHECKOUT, conteudo.get(TemplateConstantes.TAPPR_TAG_DATA_CHECKOUT));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_DATA_PREVISTA_DEPOSITO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_DATA_PREVISTA_DEPOSITO, conteudo.get(TemplateConstantes.TAPPR_TAG_DATA_PREVISTA_DEPOSITO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_DATA_VALIDACAO_PRE_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_DATA_VALIDACAO_PRE_RESERVA, conteudo.get(TemplateConstantes.TAPPR_TAG_DATA_VALIDACAO_PRE_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_EMAIL_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_EMAIL_CONTATO, conteudo.get(TemplateConstantes.TAPPR_TAG_EMAIL_CONTATO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_FORMA_DE_PAGAMENTO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_FORMA_DE_PAGAMENTO, conteudo.get(TemplateConstantes.TAPPR_TAG_FORMA_DE_PAGAMENTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_NOME_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_NOME_PROPOSTO, conteudo.get(TemplateConstantes.TAPPR_TAG_NOME_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_CPF_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_CPF_PROPOSTO, conteudo.get(TemplateConstantes.TAPPR_TAG_CPF_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_TELEFONES_CENTRAL_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_TELEFONES_CENTRAL_RESERVA, conteudo.get(TemplateConstantes.TAPPR_TAG_TELEFONES_CENTRAL_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_TITULO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_TITULO_IMOVEL, conteudo.get(TemplateConstantes.TAPPR_TAG_TITULO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_TOKEN)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_TOKEN, conteudo.get(TemplateConstantes.TAPPR_TAG_TOKEN));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_VALOR_NEGOCIADO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_VALOR_NEGOCIADO, conteudo.get(TemplateConstantes.TAPPR_TAG_VALOR_NEGOCIADO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_VALOR_PREVISTO_DEPOSITO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_VALOR_PREVISTO_DEPOSITO, conteudo.get(TemplateConstantes.TAPPR_TAG_VALOR_PREVISTO_DEPOSITO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_VALOR_POR_EXTENSO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_VALOR_POR_EXTENSO, conteudo.get(TemplateConstantes.TAPPR_TAG_VALOR_POR_EXTENSO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_VALOR_CAUCAO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_VALOR_CAUCAO, conteudo.get(TemplateConstantes.TAPPR_TAG_VALOR_CAUCAO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_VALOR_CAUCAO_POR_EXTENSO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_VALOR_CAUCAO_POR_EXTENSO, conteudo.get(TemplateConstantes.TAPPR_TAG_VALOR_CAUCAO_POR_EXTENSO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_VALOR_TOTAL_DEPOSITO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_VALOR_TOTAL_DEPOSITO, conteudo.get(TemplateConstantes.TAPPR_TAG_VALOR_TOTAL_DEPOSITO));
			}
			if (conteudo.containsKey(TemplateConstantes.TAPPR_TAG_VALOR_TOTAL_DEPOSITO_POR_EXTENSO)) {
				this.adicionarParametro(TemplateConstantes.TAPPR_TAG_VALOR_TOTAL_DEPOSITO_POR_EXTENSO, conteudo.get(TemplateConstantes.TAPPR_TAG_VALOR_TOTAL_DEPOSITO_POR_EXTENSO));
			}
		}
	}

}

