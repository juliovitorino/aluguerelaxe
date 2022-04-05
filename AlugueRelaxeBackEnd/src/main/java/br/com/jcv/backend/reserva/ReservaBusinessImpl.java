package br.com.jcv.backend.reserva;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.Logger;

import br.com.jcv.backend.cliente.ClienteBusinessImpl;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.contatoanunciante.ContatoAnuncianteBusinessImpl;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.datemanager.DateManagerBase;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.imovel.ImovelBusinessImpl;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.interfaces.business.ClienteBusiness;
import br.com.jcv.backend.interfaces.business.ContatoAnuncianteBusiness;
import br.com.jcv.backend.interfaces.business.ImovelBusiness;
import br.com.jcv.backend.interfaces.business.ReservaBusiness;
import br.com.jcv.backend.interfacesdao.LocatarioDAO;
import br.com.jcv.backend.interfacesdao.ReservaDAO;
import br.com.jcv.backend.locatario.LocatarioDTO;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;
 
/**
 * <h1>ReservaBusinessImpl</h1> 
 * <p>Objetivo desta classe e gerenciar os metodos de reserva de imovel de temporada
 *
 * @author Julio Vitorino
 * @version 1.10
 * @since 11 Jan 2012
 */
public class ReservaBusinessImpl implements ReservaBusiness<ReservaDTO>{
	/**
	 * <h2>Atributo logger</h2>
	 * <p>Logger para instancica corrente de <code>ReservaBusinessImpl</code></p>
	 */
	private static Logger logger = Logger.getLogger(ReservaBusinessImpl.class.getName());
	
	public static final int FASE_PRE_RESERVA = 0;
	public static final int FASE_APROVACAO = 1;
	public static final int FASE_CONFIRMACAO_DEPOSITO = 2;
	public static final int FASE_LIBERACAO = 3;
	public static final int FASE_TRANSFERENCIA = 4;
	public static final int FASE_AVALIACAO = 5;
    
	public ReservaBusinessImpl() {
		
	}

