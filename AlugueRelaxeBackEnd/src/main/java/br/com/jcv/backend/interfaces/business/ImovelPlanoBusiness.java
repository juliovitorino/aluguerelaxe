package br.com.jcv.backend.interfaces.business;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

/**
 * <h1>ImovelPlanoBusiness</h1>
 * <p>Interface responsável pelo relacionamento Imovel x Plano.
 * </p>
 * @author julio
 * @version 1.0
 */
public interface ImovelPlanoBusiness<DTO> extends BusinessObject<DTO> {
	DTO procurar(DAOFactory daofactory, long idImovel, long idPlano) throws AlugueRelaxeException;
}
