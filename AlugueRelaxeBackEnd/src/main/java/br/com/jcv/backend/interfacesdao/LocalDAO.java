package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.local.ClassificacaoDTO;
import br.com.jcv.backend.local.LocalDTO;

public interface LocalDAO<DTO> extends DAO<DTO> {

	List<DTO> listLocaisUFCidade(long idUfCidadeItem, long[] idLocais) throws AlugueRelaxeException;
	List<DTO> listLocaisUFCidadeClassificacao(long idUfCidadeItem, long[] idClassificacao) throws AlugueRelaxeException;
	List<LocalDTO> listLocal() throws AlugueRelaxeException;
	List<ClassificacaoDTO> listClassificacaoLocal() throws AlugueRelaxeException;

}
