package br.com.jcv.backend.paginacao;

import java.io.Serializable;
import java.util.List;

import br.com.jcv.backend.dto.DTOPadrao;


public class EnvelopePaginacaoDataGridDTO extends DTOPadrao implements Serializable {
	private static final long serialVersionUID = -2038430178806851993L;
	public long totalRegistros;
	public List<RegDataGridDTO> lst;
}
