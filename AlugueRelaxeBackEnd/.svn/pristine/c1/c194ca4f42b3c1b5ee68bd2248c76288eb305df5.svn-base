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

package br.com.jcv.backend.uf;

import java.util.List;

import br.com.jcv.backend.charter.AbstractStaticCharter;
import br.com.jcv.backend.charter.CharterSequence;
import br.com.jcv.backend.charter.MapaBrasilCharter;
import br.com.jcv.backend.charter.PizzaCharter;
import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.UFBusiness;
import br.com.jcv.backend.interfacesdao.DAO;
import br.com.jcv.backend.interfacesdao.UFDAO;
import br.com.jcv.backend.mensagem.MensagemCache;

/**
 * <h1>UFBusiness</h1> 
 * <p>Objetivo desta classe é gerenciar todos os métodos que implementam regras de negócio referentes ao objeto UF.
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public class UFBusinessImpl implements UFBusiness<UFDTO> {
	

	/**
	 * <h2>Atributo id</h2>
	 * <p>Identificador único da propriedade id. Deve ser um número do tipo <code>String</code>.</p>
	 * <p>Representa diretamente a PK da tabela UF.</p>
	 */
	private String id;
	/**
	 * <h2>Atributo descricao</h2>
	 * <p>Descrição da UF. Tipo <code>String</code>.</p>
	 */
	private String descricao;
	/**
	 * <h2>Construtor UFBusiness()</h2>
	 * Construtor simples para a classe.
	 */
	public UFBusinessImpl() {}

	/**
	 * <h2>getId()</h2>
	 * <p>Getter para  retornar o conteúdo do atributo <code>id</code> da instância
	 * corrente do objeto.</p>
	 * @return Long
	 */
	public String getId() {
		return id;
	}

	/**
	 * <h2>setId()</h2>
	 * <p>Setter para atribuir um código para o atributo <code>id</code> da instância
	 * corrente do objeto.</p>
	 * @param String - valor que será atribuído como código da uf.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public void setId(String id) throws AlugueRelaxeException {
		if (id == null || id.trim().length() == 0){
			throw AlugueRelaxeException.getInstance(MSGCODE.CAMPO_ID_UF_NULO, MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_DESCRICAO_VARIAVEL_NULO), descricao);
		}
		this.id = id.toUpperCase();
	}

	/**
	 * <h2>getDescricao()</h2>
	 * <p>Getter para  retornar o conteúdo do atributo <code>descricao</code> da instância
	 * corrente do objeto.</p>
	 * @return String
	 */
	public String getDescricao() {
		return descricao;
	}



	/**
	 * <h2>setDescricao()</h2>
	 * <p>Setter para atribuir uma String para o atributo <code>descricao</code> da instância
	 * corrente do objeto.</p>
	 * @param String - valor que será atribuído como nome da uf.
	 * @throws AlugueRelaxeException - Exceção padrão do sistema.
	 * @return void
	 */
	public void setDescricao(String descricao) throws AlugueRelaxeException   {
		if (descricao == null || descricao.trim().length() == 0){
			throw AlugueRelaxeException.getInstance(MSGCODE.CAMPO_DESCRICAO_UF_NULO, MensagemCache.getInstance().getMensagem(MSGCODE.CAMPO_DESCRICAO_VARIAVEL_NULO), descricao);
		}
		this.descricao = descricao;
	}

	/**
	 * <h2>atualizar()</h2>
	 * Método com a finalidade de realizar a atualizaééo de apenas um registro 
	 * no banco de dados por vez, especificamente na tabela UF.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param UFDTO - DTO contendo os dados que serão atualizados de acordo com a PK.
	 * @return UFDTO - Contendo os dados que foram atualizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public UFDTO atualizar(DAOFactory daofactory, UFDTO dto)
			throws AlugueRelaxeException {
		UFDTO dtoretorno = null;
		this.setDados(dto);
		DAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		dtoretorno = dao.update(this.getDados());
		return dtoretorno;	}

	/**
	 * <h2>buscarTodas()</h2>
	 * Método com a finalidade de realizar uma lista com os dados da tabela UF.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram atualizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<UFDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		List<UFDTO> list = null;
		DAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		list = dao.list();
		return list;
	}

	/**
	 * <h2>excluir()</h2>
	 * Método com a finalidade de excluir um registro da tabela UF.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param UFDTO - DTO contendo os dados que serão atualizados de acordo com a PK.
	 * @return void
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public void excluir(DAOFactory daofactory, UFDTO dto)
			throws AlugueRelaxeException {
		this.setDados(dto);
		DAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		dao.delete(this.getDados());
	}

	/**
	 * <h2>getDados()</h2>
	 * Método com a finalidade de retornar um DTO com base nos atributos da instância corrente
	 * do objeto.
	 * @return UFDTO - DTO de uf
	 */
	public UFDTO getDados() throws AlugueRelaxeException {
		UFDTO dto = new UFDTO();
		dto.setId(this.getId());
		dto.setDescricao(this.getDescricao());
		return dto;	
	}

	/**
	 * <h2>incluir()</h2>
	 * Método com a finalidade de realizar a inclusão de apenas um registro na tabela CIDADES.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param UFDTO - DTO contendo os dados que serão inseridos.
	 * @return UFDTO - Contendo os dados que foram atualizados mais sua PK.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public UFDTO incluir(DAOFactory daofactory, UFDTO dto)
			throws AlugueRelaxeException {
		UFDTO dtoretorno = null;
		this.setDados(dto);
		DAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		dtoretorno = dao.insert(this.getDados());
		return dtoretorno;
	}

	/**
	 * <h2>procurar()</h2>
	 * Método com a finalidade de realizar uma busca com base em um DTO e retornar
	 * um objeto DTO populado com o registro completo de uf.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param UFDTO - DTO contendo os dados da busca.
	 * @return UFDTO - Contendo o registro com os dados que foram localizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public UFDTO procurar(DAOFactory daofactory, UFDTO dto)
			throws AlugueRelaxeException {
		DAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		return dao.load(dto);
	}

	/**
	 * <h2>setDados()</h2>
	 * Método com a finalidade de popular os atributos do objeto corrente e executar o Método
	 * <code>validarCamposObrigatorios()</code>.
	 * @see validarCamposObrigatorios()
	 * @param UFDTO - DTO contendo os dados para carregar o objeto.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public void setDados(UFDTO dto) throws AlugueRelaxeException {
		this.setId(dto.getId());
		this.setDescricao(dto.getDescricao());
		this.validarCamposObrigatorios(dto);
	}

	/**
	 * <h2>validarCamposObrigatorios()</h2>
	 * Método com a finalidade de validar o conteúdo do DTO de acordo com regras de negócio.
	 * @param UFDTO - DTO contendo os dados para carregar o objeto.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public void validarCamposObrigatorios(UFDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * <h2>buscarTodas()</h2>
	 * Método com a finalidade de realizar uma lista com os dados da tabela UF.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param int - Offset inicial para retornar a lista paginada.
	 * @return List - Contendo os dados que foram atualizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<UFDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * <h2>listarMaioresQtdeImoveisPorEstado()</h2>
	 * Método com a finalidade de totalizar os imoveis por estado.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram totalizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<UFDTO> listarMaioresQtdeImoveisPorEstado(DAOFactory daofactory) throws AlugueRelaxeException {
		UFDAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		return dao.listMaioresQtdeImoveisPorEstado(); 
	}

	/**
	 * <h2>listarEstadosMaisVisitados()</h2>
	 * Método com a finalidade de retornar até os 4 estados com maior 
	 * número de visitas aos imóveis.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram totalizados.
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<UFDTO> listarEstadosMaisVisitados(DAOFactory daofactory) throws AlugueRelaxeException {
		UFDAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		return dao.listEstadosMaisVisitados(); 
	}

	public List<CidadeDTO> listarCidadesDaUF(DAOFactory daofactory, String uf) throws AlugueRelaxeException {
		UFDAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		return dao.listarCidadesDaUF(uf); 
	}

	public List<CidadeDTO> listarCidadesDaUFComImoveis(DAOFactory daofactory, String uf) throws AlugueRelaxeException {
		UFDAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		return dao.listarCidadesDaUFComImoveis(uf); 
	}

	public List<UFDTO> listarSumarizadoImoveisUF(DAOFactory daofactory)
			throws AlugueRelaxeException {
		UFDAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		return dao.listSumarizadoImoveisUF(); 
	}

	public String charterSumarizadoImoveisUF(DAOFactory daofactory)
			throws AlugueRelaxeException {
		List<UFDTO> lst = this.listarSumarizadoImoveisUF(daofactory);
		AbstractStaticCharter<CharterSequence> asc =  new MapaBrasilCharter(AbstractStaticCharter.TIPO_CHARTER_MAPA_BRASIL, 300, 300);
		for (UFDTO dto: lst){
			asc.addCharterSequence(new CharterSequence(dto.id, dto.quantidade));
		}
		return asc.getUrl();
	}

	public CidadeUFDTO ProcurarUFCidadeItem(DAOFactory daofactory, long idUfCidadeItem)
			throws AlugueRelaxeException {
		UFDAO<UFDTO> dao = daofactory.getUFDAO(daofactory);
		return dao.load(idUfCidadeItem); 
	}

}
