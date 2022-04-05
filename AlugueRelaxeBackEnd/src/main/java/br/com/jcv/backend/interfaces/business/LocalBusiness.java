package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.local.ClassificacaoDTO;
import br.com.jcv.backend.local.LocalDTO;


public interface LocalBusiness<DTO> extends BusinessObject<DTO> {
	List<DTO> listarLocaisUFCidade(DAOFactory daoFactory, 
			long idUfCidadeItem,
			long[] idLocais) throws AlugueRelaxeException;
	
	List<DTO> listarLocaisUFCidadeClassificacao(DAOFactory daoFactory, 
			long idUfCidadeItem,
			long[] idClassificacao) throws AlugueRelaxeException;

	List<ClassificacaoDTO> listarClassificacaoLocal(DAOFactory daoFactory) throws AlugueRelaxeException;

	List<LocalDTO> listarLocal(DAOFactory daoFactory) throws AlugueRelaxeException;

}
