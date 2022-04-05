package br.com.jcv.aluguerelaxe.client.mobile.vo;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ImovelVO extends ImovelBaseVO implements IsSerializable {
	/**
	 * <h2>dataAtualizacao</h2>
	 * <p>Data em que o im�vel foi atualizado no banco de dados.</p>
	 */
	public Date dataAtualizacao;
	/**
	 * <h2>indicadorStatus</h2>
	 * <p>Status do im�vel</p>
	 */
	//TODO: Pegar os status no dicionario de dados para colocar no javadoc 
	public String indicadorStatus;
	/**
	 * <h2>indicadorMostraTabelaPreco</h2>
	 * <p>Indicador para saber se o propriet�rio do im�vel
	 * deseja que sua tabela de preÃ§os seja apresentada junto com a ficha
	 * do im�vel.</p>
	 */
	public String indicadorMostraTabelaPreco;
	/**
	 * <h2>dataUltimaVisita</h2>
	 * <p>registro da data da Ãºltima visita na ficha do im�vel.</p>
	 */
	public Date dataUltimaVisita;
	
	public String valorTarifaBasica ;
 

}
