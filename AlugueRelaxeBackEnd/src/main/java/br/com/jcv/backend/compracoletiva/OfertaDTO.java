package br.com.jcv.backend.compracoletiva;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import br.com.jcv.backend.dto.DTOPadrao;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;

public class OfertaDTO extends DTOPadrao implements Serializable {

	private static final long serialVersionUID = -888328997354603907L;
	
	public long id;
	public ImovelFichaCompletaDTO ifcdto;
	public String titulo;
	public String status;
	public List<OfertaItemDTO> lstItem;
	public List<OfertaRegulamentoDTO> lstRegra;
	public Timestamp dataInicio;
	public Timestamp dataFim;
	public Timestamp dataCadastro;
}
