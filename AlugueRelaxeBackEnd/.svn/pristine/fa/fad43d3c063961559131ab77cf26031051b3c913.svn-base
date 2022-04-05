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
public class TemplateDepositoTransferido extends Template {

	public TemplateDepositoTransferido(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-deposito-transferido.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TVPR_TAG_LINK_RESERVA)) {
				this.adicionarParametro(TemplateConstantes.TVPR_TAG_LINK_RESERVA, conteudo.get(TemplateConstantes.TVPR_TAG_LINK_RESERVA));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_CODIGO_TRACKING)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_CODIGO_TRACKING, conteudo.get(TemplateConstantes.TETD_TAG_CODIGO_TRACKING));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_NOME_BANCO)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_NOME_BANCO, conteudo.get(TemplateConstantes.TETD_TAG_NOME_BANCO));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_NOME_AGENCIA)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_NOME_AGENCIA, conteudo.get(TemplateConstantes.TETD_TAG_NOME_AGENCIA));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_CONTA_CORRENTE)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_CONTA_CORRENTE, conteudo.get(TemplateConstantes.TETD_TAG_CONTA_CORRENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_NOME_LOCADOR)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_NOME_LOCADOR, conteudo.get(TemplateConstantes.TETD_TAG_NOME_LOCADOR));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_VALOR_NEGOCIADO_TEMPORADA)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_VALOR_NEGOCIADO_TEMPORADA, conteudo.get(TemplateConstantes.TETD_TAG_VALOR_NEGOCIADO_TEMPORADA));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_FORMA_PGTO_ESCOLHIDA)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_FORMA_PGTO_ESCOLHIDA, conteudo.get(TemplateConstantes.TETD_TAG_FORMA_PGTO_ESCOLHIDA));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_VALOR_DEPOSITADO_CUSTODIA)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_VALOR_DEPOSITADO_CUSTODIA, conteudo.get(TemplateConstantes.TETD_TAG_VALOR_DEPOSITADO_CUSTODIA));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_VALOR_DEPOSITO_CAUCAO)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_VALOR_DEPOSITO_CAUCAO, conteudo.get(TemplateConstantes.TETD_TAG_VALOR_DEPOSITO_CAUCAO));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_VALOR_A_SER_PAGO_NA_CHAVE)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_VALOR_A_SER_PAGO_NA_CHAVE, conteudo.get(TemplateConstantes.TETD_TAG_VALOR_A_SER_PAGO_NA_CHAVE));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_PERC_COMISSAO_ALUGUE_RELAXE)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_PERC_COMISSAO_ALUGUE_RELAXE, conteudo.get(TemplateConstantes.TETD_TAG_PERC_COMISSAO_ALUGUE_RELAXE));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_VALOR_COMISSAO_ALUGUE_RELAXE)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_VALOR_COMISSAO_ALUGUE_RELAXE, conteudo.get(TemplateConstantes.TETD_TAG_VALOR_COMISSAO_ALUGUE_RELAXE));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_VALOR_A_SER_LIBERADO)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_VALOR_A_SER_LIBERADO, conteudo.get(TemplateConstantes.TETD_TAG_VALOR_A_SER_LIBERADO));
			}
			if (conteudo.containsKey(TemplateConstantes.TETD_TAG_DATA_TRANSFERENCIA_VALOR_A_SER_LIBERADO)) {
				this.adicionarParametro(TemplateConstantes.TETD_TAG_DATA_TRANSFERENCIA_VALOR_A_SER_LIBERADO, conteudo.get(TemplateConstantes.TETD_TAG_DATA_TRANSFERENCIA_VALOR_A_SER_LIBERADO));
			}
		}
	}

}

