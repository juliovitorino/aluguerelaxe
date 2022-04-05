package br.com.jcv.backend.interfacesdao;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.painelcontrole.EstatisticaOrigemVisitaDTO;
import br.com.jcv.backend.painelcontrole.EstatisticaVisitasDTO;
import br.com.jcv.backend.painelcontrole.EstatisticaVisitasDiarioDTO;

public interface PainelControleDAO {
	
	long loadSomaImovelGeral() throws AlugueRelaxeException;
	long loadSomaImovelGeral(int ano, int mes, int dia) throws AlugueRelaxeException;
	long loadSomaImovelGeral(int ano, int mes) throws AlugueRelaxeException;
	long loadSomaImovelGeral(int ano) throws AlugueRelaxeException;

	long loadSomaVisitasGeral() throws AlugueRelaxeException;
	long loadSomaVisitasGeral(int ano, int mes, int dia) throws AlugueRelaxeException;
	long loadSomaVisitasGeral(int ano, int mes) throws AlugueRelaxeException;
	long loadSomaVisitasGeral(int ano) throws AlugueRelaxeException;
	
	long loadContatosPendentes() throws AlugueRelaxeException;
	long loadDepoimentosPendentes() throws AlugueRelaxeException;
	
	Date loadMaximaDataPublicidade(String tipo) throws AlugueRelaxeException;
	
	List<EstatisticaVisitasDTO> listEstatisticaVisitantes() throws AlugueRelaxeException;
	List<EstatisticaOrigemVisitaDTO> listEstatisticaOrigemVisita(int ano) throws AlugueRelaxeException;
	EstatisticaVisitasDiarioDTO loadEstatisticaVisitantesDiario(int ano, int mes) throws AlugueRelaxeException;
	
}
