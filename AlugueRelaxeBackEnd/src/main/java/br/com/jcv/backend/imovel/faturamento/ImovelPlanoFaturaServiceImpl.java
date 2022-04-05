package br.com.jcv.backend.imovel.faturamento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ImovelBusinessImpl;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.plano.ImovelPlanoRelacaoDTO;
import br.com.jcv.backend.imovel.plano.ImovelPlanoServiceImpl;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.interfaces.business.ImovelBusiness;
import br.com.jcv.backend.interfaces.business.ImovelPlanoFaturaBusiness;
import br.com.jcv.backend.interfaces.services.ImovelPlanoFaturaService;
import br.com.jcv.backend.interfaces.services.ImovelPlanoService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.interfaces.services.PlanoService;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.plano.CanecaPlanoRecursos;
import br.com.jcv.backend.plano.FuraFilaPlanoRecursos;
import br.com.jcv.backend.plano.PaginaDestaquePlanoRecursos;
import br.com.jcv.backend.plano.PainelPatrocinadorPlanoRecursos;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.plano.PlanoRecursos;
import br.com.jcv.backend.plano.PlanoServicesImpl;
import br.com.jcv.backend.plano.PrimeiraPaginaPlanoRecursos;
import br.com.jcv.backend.plano.SuperBannerPlanoRecursos;
import br.com.jcv.backend.plano.VideoImovelPlanoRecursos;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;


/*
*
* Copyright (c) 2009-2012, Julio Cesar Vitorino, Todos os direitos reservados.
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


/**
 * <h1>ImovelPlanoFaturaServiceImpl</h1> 
 * <p>Classe de serviços para ImovelPlanoFaturaService</p>
 * <p>Esta classe tem por objetivo invocar os Métodos de negócio nos respectivos Business Object apontados pela interface <code>BusinessObject</code>.
 * </p>
 * <p>Esta é a camada de comunicação que o cliente usa para ter acesso ao backend de negócios, podem ser:</p>
 * <ul>
 *    <li>Chamada RPC via servlet</li>
 *    <li>JUnit</li>
 * </ul>
 * <p> Todo Método de <code>ApplicationService</code> que invocar uma chamada a Implementação de <code>BusinessObject</code> envolvendo acesso a dados, deve quando necessério
 * abrir um contexto de transação, tal como apresentando no código abaixo:
 * <pre>		
 * 		try {
 *			daoFactory = DAOFactory.getDAOFactory();
 *			daoFactory.open();
 *			daoFactory.beginTransaction();
 *			
 *			BusinessObject<ImovelPlanoFaturaDTO> bo = new UFBusiness();
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
 * <br>O ideal é que toda Método de <code>ApplicationService</code> ao seu final devolva encapsulada uma mensagem de retorno
 * para que o cliente que invocou o serviéo tenha o retorno da operação e decida fazer o que for melhor para seu processo.
 * </p>
 * @author Júlio Vitorino
 * @version 1.10
 * @since 07 May 2009
 */
public class ImovelPlanoFaturaServiceImpl implements ImovelPlanoFaturaService<ImovelPlanoFaturaDTO> {

	/**
	 * <h2>Atributo logger</h2>
	 * <p>Usado para obter uma instância de <code>Logger</code>
	 * para a classe UFServices.</p>
	 */
	private static Logger logger = Logger.getLogger(ImovelPlanoFaturaServiceImpl.class); 

