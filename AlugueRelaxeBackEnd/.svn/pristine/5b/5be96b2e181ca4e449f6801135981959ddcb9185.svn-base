package br.com.jcv.backend.interfaces.services;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface ChatService<DTO> extends ApplicationService<DTO> {	
	
	/**
	 * <h2>getChatAtivo</h2>
	 * <p>Método responsável por localizar o chat mais recente ativo de 
	 * uma determinada sessao</p>
	 * @param String
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO getChatAtivo(String sessao) throws AlugueRelaxeException;

	/**
	 * <h2>getChatPrivado</h2>
	 * <p>Método responsável por localizar o chat mais recente ativo de 
	 * da area privada para um anunciante</p>
	 * @param String
	 * @param long
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO getChatPrivado(String sessao, long idCliente) throws AlugueRelaxeException;


}
