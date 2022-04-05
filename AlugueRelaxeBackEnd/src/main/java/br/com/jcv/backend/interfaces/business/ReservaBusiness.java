
package br.com.jcv.backend.interfaces.business;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.reserva.AvaliacaoReservaDTO;
import br.com.jcv.backend.reserva.ReservaDTO;

public interface ReservaBusiness<DTO> extends BusinessObject<DTO> {
	/**
	 * criarComentario - Criar o comentario sobre o imovel que foi reservado
	 * @param DAOFactory
	 * @param DTO
	 * @throws AlugueRelaxeException
	 */
	void criarComentario(DAOFactory daoFactory, AvaliacaoReservaDTO dto) throws AlugueRelaxeException;
	/**
	 * pesquisarReserva - Pesquisa por uma reserva usando o hash de contato do anunciante
	 * @param DAOFactory
	 * @param String
	 * @throws AlugueRelaxeException
	 */
	DTO pesquisarReserva(DAOFactory daoFactory, String hashContatoAnunciante) throws AlugueRelaxeException;
	/**
	 * criarPreReserva - Criar a pre-reserva de um imovel para temporada
	 * @param DAOFactory
	 * @param DTO
	 * @throws AlugueRelaxeException
	 */
	DTO criarPreReserva(DAOFactory daoFactory, DTO dto) throws AlugueRelaxeException;
	/**
	 * procurar - Procurar uma pre-reserva pelo token
	 * @param DAOFactory
	 * @param hash
	 * @throws AlugueRelaxeException
	 */
	DTO procurar(DAOFactory daofactory, String hash) throws AlugueRelaxeException;
	/**
	 * reprovarPreReserva - Reprovacao da pre-reserva
	 * @param DAOFactory
	 * @param token
	 * @throws AlugueRelaxeException
	 */
	DTO reprovarPreReserva(DAOFactory daofactory, String token) throws AlugueRelaxeException;
	/**
	 * aprovarPreReserva - Verificacao do AR e aprovacao da Pre-reserva
	 * @param DAOFactory
	 * @param token
	 * @throws AlugueRelaxeException
	 */
	DTO aprovarPreReserva(DAOFactory daofactory, String token) throws AlugueRelaxeException;
	/**
	 * aprovarPreReserva - Verificacao do AR e aprovacao da Pre-reserva
	 * @param DAOFactory
	 * @param token
	 * @param dataRealDeposito
	 * @param valorRealDeposito
	 * @throws AlugueRelaxeException
	 */
	DTO confirmarReserva(DAOFactory daoFactory, 
							String token, Date dataRealDeposito, 
							double valorRealDeposito) throws AlugueRelaxeException;
	/**
	 * isReservaReprovada - Retorna se a reserva foi reprovada
	 * @param DAOFactory
	 * @param token
	 * @throws AlugueRelaxeException
	 */
	boolean isReservaReprovada(DAOFactory daofactory, String token) throws AlugueRelaxeException;
	/**
	 * procurarChaveTracker - Procura uma reserva pelo codigo de rastreamento
	 * @param DAOFactory
	 * @param chave
	 * @throws AlugueRelaxeException
	 */
	DTO procurarChaveTracker(DAOFactory daofactory, String chave) throws AlugueRelaxeException ;
	/**
	 * validarReservaConfirmada - Valida uma reserva ja confirmada anteriormente
	 * @param token
	 * @throws AlugueRelaxeException
	 */
	boolean validarReservaConfirmada(DTO dto) throws AlugueRelaxeException ;
	/**
	 * solicitarLiberacaoDeposito - Registrar a solicitacao de liberacao do pagamento antecipado na reserva
	 * @param DAOFactory
	 * @param idClienteSolicitante
	 * @param voucher
	 * @throws AlugueRelaxeException
	 */
	DTO solicitarLiberacaoDeposito(DAOFactory daofactory, long idClienteSolicitante, String voucher, String tracking) throws AlugueRelaxeException;	
	/**
	 * validarLiberacaoConfirmada - Valida se o locador já solicitou uma liberacao de voucher
	 * @param token
	 * @throws AlugueRelaxeException
	 */
	boolean validarLiberacaoConfirmada(DTO dto) throws AlugueRelaxeException ;
	/**
	 * reprovarPreReservaTracker - Reprovacao da pre-reserva
	 * @param DAOFactory
	 * @param token
	 * @throws AlugueRelaxeException
	 */
	DTO reprovarPreReservaTracker(DAOFactory daofactory, String chave) throws AlugueRelaxeException;
	/**
	 * aprovarPreReservaTracker - Verificacao do AR e aprovacao da Pre-reserva
	 * @param DAOFactory
	 * @param token
	 * @throws AlugueRelaxeException
	 */
	DTO aprovarPreReservaTracker(DAOFactory daofactory, String chave) throws AlugueRelaxeException;
	/**
	 * transferirDeposito - Registrar a transferência do dinheiro para a conta do anunciante
	 * @param DAOFactory
	 * @param DTO
	 * @throws AlugueRelaxeException
	 */
	DTO transferirDeposito(DAOFactory daoFactory, DTO dto) throws AlugueRelaxeException;
	/**
	 * listarTarefasPendentes - Listar as tarefas pendentes de uma determinada fase am aberto
	 * @param DAOFactory
	 * @param String
	 * @throws AlugueRelaxeException
	 */
	List<ReservaDTO> listarTarefasPendentes(DAOFactory daoFactory, String fase) throws AlugueRelaxeException;

}