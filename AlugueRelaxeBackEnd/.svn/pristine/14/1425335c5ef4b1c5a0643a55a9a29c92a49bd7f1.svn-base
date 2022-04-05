package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;

public interface TabelaPrecoBusiness<DTO> extends BusinessObject<DTO> {
	
	List<DTO> buscarTabelaPrecoImovel(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException;
	TabelaPrecoDTO incluir(DAOFactory daofactory, long codigoImovel, TabelaPrecoDTO dto) throws AlugueRelaxeException;

}
