
package br.com.jcv.backend.imovel;

import java.io.Serializable;
import java.sql.Timestamp;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.plano.PlanoDTO;

public class ImovelPlanoDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = -7885203070318163743L;

	public long id;
	public PlanoDTO plano;
	public Timestamp dataProximaFatura;
	public Timestamp dataCadastro;
}

