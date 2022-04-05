package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadDTO;

public interface ContatoAnuncianteThreadDAO extends DAO<ContatoAnuncianteThreadDTO> {
	ContatoAnuncianteThreadDTO load(String hash) throws AlugueRelaxeException;
	void updateStatus(long id, String status) throws AlugueRelaxeException;
	List<ContatoAnuncianteThreadDTO> listThreads(String hash, String modo) throws AlugueRelaxeException;
	List<ContatoAnuncianteThreadDTO> listComentarios(long idImovel) throws AlugueRelaxeException;
	ContatoAnuncianteThreadDTO insert(ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException;
	//ContatoAnuncianteThreadDTO insert(ContatoClienteDTO ccdto, ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException;
	ContatoAnuncianteThreadDTO load(long idParent) throws AlugueRelaxeException;

}