	/**
	 * <h2>isReservaReprovada</h2>
	 * <p>Reserva encontra-se cancelada</p>
	 */
	public boolean isReservaReprovada(DAOFactory daofactory, String token)
			throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);

		return (dao.loadToken(token).dataReprovacao == null ? false : true);
	}


	/**
	 * <h2>confirmarReserva</h2>
	 * <p>Confirma a reserva atraves das datas e valor de pagamentos realmente realizados</p>
	 */
	public ReservaDTO confirmarReserva(DAOFactory daoFactory, String token,
			Date dataRealDeposito, double valorRealDeposito) throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daoFactory.getReservaDAO(daoFactory);

		validarReservaReprovada(daoFactory, token);
		validarReservaCancelada(daoFactory, token);
		validarFaseAnteriorConcluida(daoFactory, token, FASE_CONFIRMACAO_DEPOSITO);
		
		ReservaDTO dto = dao.loadChaveTracker(token);

		// Verifica se a data do pagto ja foi validada anteriormente
		if (dto.dataRealDeposito == null){
			
			// monta a URL completa para contrato do locador e locatario
			dto.urlContratoLocador = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_CONTRATO_LOCATARIO) + "lctr-" + dto.chaveTracker + "-" + dto.token + ".html";
			dto.urlContratoLocatario = VariavelCache.getInstance().getValor(VariavelConstantes.LINK_CONTRATO_LOCATARIO) + "ancnt-" + dto.chaveTracker + "-" + dto.token + ".html";
			
			// Lanca a data pgto e valor efetivados no deposito
			dao.updatePagamentoReserva(token, dataRealDeposito, valorRealDeposito, dto.urlContratoLocador, dto.urlContratoLocatario);
			
			// Recarrega registro com informacoes atualizadas
			dto = dao.loadChaveTracker(token);
			
			// Busca o locatario associado a esta reserva
			LocatarioDAO<LocatarioDTO> ldao = daoFactory.getLocatarioDAO(daoFactory);
			dto.locatario = ldao.load(dto.locatario.id);

		} else {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, token);
			
			throw new AlugueRelaxeException(MSGCODE.PAGAMENTO_JA_FOI_VALIDADO,
					MensagemCache.getInstance().getMensagem(MSGCODE.PAGAMENTO_JA_FOI_VALIDADO, parametros), 
					null );
		}
		return dto;
	}

	/**
	 * <h2>aprovarPreReserva</h2>
	 * <p>Administrador do AR aprova a pre-reserva com um token valido</p>
	 */
	public ReservaDTO aprovarPreReserva(DAOFactory daofactory, String token)
			throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);

		ReservaDTO dto = dao.loadToken(token);
		
		// Verifica se pre-reserva foi cancelada
		if (dto.dataReprovacao != null) {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, token);
			parametros.put(Constantes.P2, dto.dataReprovacao.toString());
			
			throw new AlugueRelaxeException(MSGCODE.RESERVA_FOI_REPROVADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.RESERVA_FOI_REPROVADA, parametros), 
					null );
		}		

		// Verifica se a reserva ja foi validada anteriormente
		if (dto.dataValidacaoPreReserva == null){
			
			// Aprova a pre-reserva e recarrega a reserva com dados atualizados
			dao.updateAprovacaoPreReserva(token);
			dto = dao.loadToken(token);
			
			// Busca o locatario associado a esta reserva
			LocatarioDAO<LocatarioDTO> ldao = daofactory.getLocatarioDAO(daofactory);
			dto.locatario = ldao.load(dto.locatario.id);

		} else {
			throw new AlugueRelaxeException(MSGCODE.PRE_RESERVA_JA_FOI_VALIDADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_JA_FOI_VALIDADA), 
					null );
		}
		return dto;
	}
	/**
	 * <h2>aprovarPreReserva</h2>
	 * <p>Administrador do AR aprova a pre-reserva com um token valido</p>
	 */
	public ReservaDTO aprovarPreReservaTracker(DAOFactory daofactory, String chave)
			throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);

		ReservaDTO dto = dao.loadChaveTracker(chave);
		
		validarReservaReprovada(daofactory, chave);
		validarFaseAnteriorConcluida(daofactory, chave, FASE_APROVACAO);

		// Verifica se a reserva ja foi validada anteriormente
		if (dto.dataValidacaoPreReserva == null){
			
			// Aprova a pre-reserva e recarrega a reserva com dados atualizados
			dao.updateAprovacaoPreReservaTracker(chave);
			dto = dao.loadChaveTracker(chave);
			
			// Busca o locatario associado a esta reserva
			LocatarioDAO<LocatarioDTO> ldao = daofactory.getLocatarioDAO(daofactory);
			dto.locatario = ldao.load(dto.locatario.id);

		} else {
			throw new AlugueRelaxeException(MSGCODE.PRE_RESERVA_JA_FOI_VALIDADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_JA_FOI_VALIDADA), 
					null );
		}
		return dto;
	}


	/**
	 * <h2>reprovarPreReserva</h2>
	 * <p>Administrador do AR reprova a pre-reserva com um token valido</p>
	 */
	public ReservaDTO reprovarPreReserva(DAOFactory daofactory, String token)
			throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);

		ReservaDTO dto = dao.loadToken(token);
		
		// Verifica se pre-reserva foi cancelada
		if (dto.dataReprovacao != null) {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, token);
			parametros.put(Constantes.P2, dto.dataReprovacao.toString());
			
			throw new AlugueRelaxeException(MSGCODE.RESERVA_FOI_REPROVADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.RESERVA_FOI_REPROVADA, parametros), 
					null );
		}		

		// Verifica se a reserva ja foi validada anteriormente
		if (dto.dataValidacaoPreReserva == null){
			
			// Reprova a pre-reserva e recarrega a reserva com dados atualizados
			dao.updateReprovacaoPreReserva(token);
			dto = dao.loadToken(token);
			
			// Busca o locatario associado a esta reserva
			LocatarioDAO<LocatarioDTO> ldao = daofactory.getLocatarioDAO(daofactory);
			dto.locatario = ldao.load(dto.locatario.id);

		} else {
			throw new AlugueRelaxeException(MSGCODE.PRE_RESERVA_JA_FOI_VALIDADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_JA_FOI_VALIDADA), 
					null );
		}
		return dto;
	}


	/**
	 * <h2>reprovarPreReserva</h2>
	 * <p>Administrador do AR reprova a pre-reserva com um token valido</p>
	 */
	public ReservaDTO reprovarPreReservaTracker(DAOFactory daofactory, String chave)
			throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);

		ReservaDTO dto = dao.loadChaveTracker(chave);
		
		validarReservaReprovada(daofactory, chave);
		validarFaseAnteriorConcluida(daofactory, chave, FASE_APROVACAO);
		
		// Verifica se a reserva ja foi validada anteriormente
		if (dto.dataValidacaoPreReserva == null){
			
			// Reprova a pre-reserva e recarrega a reserva com dados atualizados
			dao.updateReprovacaoPreReservaTracker(chave);
			dto = dao.loadChaveTracker(chave);
			
			// Busca o locatario associado a esta reserva
			LocatarioDAO<LocatarioDTO> ldao = daofactory.getLocatarioDAO(daofactory);
			dto.locatario = ldao.load(dto.locatario.id);

		} else {
			throw new AlugueRelaxeException(MSGCODE.PRE_RESERVA_JA_FOI_VALIDADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.PRE_RESERVA_JA_FOI_VALIDADA), 
					null );
		}
		return dto;
	}

	/**
	 * <h2>criarPreReserva</h2>
	 * <p>Cria a pre-reserva e aplica algumas regras e validacoes</p>
	 */
	public ReservaDTO criarPreReserva(DAOFactory daoFactory, ReservaDTO dto) throws AlugueRelaxeException{
	
		// Validar os campos
		//this.validarCamposObrigatorios(dto);
		
		List<String> lstErros = ValidadorFactory.getInstanceCriarPreReserva().execute(dto);
		if ((lstErros != null) && (lstErros.size() > 0)) {
			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
					lstErros );
		}
		
		// Cria o locatario
		LocatarioDAO<LocatarioDTO> ldao = daoFactory.getLocatarioDAO(daoFactory);
		
		//LocatarioDTO ldto = ldao.load(dto.locatario);
		//if (ldto == null){
			// Obtem novo sequence
			dto.locatario.id = daoFactory.getNextSequence(daoFactory, "SEQ_LOTE_CD_CLIENTE");
			ldao.insert(dto.locatario);
		//} else {
		//	dto.locatario.id = ldto.id;
		//}
		
		// Obtem novo sequence
		dto.id = daoFactory.getNextSequence(daoFactory, "SEQ_RESE_CD_RESERVA");
		
		Date dthash = new Date();
		
		// Calcula Hash
		dto.token = this.geraSHA1(	String.valueOf(dto.id) +
									dto.locatario.nome +
									dto.locatario.email +
									dto.locatario.cpf +
									dto.dataCheckin.toString() +
									dto.dataCheckout.toString() +
									dthash.toString()
								);
		
		// Gera o codigo que sera usado para liberacao do deposito antecipado
		dto.chaveLiberacaoDeposito = StringUtil.gerarCodigo(10);
		
		// Verifica o percentual de comissao
		if (dto.percentualComissao <= 0){
			dto.percentualComissao = Double.valueOf(VariavelCache.getInstance().getValor(VariavelConstantes.PERC_COMISSAO_PADRAO_TEMPORADA));
		}

		ReservaDAO<ReservaDTO> dao = daoFactory.getReservaDAO(daoFactory);
		
		// Gera o codigo que sera usado para tracking da reserva do locador e locatario
		boolean continua = true;
		while (continua){
			dto.chaveTracker = StringUtil.gerarCodigo(10);
			ReservaDTO dtoct = dao.loadChaveTracker(dto.chaveTracker);
			
			if (dtoct == null) {
				continua = false;
			}
		}
									
		ReservaDTO dtoretorno = dao.insert(dto);
		
		// Atualiza a thread do contato com anunciante para "I"
		// deixando-a somente para consulta
		ContatoAnuncianteBusiness cab = new ContatoAnuncianteBusinessImpl();
		cab.atualizarStatusThread(daoFactory, dtoretorno.idContato, Constantes.INATIVO);
		
		return dtoretorno;
	}
	
	public void validarCamposObrigatorios(ReservaDTO dto)
			throws AlugueRelaxeException {
//		List<String> lstErros = ValidadorFactory.getInstanceReserva().execute(dto);
//		if ((lstErros != null) && (lstErros.size() > 0)) {
//			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
//					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
//					lstErros );
//		}
	}

	public ReservaDTO incluir(DAOFactory daofactory, ReservaDTO dto)
			throws AlugueRelaxeException {
			return null;
	}

	public ReservaDTO procurar(DAOFactory daofactory, ReservaDTO dto)
			throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);
		return dao.load(dto);
	}

	public ReservaDTO procurar(DAOFactory daofactory, String hash)
			throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);
		return dao.loadToken(hash);
	}

	public void setDados(ReservaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ReservaDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ReservaDTO atualizar(DAOFactory daofactory, ReservaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void excluir(DAOFactory daofactory, ReservaDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public List<ReservaDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ReservaDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	private String geraSHA1(String str) {
		// Aplica algoritimo SHA1 na string informada
		String strsha1 = "";
		BaseFactorySecurity bfs = BaseFactorySecurity.getInstance(BaseFactorySecurity.SHA1);
		try {
			strsha1 = bfs.criptografar(str);
		} catch (InvalidKeyException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Tratar exceção.
			e.printStackTrace();
		}	
		return strsha1;
	}

	public ReservaDTO procurarChaveTracker(DAOFactory daofactory, String chave)
			throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);
		LocatarioDAO<LocatarioDTO> daoloca = daofactory.getLocatarioDAO(daofactory);
		ReservaDTO dto = dao.loadChaveTracker(chave);
		if (dto != null){
			dto.locatario = daoloca.load(dto.locatario.id);
		}
		return dto;
	}

	public boolean validarReservaConfirmada(ReservaDTO dto)
			throws AlugueRelaxeException {
		boolean retorno = false;

		// Verifica se pre-reserva ja tem seu pagamento realizado
		if (dto.dataRealDeposito != null) {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, dto.dataRealDeposito.toString());
			
			throw new AlugueRelaxeException(MSGCODE.RESERVA_JA_ESTA_CONFIRMADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.RESERVA_JA_ESTA_CONFIRMADA, parametros), 
					null );
		}
		
		return retorno;
	}

	public ReservaDTO solicitarLiberacaoDeposito(DAOFactory daofactory, long idClienteSolicitante,
			String voucher, String tracking) throws AlugueRelaxeException {
		
		validarClienteSolicitante(daofactory,idClienteSolicitante,voucher, tracking);
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);
		dao.updateLiberacaoDeposito(tracking, voucher);
		
		return this.procurarChaveTracker(daofactory, tracking);
	}

	private void validarClienteSolicitante(DAOFactory daofactory, long idClienteSolicitante,
			String voucher, String tracking) throws AlugueRelaxeException {
		
		boolean sucesso = true;
		
		ReservaDTO dto = this.procurarChaveTracker(daofactory, tracking);
		if (dto == null){
			throw new AlugueRelaxeException(MSGCODE.CODIGO_TRACKING_INVALIDO,
					MensagemCache.getInstance().getMensagem(MSGCODE.CODIGO_TRACKING_INVALIDO), 
					null );
		}
		//-----------------------------------------------------
		// Se a fase de pagamento do deposito nao foi realizada,
		// Nao existe liberacao
		//-----------------------------------------------------
		sucesso = (dto.dataRealDeposito != null);
		if (! sucesso) {
			throw new AlugueRelaxeException(MSGCODE.PAGAMENTO_RESERVA_NAO_CONFIRMADO,
					MensagemCache.getInstance().getMensagem(MSGCODE.PAGAMENTO_RESERVA_NAO_CONFIRMADO), 
					null );
		}
		
		//-----------------------------------------------------
		// Se a data atual for inferior a data de checkin
		// Nao pode permitir a liberacao do pagamento
		//-----------------------------------------------------
		sucesso = (System.currentTimeMillis() >= dto.dataCheckin.getTime());
		if (! sucesso) {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, dto.dataCheckin.toString());

			throw new AlugueRelaxeException(MSGCODE.LIBERACAO_IMPEDIDA_ANTES_DATA_CHECKIN,
					MensagemCache.getInstance().getMensagem(MSGCODE.LIBERACAO_IMPEDIDA_ANTES_DATA_CHECKIN, parametros), 
					null );
		}
		
		//-----------------------------------------------------
		// Localiza a reserva pelo número do voucher para obter a ficha do imovel completa
		// e verificar se o idClienteSolicitante eh o mesmo proprietario do imovel
		//-----------------------------------------------------
		ImovelBusiness<ImovelDTO> bo = new ImovelBusinessImpl();
		ImovelDTO imoveldto = bo.procurar(daofactory, dto.ifcdto.imovel);
		
		sucesso = (idClienteSolicitante == imoveldto.idCliente);
		if (! sucesso) {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, String.valueOf(idClienteSolicitante));
			
			throw new AlugueRelaxeException(MSGCODE.SOLICITANTE_DE_LIBERACAO_INVALIDO,
					MensagemCache.getInstance().getMensagem(MSGCODE.SOLICITANTE_DE_LIBERACAO_INVALIDO, parametros), 
					null );
		}
		//-----------------------------------------------------
		// Verifica se o voucher informado bate com o armazenado
		// na reserva do imovel
		//-----------------------------------------------------
		sucesso = (voucher.equals(dto.chaveLiberacaoDeposito));
		if (! sucesso) {
			throw new AlugueRelaxeException(MSGCODE.VOUCHER_INVALIDO,
					MensagemCache.getInstance().getMensagem(MSGCODE.VOUCHER_INVALIDO), 
					null );
		}

		//-----------------------------------------------------
		// Verifica se as informacoes bancarias do dono
		// do imovel estao preenchidas
		//-----------------------------------------------------
		ClienteBusiness<ClienteDTO> cb = new ClienteBusinessImpl();
		ClienteDTO clientedto = new ClienteDTO();
		clientedto.id = imoveldto.idCliente;
		clientedto = cb.procurar(daofactory, clientedto);
		
		if ((clientedto.banco == null) || (clientedto.banco.length() == 0)){
			sucesso = false;
		} else if ((clientedto.agencia == null) || (clientedto.agencia.length() == 0)) {
			sucesso = false;
		} else if ((clientedto.contacorrente == null) || (clientedto.contacorrente.length() == 0)) {
			sucesso = false;
		}
		if (! sucesso) {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, clientedto.nome);
			
			throw new AlugueRelaxeException(MSGCODE.FALTANDO_INFORMACOES_BANCARIAS,
					MensagemCache.getInstance().getMensagem(MSGCODE.FALTANDO_INFORMACOES_BANCARIAS, parametros), 
					null );
		}

	}

	public boolean validarLiberacaoConfirmada(ReservaDTO dto)
			throws AlugueRelaxeException {
		boolean retorno = false;

		// Verifica se ja houve solicitacao da liberacao
		if (dto.dataChaveLiberacaoCheck != null) {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, dto.chaveLiberacaoDeposito);
			parametros.put(Constantes.P2, dto.dataChaveLiberacaoCheck.toString());
			
			throw new AlugueRelaxeException(MSGCODE.LIBERACAO_DE_PAGAMENTO_JA_FOI_SOLICITADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.LIBERACAO_DE_PAGAMENTO_JA_FOI_SOLICITADA, parametros), 
					null );
		}
		
		return retorno;
	}
	
	private void validarReservaReprovada(DAOFactory daofactory, String tracking) throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);
		ReservaDTO dto = dao.loadChaveTracker(tracking);
		
		if ((dto != null) && (dto.dataReprovacao != null))  {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, tracking);
			parametros.put(Constantes.P2, dto.dataReprovacao.toString());
			
			throw new AlugueRelaxeException(MSGCODE.RESERVA_FOI_REPROVADA,
					MensagemCache.getInstance().getMensagem(MSGCODE.RESERVA_FOI_REPROVADA, parametros), 
					null );

		}
	}

	private void validarReservaCancelada(DAOFactory daofactory, String tracking) throws AlugueRelaxeException {
		
	}

	private void validarFaseAnteriorConcluida(DAOFactory daofactory, String tracking, int faseAtual) throws AlugueRelaxeException {
		// carrega a reserva
		ReservaDAO<ReservaDTO> dao = daofactory.getReservaDAO(daofactory);
		ReservaDTO dto = dao.loadChaveTracker(tracking);
		
		// Realiza a verificacao da fase anterior em relacao a fase atual
		switch(faseAtual){
		case FASE_APROVACAO:
			if (dto.dataCadastro == null) {
				throw new AlugueRelaxeException(MSGCODE.FASE_PRE_RESERVA_NAO_CONCLUIDA,
						MensagemCache.getInstance().getMensagem(MSGCODE.FASE_PRE_RESERVA_NAO_CONCLUIDA, null), 
						null );
			}
			break;
		case FASE_CONFIRMACAO_DEPOSITO:
			if (dto.dataValidacaoPreReserva == null) {
				throw new AlugueRelaxeException(MSGCODE.FASE_APROVACAO_NAO_CONCLUIDA,
						MensagemCache.getInstance().getMensagem(MSGCODE.FASE_APROVACAO_NAO_CONCLUIDA, null), 
						null );
			}
			break;
		case FASE_LIBERACAO:
			if (dto.dataRealDeposito == null) {
				throw new AlugueRelaxeException(MSGCODE.FASE_CONFIRMACAO_DEPOSITO_NAO_CONCLUIDA,
						MensagemCache.getInstance().getMensagem(MSGCODE.FASE_CONFIRMACAO_DEPOSITO_NAO_CONCLUIDA, null), 
						null );
			}
			break;
		case FASE_TRANSFERENCIA:
			if (dto.dataChaveLiberacaoCheck == null) {
				throw new AlugueRelaxeException(MSGCODE.FASE_LIBERACAO_NAO_CONCLUIDA,
						MensagemCache.getInstance().getMensagem(MSGCODE.FASE_LIBERACAO_NAO_CONCLUIDA, null), 
						null );
			}
			break;
		case FASE_AVALIACAO:
			if (dto.dataTranferenciaDeposito == null) {
				throw new AlugueRelaxeException(MSGCODE.FASE_TRANSFERENCIA_NAO_CONCLUIDA,
						MensagemCache.getInstance().getMensagem(MSGCODE.FASE_TRANSFERENCIA_NAO_CONCLUIDA, null), 
						null );
			}
			break;
		default:
		
		}
	}

	public ReservaDTO transferirDeposito(DAOFactory daoFactory, ReservaDTO dto)
			throws AlugueRelaxeException {
		
		ReservaDTO retorno = null;
		ReservaDAO<ReservaDTO> dao = daoFactory.getReservaDAO(daoFactory);

		validarReservaReprovada(daoFactory, dto.chaveTracker);
		validarReservaCancelada(daoFactory, dto.chaveTracker);
		validarFaseAnteriorConcluida(daoFactory, dto.chaveTracker, FASE_TRANSFERENCIA);

		// Verifica se a data do transferencia ja foi validada anteriormente
		if (dto.dataTranferenciaDeposito == null){
			
			// Calcula do valor da comissao
			double vlComissao = dto.valorAluguelNegociado * dto.percentualComissao;
			
			// Calcula o deposito a ser realizado
			double vlTrans = dto.valorRealDeposito - vlComissao;
			
			// Lanca a data pgto e valor efetivados no deposito
			dao.updateTransferenciaDeposito(dto.id, vlTrans);

			// Atualiza o dto com informacoes novas de registro de transferencia
			retorno = dao.loadChaveTracker(dto.chaveTracker);
			
			
			// Busca o locatario associado a esta reserva
			LocatarioDAO<LocatarioDTO> ldao = daoFactory.getLocatarioDAO(daoFactory);
			retorno.locatario = ldao.load(dto.locatario.id);

		} else {
			Map<String,String> parametros = new HashMap<String,String>();
			parametros.put(Constantes.P1, dto.chaveTracker);
			try {
				parametros.put(Constantes.P2, DateManagerBase.getDateManagerInstance(dto.dataTranferenciaDeposito).getDateTimeFull());
			} catch (ParseException e) {
				parametros.put(Constantes.P2, dto.dataTranferenciaDeposito.toString());
			}
			
			throw new AlugueRelaxeException(MSGCODE.TRANSFERENCIA_DEPOSITO_JA_FOI_REALIZADO,
					MensagemCache.getInstance().getMensagem(MSGCODE.TRANSFERENCIA_DEPOSITO_JA_FOI_REALIZADO, parametros), 
					null );
		}
		
		return retorno;
	}

	public List<ReservaDTO> listarTarefasPendentes(DAOFactory daoFactory,
			String fase) throws AlugueRelaxeException {
		
		int faseParse = -1;
		if (fase.equals("AP")) {
			faseParse = FASE_APROVACAO; 
		} else if (fase.equals("CD")) {
			faseParse = FASE_CONFIRMACAO_DEPOSITO; 
		} else if (fase.equals("LD")) {
			faseParse = FASE_LIBERACAO; 
		} else if (fase.equals("TD")) {
			faseParse = FASE_TRANSFERENCIA; 
		} else if (fase.equals("AV")) {
			faseParse = FASE_AVALIACAO; 
		} 
		
		ReservaDAO<ReservaDTO> dao = daoFactory.getReservaDAO(daoFactory);
		List<ReservaDTO> lst = dao.listTarefasPendentes(faseParse);
		
		// Busca o locatario associado a esta reserva
		if (lst != null){
			for (ReservaDTO dto : lst){
				LocatarioDAO<LocatarioDTO> ldao = daoFactory.getLocatarioDAO(daoFactory);
				dto.locatario = ldao.load(dto.locatario.id);
			}
		}
		
		return lst; 
	}

	public ReservaDTO pesquisarReserva(DAOFactory daoFactory,
			String hashContatoAnunciante) throws AlugueRelaxeException {
		ReservaDAO<ReservaDTO> dao = daoFactory.getReservaDAO(daoFactory);
		return dao.loadReserva(hashContatoAnunciante);
	}

	public void criarComentario(DAOFactory daoFactory, AvaliacaoReservaDTO dto)
			throws AlugueRelaxeException {
			
		// Realiza a validacao das notas
		if (dto.reserva.notaImovel < 1) {
			throw new AlugueRelaxeException(MSGCODE.NECESSARIO_AVALIAR_NOTA_IMOVEL,
					MensagemCache.getInstance().getMensagem(MSGCODE.NECESSARIO_AVALIAR_NOTA_IMOVEL), 
					null );
					
		}
		
		if (dto.reserva.notaAnfitriao < 1) {
			throw new AlugueRelaxeException(MSGCODE.NECESSARIO_AVALIAR_NOTA_ANFITRIAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.NECESSARIO_AVALIAR_NOTA_ANFITRIAO), 
					null );
					
		}
		
		// Atualiza informacoes do comentario na tabela de reservas
		ReservaDAO<ReservaDTO> dao = daoFactory.getReservaDAO(daoFactory);
		dao.updateComentario(dto);
		
	}
	
	
}
