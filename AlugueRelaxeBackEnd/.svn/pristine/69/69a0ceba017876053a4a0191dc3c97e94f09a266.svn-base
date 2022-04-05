package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.local.ClassificacaoDTO;
import br.com.jcv.backend.local.LocalDTO;


public interface LocalService<DTO> extends ApplicationService<DTO> {
	List<DTO> listarLocaisUFCidade(long idUfCidadeItem,
			long[] idLocais) throws AlugueRelaxeException;
	List<DTO> listarLocaisUFCidadeClassificacao(long idUfCidadeItem,
			long[] idClassificacao) throws AlugueRelaxeException;

	List<LocalDTO> listarLocal() throws AlugueRelaxeException;
	List<ClassificacaoDTO> listarClassificacaoLocal() throws AlugueRelaxeException;
}