	public void notificaPlanoAnuncioBloqueado(ImovelFichaCompletaDTO ifcdto, ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException  {
		Map<String, String> conteudo = new HashMap<String,String>();
		conteudo.put(TemplateConstantes.TAGC_NOME_CLIENTE, ifcdto.cliente.nome);
		conteudo.put(TemplateConstantes.TAGC_ID_IMOVEL, String.valueOf(ifcdto.imovel.id));
		conteudo.put(TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_CD_IMOVEL_PLANO_FATURA,  String.valueOf(ipfdto.id));
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_PLANO, ipfdto.plano.nome);
		conteudo.put(TemplateConstantes.TAGC_VALOR_PLANO, StringUtil.valorCorreto(ipfdto.valorFatura));
		conteudo.put(TemplateConstantes.TAGC_TELEFONES_CENTRAL_RESERVA, VariavelCache.getInstance().getValor(VariavelConstantes.TELEFONES_CENTRAL_RESERVA));
		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VENCIMENTO_ANUNCIO, DateManagerBase.getDateManagerInstance(ipfdto.dataVencimento).getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TAGC_DATA_CADASTRO, DateManagerBase.getDateManagerInstance(ipfdto.dataCadastro).getDateTimeFull());
			conteudo.put(TemplateConstantes.TAGC_DATA_PAGAMENTO, DateManagerBase.getDateManagerInstance(ipfdto.dataPagamento).getDateTimeFull());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<EmailRecord> lstDestinatarios = new ArrayList<EmailRecord>();
		lstDestinatarios.add(new EmailRecord(ifcdto.cliente.email, ifcdto.cliente.nome));
		
