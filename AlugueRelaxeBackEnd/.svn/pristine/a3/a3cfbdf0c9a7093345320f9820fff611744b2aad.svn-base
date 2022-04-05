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
package br.com.jcv.backend.cidade;

import java.io.Serializable;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;

/**
 * <h1>CidadeDTO</h1> 
 * <p>Classe de transferência de dados</p>
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public class CidadeDTO extends DTOPadrao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5866375571267270878L;
	/**
	 * <h2>Atributo id</h2>
	 * <p>Identificador único da propriedade id. Deve ser um número do tipo <code>Long</code>.</p>
	 * <p>Representa diretamente a PK da tabela UF.</p>
	 */
	public long id;
	/**
	 * <h2>Atributo nome</h2>
	 * <p>Nome da cidade. Tipo <code>String</code>.</p>
	 */
	public String nome;
	/**
	 * <h2>getId()</h2>
	 * <p>Getter para  retornar o conteúdo do atributo <code>id</code> da instância
	 * corrente do objeto.</p>
	 * @return String
	 */
	public Long getId() {
		return id;
	}
	/**
	 * <h2>setId()</h2>
	 * <p>Setter para atribuir um código para o atributo <code>id</code> da instância
	 * corrente do objeto.</p>
	 * @param Long - valor que será atribuído como código da cidade.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * <h2>getNome()</h2>
	 * <p>Getter para  retornar o conteúdo do atributo <code>nome</code> da instância
	 * corrente do objeto.</p>
	 * @return String
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * <h2>setNome()</h2>
	 * <p>Setter para atribuir uma String para o atributo <code>nome</code> da instância
	 * corrente do objeto.</p>
	 * @param String - valor que será atribuído como nome da cidade.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
