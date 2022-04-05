
package br.com.jcv.backend.template;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

import java.util.Map;

public class TemplateNotificarInativacaoImovel extends Template {


	public TemplateNotificarInativacaoImovel(Map<String,String> conteudo) throws  AlugueRelaxeException {
		super(VariavelCache.getInstance().getValor(VariavelConstantes.TEMPLATE_PATH).concat("template-notificar-inativacao-imovel.html"));
		inicializaTags(conteudo);
		this.execute();
	}
	
	private void inicializaTags(Map<String, String> conteudo) {
		if (conteudo != null) {
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_CODIGO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_CODIGO_IMOVEL, conteudo.get(TemplateConstantes.TNCAI_TAG_CODIGO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_CODIGO_CLIENTE)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_CODIGO_CLIENTE, conteudo.get(TemplateConstantes.TNCAI_TAG_CODIGO_CLIENTE));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_QTDE_QUARTOS)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_QTDE_QUARTOS, conteudo.get(TemplateConstantes.TNCAI_TAG_QTDE_QUARTOS));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_QTDE_SUITES)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_QTDE_SUITES, conteudo.get(TemplateConstantes.TNCAI_TAG_QTDE_SUITES));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_QTDE_BANHEIROS)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_QTDE_BANHEIROS, conteudo.get(TemplateConstantes.TNCAI_TAG_QTDE_BANHEIROS));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_QTDE_CAPACIDADE_MAX)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_QTDE_CAPACIDADE_MAX, conteudo.get(TemplateConstantes.TNCAI_TAG_QTDE_CAPACIDADE_MAX));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_DESC_GERAL_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_DESC_GERAL_IMOVEL, conteudo.get(TemplateConstantes.TNCAI_TAG_DESC_GERAL_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_DESC_QUARTOS)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_DESC_QUARTOS, conteudo.get(TemplateConstantes.TNCAI_TAG_DESC_QUARTOS));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_DESC_ARREDORES)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_DESC_ARREDORES, conteudo.get(TemplateConstantes.TNCAI_TAG_DESC_ARREDORES));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_DESC_TITULO_ANUNCIO)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_DESC_TITULO_ANUNCIO, conteudo.get(TemplateConstantes.TNCAI_TAG_DESC_TITULO_ANUNCIO));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_STATUS_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_STATUS_IMOVEL, conteudo.get(TemplateConstantes.TNCAI_TAG_STATUS_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_OBSERVACOES)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_OBSERVACOES, conteudo.get(TemplateConstantes.TNCAI_TAG_OBSERVACOES));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_FLAG_MOSTRA_TAB_PRECO)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_FLAG_MOSTRA_TAB_PRECO, conteudo.get(TemplateConstantes.TNCAI_TAG_FLAG_MOSTRA_TAB_PRECO));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_FLAG_CONDOMINIO)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_FLAG_CONDOMINIO, conteudo.get(TemplateConstantes.TNCAI_TAG_FLAG_CONDOMINIO));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_DATA_ULTIMA_VISITA)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_DATA_ULTIMA_VISITA, conteudo.get(TemplateConstantes.TNCAI_TAG_DATA_ULTIMA_VISITA));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_QTDE_VISITAS)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_QTDE_VISITAS, conteudo.get(TemplateConstantes.TNCAI_TAG_QTDE_VISITAS));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_FLAG_PERMITE_ALUGAR_FESTA)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_FLAG_PERMITE_ALUGAR_FESTA, conteudo.get(TemplateConstantes.TNCAI_TAG_FLAG_PERMITE_ALUGAR_FESTA));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_FLAG_TIPO_PROPRIEDADE)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_FLAG_TIPO_PROPRIEDADE, conteudo.get(TemplateConstantes.TNCAI_TAG_FLAG_TIPO_PROPRIEDADE));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_ENDERECO_IMOVEL)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_ENDERECO_IMOVEL, conteudo.get(TemplateConstantes.TNCAI_TAG_ENDERECO_IMOVEL));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_ENDERECO_NUMERO)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_ENDERECO_NUMERO, conteudo.get(TemplateConstantes.TNCAI_TAG_ENDERECO_NUMERO));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_ENDERECO_BAIRRO)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_ENDERECO_BAIRRO, conteudo.get(TemplateConstantes.TNCAI_TAG_ENDERECO_BAIRRO));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_ENDERECO_CEP)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_ENDERECO_CEP, conteudo.get(TemplateConstantes.TNCAI_TAG_ENDERECO_CEP));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_ENDERECO_CIDADE)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_ENDERECO_CIDADE, conteudo.get(TemplateConstantes.TNCAI_TAG_ENDERECO_CIDADE));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_ENDERECO_UF)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_ENDERECO_UF, conteudo.get(TemplateConstantes.TNCAI_TAG_ENDERECO_UF));
			}
			if (conteudo.containsKey(TemplateConstantes.TNCAI_TAG_IMAGEM_PREFERIDA_ID)) {
				this.adicionarParametro(TemplateConstantes.TNCAI_TAG_IMAGEM_PREFERIDA_ID, conteudo.get(TemplateConstantes.TNCAI_TAG_IMAGEM_PREFERIDA_ID));
			}
		}
	}
}