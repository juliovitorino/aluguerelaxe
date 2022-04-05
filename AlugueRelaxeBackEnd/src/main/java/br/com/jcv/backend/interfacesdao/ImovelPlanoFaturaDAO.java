package br.com.jcv.backend.interfacesdao;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;


public interface ImovelPlanoFaturaDAO<DTO> extends DAO<DTO> {
	/**
	* Localiza a ultima fatura de um determinado plano para o imovel
	* @param long - Codigo do imovel
	* @param String - Tipo do plano - pode ser: (N)ornal; (P)ublicidade Primeira Pagina; Publicidade (D)estaque e (E) Especial IMOBILIARIAS
	*/
	DTO loadUltimaFatura(long codigoImovel, String tipo) throws AlugueRelaxeException;
	DTO loadFatura(long idFatura) throws AlugueRelaxeException;
	List<Long> listPlanosPagosAVencer() throws AlugueRelaxeException ;
	List<Long> listPlanosPagosNaoVencidos() throws AlugueRelaxeException ;
	List<Long> listPlanosGratuitosAVencer() throws AlugueRelaxeException ;
	List<Long> listPlanosPagosVencidos() throws AlugueRelaxeException ;
	List<Long> listPlanosPendentesVencidos() throws AlugueRelaxeException ;
	List<Long> listPlanosGratuitosVencidos() throws AlugueRelaxeException ;
	void updateStatusFatura(long idFatura, String status) throws AlugueRelaxeException;
}
