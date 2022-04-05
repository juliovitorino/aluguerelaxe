package br.com.jcv.backend.reserva;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteBusinessImpl;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteServiceImpl;
import br.com.jcv.backend.contrato.Contrato;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.email.Email;
import br.com.jcv.backend.email.EmailFactory;
import br.com.jcv.backend.email.EmailRecord;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.filasms.FilaSMSDTO;
import br.com.jcv.backend.filasms.FilaSMSFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelServiceImpl;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadBusinessImpl;
import br.com.jcv.backend.imovel.thread.ContatoAnuncianteThreadDTO;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteBusiness;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteThreadBusiness;
import br.com.jcv.backend.interfaces.business.LocatarioBusiness;
import br.com.jcv.backend.interfaces.business.ReservaBusiness;
import br.com.jcv.backend.interfaces.services.ContatoAnuncianteService;
import br.com.jcv.backend.interfaces.services.ImovelService;
import br.com.jcv.backend.interfaces.services.ReservaService;
import br.com.jcv.backend.locatario.LocatarioBusinessImpl;
import br.com.jcv.backend.locatario.LocatarioDTO;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.template.TemplateConstantes;
import br.com.jcv.backend.template.TemplateFactory;
import br.com.jcv.backend.utility.Extenso;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ReservaServiceImpl implements ReservaService<ReservaDTO> {

	private static Logger logger = Logger.getLogger(ReservaServiceImpl.class);

	public ReservaDTO pesquisarReserva(String hashContatoAnunciante)
			throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			retorno = bo.pesquisarReserva(daoFactory, hashContatoAnunciante);
			
			ContatoAnuncianteBusiness cab = new ContatoAnuncianteBusinessImpl();
			retorno.contatoCliente = cab.procurar(daoFactory, retorno.idContato);
			
			if (retorno != null){
				LocatarioBusiness<LocatarioDTO> lb = new LocatarioBusinessImpl();
				retorno.locatario = lb.procurar(daoFactory, retorno.locatario);
			}
			daoFactory.commit();
			
			if (retorno != null){
				ImovelService<ImovelDTO> is = new ImovelServiceImpl();
				retorno.ifcdto = is.pesquisarFichaCompletaImovel(retorno.ifcdto.imovel.id);
				retorno.contatoCliente.ifcdto = is.pesquisarFichaCompletaImovel(retorno.ifcdto.imovel.id);
			}

			retorno.msgcode = MSGCODE.COMANDO_EXECUTADO_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(
					MSGCODE.COMANDO_EXECUTADO_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}


	public ReservaDTO solicitarLiberacaoDeposito(long idClienteSolicitante,
			String voucher, String tracking) throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();

			// Verifica se já houve liberacao pelo locador anteriormente
			ReservaDTO dtorsrv = bo.procurarChaveTracker(daoFactory, tracking);
			if (dtorsrv == null) {
				throw new AlugueRelaxeException(
						MSGCODE.CODIGO_TRACKING_INVALIDO, MensagemCache
								.getInstance().getMensagem(
										MSGCODE.CODIGO_TRACKING_INVALIDO), null);
			}

			bo.validarLiberacaoConfirmada(dtorsrv);

			// executa os procedimentos para liberacao do pagamento ao locador
			retorno = bo.solicitarLiberacaoDeposito(daoFactory,
					idClienteSolicitante, voucher, tracking);
			daoFactory.commit();
			
			ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
			retorno.contatoCliente = cas.pesquisarRegistro(retorno.idContato);

			// Envia SMS ao anunciante sobre a liberacao do pagamento
			Map<String,String> param = new HashMap<String,String>();
			param.put(Constantes.P1, voucher);
			param.put(Constantes.P2, String.valueOf(retorno.id));
			param.put(Constantes.P3, StringUtil.valorCorreto( retorno.valorRealDeposito - retorno.valorTaxaServico));
			
			List<FilaSMSDTO> lstcel = FilaSMSFactory.getDTOCliente(idClienteSolicitante, 
				MensagemCache.getInstance().getMensagem(MSGCODE.SOLICITACAO_LIBERACAO_APROVADA, param));
				
			FilaSMSFactory.enviarSMS(lstcel);
			
			// Busca a ficha do imovel completa
			ImovelService is = new ImovelServiceImpl<ImovelDTO>();
			retorno.ifcdto = is
					.pesquisarFichaCompletaImovel(retorno.ifcdto.imovel.id);

			// ------------------------------------------------------------------
			// Envia email ao aluguerelaxe avisando sobre a necessidade
			// de transferencia do deposito para a conta corrente do
			// locador.
			// ------------------------------------------------------------------
			// substitui campos do contrato

			String strFormaPgto = (retorno.formaPagamento
					.equals(Constantes.FORMA_PGTO_PARCIAL) ? Constantes.FORMA_PGTO_PARCIAL_STR
					: Constantes.FORMA_PGTO_TOTAL_STR);
			double vcomissao = retorno.valorTaxaServico;
			double vliberar = retorno.valorRealDeposito - vcomissao;
			double vpagochave = (retorno.valorAluguelNegociado + vcomissao)
					* (retorno.formaPagamento
							.equals(Constantes.FORMA_PGTO_PARCIAL) ? 0.5 : 0);
			
			String linkReserva = VariavelCache.getInstance().getValor(
					VariavelConstantes.LINK_ACOMPANHAMENTO_WORKFLOW_PRE_RESERVA);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${hash}", retorno.idContato);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${omc}", retorno.contatoCliente.codigoOMCThreadVisitante);

			Map<String, String> conteudo = new HashMap<String, String>();
			
			conteudo.put(TemplateConstantes.TVPR_TAG_LINK_RESERVA,
					linkReserva);
			conteudo.put(TemplateConstantes.TETD_TAG_CODIGO_TRACKING,
					retorno.chaveTracker);
			conteudo.put(TemplateConstantes.TETD_TAG_NOME_BANCO,
					retorno.ifcdto.cliente.banco);
			conteudo.put(TemplateConstantes.TETD_TAG_NOME_AGENCIA,
					retorno.ifcdto.cliente.agencia);
			conteudo.put(TemplateConstantes.TETD_TAG_CONTA_CORRENTE,
					retorno.ifcdto.cliente.contacorrente);
			conteudo.put(TemplateConstantes.TETD_TAG_NOME_LOCADOR,
					retorno.ifcdto.cliente.nome);
			conteudo.put(TemplateConstantes.TETD_TAG_VALOR_NEGOCIADO_TEMPORADA,
					String.valueOf(StringUtil
							.valorCorreto(retorno.valorAluguelNegociado + retorno.valorTaxaServico)));
			conteudo.put(TemplateConstantes.TETD_TAG_FORMA_PGTO_ESCOLHIDA,
					strFormaPgto);
			conteudo.put(TemplateConstantes.TETD_TAG_VALOR_DEPOSITADO_CUSTODIA,
					String.valueOf(StringUtil
							.valorCorreto(retorno.valorRealDeposito)));
			conteudo.put(
					TemplateConstantes.TETD_TAG_VALOR_DEPOSITO_CAUCAO,
					String.valueOf(StringUtil.valorCorreto(retorno.valorCaucao)));
			conteudo.put(TemplateConstantes.TETD_TAG_VALOR_A_SER_PAGO_NA_CHAVE,
					String.valueOf(StringUtil.valorCorreto(vpagochave)));
			conteudo.put(
					TemplateConstantes.TETD_TAG_PERC_COMISSAO_ALUGUE_RELAXE,
					String.valueOf(Math.abs(retorno.percentualComissao * 100)));
			conteudo.put(
					TemplateConstantes.TETD_TAG_VALOR_COMISSAO_ALUGUE_RELAXE,
					String.valueOf(StringUtil.valorCorreto(vcomissao)));
			conteudo.put(TemplateConstantes.TETD_TAG_VALOR_A_SER_LIBERADO,
					String.valueOf(StringUtil.valorCorreto(vliberar)));

			ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
			lst.add(new EmailRecord(VariavelCache.getInstance().getValor(
					VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE), "Reservas AR"));

			Email email = EmailFactory
					.getInstanceEmailTransferenciaDeposito(conteudo);
			email.enviar(lst, null, "Executar transferencia de deposito - #"
					+ retorno.id, null);

			retorno.msgcode = MSGCODE.LIBERACAO_PAGAMENTO_REGISTRADO_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(
					MSGCODE.LIBERACAO_PAGAMENTO_REGISTRADO_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}

	public ReservaDTO confirmarReserva(String chave, Date dataRealDeposito,
			double valorRealDeposito) throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();

			// Verifica se já houve deposito
			ReservaDTO dtorsrv = bo.procurarChaveTracker(daoFactory, chave);
			bo.validarReservaConfirmada(dtorsrv);

			retorno = bo.confirmarReserva(daoFactory, chave, dataRealDeposito,
					valorRealDeposito);
			daoFactory.commit();
			
			ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
			retorno.contatoCliente = cas.pesquisarRegistro(retorno.idContato);

			// Busca a ficha do imovel completa
			ImovelService is = new ImovelServiceImpl<ImovelDTO>();
			retorno.ifcdto = is
					.pesquisarFichaCompletaImovel(retorno.ifcdto.imovel.id);

			// -----------------------------------------------------------------------
			// Envia SMS ao anunciante sobre o deposito parcial ou total
			// -----------------------------------------------------------------------
			Map<String,String> param = new HashMap<String,String>();
			param.put(Constantes.P1, retorno.ifcdto.imovel.endereco.cidade.toUpperCase());
			param.put(Constantes.P2, retorno.ifcdto.imovel.endereco.uf.toUpperCase());
			param.put(Constantes.P3, StringUtil.valorCorreto(retorno.valorRealDeposito));
			param.put(Constantes.P4, retorno.ifcdto.cliente.email);
			
			List<FilaSMSDTO> lstcel = FilaSMSFactory.getDTOCliente(retorno.ifcdto.cliente.id, 
				MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_CONFIRMADA, param));

			FilaSMSFactory.enviarSMS(lstcel);
				
			// -----------------------------------------------------------------------
			// Envia SMS ao cliente que alugou o imovel
			// -----------------------------------------------------------------------
			Map<String,String> p = new HashMap<String,String>();
			p.put(Constantes.P1, retorno.ifcdto.imovel.endereco.cidade.toUpperCase());
			p.put(Constantes.P2, retorno.ifcdto.imovel.endereco.uf.toUpperCase());
			p.put(Constantes.P3, StringUtil.valorCorreto(retorno.valorRealDeposito));
			p.put(Constantes.P4, dtorsrv.locatario.email);
			
			FilaSMSDTO smsdto = FilaSMSFactory.getDTO(dtorsrv.locatario.telefone.ddd + dtorsrv.locatario.telefone.telefone, 
				MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_CONFIRMADA, p));

			FilaSMSFactory.enviarSMS(smsdto);
				
			// -----------------------------------------------------------------------
			// Cria o contrato com base no template para enviar para as partes e
			// LOCATARIO
			// -----------------------------------------------------------------------
			String strFormaPgto = (retorno.formaPagamento
					.equals(Constantes.FORMA_PGTO_PARCIAL) ? Constantes.FORMA_PGTO_PARCIAL_STR
					: Constantes.FORMA_PGTO_TOTAL_STR);
			String centralfones = VariavelCache.getInstance().getValor(
					VariavelConstantes.TELEFONES_CENTRAL_RESERVA);
			
			String linkReserva = VariavelCache.getInstance().getValor(
					VariavelConstantes.LINK_ACOMPANHAMENTO_WORKFLOW_PRE_RESERVA);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${hash}", retorno.idContato);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${omc}", retorno.contatoCliente.codigoOMCThreadVisitante);

			// substitui campos do contrato
			Map<String, String> conteudo = new HashMap<String, String>();
			try {
				
				conteudo.put(
						TemplateConstantes.TVPR_TAG_LINK_RESERVA,
						linkReserva);
				
				conteudo.put(
						TemplateConstantes.TCTL_TAG_DATA_CHECKIN,
						DateManagerBase.getDateManagerInstance(
								retorno.dataCheckin)
								.getDateCustom("dd/MM/yyyy"));
				conteudo.put(
						TemplateConstantes.TCTL_TAG_DATA_CHECKOUT,
						DateManagerBase.getDateManagerInstance(
								retorno.dataCheckout).getDateCustom(
								"dd/MM/yyyy"));
				conteudo.put(
						TemplateConstantes.TCTL_TAG_DATA_CADASTRO,
						DateManagerBase.getDateManagerInstance(
								retorno.dataCadastro).getDateTimeFull());
				conteudo.put(
						TemplateConstantes.TCTL_TAG_DATA_PREVISTA_DEPOSITO,
						DateManagerBase.getDateManagerInstance(
								retorno.dataPrevistaDeposito).getDateCustom(
								"dd/MM/yyyy"));
				conteudo.put(
						TemplateConstantes.TCTL_TAG_DATA_VALIDACAO_PRE_RESERVA,
						DateManagerBase.getDateManagerInstance(
								retorno.dataValidacaoPreReserva)
								.getDateTimeFull());
			} catch (ParseException e) {
				conteudo.put(TemplateConstantes.TCTL_TAG_DATA_CHECKIN,
						retorno.dataCheckin.toString());
				conteudo.put(TemplateConstantes.TCTL_TAG_DATA_CHECKOUT,
						retorno.dataCheckout.toString());
				conteudo.put(TemplateConstantes.TCTL_TAG_DATA_CADASTRO,
						retorno.dataCadastro.toString());
				conteudo.put(
						TemplateConstantes.TCTL_TAG_DATA_PREVISTA_DEPOSITO,
						retorno.dataPrevistaDeposito.toString());
				conteudo.put(
						TemplateConstantes.TCTL_TAG_DATA_VALIDACAO_PRE_RESERVA,
						retorno.dataValidacaoPreReserva.toString());
			}
			conteudo.put(TemplateConstantes.TCTL_TAG_CODIGO_RESERVA,
					String.valueOf(retorno.id));
			conteudo.put(TemplateConstantes.TCTL_TAG_NOME_PROPOSTO,
					retorno.locatario.nome.toUpperCase());
			conteudo.put(TemplateConstantes.TCTL_TAG_CODIGO_IMOVEL,
					String.valueOf(retorno.ifcdto.imovel.id));
			conteudo.put(TemplateConstantes.TCTL_TAG_TITULO_IMOVEL,
					retorno.ifcdto.imovel.descricaoTituloAnuncio);
			conteudo.put(TemplateConstantes.TCTL_TAG_CODIGO_TRACKING,
					retorno.chaveTracker);
			conteudo.put(TemplateConstantes.TCTL_TAG_VALOR_NEGOCIADO, String
					.valueOf(StringUtil
							.valorCorreto(retorno.valorAluguelNegociado + retorno.valorTaxaServico)));
			conteudo.put(TemplateConstantes.TCTL_TAG_EMAIL_CONTATO,
					retorno.locatario.email);
			conteudo.put(TemplateConstantes.TCTL_TAG_TOKEN, retorno.token);
			conteudo.put(TemplateConstantes.TCTL_TAG_CPF_PROPOSTO,
					retorno.locatario.cpf);
			conteudo.put(TemplateConstantes.TCTL_TAG_FORMA_DE_PAGAMENTO,
					strFormaPgto);
			conteudo.put(TemplateConstantes.TCTL_TAG_VALOR_PREVISTO_DEPOSITO,
					String.valueOf(StringUtil
							.valorCorreto(retorno.valorPrevistoDeposito)));
			conteudo.put(
					TemplateConstantes.TCTL_TAG_VALOR_PREVISTO_DEPOSITO_EXTENSO,
					Extenso.valor(retorno.valorPrevistoDeposito));
			conteudo.put(TemplateConstantes.TCTL_TAG_TELEFONES_CENTRAL_RESERVA,
					centralfones);
			conteudo.put(TemplateConstantes.TCTL_TAG_VALOR_POR_EXTENSO,
					Extenso.valor(retorno.valorAluguelNegociado + retorno.valorTaxaServico));
			conteudo.put(TemplateConstantes.TCTL_TAG_VALOR_DA_CAUCAO, String
					.valueOf(StringUtil.valorCorreto(retorno.valorCaucao)));
			conteudo.put(
					TemplateConstantes.TCTL_TAG_VALOR_DA_CAUCAO_POR_EXTENSO,
					Extenso.valor(retorno.valorCaucao));
			conteudo.put(
					TemplateConstantes.TCTL_TAG_PERCENTUAL_COMISSAO_RESERVA,
					String.valueOf(Math.abs(retorno.percentualComissao * 100)));
			conteudo.put(
					TemplateConstantes.TCTL_TAG_ENDERECO_COMPLETO_LOCATARIO,
					retorno.locatario.endereco.toString());
			conteudo.put(
					TemplateConstantes.TCTL_TAG_PERCENTUAL_COMISSAO_RESERVA_POR_EXTENSO,
					Extenso.inteiro(retorno.percentualComissao * 100));
			conteudo.put(TemplateConstantes.TCTL_TAG_QTDE_PESSOAS_IMOVEL,
					String.valueOf(retorno.ifcdto.imovel.qtdeCapacidade));
			conteudo.put(
					TemplateConstantes.TCTL_TAG_QTDE_PESSOAS_IMOVEL_EXTENSO,
					Extenso.inteiro(retorno.ifcdto.imovel.qtdeCapacidade));
			conteudo.put(
					TemplateConstantes.TCTL_TAG_DATA_INICIO_LOCACAO_EXTENSO,
					Extenso.data(retorno.dataCheckin));
			conteudo.put(TemplateConstantes.TCTL_TAG_DATA_FIM_LOCACAO_EXTENSO,
					Extenso.data(retorno.dataCheckout));
			conteudo.put(
					TemplateConstantes.TCTL_TAG_DATA_DO_CONTRATO_POR_EXTENSO,
					Extenso.data(retorno.dataRealDeposito));
			conteudo.put(TemplateConstantes.TCTL_TAG_ENDERECO_COMPLETO_IMOVEL,
					retorno.ifcdto.imovel.endereco.toString());

			// Informações do LOCADOR
			conteudo.put(TemplateConstantes.TCTL_TAG_NOME_DO_LOCADOR,
					retorno.ifcdto.cliente.nome.toUpperCase());
			conteudo.put(TemplateConstantes.TCTL_TAG_CPF_LOCADOR,
					retorno.ifcdto.cliente.cpf);
			conteudo.put(TemplateConstantes.TCTL_TAG_ENDERECO_COMPLETO_LOCADOR,
					retorno.ifcdto.cliente.endereco.toString());

			// ------------------------------------------------------------------
			// Verifica que modelo de contrato ira enviar: Seguro caucao ou nao
			// ------------------------------------------------------------------
			Contrato ctrLocador = null;
			if (retorno.valorCaucao > 0) {
				ctrLocador = new Contrato(
						VariavelCache.getInstance().getValor(
								VariavelConstantes.PATH_CONTRATO_LOCATARIO)
								+ "lctr-"
								+ retorno.chaveTracker
								+ "-"
								+ retorno.token + ".html",
						TemplateFactory
								.getInstance(
										TemplateConstantes.TEMPLATE_CTR_LOCACAO_TEMPORADA_LOCATARIO,
										conteudo));
			} else {
				ctrLocador = new Contrato(
						VariavelCache.getInstance().getValor(
								VariavelConstantes.PATH_CONTRATO_LOCATARIO)
								+ "lctr-"
								+ retorno.chaveTracker
								+ "-"
								+ retorno.token + ".html",
						TemplateFactory
								.getInstance(
										TemplateConstantes.TEMPLATE_CTR_LOCACAO_TEMPORADA_LOCADOR_SEM_CAUCAO,
										conteudo));
			}
			ctrLocador.gerar();

			// -----------------------------------------------------------------------
			// Cria o email de agradecimento ao LOCATARIO e LOCADOR pela
			// negociacao
			// e envia o link para contrato
			// -----------------------------------------------------------------------
			double vpagochave = (retorno.valorAluguelNegociado + retorno.valorTaxaServico)
					* (retorno.formaPagamento
							.equals(Constantes.FORMA_PGTO_PARCIAL) ? 0.5 : 0);

			Map<String, String> conteudocp = new HashMap<String, String>();
			try {
				conteudocp.put(
						TemplateConstantes.TVPR_TAG_LINK_RESERVA,
						linkReserva);
				
				conteudocp.put(
						TemplateConstantes.TCPR_TAG_DATA_CHECKIN,
						DateManagerBase.getDateManagerInstance(
								retorno.dataCheckin)
								.getDateCustom("dd/MM/yyyy"));
				conteudocp.put(
						TemplateConstantes.TCPR_TAG_DATA_CHECKOUT,
						DateManagerBase.getDateManagerInstance(
								retorno.dataCheckout).getDateCustom(
								"dd/MM/yyyy"));
				conteudocp.put(
						TemplateConstantes.TCPR_TAG_DATA_CADASTRO,
						DateManagerBase.getDateManagerInstance(
								retorno.dataCadastro).getDateTimeFull());
				conteudocp.put(
						TemplateConstantes.TCPR_TAG_DATA_PREVISTA_DEPOSITO,
						DateManagerBase.getDateManagerInstance(
								retorno.dataPrevistaDeposito).getDateCustom(
								"dd/MM/yyyy"));
				conteudocp.put(
						TemplateConstantes.TCPR_TAG_DATA_VALIDACAO_PRE_RESERVA,
						DateManagerBase.getDateManagerInstance(
								retorno.dataValidacaoPreReserva)
								.getDateTimeFull());
				conteudocp
						.put(TemplateConstantes.TCPR_TAG_DATA_VALIDACAO_PGTO_PRE_RESERVA,
								DateManagerBase.getDateManagerInstance(
										retorno.dataRealDeposito)
										.getDateTimeFull());
			} catch (ParseException e) {
				conteudocp.put(TemplateConstantes.TCPR_TAG_DATA_CHECKIN,
						retorno.dataCheckin.toString());
				conteudocp.put(TemplateConstantes.TCPR_TAG_DATA_CHECKOUT,
						retorno.dataCheckout.toString());
				conteudocp.put(TemplateConstantes.TCPR_TAG_DATA_CADASTRO,
						retorno.dataCadastro.toString());
				conteudocp.put(
						TemplateConstantes.TCPR_TAG_DATA_PREVISTA_DEPOSITO,
						retorno.dataPrevistaDeposito.toString());
				conteudocp.put(
						TemplateConstantes.TCPR_TAG_DATA_VALIDACAO_PRE_RESERVA,
						retorno.dataValidacaoPreReserva.toString());
				conteudocp
						.put(TemplateConstantes.TCPR_TAG_DATA_VALIDACAO_PGTO_PRE_RESERVA,
								retorno.dataRealDeposito.toString());
			}
			conteudocp.put(TemplateConstantes.TCPR_TAG_CODIGO_RESERVA,
					String.valueOf(retorno.id));
			conteudocp.put(TemplateConstantes.TCPR_TAG_NOME_PROPOSTO,
					retorno.locatario.nome);
			conteudocp.put(TemplateConstantes.TCPR_TAG_CODIGO_IMOVEL,
					String.valueOf(retorno.ifcdto.imovel.id));
			conteudocp.put(TemplateConstantes.TCPR_TAG_TITULO_IMOVEL,
					retorno.ifcdto.imovel.descricaoTituloAnuncio);
			conteudocp.put(TemplateConstantes.TCPR_TAG_CODIGO_TRACKING,
					retorno.chaveTracker);
			conteudocp.put(TemplateConstantes.TCPR_TAG_VALOR_NEGOCIADO, String
					.valueOf(StringUtil
							.valorCorreto(retorno.valorAluguelNegociado + retorno.valorTaxaServico)));
			conteudocp.put(TemplateConstantes.TCPR_TAG_EMAIL_CONTATO,
					retorno.locatario.email);
			conteudocp.put(TemplateConstantes.TCPR_TAG_TOKEN, retorno.token);
			conteudocp.put(TemplateConstantes.TCPR_TAG_CPF_PROPOSTO,
					retorno.locatario.cpf);
			conteudocp.put(TemplateConstantes.TCPR_TAG_FORMA_DE_PAGAMENTO,
					strFormaPgto);
			conteudocp.put(TemplateConstantes.TCPR_TAG_VALOR_PREVISTO_DEPOSITO,
					String.valueOf(StringUtil
							.valorCorreto(retorno.valorPrevistoDeposito)));
			conteudocp
					.put(TemplateConstantes.TCPR_TAG_VALOR_PREVISTO_DEPOSITO_EXTENSO,
							Extenso.valor(retorno.valorPrevistoDeposito));
			conteudocp.put(
					TemplateConstantes.TCPR_TAG_TELEFONES_CENTRAL_RESERVA,
					centralfones);
			conteudocp.put(TemplateConstantes.TCPR_TAG_VALOR_POR_EXTENSO,
					Extenso.valor(retorno.valorRealDeposito));
			conteudocp
					.put(TemplateConstantes.TCPR_TAG_VALOR_VALIDACAO_PGTO_PRE_RESERVA,
							String.valueOf(StringUtil
									.valorCorreto(retorno.valorRealDeposito)));
			conteudocp.put(TemplateConstantes.TCPR_TAG_LINK_CONTRATO_LOCATARIO,
					retorno.urlContratoLocador);
			conteudocp.put(TemplateConstantes.TCPR_TAG_VALOR_CAUCAO, String
					.valueOf(StringUtil.valorCorreto(retorno.valorCaucao)));
			conteudocp.put(
					TemplateConstantes.TCPR_TAG_VALOR_PAGAR_NA_ENTREGA_CHAVE,
					String.valueOf(StringUtil.valorCorreto(vpagochave)));
			conteudocp
					.put(TemplateConstantes.TCPR_TAG_VALOR_PAGAR_NA_ENTREGA_CHAVE_POR_EXTENSO,
							Extenso.valor(vpagochave));

			ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
			lst.add(new EmailRecord(retorno.locatario.email,
					retorno.locatario.nome));
			lst.add(new EmailRecord(retorno.ifcdto.cliente.email,
					retorno.ifcdto.cliente.nome));
			lst.add(new EmailRecord(VariavelCache.getInstance().getValor(
					VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE), "Reservas AR"));

			Email email = EmailFactory
					.getInstanceEmailConfirmacaoPgtoReserva(conteudocp);
			email.enviar(lst, null, "Pagamento de reserva confirmado - #"
					+ retorno.id, null);

			// --------------------------------------------------------------------------
			// Envia e-mail para proposto com voucher
			// --------------------------------------------------------------------------
			Map<String, String> cv = new HashMap<String, String>();
			try {
				cv.put(TemplateConstantes.TV_TAG_DATA_VALIDACAO_PRE_RESERVA,
						DateManagerBase.getDateManagerInstance(
								retorno.dataValidacaoPreReserva)
								.getDateTimeFull());
				cv.put(TemplateConstantes.TV_TAG_DATA_CHECKIN, DateManagerBase
						.getDateManagerInstance(retorno.dataCheckin)
						.getDateCustom("dd/MM/yyyy"));
				cv.put(TemplateConstantes.TV_TAG_DATA_CHECKOUT, DateManagerBase
						.getDateManagerInstance(retorno.dataCheckout)
						.getDateCustom("dd/MM/yyyy"));
				cv.put(TemplateConstantes.TV_TAG_DATA_CADASTRO, DateManagerBase
						.getDateManagerInstance(retorno.dataCadastro)
						.getDateTimeFull());
				cv.put(TemplateConstantes.TV_TAG_DATA_PREVISTA_DEPOSITO,
						DateManagerBase.getDateManagerInstance(
								retorno.dataPrevistaDeposito).getDateCustom(
								"dd/MM/yyyy"));

			} catch (ParseException e) {
				cv.put(TemplateConstantes.TV_TAG_DATA_VALIDACAO_PRE_RESERVA,
						retorno.dataValidacaoPreReserva.toString());
				cv.put(TemplateConstantes.TV_TAG_DATA_CHECKIN,
						retorno.dataCheckin.toString());
				cv.put(TemplateConstantes.TV_TAG_DATA_CHECKOUT,
						retorno.dataCheckout.toString());
				cv.put(TemplateConstantes.TV_TAG_DATA_CADASTRO,
						retorno.dataCadastro.toString());
				cv.put(TemplateConstantes.TV_TAG_DATA_PREVISTA_DEPOSITO,
						retorno.dataPrevistaDeposito.toString());
			}
			cv.put(TemplateConstantes.TV_TAG_CODIGO_RESERVA,
					String.valueOf(retorno.id));
			cv.put(TemplateConstantes.TV_TAG_NOME_PROPOSTO,
					retorno.locatario.nome);
			cv.put(TemplateConstantes.TV_TAG_CODIGO_IMOVEL,
					String.valueOf(retorno.ifcdto.imovel.id));
			cv.put(TemplateConstantes.TV_TAG_TITULO_IMOVEL,
					retorno.ifcdto.imovel.descricaoTituloAnuncio);
			cv.put(TemplateConstantes.TV_TAG_CODIGO_VOUCHER,
					retorno.chaveLiberacaoDeposito);
			cv.put(TemplateConstantes.TV_TAG_CODIGO_TRACKING,
					retorno.chaveTracker);
			cv.put(TemplateConstantes.TV_TAG_VALOR_NEGOCIADO, String
					.valueOf(StringUtil
							.valorCorreto(retorno.valorAluguelNegociado + retorno.valorTaxaServico)));
			cv.put(TemplateConstantes.TV_TAG_EMAIL_CONTATO,
					retorno.locatario.email);
			cv.put(TemplateConstantes.TV_TAG_TOKEN, retorno.token);
			cv.put(TemplateConstantes.TV_TAG_CPF_PROPOSTO,
					retorno.locatario.cpf);
			cv.put(TemplateConstantes.TV_TAG_FORMA_DE_PAGAMENTO, strFormaPgto);
			cv.put(TemplateConstantes.TV_TAG_VALOR_PREVISTO_DEPOSITO, String
					.valueOf(StringUtil
							.valorCorreto(retorno.valorPrevistoDeposito)));
			cv.put(TemplateConstantes.TV_TAG_VALOR_REAL_DEPOSITO,
					String.valueOf(StringUtil
							.valorCorreto(retorno.valorRealDeposito)));
			cv.put(TemplateConstantes.TV_TAG_VALOR_PAGAR_ENTREGA_CHAVE,
					String.valueOf(StringUtil.valorCorreto(vpagochave)));
			cv.put(TemplateConstantes.TV_TAG_TELEFONES_CENTRAL_RESERVA,
					centralfones);
			cv.put(TemplateConstantes.TV_TAG_VALOR_POR_EXTENSO,
					Extenso.valor(retorno.valorAluguelNegociado + retorno.valorTaxaServico));
			cv.put(TemplateConstantes.TV_TAG_VALOR_DA_CAUCAO, String
					.valueOf(StringUtil.valorCorreto(retorno.valorCaucao)));
			cv.put(TemplateConstantes.TV_TAG_VALOR_DA_CAUCAO_POR_EXTENSO,
					Extenso.valor(retorno.valorCaucao));
			cv.put(TemplateConstantes.TV_TAG_PERCENTUAL_COMISSAO_RESERVA,
					String.valueOf(Math.abs(retorno.percentualComissao * 100)));
			cv.put(TemplateConstantes.TV_TAG_ENDERECO_COMPLETO_LOCATARIO,
					retorno.locatario.endereco.toString());
			cv.put(TemplateConstantes.TV_TAG_PERCENTUAL_COMISSAO_RESERVA_POR_EXTENSO,
					Extenso.inteiro(retorno.percentualComissao * 100));
			cv.put(TemplateConstantes.TV_TAG_QTDE_PESSOAS_IMOVEL,
					String.valueOf(retorno.ifcdto.imovel.qtdeCapacidade));
			cv.put(TemplateConstantes.TV_TAG_QTDE_PESSOAS_IMOVEL_EXTENSO,
					Extenso.inteiro(retorno.ifcdto.imovel.qtdeCapacidade));

			ArrayList<EmailRecord> lstv = new ArrayList<EmailRecord>();
			lstv.add(new EmailRecord(retorno.locatario.email,
					retorno.locatario.nome));

			Email emailv = EmailFactory.getInstanceEmailVoucher(cv);
			emailv.enviar(lstv, null,
					"Voucher para libera\u00e7\u00e3o de pagamento reserva - #"
							+ retorno.id, null);
			//----------------------------------------------------------------
			// envia email ao anunciante com as instrucoes para
			// resgate do adiantamento recebido
			//----------------------------------------------------------------
			linkReserva = VariavelCache.getInstance().getValor(
					VariavelConstantes.LINK_ACOMPANHAMENTO_WORKFLOW_PRE_RESERVA);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${hash}", retorno.idContato);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${omc}", retorno.contatoCliente.codigoOMCThreadAnunciante);

			Map<String, String> mapor = new HashMap<String, String>();
			mapor.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO,retorno.ifcdto.imovel.descricaoTituloAnuncio.toUpperCase());
			mapor.put(TemplateConstantes.TAGC_NOME_CLIENTE,retorno.ifcdto.cliente.nome.toUpperCase());
			mapor.put(TemplateConstantes.TAGC_NOME_VISITANTE,dtorsrv.locatario.nome.toUpperCase());
			mapor.put(TemplateConstantes.TAGC_CIDADE,retorno.ifcdto.imovel.endereco.cidade.toUpperCase());
			mapor.put(TemplateConstantes.TAGC_UF,retorno.ifcdto.imovel.endereco.uf);
			mapor.put(TemplateConstantes.TAGC_DESCRICAO_GERAL_IMOVEL,retorno.ifcdto.imovel.descricaoGeral);
			mapor.put(TemplateConstantes.TAGC_LINK_ACOMPANHAMENTO_PORTAL, linkReserva);
			try {
				mapor.put(TemplateConstantes.TVPR_TAG_DATA_CHECKIN, DateManagerBase.getDateManagerInstance(dtorsrv.dataCheckin).getDateCustom("dd/MM/yyyy") );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			ArrayList<EmailRecord> lstor = new ArrayList<EmailRecord>();
			lstor.add(new EmailRecord(retorno.ifcdto.cliente.email,
					retorno.ifcdto.cliente.nome));

			Email emailor = EmailFactory.getInstanceEmailOrientacoesResgateReserva(mapor);
			emailor.enviar(lstor, null,
					"AlugueRelaxe - Instrucoes para resgate da reserva do imovel #"
							+ retorno.ifcdto.imovel.id, null);

			retorno.msgcode = MSGCODE.PAGAMENTO_REGISTRADO_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(
					MSGCODE.PAGAMENTO_REGISTRADO_REALIZADA_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}

	public ReservaDTO aprovarPreReserva(String token)
			throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Inscreve o novo assinante da campanha
			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			retorno = bo.aprovarPreReserva(daoFactory, token);
			daoFactory.commit();
			
			ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
			retorno.contatoCliente = cas.pesquisarRegistro(retorno.idContato);

			enviarEmailAguardandoDeposito(retorno);

			// Busca a ficha do imovel completa
			ImovelService is = new ImovelServiceImpl<ImovelDTO>();
			retorno.ifcdto = is
					.pesquisarFichaCompletaImovel(retorno.ifcdto.imovel.id);
			
			// -----------------------------------------------------------------------
			// Envia SMS ao anunciante sobre a prereserva
			// -----------------------------------------------------------------------
			Map<String,String> param = new HashMap<String,String>();
			param.put(Constantes.P1, retorno.ifcdto.imovel.endereco.cidade.toUpperCase());
			param.put(Constantes.P2, retorno.ifcdto.imovel.endereco.uf.toUpperCase());
			param.put(Constantes.P3, StringUtil.valorCorreto((retorno.valorAluguelNegociado + retorno.valorTaxaServico)
							* (retorno.formaPagamento.equals(Constantes.FORMA_PGTO_PARCIAL) ? 0.5 : 1))); 
			param.put(Constantes.P4, retorno.ifcdto.cliente.email);
			
			List<FilaSMSDTO> lstcel = FilaSMSFactory.getDTOCliente(retorno.ifcdto.cliente.id, 
				MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_AGUARDANDO_PGTO, param));

			FilaSMSFactory.enviarSMS(lstcel);
				

			// -----------------------------------------------------------------------
			// Envia SMS ao cliente que alugou o imovel
			// -----------------------------------------------------------------------
			Map<String,String> p = new HashMap<String,String>();
			p.put(Constantes.P1, retorno.ifcdto.imovel.endereco.cidade.toUpperCase());
			p.put(Constantes.P2, retorno.ifcdto.imovel.endereco.uf.toUpperCase());
			p.put(Constantes.P3, StringUtil.valorCorreto(retorno.valorRealDeposito));
			p.put(Constantes.P4, retorno.locatario.email);
			
			FilaSMSDTO smsdto = FilaSMSFactory.getDTO(retorno.locatario.telefone.ddd + retorno.locatario.telefone.telefone, 
				MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_AGUARDANDO_PGTO, p));

			FilaSMSFactory.enviarSMS(smsdto);

			// Envia outro e-mail para anunciante do imovel avisando da
			// negociacao em andamento.

			retorno.msgcode = MSGCODE.VALIDACAO_PRE_RESERVA_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(
					MSGCODE.VALIDACAO_PRE_RESERVA_REALIZADA_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}

	public ReservaDTO aprovarPreReservaTracker(String chave)
			throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Inscreve o novo assinante da campanha
			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			retorno = bo.aprovarPreReservaTracker(daoFactory, chave);
			daoFactory.commit();
			
			ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
			retorno.contatoCliente = cas.pesquisarRegistro(retorno.idContato);

			enviarEmailAguardandoDeposito(retorno);

			// Envia outro e-mail para anunciante do imovel avisando da
			// negociacao em andamento.

			retorno.msgcode = MSGCODE.VALIDACAO_PRE_RESERVA_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(
					MSGCODE.VALIDACAO_PRE_RESERVA_REALIZADA_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}

	private void enviarEmailAguardandoDeposito(ReservaDTO retorno)
			throws AlugueRelaxeException {
		// -----------------------------------------------------------------------
		// Envia e-mail para proposto avisando que estamos aguardando o deposito
		// -----------------------------------------------------------------------

		// executa algumas conversoes
		String strFormaPgto = (retorno.formaPagamento
				.equals(Constantes.FORMA_PGTO_PARCIAL) ? Constantes.FORMA_PGTO_PARCIAL_STR
				: Constantes.FORMA_PGTO_TOTAL_STR);
		String centralfones = VariavelCache.getInstance().getValor(
				VariavelConstantes.TELEFONES_CENTRAL_RESERVA);
		
		String linkReserva = VariavelCache.getInstance().getValor(
				VariavelConstantes.LINK_ACOMPANHAMENTO_WORKFLOW_PRE_RESERVA);
		linkReserva = StringUtil.replaceStringNew(linkReserva,
				"${hash}", retorno.idContato);
		linkReserva = StringUtil.replaceStringNew(linkReserva,
				"${omc}", retorno.contatoCliente.codigoOMCThreadVisitante);

		Map<String, String> conteudo = new HashMap<String, String>();

		try {
			conteudo.put(
					TemplateConstantes.TVPR_TAG_LINK_RESERVA,
					linkReserva);
			conteudo.put(
					TemplateConstantes.TAPPR_TAG_DATA_VALIDACAO_PRE_RESERVA,
					DateManagerBase.getDateManagerInstance(
							retorno.dataValidacaoPreReserva).getDateTimeFull());
			conteudo.put(TemplateConstantes.TAPPR_TAG_DATA_CHECKIN,
					DateManagerBase.getDateManagerInstance(retorno.dataCheckin)
							.getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TAPPR_TAG_DATA_CHECKOUT,
					DateManagerBase
							.getDateManagerInstance(retorno.dataCheckout)
							.getDateCustom("dd/MM/yyyy"));
			conteudo.put(TemplateConstantes.TAPPR_TAG_DATA_CADASTRO,
					DateManagerBase
							.getDateManagerInstance(retorno.dataCadastro)
							.getDateTimeFull());
			conteudo.put(
					TemplateConstantes.TAPPR_TAG_DATA_PREVISTA_DEPOSITO,
					DateManagerBase.getDateManagerInstance(
							retorno.dataPrevistaDeposito).getDateCustom(
							"dd/MM/yyyy"));
		} catch (ParseException e) {
			conteudo.put(
					TemplateConstantes.TAPPR_TAG_DATA_VALIDACAO_PRE_RESERVA,
					retorno.dataValidacaoPreReserva.toString());
			conteudo.put(TemplateConstantes.TAPPR_TAG_DATA_CHECKIN,
					retorno.dataCheckin.toString());
			conteudo.put(TemplateConstantes.TAPPR_TAG_DATA_CHECKOUT,
					retorno.dataCheckout.toString());
			conteudo.put(TemplateConstantes.TAPPR_TAG_DATA_CADASTRO,
					retorno.dataCadastro.toString());
			conteudo.put(TemplateConstantes.TAPPR_TAG_DATA_PREVISTA_DEPOSITO,
					retorno.dataPrevistaDeposito.toString());
		}
		conteudo.put(TemplateConstantes.TAPPR_TAG_CODIGO_RESERVA,
				String.valueOf(retorno.id));
		conteudo.put(TemplateConstantes.TAPPR_TAG_NOME_PROPOSTO,
				retorno.locatario.nome);
		conteudo.put(TemplateConstantes.TAPPR_TAG_CODIGO_IMOVEL,
				String.valueOf(retorno.ifcdto.imovel.id));
		conteudo.put(TemplateConstantes.TAPPR_TAG_TITULO_IMOVEL,
				retorno.ifcdto.imovel.descricaoTituloAnuncio);
		conteudo.put(TemplateConstantes.TAPPR_TAG_CODIGO_TRACKING,
				retorno.chaveTracker);
		conteudo.put(TemplateConstantes.TAPPR_TAG_VALOR_NEGOCIADO,
				String.valueOf(StringUtil
						.valorCorreto(retorno.valorAluguelNegociado + retorno.valorTaxaServico)));
		conteudo.put(TemplateConstantes.TAPPR_TAG_EMAIL_CONTATO,
				retorno.locatario.email);
		conteudo.put(TemplateConstantes.TAPPR_TAG_TOKEN, retorno.token);
		conteudo.put(TemplateConstantes.TAPPR_TAG_CPF_PROPOSTO,
				retorno.locatario.cpf);
		conteudo.put(TemplateConstantes.TAPPR_TAG_FORMA_DE_PAGAMENTO,
				strFormaPgto);
		conteudo.put(TemplateConstantes.TAPPR_TAG_VALOR_PREVISTO_DEPOSITO,
				String.valueOf(StringUtil
						.valorCorreto(retorno.valorPrevistoDeposito)));
		conteudo.put(TemplateConstantes.TAPPR_TAG_TELEFONES_CENTRAL_RESERVA,
				centralfones);
		conteudo.put(TemplateConstantes.TAPPR_TAG_VALOR_POR_EXTENSO,
				Extenso.valor(retorno.valorPrevistoDeposito));
		conteudo.put(TemplateConstantes.TAPPR_TAG_VALOR_CAUCAO,
				String.valueOf(StringUtil.valorCorreto(retorno.valorCaucao)));
		conteudo.put(TemplateConstantes.TAPPR_TAG_VALOR_CAUCAO_POR_EXTENSO,
				Extenso.valor(retorno.valorCaucao));
		conteudo.put(TemplateConstantes.TAPPR_TAG_VALOR_TOTAL_DEPOSITO,
				String.valueOf(StringUtil.valorCorreto(retorno.valorCaucao + retorno.valorPrevistoDeposito)));
		conteudo.put(TemplateConstantes.TAPPR_TAG_VALOR_TOTAL_DEPOSITO_POR_EXTENSO,
				Extenso.valor(retorno.valorCaucao + retorno.valorPrevistoDeposito));

		ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
		lst.add(new EmailRecord(retorno.locatario.email, retorno.locatario.nome));
		lst.add(new EmailRecord(VariavelCache.getInstance().getValor(
				VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE), "Reservas AR"));

		Email email = EmailFactory.getInstanceEmailPreReservaAprovada(conteudo);
		email.enviar(lst, null, "Aguardando pagamento de reserva - #"
				+ retorno.id, null);
	}

	public ReservaDTO reprovarPreReserva(String token)
			throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Inscreve o novo assinante da campanha
			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			retorno = bo.reprovarPreReserva(daoFactory, token);
			daoFactory.commit();

			Map<String, String> p = new HashMap<String, String>();
			p.put(Constantes.P1, retorno.token);
			retorno.msgcode = MSGCODE.REPROVAR_PRE_RESERVA_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(
					MSGCODE.REPROVAR_PRE_RESERVA_REALIZADA_SUCESSO, p);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}

	public ReservaDTO reprovarPreReservaTracker(String chave)
			throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Inscreve o novo assinante da campanha
			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			retorno = bo.reprovarPreReservaTracker(daoFactory, chave);
			daoFactory.commit();

			Map<String, String> p = new HashMap<String, String>();
			p.put(Constantes.P1, retorno.token);
			retorno.msgcode = MSGCODE.REPROVAR_PRE_RESERVA_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(
					MSGCODE.REPROVAR_PRE_RESERVA_REALIZADA_SUCESSO, p);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}

	/**
	 * <h2>criarPreReserva</h2>
	 * <p>
	 * Criar a pre-reserva de um imovel e iniciar o WF de comunicacao e
	 * aprovacao
	 * </p>
	 */
	public ReservaDTO criarPreReserva(ReservaDTO dto)
			throws AlugueRelaxeException {

		//----------------------------------------------
		// Obtem as informacoes atualizadas do imovel
		//----------------------------------------------
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		dto.ifcdto = is.pesquisarFichaCompletaImovel(dto.ifcdto.imovel.id);
		
		//----------------------------------------------------------------
		// Obtem as informacoes atualizadas do contato com o anunciante
		//----------------------------------------------------------------
		ContatoAnuncianteService cass = new ContatoAnuncianteServiceImpl();
		ContatoClienteDTO ccdto = cass.pesquisarRegistro(dto.idContato);
		dto.imgVisitante = "";
		if (ccdto != null){
			dto.imgVisitante = ccdto.imgVisitante;
		}

		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Inscreve o novo assinante da campanha
			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			retorno = bo.criarPreReserva(daoFactory, dto);
			daoFactory.commit();
			
			ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
			retorno.contatoCliente = cas.pesquisarRegistro(retorno.idContato);
			
			
			// Envia SMS ao ADministrador
			Map<String,String> param = new HashMap<String,String>();
			param.put(Constantes.P1, retorno.chaveTracker );
			param.put(Constantes.P2, retorno.contatoCliente.primeiroNome);
			param.put(Constantes.P3, dto.ifcdto.imovel.endereco.cidade);
			param.put(Constantes.P4, dto.ifcdto.imovel.endereco.uf);
			FilaSMSDTO fsmsdto = FilaSMSFactory.getDTO(	VariavelCache.getInstance().getValor(VariavelConstantes.CELULAR_ADMIN_GATEWAY),
															MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_APROVACAO_ADMIN, param)
														);
			FilaSMSFactory.enviarSMS(fsmsdto);
			
			/*
			FilaSMSDTO fsmsdto = new FilaSMSDTO();
			fsmsdto.celular = VariavelCache.getInstance().getValor(VariavelConstantes.CELULAR_ADMIN_GATEWAY);
			fsmsdto.msg = MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_APROVACAO_ADMIN, param);
			fsmsdto.modo = Constantes.SMS_MODO_ENVIO_GATEWAY;
			FilaSMSService fsmssvc = new FilaSMSServiceImpl();
			fsmssvc.gravarRegistro(fsmsdto);
			*/

			// Envia e-mail para proposto solicitando a confirmacao das
			// informacoes
			double vpagochave = (retorno.valorAluguelNegociado + retorno.valorTaxaServico)
					* (retorno.formaPagamento
							.equals(Constantes.FORMA_PGTO_PARCIAL) ? 0.5 : 0);

			String linkAprovar = VariavelCache.getInstance().getValor(
					VariavelConstantes.LINK_PRE_RESERVA);
			linkAprovar = StringUtil.replaceStringNew(linkAprovar, "${token}",
					dto.token);
			linkAprovar = StringUtil.replaceStringNew(linkAprovar, "${action}",
					Constantes.APROVAR);

			String linkReprovar = VariavelCache.getInstance().getValor(
					VariavelConstantes.LINK_PRE_RESERVA);
			linkReprovar = StringUtil.replaceStringNew(linkReprovar,
					"${token}", dto.token);
			linkReprovar = StringUtil.replaceStringNew(linkReprovar,
					"${action}", Constantes.REPROVAR);

			String linkReserva = VariavelCache.getInstance().getValor(
					VariavelConstantes.LINK_ACOMPANHAMENTO_WORKFLOW_PRE_RESERVA);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${hash}", dto.idContato);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${omc}", dto.contatoCliente.codigoOMCThreadVisitante);
			
			Map<String, String> conteudo = new HashMap<String, String>();
			try {
				conteudo.put(
						TemplateConstantes.TVPR_TAG_LINK_RESERVA,
						linkReserva);
				conteudo.put(
						TemplateConstantes.TVPR_TAG_DATA_CHECKIN,
						DateManagerBase.getDateManagerInstance(
								retorno.dataCheckin)
								.getDateCustom("dd/MM/yyyy"));
				conteudo.put(
						TemplateConstantes.TVPR_TAG_DATA_CHECKOUT,
						DateManagerBase.getDateManagerInstance(
								retorno.dataCheckout).getDateCustom(
								"dd/MM/yyyy"));
				conteudo.put(
						TemplateConstantes.TVPR_TAG_DATA_PREVISTA_DEPOSITO,
						DateManagerBase.getDateManagerInstance(
								retorno.dataPrevistaDeposito).getDateCustom(
								"dd/MM/yyyy"));
				conteudo.put(TemplateConstantes.TVPR_TAG_DATA_CADASTRO,
						DateManagerBase.getDateManagerInstance((new Date()))
								.getDateTimeFull());

			} catch (ParseException e) {
				conteudo.put(TemplateConstantes.TVPR_TAG_DATA_CHECKIN,
						retorno.dataCheckin.toString());
				conteudo.put(TemplateConstantes.TVPR_TAG_DATA_CHECKOUT,
						retorno.dataCheckout.toString());
				conteudo.put(
						TemplateConstantes.TVPR_TAG_DATA_PREVISTA_DEPOSITO,
						retorno.dataPrevistaDeposito.toString());
				conteudo.put(TemplateConstantes.TVPR_TAG_DATA_CADASTRO,
						(new Date()).toString());
			}
			conteudo.put(TemplateConstantes.TVPR_TAG_NOME_PROPOSTO,
					retorno.locatario.nome);
			conteudo.put(TemplateConstantes.TVPR_TAG_CPF_PROPOSTO,
					retorno.locatario.cpf);
			conteudo.put(TemplateConstantes.TVPR_TAG_CODIGO_TRACKING,
					retorno.chaveTracker);
			conteudo.put(TemplateConstantes.TVPR_TAG_CODIGO_IMOVEL,
					String.valueOf(retorno.ifcdto.imovel.id));
			conteudo.put(TemplateConstantes.TVPR_TAG_TITULO_IMOVEL,
					retorno.ifcdto.imovel.descricaoTituloAnuncio);
			conteudo.put(TemplateConstantes.TVPR_TAG_VALOR_NEGOCIADO, String
					.valueOf(StringUtil
							.valorCorreto(retorno.valorAluguelNegociado + retorno.valorTaxaServico)));
			conteudo.put(TemplateConstantes.TVPR_TAG_VALOR_PREVISTO_DEPOSITO,
					String.valueOf(StringUtil
							.valorCorreto(retorno.valorPrevistoDeposito)));
			conteudo.put(TemplateConstantes.TVPR_TAG_LINK_LIBERAR_RESERVA,
					linkAprovar);
			conteudo.put(TemplateConstantes.TVPR_TAG_LINK_REPROVAR_RESERVA,
					linkReprovar);
			conteudo.put(TemplateConstantes.TVPR_TAG_VALOR_PAGAR_NA_CHAVE,
					String.valueOf(StringUtil.valorCorreto(vpagochave)));
			conteudo.put(TemplateConstantes.TVPR_TAG_VALOR_CAUCAO, String
					.valueOf(StringUtil.valorCorreto(retorno.valorCaucao)));

			ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
			lst.add(new EmailRecord(VariavelCache.getInstance().getValor(
					VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE),
					"Administrador do AlugueRelaxe"));

			// Envia e-mail para AlugueRelaxe aprovar
			Email email = EmailFactory.getInstanceEmailPreReserva(conteudo);
			email.enviar(lst, null,
					"Solicitar de aprovar pre-reserva de temporada - #"
							+ retorno.id, null);
			
			// Envia e-mail para Visitante acompanhar o workflow
			ArrayList<EmailRecord> lstv = new ArrayList<EmailRecord>();
			lstv.add(new EmailRecord(retorno.contatoCliente.email,
					retorno.contatoCliente.proposto));
			Email emailv = EmailFactory.getInstanceEmailPreReservaVisitante(conteudo);
			emailv.enviar(lstv, null,
					"AlugueRelaxe - Pre-reserva de temporada - #"
							+ retorno.id + " aguardando aprovacao", null);
			
			
			retorno.msgcode = MSGCODE.PRE_RESERVA_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(
					MSGCODE.PRE_RESERVA_REALIZADA_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}

	public ReservaDTO gravarRegistro(ReservaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ReservaDTO excluirRegistro(ReservaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ReservaDTO atualizarRegistro(ReservaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ReservaDTO pesquisarRegistro(ReservaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<? extends ReservaDTO> listarRegistros()
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ReservaDTO procurarChaveTracker(String chave)
			throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Inscreve o novo assinante da campanha
			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			retorno = bo.procurarChaveTracker(daoFactory, chave);
			daoFactory.commit();

			// Obtem as informacoes atualizadas do imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			retorno.ifcdto = is
					.pesquisarFichaCompletaImovel(retorno.ifcdto.imovel.id);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}

	public ReservaDTO transferirDeposito(String chave)
			throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		ReservaDTO retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Localiza as informacoes da reserva para realizar a transferencia
			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			retorno = bo.procurarChaveTracker(daoFactory, chave);
			retorno = bo.transferirDeposito(daoFactory, retorno);

			daoFactory.commit();
			
			ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
			retorno.contatoCliente = cas.pesquisarRegistro(retorno.idContato);

			// Obtem as informacoes atualizadas do imovel
			ImovelService<ImovelDTO> is = new ImovelServiceImpl();
			retorno.ifcdto = is
					.pesquisarFichaCompletaImovel(retorno.ifcdto.imovel.id);

			//-------------------------------------------------------
			// Envia e-mail ao locador avisando do deposito realizado
			//-------------------------------------------------------
			String strFormaPgto = (retorno.formaPagamento
					.equals(Constantes.FORMA_PGTO_PARCIAL) ? Constantes.FORMA_PGTO_PARCIAL_STR
					: Constantes.FORMA_PGTO_TOTAL_STR);
			double vcomissao = retorno.valorAluguelNegociado
					* retorno.percentualComissao;
			double vliberar = retorno.valorRealDeposito - vcomissao;
			double vpagochave = (retorno.valorAluguelNegociado + retorno.valorTaxaServico)
					* (retorno.formaPagamento
							.equals(Constantes.FORMA_PGTO_PARCIAL) ? 0.5 : 0);
			String linkReserva = VariavelCache.getInstance().getValor(
					VariavelConstantes.LINK_ACOMPANHAMENTO_WORKFLOW_PRE_RESERVA);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${hash}", retorno.idContato);
			linkReserva = StringUtil.replaceStringNew(linkReserva,
					"${omc}", retorno.contatoCliente.codigoOMCThreadAnunciante);
			

			Map<String, String> conteudo = new HashMap<String, String>();
			conteudo.put(TemplateConstantes.TVPR_TAG_LINK_RESERVA,
					linkReserva);
			conteudo.put(TemplateConstantes.TETD_TAG_CODIGO_TRACKING,
					retorno.chaveTracker);
			conteudo.put(TemplateConstantes.TETD_TAG_NOME_BANCO,
					retorno.ifcdto.cliente.banco);
			conteudo.put(TemplateConstantes.TETD_TAG_NOME_AGENCIA,
					retorno.ifcdto.cliente.agencia);
			conteudo.put(TemplateConstantes.TETD_TAG_CONTA_CORRENTE,
					retorno.ifcdto.cliente.contacorrente);
			conteudo.put(TemplateConstantes.TETD_TAG_NOME_LOCADOR,
					retorno.ifcdto.cliente.nome);
			conteudo.put(TemplateConstantes.TETD_TAG_VALOR_NEGOCIADO_TEMPORADA,
					String.valueOf(StringUtil
							.valorCorreto(retorno.valorAluguelNegociado + retorno.valorTaxaServico)));
			conteudo.put(TemplateConstantes.TETD_TAG_FORMA_PGTO_ESCOLHIDA,
					strFormaPgto);
			conteudo.put(TemplateConstantes.TETD_TAG_VALOR_DEPOSITADO_CUSTODIA,
					String.valueOf(StringUtil
							.valorCorreto(retorno.valorRealDeposito)));
			conteudo.put(
					TemplateConstantes.TETD_TAG_VALOR_DEPOSITO_CAUCAO,
					String.valueOf(StringUtil.valorCorreto(retorno.valorCaucao)));
			conteudo.put(TemplateConstantes.TETD_TAG_VALOR_A_SER_PAGO_NA_CHAVE,
					String.valueOf(StringUtil.valorCorreto(vpagochave)));
			conteudo.put(
					TemplateConstantes.TETD_TAG_PERC_COMISSAO_ALUGUE_RELAXE,
					String.valueOf(Math.abs(retorno.percentualComissao * 100)));
			conteudo.put(
					TemplateConstantes.TETD_TAG_VALOR_COMISSAO_ALUGUE_RELAXE,
					String.valueOf(StringUtil.valorCorreto(vcomissao)));
			conteudo.put(TemplateConstantes.TETD_TAG_VALOR_A_SER_LIBERADO,
					String.valueOf(StringUtil.valorCorreto(vliberar)));
			try {
				conteudo.put(
						TemplateConstantes.TETD_TAG_DATA_TRANSFERENCIA_VALOR_A_SER_LIBERADO,
						DateManagerBase.getDateManagerInstance(
								retorno.dataTranferenciaDeposito).getDateTimeFull());
			} catch (ParseException e) {
				conteudo.put(
						TemplateConstantes.TETD_TAG_DATA_TRANSFERENCIA_VALOR_A_SER_LIBERADO,
						retorno.dataTranferenciaDeposito.toString());
			}


			ArrayList<EmailRecord> lst = new ArrayList<EmailRecord>();
			lst.add(new EmailRecord(VariavelCache.getInstance().getValor(
					VariavelConstantes.EMAIL_ADMIN_ALUGUERELAXE), "Reservas AR"));
			lst.add(new EmailRecord(retorno.ifcdto.cliente.email, retorno.ifcdto.cliente.nome));

			Email email = EmailFactory
					.getInstanceEmailDepositoTransferido(conteudo);
			email.enviar(lst, null, "Transferencia de deposito confirmada - #"
					+ retorno.id, null);

			//--------------------------------------------------------------------
			// Envia e-mail ao Locatario para relizar a avalicao do imovel
			// alugado
			//--------------------------------------------------------------------
			String linkAval = VariavelCache.getInstance().getValor(
					VariavelConstantes.LINK_AVALIACAO_RESERVA);
			linkAval = StringUtil.replaceStringNew(linkAval,
					"${hash}", retorno.idContato);
			linkAval = StringUtil.replaceStringNew(linkAval,
					"${omc}", retorno.contatoCliente.codigoOMCThreadVisitante);
			

			Map<String, String> conteudov = new HashMap<String, String>();
			conteudov.put(TemplateConstantes.TAGC_LINK_ACOMPANHAMENTO_PORTAL,
					linkAval);
			conteudov.put(TemplateConstantes.TAGC_NOME_VISITANTE,
					retorno.locatario.nome.toUpperCase());
			conteudov.put(TemplateConstantes.TAGC_TITULO_IMOVEL_VISITADO,
					retorno.ifcdto.imovel.descricaoTituloAnuncio);

			ArrayList<EmailRecord> lstv = new ArrayList<EmailRecord>();
			lstv.add(new EmailRecord(retorno.locatario.email, retorno.locatario.nome));

			Email emailv = EmailFactory
					.getInstanceEmailAvaliacaoReserva(conteudov);
			emailv.enviar(lstv, null, retorno.ifcdto.cliente.primeiroNome.toUpperCase() 
									+ " solicita sua avaliacao do imovel #"
									+ retorno.ifcdto.imovel.id 
									+ " em "
									+ retorno.ifcdto.imovel.endereco.cidade
									+ "/"
									+ retorno.ifcdto.imovel.endereco.uf, null);
			
			
			if (retorno != null) {
				Map<String, String> parametros = new HashMap<String, String>();
				parametros.put(Constantes.P1, retorno.chaveTracker);

				retorno.msgcode = MSGCODE.TRANSFERENCIA_DEPOSITO_REALIZADA_SUCESSO;
				retorno.msgcodeString = MensagemCache
						.getInstance()
						.getMensagem(
								MSGCODE.TRANSFERENCIA_DEPOSITO_REALIZADA_SUCESSO,
								parametros);
			}

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;

	}

	public List<ReservaDTO> listarTarefasPendentes(String fase)
			throws AlugueRelaxeException {
		DAOFactory daoFactory = null;
		List<ReservaDTO> lst = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Localiza as informacoes da reserva para realizar a transferencia
			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			lst = bo.listarTarefasPendentes(daoFactory, fase);

			daoFactory.commit();

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return lst;
	}


	public DTOPadrao criarComentario(AvaliacaoReservaDTO dto)
			throws AlugueRelaxeException {
		//------------------------------------------------------
		// Verifica se ja houve comentario nesta reserva
		//------------------------------------------------------
		
		ContatoAnuncianteService cas = new ContatoAnuncianteServiceImpl();
		dto.contatoCliente = cas.pesquisarRegistro(dto.reserva.idContato);
		
		ImovelService<ImovelDTO> is = new ImovelServiceImpl();
		dto.contatoCliente.ifcdto = is.pesquisarFichaCompletaImovel(dto.contatoCliente.idImovel);
			
		//------------------------------------------------------
		// Verifica se ja houve comentario nesta reserva
		//------------------------------------------------------
		ReservaDTO rvdto = this.pesquisarReserva(dto.contatoCliente.id);
		if (rvdto != null) {
			if (rvdto.dataAvaliacao != null){
				throw AlugueRelaxeException.getInstance(MSGCODE.COMENTARIO_JA_EMITIDO,
						MensagemCache.getInstance().getMensagem(MSGCODE.COMENTARIO_JA_EMITIDO),
						null);
			}
		}

		DAOFactory daoFactory = null;
		DTOPadrao retorno = null;

		try {
			daoFactory = DAOFactory.getDAOFactory();
			daoFactory.open();
			daoFactory.beginTransaction();

			// Atualizar o workflow da reserva com relacao ao comentario na reserva
			ReservaBusiness<ReservaDTO> bo = new ReservaBusinessImpl();
			bo.criarComentario(daoFactory, dto);
			
			// cria a thread do comentario para ser submetida a aprovacao
			ContatoAnuncianteThreadBusiness catb = new ContatoAnuncianteThreadBusinessImpl();
			ContatoAnuncianteThreadDTO catretdto = catb.incluirComentario(daoFactory, dto);

			daoFactory.commit();
			
			retorno = new DTOPadrao();
			retorno.msgcode = MSGCODE.AVALIACAO_IMOVEL_REALIZADA_SUCESSO;
			retorno.msgcodeString = MensagemCache.getInstance().getMensagem(
					MSGCODE.AVALIACAO_IMOVEL_REALIZADA_SUCESSO);

		} catch (HibernateException he) {
			logger.error(he.getMessage(), he);
			daoFactory.rollback();
			throw AlugueRelaxeException.getInstance(MSGCODE.ERRO_GENERICO_BD,
					he.getMessage(), null);

		} catch (AlugueRelaxeException he) {
			throw he;

		} finally {
			try {
				daoFactory.close();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new AlugueRelaxeException(e);
			}
		}

		return retorno;
	}
}
