package br.com.jcv.backend.imovel.publicidade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelFichaCompletaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.interfacesdao.ImovelPublicidadeDAO;

/**
 * <h1>FirebirdImovelPublicidadeDAO</h1> 
 * <p>Objetivo desta classe é gerenciar todos os métodos de 
 * acesso a dados na tabela PUBLICIDADE.
 *
 * @author Júlio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */
public class FirebirdImovelPublicidadeDAO implements ImovelPublicidadeDAO<ImovelPublicidadeDTO> {

	private static final String SQL_COUNT_GRADE_PUBLICIDADE = "select count(*) " +
															"from PUBLICIDADE a " +
															"inner join IMOVEL_PLANO_FATURA b on a.IMPF_CD_IMOVEL_PLANO_FATURA = b.IMPF_CD_IMOVEL_PLANO_FATURA " +
															"inner join IMOVEL c on a.IMOV_CD_IMOVEL = c.IMOV_CD_IMOVEL " +
															"where a.PUBL_DT_INICIO = ? " +
															"and   a.PUBL_IN_STATUS = ? " +
															"and   a.PUBL_IN_TIPO_PUBLICIDADE = ? " +
															"and   b.IMPF_IN_STATUS = ? " +
															"and   b.IMPF_DT_PGTO IS NOT NULL " +
															"and   c.IMOV_IN_STATUS = ?";															


	private static final String SQL_LISTAR_PUBL_PAGAS_DENTRO_PRAZO = "select " +
			"a.PUBL_CD_PUBLICIDADE, " +
			"a.PUBL_DT_INICIO, " +
			"a.PUBL_DT_FIM, " +
			"a.PUBL_IN_TIPO_PUBLICIDADE, " +
			"a.PUBL_IN_STATUS, " +
			"a.IMOV_CD_IMOVEL, " +
			"a.PUBL_DT_CADASTRO , " +
			"a.IMPF_CD_IMOVEL_PLANO_FATURA, " +
			"b.IMPF_VL_FATURA " +
		"from PUBLICIDADE a " +
		"inner join IMOVEL_PLANO_FATURA b on a.IMPF_CD_IMOVEL_PLANO_FATURA = b.IMPF_CD_IMOVEL_PLANO_FATURA " +
		"where 	b.IMPF_IN_STATUS = ? " +
		"and 	b.IMPF_DT_PGTO is not null " +
		"and 	a.PUBL_IN_TIPO_PUBLICIDADE = ? " +
		"and 	CURRENT_DATE between a.PUBL_DT_INICIO and a.PUBL_DT_FIM";

