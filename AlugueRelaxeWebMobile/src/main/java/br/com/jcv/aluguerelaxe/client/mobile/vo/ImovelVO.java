package br.com.jcv.aluguerelaxe.client.mobile.vo;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ImovelVO extends ImovelBaseVO implements IsSerializable {
	/**
	 * <h2>dataAtualizacao</h2>
	 * <p>Data em que o imóvel foi atualizado no banco de dados.</p>
	 */
	public Date dataAtualizacao;
	/**
	 * <h2>indicadorStatus</h2>
	 * <p>Status do imóvel</p>
	 */
	//TODO: Pegar os status no dicionario de dados para colocar no javadoc 
	public String indicadorStatus;
	/**
	 * <h2>indicadorMostraTabelaPreco</h2>
	 * <p>Indicador para saber se o proprietário do imóvel
	 * deseja que sua tabela de preÃƒÂ§os seja apresentada junto com a ficha
	 * do imóvel.</p>
	 */
	public String indicadorMostraTabelaPreco;
	/**
	 * <h2>dataUltimaVisita</h2>
	 * <p>registro da data da ÃƒÂºltima visita na ficha do imóvel.</p>
	 */
	public Date dataUltimaVisita;
	
	public String valorTarifaBasica ;
 

}
