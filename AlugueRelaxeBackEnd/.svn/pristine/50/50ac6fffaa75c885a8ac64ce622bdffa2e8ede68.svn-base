package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;

/**
 *
 * Interface utilizada por todas as classes DAO's do sistema
 *  para implementar as funcionalidades de manipulaééo dos
 *  dados no banco.
 *
 * @author ei5h
 *
 * @param <DTO> Objeto de transferéncia que seré manipulado.
 */
public interface DAO<DTO> {

	/**
	 * Método utilizado para incluir um novo registro,
	 * utilizando os valores do objeto de transferéncia informado.
	 *
	 * @param conn instância da conexão com o banco.
	 * @param dto Objeto de transferéncia que contém os valores
	 *  do registro a ser incluido.
	 * @return O transfer object populado com os dados do registro
	 *  inserido.
	 * @throws AlugueRelaxeException Caso ocorra algum erro no Oracle.
	 */
	public DTO insert(DTO dto) throws AlugueRelaxeException;
	
	/**
	 * Método utilizado para atualizar as informaéées de um
	 * registro de acordo com os dados informados no
	 * objeto de transferéncia.
	 *
	 * @param conn instância da conexão com o banco.
	 * @param dto Objeto de transferéncia com as informaéées
	 *  do registro a ser atualizado.
	 * @throws SigemDAOException Caso ocorra algum erro no
	 *  Oracle durante a atualizaééo do registro.
	 *  @return Um transfer object populado com a informaééo
	 * do registro que foi atualizado.
	 */
	public DTO update(DTO dto) throws AlugueRelaxeException;

	/**
	 * Método utilizado para remover um registro que
	 * atende os critérios do objeto de transferéncia informado.
	 *
	 * @param conn instância da conexão com o banco.
	 * @param dto Objeto de transferéncia que contém os valores
	 *  do registro a ser excluido.
	 * @throws AlugueRelaxeException Caso ocorra algum erro no Oracle
	 * durante a exclusão do registro.
	 * @return Um transfer object populado com a informaééo
	 * do registro que foi removido.
	 */
	public void delete(DTO dto) throws AlugueRelaxeException;
	
	/**
	 * @param dto
	 * @throws AlugueRelaxeException
	 */
	public DTO load(DTO dto) throws AlugueRelaxeException;
	
	/**
	 * @return
	 * @throws AlugueRelaxeException
	 */
	public List<DTO> list() throws AlugueRelaxeException;
	
	public List<DTO> list(int pagina) throws AlugueRelaxeException;



}
