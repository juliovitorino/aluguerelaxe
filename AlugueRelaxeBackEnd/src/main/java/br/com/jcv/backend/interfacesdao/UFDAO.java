package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface UFDAO<DTO> extends DAO<DTO> {
	
	List<DTO> listMaioresQtdeImoveisPorEstado() throws AlugueRelaxeException;
	List<DTO> listEstadosMaisVisitados() throws AlugueRelaxeException;
	List<CidadeDTO> listarCidadesDaUF(String uf) throws AlugueRelaxeException;
	List<CidadeDTO> listarCidadesDaUFComImoveis(String uf) throws AlugueRelaxeException;
	List<DTO> listSumarizadoImoveisUF() throws AlugueRelaxeException;
	CidadeUFDTO load(long idCidadeUfItem) throws AlugueRelaxeException;
	
}
