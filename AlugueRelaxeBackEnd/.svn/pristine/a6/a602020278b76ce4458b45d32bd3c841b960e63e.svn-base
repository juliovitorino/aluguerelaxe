/*
*
*
** Copyright (c) 2009-2009, Julio Cesar Vitorino, Todos os direitos reservados.
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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.cache.UltimosImoveisCadastradosCache;
import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteBusinessImpl;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.datemanager.DateManager;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.filasms.FilaSMSServiceImpl;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaBusinessImpl;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaServiceImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoBusinessImpl;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeBusinessImpl;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoBusinessImpl;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadBusinessImpl;
import br.com.jcv.backend.interfaces.business.ClienteBusiness;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteBusiness;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteThreadBusiness;
import br.com.jcv.backend.interfaces.business.ImovelBusiness;
import br.com.jcv.backend.interfaces.business.ImovelCaracteristicaBusiness;
import br.com.jcv.backend.interfaces.business.ImovelImagemVideoBusiness;
import br.com.jcv.backend.interfaces.business.ImovelPublicidadeBusiness;
import br.com.jcv.backend.interfaces.business.PlanoBusiness;
import br.com.jcv.backend.interfaces.business.TabelaPrecoBusiness;
import br.com.jcv.backend.interfaces.services.FilaSMSService;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.interfaces.services.PlanoService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.plano.PlanoBusinessImpl;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.plano.PlanoServicesImpl;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

/**
 * <h1>ImovelServices</h1> 
 * <p>Classe de servi√ßos para Cidade</p>
 * <p>Esta classe tem por objetivo invocar os M√©todos de neg√≥cio nos respectivos Business Object apontados pela interface <code>BusinessObject</code>.
 * </p>
 * <p>Esta √© a camada de comunica√ß√£o que o cliente usa para ter acesso ao backend de neg√≥cios, podem ser:</p>
 * <ul>
 *    <li>Chamada RPC via servlet</li>
 *    <li>JUnit</li>
 * </ul>
 * <p> Todo M√©todo de <code>ApplicationService</code> que invocar uma chamada a Implementa√ß√£o de <code>BusinessObject</code> envolvendo acesso a dados, deve quando necess√©rio
 * abrir um contexto de transa√ß√£o, tal como apresentando no c√≥digo abaixo:
 * <pre>		
 * 		try {
 *			daoFactory = DAOFactory.getDAOFactory();
 *			daoFactory.open();
 *			daoFactory.beginTransaction();
 *			
 *			BusinessObject<UFDTO> bo = new UFBusiness();
 *			dtoretorno = bo.atualizar(daoFactory, dto);
 *			daoFactory.commit();
 *			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
 *			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
 *		} catch (Exception e) {
 *			daoFactory.rollback();
 *			e.printStackTrace();
 *			throw new AlugueRelaxeException(e);
 *		} finally {
 *			try {
 *				daoFactory.close();
 *			} catch (Exception e) {
 *				throw new AlugueRelaxeException(e);
 *			}
 *		}
 * </pre>
 * 
 * <br>O ideal √© que toda M√©todo de <code>ApplicationService</code> ao seu final devolva encapsulada uma mensagem de retorno
 * para que o cliente que invocou o servi√©o tenha o retorno da opera√ß√£o e decida fazer o que for melhor para seu processo.
 * </p>
 * @author J√∫lio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */

public class ImovelServiceImpl<X extends ImovelBaseDTO> implements ImovelService<ImovelBaseDTO>{

