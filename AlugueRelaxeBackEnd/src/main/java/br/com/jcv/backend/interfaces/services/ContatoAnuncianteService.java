package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;

public interface ContatoAnuncianteService extends ApplicationService<ContatoClienteDTO> {
	List<ContatoClienteDTO> listarContatosAnuncianteStatus(String status) throws AlugueRelaxeException;
	List<ContatoClienteDTO> listarContatosPendentesAlerta24h() throws AlugueRelaxeException;
	void atualizarFilaAlerta24h(String hashID, String status) throws AlugueRelaxeException;
	ContatoClienteDTO pesquisarProximoOferecimento() throws AlugueRelaxeException;
	void atualizarOferecimento(String id) throws AlugueRelaxeException;
	ContatoClienteDTO pesquisarRegistro(String idhash) throws AlugueRelaxeException;
}
