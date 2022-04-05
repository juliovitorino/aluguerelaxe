package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;

public interface TabelaPrecoDAO<DTO> extends DAO<DTO> {
	
	List<DTO> buscarTabelaPrecoImovel(long codigoImovel) throws AlugueRelaxeException;
	TabelaPrecoDTO insert(long codigoImovel,TabelaPrecoDTO dto) throws AlugueRelaxeException;
	void delete(long codigoImovel) throws AlugueRelaxeException;
}