	/**
	 * <h2>Atributo logger</h2>
	 * <p>Usado para obter uma inst√¢ncia de <code>Logger</code>
	 * para a classe UFServices.</p>
	 */
	private static final Logger logger = Logger.getLogger(ImovelServiceImpl.class.getName());
	/**
	 * <h2>atualizarRegistro()</h2>
	 * <p>M√©todo com a finalidade de realizar a atualiza√ß√£o de registros 
	 * no banco de dados usando o metodo de neg√≥cio <code>BusinessObject.atualizar()</code>.</p>
	 * @param ImovelDTO - DTO contendo os dados que ser√£o atualizados fornecidos pela camada cliente.
	 * @return ImovelDTO - DTO Contendo o resultado da opera√ß√£o.
	 * @exception AlugueRelaxeException - Exce√ß√£o padr√£o de n√≠vel de aplica√ß√£o.
	 */
	public ImovelBaseDTO atualizarRegistro(ImovelBaseDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <h2>excluirRegistro()</h2>
	 * <p>M√©todo com a finalidade de realizar a exclus√£o de registros 
	 * no banco de dados usando o metodo de neg√≥cio <code>BusinessObject.excluir()</code>.</p>
	 * @param ImovelDTO - DTO contendo a chave de pesquisa para exclus√£o do registro.
	 * @return ImovelDTO - DTO Contendo o resultado da opera√ß√£o.
	 * @exception AlugueRelaxeException - Exce√ß√£o padr√£o de n√≠vel de aplica√ß√£o.
	 */
	public ImovelBaseDTO excluirRegistro(ImovelBaseDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <h2>gravarRegistro()</h2>
	 * <p>M√©todo com a finalidade de realizar a inclus√£o de registros 
	 * no banco de dados usando o metodo de neg√≥cio <code>BusinessObject.incluir()</code>.</p>
	 * @param ImovelDTO - DTO contendo os dados para inclus√£o do registro.
	 * @return ImovelDTO - DTO Contendo o resultado da opera√ß√£o.
	 * @exception AlugueRelaxeException - Exce√ß√£o padr√£o de n√≠vel de aplica√ß√£o.
	 */
	public ImovelBaseDTO gravarRegistro(ImovelBaseDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <h2>listarRegistros()</h2>
	 * <p>M√©todo com a finalidade de retornar toda a lista de registros 
	 * do banco de dados usando o metodo de neg√≥cio <code>BusinessObject.buscarTodas()</code>.</p>
	 * @param ImovelDTO - DTO contendo os dados para inclus√£o do registro.
	 * @return List - DTO Contendo o resultado da opera√ß√£o.
	 * @exception AlugueRelaxeException - Exce√ß√£o padr√£o de n√≠vel de aplica√ß√£o.
	 */
	public List<? extends ImovelBaseDTO> listarRegistros() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <h2>pesquisarRegistros()</h2>
	 * <p>M√©todo com a finalidade de retornar um registro  
	 * do banco de dados usando o metodo de neg√≥cio <code>BusinessObject.procurar()</code>.</p>
	 * @param ImovelDTO - DTO contendo a chave de pesquisa do registro.
	 * @return ImovelDTO - DTO Contendo o resultado da opera√ß√£o.
	 * @exception AlugueRelaxeException - Exce√ß√£o padr√£o de n√≠vel de aplica√ß√£o.
	 */
	public ImovelBaseDTO pesquisarRegistro(ImovelBaseDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <h2>listImovelPublicidadePaginaPrincipal</h2>
	 * <p>Metodo para contar quantos imoveis estao LIBERADOS para publicidade do tipo PP
	 * </p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	public int contarImoveisCliente(long idCliente) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		int retorno = 0;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();
			retorno = bo.contarImoveisCliente(daoFactory, idCliente);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return retorno;
	}

	/**
	 * <h2>listImovelPublicidadePaginaPrincipal</h2>
	 * <p>Metodo para contar quantos imoveis estao LIBERADOS para publicidade do tipo PP
	 * </p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	public int contarGradePublicidade(Date d, String tipo) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		int retorno = 0;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPublicidadeBusiness<ImovelPublicidadeDTO> bo = new ImovelPublicidadeBusinessImpl();
			retorno = bo.contarGradePublicidade(daoFactory, d, tipo);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return retorno;
	}

	/**
	 * <h2>listImovelPublicidadePaginaPrincipal</h2>
	 * <p>M√©todo respons√°vel por listar todos os im√≥veis dispon√≠veis para
	 * o painel publicidade na primeira p√°gina do site.
	 * </p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	public List<? extends ImovelBaseDTO> listImovelPublicidadePaginaPrincipal() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<? extends ImovelBaseDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPublicidadeBusiness<ImovelPublicidadeDTO> bo = new ImovelPublicidadeBusinessImpl();
			dtoretorno = bo.listImovelPublicidadePaginaPrincipal(daoFactory);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	/**
	 * <h2>listImovelPublicidadePaginaPrincipal</h2>
	 * <p>M√©todo respons√°vel por listar todos os im√≥veis dispon√≠veis para
	 * o painel publicidade na primeira p√°gina do site.
	 * </p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	public List<? extends ImovelBaseDTO> listImovelSuperBanner() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<? extends ImovelBaseDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPublicidadeBusiness<ImovelPublicidadeDTO> bo = new ImovelPublicidadeBusinessImpl();
			dtoretorno = bo.listImovelSuperBanner(daoFactory);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	
	
	/**
	 * <h2>listImovelPublicidadePaginaDestaque</h2>
	 * <p>MÈtodo respons·vel por listar todos os imÛveis disponÌ≠veis para
	 * o painel publicidade destaque do site.
	 * </p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	public List<? extends ImovelBaseDTO> listImovelPublicidadePaginaDestaque()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<? extends ImovelBaseDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPublicidadeBusiness<ImovelPublicidadeDTO> bo = new ImovelPublicidadeBusinessImpl();
			dtoretorno = bo.listImovelPublicidadePaginaDestaque(daoFactory);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	
	/**
	 * <h2>pesquisarFichaCompletaImovel</h2>
	 * <p>ImplementaÁ„o do mÈtodo respons·vel por localizar a ficha completa do imÛvel</p>
	 * @param codigoImovel
	 * @return ImovelFichaCompletaDTO
	 * @exception AlugueRelaxeException
	 */
	public ImovelFichaCompletaDTO pesquisarFichaCompletaImovel(long codigoImovel)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ImovelFichaCompletaDTO dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			//-----------------------------------------------------
			// localiza as informaÁıes do imÛvel
			//-----------------------------------------------------
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();
			ImovelDTO imoveldto = new ImovelDTO();
			imoveldto.id = codigoImovel;
			imoveldto = bo.procurar(daoFactory, imoveldto);
			imoveldto.idhash = this.calculaHash(imoveldto.id).toLowerCase();
			
			//-----------------------------------------------------
			// localiza as informaÁıes do propriet·rio do imÛvel
			//-----------------------------------------------------
			ClienteBusiness<ClienteDTO> cb = new ClienteBusinessImpl();
			ClienteDTO clientedto = new ClienteDTO();
			clientedto.id = imoveldto.idCliente;
			clientedto = cb.procurar(daoFactory, clientedto);
			clientedto.telefones = cb.obtemTelefonesCliente(daoFactory, imoveldto.idCliente);

			//-----------------------------------------------------
			// localiza as caracterÌsticas do imÛvel e 
			// do condomÌnio
			//-----------------------------------------------------
			ImovelCaracteristicaBusiness<ImovelCaracteristicaDTO> icb = new ImovelCaracteristicaBusinessImpl();
			List<ImovelCaracteristicaDTO> caracteristicaImovel = icb.buscarCaracteristicasImovel(daoFactory, imoveldto.id);
			List<ImovelCaracteristicaDTO> caracteristicaCondominio = icb.buscarCaracteristicasCondominio(daoFactory, imoveldto.id);
			
			//-----------------------------------------------------
			// localiza as informaÁıes sobre os preÁos praticados
			// para este imÛvel e insere a tarifa basica do 
			// imovel se valor > 0
			//-----------------------------------------------------
			TabelaPrecoBusiness<TabelaPrecoDTO> tpb = new TabelaPrecoBusinessImpl();
			List<TabelaPrecoDTO> tabelaprecos =  tpb.buscarTabelaPrecoImovel(daoFactory, imoveldto.id);
			if ((tabelaprecos == null) && (imoveldto.valorTarifaBasica > 0)) {
				tabelaprecos = new ArrayList<TabelaPrecoDTO>();
				TabelaPrecoDTO tpdto = new TabelaPrecoDTO();
				tpdto.id = 0;
				tpdto.textoPeriodo = "Diaria base";
				tpdto.valorTabela = imoveldto.valorTarifaBasica;
				tabelaprecos.add(tpdto);
			}
			
			//-----------------------------------------------------
			// Verifica em que plano financeiro o imovel est·
			// cadastrado
			//-----------------------------------------------------
			ImovelPlanoDTO imovelPlano = bo.procurarPlanoFinanceiro(daoFactory, imoveldto.id); 
			
			//-----------------------------------------------------
			// localiza as indisponibilidades de calend·rio do 
			// imÛvel
			//-----------------------------------------------------

			//-----------------------------------------------------
			// localiza as coordenadas geograficas do 
			// imÛvel
			//-----------------------------------------------------
			GeoLocalizacaoDTO geolocalizacao = bo.procurarGeoLocalizacao(daoFactory, imoveldto.id);

			//-----------------------------------------------------
			// localiza as imagens do painel de imagens do tipo TB, XG e MI
			//-----------------------------------------------------
			ImovelImagemVideoBusiness<ImovelImagemVideoDTO> iivb = new ImovelImagemVideoBusinessImpl();
			List<ImovelImagemVideoDTO> imagenstb = iivb.buscarListaImagensTB(daoFactory, imoveldto.id);
			List<ImovelImagemVideoDTO> imagensmi = iivb.buscarListaImagensMI(daoFactory, imoveldto.id);
			List<ImovelImagemVideoDTO> imagensxg = iivb.buscarListaImagensXG(daoFactory, imoveldto.id);

			//-----------------------------------------------------
			// Localiza Video do Imovel se o plano for pago
			//-----------------------------------------------------
			ImovelImagemVideoDTO videoimo = null;
			if( (imovelPlano != null) && 
				(imovelPlano.plano.id != Constantes.CODIGO_ESPECIAL_PLANO_GRATUITO) ) {
				videoimo = iivb.procurarVideoImovel(daoFactory, imoveldto.id);
			}
			
			Map<String,String> tipoPropriedade = new HashMap<String, String>();
			tipoPropriedade.put("C", "Casa");
			tipoPropriedade.put("H", "Hotel");
			tipoPropriedade.put("X", "Chacara");
			tipoPropriedade.put("F", "Flat");
			tipoPropriedade.put("Z", "Fazenda");
			tipoPropriedade.put("S", "Sitio");
			tipoPropriedade.put("L", "Chale");
			tipoPropriedade.put("P", "Pousada");
			tipoPropriedade.put("A", "Apartamento");
			imoveldto.indicadorTipoPropriedadeStr = tipoPropriedade.get(imoveldto.indicadorTipoPropriedade);
			
			//-----------------------------------------------------
			// Coloca todas as informaÁıes encontradas no dto
			// para transferir a camada cliente que solicitou a 
			// informaÁ„o.
			//-----------------------------------------------------
			dtoretorno = new ImovelFichaCompletaDTO();
			dtoretorno.imovel = imoveldto;
			dtoretorno.cliente = clientedto;
			dtoretorno.imagensImovelTB = imagenstb;
			dtoretorno.imagensImovelMI = imagensmi;
			dtoretorno.imagensImovelXG = imagensxg;
			dtoretorno.tabelaPreco = tabelaprecos;
			dtoretorno.caracteristicaCondominio = caracteristicaCondominio;
			dtoretorno.caracteristicaImovel = caracteristicaImovel;
			dtoretorno.imovelPlano = imovelPlano;
			dtoretorno.geolocalizacao = geolocalizacao;
			dtoretorno.videoImovel = videoimo;
			dtoretorno.indCentralReserva = (VariavelCache.getInstance().getValor(VariavelConstantes.CENTRAL_RESERVAS_ATIVO).equals(Constantes.ON));
			
			String fa = VariavelCache.getInstance().getValor(VariavelConstantes.URL_FICHA_IMOVEL_ATIVA);
			dtoretorno.urlFichaImovelEstatica = VariavelCache.getInstance().getValor(fa);
			/*
			dtoretorno.urlHomeMobile = VariavelCache.getInstance().getValor(VariavelConstantes.HOME_MOBILE);
			dtoretorno.urlHomeWWW = VariavelCache.getInstance().getValor(VariavelConstantes.HOME_WWW);
			dtoretorno.urlFichaImovelMobile = VariavelCache.getInstance().getValor(VariavelConstantes.URL_FICHA_IMOVEL_MOBILE);
			dtoretorno.urlFichaImovelWWW = VariavelCache.getInstance().getValor(VariavelConstantes.URL_FICHA_IMOVEL_WWW);
			*/
			// Troca tags da url
			dtoretorno.urlFichaImovelEstatica = StringUtil.replaceStringNew(dtoretorno.urlFichaImovelEstatica, "${TIPO}", imoveldto.indicadorTipoPropriedadeStr.toLowerCase());
			dtoretorno.urlFichaImovelEstatica = StringUtil.replaceStringNew(dtoretorno.urlFichaImovelEstatica, "${UF}", imoveldto.endereco.uf.toLowerCase());
			dtoretorno.urlFichaImovelEstatica = StringUtil.replaceStringNew(dtoretorno.urlFichaImovelEstatica, "${CIDADE}", String.valueOf(imoveldto.endereco.codigoUfCidadeItem));
			dtoretorno.urlFichaImovelEstatica = StringUtil.replaceStringNew(dtoretorno.urlFichaImovelEstatica, "${ID_IMOVEL}", String.valueOf(imoveldto.id));
			dtoretorno.urlFichaImovelEstatica = dtoretorno.urlFichaImovelEstatica + "/" + imoveldto.idhash + ".html";
			
			/*
			dtoretorno.urlFichaImovelWWW = StringUtil.replaceStringNew(dtoretorno.urlFichaImovelWWW, "${TIPO}", imoveldto.indicadorTipoPropriedade.toLowerCase());
			dtoretorno.urlFichaImovelWWW = StringUtil.replaceStringNew(dtoretorno.urlFichaImovelWWW, "${UF}", imoveldto.endereco.uf.toLowerCase());
			dtoretorno.urlFichaImovelWWW = StringUtil.replaceStringNew(dtoretorno.urlFichaImovelWWW, "${CIDADE}", String.valueOf(imoveldto.endereco.codigoUfCidadeItem));
			dtoretorno.urlFichaImovelWWW = StringUtil.replaceStringNew(dtoretorno.urlFichaImovelWWW, "${ID_IMOVEL}", String.valueOf(imoveldto.id));
			dtoretorno.urlFichaImovelWWW = dtoretorno.urlFichaImovelWWW + "/" + imoveldto.idhash + ".html";
			*/
			
			bo.atualizaIndicadorColaboracao(daoFactory, dtoretorno);
			
			daoFactory.commit();
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	
	/**
	 * <h2>listaImoveisPorCidadeUf</h2>
	 * <p>MÈtodo respons·vel por listar os IDs imÛveis por UF de forma paginada</p>
	 * @param
	 * @return List<ImovelFichaCompletaDTO>
	 * @throws AlugueRelaxeException
	 */
	public List<ImovelFichaCompletaDTO> listaImoveisPorCidadeUf(long idCidadeUf, int pagina, int regPagina) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lstid = null;
		List<ImovelFichaCompletaDTO> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lstid = ib.listaImoveisPorCidadeUf(daoFactory, idCidadeUf, pagina, regPagina);

			daoFactory.commit();
			
			if ((lstid != null) && (lstid.size() > 0)){
				lst = new ArrayList<ImovelFichaCompletaDTO>();
				for(Long id : lstid){
					lst.add(this.pesquisarFichaCompletaImovel(id.longValue()));
				}
			}
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return lst;
	}

	/**
	 * <h2>contarImoveisPorCidadeUf</h2>
	 * <p>MÈtodo respons·vel por contar os imÛveis por UF</p>
	 * @param String
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	public long contarImoveisPorCidadeUf(long idCidadeUf) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		long dtoretorno = 0;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dtoretorno = ib.contarImoveisPorCidadeUf(daoFactory, idCidadeUf);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	
	
	/**
	 * <h2>listaImoveisPorEstado</h2>
	 * <p>MÈtodo respons·vel por listar os imÛveis por UF de forma paginada</p>
	 * @param
	 * @return List<ImovelFichaCompletaDTO>
	 * @throws AlugueRelaxeException
	 */
	public List<ImovelFichaCompletaDTO> listaImoveisPorEstado(String uf,
			int pagina, int regPagina) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ImovelFichaCompletaDTO> dtoretorno = null;
		List<ImovelFichaCompletaDTO> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dtoretorno = ib.listaImoveisPorEstado(daoFactory, uf, pagina, regPagina);

			daoFactory.commit();
			
			if ((dtoretorno != null) && (dtoretorno.size() > 0)){
				lst = new ArrayList<ImovelFichaCompletaDTO>();
				for(ImovelFichaCompletaDTO ifcdto : dtoretorno){
					lst.add(this.pesquisarFichaCompletaImovel(ifcdto.imovel.id));
				}
			}
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return lst;
	}

	/**
	 * <h2>listarQtdeImoveisCidadeUF</h2>
	 * <p>MÈtodo respons·vel por listar as quantidades de imÛveis 
	 * por Cidade - UF</p>
	 * @param
	 * @return List<CidadeUFDTO>
	 * @throws AlugueRelaxeException
	 */
	public List<CidadeUFDTO> listarQtdeImoveisCidadeUF()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<CidadeUFDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dtoretorno = ib.listarQtdeImoveisCidadeUF(daoFactory);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	
	/* (non-Javadoc)
	 * @see br.com.jcv.backend.interfaces.services.ImovelService#entrarEmContatoComAnunciante(br.com.jcv.backend.imovel.ContatoClienteDTO)
	 */
	public ContatoClienteDTO entrarEmContatoComAnunciante(ContatoClienteDTO ccdto)
			throws AlugueRelaxeException {
		
		List<String> lstErrosEncontrados = ValidadorFactory.getInstanceContatoAnunciante().execute(ccdto);
		if ((lstErrosEncontrados != null) && (lstErrosEncontrados.size() > 0)) {
			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
					lstErrosEncontrados );
		}

		calculaHash(ccdto);
		DAOFactory daoFactory =  null;
		ContatoClienteDTO dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dto = ib.gravarContatoComAnunciante(daoFactory, ccdto);

			daoFactory.commit();
			dto.msgcode = MSGCODE.CONTATO_GRAVADO_COM_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.CONTATO_GRAVADO_COM_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		if (dto != null){
			enviarEmailVerificacaoConteudoImproprio(dto);
			
			// Enviar SMS para Administrador
			FilaSMSDTO fsmsdto = new FilaSMSDTO();
			
			Map<String,String> param = new HashMap<String, String>();
			param.put(Constantes.P1, String.valueOf(1));
			fsmsdto.celular = VariavelCache.getInstance().getValor(VariavelConstantes.CELULAR_ADMIN_GATEWAY);
			fsmsdto.msg = MensagemCache.getInstance().getMensagem(MSGCODE.CONTATO_ANUNCIANTE_PENDENTE_APROV, param);
			fsmsdto.modo = VariavelCache.getInstance().getValor(Constantes.SMS_MODO_ENVIO_GATEWAY);;
			fsmsdto.prioridade = Constantes.SMS_PRIORIDADE_ALTA;
			FilaSMSService fsms = new FilaSMSServiceImpl();
			fsms.gravarRegistro(fsmsdto);
		}
		
		//----------------------------------------------------------
		// Recupera a ficha completa do imovel
		//----------------------------------------------------------
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		dto.ifcdto = is.pesquisarFichaCompletaImovel(ccdto.idImovel);
		
		return dto;
		
	}
	
