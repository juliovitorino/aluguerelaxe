package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;


/**
 * @author Julio
 * @version 1.0
 * @created 06-nov-2009 22:25:52
 */
public interface CaracteristicaService<DTO> extends ApplicationService<DTO> {
	
	public static final String IMOVEL = "I";
	public static final String CONDOMINIO = "C";
	
	/**
	 * <h2>listarRegistros()</h2>
	 * <p>Contrato com a finalidade de retornar toda a lista de registros 
	 * do banco de dados usando o metodo de negócio <code>BusinessObject.buscarTodas()</code>.</p>
	 * @param CaracteristicaDTO - DTO contendo os dados para inclusão do registro.
	 * @return CaracteristicaDTO - DTO Contendo o resultado da operação.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	List<DTO> listarRegistros() throws AlugueRelaxeException;
	
}