/*
*
* Copyright (c) 2009-2009, Julio Cesar Vitorino, Todos os direitos reservados.
*
* This software is the confidential and proprietary information of Sun
* Microsystems, Inc. ("Confidential Information"). You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with Sun.
*
* SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
* THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
* TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
* PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
* ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
* DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/
package br.com.jcv.backend.imovel;

import java.io.Serializable;
import java.util.Date;

import br.com.jcv.backend.anotacoes.Obrigatorio;
import br.com.jcv.backend.constantes.Constantes;

/**
 * <h1>ImovelDTO</h1> 
 * <p>Classe de transferência de dados</p>
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */
public class ImovelDTO extends ImovelBaseDTO implements Serializable {

	/**
	 * serialVersionUID - long representando o UID.
	 */
	private static final long serialVersionUID = -7986413788326769121L;

	/**
	 * <h2>dataAtualizacao</h2>
	 * <p>Data em que o imóvel foi atualizado no banco de dados.</p>
	 */
	public java.sql.Timestamp dataAtualizacao;
	/**
	 * <h2>descricaoTituloAnuncio</h2>
	 * <p>Titulo geral do anÃƒÂºncio.</p>
	 */
	@Obrigatorio(tamanho=200, notNull=true)
	public String descricaoTituloAnuncio;
	/**
	 * <h2>indicadorStatus</h2>
	 * <p>Status do imóvel</p>
	 */
	//TODO: Pegar os status no dicionario de dados para colocar no javadoc 
	@Obrigatorio(tamanho=1, notNull=true,
			dominio={ImovelBusinessImpl.STATUS_ATIVO,
					 ImovelBusinessImpl.STATUS_INATIVO
					}
				)
	public String indicadorStatus;
	/**
	 * <h2>indicadorMostraTabelaPreco</h2>
	 * <p>Indicador para saber se o proprietário do imóvel
	 * deseja que sua tabela de preÃƒÂ§os seja apresentada junto com a ficha
	 * do imóvel.</p>
	 */
	@Obrigatorio(tamanho=1, notNull=true,
			dominio={Constantes.SIM,
					 Constantes.NAO
					}
				)
	public String indicadorMostraTabelaPreco;
	/**
	 * <h2>dataUltimaVisita</h2>
	 * <p>registro da data da ÃƒÂºltima visita na ficha do imóvel.</p>
	 */
	public Date dataUltimaVisita;
	/**
	 * <h2>indicadorPermiteAlugarFestas</h2>
	 * <p>Indicador se o proprietário permite o uso do imóvel para festas.</p>
	 */
	@Obrigatorio(tamanho=1, notNull=true,
				dominio={Constantes.SIM,
						 Constantes.NAO
					}
				)
	public String indicadorPermiteAlugarFestas;
	
	public int valorTarifaBasica = 0;
	
	public String getDescricaoTituloAnuncio() {
		return descricaoTituloAnuncio;
	}
	public void setDescricaoTituloAnuncio(String descricaoTituloAnuncio) {
		this.descricaoTituloAnuncio = descricaoTituloAnuncio;
	}
	public String getIndicadorStatus() {
		return indicadorStatus;
	}
	public void setIndicadorStatus(String indicadorStatus) {
		this.indicadorStatus = indicadorStatus;
	}
	public String getIndicadorMostraTabelaPreco() {
		return indicadorMostraTabelaPreco;
	}
	public void setIndicadorMostraTabelaPreco(String indicadorMostraTabelaPreco) {
		this.indicadorMostraTabelaPreco = indicadorMostraTabelaPreco;
	}
	public Date getDataUltimaVisita() {
		return dataUltimaVisita;
	}
	public void setDataUltimaVisita(Date dataUltimaVisita) {
		this.dataUltimaVisita = dataUltimaVisita;
	}
	public String getIndicadorPermiteAlugarFestas() {
		return indicadorPermiteAlugarFestas;
	}
	public void setIndicadorPermiteAlugarFestas(String indicadorPermiteAlugarFestas) {
		this.indicadorPermiteAlugarFestas = indicadorPermiteAlugarFestas;
	}
}
