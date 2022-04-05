
package br.com.jcv.backend.portal.login;

import br.com.jcv.backend.anotacoes.Obrigatorio;
import br.com.jcv.backend.dto.DTOPadrao;

import java.io.Serializable;

public class LoginDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = -589942694312870287L;
	
	@Obrigatorio(tamanho=100, notNull=true)
	public String email;
	
	@Obrigatorio(tamanho=12, notNull=true)
	public String senha;

}

