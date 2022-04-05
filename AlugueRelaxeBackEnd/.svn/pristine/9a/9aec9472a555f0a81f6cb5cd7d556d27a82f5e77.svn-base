package br.com.jcv.backend.dto;

import br.com.jcv.backend.anotacoes.Obrigatorio;

import java.io.Serializable;

/**
 * <h1>EnderecoDTO</h1>
 * <p>Classe de transferencia de dados</p>
 * 
 * @author julio
 * @since 1.0
 */
public class EnderecoDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = 6797842037004464096L;
	
	/**
	 * <h2>nome</h2>
	 * <p>Nome do logradouro</p>
	 */
	@Obrigatorio(tamanho=120,notNull=true)
	public String nome;
	/**
	 * <h2>numero</h2>
	 * <p>Numero identificador do imovel ou cliente</p>
	 */
	@Obrigatorio(tamanho=10,notNull=true)
	public String numero;
	/**
	 * <h2>complemento</h2>
	 * <p>Complemento de identificação do endereço do imóvel ou cliente</p>
	 */
	@Obrigatorio(tamanho=20)
	public String complemento;
	/**
	 * <h2>bairro</h2>
	 * <p>Bairro onde o imóvel/cliente encontra-se localizado/reside.</p>
	 */
	@Obrigatorio(tamanho=30,notNull=true)
	public String bairro;
	/**
	 * <h2>cep</h2>
	 * <p>cep do endereço apontado</p>
	 */
	@Obrigatorio(tamanho=8,notNull=true)
	public String cep;
	
	public String cidade;
	
	public String uf;
	
	public String nomeuf;
	
	public long codigoUfCidadeItem;
	
	public String toString() {
		return nome + ", " + numero + ", " + bairro + ", " + cidade + ", " +  uf;
	}
}