	private void enviarEmailNotificaInativacao(ImovelFichaCompletaDTO dto) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TNCAI_TAG_CODIGO_IMOVEL, String.valueOf(dto.imovel.id) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_CODIGO_CLIENTE, String.valueOf(dto.imovel.idCliente) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_QUARTOS, String.valueOf(dto.imovel.qtdeQuartos) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_SUITES, String.valueOf(dto.imovel.qtdeSuites) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_BANHEIROS, String.valueOf(dto.imovel.qtdeBanheiros) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_CAPACIDADE_MAX, String.valueOf(dto.imovel.qtdeCapacidade) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_DESC_GERAL_IMOVEL, dto.imovel.descricaoGeral );
		conteudo.put(TemplateConstantes.TNCAI_TAG_DESC_QUARTOS, dto.imovel.descricaoQuartos );
		conteudo.put(TemplateConstantes.TNCAI_TAG_DESC_ARREDORES, dto.imovel.descricaoArredores );
		conteudo.put(TemplateConstantes.TNCAI_TAG_DESC_TITULO_ANUNCIO, dto.imovel.descricaoTituloAnuncio );
		conteudo.put(TemplateConstantes.TNCAI_TAG_STATUS_IMOVEL, dto.imovel.indicadorStatus );
		conteudo.put(TemplateConstantes.TNCAI_TAG_OBSERVACOES, dto.imovel.observacoes );
		conteudo.put(TemplateConstantes.TNCAI_TAG_FLAG_MOSTRA_TAB_PRECO, dto.imovel.indicadorMostraTabelaPreco );
		conteudo.put(TemplateConstantes.TNCAI_TAG_FLAG_CONDOMINIO, dto.imovel.indicadorCondominio );
		//conteudo.put(TemplateConstantes.TNCAI_TAG_DATA_ULTIMA_VISITA, dto.imovel.dataUltimaVisita.toString() );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_VISITAS, String.valueOf(dto.imovel.qtdeVisitas) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_FLAG_PERMITE_ALUGAR_FESTA, dto.imovel.indicadorPermiteAlugarFestas );
		conteudo.put(TemplateConstantes.TNCAI_TAG_FLAG_TIPO_PROPRIEDADE, dto.imovel.indicadorTipoPropriedade );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_IMOVEL, dto.imovel.endereco.nome );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_NUMERO, dto.imovel.endereco.numero );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_BAIRRO, dto.imovel.endereco.bairro );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_CEP, dto.imovel.endereco.cep );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_CIDADE, dto.imovel.endereco.cidade );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_UF, dto.imovel.endereco.nomeuf );
		
		Email email = EmailFactory.getInstanceEnviarEmailNotificaInativacao(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(dto.cliente.email,dto.cliente.nome));

		email.enviar(lst, null, "[ALUGUERELAXE] - Cancelamento de anuncio de imovel #" + dto.imovel.id, null);
	}

	private void enviarEmailNotificacaoAlugueRelaxe(ImovelFichaCompletaDTO dto) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TNCAI_TAG_CODIGO_IMOVEL, String.valueOf(dto.imovel.id) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_CODIGO_CLIENTE, String.valueOf(dto.imovel.idCliente) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_QUARTOS, String.valueOf(dto.imovel.qtdeQuartos) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_SUITES, String.valueOf(dto.imovel.qtdeSuites) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_BANHEIROS, String.valueOf(dto.imovel.qtdeBanheiros) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_CAPACIDADE_MAX, String.valueOf(dto.imovel.qtdeCapacidade) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_DESC_GERAL_IMOVEL, dto.imovel.descricaoGeral );
		conteudo.put(TemplateConstantes.TNCAI_TAG_DESC_QUARTOS, dto.imovel.descricaoQuartos );
		conteudo.put(TemplateConstantes.TNCAI_TAG_DESC_ARREDORES, dto.imovel.descricaoArredores );
		conteudo.put(TemplateConstantes.TNCAI_TAG_DESC_TITULO_ANUNCIO, dto.imovel.descricaoTituloAnuncio );
		conteudo.put(TemplateConstantes.TNCAI_TAG_STATUS_IMOVEL, dto.imovel.indicadorStatus );
		conteudo.put(TemplateConstantes.TNCAI_TAG_OBSERVACOES, dto.imovel.observacoes );
		conteudo.put(TemplateConstantes.TNCAI_TAG_FLAG_MOSTRA_TAB_PRECO, dto.imovel.indicadorMostraTabelaPreco );
		conteudo.put(TemplateConstantes.TNCAI_TAG_FLAG_CONDOMINIO, dto.imovel.indicadorCondominio );
		//conteudo.put(TemplateConstantes.TNCAI_TAG_DATA_ULTIMA_VISITA, dto.imovel.dataUltimaVisita.toString() );
		conteudo.put(TemplateConstantes.TNCAI_TAG_QTDE_VISITAS, String.valueOf(dto.imovel.qtdeVisitas) );
		conteudo.put(TemplateConstantes.TNCAI_TAG_FLAG_PERMITE_ALUGAR_FESTA, dto.imovel.indicadorPermiteAlugarFestas );
		conteudo.put(TemplateConstantes.TNCAI_TAG_FLAG_TIPO_PROPRIEDADE, dto.imovel.indicadorTipoPropriedade );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_IMOVEL, dto.imovel.endereco.nome );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_NUMERO, dto.imovel.endereco.numero );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_BAIRRO, dto.imovel.endereco.bairro );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_CEP, dto.imovel.endereco.cep );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_CIDADE, dto.imovel.endereco.cidade );
		conteudo.put(TemplateConstantes.TNCAI_TAG_ENDERECO_UF, dto.imovel.endereco.nomeuf );
		
		Email email = EmailFactory.getInstanceEmailNotificacaoAlugueRelaxe(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE),
				"Administrador"));

		email.enviar(lst, null, "Verificar emails, url e telefones nos textos", null);
	}
	
	/**
	 * TODO Comentar aqui.
	 *
	 * @param dto
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	private void enviarEmailVerificacaoConteudoImproprio(ContatoClienteDTO dto) throws AlugueRelaxeException {
		String linkAprovar = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_LIBERAR_PARA_CONTATO);
		linkAprovar = StringUtil.replaceStringNew(linkAprovar, "${id}", dto.id);
		linkAprovar = StringUtil.replaceStringNew(linkAprovar, "${action}", Constantes.APROVAR);

		String linkReprovar = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_LIBERAR_PARA_CONTATO);
		linkReprovar = StringUtil.replaceStringNew(linkReprovar, "${id}", dto.id);
		linkReprovar = StringUtil.replaceStringNew(linkReprovar, "${action}", Constantes.REPROVAR);

		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TCAP_TAG_DATA_CONTATO, dto.dataCadastro.toString());
		conteudo.put(TemplateConstantes.TCAP_TAG_ID_IMOVEL, String.valueOf(dto.idImovel));
		conteudo.put(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO, dto.proposto);
		conteudo.put(TemplateConstantes.TCAP_TAG_ID_IMOVEL, String.valueOf(dto.idImovel));
		conteudo.put(TemplateConstantes.TCAP_TAG_NOME_PROPOSTO, dto.proposto);
		conteudo.put(TemplateConstantes.TCAP_TAG_CIDADE_PROPOSTO, dto.cidade);
		conteudo.put(TemplateConstantes.TCAP_TAG_UF_PROPOSTO, dto.uf);
		conteudo.put(TemplateConstantes.TCAP_TAG_EMAIL_PROPOSTO, dto.email);
		conteudo.put(TemplateConstantes.TCAP_TAG_DDD_PROPOSTO, dto.ddd);
		conteudo.put(TemplateConstantes.TCAP_TAG_FONE_PROPOSTO, dto.telefone);
		conteudo.put(TemplateConstantes.TCAP_TAG_CHEGADA_PREVISTA_PROPOSTO, dto.chegadaPrevista.toString());
		conteudo.put(TemplateConstantes.TCAP_TAG_PARTIDA_PREVISTA_PROPOSTO, dto.partidaPrevista.toString());
		conteudo.put(TemplateConstantes.TCAP_TAG_ADULTOS_PROPOSTO, String.valueOf(dto.qtdeAdultos));
		conteudo.put(TemplateConstantes.TCAP_TAG_CRIANCAS_PROPOSTO, String.valueOf(dto.qtdeCriancas));
		conteudo.put(TemplateConstantes.TCAP_TAG_INFO_ADICIONAL_PROPOSTO, dto.informacoesAdicionais);
		conteudo.put(TemplateConstantes.TCAP_TAG_LINK_LIBERAR_PARA_CONTATO, linkAprovar);
		conteudo.put(TemplateConstantes.TCAP_TAG_LINK_REPROVAR_PARA_CONTATO, linkReprovar);
		
		Email email = EmailFactory.getInstanceEmailContatoAnunciantePendente(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE),
				"Administrador"));

		email.enviar(lst, null, "Verificar conte˙do imprÛprio", null);
	}
	
	private void enviarEmailImovelNovoPgtoPendente(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException {
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TIPP_TAG_NOME_ANUNCIANTE_IMOVEL, ifcdto.cliente.nome);
		conteudo.put(TemplateConstantes.TIPP_TAG_TITULO_IMOVEL, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TIPP_TAG_DATA_CADASTRO_IMOVEL,ifcdto.imovel.dataCadastro.toString() );
		conteudo.put(TemplateConstantes.TIPP_TAG_NOME_PLANO_IMOVEL, ifcdto.imovelPlano.plano.nome);
		conteudo.put(TemplateConstantes.TIPP_TAG_DESCRICAO_PLANO_IMOVEL, ifcdto.imovelPlano.plano.descricao);
		conteudo.put(TemplateConstantes.TIPP_TAG_VALOR_PLANO_IMOVEL,StringUtil.valorCorreto(ifcdto.imovelPlano.plano.valor));
		//conteudo.put(TemplateConstantes.TIPP_TAG_DATA_VALIDADE_ANUNCIO_IMOVEL, "##data de validade do anuncio##");
		
		Email email = EmailFactory.getInstanceEmailImovelPendentePgto(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord( ifcdto.cliente.email, ifcdto.cliente.nome));

		email.enviar(lst, null, "ImÛvel novo incluÌdo com pendÍncia de pagamento", null);
	}

	private void calculaHash(ContatoClienteDTO ccdto) {
		StringBuilder sb = new StringBuilder();
		sb.append(ccdto.idImovel);
		sb.append(ccdto.email);
		sb.append(ccdto.proposto);
		sb.append(ccdto.ddd);
		sb.append(ccdto.telefone);
		sb.append(ccdto.cidade);
		sb.append(ccdto.uf);
		sb.append(ccdto.pais);
		sb.append(ccdto.chegadaPrevista.toString());
		sb.append(ccdto.partidaPrevista.toString());
		sb.append(ccdto.qtdeAdultos);
		sb.append(ccdto.qtdeCriancas);
		sb.append(ccdto.informacoesAdicionais);
		sb.append(String.valueOf(System.currentTimeMillis()));

		BaseFactorySecurity sha1 = BaseFactorySecurity.getInstance(0);
		try {
			ccdto.id = sha1.criptografar(sb.toString());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}

	private String calculaHash(long id) {
		String idhash = null;

		BaseFactorySecurity sha1 = BaseFactorySecurity.getInstance(0);
		try {
			idhash = sha1.criptografar(String.valueOf(id));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return idhash;
	}


	public List<ContatoClienteDTO> listarContatosAnunciantesPorStatus(String status)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ContatoClienteDTO> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lst = ib.listarContatosAnunciantesPorStatus(daoFactory, status);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}

	
	public List<ContatoClienteDTO> listarContatosAnunciantesPendentes()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ContatoClienteDTO> lst = null;
		lst = this.listarContatosAnunciantesPorStatus("P");
		for (ContatoClienteDTO ccdto : lst) {
			try {
				daoFactory = DAOFactory.getDAOFactory();
				daoFactory.open();
				daoFactory.beginTransaction();
				
				ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
				ImovelDTO imoveldto = new ImovelDTO();
				imoveldto.id = ccdto.idImovel;
				imoveldto = ib.procurar(daoFactory, imoveldto);
				
				ClienteBusiness<ClienteDTO> cb = new ClienteBusinessImpl();
				ClienteDTO clientedto = new ClienteDTO();
				clientedto.id = imoveldto.idCliente;
				clientedto = cb.procurar(daoFactory, clientedto);
				
				ccdto.anunciante = clientedto;
				
				daoFactory.commit();
				
			} catch (HibernateException he) {
				logger.error(he.getMessage(), he);
				if (daoFactory != null){
					daoFactory.rollback();
				}
				throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
						he.getMessage(),
						null);

				
			} catch (Exception e) {
				if (daoFactory != null){
					daoFactory.rollback();
				}
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
				
			} finally {
				try {
					if (daoFactory != null){
						daoFactory.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					throw new AlugueRelaxeException(e);
				}
			}
			
		}		
		return lst;
	}
	
	public List<ContatoClienteDTO> listarContatosAnunciantesLiberados() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ContatoClienteDTO> lst = null;
		lst = this.listarContatosAnunciantesPorStatus("L");

		for (ContatoClienteDTO ccdto : lst) {
			try {
				daoFactory = DAOFactory.getDAOFactory();
				daoFactory.open();
				daoFactory.beginTransaction();
				
				ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
				ImovelDTO imoveldto = new ImovelDTO();
				imoveldto.id = ccdto.idImovel;
				imoveldto = ib.procurar(daoFactory, imoveldto);
				
				ClienteBusiness<ClienteDTO> cb = new ClienteBusinessImpl();
				ClienteDTO clientedto = new ClienteDTO();
				clientedto.id = imoveldto.idCliente;
				clientedto = cb.procurar(daoFactory, clientedto);
				
				ccdto.anunciante = clientedto;
				
				daoFactory.commit();
				
			} catch (HibernateException he) {
				logger.error(he.getMessage(), he);
				if (daoFactory != null){
					daoFactory.rollback();
				}
				throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
						he.getMessage(),
						null);

				
			} catch (Exception e) {
				if (daoFactory != null){
					daoFactory.rollback();
				}
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
				
			} finally {
				try {
					if (daoFactory != null){
						daoFactory.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					throw new AlugueRelaxeException(e);
				}
			}
			
		}
		
		return lst;
	}
	
	public ImovelFichaCompletaDTO adicionarImovelCarteira(ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException {
		
		DAOFactory daoFactory =  null;
		boolean lNotificar = false;
		try {
			
			List<String> lstErrosEncontrados = ValidadorFactory.getInstanceImovelFichaCompleta().execute(ifcdto);
			if ((lstErrosEncontrados != null) && (lstErrosEncontrados.size() > 0)) {
				throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
						MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
						lstErrosEncontrados );
			}

			//----------------------------------------------------------
			// Se o plano de entrada È um plano gratuito, precisamos
			// saber quantos imoveis o cliente tem no plano gratuito?
			//----------------------------------------------------------
			if (ifcdto.imovelPlano == null){
				throw AlugueRelaxeException.getInstance(MSGCODE.INCLUIR_IMOVEL_SEM_PLANO_DEFINIDO,
						MensagemCache.getInstance().getMensagem(MSGCODE.INCLUIR_IMOVEL_SEM_PLANO_DEFINIDO),
						null);
			}
			
			if(ifcdto.imovelPlano.plano.id == Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO))){
				long idPlano = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO)); 
				long qtd = this.contarImoveisAnunciante(ifcdto.imovel.idCliente, idPlano);
				long maxPermitidoGratis = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.MAXIMO_IMOVEL_PERMITIDO_GRATIS)); 
				
				if (qtd > (maxPermitidoGratis - 1) ){
					final Map<String,String> parametros = new HashMap<String,String>();
					parametros.put(Constantes.P1, String.valueOf(maxPermitidoGratis));
					throw new AlugueRelaxeException(MSGCODE.MAXIMO_IMOVEL_PERMITIDO_ULTRAPASSADO,
							MensagemCache.getInstance().getMensagem(MSGCODE.MAXIMO_IMOVEL_PERMITIDO_ULTRAPASSADO, parametros),
							null);
				}
			}
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			//--------------------------------------------------------
			// Os passos realizados para a inclus„o do imÛvel s„o:
			// 1) Incluir o imovel
			// 2) Incluir as caracteristicas do imovel (extend) 
			// 3) Incluir as caracteristicas do condominio (extend)
			// 4) Incluir a tabela do preco do imÛvel (extend)
			// 5) Incluir o plano de adesao
			//--------------------------------------------------------
			
			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ifcdto.imovel = ib.incluir(daoFactory, ifcdto).imovel;
			
			if (ifcdto.caracteristicaImovel != null) {
				for (ImovelCaracteristicaDTO icdto : ifcdto.caracteristicaImovel) {
					icdto.imovel = ifcdto.imovel;
					ImovelCaracteristicaBusiness<ImovelCaracteristicaDTO> icb = new ImovelCaracteristicaBusinessImpl();
					icdto.indicadorCondominio = ImovelCaracteristicaBusinessImpl.INDICADOR_CARACTERISTICA_IMOVEL;
					icdto = icb.incluir(daoFactory, icdto);
				}
			}

			if (ifcdto.caracteristicaCondominio != null) {
				for (ImovelCaracteristicaDTO icdto : ifcdto.caracteristicaCondominio) {
					icdto.imovel = ifcdto.imovel;
					ImovelCaracteristicaBusiness<ImovelCaracteristicaDTO> icb = new ImovelCaracteristicaBusinessImpl();
					icdto.indicadorCondominio = ImovelCaracteristicaBusinessImpl.INDICADOR_CARACTERISTICA_CONDOMINIO;
					icdto = icb.incluir(daoFactory, icdto);
				}
			}
			
			if (ifcdto.tabelaPreco != null) {
				for (TabelaPrecoDTO tpdto : ifcdto.tabelaPreco) {
					TabelaPrecoBusiness<TabelaPrecoDTO> icb = new TabelaPrecoBusinessImpl();
					tpdto = icb.incluir(daoFactory, ifcdto.imovel.id, tpdto);
				}
			}
			
			//-----------------------------------------------------
			// Verifica se o plano de adesao gratuito est· ativo, 
			// se estiver obtem o cÛdigo da campanha
			//-----------------------------------------------------
			PlanoBusiness<PlanoDTO> pb = new PlanoBusinessImpl();
			if (VariavelCache.getInstance().getValor(VariavelConstantes.STATUS_PLANO_ADESAO_GRATUITO).equals("ON")) {
				if (ifcdto.imovelPlano == null){
					ifcdto.imovelPlano = new ImovelPlanoDTO();
					ifcdto.imovelPlano.plano = new PlanoDTO();
				}
				ifcdto.imovelPlano.plano.id = Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO));
				ifcdto.imovelPlano.plano = pb.procurar(daoFactory, ifcdto.imovelPlano.plano);
				ifcdto.imovelPlano.plano.valorDesconto = ifcdto.imovelPlano.plano.valor;
				ib.incluirAdesao(daoFactory, ifcdto.imovel.id, ifcdto.imovelPlano);
			} else {
				if (ifcdto.imovelPlano != null) {
					// Se o plano È o gratuito zera a conta
					if (ifcdto.imovelPlano.plano.id == Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO)) ){
						ifcdto.imovelPlano.plano = pb.procurar(daoFactory, ifcdto.imovelPlano.plano);
						ifcdto.imovelPlano.plano.valorDesconto = ifcdto.imovelPlano.plano.valor;
						ib.incluirAdesao(daoFactory, ifcdto.imovel.id, ifcdto.imovelPlano);
					} else {
						ifcdto.imovelPlano.plano = pb.procurar(daoFactory, ifcdto.imovelPlano.plano);
						ib.incluir(daoFactory, ifcdto.imovel.id, ifcdto.imovelPlano);
					}
				} else {
					throw AlugueRelaxeException.getInstance(MSGCODE.INCLUIR_IMOVEL_SEM_PLANO_DEFINIDO,
							MensagemCache.getInstance().getMensagem(MSGCODE.INCLUIR_IMOVEL_SEM_PLANO_DEFINIDO),
							null);
				}
				
			}

			daoFactory.commit();
			
			// Recupera ficha completa e atualizada
			ifcdto = this.pesquisarFichaCompletaImovel(ifcdto.imovel.id);
			
			//---------------------------------------------------------------------------
			// Atualiza o cache de ultimos imÛveis cadastrados
			//---------------------------------------------------------------------------
			try {
				UltimosImoveisCadastradosCache.getInstance().addNovoImovel(ifcdto.imovel.id);
			} catch (AlugueRelaxeException are) {
				are.printStackTrace();
			}
			
			//---------------------------------------------------------------------------
			// Envia um e-mail avisando das instrucoes de pgto caso plano do imovel seja
			// diferente de GRATUITO
			//---------------------------------------------------------------------------
			if(ifcdto.imovelPlano.plano.id != Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO))){
				try { 
					//ImovelFichaCompletaDTO dtoretorno = this.pesquisarFichaCompletaImovel(ifcdto.imovel.id);
					enviarEmailImovelNovoPgtoPendente(ifcdto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			final Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, ifcdto.imovel.descricaoTituloAnuncio);
			ifcdto.msgcode = MSGCODE.IMOVEL_CADASTRADO_COM_SUCESSO; 
			ifcdto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.IMOVEL_CADASTRADO_COM_SUCESSO, parametros);
			
			lNotificar = true;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);
			
		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		if (lNotificar){
			// Enviar email para AlugueRelaxe realizar um CheckList dos campos
			enviarEmailNotificacaoAlugueRelaxe(ifcdto);
		}

		return ifcdto;
	}
	
	public DTOPadrao liberarEmailContatoAnunciante(String id, String acao)
			throws AlugueRelaxeException {
		
		if ((! acao.equals(Constantes.APROVAR)) &&
			(! acao.equals(Constantes.REPROVAR))) {
			throw AlugueRelaxeException.getInstance(MSGCODE.ACAO_NAO_PERMITIDA_PARA_LIBERACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ACAO_NAO_PERMITIDA_PARA_LIBERACAO), 
					null);
		}
		
		DAOFactory daoFactory =  null;
		DTOPadrao dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			// Busca informaÁıes do contato do anunciante
			ContatoAnuncianteBusiness cab = new ContatoAnuncianteBusinessImpl();
			ContatoClienteDTO ccdto = new ContatoClienteDTO();
			ccdto.id = id;
			ccdto = cab.procurar(daoFactory, ccdto);
			daoFactory.commit();
			
			// Recupera as informacoes sobre o imovel que foi visitado
			ImovelFichaCompletaDTO ifcdto = this.pesquisarFichaCompletaImovel(ccdto.idImovel);
			
			// Obtem a ultima fatura referente ao plano deste imovel
			ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
			ImovelPlanoFaturaDTO ipfdto = ipfs.pesquisarUltimaFatura(ifcdto.imovel.id, Constantes.TIPO_PLANO_NORMAL);
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.mudaStatusContatoAnunciante(daoFactory, id, ( acao.equals(Constantes.APROVAR) ? "L" : "R" ));

			ib.mudaStatusContatoAnunciante(daoFactory, id, "E");
			ib.mudaStatusThread(daoFactory, id, Constantes.ATIVO);
			
			//----------------------------------------------------------------------------------------------------
			// - Gera uma thread aprovada para iniciar a negociacao entre o visitante e o anunciante
			// - Incrementa contador de perguntas do anunciante para estatÌstica de respostas atendidas
			//----------------------------------------------------------------------------------------------------
			if (acao.equals(Constantes.APROVAR)){
				
				ClienteBusiness<ClienteDTO> cb = new ClienteBusinessImpl();
				cb.incrementarPerguntas(daoFactory, ifcdto.cliente.id);

				ContatoAnuncianteThreadBusiness catb = new ContatoAnuncianteThreadBusinessImpl();
				catb.incluir(daoFactory, ccdto, ifcdto);
			}

			daoFactory.commit();

			//----------------------------------------------------------------------------------------------------
			// Criterio de envio do e-mail>
			// 0 - Somente envia se o gerenciador de duvidas estiver OFF
			// 1 - Se o plano for gratuito e central de reserva = ON enviado e-mail SIMPLES para anunciante
			// 2 - Se o imovel consta em plano pago, est· em dia e o prazo de vencimento do anuncio nao expirou
			//----------------------------------------------------------------------------------------------------
			if (VariavelCache.getInstance().getValor(VariavelConstantes.CHAVE_GERAL_GERENCIADOR_DUVIDAS).equals(Constantes.OFF)){
				if (ifcdto.imovelPlano.plano.id == Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO))){
					if (ifcdto.indCentralReserva) {
						cab.liberarEmailContatoAnuncianteSimples(daoFactory, id);
						cab.liberarEmailInstrucoesCaptarAutorizacao(ccdto, ifcdto);
					} else {
						cab.liberarEmailContatoAnunciante(daoFactory, id);
					}
					
				} else if ( (ipfdto.indicadorStatus.equals(Constantes.IMPF_STATUS_LIBERADO)) &&
						(System.currentTimeMillis() <= ipfdto.dataVencimento.getTime())
				) {
					cab.liberarEmailContatoAnunciante(daoFactory, id);
				}
			}

			
			dto = new DTOPadrao();			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}
	public List<ImovelFichaCompletaDTO> listarGaleriaFotosImoveis(long clienteId)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ImovelFichaCompletaDTO> lst = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			//--------------------------------------------------------
			// Obtem a galeria de imoveis do cliente
			//--------------------------------------------------------
			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ClienteDTO dto = new ClienteDTO();
			dto.id = clienteId;
			List<ImovelDTO> lstbasica = ib.buscarTodas(daoFactory, dto);
			if (lstbasica != null){
				if (lstbasica.size() > 0){
					lst = new ArrayList<ImovelFichaCompletaDTO>();
					for (ImovelDTO imoveldto : lstbasica) {

						// Abre uma ficha completa do imÛvel
						ImovelFichaCompletaDTO ifcdto = new ImovelFichaCompletaDTO();

						ifcdto.cliente = new ClienteDTO();
						ifcdto.cliente.id = clienteId;
						ifcdto.imovel = imoveldto;
						
						ImovelImagemVideoBusiness<ImovelImagemVideoDTO> iivs = new ImovelImagemVideoBusinessImpl();
						ifcdto.imagensImovelTB = iivs.buscarListaImagensTB(daoFactory, ifcdto.imovel.id);
						lst.add(ifcdto);
					}
				}
			}
			
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
			
		return lst;
	}

	public List<ImovelFichaCompletaDTO> listarGaleriaFotosImoveis(String indPatrocinadorColaborador)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ImovelFichaCompletaDTO> lst = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			//--------------------------------------------------------
			// Obtem a galeria de imoveis do tipo determinado
			//--------------------------------------------------------
			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			List<ImovelDTO> lstbasica = ib.buscarTodas(daoFactory, indPatrocinadorColaborador);
			if (lstbasica != null){
				if (lstbasica.size() > 0){
					lst = new ArrayList<ImovelFichaCompletaDTO>();
					for (ImovelDTO imoveldto : lstbasica) {

						// Abre uma ficha completa do imÛvel
						ImovelFichaCompletaDTO ifcdto = new ImovelFichaCompletaDTO();

						ifcdto.cliente = new ClienteDTO();
						ifcdto.cliente.id = imoveldto.idCliente;
						ifcdto.imovel = imoveldto;
						
						ImovelImagemVideoBusiness<ImovelImagemVideoDTO> iivs = new ImovelImagemVideoBusinessImpl();
						ifcdto.imagensImovelTB = iivs.buscarListaImagensTB(daoFactory, ifcdto.imovel.id);
						lst.add(ifcdto);
					}
				}
			}
			
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
			
		return lst;
	}

	
	public List<ImovelCaracteristicaDTO> listarCaracteristicas(long imovelId,
			String tipo) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ImovelFichaCompletaDTO atualizarImovelCarteira(ImovelFichaCompletaDTO ifcdto)
			throws AlugueRelaxeException {
		
		DAOFactory daoFactory =  null;
		boolean lNotificar = false;
		try {
			
			List<String> lstErrosEncontrados = ValidadorFactory.getInstanceImovelFichaCompleta().execute(ifcdto);
			if ((lstErrosEncontrados != null) && (lstErrosEncontrados.size() > 0)) {
				throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
						MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
						lstErrosEncontrados );
			}
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.atualizarFichaImovel(daoFactory, ifcdto);
			
			daoFactory.commit();
			
			ifcdto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ifcdto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
			// Notificar AlugueRelaxe que houve mudanÁa em algum campo da ficha do imovel
			lNotificar = true;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		// Recupera ficha completa e atualizada
		ifcdto = this.pesquisarFichaCompletaImovel(ifcdto.imovel.id);
		
		if (lNotificar){
			// Enviar email para AlugueRelaxe realizar um CheckList dos campos
			enviarEmailNotificacaoAlugueRelaxe(ifcdto);
		}
		
		return ifcdto;
	}

	public DTOPadrao atualizarTarifaImovel(ImovelFichaCompletaDTO ifcdto)
			throws AlugueRelaxeException {
		
		DAOFactory daoFactory =  null;
		DTOPadrao dto = null;
		try {
			
//			List<String> lstErrosEncontrados = ValidadorFactory.getInstanceTarifaImovel().execute(ifcdto.tarifas);
//			if ((lstErrosEncontrados != null) && (lstErrosEncontrados.size() > 0)) {
//				throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
//						MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
//						lstErrosEncontrados );
//			}
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.atualizarTarifaImovel(daoFactory, ifcdto);
			
			daoFactory.commit();
			
			dto = new DTOPadrao();			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}


	/* (non-Javadoc)
	 * @see br.com.jcv.backend.interfaces.services.ImovelService#listarUltimosImoveisCadastrados()
	 */
	public List<ImovelFichaCompletaDTO> listarUltimosImoveisCadastrados()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ImovelFichaCompletaDTO> lst = null;

		try {
			int qtdeAnuncios = Integer.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.QTDE_EXIBIR_ULTIMOS_CADASTRADOS));

			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			
			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			List<ImovelDTO> lstImovel = ib.listarUltimosImoveisCadastrados(daoFactory, qtdeAnuncios);
			daoFactory.commit();

			if (lstImovel != null){
				lst = new ArrayList<ImovelFichaCompletaDTO>();
				for (ImovelDTO dto : lstImovel) {
					lst.add(this.pesquisarFichaCompletaImovel(dto.id));
				}
			}
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
			
		return lst;
	}

	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>MÈtodo respons·vel por localizar a ficha completa do imÛvel e
	 * atualizar o contador de visitas.</p>
	 * @param codigoImovel
	 * @return ImovelFichaCompletaDTO
	 * @throws AlugueRelaxeException
	 */
	@Deprecated
	public ImovelFichaCompletaDTO apresentarFichaCompletaImovel(long codigoImovel)
			throws AlugueRelaxeException {

		ImovelFichaCompletaDTO dtoretorno = this.pesquisarFichaCompletaImovel(codigoImovel);
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();

			//-----------------------------------------------------
			// Atualiza contador de visitas na ficha do imÛvel 
			//-----------------------------------------------------
			bo.atualizaVisitasImovel(daoFactory, dtoretorno.imovel.id);
			
			daoFactory.commit();
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	
	/**
	 * <h2>apresentarFichaCompletaImovel</h2>
	 * <p>MÈtodo respons·vel por localizar a ficha completa do imÛvel e
	 * atualizar o contador de visitas por origem de acesso.</p>
	 * @param codigoImovel
	 * @return ImovelFichaCompletaDTO
	 * @throws AlugueRelaxeException
	 */
	public ImovelFichaCompletaDTO apresentarFichaCompletaImovel(long codigoImovel, String origemAcesso)
			throws AlugueRelaxeException {

		ImovelFichaCompletaDTO dtoretorno = this.pesquisarFichaCompletaImovel(codigoImovel);
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();

			//-----------------------------------------------------
			// Atualiza contador de visitas na ficha do imÛvel 
			// pela sua origem de acesso
			//-----------------------------------------------------
			bo.atualizaVisitasImovel(daoFactory, dtoretorno.imovel.id, origemAcesso);
			
			daoFactory.commit();
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	
	public DTOPadrao adicionarImagemImovel(ImovelImagemVideoDTO iivdto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			ImovelBusiness bo = new ImovelBusinessImpl();

			bo.adicionarImagemImovel(daoFactory, iivdto);
			
			daoFactory.commit();
			
			dtoretorno = new DTOPadrao();
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dtoretorno;
	}

	public long contarImoveisSorteio(String tipoSorteio) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		long dtoretorno = 0;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dtoretorno = ib.contarImoveisSorteio(daoFactory, tipoSorteio);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	
	/**
	 * <h2>contarImoveisPorEstado</h2>
	 * <p>MÈtodo respons·vel por contar os imÛveis por UF</p>
	 * @param String
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	public long contarImoveisPorEstado(String uf) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		long dtoretorno = 0;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dtoretorno = ib.contarImoveisPorEstado(daoFactory, uf);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}

	/**
	 * <h2>listaImoveisPorFiltro</h2>
	 * <p>MÈtodo respons·vel por listar os imÛveis por filtro customizado de forma paginada</p>
	 * @param Map
	 * @return List<ImovelFichaCompletaDTO>
	 * @throws AlugueRelaxeException
	 */
	public List<ImovelFichaCompletaDTO> listaImoveisPorFiltro(Map<String,String> param,
			int pagina, int regPagina) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ImovelFichaCompletaDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dtoretorno = ib.listaImoveisPorFiltro(daoFactory, param, pagina, regPagina);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	
	/**
	 * <h2>contarImoveisPorFiltro</h2>
	 * <p>MÈtodo respons·vel por contar os imÛveis por filtro customizado</p>
	 * @param Map
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	public long contarImoveisPorFiltro(Map<String,String> param) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		long dtoretorno = 0;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dtoretorno = ib.contarImoveisPorFiltro(daoFactory, param);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	public DTOPadrao atualizarGeoLocalizacaoImovel(ImovelFichaCompletaDTO ifcdto)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dto = null;
		try {
			
//			List<String> lstErrosEncontrados = ValidadorFactory.getInstanceImovelFichaCompleta().execute(ifcdto);
//			if ((lstErrosEncontrados != null) && (lstErrosEncontrados.size() > 0)) {
//				throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
//						MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
//						lstErrosEncontrados );
//			}
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.atualizarGeoLocalizacaoImovel(daoFactory, ifcdto);
			
			daoFactory.commit();
			
			dto = new DTOPadrao();			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}
	public DTOPadrao emitirNotificacaoGaleriaFotoVazia(
			ImovelFichaCompletaDTO ifcdto) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		ClienteDTO dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ClienteDTO clientedto = new ClienteDTO();
			clientedto.id = ifcdto.imovel.idCliente;
			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			dtoretorno = ib.procurar(daoFactory, clientedto);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		if (dtoretorno != null){
			Map<String,String> conteudo = new HashMap<String, String>();
			conteudo.put(TemplateConstantes.TNGFV_TAG_CLIENTE, dtoretorno.nome);
			conteudo.put(TemplateConstantes.TNGFV_TAG_ID_CLIENTE,String.valueOf(ifcdto.imovel.id));
			conteudo.put(TemplateConstantes.TNGFV_TAG_TITULO_IMOVEL, ifcdto.imovel.descricaoTituloAnuncio);
			
			Email email = EmailFactory.getInstanceEmailNotificacaoGaleriaVazia(conteudo);
			
			ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
			lst.add(new EmailRecord(dtoretorno.email, dtoretorno.nome));
			
			email.enviar(lst, null, "Aviso de Galeria Vazia imÛvel #" + String.valueOf(ifcdto.imovel.id), null);
			
		}

		DTOPadrao dto = new DTOPadrao();
		dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		return dto;
		
	}
	public DTOPadrao enviarIndicacaoImovelAmigo(ImovelFichaCompletaDTO ifcdto,
			IndicarAmigoDTO iadto) throws AlugueRelaxeException {

		String linkIndicarAmigo = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_INDICAR_AMIGO);
		linkIndicarAmigo = StringUtil.replaceStringNew(linkIndicarAmigo, "${id}", String.valueOf(ifcdto.imovel.id));
		
		Map<String,String> conteudo = new HashMap<String, String>();
		conteudo.put(TemplateConstantes.TIIA_TAG_NOME_AMIGO, iadto.nomeamigo);
		conteudo.put(TemplateConstantes.TIIA_TAG_SEU_AMIGO, iadto.seunome);
		conteudo.put(TemplateConstantes.TIIA_TAG_ID_IMOVEL,String.valueOf(ifcdto.imovel.id));
		conteudo.put(TemplateConstantes.TIIA_TAG_TITULO_IMOVEL, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TIIA_TAG_LINK_DO_IMOVEL, linkIndicarAmigo);
		conteudo.put(TemplateConstantes.TIIA_TAG_MENSAGEM, iadto.mensagem);

		
		Email email = EmailFactory.getInstanceEmailIndicarImovelAmigo(conteudo);
		
		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(iadto.emailamigo, iadto.nomeamigo));
		
		email.enviar(lst, null, "Um amigo indicou ImÛvel de Temporada pra vocÍ ", null);
			
		DTOPadrao dto = new DTOPadrao();
		dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
		dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
		return dto;
		
	}
	
	/**
	 * <h2>listaImoveisPorCidade</h2>
	 * <p>MÈtodo respons·vel por listar os imÛveis por cidade de forma paginada</p>
	 * @param
	 * @return List<ImovelFichaCompletaDTO>
	 * @throws AlugueRelaxeException
	 */
	public List<ImovelFichaCompletaDTO> listaImoveisPorCidade(int cidade,
			int pagina, int regPagina) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ImovelFichaCompletaDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dtoretorno = ib.listaImoveisPorCidade(daoFactory, cidade, pagina, regPagina);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	
	/**
	 * <h2>contarImoveisPorCidade</h2>
	 * <p>MÈtodo respons·vel por contar os imÛveis por UF</p>
	 * @param String
	 * @return long
	 * @throws AlugueRelaxeException
	 */
	public long contarImoveisPorCidade(int cidade) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		long dtoretorno = 0;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			dtoretorno = ib.contarImoveisPorCidade(daoFactory, cidade);

			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	
	public DTOPadrao enviarImagensLixeira(long idImovel,
			List<ImovelImagemVideoDTO> lst) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dto = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.enviarImagensLixeira(daoFactory, idImovel, lst);
			
			daoFactory.commit();
			
			dto = new DTOPadrao();			
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}
	public String charterDistribuicaoVisitasImovel(
			ImovelFichaCompletaDTO ifcdto, int ano)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		String url = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelBusiness bo = new ImovelBusinessImpl();
			url = bo.charterDistribuicaoVisitasImovel(daoFactory, ifcdto, ano);
			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				throw new AlugueRelaxeException(e);
			}
		}
		return url;
	}
	
	public Date obterDataInicioPublicidadePP() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		Date d = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			d = ib.obterMaiorDataPublicidade(daoFactory, "PP");
			if (d == null){
				d = new Date();
			}
			
			DateManagerBase dt = DateManager.getDateManagerInstance(d);
			d = dt.add(1);
			
			daoFactory.commit();
			
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return d;
	}

	public Date obterDataInicioPublicidadePD() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		Date d = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			d = ib.obterMaiorDataPublicidade(daoFactory, "PD");
			if (d == null){
				d = new Date();
			}
			
			DateManagerBase dt = DateManager.getDateManagerInstance(d);
			d = dt.add(1);
			
			daoFactory.commit();
			
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return d;
	}

	public List<Long> listarImoveisSorteados() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lst = ib.listarImoveisParaSorteio(daoFactory);
			
			daoFactory.commit();
			
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}

	public List<Long> listarImoveis(String status) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lst = ib.listarImoveis(daoFactory, status);
			
			daoFactory.commit();
			
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}


	public List<Long> listarImoveisSorteadosPD() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lst = ib.listarImoveisParaSorteioPD(daoFactory);
			
			daoFactory.commit();
			
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}

	@Deprecated
	public PublicidadeImovelDTO criarPublicidade(PublicidadeImovelDTO pub)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		PublicidadeImovelDTO pubCriado = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.criarPublicidade(daoFactory, pub);
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return pubCriado;	
	}
	
	public PublicidadeImovelDTO criarPublicidade(PublicidadeImovelDTO pub, long idPlano)
			throws AlugueRelaxeException {
		
		return criarPublicidade(pub, idPlano, false);
	}
	
	public PublicidadeImovelDTO criarPublicidade(PublicidadeImovelDTO pub, long idPlano, boolean aplicarDesconto) throws AlugueRelaxeException {
		
		ImovelFichaCompletaDTO ifcdto = this.pesquisarFichaCompletaImovel(pub.fichaImovel.imovel.id);
		if(! ifcdto.imovel.indicadorStatus.equals(ImovelBusiness.STATUS_ATIVO)){
			final Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, ifcdto.imovel.descricaoTituloAnuncio);
			throw new AlugueRelaxeException(MSGCODE.COMPRA_PUBLICIDADE_NEGADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.COMPRA_PUBLICIDADE_NEGADA, parametros),
					null);
		}
	
		DAOFactory daoFactory =  null;
		PublicidadeImovelDTO pubCriado = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			pubCriado = ib.criarPublicidade(daoFactory, pub, idPlano, aplicarDesconto);
			
			daoFactory.commit();
			final Map<String,String> param = new HashMap<String, String>();
			param.put(Constantes.P1, pubCriado.plano.nome);
			param.put(Constantes.P2, pubCriado.plano.descricao);
			param.put(Constantes.P3, ifcdto.imovel.descricaoTituloAnuncio);
			pubCriado.msgcode = MSGCODE.PLANO_PUBLICIDADE_COMPRADO_COM_SUCESSO;
			pubCriado.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.PLANO_PUBLICIDADE_COMPRADO_COM_SUCESSO, param);

			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		if (pubCriado != null){
			pubCriado.fichaImovel = this.pesquisarFichaCompletaImovel(pubCriado.fichaImovel.imovel.id);
		}
		
		return pubCriado;	
	}
	
	public void liberarPublicidade(PublicidadeImovelDTO idPublicidade)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.liberarPublicidade(daoFactory, idPublicidade);
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
	}

	
	public void liberarPublicidade(long idFatura)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.liberarPublicidade(daoFactory, idFatura);
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
	}
	
	public void atualizarStatusSorteio(ImovelFichaCompletaDTO ifcdto, String novoStatus)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.atualizarStatusSorteio(daoFactory, ifcdto, novoStatus);
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
	}

	
	public void atualizarStatusSorteioPD(ImovelFichaCompletaDTO ifcdto, String novoStatus)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.atualizarStatusSorteioPD(daoFactory, ifcdto, novoStatus);
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
	}
	
	/**
	 * <h2>avaliarAnuncio</h2>
	 * <p>Avisa ao anunciante sobre uma avaliacao do seu imovel</p>
	 * @throws AlugueRelaxeException
	 */	
	public DTOPadrao avaliarAnuncio(AvaliacaoAnuncioImovelDTO avaliacao) 
		throws AlugueRelaxeException {
		
		DTOPadrao dto = null;

		try {
			/*
			if (avaliacao == null) {
				throw new AlugueRelaxeException(...);
			}
			if (avaliacao.classFotografia == null) {
				throw new AlugueRelaxeException(...);
			}
			if (avaliacao.classQualidadeTexto == null) {
				throw new AlugueRelaxeException(...);
			}
			if (avaliacao.classInformacaoRelevante == null) {
				throw new AlugueRelaxeException(...);
			}
			*/
		
			// Pesquisa a ficha do imovel avaliado
			ImovelFichaCompletaDTO ifcdto = this.pesquisarFichaCompletaImovel(avaliacao.idImovelAvaliado);
			if (ifcdto != null) {
				
				Map<String,String> conteudo = new HashMap<String, String>();
				conteudo.put(TemplateConstantes.TAIA_TAG_NOME_CLIENTE,String.valueOf(ifcdto.cliente.nome));
				conteudo.put(TemplateConstantes.TAIA_TAG_ID_IMOVEL, String.valueOf(ifcdto.imovel.id));
				conteudo.put(TemplateConstantes.TAIA_TAG_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
				conteudo.put(TemplateConstantes.TAIA_TAG_TIT_AVAL_FOTOGRAFIA, avaliacao.classFotografia.titulo);
				conteudo.put(TemplateConstantes.TAIA_TAG_NOTA_AVAL_FOTOGRAFIA, String.valueOf(avaliacao.classFotografia.notaClassificador));
				conteudo.put(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_FOTOGRAFIA, avaliacao.classFotografia.comentarioClassificador);
				conteudo.put(TemplateConstantes.TAIA_TAG_TIT_AVAL_QUALIDADE_TEXTO,avaliacao.classQualidadeTexto.titulo);
				conteudo.put(TemplateConstantes.TAIA_TAG_NOTA_AVAL_QUALIDADE_TEXTO,String.valueOf(avaliacao.classQualidadeTexto.notaClassificador));
				conteudo.put(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_QUALIDADE_TEXTO,avaliacao.classQualidadeTexto.comentarioClassificador);
				conteudo.put(TemplateConstantes.TAIA_TAG_TIT_AVAL_INFORMACAO_RELEVANTE,avaliacao.classInformacaoRelevante.titulo);
				conteudo.put(TemplateConstantes.TAIA_TAG_NOTA_AVAL_INFORMACAO_RELEVANTE,String.valueOf(avaliacao.classInformacaoRelevante.notaClassificador));
				conteudo.put(TemplateConstantes.TAIA_TAG_TEXTO_AVAL_INFORMACAO_RELEVANTE,avaliacao.classInformacaoRelevante.comentarioClassificador);
				

				// Pede uma factory e envia email para o anunciante e outro para o AR
				Email email = EmailFactory.getInstanceEmailAvaliacaoAnuncio(conteudo);
				
				ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
				lst.add(new EmailRecord(ifcdto.cliente.email, ifcdto.cliente.nome));
				lst.add(new EmailRecord(VariavelCache.getInstance().getValor(VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE),
				"Administrador"));
				
				email.enviar(lst, null, "ImÛvel #" + String.valueOf(ifcdto.imovel.id) + " foi avaliado", null);
					
				dto = new DTOPadrao();
				dto.msgcode = MSGCODE.IMOVEL_AVALIACAO_ENVIADA;
				dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.IMOVEL_AVALIACAO_ENVIADA);
			} else {
				
				final Map<String,String> parametros = new HashMap<String,String>();
				parametros.put(Constantes.P1, String.valueOf(avaliacao.idImovelAvaliado));
				throw new AlugueRelaxeException(MSGCODE.IMOVEL_AVALIACAO_NAO_ENCONTRADO,
						 MensagemCache.getInstance().getMensagem(MSGCODE.IMOVEL_AVALIACAO_NAO_ENCONTRADO, parametros),
						null);
			}
		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
		}
		
		return dto;
		
	}
	
	private long contarImoveisAnunciante(long idCliente, long idPlano) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<ContatoClienteDTO> lst = null;
		long qtd = 0;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			ClienteBusiness<ClienteDTO> ib = new ClienteBusinessImpl();
			qtd = ib.contarImoveisAnunciante(daoFactory, idCliente, idPlano);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return qtd;
		
	}
	

	public List<Long> listarImoveisParaNegativacao()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lst = ib.listarImoveisParaNegativacao(daoFactory);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException are) {
			throw are;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}
	
	
	public void negativarImovel(long idImovel, double pesoNegativacao)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();
			bo.negativarImovel(daoFactory, idImovel, pesoNegativacao);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);
		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
	}

	public DTOPadrao inativarAnuncio(long idImovel) 
			throws AlugueRelaxeException {
		
		// Verifica se o imovel ja encontra-se inativo
		ImovelFichaCompletaDTO ifcdto = this.pesquisarFichaCompletaImovel(idImovel);
		if (ifcdto == null){
				final Map<String,String> parametros = new HashMap<String,String>();
				parametros.put(Constantes.P1, String.valueOf(idImovel));
				throw new AlugueRelaxeException(MSGCODE.IMOVEL_INATIVACAO_NAO_ENCONTRADO,
						 MensagemCache.getInstance().getMensagem(MSGCODE.IMOVEL_INATIVACAO_NAO_ENCONTRADO, parametros),
						null);
		} else {
			if (ifcdto.imovel.indicadorStatus.equals(Constantes.IMOVEL_STATUS_INATIVO)) {
				final Map<String,String> parametros = new HashMap<String,String>();
				parametros.put(Constantes.P1, ifcdto.imovel.descricaoTituloAnuncio);
				throw new AlugueRelaxeException(MSGCODE.IMOVEL_JA_ESTA_INATIVO,
						 MensagemCache.getInstance().getMensagem(MSGCODE.IMOVEL_JA_ESTA_INATIVO, parametros),
						null);
			}
		}
		
		// Atualiza o status do imovel e envia notificacao ao anunciante
		DTOPadrao dto = this.atualizarStatusImovel(idImovel, Constantes.IMOVEL_STATUS_INATIVO);
		
		final Map<String,String> parametros = new HashMap<String,String>();
		parametros.put(Constantes.P1, ifcdto.imovel.descricaoTituloAnuncio);
		dto.msgcode = MSGCODE.IMOVEL_INATIVADO_COM_SUCESSO;
		dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.IMOVEL_INATIVADO_COM_SUCESSO, parametros);
		enviarEmailNotificaInativacao(ifcdto);
		
		return dto;
	}

	public DTOPadrao atualizarStatusImovel(long codImovel, String novoStatus)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();
			bo.atualizarStatusImovel(daoFactory, codImovel, novoStatus);
			daoFactory.commit();
			
			dto = new DTOPadrao();
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}

	public List<Long> listarImoveisPatrocinadores()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lst = ib.listarImoveisPatrocinadores(daoFactory);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException are) {
			throw are;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}

	public List<Long> listarImoveisFuraFila()
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lst = ib.listarImoveisFuraFila(daoFactory);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException are) {
			throw are;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}
	

	public DTOPadrao atualizaSaldoFuraFila(long idImovel, double vlr)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.atualizaSaldoFuraFila(daoFactory, idImovel, vlr);
			daoFactory.commit();
			
			dto = new DTOPadrao();
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (AlugueRelaxeException are) {
			throw are;
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}
	/**
	 * <h2>listPublicidadeDentroPrazo</h2>
	 * @param String
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	public List<PublicidadeImovelDTO> listPublicidadeDentroPrazo(String tipo) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<PublicidadeImovelDTO> dtoretorno = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPublicidadeBusiness<ImovelPublicidadeDTO> bo = new ImovelPublicidadeBusinessImpl();
			dtoretorno = bo.listPublicidadeDentroPrazo(daoFactory, tipo);
			daoFactory.commit();
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		return dtoretorno;
	}
	

	public DTOPadrao atualizarStatusImovelColaborador(long codImovel, String novoStatus, double valor)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();
			bo.atualizarStatusImovelColaborador(daoFactory, codImovel, novoStatus, valor);
			daoFactory.commit();
			
			dto = new DTOPadrao();
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}

	public List<Long> listarColaboradorInconsistente() throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lst = ib.listarColaboradorInconsistente(daoFactory);
			
			daoFactory.commit();
			
			
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;
	}
	

	public DTOPadrao normalizarImovelColaborador(long codImovel) 
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dto = null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();
			bo.atualizarStatusImovelColaborador(daoFactory, codImovel, "N", -1);
			daoFactory.commit();
			
			dto = new DTOPadrao();
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return dto;
	}
	public List<Long> listarIDImoveis(String uf, String cidade)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		try {
			
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			lst = ib.listarIDImoveis(daoFactory, uf, cidade);
			
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);

		} catch (AlugueRelaxeException are) {
			throw are;

		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return lst;

	}
	public long ImovelIDProximoOferecimento(String uf, String cidade)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		long id = -1;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();
			id = bo.ImovelIDProximoOferecimento(daoFactory, uf, cidade);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
		
		return id;
	}

	public void atualizarOferecimento(long id) throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();
			bo.atualizarOferecimento(daoFactory, id);
			daoFactory.commit();
			
		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			if (daoFactory != null){
				daoFactory.rollback();
			}
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(),
					null);


		} catch (Exception e) {
			if (daoFactory != null){
				daoFactory.rollback();
			}
			logger.error(e.getMessage(), e);
			throw new AlugueRelaxeException(e);
			
		} finally {
			try {
				if (daoFactory != null){
					daoFactory.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}
	}
	public PublicidadeImovelDTO prepararRealizarPagamento(long idImovel)
			throws AlugueRelaxeException {
		PublicidadeImovelDTO pidto = new PublicidadeImovelDTO();
		pidto.fichaImovel = this.pesquisarFichaCompletaImovel(idImovel);
		
		ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> ipfs = new ImovelPlanoFaturaServiceImpl();
		pidto.fatura = ipfs.pesquisarUltimaFatura(idImovel, Constantes.TIPO_PLANO_NORMAL);
		
		PlanoService<PlanoDTO> ps = new PlanoServicesImpl();
		pidto.plano = ps.pesquisarRegistro(pidto.fichaImovel.imovelPlano.plano.id);
		
		return pidto;
	}
		
}
