package br.com.jcv.backend.painelcontrole;

import java.util.Date;
import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfaces.business.PainelControleBusiness;
import br.com.jcv.backend.interfacesdao.PainelControleDAO;

public class PainelControleBusinessImpl implements PainelControleBusiness {

	public IndicadorPerformanceEntradaImovelDTO getIndPerfEntradaImovel(
			DAOFactory daofactory, int ano, int mes, int dia) throws AlugueRelaxeException {

		IndicadorPerformanceEntradaImovelDTO retorno = new IndicadorPerformanceEntradaImovelDTO();
		
		// Busca cada entrada de forma independente
		long ipeiGeral = obterIndPerfEntradaImovel(daofactory);
		long ipeiAMD = obterIndPerfEntradaImovel(daofactory, ano, mes, dia);
		long ipeiAM = obterIndPerfEntradaImovel(daofactory, ano, mes);
		long ipeiA = obterIndPerfEntradaImovel(daofactory, ano);
		
		// monta o resultado
		retorno.totalGeral = ipeiGeral;
		retorno.totalAnual = ipeiA;
		retorno.totalMensal = ipeiAM;
		retorno.totalDiario = ipeiAMD;
		
		// devolve o resultado
		return retorno;
	}

	public IndicadorPerformanceVisitasDTO getIndPerfVisitas(DAOFactory daofactory, int ano, int mes,
			int dia) throws AlugueRelaxeException {
		IndicadorPerformanceVisitasDTO retorno = new IndicadorPerformanceVisitasDTO();
		
		// Busca cada entrada de forma independente
		long ipeiGeral = obterIndPerfVisitasImovel(daofactory);
		long ipeiAMD = obterIndPerfVisitasImovel(daofactory, ano, mes, dia);
		long ipeiAM = obterIndPerfVisitasImovel(daofactory, ano, mes);
		long ipeiA = obterIndPerfVisitasImovel(daofactory, ano);
		
		// monta o resultado
		retorno.totalGeral = ipeiGeral;
		retorno.totalAnual = ipeiA;
		retorno.totalMensal = ipeiAM;
		retorno.totalDiario = ipeiAMD;
		
		// devolve o resultado
		return retorno;
	}
	
	public long getContatoVisitantesPendentes(DAOFactory daofactory) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadContatosPendentes();
	}
	
	public long getDepoimentosPendentes(DAOFactory daofactory) throws AlugueRelaxeException{
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadDepoimentosPendentes();
	}

	public Date getMaximaDataPP(DAOFactory daofactory) throws AlugueRelaxeException{
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadMaximaDataPublicidade("PP");
	}

	public Date getMaximaDataPD(DAOFactory daofactory) throws AlugueRelaxeException{
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadMaximaDataPublicidade("PD");
	}

	public List<EstatisticaVisitasDTO> getEstatisticaVisitantes(DAOFactory daofactory) throws AlugueRelaxeException{
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.listEstatisticaVisitantes();
	}

	public List<EstatisticaOrigemVisitaDTO> getEstatisticaOrigemVisita(DAOFactory daofactory, int ano) throws AlugueRelaxeException{
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.listEstatisticaOrigemVisita(ano);
	}
	
	public EstatisticaVisitasDiarioDTO getEstatisticaVisitantesDiario(DAOFactory daofactory, int ano, int mes) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadEstatisticaVisitantesDiario(ano, mes);
	}


/* Metodos privados de apoio a classe */	

	private long obterIndPerfEntradaImovel(DAOFactory daofactory, int ano) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadSomaImovelGeral(ano);
	}


	private long obterIndPerfEntradaImovel(DAOFactory daofactory, int ano, int mes) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadSomaImovelGeral(ano, mes);
	}


	private long obterIndPerfEntradaImovel(DAOFactory daofactory, int ano, int mes, int dia) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadSomaImovelGeral(ano, mes, dia);
	}


	private long obterIndPerfEntradaImovel(DAOFactory daofactory) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadSomaImovelGeral();
	}

	private long obterIndPerfVisitasImovel(DAOFactory daofactory, int ano) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadSomaVisitasGeral(ano);
	}


	private long obterIndPerfVisitasImovel(DAOFactory daofactory, int ano, int mes) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadSomaVisitasGeral(ano, mes);
	}


	private long obterIndPerfVisitasImovel(DAOFactory daofactory, int ano, int mes, int dia) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadSomaVisitasGeral(ano, mes, dia);
	}


	private long obterIndPerfVisitasImovel(DAOFactory daofactory) throws AlugueRelaxeException {
		PainelControleDAO pcdao = daofactory.getPainelControleDAO(daofactory);
		return pcdao.loadSomaVisitasGeral();
	}



	
	

}
