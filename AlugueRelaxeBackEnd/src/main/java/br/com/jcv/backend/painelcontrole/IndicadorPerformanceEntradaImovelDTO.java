package br.com.jcv.backend.painelcontrole;

import java.io.Serializable;

import br.com.jcv.backend.dto.DTOPadrao;

public class IndicadorPerformanceEntradaImovelDTO extends DTOPadrao implements
		Serializable {

	private static final long serialVersionUID = -8603205766150083093L;
	
	public long totalGeral;
	public long totalAnual;
	public long totalMensal;
	public long totalDiario;

}
