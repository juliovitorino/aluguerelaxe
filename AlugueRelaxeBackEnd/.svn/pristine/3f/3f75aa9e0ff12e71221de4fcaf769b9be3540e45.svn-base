package br.com.jcv.backend.interfaces.services;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.painelcontrole.EstatisticaOrigemVisitaDTO;
import br.com.jcv.backend.painelcontrole.EstatisticaVisitasDTO;
import br.com.jcv.backend.painelcontrole.EstatisticaVisitasDiarioDTO;
import br.com.jcv.backend.painelcontrole.IndicadorPerformanceEntradaImovelDTO;
import br.com.jcv.backend.painelcontrole.IndicadorPerformanceVisitasDTO;
import br.com.jcv.backend.painelcontrole.ModeracaoDTO;

public interface PainelControleService {
	
	/**
	 *<h2>getIndPerfEntradaImovel</h2>
	 *<p>Obtem os indicadores de performance de cadastramento de imoveis</p>
	 */
	IndicadorPerformanceEntradaImovelDTO getIndPerfEntradaImovel(int ano, int mes, int dia) throws AlugueRelaxeException;

	/**
	 *<h2>getIndPerfVisitas</h2>
	 *<p>Obtem os indicadores de performance de visitas as fichas de imovel</p>
	 */
	IndicadorPerformanceVisitasDTO getIndPerfVisitas(int ano, int mes, int dia) throws AlugueRelaxeException;

	/**
	 *<h2>getPendenciaModeracao</h2>
	 *<p>Obtem as situacoes de pendencias de aprovacao</p>
	 */
	ModeracaoDTO getPendenciaModeracao() throws AlugueRelaxeException;

	/**
	 *<h2>getEstatisticaVisitantes</h2>
	 *<p>Obtem a estatistica de visitas as fichas dos imoveis ano apos ano</p>
	 */
	List<EstatisticaVisitasDTO> getEstatisticaVisitantes() throws AlugueRelaxeException;

	/**
	 *<h2>getEstatisticaVisitantesDiario</h2>
	 *<p>Obtem a estatistica de visitas as fichas dos imoveis dia a dia para um dado ano/mes</p>
	 */
	EstatisticaVisitasDiarioDTO getEstatisticaVisitantesDiario(int ano, int mes) throws AlugueRelaxeException;

	/**
	 *<h2>getEstatisticaOrigemVisita</h2>
	 *<p>Obtem a estatistica por origem de visitas as fichas dos imoveis de um determinado ano</p>
	 */
	List<EstatisticaOrigemVisitaDTO> getEstatisticaOrigemVisita(int ano) throws AlugueRelaxeException;

}
