package br.com.jcv.aluguerelaxe.server.paginacao;

import java.io.Serializable;
import java.util.List;

import br.com.jcv.backend.dto.DTOPadrao;

public class EnvelopePaginacaoDTO<V extends DTOPadrao> extends DTOPadrao implements Serializable {
	
	private static final long serialVersionUID = -7841852364062961882L;
	public long totalRegistros;
	public List<V> lst;
}
