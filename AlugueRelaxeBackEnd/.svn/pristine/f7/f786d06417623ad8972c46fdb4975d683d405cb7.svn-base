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
public class TemplateValidarPreReserva extends Template {

	public TemplateValidarPreReserva(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-validar-pre-reserva.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_LINK_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_LINK_RESERVA, conteudo.get(TemplateConstantes.TVPR_TAG_LINK_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_LINK_LIBERAR_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_LINK_LIBERAR_RESERVA, conteudo.get(TemplateConstantes.TVPR_TAG_LINK_LIBERAR_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_LINK_REPROVAR_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_LINK_REPROVAR_RESERVA, conteudo.get(TemplateConstantes.TVPR_TAG_LINK_REPROVAR_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_NOME_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_NOME_PROPOSTO, conteudo.get(TemplateConstantes.TVPR_TAG_NOME_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_CPF_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_CPF_PROPOSTO, conteudo.get(TemplateConstantes.TVPR_TAG_CPF_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_CODIGO_TRACKING)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_CODIGO_TRACKING, conteudo.get(TemplateConstantes.TVPR_TAG_CODIGO_TRACKING));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_CODIGO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_CODIGO_IMOVEL, conteudo.get(TemplateConstantes.TVPR_TAG_CODIGO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_TITULO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_TITULO_IMOVEL, conteudo.get(TemplateConstantes.TVPR_TAG_TITULO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_VALOR_NEGOCIADO)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_VALOR_NEGOCIADO, conteudo.get(TemplateConstantes.TVPR_TAG_VALOR_NEGOCIADO));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_DATA_CHECKIN)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_DATA_CHECKIN, conteudo.get(TemplateConstantes.TVPR_TAG_DATA_CHECKIN));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_DATA_CHECKOUT)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_DATA_CHECKOUT, conteudo.get(TemplateConstantes.TVPR_TAG_DATA_CHECKOUT));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_DATA_PREVISTA_DEPOSITO)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_DATA_PREVISTA_DEPOSITO, conteudo.get(TemplateConstantes.TVPR_TAG_DATA_PREVISTA_DEPOSITO));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_VALOR_PREVISTO_DEPOSITO)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_VALOR_PREVISTO_DEPOSITO, conteudo.get(TemplateConstantes.TVPR_TAG_VALOR_PREVISTO_DEPOSITO));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_DATA_CADASTRO)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_DATA_CADASTRO, conteudo.get(TemplateConstantes.TVPR_TAG_DATA_CADASTRO));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_DATA_CADASTRO)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_DATA_CADASTRO, conteudo.get(TemplateConstantes.TVPR_TAG_DATA_CADASTRO));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_VALOR_CAUCAO)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_VALOR_CAUCAO, conteudo.get(TemplateConstantes.TVPR_TAG_VALOR_CAUCAO));
			}
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_VALOR_PAGAR_NA_CHAVE)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_VALOR_PAGAR_NA_CHAVE, conteudo.get(TemplateConstantes.TVPR_TAG_VALOR_PAGAR_NA_CHAVE));
			}
		}
	}

}

