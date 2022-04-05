package br.com.jcv.backend.template;

import java.util.Map;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * Template para contrato de locacao de temporada com o locatario
 * @author Julio
 *
 */
public class TemplateVoucher extends Template {

	public TemplateVoucher(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-voucher.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_CODIGO_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_CODIGO_RESERVA, conteudo.get(TemplateConstantes.TV_TAG_CODIGO_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_CODIGO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_CODIGO_IMOVEL, conteudo.get(TemplateConstantes.TV_TAG_CODIGO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_CODIGO_VOUCHER)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_CODIGO_VOUCHER, conteudo.get(TemplateConstantes.TV_TAG_CODIGO_VOUCHER));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_CPF_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_CPF_PROPOSTO, conteudo.get(TemplateConstantes.TV_TAG_CPF_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_DATA_CADASTRO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_DATA_CADASTRO, conteudo.get(TemplateConstantes.TV_TAG_DATA_CADASTRO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_DATA_CHECKIN)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_DATA_CHECKIN, conteudo.get(TemplateConstantes.TV_TAG_DATA_CHECKIN));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_DATA_CHECKOUT)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_DATA_CHECKOUT, conteudo.get(TemplateConstantes.TV_TAG_DATA_CHECKOUT));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_DATA_PREVISTA_DEPOSITO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_DATA_PREVISTA_DEPOSITO, conteudo.get(TemplateConstantes.TV_TAG_DATA_PREVISTA_DEPOSITO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_DATA_VALIDACAO_PRE_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_DATA_VALIDACAO_PRE_RESERVA, conteudo.get(TemplateConstantes.TV_TAG_DATA_VALIDACAO_PRE_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_EMAIL_CONTATO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_EMAIL_CONTATO, conteudo.get(TemplateConstantes.TV_TAG_EMAIL_CONTATO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_FORMA_DE_PAGAMENTO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_FORMA_DE_PAGAMENTO, conteudo.get(TemplateConstantes.TV_TAG_FORMA_DE_PAGAMENTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_NOME_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_NOME_PROPOSTO, conteudo.get(TemplateConstantes.TV_TAG_NOME_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_RG_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_RG_PROPOSTO, conteudo.get(TemplateConstantes.TV_TAG_RG_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_CPF_PROPOSTO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_CPF_PROPOSTO, conteudo.get(TemplateConstantes.TV_TAG_CPF_PROPOSTO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_TELEFONES_CENTRAL_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_TELEFONES_CENTRAL_RESERVA, conteudo.get(TemplateConstantes.TV_TAG_TELEFONES_CENTRAL_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_TITULO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_TITULO_IMOVEL, conteudo.get(TemplateConstantes.TV_TAG_TITULO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_TOKEN)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_TOKEN, conteudo.get(TemplateConstantes.TV_TAG_TOKEN));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_VALOR_NEGOCIADO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_VALOR_NEGOCIADO, conteudo.get(TemplateConstantes.TV_TAG_VALOR_NEGOCIADO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_VALOR_PREVISTO_DEPOSITO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_VALOR_PREVISTO_DEPOSITO, conteudo.get(TemplateConstantes.TV_TAG_VALOR_PREVISTO_DEPOSITO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_VALOR_POR_EXTENSO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_VALOR_POR_EXTENSO, conteudo.get(TemplateConstantes.TV_TAG_VALOR_POR_EXTENSO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_NOME_DO_LOCADOR)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_NOME_DO_LOCADOR, conteudo.get(TemplateConstantes.TV_TAG_NOME_DO_LOCADOR));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_CPF_LOCADOR)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_CPF_LOCADOR, conteudo.get(TemplateConstantes.TV_TAG_CPF_LOCADOR));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_ENDERECO_COMPLETO_LOCADOR)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_ENDERECO_COMPLETO_LOCADOR, conteudo.get(TemplateConstantes.TV_TAG_ENDERECO_COMPLETO_LOCADOR));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_ENDERECO_COMPLETO_LOCATARIO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_ENDERECO_COMPLETO_LOCATARIO, conteudo.get(TemplateConstantes.TV_TAG_ENDERECO_COMPLETO_LOCATARIO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_VALOR_DA_CAUCAO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_VALOR_DA_CAUCAO, conteudo.get(TemplateConstantes.TV_TAG_VALOR_DA_CAUCAO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_VALOR_DA_CAUCAO_POR_EXTENSO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_VALOR_DA_CAUCAO_POR_EXTENSO, conteudo.get(TemplateConstantes.TV_TAG_VALOR_DA_CAUCAO_POR_EXTENSO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_PERCENTUAL_COMISSAO_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_PERCENTUAL_COMISSAO_RESERVA, conteudo.get(TemplateConstantes.TV_TAG_PERCENTUAL_COMISSAO_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_PERCENTUAL_COMISSAO_RESERVA_POR_EXTENSO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_PERCENTUAL_COMISSAO_RESERVA_POR_EXTENSO, conteudo.get(TemplateConstantes.TV_TAG_PERCENTUAL_COMISSAO_RESERVA_POR_EXTENSO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_QTDE_PESSOAS_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_QTDE_PESSOAS_IMOVEL, conteudo.get(TemplateConstantes.TV_TAG_QTDE_PESSOAS_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_QTDE_PESSOAS_IMOVEL_EXTENSO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_QTDE_PESSOAS_IMOVEL_EXTENSO, conteudo.get(TemplateConstantes.TV_TAG_QTDE_PESSOAS_IMOVEL_EXTENSO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_VALOR_REAL_DEPOSITO)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_VALOR_REAL_DEPOSITO, conteudo.get(TemplateConstantes.TV_TAG_VALOR_REAL_DEPOSITO));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_CODIGO_TRACKING)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_CODIGO_TRACKING, conteudo.get(TemplateConstantes.TV_TAG_CODIGO_TRACKING));
			}
			if (conteudo.containsKey(TemplateConstantes.TV_TAG_VALOR_PAGAR_ENTREGA_CHAVE)) {
				this.adicionarParametro(TemplateConstantes.TV_TAG_VALOR_PAGAR_ENTREGA_CHAVE, conteudo.get(TemplateConstantes.TV_TAG_VALOR_PAGAR_ENTREGA_CHAVE));
			}
		}
	}

}

