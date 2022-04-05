package br.com.jcv.backend.imovel;

import java.io.Serializable;

import br.com.jcv.backend.dto.DTOPadrao;

public class TipoColaboracaoDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = 4784906718835117493L;
	
	public String indicadorTipoColaboracao;
	public double valor;

}
