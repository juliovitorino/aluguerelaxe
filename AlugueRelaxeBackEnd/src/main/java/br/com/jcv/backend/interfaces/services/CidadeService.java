package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;

/**
 * @author Julio
 * @version 1.0
 * @created 06-nov-2009 22:26:14
 */
public interface CidadeService<DTO> extends ApplicationService<DTO> {
	List<CidadeUFDTO> listCidadesDaUfComImoveis() throws AlugueRelaxeException;
	List<DTO> listCidadesDaUfComImoveis(String uf) throws AlugueRelaxeException;
	List<DTO> listCidadesDaUfComImovesMaisVisitadas(String uf) throws AlugueRelaxeException;
}