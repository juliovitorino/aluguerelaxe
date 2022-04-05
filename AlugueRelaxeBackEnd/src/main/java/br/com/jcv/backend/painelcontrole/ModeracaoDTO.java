package br.com.jcv.backend.painelcontrole;

import java.io.Serializable;

import br.com.jcv.backend.dto.DTOPadrao;

public class ModeracaoDTO extends DTOPadrao implements Serializable{

	private static final long serialVersionUID = -7932746539444688704L;
	
	public long totalContatosPendentes;
	public long totalDepoimentosPendentes;

}
