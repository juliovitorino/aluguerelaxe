/*
*
* Copyright (c) 2009-2009, Julio Cesar Vitorino, Todos os direitos reservados.
*
* This software is the confidential and proprietary information of Sun
* Microsystems, Inc. ("Confidential Information"). You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with Sun.
*
* SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
* THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
* TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
* PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
* ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
* DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/

package br.com.jcv.backend.caracteristicas;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.CaracteristicaBusiness;
import br.com.jcv.backend.interfacesdao.DAO;
import br.com.jcv.backend.utility.Introspeccao;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * <h1>CarateristicaBusiness</h1> 
 * <p>Objetivo desta classe é gerenciar todos os Métodos que implementam regras de negócio referentes ao objeto caracteristica.
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 31 May 2009
 */
public class CaracteristicaBusinessImpl implements CaracteristicaBusiness<CaracteristicaDTO> {
	
	/**
	 * <h2>Atributo logger</h2>
	 * <p>Logger para instância corrente de <code>CidadeBusinnes</code></p>
	 */
	private static Logger logger = Logger.getLogger(CaracteristicaBusinessImpl.class.getName());

	/**
	 * <h2>Atributo TRACE</h2>
	 * <p>Constante de identificação para trace da classe dentro
	 * dos arquivos de log</p>
	 */
	private final static String TRACE = "BUSINESS:";
	
	/**
	 * <h2>Atributo id</h2>
	 * <p>Identificador único da propriedade id. Deve ser um número do tipo <code>Long</code>.</p>
	 * <p>Representa diretamente a PK da tabela CARACTERISTICA.</p>
	 */
	private Long id;
	
	/**
	 * <h2>Atributo nome</h2>
	 * <p>Nome da caracteristica. Tipo <code>String</code>.</p>
	 * <p>Representa diretamente a PK da tabela CARACTERISTICA.</p>
	 */
	private String nome;
	/**
	 * <h2>Atributo descricaoAnuncio</h2>
	 * <p>Descrição detalhada da característica. Tipo <code>String</code>.</p>
	 */
	public String descricaoAnuncio;
	
	/**
	 * <h2>Construtor CidadeBusiness()</h2>
	 * Construtor simples para a classe.
	 */
	public CaracteristicaBusinessImpl() {
		logger.trace(TRACE.concat(CaracteristicaBusinessImpl.class.getName()).concat(" criada."));
	}
	
	/**
	 * <h2>getId()</h2>
	 * <p>Getter para  retornar o conteúdo do atributo <code>id</code> da instância
	 * corrente do objeto.</p>
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <h2>setId()</h2>
	 * <p>Setter para atribuir um código para o atributo <code>id</code> da instância
	 * corrente do objeto.</p>
	 * @param Long - valor que será atribuído como código da cidade.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @throws IllegalArgumentException - Argumento ilegal informado no parametro.
	 * @return void
	 */
	public void setId(Long id) throws AlugueRelaxeException {
		if (id != null ) {
			if (id < 0) {
				throw new IllegalArgumentException();
			}
			this.id = id;
		}
	}

	/**
	 * <h2>getNome()</h2>
	 * <p>Getter para  retornar o conteúdo do atributo <code>nome</code> da instância
	 * corrente do objeto.</p>
	 * @return String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * <h2>setNome()</h2>
	 * <p>Setter para atribuir uma String para o atributo <code>nome</code> da instância
	 * corrente do objeto.</p>
	 * @param String - valor que será atribuído como nome da cidade.
	 * @return void
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * <h2>getDescricaoAnuncio()</h2>
	 * <p>Getter para  retornar o conteúdo do atributo <code>descricaoAnuncio</code> da instância
	 * corrente do objeto.</p>
	 * @return String
	 */
	public String getDescricaoAnuncio() {
		return descricaoAnuncio;
	}
	/**
	 * <h2>setDescricaoAnuncio()</h2>
	 * <p>Setter para atribuir uma String para o atributo <code>descricaoAnuncio</code> da instância
	 * corrente do objeto.</p>
	 * @param String - valor que será atribuído como nome.
	 * @return void
	 */
	public void setDescricaoAnuncio(String descricaoAnuncio) {
		this.descricaoAnuncio = descricaoAnuncio;
	}

