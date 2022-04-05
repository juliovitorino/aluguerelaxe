package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;

/**
 * @author Julio
 * @version 1.0
 * @created 21-mar-2012
 */
public interface ImovelPlanoFaturaService<DTO> extends ApplicationService<DTO> {

	/**
	 * <h2>pesquisarUltimaFatura</h2>
	 * <p>Atualizar o status da fatura</p>
	 * @param long
	 * @param String
	 * @return DTOPadrao
	 * @throws AlugueRelaxeException
	 */
	DTOPadrao atualizarStatusFatura(long idFatura, String status) throws AlugueRelaxeException;
	
	/**
	 * <h2>pesquisarUltimaFatura</h2>
	 * <p>Método responsável por localizar o registro da ultima fatura de um imovel em determinado plano</p>
	 * @param long - Codigo do imovel
	 * @param String - Tipo do plano - pode ser: (N)ormal; (P)ublicidade Primeira Pagina; Publicidade (D)estaque e (E) Especial IMOBILIARIAS
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO pesquisarUltimaFatura(long codigoImovel, String tipo) throws AlugueRelaxeException;

	/**
	 * <h2>pesquisarUltimaFatura</h2>
	 * <p>Método responsável por localizar o registro da ultima fatura de um imovel em determinado plano</p>
	 * @param long - Codigo da fatura
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO pesquisarFatura(long idFatura) throws AlugueRelaxeException;
	
	/**
	 * <h2>listarPlanosPagosAVencer</h2>
	 * <p>Listar faturas de planos pagos de anunciantes quitadas a vencer anuncio dentro do 7 dias</p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarPlanosPagosAVencer() throws AlugueRelaxeException;

	/**
	 * <h2>listarPlanosPagosNaoVencidos</h2>
	 * <p>Listar faturas de planos pagos de anunciantes quitadas a vencer anuncio</p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarPlanosPagosNaoVencidos() throws AlugueRelaxeException;
	/**
	 * <h2>listarPlanosGratuitosAVencer</h2>
	 * <p>Listar faturas de planos pagos de anunciantes quitadas a vencer anuncio dentro do 7 dias</p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarPlanosGratuitosAVencer() throws AlugueRelaxeException;
	/**
	 * <h2>listarPlanosPendentesVencidos</h2>
	 * <p>Listar imoveis que tem faturas de anuncio pendentes mas que se encontram vencidas e nao pagas</p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarPlanosPendentesVencidos() throws AlugueRelaxeException;
	/**
	 * <h2>listarPlanosPagosVencidos</h2>
	 * <p>Listar imoveis que tem faturas de anuncio pagas mas que se encontram vencidas</p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarPlanosPagosVencidos() throws AlugueRelaxeException;
	/**
	 * <h2>listarPlanosGratuitosVencidos</h2>
	 * <p>Listar imoveis que tem faturas de anuncio gratuito mas que se encontram vencidas</p>
	 * @return List
	 * @throws AlugueRelaxeException
	 */
	List<Long> listarPlanosGratuitosVencidos() throws AlugueRelaxeException;
	/**
	 * <h2>renovarPlano</h2>
	 * <p>Renovar o plano de anuncio de um determinado imovel</p>
	 * @param long
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO renovarPlano(long idImovel) throws AlugueRelaxeException;
	/**
	 * <h2>registrarPagtoFatura</h2>
	 * <p>Registrar o pagamento de fatura do plano de anuncio de um determinado imovel</p>
	 * @param long
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO registrarPagtoFatura(long idImovel) throws AlugueRelaxeException;
	/**
	 * <h2>registrarPagtoPlano</h2>
	 * <p>Registrar o pagamento de fatura do plano de anuncio</p>
	 * @param long
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO registrarPagtoPlano(long idFatura) throws AlugueRelaxeException;
	/**
	 * <h2>registrarPagtoPublicidade</h2>
	 * <p>Registrar o pagamento de fatura do plano de anuncio</p>
	 * @param long
	 * @return DTO
	 * @throws AlugueRelaxeException
	 */
	DTO registrarPagtoPublicidade(long idFatura) throws AlugueRelaxeException;
	/**
	 * <h2>notificaVencimentoAnuncio</h2>
	 * <p>Enviar email de notificacao do vencimento do anuncio</p>
	 * @param ImovelFichaCompletaDTO
	 * @param DTO
	 * @return void
	 * @throws AlugueRelaxeException
	 */
	void notificaVencimentoAnuncio(ImovelFichaCompletaDTO ifcdto, DTO ipfdto) throws AlugueRelaxeException;
	/**
	 * <h2>notificaPlanoAnuncioBloqueado</h2>
	 * <p>Enviar email de notificacao do bloqueio do anuncio</p>
	 * @param ImovelFichaCompletaDTO
	 * @param DTO
	 * @return void
	 * @throws AlugueRelaxeException
	 */
	void notificaPlanoAnuncioBloqueado(ImovelFichaCompletaDTO ifcdto, DTO ipfdto) throws AlugueRelaxeException;

	/**
	 * @param ipfdto
	 * @throws AlugueRelaxeException
	 */
	void isAnuncioPendenteNaoVencido(ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException;

	/**
	 * @param ipfdto
	 * @throws AlugueRelaxeException
	 */
	void isAnuncioPendenteVencido(ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException;	

	/**
	 * @param ipfdto
	 * @throws AlugueRelaxeException
	 */
	void isAnuncioLiberadoVencido(ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException;

	/**
	 * @param ipfdto
	 * @throws AlugueRelaxeException
	 */
	void isPlanoGratuito(ImovelPlanoFaturaDTO ipfdto) throws AlugueRelaxeException;
}