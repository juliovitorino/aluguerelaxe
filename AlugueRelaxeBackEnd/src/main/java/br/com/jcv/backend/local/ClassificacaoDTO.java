package br.com.jcv.backend.local;

import java.io.Serializable;
import java.sql.Timestamp;

import br.com.jcv.backend.dto.DTOPadrao;

public class ClassificacaoDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = 1391997177990018529L;
	
	public long id;
	public String descricao;
	public Timestamp dataCadastro;
}
