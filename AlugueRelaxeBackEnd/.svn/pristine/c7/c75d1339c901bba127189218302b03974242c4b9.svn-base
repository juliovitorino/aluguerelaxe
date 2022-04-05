/*
*
'* Copyright (c) 2009-2009, Julio Cesar Vitorino, Todos os direitos reservados.
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
package br.com.jcv.backend.imovel;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import br.com.jcv.backend.charter.AbstractStaticCharter;
import br.com.jcv.backend.charter.CharterSequence;
import br.com.jcv.backend.charter.PizzaCharter;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaBusinessImpl;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoBusinessImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.plano.ImovelPlanoBusinessImpl;
import br.com.jcv.backend.imovel.plano.ImovelPlanoRelacaoDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.interfaces.business.ImovelBusiness;
import br.com.jcv.backend.interfaces.business.ImovelImagemVideoBusiness;
import br.com.jcv.backend.interfaces.business.ImovelPlanoBusiness;
import br.com.jcv.backend.interfaces.business.ImovelPlanoFaturaBusiness;
import br.com.jcv.backend.interfaces.business.PlanoBusiness;
import br.com.jcv.backend.interfacesdao.DAO;
import br.com.jcv.backend.interfacesdao.ImovelDAO;
import br.com.jcv.backend.interfacesdao.ImovelHistoricoVisitaDAO;
import br.com.jcv.backend.interfacesdao.ImovelImagemVideoDAO;
import br.com.jcv.backend.plano.PlanoBusinessImpl;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.utility.Introspeccao;
import br.com.jcv.backend.utility.StringUtil;

/**
 * <h1>ImovelBusiness</h1> 
 * <p>Objetivo desta classe é gerenciar todos os Métodos que implementam regras de negócio referentes ao objeto imovel.
 *
 * @author Julio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */
public class ImovelBusinessImpl implements ImovelBusiness<ImovelDTO>{
	/**
	 * <h2>Atributo logger</h2>
	 * <p>Logger para instância corrente de <code>ImovelBusinnes</code></p>
	 */
	private static Logger logger = Logger.getLogger(ImovelBusinessImpl.class.getName());

    
	public ImovelBusinessImpl() {
		
	}
	
	
	/**
	 * <h2>atualizar()</h2>
	 * Método com a finalidade de realizar a atualizaééo de apenas um registro 
	 * no banco de dados por vez, especificamente na tabela IMOVEL.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param ImovelDTO - DTO contendo os dados que serão atualizados de acordo com a PK.
	 * @return ImovelDTO - Contendo os dados que foram atualizados.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public ImovelDTO atualizar(DAOFactory daofactory, ImovelDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <h2>buscarTodas()</h2>
	 * Método com a finalidade de realizar uma lista com os dados da tabela IMOVEL.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @return List - Contendo os dados que foram atualizados.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public List<ImovelDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <h2>excluir()</h2>
	 * Método com a finalidade de excluir um registro da tabela IMOVEL.
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param ImovelDTO - DTO contendo os dados que serão atualizados de acordo com a PK.
	 * @return void
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public void excluir(DAOFactory daofactory, ImovelDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * <h2>getDados()</h2>
	 * <p>Método com a finalidade de retornar um DTO com base nos atributos da instância corrente
	 * do objeto.</p>
	 * @return ImovelDTO - DTO de Cidade
	 */
	public ImovelDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * M�todo sobrescrito.
	 * @see br.com.jcv.backend.interfaces.business.BusinessObject#incluir(br.com.jcv.backend.factory.DAOFactory, java.lang.Object)
	 * @deprecated Use o m�todo <code>incluir(DAOFactory daofactory, ImovelFichaCompletaDTO dto)</code> ao inv�s deste.
	 */
	public ImovelDTO incluir(DAOFactory daofactory, ImovelDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * <h2>procurar()</h2>
	 * <p>Método com a finalidade de realizar uma busca com base em um DTO e retornar
	 * um objeto DTO populado com o registro completo de imóvel.</p>
	 * @param DAOFactory - Implementação concreta de uma conexão hibernate.
	 * @param ImovelDTO - DTO contendo os dados da busca.
	 * @return ImovelDTO - Contendo o registro com os dados que foram localizados.
	 * @author Julio Vitorino
	 * @exception AlugueRelaxeException - Exceção padrão de nível de aplicação.
	 */
	public ImovelDTO procurar(DAOFactory daofactory, ImovelDTO dto)
			throws AlugueRelaxeException {
		DAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		Introspeccao.debugdto(logger, dto);
		return dao.load(dto);
	}

	public void setDados(ImovelDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public void validarCamposObrigatorios(ImovelDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}
	
	public void atualizaIndicadorColaboracao(DAOFactory daofactory,
			ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		ifcdto.tipoColaboracao = dao.loadIndicador(ifcdto.imovel.id);
	}

	public List<Long> listaImoveisPorCidadeUf(
			DAOFactory daofactory, long idCidadeUf, int pagina,int regPagina)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.listImoveisPorCidadeUf(idCidadeUf, pagina, regPagina);
	}

	public long contarImoveisPorCidadeUf(DAOFactory daofactory, long idCidadeUf) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.countImoveisPorCidadeUf(idCidadeUf);
	}

