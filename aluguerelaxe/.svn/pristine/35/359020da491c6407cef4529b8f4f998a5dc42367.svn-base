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
package br.com.jcv.aluguerelaxe.client.painel;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * <h1>DestinoVO</h1> 
 * <p>Classe de transferência de dados</p>
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public class PainelUfCidadesVO implements IsSerializable {

	public PainelUfCidadesVO() {}
	
	/**
	 * <h2>Atributo id</h2>
	 * <p>Identificador único da propriedade id. Deve ser um número do tipo <code>String</code>.</p>
	 * <p>Representa diretamente a PK da tabela UF.</p>
	 */
	public String id;
	/**
	 * <h2>Atributo descricao</h2>
	 * <p>Descrição da UF. Tipo <code>String</code>.</p>
	 */
	public String descricao;

	/**
	 * <h2>Atributo quantidade</h2>
	 * <p>Quantidade de imoveis por UF</p>
	 */
	public int quantidade;
	
	public List<PainelUfCidadesVO> listaCidades = null;

	/**
	 * <h2>getId()</h2>
	 * <p>Getter para  retornar o conteúdo do atributo <code>id</code> da instância
	 * corrente do objeto.</p>
	 * @return String
	 */

	
	public String getId() {
		return id;
	}
	/**
	 * <h2>setId()</h2>
	 * <p>Setter para atribuir um código para o atributo <code>id</code> da instância
	 * corrente do objeto.</p>
	 * @param String - valor que será atribuído como código da uf.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * <h2>getDescricao()</h2>
	 * <p>Getter para  retornar o conteúdo do atributo <code>descricao</code> da instância
	 * corrente do objeto.</p>
	 * @return String
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * <h2>setDescricao()</h2>
	 * <p>Setter para atribuir uma String para o atributo <code>descricao</code> da instância
	 * corrente do objeto.</p>
	 * @param String - valor que será atribuído como nome da uf.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}
	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
