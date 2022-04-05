package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;

public interface ContatoAnuncianteDAO extends DAO<ContatoClienteDTO> {
	List<ContatoClienteDTO> listContatosAnuncianteStatus(String status) throws AlugueRelaxeException;
	List<ContatoClienteDTO> listContatosPendentesAlerta24h() throws AlugueRelaxeException;
	void updateFilaAlerta24h(String hashID, String status) throws AlugueRelaxeException;
	List<ContatoClienteDTO> loadProximoOferecimento() throws AlugueRelaxeException;
	void updateOferecimento(String id, long seq) throws AlugueRelaxeException;
	void updateFotoThread(String hashParent, String foto) throws AlugueRelaxeException;
	void updateStatusThread(String idContato, String status) throws AlugueRelaxeException;
}