	public List<ImovelFichaCompletaDTO> listaImoveisPorEstado(
			DAOFactory daofactory, String uf, int pagina,int regPagina)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		List<ImovelFichaCompletaDTO> lst = dao.list(uf, pagina, regPagina);
		if(lst != null){
			for (ImovelFichaCompletaDTO ifcdto : lst) {
				//-----------------------------------------------------
				// localiza as imagens do painel de imagens do tipo TB 
				//-----------------------------------------------------
				ImovelImagemVideoBusiness<ImovelImagemVideoDTO> iivb = new ImovelImagemVideoBusinessImpl();
				List<ImovelImagemVideoDTO> imagenstb = iivb.buscarListaImagensTB(daofactory, ifcdto.imovel.id);
				ifcdto.imagensImovelTB = imagenstb;
				List<ImovelImagemVideoDTO> imagensxg = iivb.buscarListaImagensXG(daofactory, ifcdto.imovel.id);
				ifcdto.imagensImovelXG = imagensxg;
				atualizaIndicadorColaboracao(daofactory, ifcdto);
			}
		}
		return lst;
	}

	public long contarImoveisPorEstado(DAOFactory daofactory, String uf) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.count(uf);
	}
	
	public int contarImoveisCliente(DAOFactory daoFactory, long idCliente) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.count(idCliente);
	}
	
	public long contarImoveisSorteio(DAOFactory daofactory,String tipoSorteio) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		long n = 0;
		
		// Devolve a quantidade de imoveis nao sorteados pelo tipo
		if (tipoSorteio.equals("PP")) {
			n = dao.countImoveisSorteioPP("N");
		} else if (tipoSorteio.equals("PD")) {
			n = dao.countImoveisSorteioPD("N");
		} else if (tipoSorteio.equals("SB")) {
			n = dao.countImoveisSorteioSB("N");
		}
		return n;
	}


	public List<ImovelFichaCompletaDTO> listaImoveisPorCidade(
			DAOFactory daofactory, int cidade, int pagina,int regPagina)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		List<ImovelFichaCompletaDTO> lst = dao.list(cidade, pagina, regPagina);
		if(lst != null){
			for (ImovelFichaCompletaDTO ifcdto : lst) {
				//-----------------------------------------------------
				// localiza as imagens do painel de imagens do tipo TB 
				//-----------------------------------------------------
				ImovelImagemVideoBusiness<ImovelImagemVideoDTO> iivb = new ImovelImagemVideoBusinessImpl();
				List<ImovelImagemVideoDTO> imagenstb = iivb.buscarListaImagensTB(daofactory, ifcdto.imovel.id);
				ifcdto.imagensImovelTB = imagenstb;
				List<ImovelImagemVideoDTO> imagensxg = iivb.buscarListaImagensXG(daofactory, ifcdto.imovel.id);
				ifcdto.imagensImovelXG = imagensxg;
				atualizaIndicadorColaboracao(daofactory, ifcdto);
			}
		}
		return lst;
	}

	public long contarImoveisPorCidade(DAOFactory daofactory, int cidade) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.count(cidade);
	}

	/**
	 * <h2>atualizaVisitasImovel</h2>
	 * <p>Atualizacao do contador de visitas do im�vel</p>
	 */
	@Deprecated
	public void atualizaVisitasImovel(DAOFactory daofactory, long codigoImovel) throws AlugueRelaxeException {
		//-----------------------------------------------------------
		// Atualiza o contador de visitas do imovel
		//-----------------------------------------------------------
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		dao.atualizaVisitas(codigoImovel);
		
		//-----------------------------------------------------------
		// Contabiliza o hist�rico mensal de visitas
		//-----------------------------------------------------------
		DateManagerBase dmb = DateManagerBase.getDateManagerInstance();
		int dia = Integer.valueOf(dmb.getDay());
		int mes = Integer.valueOf(dmb.getMonth());
		int ano = Integer.valueOf(dmb.getYear());
		ImovelHistoricoVisitaDAO<ImovelHistoricoVisitaDTO> daohist = daofactory.getImovelHistoricoVisitaDAO(daofactory);
		ImovelHistoricoVisitaDTO dtohist = daohist.load(codigoImovel,ano,mes,dia);
		if (dtohist == null) {
			ImovelHistoricoVisitaDTO ihvdto = new ImovelHistoricoVisitaDTO();
			ihvdto.id = daofactory.getNextSequence(daofactory, "SEQ_IMVI_CD_IMOVEL_VISITAS");
			ihvdto.idImovel = codigoImovel;
			ihvdto.ano = ano ;
			ihvdto.mes = mes;
			ihvdto.dia = dia;
			daohist.insert(ihvdto);
		} else {
			daohist.incrementaImovelHistoricoVisita(dtohist.id);
		}
	}

	/**
	 * <h2>atualizaVisitasImovel</h2>
	 * <p>Atualizacao do contador de visitas do im�vel</p>
	 */
	public void atualizaVisitasImovel(DAOFactory daofactory, long codigoImovel, String origemAcesso) throws AlugueRelaxeException {
		//-----------------------------------------------------------
		// Atualiza o contador de visitas do imovel
		//-----------------------------------------------------------
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		dao.atualizaVisitas(codigoImovel);
		
		//-----------------------------------------------------------
		// Contabiliza o hist�rico mensal de visitas por origem
		// do acesso
		//-----------------------------------------------------------
		DateManagerBase dmb = DateManagerBase.getDateManagerInstance();
		int dia = Integer.valueOf(dmb.getDay());
		int mes = Integer.valueOf(dmb.getMonth());
		int ano = Integer.valueOf(dmb.getYear());
		ImovelHistoricoVisitaDAO<ImovelHistoricoVisitaDTO> daohist = daofactory.getImovelHistoricoVisitaDAO(daofactory);
		ImovelHistoricoVisitaDTO dtohist = daohist.load(codigoImovel,ano,mes,dia,origemAcesso);
		if (dtohist == null) {
			ImovelHistoricoVisitaDTO ihvdto = new ImovelHistoricoVisitaDTO();
			ihvdto.id = daofactory.getNextSequence(daofactory, "SEQ_IMVI_CD_IMOVEL_VISITAS");
			ihvdto.idImovel = codigoImovel;
			ihvdto.ano = ano ;
			ihvdto.mes = mes;
			ihvdto.dia = dia;
			ihvdto.origemAcesso = origemAcesso;
			daohist.insert(ihvdto);
		} else {
			daohist.incrementaImovelHistoricoVisita(dtohist.id);
		}
	}


	/**
	 * <h2>listarQtdeImoveisCidadeUF</h2>
	 * <p>Listar a quantidade de imoveis por cidade - uf</p>
	 * @param
	 * @return List
	 */
	public List<CidadeUFDTO> listarQtdeImoveisCidadeUF(DAOFactory daofactory)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.listarQtdeImoveisCidadeUF();
	}

	public ContatoClienteDTO gravarContatoComAnunciante(DAOFactory daofactory,
			ContatoClienteDTO ccdto) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		ccdto.idOferecimento = daofactory.getNextSequence(daofactory, "SEQ_OFERECIMENTO");
		ccdto.codigoOMCThreadAnunciante = StringUtil.gerarCodigo(12);
		ccdto.codigoOMCThreadVisitante = StringUtil.gerarCodigo(12);
		ccdto.codigoOMCThreadAdmin = StringUtil.gerarCodigo(12);
		return dao.gravarContatoComAnunciante(ccdto);
	}

	public List<ContatoClienteDTO> listarContatosAnunciantesPorStatus(
			DAOFactory daofactory, String status) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.listarContatosAnunciantesPorStatus(status);

	}

	public ImovelFichaCompletaDTO incluir(DAOFactory daofactory, ImovelFichaCompletaDTO dto) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		long novoId = daofactory.getNextSequence(daofactory, "SEQ_IMOV_CD_IMOVEL");
		dto.imovel.id = novoId;
		dto.imovel = dao.insert(dto.imovel);
		return dto;
	}


	public ContatoClienteDTO procurarContatoAnunciante(DAOFactory daofactory,
			String id) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.procurarContatoAnunciante(id);
	}


	public void mudaStatusContatoAnunciante(DAOFactory daoFactory,
			String id, String acao) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.mudaStatusContatoAnunciante(id, acao);
	}


	public void mudaStatusThread(DAOFactory daoFactory,
			String id, String status) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.mudaStatusThread(id, status);
	}


	public List<ImovelDTO> buscarTodas(DAOFactory daoFactory,
			ClienteDTO clientedto) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.list(clientedto);
	}

	public List<ImovelDTO> buscarTodas(DAOFactory daoFactory,
			String indPatrocinadorColaborador) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.list(indPatrocinadorColaborador);
	}


	public ImovelPlanoDTO incluirAdesao(DAOFactory daofactory, long codigoImovel, ImovelPlanoDTO dto)
			throws AlugueRelaxeException {
			
		// Insere o plano
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		dto.id = daofactory.getNextSequence(daofactory, "SEQ_IMPL_CD_IMOVEL_PLANO");
		dao.insert(codigoImovel, dto);
		
		// Insere a primeira fatura que ser� a guia para as pr�ximas
		ImovelPlanoFaturaDTO ipfdto = new ImovelPlanoFaturaDTO();
		ipfdto.id = daofactory.getNextSequence(daofactory, "SEQ_IMPF_CD_IMOVEL_PLANO_FATURA");
		ipfdto.idImovelPlano = dto.id;
		ipfdto.valorFatura = dto.plano.valor;
		ipfdto.valorDesconto = dto.plano.valorDesconto;
		DateManagerBase dv = DateManager.getDateManagerInstance();
		ipfdto.dataVencimento = dv.add(dto.plano.numeroDiasCalculo);
		dao.insert(dto.id, ipfdto);
		
		this.pagarFatura(daofactory, ipfdto.id);
		
		return dto;
	}


	public ImovelPlanoDTO pagarFatura(DAOFactory daofactory, long idFatura)
			throws AlugueRelaxeException {
			
		ImovelPlanoDTO dto = new ImovelPlanoDTO();
		// Insere o plano
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		dto.id = idFatura;
		dao.updateDataPgtoFatura(idFatura);
		
		return dto;
	}



	public ImovelPlanoDTO incluir(DAOFactory daofactory, long codigoImovel, ImovelPlanoDTO dto)
			throws AlugueRelaxeException {
			
		// Insere o plano
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		dto.id = daofactory.getNextSequence(daofactory, "SEQ_IMPL_CD_IMOVEL_PLANO");
		dao.insert(codigoImovel, dto);
		
		// Insere a primeira fatura que ser� a guia para as pr�ximas
		ImovelPlanoFaturaDTO ipfdto = new ImovelPlanoFaturaDTO();
		ipfdto.id = daofactory.getNextSequence(daofactory, "SEQ_IMPF_CD_IMOVEL_PLANO_FATURA");
		ipfdto.idImovelPlano = dto.id;
		ipfdto.valorFatura = dto.plano.valor;
		ipfdto.valorDesconto = dto.plano.valorDesconto;
		ipfdto.dataVencimento = DateManager.getDateManagerInstance().add(dto.plano.numeroDiasCalculo);
		dao.insert(dto.id, ipfdto);
		
		// Modifica o status do im�vel para Pendencia de Pagamento
		dao.updateStatus(codigoImovel, Constantes.IMOVEL_STATUS_PENDENTE_PGTO);
		return dto;
	}


	public ImovelPlanoDTO procurarPlanoFinanceiro(DAOFactory daofactory,
			long codigoImovel) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.procurarPlanoFinanceiro(codigoImovel);
	}

	public void enviarImagensLixeira(DAOFactory daofactory, long idImovel, List<ImovelImagemVideoDTO> lst)
		throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		// Elimina as imagens de TB e MI que vieram no DTO
		if (lst != null) {
			for (ImovelImagemVideoDTO iivvodto : lst ){
				ImovelImagemVideoBusiness<ImovelImagemVideoDTO> iivb = new ImovelImagemVideoBusinessImpl();
				iivb.removeImagensMITB(daofactory, idImovel, iivvodto);
			}
		}
	}

	public void atualizarTarifaImovel(DAOFactory daofactory, ImovelFichaCompletaDTO dto)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		dao.update(dto.imovel.id, dto.tabelaPreco);
	}

	public void atualizarFichaImovel(DAOFactory daofactory, ImovelFichaCompletaDTO dto)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		dao.update(dto);
		this.enviarImagensLixeira(daofactory, dto.imovel.id, dto.imagensImovelMI);
	}


	public List<ImovelDTO> listarUltimosImoveisCadastrados(DAOFactory daoFactory, int qtdeAnuncios)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.listarUltimosImoveisCadastrados(qtdeAnuncios);
	}


	public void adicionarImagemImovel(DAOFactory daoFactory, ImovelImagemVideoDTO iivdto)
			throws AlugueRelaxeException {
		ImovelImagemVideoDAO<ImovelImagemVideoDTO> dao = daoFactory.getImovelImagemVideoDAO(daoFactory);
		iivdto.id = daoFactory.getNextSequence(daoFactory, "SEQ_IMIV_CD_IMOVEL_IMAGEM_VIDE");
		iivdto.ordem = daoFactory.getNextSequence(daoFactory, "SEQ_IMIV_NR_ORDEM");
		dao.insert(iivdto);
		
	}

	public List<ImovelFichaCompletaDTO> listaImoveisPorFiltro(
			DAOFactory daofactory, Map<String,String> param, int pagina,int regPagina)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		List<ImovelFichaCompletaDTO> lst = dao.list(param, pagina, regPagina);
		if(lst != null){
			for (ImovelFichaCompletaDTO ifcdto : lst) {
				//-----------------------------------------------------
				// localiza as imagens do painel de imagens do tipo TB 
				//-----------------------------------------------------
				ImovelImagemVideoBusiness<ImovelImagemVideoDTO> iivb = new ImovelImagemVideoBusinessImpl();
				List<ImovelImagemVideoDTO> imagenstb = iivb.buscarListaImagensTB(daofactory, ifcdto.imovel.id);
				ifcdto.imagensImovelTB = imagenstb;
				atualizaIndicadorColaboracao(daofactory, ifcdto);
			}
		}
		return lst;
	}

	public long contarImoveisPorFiltro(DAOFactory daofactory, Map<String,String> param) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.count(param);
	}


	public void atualizarGeoLocalizacaoImovel(DAOFactory daofactory,
			ImovelFichaCompletaDTO dto) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		dao.update(dto.imovel.id, dto.geolocalizacao);
	}


	public GeoLocalizacaoDTO procurarGeoLocalizacao(DAOFactory daofactory,
			long codigoImovel) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daofactory.getImovelDAO(daofactory);
		return dao.procurarGeoLocalizacao(codigoImovel);
	}


	public String charterDistribuicaoVisitasImovel(DAOFactory daoFactory,
			ImovelFichaCompletaDTO ifcdto, int ano)
			throws AlugueRelaxeException {
		String[] mes = { "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"}; 
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		List<CharterSequence> lst = dao.listarSumarizadoImoveis(ifcdto, ano);
		if (lst != null){
			AbstractStaticCharter<CharterSequence> asc =  PizzaCharter.getPizzaCharter3D(400,200);
			for (CharterSequence dto: lst){
				asc.addCharterSequence(new CharterSequence(mes[Integer.valueOf(dto.legenda)-1] + "("+ (int) dto.valor +")", dto.valor));
			}
			return asc.getUrl();	
		} else {
			return null;
		}
	}


	public List<Long> listarImoveis(DAOFactory daoFactory, String status) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.listImoveis(status);
	}

	public List<Long> listarImoveisParaSorteio(DAOFactory daoFactory) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.listarImoveisParaSorteio();
	}

	public List<Long> listarImoveisParaSorteioPD(DAOFactory daoFactory) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.listarImoveisParaSorteioPD();
	}


	public Date obterMaiorDataPublicidade(DAOFactory daoFactory, String tipo)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.obterMaiorDataPublicidade(tipo);
	}

	@Deprecated
	public void criarPublicidade(DAOFactory daoFactory, PublicidadeImovelDTO pub)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		pub.publicidade.idPublicidade = daoFactory.getNextSequence(daoFactory, "SEQ_PUBL_CD_PUBLICIDADE");
		dao.insert(pub);
	}
	
	public PublicidadeImovelDTO criarPublicidade(DAOFactory daoFactory, PublicidadeImovelDTO pub, long idPlano, boolean aplicaDesconto)
			throws AlugueRelaxeException {
			
		//------------------------------------------------------------------------
		// Busca as condi��es atuais do plano
		//------------------------------------------------------------------------
		PlanoBusiness<PlanoDTO> pb = new PlanoBusinessImpl();
		PlanoDTO pdto = new PlanoDTO();
		pdto.id = idPlano;
		pdto = pb.procurar(daoFactory, pdto);
		
		//------------------------------------------------------------------------
		// Verifica se o imovel x plano existe. Se n�o existir, eh porque o imovel
		// nunca teve uma promocao de publicidade registrada, logo cria-se o 
		// relacionamento.
		//------------------------------------------------------------------------
		ImovelPlanoBusiness<ImovelPlanoRelacaoDTO> ipb = new ImovelPlanoBusinessImpl();
		ImovelPlanoRelacaoDTO ipdto = ipb.procurar( daoFactory, pub.fichaImovel.imovel.id, idPlano);
		
		if (ipdto == null) {
			ipdto = new ImovelPlanoRelacaoDTO();
			ipdto.id = daoFactory.getNextSequence(daoFactory, "SEQ_IMPL_CD_IMOVEL_PLANO");
			ipdto.idImovel = pub.fichaImovel.imovel.id;
			ipdto.idPlano = idPlano;
			ipdto = ipb.incluir(daoFactory, ipdto);
		}
		
		//------------------------------------------------------------------------
		// Registra a fatura e a deixa em aberto
		//------------------------------------------------------------------------
		ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> ipfb = new ImovelPlanoFaturaBusinessImpl();
		ImovelPlanoFaturaDTO ipfdto = new ImovelPlanoFaturaDTO();
		ipfdto.id = daoFactory.getNextSequence(daoFactory, "SEQ_IMPF_CD_IMOVEL_PLANO_FATURA");
		DateManagerBase diff = DateManagerBase.getDateManagerInstance();
		
		if(pub.publicidade.dataFim == null){
			DateManagerBase di = DateManagerBase.getDateManagerInstance(pub.publicidade.dataInicio);
			di.add(pdto.numeroDiasCalculo - 1);
			pub.publicidade.dataFim = di.getDate();
		}
		
		int diffdat = diff.dataDiff( pub.publicidade.dataInicio, pub.publicidade.dataFim) + 1;

		ipfdto.idImovelPlano = ipdto.id;
		ipfdto.valorFatura = pdto.valor * diffdat;
		if (aplicaDesconto) {
			ipfdto.valorDesconto = ipfdto.valorFatura;
		}
		ipfdto.dataVencimento = pub.publicidade.dataFim ;
		ipfdto = ipfb.incluir(daoFactory, ipfdto);
		
		//------------------------------------------------------------------------
		// Registra a publicidade
		//------------------------------------------------------------------------
		// Realiza o De -> para do tipo de compra para  tipo de publicidade
		if (pdto.indicadorTipoCompra.equals(Constantes.TIPO_COMPRA_BANNER)){
			pub.publicidade.tipoPublicidade = (pub.publicidade.tipoPublicidade != null ? pub.publicidade.tipoPublicidade : Constantes.IND_TIPO_PUBLICIDADE_BANNER);
		} else if (pdto.indicadorTipoCompra.equals(Constantes.TIPO_COMPRA_PP)){
			pub.publicidade.tipoPublicidade = (pub.publicidade.tipoPublicidade != null ? pub.publicidade.tipoPublicidade : Constantes.IND_TIPO_PUBLICIDADE_PP);
		} else if (pdto.indicadorTipoCompra.equals(Constantes.TIPO_COMPRA_PD)){
			pub.publicidade.tipoPublicidade = (pub.publicidade.tipoPublicidade != null ? pub.publicidade.tipoPublicidade : Constantes.IND_TIPO_PUBLICIDADE_PD);
		} else if (pdto.indicadorTipoCompra.equals(Constantes.TIPO_COMPRA_EMAIL)){
			pub.publicidade.tipoPublicidade = (pub.publicidade.tipoPublicidade != null ? pub.publicidade.tipoPublicidade : Constantes.IND_TIPO_PUBLICIDADE_EMAIL);
		} else if (pdto.indicadorTipoCompra.equals(Constantes.TIPO_COMPRA_FB)){
			pub.publicidade.tipoPublicidade = (pub.publicidade.tipoPublicidade!= null  ? pub.publicidade.tipoPublicidade : Constantes.IND_TIPO_PUBLICIDADE_FACEBOOK);
		} else if (pdto.indicadorTipoCompra.equals(Constantes.TIPO_COMPRA_FF)){
			pub.publicidade.tipoPublicidade = (pub.publicidade.tipoPublicidade!= null  ? pub.publicidade.tipoPublicidade : Constantes.IND_TIPO_PUBLICIDADE_FF);
		} else if (pdto.indicadorTipoCompra.equals(Constantes.TIPO_COMPRA_PATROCINADOR)){
			pub.publicidade.tipoPublicidade = (pub.publicidade.tipoPublicidade!= null  ? pub.publicidade.tipoPublicidade : Constantes.IND_TIPO_PUBLICIDADE_PATROCINADOR);
		} else if (pdto.indicadorTipoCompra.equals(Constantes.TIPO_COMPRA_SB)){
			pub.publicidade.tipoPublicidade = (pub.publicidade.tipoPublicidade!= null  ? pub.publicidade.tipoPublicidade : Constantes.IND_TIPO_PUBLICIDADE_SB);
		} else if (pdto.indicadorTipoCompra.equals(Constantes.TIPO_COMPRA_SMS)){
			pub.publicidade.tipoPublicidade = (pub.publicidade.tipoPublicidade!= null  ? pub.publicidade.tipoPublicidade : Constantes.IND_TIPO_PUBLICIDADE_SMS);
		}

		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		pub.publicidade.idPublicidade = daoFactory.getNextSequence(daoFactory, "SEQ_PUBL_CD_PUBLICIDADE");
		pub.publicidade.idFatura = ipfdto.id;
		pub.fatura = ipfdto;
		pub.plano = pdto;
		dao.insert(pub);
		return pub;
	}
		


	public void liberarPublicidade(DAOFactory daoFactory, PublicidadeImovelDTO idPublicidade)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.update(idPublicidade);
	}

	public void liberarPublicidade(DAOFactory daoFactory, long idFatura)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.update(idFatura);
	}
	
	public void atualizarStatusSorteio(DAOFactory daoFactory,
			ImovelFichaCompletaDTO ifcdto, String novoStatus)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.update(ifcdto.imovel.id, novoStatus);
	}

	public void atualizarStatusSorteioPD(DAOFactory daoFactory,
			ImovelFichaCompletaDTO ifcdto, String novoStatus)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.updatePD(ifcdto.imovel.id, novoStatus);
	}


	public void atualizarStatusImovel(DAOFactory daoFactory, long codImovel,
			String novoStatus) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.updateStatus(codImovel, novoStatus);
	}


	public List<Long> listarImoveisParaNegativacao(DAOFactory daoFactory)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.listImoveisParaNegativacao();
	}

	public void negativarImovel(DAOFactory daoFactory, long idImovel,
			double pesoNegativacao) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.updateNotaAnuncioImovel(idImovel, pesoNegativacao);
	}


	public List<Long> listarImoveisFuraFila(DAOFactory daoFactory)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.listImoveisColaborador(Constantes.FURAFILA);
	}


	public List<Long> listarImoveisPatrocinadores(DAOFactory daoFactory)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.listImoveisColaborador(Constantes.PATROCINADOR);
	}

	public void atualizaSaldoFuraFila(DAOFactory daoFactory, long idImovel, double vlr) 
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.updateSaldoFuraFila(idImovel, vlr);
	}


	public void atualizarStatusImovelColaborador(DAOFactory daoFactory, long codImovel,
			String novoStatus, double valor) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		dao.updateStatusColaborador(codImovel, novoStatus, valor);
	}
	
	public List<Long> listarColaboradorInconsistente(DAOFactory daoFactory) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.listColaboradorInconsistente();
	}


	public List<Long> listarIDImoveis(DAOFactory daoFactory, String uf,
			String cidade) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.listIDImoveis(uf,cidade);
	}

	public long ImovelIDProximoOferecimento(DAOFactory daoFactory, String uf,
			String cidade) throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		return dao.loadIDProximoOferecimento(uf,cidade);
	}


	public void atualizarOferecimento(DAOFactory daoFactory, long id)
			throws AlugueRelaxeException {
		ImovelDAO<ImovelDTO> dao = daoFactory.getImovelDAO(daoFactory);
		long seq = daoFactory.getNextSequence(daoFactory, "SEQ_OFERECIMENTO");
		dao.updateOferecimento(id, seq);
	}

}
