package br.com.jcv.backend.imovel.imagem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.interfacesdao.ImovelImagemVideoDAO;

public class FirebirdImovelImagemVideoDAO implements ImovelImagemVideoDAO<ImovelImagemVideoDTO> {

	private static final String SQL_DEL_IMAGENS_MITB = "delete from imovel_imagem_video " +
	"where imov_cd_imovel = ? " +
	"and imiv_nm_imagem_video like ? " +
	"and (imiv_in_imagem_video = ? or imiv_in_imagem_video = ? or imiv_in_imagem_video = ? or imiv_in_imagem_video = ? or imiv_in_imagem_video = ?  )";

	private static final String SQL_BASICO = "select " +
	"IMIV_CD_IMOVEL_IMAGEM_VIDEO, " +
	"IMIV_NM_IMAGEM_VIDEO, " +
	"IMIV_IN_IMAGEM_VIDEO, " +
	"IMOV_CD_IMOVEL, " +
	"IMIV_DT_CADASTRO " +
	"from IMOVEL_IMAGEM_VIDEO " +
	"where IMIV_CD_IMOVEL_IMAGEM_VIDEO = ?";
	
	private static final String SQL_VIDEO_IMOVEL_RECENTE = "select " +
	"IMOVEL_IMAGEM_VIDEO.IMIV_CD_IMOVEL_IMAGEM_VIDEO, " +
	"IMOVEL_IMAGEM_VIDEO.IMIV_NM_IMAGEM_VIDEO, " +
	"IMOVEL_IMAGEM_VIDEO.IMIV_IN_IMAGEM_VIDEO " +
	"from IMOVEL_IMAGEM_VIDEO " +
	"where IMOVEL_IMAGEM_VIDEO.IMIV_CD_IMOVEL_IMAGEM_VIDEO = ( " +
	                                                            "select max(IMOVEL_IMAGEM_VIDEO.IMIV_CD_IMOVEL_IMAGEM_VIDEO) " +
	                                                            "from IMOVEL_IMAGEM_VIDEO " +
	                                                            "where IMOVEL_IMAGEM_VIDEO.IMOV_CD_IMOVEL = ? " +
	                                                            "and   IMOVEL_IMAGEM_VIDEO.IMIV_IN_IMAGEM_VIDEO = ? " +
	                                                            ")";
	
	private static final String SQL_LISTA_IMAGENS_IMOVEL_TIPO = "select " +
	   "IMOVEL_IMAGEM_VIDEO.IMIV_CD_IMOVEL_IMAGEM_VIDEO, " +
	   "IMOVEL_IMAGEM_VIDEO.IMIV_NM_IMAGEM_VIDEO, " +
	   "IMOVEL_IMAGEM_VIDEO.IMIV_IN_IMAGEM_VIDEO, " +
	   "IMOVEL_IMAGEM_VIDEO.IMIV_DT_CADASTRO " +
	   "from IMOVEL_IMAGEM_VIDEO " +
	   "where IMOVEL_IMAGEM_VIDEO.IMOV_CD_IMOVEL = ? " +
	   "and   IMOVEL_IMAGEM_VIDEO.IMIV_IN_IMAGEM_VIDEO = ?" ;
	
	private static final String SQL_INS_IMAGEM_IMOVEL = "INSERT INTO IMOVEL_IMAGEM_VIDEO " +
														"(" +
														"IMIV_CD_IMOVEL_IMAGEM_VIDEO," +
														"IMIV_NM_IMAGEM_VIDEO," +
														"IMIV_IN_IMAGEM_VIDEO," +
														"IMOV_CD_IMOVEL," +
														"IMIV_NR_ORDEM," +
														"IMIV_TX_HASH" +
														")" +
														"VALUES (?,?,?,?,?,?)";
	


	private DAOFactory daofactory = null;
	
	public FirebirdImovelImagemVideoDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(ImovelImagemVideoDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelImagemVideoDTO insert(ImovelImagemVideoDTO dto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_INS_IMAGEM_IMOVEL);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setString(i++, dto.nome);
		qry.setString(i++, dto.tipo);
		qry.setLong(i++, dto.imovel.id);
		qry.setLong(i++, dto.ordem);
		qry.setString(i++, dto.hash);
		qry.executeUpdate();		
		return dto;
	}

	public List<ImovelImagemVideoDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelImagemVideoDTO> list(int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelImagemVideoDTO load(ImovelImagemVideoDTO dto) 
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BASICO);
		qry.setLong(0, dto.id);
		List resultado = qry.list();
		ImovelImagemVideoDTO registro = null;
		if (resultado.size() > 0) {
			Object rdto[] = (Object[])resultado.get(0);
			
			int i = -1;
			registro = new ImovelImagemVideoDTO();
			registro.id = Long.valueOf(rdto[++i].toString());
			registro.nome = (rdto[++i] == null ? null : rdto[i].toString());
			registro.tipo = (rdto[++i] == null ? null : rdto[i].toString());
			registro.radical = registro.nome.substring(0, registro.nome.indexOf("-"));
			registro.imovel = new ImovelDTO();
			registro.imovel.id = Long.valueOf(rdto[++i].toString());
			registro.dataCadastro = (rdto[++i] == null ? null : (Timestamp) rdto[i]);

		}
		return registro;
	}

	public ImovelImagemVideoDTO update(ImovelImagemVideoDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelImagemVideoDTO> buscarListaImagens(long codigoImovel,
			String tipoImagem) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTA_IMAGENS_IMOVEL_TIPO);
		qry.setLong(0, codigoImovel);
		qry.setString(1, tipoImagem);
		List resultado = qry.list();
		List<ImovelImagemVideoDTO> lstImagens = null;
		if (resultado.size() > 0) {
			lstImagens = new ArrayList<ImovelImagemVideoDTO>();
			
			for (int j = 0; j < resultado.size(); j++) {
				Object dto[] = (Object[])resultado.get(j);
				
				int i = -1;
				ImovelImagemVideoDTO iivdto = new ImovelImagemVideoDTO();
				iivdto.id = Long.valueOf(dto[++i].toString());
				iivdto.nome = (dto[++i] == null ? null : dto[i].toString());
				iivdto.tipo = (dto[++i] == null ? null : dto[i].toString());
				iivdto.dataCadastro = (dto[++i] == null ? null : (Timestamp) dto[i]);

				lstImagens.add(iivdto);
			}
			
		}
		return lstImagens;

	}

	public void deleteImagensMITB(long codigoImovel, String radical)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_DEL_IMAGENS_MITB);
		int i = 0;
		qry.setLong(i++, codigoImovel);
		qry.setString(i++, radical.concat("%"));
		qry.setString(i++, "MI");
		qry.setString(i++, "TB");
		qry.setString(i++, "XG");
		qry.setString(i++, "PP");
		qry.setString(i++, "PD");
		qry.executeUpdate();	
	}

	public ImovelImagemVideoDTO loadVideoImovel(long codigoImovel)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_VIDEO_IMOVEL_RECENTE);
		qry.setLong(0, codigoImovel);
		qry.setString(1, Constantes.IND_TIPO_IMAGEM_VD);
		List resultado = qry.list();
		ImovelImagemVideoDTO registro = null;
		if (resultado.size() > 0) {
			Object rdto[] = (Object[])resultado.get(0);
			
			int i = -1;
			registro = new ImovelImagemVideoDTO();
			registro.id = Long.valueOf(rdto[++i].toString());
			registro.nome = (rdto[++i] == null ? null : rdto[i].toString());
			registro.tipo = (rdto[++i] == null ? null : rdto[i].toString());
			registro.imovel = new ImovelDTO();
			registro.imovel.id = codigoImovel;
		}
		return registro;
	}

}
