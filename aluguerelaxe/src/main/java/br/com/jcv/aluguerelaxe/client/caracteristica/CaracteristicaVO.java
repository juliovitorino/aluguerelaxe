package br.com.jcv.aluguerelaxe.client.caracteristica;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CaracteristicaVO implements IsSerializable {
	/**
	 * <h2>Atributo id</h2>
	 * <p>Identificador �nico da propriedade id. Deve ser um n�mero do tipo <code>Long</code>.</p>
	 * <p>Representa diretamente a PK da tabela UF.</p>
	 */
	public Long id;
	/**
	 * <h2>Atributo nome</h2>
	 * <p>Nome da caracter�stica. Tipo <code>String</code>.</p>
	 */
	public String nome;
	/**
	 * <h2>Atributo descricaoAnuncio</h2>
	 * <p>Descri��o detalhada da caracter�stica. Tipo <code>String</code>.</p>
	 */
	public String descricaoAnuncio;

}
