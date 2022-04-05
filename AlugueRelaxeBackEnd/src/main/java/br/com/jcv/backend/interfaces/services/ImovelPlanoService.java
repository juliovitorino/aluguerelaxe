package br.com.jcv.backend.interfaces.services;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface ImovelPlanoService<DTO> extends ApplicationService<DTO> {
	/**
	 * <h2>migrarPlanoImovel</h2>
	 * <p>Método responsável por migrar o plano atual para um novo, independente de seu estado
	 * </p>
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO migrarPlanoImovel(long codImovel, long codNovoPlano) throws AlugueRelaxeException;
	/**
	 * <h2>pesquisarRegistro</h2>
	 * <p>Pesquisa o registro do plano pelo ID
	 * </p>
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	DTO pesquisarRegistro(long id) throws AlugueRelaxeException;


}
