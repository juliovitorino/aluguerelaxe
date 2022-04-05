package br.com.jcv.backend.interfaces.business;

import java.util.List;

import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.TelefoneDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ContatoClienteDTO;
import br.com.jcv.backend.portal.NovoCodigoAcessoDTO;
import br.com.jcv.backend.portal.login.LoginDTO;


/**
 * <h1>ClienteBusiness</h1>
 * <p>Interface responsável por contratos que a Implementação de 
 * negócio de cliente deve realizar.
 * </p>
 * @author julio
 * @version 1.0
 */
public interface ClienteBusiness<DTO> extends BusinessObject<DTO> {

	public static final String TIPO_ANUNCIANTE_PROPRIETARIO = "P";
	public static final String TIPO_ANUNCIANTE_IMOBILIARIA = "I";
	public static final String TIPO_ANUNCIANTE_PATROCINADOR = "C";
	public static final String TIPO_ANUNCIANTE_T = "T";
	
	public static final String CONTA_STATUS_PENDENTE = "P";
	public static final String CONTA_STATUS_ATIVA = "A";
	public static final String CONTA_STATUS_BLOQUEADA = "B";
	public static final String CONTA_STATUS_INATIVA = "I";
	public static final String CONTA_STATUS_VERIFICAR = "V";
	public static final String CONTA_STATUS_REPROVADO = "R";
	
	/**
	 * Obter a lista de telefones de um determinado cliente.
	 *
	 * @param daofactory
	 * @param idCliente
	 * @return
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	List<TelefoneDTO> obtemTelefonesCliente(DAOFactory daofactory, long idCliente) throws AlugueRelaxeException;
	/**
	 * Listar todas as contas novas que estão pendentes de ativação para 
	 * envio de e-mail.
	 *
	 * @param daofactory
	 * @return List
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	List<DTO> listarNovasContasPendentesAtivacao(DAOFactory daofactory) throws AlugueRelaxeException; 
	/**
	 * Ativa a nova conta do cliente
	 *
	 * @param daofactory
	 * @param hash
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	void ativarNovaConta(DAOFactory daofactory, String hash) throws AlugueRelaxeException;
	/**
	 * Obtem um DTO de cliente com base no hash de ativação
	 *
	 * @param daofactory
	 * @param hash
	 * @return
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	DTO getStatus(DAOFactory daofactory, String hash) throws AlugueRelaxeException;
	/**
	 * Validar a conta do usuario atraves do seu e-mail e senha
	 *
	 * @param daofactory
	 * @param dto
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	ClienteDTO validaContaUsuario(DAOFactory daofactory, LoginDTO dto) throws AlugueRelaxeException;
	/**
	 * Trocar a senha do cliente
	 *
	 * @param daofactory
	 * @param dto
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	void trocarSenha(DAOFactory daofactory, long idCliente, String novaSenha) throws AlugueRelaxeException;
	
	/**
	 * Atualiza a ficha de cadastro do cliente
	 *
	 * @param daofactory
	 * @param dto
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	void atualizarFichaCadastral(DAOFactory daofactory, ClienteDTO dto) throws AlugueRelaxeException; 
	
	/**
	 * Gera um novo codigo de acesso alternativo para o cliente
	 *
	 * @param daofactory
	 * @param dto
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	NovoCodigoAcessoDTO gerarNovoCodigoAcesso(DAOFactory daofactory, NovoCodigoAcessoDTO ncadto) throws AlugueRelaxeException;
	
	/**
	 * Listar todos as solicitacoes de contatos realizados dos clientes com nossos anunciantes
	 *
	 * @param daofactory
	 * @param idCliente
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	List<ContatoClienteDTO> listarContatosAnunciante(DAOFactory daofactory, long idCliente) throws AlugueRelaxeException;

	/**
	 * Contar quantos imoveis um cliente tem em um determinado plano
	 *
	 * @param daofactory
	 * @param idCliente
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	long contarImoveisAnunciante(DAOFactory daofactory, long idCliente, long idPlano) throws AlugueRelaxeException;
	
	/**
	 * Atualizar a informacao por qual modo o cliente conheceu o AlugueRelaxe
	 *
	 * @param daofactory
	 * @param idCliente
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	void atualizarEnqueteModoPublicidade(DAOFactory daofactory, ClienteDTO dto) throws AlugueRelaxeException;
	/**
	 * Buscar todos os IDs de clientes determinados pelo Status
	 *
	 * @param daofactory
	 * @param status
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	public List<Long> buscarTodas(DAOFactory daofactory, String status) throws AlugueRelaxeException;
	/**
	 * Incrementa contador de perguntas do anunciante para estatística de respostas atendidas
	 *
	 * @param daofactory
	 * @param long
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	public void incrementarPerguntas(DAOFactory daofactory, long idCliente) throws AlugueRelaxeException;
	/**
	 * Incrementa contador de respostas do anunciante para estatística de respostas atendidas
	 *
	 * @param daofactory
	 * @param long
	 * @throws AlugueRelaxeException Comentar aqui.
	 */
	public void incrementarRespostas(DAOFactory daofactory, long idCliente) throws AlugueRelaxeException;
	/**
	 * Atualizar as fotos do perfil do usuario
	 *
	 * @param daofactory
	 * @param long
	 * @param String
	 * @param int
	 * @throws AlugueRelaxeException 
	 */
	public void atualizarFotoPerfil(DAOFactory daoFactory, long id, String arquivo, int tipoFoto) throws AlugueRelaxeException;

}
