package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadDTO;

public interface ContatoAnuncianteThreadService extends ApplicationService<ContatoAnuncianteThreadDTO> {
	ContatoAnuncianteThreadDTO gravarResposta(ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException;
	ContatoAnuncianteThreadDTO gravarDuvida(ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException;
	DTOPadrao aprovarThread(String hash, String modo, boolean editada) throws AlugueRelaxeException;
	List<ContatoAnuncianteThreadDTO> listarThreads(String hash, String modo) throws AlugueRelaxeException;
	List<ContatoAnuncianteThreadDTO> listarComentarios(long idImovel) throws AlugueRelaxeException;
	DTOPadrao atualizarFotoThread(String hash, String foto) throws AlugueRelaxeException;
}
