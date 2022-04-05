package br.com.jcv.backend.local;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.LocalDAO;

public class FirebirdLocalDAO implements LocalDAO<LocalItemDTO> {
	
	
	private static final String SQL_BASICO_LOCAL = 	"select " +
	"LOCA_CD_LOCAL, " +
	"LOCA_TX_LOCAL, " +
	"LOCA_DT_CADASTRO, " +
	"LOCA_TX_URL_IMAGEM " +
	"from LOCAL";
	
	private static final String SQL_BASICO_CLASSIFICACAO = 	"select " +
	"CLAS_CD_CLASSIFICACAO, " +
	"CLAS_TX_CLASSIFICACAO, " +
	"CLAS_DT_CADASTRO " +
	"from CLASSIFICACAO ";
	
	
	private static final String SQL_BASICO =	"select " +
	"a.LOUC_CD_LOCAL_UF_CIDADE_ITEM, " +
    "a.LOCA_CD_LOCAL, " +
    "b.LOCA_TX_LOCAL, " +
    "a.UFCI_CD_UF_CIDADE_ITEM, " +
    "c.UF_CD_ESTADO, " +
    "d.CIDA_NM_CIDADE, " +
    "f.CLAS_TX_CLASSIFICACAO, " +
    "a.LOUC_TX_DESCRICAO, " +
    "a.LOUC_NR_LATITUDE, " +
    "a.LOUC_NR_LONGITUDE, " +
    "a.LOUC_TX_URL_ADICIONAL_1, " +
    "a.LOUC_TX_URL_ADICIONAL_2, " +
    "a.LOUC_TX_URL_ADICIONAL_3, " +
    "a.LOUC_TX_URL_ADICIONAL_4, " +
    "a.LOUC_TX_URL_ADICIONAL_5, " +
    "a.LOUC_DT_CADASTRO, " +
    "b.LOCA_TX_URL_IMAGEM, " +
    "a.LOUC_TX_URL_IMAGEM ";
	
	private static final String SQL_PLANO_LOCAL_POR_CIDADE = SQL_BASICO +
	"from LOCAL_UF_CIDADE_ITEM a  " +
	"        inner join LOCAL b on b.LOCA_CD_LOCAL = a.LOCA_CD_LOCAL " +
	"        inner join uf_cidade_item c on c.ufci_cd_uf_cidade_item = a.ufci_cd_uf_cidade_item " +
	"        inner join cidade d on c.cida_cd_cidade = d.cida_cd_cidade " +
	"        inner join local_classificacao_item e on e.loca_cd_local = b.loca_cd_local " +
	"        inner join classificacao f on f.clas_cd_classificacao = e.clas_cd_classificacao " +
	"where     a.UFCI_CD_UF_CIDADE_ITEM = ? " +
	"and     a.LOCA_CD_LOCAL in (";

	private static final String SQL_PLANO_CLASSIFICACAO_POR_CIDADE = SQL_BASICO +
	"from LOCAL_UF_CIDADE_ITEM a  " +
	"        inner join LOCAL b on b.LOCA_CD_LOCAL = a.LOCA_CD_LOCAL " +
	"        inner join uf_cidade_item c on c.ufci_cd_uf_cidade_item = a.ufci_cd_uf_cidade_item " +
	"        inner join cidade d on c.cida_cd_cidade = d.cida_cd_cidade " +
	"        inner join local_classificacao_item e on e.loca_cd_local = b.loca_cd_local " +
	"        inner join classificacao f on f.clas_cd_classificacao = e.clas_cd_classificacao " +
	"where     a.UFCI_CD_UF_CIDADE_ITEM = ? " +
	"and     e.clas_cd_classificacao in (";

	private DAOFactory daofactory = null;
	
