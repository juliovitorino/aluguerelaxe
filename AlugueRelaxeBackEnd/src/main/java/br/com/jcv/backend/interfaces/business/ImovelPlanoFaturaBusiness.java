package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

public interface ImovelPlanoFaturaBusiness<DTO> extends BusinessObject<DTO> {
	DTO procurar(DAOFactory daoFactory, long codigoImovel, String tipo) throws AlugueRelaxeException;
	DTO procurar(DAOFactory daoFactory, long idFatura) throws AlugueRelaxeException;
	List<Long> listarPlanosPagosAVencer(DAOFactory daoFactory) throws AlugueRelaxeException;
	List<Long> listarPlanosPagosNaoVencidos(DAOFactory daoFactory) throws AlugueRelaxeException;
	List<Long> listarPlanosGratuitosAVencer(DAOFactory daoFactory) throws AlugueRelaxeException;
	List<Long> listarPlanosPagosVencidos(DAOFactory daoFactory) throws AlugueRelaxeException;
	List<Long> listarPlanosPendentesVencidos(DAOFactory daoFactory) throws AlugueRelaxeException;
	List<Long> listarPlanosGratuitosVencidos(DAOFactory daoFactory) throws AlugueRelaxeException;
	void atualizarStatusFatura(DAOFactory daoFactory, long idFatura, String status) throws AlugueRelaxeException;
}

