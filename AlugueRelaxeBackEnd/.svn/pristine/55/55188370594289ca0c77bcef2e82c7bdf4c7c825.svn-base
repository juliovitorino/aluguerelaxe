package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface ImovelCaracteristicaDAO<DTO> extends DAO<DTO> {
	
	List<DTO> buscarCaracteristicasImovel(long codigoImovel, String indicadorImovelCondominio) throws AlugueRelaxeException;
	void delete(long codigoImovel, String indicadorImovelCondominio) throws AlugueRelaxeException;
	
}
