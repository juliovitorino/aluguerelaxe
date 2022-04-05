package br.com.jcv.backend.depoimento;

import br.com.jcv.backend.anotacoes.Obrigatorio;
import br.com.jcv.backend.dto.DTOPadrao;

import java.io.Serializable;
import java.util.Date;

public class DepoimentoDTO extends DTOPadrao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8014906402158684376L;
	public Long id;
	
	@Obrigatorio(tamanho=500, notNull=true)
	public String depoimento;

	@Obrigatorio(tamanho=100, notNull=true)
	public String nome;
	
	@Obrigatorio(tamanho=1, 
			dominio={DepoimentoBusinessImpl.PENDENTE,
					DepoimentoBusinessImpl.LIBERADO,
					DepoimentoBusinessImpl.REPROVADO
					 }
				)
	public String status;
	
	public Date dataCadastro;
	
	public DepoimentoDTO() {}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the depoimento
	 */
	public String getDepoimento() {
		return depoimento;
	}
	/**
	 * @param depoimento the depoimento to set
	 */
	public void setDepoimento(String depoimento) {
		this.depoimento = depoimento;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