		Email email = EmailFactory.getInstanceEmailNotificaPlanoBloqueado(conteudo);
		email.enviar(lstDestinatarios, null, "AlugueRelaxe - Anuncio imovel #" + ifcdto.imovel.id + " bloqueado", null);

	}
	
	public void notificaPlanoAnuncioRenovado(ImovelFichaCompletaDTO ifcdto, ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException  {
		Map<String, String> conteudo = new HashMap<String,String>();
		conteudo.put(TemplateConstantes.TAGC_NOME_CLIENTE, ifcdto.cliente.nome);
		conteudo.put(TemplateConstantes.TAGC_ID_IMOVEL, String.valueOf(ifcdto.imovel.id));
		conteudo.put(TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_CD_IMOVEL_PLANO_FATURA,  String.valueOf(ipfdto.id));
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_PLANO, ipfdto.plano.nome);
		conteudo.put(TemplateConstantes.TAGC_VALOR_PLANO, StringUtil.valorCorreto(ipfdto.valorFatura));
		conteudo.put(TemplateConstantes.TAGC_TELEFONES_CENTRAL_RESERVA, VariavelCache.getInstance().getValor(VariavelConstantes.TELEFONES_CENTRAL_RESERVA));
		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VENCIMENTO_ANUNCIO, DateManagerBase.getDateManagerInstance(ipfdto.dataVencimento).getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TAGC_DATA_CADASTRO, DateManagerBase.getDateManagerInstance(ipfdto.dataCadastro).getDateTimeFull());
			conteudo.put(TemplateConstantes.TAGC_DATA_PAGAMENTO, DateManagerBase.getDateManagerInstance(ipfdto.dataPagamento).getDateTimeFull());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<EmailRecord> lstDestinatarios = new ArrayList<EmailRecord>();
		lstDestinatarios.add(new EmailRecord(ifcdto.cliente.email, ifcdto.cliente.nome));
		
		Email email = EmailFactory.getInstanceEmailNotificaPlanoRenovado(conteudo);
		email.enviar(lstDestinatarios, null, "AlugueRelaxe - Anuncio imovel #" + ifcdto.imovel.id + " renovado e liberado", null);

	}
	
	public void notificaPlanoAnuncioRenovadoPendente(ImovelFichaCompletaDTO ifcdto, ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException  {
		Map<String, String> conteudo = new HashMap<String,String>();
		conteudo.put(TemplateConstantes.TAGC_NOME_CLIENTE, ifcdto.cliente.nome);
		conteudo.put(TemplateConstantes.TAGC_ID_IMOVEL, String.valueOf(ifcdto.imovel.id));
		conteudo.put(TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_CD_IMOVEL_PLANO_FATURA,  String.valueOf(ipfdto.id));
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_PLANO, ipfdto.plano.nome);
		conteudo.put(TemplateConstantes.TAGC_VALOR_PLANO, StringUtil.valorCorreto(ipfdto.valorFatura));
		conteudo.put(TemplateConstantes.TAGC_TELEFONES_CENTRAL_RESERVA, VariavelCache.getInstance().getValor(VariavelConstantes.TELEFONES_CENTRAL_RESERVA));
		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VENCIMENTO_ANUNCIO, DateManagerBase.getDateManagerInstance(ipfdto.dataVencimento).getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TAGC_DATA_CADASTRO, DateManagerBase.getDateManagerInstance(ipfdto.dataCadastro).getDateTimeFull());
			//conteudo.put(TemplateConstantes.TAGC_DATA_PAGAMENTO, DateManagerBase.getDateManagerInstance(ipfdto.dataPagamento).getDateTimeFull());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<EmailRecord> lstDestinatarios = new ArrayList<EmailRecord>();
		lstDestinatarios.add(new EmailRecord(ifcdto.cliente.email, ifcdto.cliente.nome));
		
		Email email = EmailFactory.getInstanceEmailNotificaPlanoPendente(conteudo);
		email.enviar(lstDestinatarios, null, "AlugueRelaxe - Anuncio imovel #" + ifcdto.imovel.id + " renovado e aguardando pagamento", null);

	}
	

	public void notificaVencimentoAnuncio(ImovelFichaCompletaDTO ifcdto, ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException  {
		Map<String, String> conteudo = new HashMap<String,String>();
		conteudo.put(TemplateConstantes.TAGC_NOME_CLIENTE, ifcdto.cliente.nome);
		conteudo.put(TemplateConstantes.TAGC_ID_IMOVEL, String.valueOf(ifcdto.imovel.id));
		conteudo.put(TemplateConstantes.TAGC_TITULO_ANUNCIO, ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAGC_CD_IMOVEL_PLANO_FATURA, String.valueOf(ipfdto.id));
		conteudo.put(TemplateConstantes.TAGC_DESCRICAO_PLANO, ipfdto.plano.nome);
		conteudo.put(TemplateConstantes.TAGC_VALOR_PLANO, StringUtil.valorCorreto(ipfdto.valorFatura));
		conteudo.put(TemplateConstantes.TAGC_TELEFONES_CENTRAL_RESERVA, VariavelCache.getInstance().getValor(VariavelConstantes.TELEFONES_CENTRAL_RESERVA));
		try {
			conteudo.put(TemplateConstantes.TAGC_DATA_VENCIMENTO_ANUNCIO, DateManagerBase.getDateManagerInstance(ipfdto.dataVencimento).getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TAGC_DATA_CADASTRO, DateManagerBase.getDateManagerInstance(ipfdto.dataCadastro).getDateTimeFull());
			conteudo.put(TemplateConstantes.TAGC_DATA_PAGAMENTO, DateManagerBase.getDateManagerInstance(ipfdto.dataPagamento).getDateTimeFull());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<EmailRecord> lstDestinatarios = new ArrayList<EmailRecord>();
		lstDestinatarios.add(new EmailRecord(ifcdto.cliente.email, ifcdto.cliente.nome));
		
		Email email = EmailFactory.getInstanceEmailNotificacaoAnunciosAVencer(conteudo);
		email.enviar(lstDestinatarios, null, "AlugueRelaxe - Vencimento anuncio imovel #" + ifcdto.imovel.id, null);

	}
	
	public ImovelPlanoFaturaDTO registrarPagtoFatura(long idImovel) throws AlugueRelaxeException{

		DAOFactory daoFactory =  null;
		
		// Busca dados da ultima fatura do plano do imovel
		ImovelPlanoFaturaDTO ipfdto = this.pesquisarUltimaFatura(idImovel, "N");
		
		// Busca informacoes da ficha do imovel
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(idImovel);
		
		// Verifica se anuncio esta pendente e vencido
		this.isAnuncioPendenteVencido(ipfdto);

		// Verifica se anuncio liberado esta vencido
		this.isAnuncioLiberadoVencido(ipfdto);

		// Verifica se o plano e gratuito
		this.isPlanoGratuito(ipfdto);
		
		// Verifica se a ultima fatura ja encontra-se paga
		this.isPlanoPago(ipfdto);
			
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			//------------------------------------------------------------------------
			// - Registra o pagamento da fatura
			// - Ativa circulacao do anuncio do imovel 
			//------------------------------------------------------------------------
			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.pagarFatura(daoFactory, ipfdto.id);
			ib.atualizarStatusImovel(daoFactory, idImovel, ImovelBusiness.STATUS_ATIVO);
			daoFactory.commit();
			
			//------------------------------------------------------------------------
			// Notifica cliente sobre o registro de pagamento e liberacao do anuncio 
			//------------------------------------------------------------------------
			this.notificaPlanoAnuncioRenovado(ifcdto, ipfdto); 
			
			ipfdto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ipfdto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return ipfdto;
		
	}
	
	public ImovelPlanoFaturaDTO registrarPagtoPlano(long idFatura) throws AlugueRelaxeException{

		DAOFactory daoFactory =  null;
		
		// Busca dados da ultima fatura do plano do imovel
		ImovelPlanoFaturaDTO ipfdto = this.pesquisarFatura(idFatura);
		
		// Busca informacoes do plano para uso na matriz de recurso
		PlanoService<PlanoDTO> ps = new PlanoServicesImpl();
		PlanoDTO planodto = ps.pesquisarRegistro(ipfdto.plano);

		// Localiza o imovel da fatura
		ImovelPlanoService<ImovelPlanoRelacaoDTO> ips = new ImovelPlanoServiceImpl();
		
		ImovelPlanoRelacaoDTO ipsdto = new ImovelPlanoRelacaoDTO();
		ipsdto.id = ipfdto.idImovelPlano;
		ipsdto = ips.pesquisarRegistro(ipsdto);
		
		// Busca informacoes da ficha do imovel
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(ipsdto.idImovel);
		
		// Verifica se anuncio esta pendente e vencido
		this.isAnuncioPendenteVencido(ipfdto);

		// Verifica se anuncio liberado esta vencido
		this.isAnuncioLiberadoVencido(ipfdto);

		// Verifica se o plano e gratuito
		this.isPlanoGratuito(ipfdto);
		
		// Verifica se a ultima fatura ja encontra-se paga
		this.isPlanoPago(ipfdto);
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			//------------------------------------------------------------------------
			// - Registra o pagamento da fatura
			// - Ativa circulacao do anuncio do imovel 
			//------------------------------------------------------------------------
			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.pagarFatura(daoFactory, ipfdto.id);
			ib.atualizarStatusImovel(daoFactory, ipsdto.idImovel, ImovelBusiness.STATUS_ATIVO);
			
			PublicidadeImovelDTO pidto = new PublicidadeImovelDTO();
			pidto.fatura = ipfdto;
			pidto.fichaImovel = ifcdto;
			pidto.plano = planodto;
			
			executeMatrizPlano(daoFactory, pidto);
			
			daoFactory.commit();
			
			// Busca dados da ultima fatura do plano do imovel
			ipfdto = this.pesquisarFatura(idFatura);			
			
			//------------------------------------------------------------------------
			// Notifica cliente sobre o registro de pagamento e liberacao do anuncio 
			//------------------------------------------------------------------------
			this.notificaPlanoAnuncioRenovado(ifcdto, ipfdto); 
			
			ipfdto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ipfdto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return ipfdto;
		
	}

	private void executeMatrizPlano(DAOFactory daoFactory,
			PublicidadeImovelDTO pidto) {
		// Executa a matriz de recursos habilitados para o plano
		PlanoRecursos pr = new PlanoRecursos();
		pr.setContextDAO(daoFactory);
		pr.setContext(pidto);
		pr.addChain(new SuperBannerPlanoRecursos());
		pr.addChain(new CanecaPlanoRecursos());
		pr.addChain(new VideoImovelPlanoRecursos());
		pr.addChain(new PrimeiraPaginaPlanoRecursos());
		pr.addChain(new PaginaDestaquePlanoRecursos());
		pr.addChain(new PainelPatrocinadorPlanoRecursos());
		pr.addChain(new FuraFilaPlanoRecursos());
		pr.execute();
	}
	
	public ImovelPlanoFaturaDTO registrarPagtoPublicidade(long idFatura) throws AlugueRelaxeException{

		DAOFactory daoFactory =  null;
		
		// Busca dados da ultima fatura do plano do imovel
		ImovelPlanoFaturaDTO ipfdto = this.pesquisarFatura(idFatura);
		
		// Busca informacoes do plano para uso na matriz de recurso
		PlanoService<PlanoDTO> ps = new PlanoServicesImpl();
		PlanoDTO planodto = ps.pesquisarRegistro(ipfdto.plano);
		
		// Localiza o imovel da fatura
		ImovelPlanoService<ImovelPlanoRelacaoDTO> ips = new ImovelPlanoServiceImpl();
		
		ImovelPlanoRelacaoDTO ipsdto = new ImovelPlanoRelacaoDTO();
		ipsdto.id = ipfdto.idImovelPlano;
		ipsdto = ips.pesquisarRegistro(ipsdto);
		
		// Busca informacoes da ficha do imovel
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(ipsdto.idImovel);
		
		// Verifica se anuncio esta pendente e vencido
		this.isAnuncioPendenteVencido(ipfdto);

		// Verifica se anuncio liberado esta vencido
		this.isAnuncioLiberadoVencido(ipfdto);

		// Verifica se o plano e gratuito
		//this.isPlanoGratuito(ipfdto);
		
		// Verifica se a ultima fatura ja encontra-se paga
		this.isPlanoPago(ipfdto);
			
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			//------------------------------------------------------------------------
			// - Registra o pagamento da fatura
			// - Libera a publicidasde 
			//------------------------------------------------------------------------
			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.pagarFatura(daoFactory, ipfdto.id);
			ib.liberarPublicidade(daoFactory, ipfdto.id);
			
			PublicidadeImovelDTO pidto = new PublicidadeImovelDTO();
			pidto.fatura = ipfdto;
			pidto.fichaImovel = ifcdto;
			pidto.plano = planodto;
			
			executeMatrizPlano(daoFactory, pidto);
			
			daoFactory.commit();
			
			//------------------------------------------------------------------------
			// Notifica cliente sobre o registro de pagamento e liberacao do anuncio 
			//------------------------------------------------------------------------
			//this.notificaPlanoAnuncioRenovado(ifcdto, ipfdto); 
			
			ipfdto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			ipfdto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return ipfdto;
		
	}
	
	public ImovelPlanoFaturaDTO renovarPlano(long idImovel) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		
		// Busca dados da ultima fatura do plano do imovel
		ImovelPlanoFaturaDTO ipfdto = this.pesquisarUltimaFatura(idImovel, "N");

		// Verifica se anuncio esta pendente e vencido
		this.isAnuncioPendenteVencido(ipfdto);

		// Verifica se anuncio esta pendente e nao vencido
		this.isAnuncioPendenteNaoVencido(ipfdto);

		// Verifica se anuncio liberado esta vencido
		this.isAnuncioLiberadoVencido(ipfdto);

		// Verifica se o plano e gratuito
		this.isPlanoGratuito(ipfdto);
		
		// Busca informacoes do plano atualizadas
		PlanoService<PlanoDTO> ps = new PlanoServicesImpl();
		PlanoDTO pdto = ps.pesquisarRegistro(ipfdto.plano);
		
		// Busca informacoes de relaciona imovel x plano
		ImovelPlanoService<ImovelPlanoRelacaoDTO> ips = new ImovelPlanoServiceImpl();
		ImovelPlanoRelacaoDTO ipdto = ips.pesquisarRegistro(ipfdto.idImovelPlano);
		
		// Busca informacoes do imovel
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		ImovelFichaCompletaDTO ifcdto = is.pesquisarFichaCompletaImovel(ipdto.idImovel);
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			//------------------------------------------------------------------------
			// Calcula a data de vencimento da nova fatura
			//------------------------------------------------------------------------
			DateManagerBase dv = DateManagerBase.getDateManagerInstance();
			dv.add(pdto.numeroDiasCalculo);

			//------------------------------------------------------------------------
			// Se a ultima fatura estiver com status de pendencia de pagamento
			// buscar o VALOR NOVO DO PLANO se ele estiver ATIVO. Se o plano estiver
			// inativo emitir uma mensagem de erro avisando que o cliente deve realizar
			// a migracao para outro plano
			//------------------------------------------------------------------------
			if (ipfdto.indicadorStatus.equals("P")) {
				if(pdto.indicadorStatus.equals("A")) {
					ipfdto.valorFatura = pdto.valor;
					ipfdto.valorDesconto = pdto.valorDesconto;
				} else {
					final Map<String,String> parametros = new HashMap<String,String>();
					parametros.put(Constantes.P1, String.valueOf(ipfdto.plano.descricao));
					throw new AlugueRelaxeException(MSGCODE.PLANO_INDISPONIVEL,
							MensagemCache.getInstance().getMensagem(MSGCODE.PLANO_INDISPONIVEL, parametros),
							null);
				}
			}

			//------------------------------------------------------------------------
			// Registra a fatura e a deixa em aberto
			//------------------------------------------------------------------------
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> ipfb = new ImovelPlanoFaturaBusinessImpl();
			ipfdto.id = daoFactory.getNextSequence(daoFactory, "SEQ_IMPF_CD_IMOVEL_PLANO_FATURA");
			ipfdto.idImovelPlano = ipdto.id;
			ipfdto.dataVencimento = dv.getDate() ;
			ipfdto = ipfb.incluir(daoFactory, ipfdto);
			
			//------------------------------------------------------------------------
			// Inativa circulacao do anuncio do imovel e entra em estado
			// de aguardando pagamento e confirmacao da fatura
			//------------------------------------------------------------------------
			ImovelBusiness<ImovelDTO> ib = new ImovelBusinessImpl();
			ib.atualizarStatusImovel(daoFactory, idImovel, ImovelBusiness.STATUS_PENDENTE);

			daoFactory.commit();

			final Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, ipfdto.plano.descricao);
			parametros.put(Constantes.P2, ifcdto.imovel.descricaoTituloAnuncio);
			ipfdto.msgcode = MSGCODE.PLANO_RENOVADO_COM_SUCESSO;
			ipfdto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.PLANO_RENOVADO_COM_SUCESSO, parametros);

			//------------------------------------------------------------------------
			// Notifica cliente sobre nova fatura pendente
			//------------------------------------------------------------------------
			this.notificaPlanoAnuncioRenovadoPendente(ifcdto, ipfdto); 
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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

		return ipfdto;
	}


	public List<Long> listarPlanosGratuitosVencidos() throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> bo = new ImovelPlanoFaturaBusinessImpl();
			lst = bo.listarPlanosGratuitosVencidos(daoFactory);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return lst;
	}

	public List<Long> listarPlanosPagosVencidos() throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> bo = new ImovelPlanoFaturaBusinessImpl();
			lst = bo.listarPlanosPagosVencidos(daoFactory);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return lst;
	}

	public List<Long> listarPlanosGratuitosAVencer() throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> bo = new ImovelPlanoFaturaBusinessImpl();
			lst = bo.listarPlanosGratuitosAVencer(daoFactory);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return lst;
	}

	public List<Long> listarPlanosPagosNaoVencidos() throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> bo = new ImovelPlanoFaturaBusinessImpl();
			lst = bo.listarPlanosPagosNaoVencidos(daoFactory);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return lst;
	}


	public List<Long> listarPlanosPagosAVencer() throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> bo = new ImovelPlanoFaturaBusinessImpl();
			lst = bo.listarPlanosPagosAVencer(daoFactory);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return lst;
	}

	public ImovelPlanoFaturaDTO pesquisarUltimaFatura(long codigoImovel, String tipo) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		ImovelPlanoFaturaDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> bo = new ImovelPlanoFaturaBusinessImpl();
			dtoretorno = bo.procurar(daoFactory, codigoImovel, tipo);
			daoFactory.commit();
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return dtoretorno;
	}

	public ImovelPlanoFaturaDTO pesquisarFatura(long idFatura) throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		ImovelPlanoFaturaDTO dtoretorno = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> bo = new ImovelPlanoFaturaBusinessImpl();
			dtoretorno = bo.procurar(daoFactory, idFatura);
			daoFactory.commit();
			
			dtoretorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dtoretorno.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return dtoretorno;
	}

	

	public ImovelPlanoFaturaDTO gravarRegistro(ImovelPlanoFaturaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelPlanoFaturaDTO excluirRegistro(ImovelPlanoFaturaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelPlanoFaturaDTO atualizarRegistro(ImovelPlanoFaturaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelPlanoFaturaDTO pesquisarRegistro(ImovelPlanoFaturaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends ImovelPlanoFaturaDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void isAnuncioLiberadoVencido(ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException {
		//------------------------------------------------------------------------
		// Se estiver paga e data de hoje for menor data de vencimento do anuncio 
		// ... emite erro ANUNCIO NAO ESTA VENCIDO
		//------------------------------------------------------------------------
		if (ipfdto.indicadorStatus.equals("L")) {
			DateManagerBase hoje = DateManagerBase.getDateManagerInstance();

			if(hoje.getTimeInMillis() < ipfdto.dataVencimento.getTime()) {
				final Map<String,String> parametros = new HashMap<String,String>();
				try {
					parametros.put(Constantes.P1,DateManagerBase.getDateManagerInstance(ipfdto.dataVencimento).getDateCustom("dd/MM/yyyy"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				throw new AlugueRelaxeException(MSGCODE.ANUNCIO_NAO_ESTA_VENCIDO,
						MensagemCache.getInstance().getMensagem(MSGCODE.ANUNCIO_NAO_ESTA_VENCIDO, parametros),
						null);
			}
		}
	}
	
	private void isPlanoPago(ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException {
		//------------------------------------------------------------------------
		// Se estiver paga ... emite erro ANUNCIO_PAGO
		//------------------------------------------------------------------------
		if (ipfdto.indicadorStatus.equals("L")) {
			final Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, String.valueOf(ipfdto.id));
			try {
				parametros.put(Constantes.P2, DateManagerBase.getDateManagerInstance(ipfdto.dataPagamento).getDateTimeFull());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new AlugueRelaxeException(MSGCODE.ANUNCIO_PAGO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ANUNCIO_PAGO, parametros),
					null);

			// emite erro
			//ANUNCIO_PAGO = Fatura #${1} do anuncio encontra-se quitada em ${2}.
		}
	}
	
	public void isAnuncioPendenteNaoVencido(ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException {
		//------------------------------------------------------------------------
		// Se estiver pendente e data de hoje for menor data de vencimento do anuncio 
		// ... emite erro ANUNCIO NAO ESTA VENCIDO
		//------------------------------------------------------------------------
		if (ipfdto.indicadorStatus.equals("P")) {
			DateManagerBase hoje = DateManagerBase.getDateManagerInstance();

			if( hoje.getTimeInMillis() < ipfdto.dataVencimento.getTime()) {
				final Map<String,String> parametros = new HashMap<String,String>();
				try {
					parametros.put(Constantes.P1,DateManagerBase.getDateManagerInstance(ipfdto.dataVencimento).getDateCustom("dd/MM/yyyy"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				throw new AlugueRelaxeException(MSGCODE.ANUNCIO_PENDENTE_NAO_ESTA_VENCIDO,
						MensagemCache.getInstance().getMensagem(MSGCODE.ANUNCIO_PENDENTE_NAO_ESTA_VENCIDO, parametros),
						null);

				// emite erro
				//ANUNCIO_PENDENTE_NAO_ESTA_VENCIDO = O pagamento da fatura está PENDENTE e aguardando pagamento ate ${1}.
			}
		}
	}
	
	
	public void isAnuncioPendenteVencido(ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException {
		//------------------------------------------------------------------------
		// Se estiver pendente e data de hoje for menor data de vencimento do anuncio 
		// ... emite erro ANUNCIO NAO ESTA VENCIDO
		//------------------------------------------------------------------------
		if (ipfdto.indicadorStatus.equals("P")) {
			DateManagerBase hoje = DateManagerBase.getDateManagerInstance();

			if(hoje.getTimeInMillis() > ipfdto.dataVencimento.getTime()) {
				final Map<String,String> parametros = new HashMap<String,String>();
				try {
					parametros.put(Constantes.P1,DateManagerBase.getDateManagerInstance(ipfdto.dataVencimento).getDateCustom("dd/MM/yyyy"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				throw new AlugueRelaxeException(MSGCODE.ANUNCIO_PENDENTE_ESTA_VENCIDO,
						MensagemCache.getInstance().getMensagem(MSGCODE.ANUNCIO_PENDENTE_ESTA_VENCIDO, parametros),
						null);

				// emite erro
				//ANUNCIO_NAO_ESTA_VENCIDO = O pagamento da fatura está PENDENTE e a data de vencimento venceu em ${1}.
			}
		}
	}
	
	public void isPlanoGratuito(ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException {
	//------------------------------------------------------------------------
	// Se o plano for gratuito não permite a renovacao
	//------------------------------------------------------------------------
	if (ipfdto.plano.id == Long.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PLANO_ADESAO_GRATUITO))) {
			throw new AlugueRelaxeException(MSGCODE.RENOVACAO_PLANO_GRATUITO_NEGADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.RENOVACAO_PLANO_GRATUITO_NEGADA),
					null);

			// emite erro
			//RENOVACAO_PLANO_GRATUITO_NEGADA = Não é permitido renovação anuncio de plano gratuito vencida. Por favor, use a opção migrar para outro plano no menu FINANCEIRO >> MIGRAR PLANO.
		}
	}


	public DTOPadrao atualizarStatusFatura(long idFatura, String status)
			throws AlugueRelaxeException {
		DAOFactory daoFactory =  null;
		DTOPadrao dto = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			
			//------------------------------------------------------------------------
			// - Registra o pagamento da fatura
			// - Ativa circulacao do anuncio do imovel 
			//------------------------------------------------------------------------
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> ib = new ImovelPlanoFaturaBusinessImpl();
			ib.atualizarStatusFatura(daoFactory, idFatura, Constantes.IMPF_STATUS_VENCIDO);
			daoFactory.commit();
			
			dto = new DTOPadrao();
			dto.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			dto.msgcodeString = MensagemCache.getInstance().getMensagem(MSGCODE.COMANDO_EXECUTADO_SUCESSO);
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		
		return dto;
	}

	public List<Long> listarPlanosPendentesVencidos()
			throws AlugueRelaxeException {

		DAOFactory daoFactory =  null;
		List<Long> lst = null;
		
		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();
			ImovelPlanoFaturaBusiness<ImovelPlanoFaturaDTO> bo = new ImovelPlanoFaturaBusinessImpl();
			lst = bo.listarPlanosPendentesVencidos(daoFactory);
			daoFactory.commit();
			
		} catch (AlugueRelaxeException he) {
			throw he;
			
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
		return lst;
	}
}
	
