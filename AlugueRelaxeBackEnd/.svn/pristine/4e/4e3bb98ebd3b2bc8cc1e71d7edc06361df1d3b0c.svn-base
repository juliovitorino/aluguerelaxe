
package br.com.jcv.backend.cliente;

import br.com.jcv.backend.anotacoes.Obrigatorio;

import java.io.Serializable;

public class ClienteContraSenhaDTO extends ClienteDTO implements Serializable{

	private static final long serialVersionUID = -6177660046495940716L;

	@Obrigatorio(tamanho=10, notNull=true)
	public String senha;

	@Obrigatorio(tamanho=10, notNull=true)
	public String contraSenha;
	
	@Obrigatorio(tamanho=40)
	public String senhaHash;
}

