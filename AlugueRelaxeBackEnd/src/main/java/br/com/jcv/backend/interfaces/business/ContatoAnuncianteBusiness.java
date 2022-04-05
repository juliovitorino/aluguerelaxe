package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;

public interface ContatoAnuncianteBusiness extends BusinessObject<ContatoClienteDTO> {
	void liberarEmailContatoAnunciante(DAOFactory daofactory, String id) throws AlugueRelaxeException;
	void liberarEmailContatoAnuncianteSimples(DAOFactory daofactory, String id) throws AlugueRelaxeException;
	void liberarEmailInstrucoesCaptarAutorizacao(ContatoClienteDTO ccdto, ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException;
	List<ContatoClienteDTO> listarContatosPendentesAlerta24h(DAOFactory daofactory) throws AlugueRelaxeException; 
	List<ContatoClienteDTO> listarContatosAnuncianteStatus(DAOFactory daofactory, String status) throws AlugueRelaxeException; 
	void atualizarFilaAlerta24h(DAOFactory daofactory, String hashID, String status) throws AlugueRelaxeException;
	ContatoClienteDTO procurarProximoOferecimento(DAOFactory daofactory) throws AlugueRelaxeException;
	void atualizarOferecimento(DAOFactory daoFactory, String id) throws AlugueRelaxeException;
	ContatoClienteDTO procurar(DAOFactory daoFactory, String hash) throws AlugueRelaxeException;
	void atualizarFotoThread(DAOFactory daoFactory, String hashParent, String foto) throws AlugueRelaxeException;
	void atualizarStatusThread(DAOFactory daoFactory, String idContato, String status) throws AlugueRelaxeException;

}
