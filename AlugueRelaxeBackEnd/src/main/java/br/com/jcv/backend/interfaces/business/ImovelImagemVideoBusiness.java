package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;

public interface ImovelImagemVideoBusiness<DTO> extends BusinessObject<DTO> {

	List<DTO> buscarListaImagensTB(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
	List<DTO> buscarListaImagensMI(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
	List<DTO> buscarListaImagensXG(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
	void removeImagensMITB(DAOFactory daofactory, long codigoImovel, DTO dto) throws AlugueRelaxeException;
	DTO procurarVideoImovel(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
}
