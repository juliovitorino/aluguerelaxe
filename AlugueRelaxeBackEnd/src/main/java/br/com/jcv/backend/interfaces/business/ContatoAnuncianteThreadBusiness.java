package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadDTO;
import br.com.jcv.backend.reserva.AvaliacaoReservaDTO;

public interface ContatoAnuncianteThreadBusiness extends BusinessObject<ContatoAnuncianteThreadDTO> {
	List<ContatoAnuncianteThreadDTO> listarThreads(DAOFactory daofactory, String hash, String modo) throws AlugueRelaxeException;
	List<ContatoAnuncianteThreadDTO> listarComentarios(DAOFactory daofactory, long idImovel) throws AlugueRelaxeException;
	ContatoAnuncianteThreadDTO incluir(DAOFactory daofactory, ContatoClienteDTO dto, ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException;
	ContatoAnuncianteThreadDTO incluir(DAOFactory daofactory, ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException;
	void aprovarThread(DAOFactory daoFactory, ContatoClienteDTO ccdto, 
						ContatoAnuncianteThreadDTO catdto, ImovelFichaCompletaDTO ifcdto, boolean editada, String modo) 
							throws AlugueRelaxeException;
	
	ContatoAnuncianteThreadDTO incluirResposta(DAOFactory daofactory, ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException;
	ContatoAnuncianteThreadDTO incluirDuvida(DAOFactory daofactory, ContatoClienteDTO ccdto, ContatoAnuncianteThreadDTO catdto) throws AlugueRelaxeException;
	ContatoAnuncianteThreadDTO incluirComentario(DAOFactory daofactory, AvaliacaoReservaDTO dto) throws AlugueRelaxeException;
	ContatoAnuncianteThreadDTO procurar(DAOFactory daofactory, long idParent) throws AlugueRelaxeException;
	ContatoAnuncianteThreadDTO procurar(DAOFactory daofactory, String hash) throws AlugueRelaxeException;
	void atualizarFotoThread(DAOFactory aoFactory, String hashParent, String foto) throws AlugueRelaxeException;

	
}
