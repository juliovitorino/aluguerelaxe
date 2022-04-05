package br.com.jcv.backend.interfaces.business;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.painelcontrole.EstatisticaOrigemVisitaDTO;
import br.com.jcv.backend.painelcontrole.EstatisticaVisitasDTO;
import br.com.jcv.backend.painelcontrole.EstatisticaVisitasDiarioDTO;
import br.com.jcv.backend.painelcontrole.IndicadorPerformanceEntradaImovelDTO;
import br.com.jcv.backend.painelcontrole.IndicadorPerformanceVisitasDTO;

public interface PainelControleBusiness {

	/**
	 *<h2>getIndPerfEntradaImovel</h2>
	 *<p>Obtem os indicadores de performance de cadastramento de imoveis</p>
	 */
	IndicadorPerformanceEntradaImovelDTO getIndPerfEntradaImovel(DAOFactory daofactory, int ano, int mes, int dia) throws AlugueRelaxeException;

	/**
	 *<h2>getIndPerfVisitas</h2>
	 *<p>Obtem os indicadores de performance de visitas as fichas de imovel</p>
	 */
	IndicadorPerformanceVisitasDTO getIndPerfVisitas(DAOFactory daofactory, int ano, int mes, int dia) throws AlugueRelaxeException;

	/**
	 *<h2>getContatoVisitantesPendentes</h2>
	 *<p>Obtem a qtde de contatos com visitantes pendentes de verificacao</p>
	 */
	 long getContatoVisitantesPendentes(DAOFactory daofactory) throws AlugueRelaxeException;

	/**
	 *<h2>getDepoimentosPendentes</h2>
	 *<p>Obtem a qtde de depoimentos com status pendente</p>
	 */
	long getDepoimentosPendentes(DAOFactory daofactory) throws AlugueRelaxeException;

	/**
	 *<h2>getMaximaDataPP</h2>
	 *<p>Obtem a maxima data das publicidades de primeira pagina</p>
	 */
	Date getMaximaDataPP(DAOFactory daofactory) throws AlugueRelaxeException;

	/**
	 *<h2>getMaximaDataPD</h2>
	 *<p>Obtem a maxima data das publicidades destaque</p>
	 */
	Date getMaximaDataPD(DAOFactory daofactory) throws AlugueRelaxeException;

	/**
	 *<h2>getEstatisticaVisitantes</h2>
	 *<p>Obtem a estatistica de visitas as fichas dos imoveis ano apos ano</p>
	 */
	List<EstatisticaVisitasDTO> getEstatisticaVisitantes(DAOFactory daofactory) throws AlugueRelaxeException;

	/**
	 *<h2>getEstatisticaOrigemVisita</h2>
	 *<p>Obtem a estatistica por origem de visitas as fichas dos imoveis de um determinado ano</p>
	 */
	List<EstatisticaOrigemVisitaDTO> getEstatisticaOrigemVisita(DAOFactory daofactory, int ano) throws AlugueRelaxeException;

	/**
	 *<h2>getEstatisticaVisitantesDiario</h2>
	 *<p>Obtem a estatistica de visitas as fichas dos imoveis dia a dia para um dado ano/mes</p>
	 */
	EstatisticaVisitasDiarioDTO getEstatisticaVisitantesDiario(DAOFactory daofactory, int ano, int mes) throws AlugueRelaxeException;

}
