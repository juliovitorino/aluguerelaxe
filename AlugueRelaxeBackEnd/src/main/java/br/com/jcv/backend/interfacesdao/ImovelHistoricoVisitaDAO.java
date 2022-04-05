package br.com.jcv.backend.interfacesdao;

import br.com.jcv.backend.exception.AlugueRelaxeException;

public interface ImovelHistoricoVisitaDAO<DTO> extends DAO<DTO> {
	void incrementaImovelHistoricoVisita(long id) throws AlugueRelaxeException;
	@Deprecated
	DTO load(long codigoImovel, int ano, int mes, int dia) throws AlugueRelaxeException;
	DTO load(long codigoImovel, int ano, int mes, int dia, String origemAcesso) throws AlugueRelaxeException;
}
