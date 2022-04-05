package br.com.jcv.backend.imovel;

import br.com.jcv.backend.anotacoes.Obrigatorio;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.dto.EnderecoDTO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <h1>ImovelBaseDTO</h1> 
 * <p>Classe de base para Imovel de transferencia de dados</p>
 *
 * @author Julio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */
public abstract class ImovelBaseDTO extends DTOPadrao implements Serializable {
	/**
	 * Atributo '<code>serialVersionUID</code>' do tipo long
	 */
	private static final long serialVersionUID = -3282413149287072675L;
	/**
	 * <h2>id</h2>
	 * <p>Identificador unico do imovel.</p>
	 */
	public long id;
	/**
	 * <h2>idhash</h2>
	 * <p>Identificador unico do imovel em hash.</p>
	 */
	public String idhash;
	/**
	 * <h2>idCliente</h2>
	 * <p>Identificador do responsável pelo imóvel.</p>
	 */
	public long idCliente;
	/**
	 * <h2>qtdeQuartos</h2>
	 * <p>Quantidade de quartos existentes no im�vel.</p>
	 */
	public int qtdeQuartos;
	/**
	 * <h2>qtdeSuites</h2>
	 * <p>Quantidade de suÃ­tes existentes no im�vel.</p>
	 */
	public int qtdeSuites;
	
	/**
	 * <h2>qtdeBanheiros</h2>
	 * <p>Quantidade de banheiros no im�vel</p>
	 */
	public int qtdeBanheiros;
	
	/**
	 *<h2>qtdeCapacidade</h2>
	 *<p>Quantidade de pessoas m�xima permitida no im�vel</p> 
	 */
	public int qtdeCapacidade;
	
	/**
	 * <h2>descricaoGeral</h2>
	 * <p>DescriÃ§Ã£o geral do im�vel</p>
	 */
	@Obrigatorio(tamanho=2000, notNull=true)
	public String descricaoGeral;
	/**
	 * <h2>descricaoQuartos</h2>
	 * <p>DescriÃ§Ã£o geral dos quartos.</p>
	 */
	@Obrigatorio(tamanho=2000, notNull=true)
	public String descricaoQuartos;
	/**
	 * <h2>descricaoQuartos</h2>
	 * <p>DescriÃ§Ã£o geral dos quartos.</p>
	 */
	@Obrigatorio(tamanho=1, notNull=true,
			dominio={Constantes.SIM,
					 Constantes.NAO
					 }
				)
	public String indicadorCondominio;
	/**
	 * <h2>descricaoArredores</h2>
	 * <p>DescriÃ§Ã£o geral dos arredores.</p>
	 */
	@Obrigatorio(tamanho=2000, notNull=true)
	public String descricaoArredores;
	/**
	 * <h2>observacoes</h2>
	 * <p>ObservaÃ§Ãµes gerais sobre o im�vel</p>
	 */
	@Obrigatorio(tamanho=2000)
	public String observacoes;
	/**
	 * <h2>indicadorTipoPropriedade</h2>
	 * <p>Indicador do tipo de propriedade de acordo com as constraints definidas
	 * na tabela IMOVEL.</p>
	 */
	@Obrigatorio(tamanho=1, notNull=true,
			dominio={ImovelBusinessImpl.TIPO_PROPRIEDADE_CASA,
					 ImovelBusinessImpl.TIPO_PROPRIEDADE_APARTAMENTO,
					 ImovelBusinessImpl.TIPO_PROPRIEDADE_HOTEL,
					 ImovelBusinessImpl.TIPO_PROPRIEDADE_CHACARA,
					 ImovelBusinessImpl.TIPO_PROPRIEDADE_FLAT,
					 ImovelBusinessImpl.TIPO_PROPRIEDADE_FAZENDA,
					 ImovelBusinessImpl.TIPO_PROPRIEDADE_SITIO,
					 ImovelBusinessImpl.TIPO_PROPRIEDADE_CHALE,
					 ImovelBusinessImpl.TIPO_PROPRIEDADE_POUSADA
					}
				)
	public String indicadorTipoPropriedade;
	
	public String indicadorTipoPropriedadeStr;
	/**
	 * <h2>qtdeVisitas</h2>
	 * <p>Acumulador de visitas da ficha do imovel. Cada vez que a ficha do imovel
	 * e visitada o campo na tabela IMOVEL e incrementado de 1, este resultado sera
	 * refletido neste atributo.</p>
	 */
	public int qtdeVisitas;
	/**
	 * <h2>endereco</h2>
	 * <p>Enderecoo do imovel de temporada.</p>
	 */
	public EnderecoDTO endereco;
	/**
	 * <h2>dataCadastro</h2>
	 * <p>Data em que o im�vel foi cadastrado no banco de dados.</p>
	 */
	public java.sql.Timestamp dataCadastro;

	//---------------------------------------------------
	// Getters e Setters para uso no Hibernate
	//---------------------------------------------------
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public int getQtdeQuartos() {
		return qtdeQuartos;
	}
	public void setQtdeQuartos(int qtdeQuartos) {
		this.qtdeQuartos = qtdeQuartos;
	}
	public int getQtdeSuites() {
		return qtdeSuites;
	}
	public void setQtdeSuites(int qtdeSuites) {
		this.qtdeSuites = qtdeSuites;
	}
	public String getDescricaoGeral() {
		return descricaoGeral;
	}
	public void setDescricaoGeral(String descricaoGeral) {
		this.descricaoGeral = descricaoGeral;
	}
	public String getDescricaoQuartos() {
		return descricaoQuartos;
	}
	public void setDescricaoQuartos(String descricaoQuartos) {
		this.descricaoQuartos = descricaoQuartos;
	}
	public String getDescricaoArredores() {
		return descricaoArredores;
	}
	public void setDescricaoArredores(String descricaoArredores) {
		this.descricaoArredores = descricaoArredores;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getIndicadorTipoPropriedade() {
		return indicadorTipoPropriedade;
	}
	public void setIndicadorTipoPropriedade(String indicadorTipoPropriedade) {
		this.indicadorTipoPropriedade = indicadorTipoPropriedade;
	}
	public int getQtdeVisitas() {
		return qtdeVisitas;
	}
	public void setQtdeVisitas(int qtdeVisitas) {
		this.qtdeVisitas = qtdeVisitas;
	}
	public EnderecoDTO getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}

