package br.com.jcv.backend.paginacao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.interfacesdao.PaginacaoDAO;


/**
 * Classe de acesso aos dados para realizacao de paginacao de dados para qualquer tabela/view
 * @author julio
 *
 */
public class FirebirdPaginacaoDAO implements PaginacaoDAO {
	
	private DAOFactory daofactory = null;
	
	public FirebirdPaginacaoDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public EnvelopePaginacaoDataGridDTO listarPorFiltro(String tabelaView,
										List<String> campos,
										List<CondicaoDTO> param, 
										List<String> lstOrderBy, 
										int indice, 
										int qtdePorPagina) throws AlugueRelaxeException{
										
		EnvelopePaginacaoDataGridDTO retorno = null;
		
		// Monta campos
		StringBuilder sbcampos = new StringBuilder();
		for (String item : campos){
			sbcampos.append(item).append(",");
		}
		
		// Monta Order By
		StringBuilder sbOrderBy = new StringBuilder();
		if(lstOrderBy != null){
			for (String item : lstOrderBy){
				sbOrderBy.append(item).append(",");
			}
		}
		
		// Monta Clausula Where
		StringBuilder sbwhere = new StringBuilder();
		if (param != null) {
			for (CondicaoDTO item: param) {
				// Obtem nome do campo
				sbwhere.append(item.campo);
				
				// Define Operador
				if (item.condicao.equals("=")) {
					sbwhere.append(" = ");
				} else if (item.condicao.equals(">")) {
					sbwhere.append(" > ");
				} else if (item.condicao.equals(">=")) {
					sbwhere.append(" >= ");
				} else if (item.condicao.equals("<")) {
					sbwhere.append(" < ");
				} else if (item.condicao.equals("<=")) {
					sbwhere.append(" <= ");
				}
				
				// Adiciona conteudo a ser comparado
				sbwhere.append(item.conteudo);

				// Define Operador Logico
				if (item.operadorLogico != null){
					if (item.operadorLogico.equals("E")) {
						sbwhere.append(" AND ");
					} else if (item.operadorLogico.equals("OU")) {
						sbwhere.append(" OR ");
					}
				}
			}
		}
		
		// Monta a query
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT FIRST ? SKIP ? ");
		sql.append(sbcampos.toString().substring(0,sbcampos.toString().length() - 1));
		sql.append(" FROM ").append(tabelaView);
		if (sbwhere.toString().length() > 0){
			sql.append(" WHERE ").append(sbwhere.toString());
		}
		if (sbOrderBy.toString().length() > 0){
			sql.append(" ORDER BY ").append(sbOrderBy.toString().substring(0,sbOrderBy.toString().length() - 1));
		}

		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(sql.toString());
		int k = 0;
		
		qry.setInteger(k++, qtdePorPagina);
		qry.setInteger(k++, (indice - 1) * qtdePorPagina);		
		
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
		
			retorno = new EnvelopePaginacaoDataGridDTO();
			retorno.lst = new ArrayList<RegDataGridDTO>();
			
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor[] = (Object[])resultado.get(i);
				RegDataGridDTO rdg = new RegDataGridDTO();
				rdg.campo = new String[dtor.length];
				for ( int j = 0; j < dtor.length; j++){
					rdg.campo[j] = (dtor[j] == null ? null : dtor[j].toString());
				}
				
				retorno.lst.add(rdg);
			}
			
			// Realiza a contagem total de registros de acordo com o filtro
			this.countPorFiltro(retorno, tabelaView, sbwhere.toString());
		}
		
		return retorno;

	}


	private void countPorFiltro(EnvelopePaginacaoDataGridDTO envelope,
										String tabelaView,
										String where) throws AlugueRelaxeException{
		
		// Monta a query
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append(" FROM ").append(tabelaView);
		if (where.length() > 0){
			sql.append(" WHERE ").append(where);
		}

		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(sql.toString());
		
		List resultado = qry.list();
		if ((resultado != null) && (resultado.size() > 0)) {
			Object dtor = (Object)resultado.get(0);
			envelope.totalRegistros = (dtor == null ? 0 : Long.valueOf(dtor.toString()));
		}
	}
	
}
