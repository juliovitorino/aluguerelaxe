package br.com.jcv.backend.compracoletiva;

import java.util.List;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.OfertaDAO;

public class FirebirdOfertaDAO implements OfertaDAO {
	private static final String INS_BASICO = "insert into OFERTA(" +
												"OFER_ID, " +
												"IMOV_CD_IMOVEL, " +
												"OFER_TX_TITULO, " +
												"OFER_IN_STATUS, " +
												"OFER_DT_INICIO, " +
												"OFER_DT_FIM, " +
												") values (?,?,?,?,?,?)";
	
	private static final String SQL_BASICO =	"select " +
												"OFER_ID, " +
												"IMOV_CD_IMOVEL, " +
												"OFER_TX_TITULO, " +
												"OFER_IN_STATUS, " +
												"OFER_DT_INICIO, " +
												"OFER_DT_FIM, " +
												"OFER_DT_CADASTRO " ;

	private static final String SQL_BUSCA_PK = SQL_BASICO +
		"from OFERTA " +
		"where OFER_ID = ?";
		
	private static final String SQL_FULL_STATUS = SQL_BASICO +
	"from OFERTA " +
	"where OFER_IN_STATUS = ?";		

	private DAOFactory daofactory = null;
	
	public FirebirdOfertaDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public OfertaDTO insert(OfertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public OfertaDTO update(OfertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(OfertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public OfertaDTO load(OfertaDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OfertaDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OfertaDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	private OfertaDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		OfertaDTO dto = new OfertaDTO();
		/*
		dto.ifcdto = new ImovelFichaCompletaDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.tipoAlerta.id = Long.valueOf(registro[++j].toString());
		dto.ifcdto.imovel.id = Long.valueOf(registro[++j].toString());
		dto.contato.id = (registro[++j] == null ? null : registro[j].toString());
		dto.emitido = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		*/
		return dto;
	}

}
