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
package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;

/**
 * <h1>ApplicationService</h1> 
 * <p>Interface com Métodos para Implementação dos serviços que serão permitidos ser invocados pelos clientes.
 * </p>
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public interface ApplicationService<DTO> {

	/**
	 * <h2>gravarRegistro</h2>
	 * <p>Contrato para gravar um registro.
	 * </p>
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO gravarRegistro(DTO dto) throws AlugueRelaxeException;
	/**
	 * <h2>excluirRegistro</h2>
	 * <p>Contrato para excluir um registro.
	 * </p>
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO excluirRegistro(DTO dto) throws AlugueRelaxeException;
	/**
	 * <h2>atualizarRegistro</h2>
	 * <p>Contrato para atualizar um registro.
	 * </p>
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO atualizarRegistro(DTO dto) throws AlugueRelaxeException;
	/**
	 * <h2>pesquisarRegistro</h2>
	 * <p>Contrato para pesquisar um registro.
	 * </p>
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO pesquisarRegistro(DTO dto) throws AlugueRelaxeException;
	/**
	 * <h2>listarRegistros</h2>
	 * <p>Contrato para listar registro.
	 * </p>
	 * @return List 
	 * @throws AlugueRelaxeException
	 */
	List<? extends DTO> listarRegistros() throws AlugueRelaxeException;
	
}