	/**
	 * <h2>atualizar()</h2>
	 * Método com a finalidade de realizar a atualizaééo de apenas um registro 
	 * no banco de dados por vez, especificamente na tabela CIDADES.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param CaracteristicaDTO - DTO contendo os dados que serão atualizados de acordo com a PK.
	 * @return CaracteristicaDTO - Contendo os dados que foram atualizados.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public CaracteristicaDTO atualizar(DAOFactory daofactory, CaracteristicaDTO dto)
			throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
		CaracteristicaDTO dtoretorno = null;
		this.setDados(dto);
		DAO<CaracteristicaDTO> dao = daofactory.getCaracteristicaDAO(daofactory);
		dtoretorno = dao.update(this.getDados());
		return dtoretorno;
	}

	/**
	 * <h2>buscarTodas()</h2>
	 * Método com a finalidade de realizar uma lista com os dados da tabela CIDADES.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram atualizados.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<CaracteristicaDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		List<CaracteristicaDTO> list = null;
		DAO<CaracteristicaDTO> dao = daofactory.getCaracteristicaDAO(daofactory);
		list = dao.list();
		return list;
	}

	/**
	 * <h2>excluir()</h2>
	 * Método com a finalidade de excluir um registro da tabela CARACTERISTICA.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param CaracteristicaDTO - DTO contendo os dados que serão atualizados de acordo com a PK.
	 * @return void
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public void excluir(DAOFactory daofactory, CaracteristicaDTO dto)
			throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
		this.setDados(dto);
		DAO<CaracteristicaDTO> dao = daofactory.getCaracteristicaDAO(daofactory);
		dao.delete(this.getDados());
	}

	/**
	 * <h2>getDados()</h2>
	 * Método com a finalidade de retornar um DTO com base nos atributos da instância corrente
	 * do objeto.
	 * @return CaracteristicaDTO - DTO de Cidade
	 */
	public CaracteristicaDTO getDados() throws AlugueRelaxeException {
		CaracteristicaDTO dto = new CaracteristicaDTO();
		dto.setId(this.getId());
		dto.setNome(this.getNome());
		dto.setDescricaoAnuncio(this.getDescricaoAnuncio());
		return dto;
	}

	/**
	 * <h2>incluir()</h2>
	 * Método com a finalidade de realizar a inclusão de apenas um registro na tabela CIDADES.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param CaracteristicaDTO - DTO contendo os dados que serão inseridos.
	 * @return CaracteristicaDTO - Contendo os dados que foram atualizados mais sua PK.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public CaracteristicaDTO incluir(DAOFactory daofactory, CaracteristicaDTO dto)
			throws AlugueRelaxeException {

		Introspeccao.debugdto(logger, dto);
		CaracteristicaDTO dtoretorno = null;
		this.setDados(dto);
		DAO<CaracteristicaDTO> dao = daofactory.getCaracteristicaDAO(daofactory);
		dtoretorno = dao.insert(this.getDados());
		return dtoretorno;
	}

	/**
	 * <h2>procurar()</h2>
	 * Método com a finalidade de realizar uma busca com base em um DTO e retornar
	 * um objeto DTO populado com o registro completo de cidade.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param CaracteristicaDTO - DTO contendo os dados da busca.
	 * @return CaracteristicaDTO - Contendo o registro com os dados que foram localizados.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public CaracteristicaDTO procurar(DAOFactory daofactory, CaracteristicaDTO dto)
			throws AlugueRelaxeException {
		DAO<CaracteristicaDTO> dao = daofactory.getCaracteristicaDAO(daofactory);
		Introspeccao.debugdto(logger, dto);
		return dao.load(dto);
	}

	/**
	 * <h2>setDados()</h2>
	 * Método com a finalidade de popular os atributos do objeto corrente e executar o Método
	 * <code>validarCamposObrigatorios()</code>.
	 * @see validarCamposObrigatorios()
	 * @param CaracteristicaDTO - DTO contendo os dados para carregar o objeto.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public void setDados(CaracteristicaDTO dto) throws AlugueRelaxeException {
		Introspeccao.debugdto(logger, dto);
		this.validarCamposObrigatorios(dto);
		this.setId(dto.getId());
		this.setNome(dto.getNome());
		this.setDescricaoAnuncio(dto.getDescricaoAnuncio());
	}
	/**
	 * <h2>validarCamposObrigatorios()</h2>
	 * Método com a finalidade de validar o conteúdo do DTO de acordo com regras de negócio.
	 * @param CaracteristicaDTO - DTO contendo os dados para carregar o objeto.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public void validarCamposObrigatorios(CaracteristicaDTO dto)
			throws AlugueRelaxeException {
	}

	/**
	 * <h2>buscarTodas()</h2>
	 * Método com a finalidade de realizar uma lista com os dados da tabela CIDADES.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param int - Offset inicial para retornar a lista paginada.
	 * @return List - Contendo os dados que foram atualizados.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<CaracteristicaDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		return null;
	}

}
