package br.com.jcv.backend.modopublicidade;

import java.io.Serializable;
import java.sql.Timestamp;

import br.com.jcv.backend.dto.DTOPadrao;

public class ModoPublicidadeDTO extends DTOPadrao implements Serializable {
	private static final long serialVersionUID = -1766109305061893785L;
	public long id;
	public String descricao;
	public Timestamp dataCadastro;
}