	public FirebirdLocalDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public LocalItemDTO insert(LocalItemDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalItemDTO update(LocalItemDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(LocalItemDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub

	}

	public LocalItemDTO load(LocalItemDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocalItemDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocalItemDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LocalItemDTO> listLocaisUFCidade(long idUfCidadeItem,
			long[] idLocais) throws AlugueRelaxeException {
		List<LocalItemDTO> lst = null;
		
		// Monta a String corretamente de acordo com o numero de parametros
		StringBuilder sb = new StringBuilder();
		sb.append(SQL_PLANO_LOCAL_POR_CIDADE);
		for (int i = 0; i < idLocais.length; i++){
			sb.append("?,");
		}
		String strQry = sb.toString().substring(0,sb.toString().length() - 1) + ")";
		
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(strQry);
		qry.setLong(0, idUfCidadeItem);
		for (int i = 1; i <= idLocais.length; i++){
			qry.setLong(i, idLocais[i-1]);
		}
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<LocalItemDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				LocalItemDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;	
	}

	public List<LocalItemDTO> listLocaisUFCidadeClassificacao(long idUfCidadeItem,
			long[] idClassificacao) throws AlugueRelaxeException {
		List<LocalItemDTO> lst = null;
		
		// Monta a String corretamente de acordo com o numero de parametros
		StringBuilder sb = new StringBuilder();
		sb.append(SQL_PLANO_CLASSIFICACAO_POR_CIDADE);
		for (int i = 0; i < idClassificacao.length; i++){
			sb.append("?,");
		}
		String strQry = sb.toString().substring(0,sb.toString().length() - 1) + ")";
		
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(strQry);
		qry.setLong(0, idUfCidadeItem);
		for (int i = 1; i <= idClassificacao.length; i++){
			qry.setLong(i, idClassificacao[i-1]);
		}
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<LocalItemDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				LocalItemDTO dto = getDTO((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;	
	}

	public List<LocalDTO> listLocal() throws AlugueRelaxeException {
		List<LocalDTO> lst = null;
		
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BASICO_LOCAL);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<LocalDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				LocalDTO dto = getDTOLocal((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;	
	}

	public List<ClassificacaoDTO> listClassificacaoLocal()
			throws AlugueRelaxeException {
		List<ClassificacaoDTO> lst = null;
		
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_BASICO_CLASSIFICACAO);
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<ClassificacaoDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				ClassificacaoDTO dto = getDTOClassificacao((Object[]) resultado.get(i));
				lst.add(dto);
			}
		}
		return lst;	
	}


	private LocalItemDTO getDTO(Object[] registro) throws AlugueRelaxeException {
		LocalItemDTO dto = new LocalItemDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.idLocal = Long.valueOf(registro[++j].toString());
		dto.descricaoBase = (registro[++j] == null ? null : registro[j].toString());
		dto.idUfCidadeItem = Long.valueOf(registro[++j].toString());
		dto.uf = (registro[++j] == null ? null : registro[j].toString());
		dto.cidade = (registro[++j] == null ? null : registro[j].toString());
		dto.classificacao = (registro[++j] == null ? null : registro[j].toString());
		dto.descricao = (registro[++j] == null ? null : registro[j].toString());
		dto.latitude = Double.valueOf(registro[++j].toString());
		dto.longitude = Double.valueOf(registro[++j].toString());
		dto.urlRef_1 = (registro[++j] == null ? null : registro[j].toString());
		dto.urlRef_2 = (registro[++j] == null ? null : registro[j].toString());
		dto.urlRef_3 = (registro[++j] == null ? null : registro[j].toString());
		dto.urlRef_4 = (registro[++j] == null ? null : registro[j].toString());
		dto.urlRef_5 = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.urlIconeLocal = (registro[++j] == null ? null : registro[j].toString());
		dto.urlIconeLocalItem = (registro[++j] == null ? null : registro[j].toString());
		return dto;
	}

	private LocalDTO getDTOLocal(Object[] registro) throws AlugueRelaxeException {
		LocalDTO dto = new LocalDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.descricao = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		dto.urlIcone = (registro[++j] == null ? null : registro[j].toString());
		return dto;
	}
	
	private ClassificacaoDTO getDTOClassificacao(Object[] registro) throws AlugueRelaxeException {
		ClassificacaoDTO dto = new ClassificacaoDTO();
		int j = -1;
		dto.id = Long.valueOf(registro[++j].toString());
		dto.descricao = (registro[++j] == null ? null : registro[j].toString());
		dto.dataCadastro = (registro[++j] == null ? null : (Timestamp) registro[j]);
		return dto;
	}
	
}
