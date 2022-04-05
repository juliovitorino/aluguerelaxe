package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface CidadeDAO<DTO> extends DAO<DTO> {
	List<CidadeUFDTO> listCidadesDaUfComImoveis() throws AlugueRelaxeException;
	List<DTO> listCidadesDaUfComImoveis(String uf) throws AlugueRelaxeException;
	List<DTO> listCidadesDaUfComImovesMaisVisitados(String uf) throws AlugueRelaxeException;
}
