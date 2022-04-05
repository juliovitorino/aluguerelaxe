package br.com.jcv.backend.cliente;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.constantes.MSGCODE;
import br.com.jcv.backend.criptografia.BaseFactorySecurity;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.factory.ValidadorFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.interfaces.business.ClienteBusiness;
import br.com.jcv.backend.interfacesdao.ClienteDAO;
import br.com.jcv.backend.interfacesdao.DAO;
import br.com.jcv.backend.mensagem.MensagemCache;
import br.com.jcv.backend.portal.NovoCodigoAcessoDTO;
import br.com.jcv.backend.portal.login.LoginDTO;
import br.com.jcv.backend.utility.StringUtil;
import br.com.jcv.backend.variavel.VariavelCache;
import br.com.jcv.backend.variavel.VariavelConstantes;

public class ClienteBusinessImpl implements ClienteBusiness<ClienteDTO> {
/**
	 * Atualizar as fotos do perfil do usuario
	 *
	 * @param daofactory
	 * @param long
	 * @param String
	 * @param int
	 * @throws AlugueRelaxeException 
	 */
	public void atualizarFotoPerfil(DAOFactory daofactory, long id, String arquivo, int tipoFoto) throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		switch(tipoFoto) {
			case Constantes.FOTO_CHAT:
				dao.updateFotoChat(id,arquivo);
				break;
			case Constantes.FOTO_PERFIL:
				dao.updateFotoPerfil(id,arquivo);
				break;
		}
	}
	
	/**
	 * Incrementa contador de perguntas do anunciante para estatística de respostas atendidas
	 *
	 * @param daofactory
	 * @param long
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	public void incrementarPerguntas(DAOFactory daofactory, long idCliente) throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		dao.incPerguntas(idCliente);
	}
	/**
	 * Incrementa contador de respostas do anunciante para estatística de respostas atendidas
	 *
	 * @param daofactory
	 * @param long
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	public void incrementarRespostas(DAOFactory daofactory, long idCliente) throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		dao.incRespostas(idCliente);
	}
	
	public ClienteDTO atualizar(DAOFactory daofactory, ClienteDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ClienteDTO> buscarTodas(DAOFactory daofactory)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ClienteDTO> buscarTodas(DAOFactory daofactory, int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Long> buscarTodas(DAOFactory daofactory, String status)
			throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		return dao.list(status);
	}

	public void excluir(DAOFactory daofactory, ClienteDTO dto)
			throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		dao.delete(dto);
	}

	public ClienteDTO getDados() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ClienteDTO incluir(DAOFactory daofactory, ClienteDTO dto)
			throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		dto.id = daofactory.getNextSequence(daofactory, "SEQ_CLIE_CD_CLIENTE");
		return dao.insert(dto);
	}

	public ClienteDTO procurar(DAOFactory daofactory, ClienteDTO dto)
			throws AlugueRelaxeException {
		DAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		ClienteDTO dtoretorno = dao.load(dto);
		if ((dtoretorno.fotoPerfil != null) && (dtoretorno.fotoPerfil.length() > 0)) {
			dtoretorno.pathPerfil = VariavelCache.getInstance().getValor(VariavelConstantes.URL_GALERIA_PERFIL_ANUNCIANTES); 
			dtoretorno.pathPerfil = StringUtil.replaceStringNew(dtoretorno.pathPerfil, "${clienteid}", String.valueOf(dtoretorno.id)).concat("/");
		} else {
			dtoretorno.pathPerfil = VariavelCache.getInstance().getValor(VariavelConstantes.URL_BASE_IMAGENS); 
			dtoretorno.fotoPerfil = VariavelCache.getInstance().getValor(VariavelConstantes.FOTO_NO_FACE); 
			dtoretorno.fotoChat = VariavelCache.getInstance().getValor(VariavelConstantes.FOTO_NO_FACE); 
		}

		dtoretorno.primeiroNome = StringUtil.getPrimeiroNome(dtoretorno.nome);
		dtoretorno.idhash = this.calculaHash(dtoretorno.id).toLowerCase();
		
		
		//------------------------------------------------------
		// calcula taxa de reposta
		//------------------------------------------------------
		if (dtoretorno.totalPergunta + dtoretorno.totalResposta == 0) {
			dtoretorno.taxaResposta = 0;
		} else if (dtoretorno.totalPergunta == dtoretorno.totalResposta) {
			dtoretorno.taxaResposta = 100;
		} else  {
			dtoretorno.taxaResposta = (int) (dtoretorno.totalResposta / dtoretorno.totalPergunta * 100);
		}
		return dtoretorno;
	}

	public void setDados(ClienteDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public void validarCamposObrigatorios(ClienteDTO dto)
			throws AlugueRelaxeException {
		List<String> lstErros = ValidadorFactory.getInstanceCliente().execute(dto);
		if ((lstErros != null) && (lstErros.size() > 0)) {
			throw new AlugueRelaxeException(MSGCODE.ERROS_VALIDACAO,
					MensagemCache.getInstance().getMensagem(MSGCODE.ERROS_VALIDACAO), 
					lstErros );
		}
	}

	public List<TelefoneDTO> obtemTelefonesCliente(DAOFactory daofactory,
			long idCliente) throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		return dao.obtemTelefonesCliente(idCliente);
	}

	public List<ClienteDTO> listarNovasContasPendentesAtivacao(
			DAOFactory daofactory) throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		return dao.listarNovasContasPendentesAtivacao();
	}

	public void ativarNovaConta(DAOFactory daofactory, String hash)
			throws AlugueRelaxeException {
			
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		ClienteDTO clientedto = dao.getStatus(hash);
		if (
				(clientedto != null) &&
				(!clientedto.indicadorStatus.equals(Constantes.CLIE_STATUS_PENDENTE))
			) {
				throw AlugueRelaxeException.getInstance(MSGCODE.CODIGO_DE_ATIVACAO_CANCELADO, 
						MensagemCache.getInstance().getMensagem(MSGCODE.CODIGO_DE_ATIVACAO_CANCELADO),
						null);
		}
		dao.ativarNovaConta(hash);
	}

	/**
	 * Método sobrescrito.
	 * @see br.com.jcv.backend.interfaces.business.ClienteBusiness#getStatus(br.com.jcv.backend.factory.DAOFactory, java.lang.String)
	 */
	public ClienteDTO getStatus(DAOFactory daofactory, String hash)
			throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		return dao.getStatus(hash);
	}

	/**
	 * Método sobrescrito.
	 * @see br.com.jcv.backend.interfaces.business.ClienteBusiness#validaContaUsuario(br.com.jcv.backend.factory.DAOFactory, br.com.jcv.backend.portal.login.LoginDTO)
	 */
	public ClienteDTO validaContaUsuario(DAOFactory daofactory, LoginDTO dto) throws AlugueRelaxeException {
		
		// Aplica algoritimo SHA1 na senha informada
		String senhaSHA1 = this.geraSHA1(dto.senha);

		// Valida a senha atual informada com a armazenada no BD
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		ClienteDTO clientedto = dao.load(dto);
		
		if (clientedto == null){
			throw AlugueRelaxeException.getInstance(MSGCODE.USUARIO_SENHA_INVALIDA, 
					MensagemCache.getInstance().getMensagem(MSGCODE.USUARIO_SENHA_INVALIDA),
					null);
		}

		if (! senhaSHA1.equals(clientedto.senha)) {
			// Antes de emitir o erro, se existe algum codigo de acesso emitido
			
			if((clientedto.codigoAcesso == null) || (clientedto.codigoAcesso.trim().length() == 0)){
					throw AlugueRelaxeException.getInstance(MSGCODE.USUARIO_SENHA_INVALIDA, 
							MensagemCache.getInstance().getMensagem(MSGCODE.USUARIO_SENHA_INVALIDA),
							null);
			} else {
				if (! senhaSHA1.equals(clientedto.codigoAcesso)) {
					throw AlugueRelaxeException.getInstance(MSGCODE.USUARIO_SENHA_INVALIDA, 
							MensagemCache.getInstance().getMensagem(MSGCODE.USUARIO_SENHA_INVALIDA),
							null);
				} else {
					dao.updateLimparCodigoAcesso(clientedto.email);
				}
			}
		}

		if (clientedto.indicadorStatus.equals(Constantes.CLIE_STATUS_PENDENTE))	{
			throw AlugueRelaxeException.getInstance(MSGCODE.FALTA_CONFIRMACAO_EMAIL, 
					MensagemCache.getInstance().getMensagem(MSGCODE.FALTA_CONFIRMACAO_EMAIL),
					null);
		}

		if (clientedto.indicadorStatus.equals(Constantes.CLIE_STATUS_BLOQUEADO))	{
			throw AlugueRelaxeException.getInstance(MSGCODE.CONTA_BLOQUEADA, 
					MensagemCache.getInstance().getMensagem(MSGCODE.CONTA_BLOQUEADA),
					null);
		}
		
		return clientedto;
		
		
	}

	public void atualizarFichaCadastral(DAOFactory daofactory,ClienteDTO dto) throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		dao.update(dto);
	}

	public void trocarSenha(DAOFactory daofactory, long idCliente,
			String novaSenha) throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		dao.updateSenha(idCliente, novaSenha);
	}
	
	public NovoCodigoAcessoDTO gerarNovoCodigoAcesso(DAOFactory daofactory, 
			NovoCodigoAcessoDTO ncadto) throws AlugueRelaxeException {
		
		//---------------------------------------------------------------------
		// Prepara DTO de retorno e Gera o novo codigo de acesso
		//---------------------------------------------------------------------
		NovoCodigoAcessoDTO retorno = new NovoCodigoAcessoDTO();
		retorno.email = ncadto.email;
		retorno.codigoAcesso = this.gerarCodigo(8);
		retorno.codigoHash = this.geraSHA1(retorno.codigoAcesso);

		//---------------------------------------------------------------------
		// Atualiza o registro e devolve codigo gerado
		//---------------------------------------------------------------------
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		dao.updateCodigoAcesso(retorno);
		
		return retorno;
	}
	
	private String gerarCodigo(int posicoes) {
		String alimentador = "AB56FGHIijklNOPQLMabcdeRSTrstuvwUVCDE34WXYZmnopqxyz012789JKfgh";
		StringBuilder novoCodigo = new StringBuilder(); 
		
		Random rndgen = new Random();
		for( int i = 0; i < posicoes; i++) {
			int randomInt = rndgen.nextInt(alimentador.length());
			novoCodigo.append(alimentador.substring(randomInt,randomInt+1));
		}
		return novoCodigo.toString();
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
	
	public List<ContatoClienteDTO> listarContatosAnunciante(DAOFactory daofactory, long idCliente) 
		throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		return dao.listarContatosAnunciante(idCliente);
	}

	public long contarImoveisAnunciante(DAOFactory daofactory, long idCliente, long idPlano)
			throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		return dao.countImoveisAnunciante(idCliente, idPlano);
	}

	public void atualizarEnqueteModoPublicidade(DAOFactory daofactory,
			ClienteDTO dto) throws AlugueRelaxeException {
		ClienteDAO<ClienteDTO> dao = daofactory.getClienteDAO(daofactory);
		dao.updateEnqueteModoPublicidade(dto);
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
	
}