	/**
	 * <h2>SQL_LISTAR_IMOVEL_PUBLICIDADE_PRIMEIRA_PAGINA</h2>
	 * <p>Query responsável por retornar todos os registros que estão para serem
	 * apresentados na primeira página do site no painel de publicidade.
	 * </p>
	 */
	private static final String SQL_LISTAR_IMOVEL_PUBLICIDADE_PRIMEIRA_PAGINA = 
	    "SELECT a.IMOV_CD_IMOVEL," +
	    "	a.IMOV_QT_VISITAS," +
	    "	c.IMIV_NM_IMAGEM_VIDEO," +
	    "	e.UF_NM_ESTADO," +
	    "	a.IMOV_TX_DESCRICAO_GERAL," +
	    "	f.CLIE_CD_CLIENTE, " +
	    "	a.IMOV_DT_CADASTRO, " +
	    "	d.UF_CD_ESTADO, " +
	    "   g.CIDA_NM_CIDADE, " +
	    "   a.IMOV_IMAGEM_PREFERIDA " +
	    "FROM IMOVEL a," +
	    "	PUBLICIDADE b," +
	    "	IMOVEL_IMAGEM_VIDEO c," +
	    "	UF_CIDADE_ITEM d," +
	    "	UF e," +
	    "   CLIENTE f, " +
	    "   CIDADE g " +
	    "WHERE b.IMOV_CD_IMOVEL = a.IMOV_CD_IMOVEL " +
	    "AND   c.IMOV_CD_IMOVEL = a.IMOV_CD_IMOVEL " +
	    "AND   f.CLIE_CD_CLIENTE = a.CLIE_CD_CLIENTE " +
	    "AND   a.UFCI_CD_UF_CIDADE_ITEM = d.UFCI_CD_UF_CIDADE_ITEM " +
	    "AND   d.UF_CD_ESTADO = e.UF_CD_ESTADO " + 
	    "AND   d.CIDA_CD_CIDADE = g.CIDA_CD_CIDADE " +
	    "AND   CURRENT_DATE between b.PUBL_DT_INICIO AND b.PUBL_DT_FIM " +
	    "AND   b.PUBL_IN_TIPO_PUBLICIDADE = ? " +
	    "AND   c.IMIV_IN_IMAGEM_VIDEO = ? " +
	    "AND   b.PUBL_IN_STATUS = ? " +
		"AND   c.IMIV_NR_ORDEM in (  	SELECT max(c.IMIV_NR_ORDEM)" +
		"								FROM IMOVEL_IMAGEM_VIDEO c " +
		"								WHERE c.IMIV_IN_IMAGEM_VIDEO = ? " +
		"								GROUP BY c.IMOV_CD_IMOVEL)";	
	/**
	 * <h2>SQL_LISTAR_IMOVEL_PUBLICIDADE_PAGINA_DESTAQUE</h2>
	 * <p>Query responsável por retornar todos os registros que estão para serem
	 * apresentados na primeira página do site no painel de publicidade de destaque.
	 * </p>
	 */
	private static final String SQL_LISTAR_IMOVEL_PUBLICIDADE_PAGINA_DESTAQUE = 
		"SELECT a.IMOV_CD_IMOVEL, " +
	    "   f.CLIE_CD_CLIENTE, " +
	    "   a.IMOV_QT_VISITAS, " +
	    "   c.IMIV_NM_IMAGEM_VIDEO, " +
	    "   a.IMOV_DT_CADASTRO, " +
	    "   g.CIDA_NM_CIDADE, " +
	    "   d.UF_CD_ESTADO " +
		"FROM IMOVEL a, " +
	    "     PUBLICIDADE b, " +
	    "     IMOVEL_IMAGEM_VIDEO c, " +
	    "     UF_CIDADE_ITEM d, " +
	    "     CLIENTE f, " +
	    "     CIDADE g " +
	    "WHERE b.IMOV_CD_IMOVEL = a.IMOV_CD_IMOVEL " +
	    "AND   c.IMOV_CD_IMOVEL = a.IMOV_CD_IMOVEL " +
	    "AND   f.CLIE_CD_CLIENTE = a.CLIE_CD_CLIENTE " +
	    "AND   a.UFCI_CD_UF_CIDADE_ITEM = d.UFCI_CD_UF_CIDADE_ITEM " +
	    "AND   d.CIDA_CD_CIDADE = g.CIDA_CD_CIDADE " +
	    "AND   CURRENT_DATE between b.PUBL_DT_INICIO AND b.PUBL_DT_FIM " +
	    "AND   b.PUBL_IN_TIPO_PUBLICIDADE = ? " +
	    "AND   c.IMIV_IN_IMAGEM_VIDEO = ?" +
	    "AND   b.PUBL_IN_STATUS = ? " +
		"AND   c.IMIV_NR_ORDEM in (  	SELECT max(c.IMIV_NR_ORDEM)" +
		"								FROM IMOVEL_IMAGEM_VIDEO c " +
		"								WHERE c.IMIV_IN_IMAGEM_VIDEO = ? " +
		"								GROUP BY c.IMOV_CD_IMOVEL)";	
	
	/**
	 * <h2>daofactory</h2>
	 * <p>Objeto DAOFactory que contÃ©m um encapsulamento para uma sessao Hibernate</p>
	 */
	private DAOFactory daofactory = null;
	
	public FirebirdImovelPublicidadeDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(ImovelPublicidadeDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelPublicidadeDTO insert(ImovelPublicidadeDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelPublicidadeDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ImovelPublicidadeDTO> list(int start)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelPublicidadeDTO load(ImovelPublicidadeDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelPublicidadeDTO update(ImovelPublicidadeDTO dto)
			throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <h2>listImovelSuperBanner</h2>
	 * <p>Metodo responsavel por listar todos os imoveis que estao disponiveis
	 * publicidade do super banner</p>
	 * @return List - Lista de <code>ImovelPublicidadeDTO</code>
	 */
	@SuppressWarnings("unchecked")
	public List<ImovelPublicidadeDTO> listImovelSuperBanner()
			throws AlugueRelaxeException {
		
		List<ImovelPublicidadeDTO> retorno = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTAR_IMOVEL_PUBLICIDADE_PRIMEIRA_PAGINA);
		int k = 0;
		qry.setString(k++, Constantes.IND_TIPO_PUBLICIDADE_SB);
		qry.setString(k++, Constantes.IND_TIPO_IMAGEM_XG);
		qry.setString(k++, "L");
		qry.setString(k++, Constantes.IND_TIPO_IMAGEM_XG);
		List resultado = qry.list();
		if (resultado.size() > 0) {
			retorno = new ArrayList<ImovelPublicidadeDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				ImovelPublicidadeDTO ipdto = new ImovelPublicidadeDTO();
				int j = -1;
				ipdto.id = Long.valueOf(dto[++j].toString());
				ipdto.qtdeVisitas = Integer.valueOf(dto[++j].toString());
				ipdto.nomeImagemVideo = (dto[++j] == null ? null : dto[j].toString());
				ipdto.descricaoUF = (dto[++j] == null ? null : dto[j].toString());
				ipdto.descricaoGeral = (dto[++j] == null ? null : dto[j].toString());
				ipdto.idCliente = (dto[++j] == null ? 0 : Long.valueOf(dto[j].toString()));
				ipdto.dataCadastro = (dto[++j] == null ? null : (Timestamp) dto[j]);
				ipdto.endereco = new EnderecoDTO();
				ipdto.endereco.uf = (dto[++j] == null ? null : dto[j].toString());
				ipdto.endereco.cidade = (dto[++j] == null ? null : dto[j].toString());
				ipdto.idImagemPreferida = Long.valueOf(dto[++j].toString());
				retorno.add(ipdto);
			}
		}
		return retorno;
	}

	/**
	 * <h2>listImovelPublicidadePaginaPrincipal</h2>
	 * <p>Metodo responsavel por listar todos os imoveis que estÃ£o disponÃ­veis para
	 * publicidade na primeira pagina do site.</p>
	 * @return List - Lista de <code>ImovelPublicidadeDTO</code>
	 */
	@SuppressWarnings("unchecked")
	public List<ImovelPublicidadeDTO> listImovelPublicidadePaginaPrincipal()
			throws AlugueRelaxeException {
		
		List<ImovelPublicidadeDTO> retorno = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTAR_IMOVEL_PUBLICIDADE_PRIMEIRA_PAGINA);
		int k = 0;
		qry.setString(k++, Constantes.IND_TIPO_PUBLICIDADE_PP);
		qry.setString(k++, Constantes.IND_TIPO_IMAGEM_PP);
		qry.setString(k++, "L");
		qry.setString(k++, Constantes.IND_TIPO_IMAGEM_PP);
		List resultado = qry.list();
		if (resultado.size() > 0) {
			retorno = new ArrayList<ImovelPublicidadeDTO>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				ImovelPublicidadeDTO ipdto = new ImovelPublicidadeDTO();
				int j = -1;
				ipdto.id = Long.valueOf(dto[++j].toString());
				ipdto.qtdeVisitas = Integer.valueOf(dto[++j].toString());
				ipdto.nomeImagemVideo = (dto[++j] == null ? null : dto[j].toString());
				ipdto.descricaoUF = (dto[++j] == null ? null : dto[j].toString());
				ipdto.descricaoGeral = (dto[++j] == null ? null : dto[j].toString());
				ipdto.idCliente = (dto[++j] == null ? 0 : Long.valueOf(dto[j].toString()));
				ipdto.dataCadastro = (dto[++j] == null ? null : (Timestamp) dto[j]);
				ipdto.endereco = new EnderecoDTO();
				ipdto.endereco.uf = (dto[++j] == null ? null : dto[j].toString());
				ipdto.endereco.cidade = (dto[++j] == null ? null : dto[j].toString());
				ipdto.idImagemPreferida = Long.valueOf(dto[++j].toString());
				retorno.add(ipdto);
			}
		}
		return retorno;
	}

	public List<ImovelPublicidadeDTO> listImovelPublicidadePaginaDestaque()
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTAR_IMOVEL_PUBLICIDADE_PAGINA_DESTAQUE);
		int k = 0;
		qry.setString(k++, Constantes.IND_TIPO_PUBLICIDADE_PD);
		qry.setString(k++, Constantes.IND_TIPO_IMAGEM_PD);
		qry.setString(k++, "L");
		qry.setString(k++, Constantes.IND_TIPO_IMAGEM_PD);
		List resultado = qry.list();
		List<ImovelPublicidadeDTO> retorno = new ArrayList<ImovelPublicidadeDTO>();
		if (resultado.size() > 0) {
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				int j = -1;
				ImovelPublicidadeDTO ipdto = new ImovelPublicidadeDTO();
				ipdto.id = Long.valueOf(dto[++j].toString());
				ipdto.idCliente = Long.valueOf(dto[++j].toString());
				ipdto.qtdeVisitas = Integer.valueOf(dto[++j].toString());
				ipdto.nomeImagemVideo = (dto[++j] == null ? null : dto[j].toString());
				ipdto.dataCadastro = (dto[++j] == null ? null : (Timestamp) dto[j]);
				String cidade = (dto[++j] == null ? null : dto[j].toString());
				String uf = (dto[++j] == null ? null : dto[j].toString());
				ipdto.descricaoUF = cidade.concat(" - ").concat(uf) ;
				retorno.add(ipdto);
			}
		}
		return retorno;
	}
	
	public List<PublicidadeImovelDTO> listPublicidadeDentroPrazo(String tipo) throws AlugueRelaxeException {

		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_LISTAR_PUBL_PAGAS_DENTRO_PRAZO);
		int k = 0;
		qry.setString(k++, "L");
		qry.setString(k++, tipo);
		List resultado = qry.list();
		List<PublicidadeImovelDTO> retorno = new ArrayList<PublicidadeImovelDTO>();
		if (resultado.size() > 0) {
			for (int i = 0; i < resultado.size(); i++) {
				Object dto[] = (Object[])resultado.get(i);
				int j = -1;
				PublicidadeImovelDTO ipdto = new PublicidadeImovelDTO();
				ipdto.publicidade = new PublicidadeDTO();
				ipdto.fatura = new ImovelPlanoFaturaDTO();
				ipdto.fichaImovel = new ImovelFichaCompletaDTO();
				ipdto.fichaImovel.imovel = new ImovelDTO();
				
				ipdto.publicidade.idPublicidade = Long.valueOf(dto[++j].toString());
				ipdto.publicidade.dataInicio = (dto[++j] == null ? null : (Date) dto[j]);
				ipdto.publicidade.dataFim = (dto[++j] == null ? null : (Date) dto[j]);
				ipdto.publicidade.tipoPublicidade = (dto[++j] == null ? null : dto[j].toString());
				ipdto.publicidade.status = (dto[++j] == null ? null : dto[j].toString());
				ipdto.fichaImovel.imovel.id = Long.valueOf(dto[++j].toString());
				ipdto.publicidade.dataCadastro = (dto[++j] == null ? null : (Timestamp) dto[j]);
				ipdto.publicidade.idFatura = Long.valueOf(dto[++j].toString());
				ipdto.fatura.id = ipdto.publicidade.idFatura;
				ipdto.fatura.valorFatura = Double.valueOf(dto[++j].toString());
				retorno.add(ipdto);
			}
		}
		return retorno;
	}
	
	public int countGradePublicidade(Date d, String tipo) throws AlugueRelaxeException {

		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_COUNT_GRADE_PUBLICIDADE);
		int k = 0;
		qry.setDate(k++, d);
		qry.setString(k++, "L");
		qry.setString(k++, tipo);
		qry.setString(k++, "L");
		qry.setString(k++, "A");

		List resultado = qry.list();
		int retorno = 0;
		if (resultado.size() > 0) {
			Object o = (Object) resultado.get(0);
			retorno = Integer.valueOf(o.toString());
		}
		return retorno;
	}

}
