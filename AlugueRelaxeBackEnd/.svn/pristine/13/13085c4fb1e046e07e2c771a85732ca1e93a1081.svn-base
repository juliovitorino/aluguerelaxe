/*
* Copyright (c) 2009-2009, Julio Cesar Vitorino, Todos os direitos reservados.
*
* This software is the confidential and proprietary information of Sun
* Microsystems, Inc. ("Confidential Information"). You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with Sun.
*
* SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
* THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
* TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
* PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
* ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
* DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/
package br.com.jcv.backend.imovel;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.charter.CharterSequence;
import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.dto.CidadeUFDTO;
import br.com.jcv.backend.dto.EnderecoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.factory.DAOFactory;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.publicidade.PublicidadeImovelDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.interfacesdao.ImovelCaracteristicaDAO;
import br.com.jcv.backend.interfacesdao.ImovelDAO;
import br.com.jcv.backend.interfacesdao.TabelaPrecoDAO;
import br.com.jcv.backend.plano.PlanoDTO;

/**
 * <h1>FirebirdImovelDAO</h1> 
 * <p>Objetivo desta classe Ã© gerenciar todos os MÃ©todos de acesso a dados na tabela IMOVEL.
 *
 * @author JÃºlio Vitorino
 * @version 1.10
 * @since 12 Aug 2009
 */
public class FirebirdImovelDAO implements ImovelDAO<ImovelDTO> {
	
	private static final String UPD_IMOVEL_STATUS_COLABORADOR = "update IMOVEL set " +
	"IMOV_IN_TIPO_COLABORACAO = ?, " +
	"IMOV_VL_TIPO_COLABORACAO = ? " +
	"where IMOV_CD_IMOVEL = ?";
	
	private static final String UPD_SEQ_OFERECIMENTO = "update IMOVEL set " +
	"IMOV_SQ_OFERECIMENTO = ? " +
	"where IMOV_CD_IMOVEL = ?";

	private static final String UPD_SALDO_FURA_FILA = "update IMOVEL " +
	"set IMOV_VL_TIPO_COLABORACAO = IMOV_VL_TIPO_COLABORACAO - ? " +
	"where IMOV_CD_IMOVEL = ?";
	
	private static final String UPD_NOTA_ANUNCIO = "update IMOVEL " +
	"set IMOV_VL_TIPO_COLABORACAO = ? " +
	"where IMOV_CD_IMOVEL = ?";
	
	private static final String SQL_IMOVEL_ID = "select " +
	"IMOV_CD_IMOVEL ";
	
	private static final String SQL_IMOVEL_ID_PAGINACAO = "SELECT FIRST ? SKIP ? " +
	"IMOV_CD_IMOVEL ";
	
	private static final String SQL_IMOVEL_CIDADE_UF = SQL_IMOVEL_ID_PAGINACAO +
	"from IMOVEL " +	
	"where UFCI_CD_UF_CIDADE_ITEM = ? " +
	"and IMOV_IN_STATUS = ? " +
	"order by IMOV_VL_TIPO_COLABORACAO DESC ";
	
	private static final String SQL_COUNT_IMOVEL_CIDADE_UF = "select count(*) " +
	"from IMOVEL " +	
	"where UFCI_CD_UF_CIDADE_ITEM = ? " +
	"and IMOV_IN_STATUS = ? ";
	
	private static final String SQL_IMOVEIS_STATUS = SQL_IMOVEL_ID + 
	"from IMOVEL " +
	"where IMOV_IN_STATUS = ? " ;
	
	private static final String SQL_ID_IMOVEL_UF_CIDADE = SQL_IMOVEL_ID +
	"from IMOVEL a " +
	"inner join CLIENTE d on d.CLIE_CD_CLIENTE = a.CLIE_CD_CLIENTE " +
	"inner join UF_CIDADE_ITEM b on a.UFCI_CD_UF_CIDADE_ITEM = b.UFCI_CD_UF_CIDADE_ITEM " +
	"inner join CIDADE c on c.CIDA_CD_CIDADE = b.CIDA_CD_CIDADE " +
	"where a.IMOV_IN_STATUS = ? " +
	"and d.CLIE_IN_STATUS = ? " +
	"and b.UF_CD_ESTADO = ? " +
	"and c.CIDA_NM_CIDADE = ? ";
	
	private static final String SQL_PROXIMO_OFERECIMENTO = SQL_IMOVEL_ID +
	"from IMOVEL " +
	"where IMOV_SQ_OFERECIMENTO = (select MIN(IMOV_SQ_OFERECIMENTO) from IMOVEL a " +
	"inner join CLIENTE d on d.CLIE_CD_CLIENTE = a.CLIE_CD_CLIENTE " +
	"inner join UF_CIDADE_ITEM b on a.UFCI_CD_UF_CIDADE_ITEM = b.UFCI_CD_UF_CIDADE_ITEM " +
	"inner join CIDADE c on c.CIDA_CD_CIDADE = b.CIDA_CD_CIDADE " +
	"where a.IMOV_IN_STATUS = ? " +
	"and d.CLIE_IN_STATUS = ? " +
	"and b.UF_CD_ESTADO = ? " +
	"and c.CIDA_NM_CIDADE = ? )";
	
	private static final String SQL_IMOVEL_NEGATIVACAO = SQL_IMOVEL_ID +
	"from  IMOVEL " +
	"where IMOV_IN_STATUS = ? " +
	"and   IMOV_VL_TIPO_COLABORACAO < ?";

	private static final String SQL_IMOVEL_FURA_FILA = SQL_IMOVEL_ID +
	"from  IMOVEL " +
	"where IMOV_IN_TIPO_COLABORACAO = ? " +
	"and   IMOV_VL_TIPO_COLABORACAO > ?";

	private static final String SQL_IMOVEL_ID_TIPO_COLABORACAO = SQL_IMOVEL_ID +
	"from  IMOVEL " +
	"where IMOV_IN_TIPO_COLABORACAO = ? " ;

	private static final String SQL_IMOVEL_COLABORADOR_INCONS = SQL_IMOVEL_ID +
	"from  IMOVEL " +
	"where IMOV_IN_TIPO_COLABORACAO in  (?,?) " +
	"and   IMOV_VL_TIPO_COLABORACAO < 0";

	
	
	private static final String SQL_IMOVEL_PLANO_FINANCEIRO = "select " + 
    "IMOVEL_PLANO.IMPL_CD_IMOVEL_PLANO, " + 
    "IMOVEL_PLANO.PLAN_CD_PLANO, " + 
    "IMOVEL_PLANO.IMPL_DT_CADASTRO, " + 
    "PLANO.PLAN_NM_PLANO, " + 
    "PLANO.PLAN_TX_DESCRICAO, " + 
    "PLANO.PLAN_VL_PLANO, " +
    "PLANO.PLAN_IN_STATUS, " +
    "PLANO.PLAN_DT_CADASTRO, " +
    "PLANO.PLAN_DT_ATIVACAO, " +
    "PLANO.PLAN_DT_CANCELAMENTO," + 
    "PLANO.PLAN_IN_TIPO_PERIODICIDADE," +  
    "PLANO.PLAN_QT_MAX_FOTO_PERMITIDOS " +
    "from IMOVEL_PLANO " +
    "Inner Join PLANO on IMOVEL_PLANO.PLAN_CD_PLANO = PLANO.PLAN_CD_PLANO " +  
    "where IMOVEL_PLANO.IMPL_CD_IMOVEL_PLANO = (select max(IMOVEL_PLANO.IMPL_CD_IMOVEL_PLANO) " +
                                               "from IMOVEL_PLANO " +
                                               "inner join PLANO on IMOVEL_PLANO.PLAN_CD_PLANO = PLANO.PLAN_CD_PLANO " +
                                               "where IMOVEL_PLANO.IMOV_CD_IMOVEL = ? " +
                                               "and PLANO.PLAN_IN_TIPO_COMPRA = 'N')" ;

	private static final String UPD_IMOVEL_STATUS = "update IMOVEL set " +
	"IMOV_IN_STATUS = ? " +
	"where IMOV_CD_IMOVEL = ?";

	private static final String UPD_IMOVEL = "update IMOVEL set " +
		"IMOV_DT_ATUALIZACAO = CURRENT_TIMESTAMP," +
		"IMOV_QT_QUARTOS = ?," +
		"IMOV_QT_SUITE = ?," +
		"IMOV_QT_BANHEIROS = ?," +
		"IMOV_QT_CAPACIDADE = ?," +
		"IMOV_TX_DESCRICAO_GERAL = ?," +
		"IMOV_TX_DESCRICAO_QUARTOS = ?," +
		"IMOV_TX_DESCRICAO_ARREDORES = ?," +
		"IMOV_TX_TITULO_ANUNCIO = ?," +
		"IMOV_TX_OBSERVACOES = ?," +
		"IMOV_IN_MOSTRA_TAB_PRECO = ?," +
		"IMOV_IN_CONDOMINIO = ?," +
		"IMOV_IN_PERMITE_ALUGAR_FESTAS = ?," +
		"IMOV_IN_TIPO_PROPRIEDADE = ?," +
		"IMOV_TX_ENDERECO = ?," +
		"IMOV_TX_NUMERO = ?," +
		"IMOV_TX_COMPLEMENTO = ?," +
		"IMOV_TX_BAIRRO = ?," +
		"IMOV_TX_CEP = ?," +
		"UFCI_CD_UF_CIDADE_ITEM = ?," +
		"IMOV_VL_TARIFA_BASICA = ? " +
		"where IMOV_CD_IMOVEL = ?";

	private static final String UPD_IMOVEL_GEO_LOCALIZACAO = "update IMOVEL set " +
	"IMOV_DT_ATUALIZACAO = CURRENT_TIMESTAMP," +
	"IMOV_NR_LATITUDE = ?," +
	"IMOV_NR_LONGITUDE = ? " +
	"where IMOV_CD_IMOVEL = ?";
	

	private static final String UPD_QTD_IMOVEIS_UFCI = "update uf_cidade_item " +
		"set UFCI_QT_IMOVEIS = UFCI_QT_IMOVEIS + 1 " +
		"where UFCI_CD_UF_CIDADE_ITEM = ?";
	
	private static final String INS_IMOVEL = "insert into IMOVEL (" +
				"IMOV_CD_IMOVEL, " +
				"CLIE_CD_CLIENTE, " +
				"IMOV_QT_QUARTOS, " +
				"IMOV_QT_SUITE, " +
				"IMOV_QT_BANHEIROS, " +
				"IMOV_QT_CAPACIDADE, " +
				"IMOV_TX_DESCRICAO_GERAL, " +
				"IMOV_TX_DESCRICAO_QUARTOS, " +
				"IMOV_TX_DESCRICAO_ARREDORES, " +
				"IMOV_TX_TITULO_ANUNCIO, " +
				"IMOV_TX_OBSERVACOES, " +
				"IMOV_IN_MOSTRA_TAB_PRECO, " +
				"IMOV_IN_CONDOMINIO, " +
				"IMOV_IN_PERMITE_ALUGAR_FESTAS, " +
				"IMOV_IN_TIPO_PROPRIEDADE, " +
				"IMOV_TX_ENDERECO, " +
				"IMOV_TX_NUMERO, " +
				"IMOV_TX_COMPLEMENTO, " +
				"IMOV_TX_BAIRRO, " +
				"IMOV_TX_CEP, " +
				"UFCI_CD_UF_CIDADE_ITEM, " +
				"IMOV_VL_TARIFA_BASICA) " +
			    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String INS_IMOVEL_PUBLICIDADE = "insert into PUBLICIDADE (" +
			"PUBL_CD_PUBLICIDADE," +
			"PUBL_DT_INICIO," +
			"PUBL_DT_FIM," +
			"PUBL_IN_TIPO_PUBLICIDADE," +
			"IMOV_CD_IMOVEL, " +
			"IMPF_CD_IMOVEL_PLANO_FATURA) " +
			"VALUES (?,?,?,?,?,?)";	
	
	private static final String SQL_CONTATO_ANUNCIANTE_STATUS = "select " + 
	"IMCA_CD_IMOVEL_CONTATO_ANU, " +
	"IMOV_CD_IMOVEL, " +
	"IMCA_NM_PROPOSTO, " +
	"IMCA_TX_EMAIL_PROPOSTO, " +
	"IMCA_TX_DDD, " +
	"IMCA_TX_TELEFONE, " +
	"IMCA_QT_ADULTOS, " +
	"IMCA_QT_CRIANCAS, " +
	"IMCA_TX_ADICIONAL, " +
	"IMCA_TX_CIDADE, " +
	"IMCA_TX_UF, " +
	"IMCA_DT_PREV_CHEGADA, " +
	"IMCA_DT_PREV_PARTIDA, " +
	"IMCA_TX_PAIS, " +
	"IMCA_DT_CADASTRO " +
	"from IMOVEL_CONTATO_ANUNCIANTE " +
	"where IMCA_IN_STATUS = ?";

	private static final String SQL_CONTATO_ANUNCIANTE_ID = "select " + 
	"IMCA_CD_IMOVEL_CONTATO_ANU, " +
	"IMOV_CD_IMOVEL, " +
	"IMCA_NM_PROPOSTO, " +
	"IMCA_TX_EMAIL_PROPOSTO, " +
	"IMCA_TX_DDD, " +
	"IMCA_TX_TELEFONE, " +
	"IMCA_QT_ADULTOS, " +
	"IMCA_QT_CRIANCAS, " +
	"IMCA_TX_ADICIONAL, " +
	"IMCA_TX_CIDADE, " +
	"IMCA_TX_UF, " +
	"IMCA_DT_PREV_CHEGADA, " +
	"IMCA_DT_PREV_PARTIDA, " +
	"IMCA_TX_PAIS, " +
	"IMCA_DT_CADASTRO " +
	"from IMOVEL_CONTATO_ANUNCIANTE " +
	"where IMCA_CD_IMOVEL_CONTATO_ANU = ?";
	
	private static final String SQL_IMOVEL_ANUNCIO_PAGINACAO = "SELECT FIRST ? SKIP ? " +
	"IMOVEL.IMOV_CD_IMOVEL, " +
	"IMOVEL.CLIE_CD_CLIENTE, " +
	"IMOVEL.IMOV_IN_PERMITE_ALUGAR_FESTAS, " +
	"IMOVEL.IMOV_IN_CONDOMINIO, " +
	"IMOV_IN_TIPO_PROPRIEDADE, " +
	"IMOVEL.IMOV_QT_QUARTOS, " +
	"IMOVEL.IMOV_QT_SUITE, " +
	"IMOVEL.IMOV_QT_BANHEIROS, " +
	"IMOVEL.IMOV_QT_CAPACIDADE, " +
	"IMOVEL.IMOV_TX_DESCRICAO_GERAL, " +
	"IMOVEL.IMOV_TX_TITULO_ANUNCIO, " +
	"UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM, " +
	"UF_CIDADE_ITEM.UF_CD_ESTADO, " +
	"CIDADE.CIDA_NM_CIDADE, " +
	"UF.UF_NM_ESTADO, " +
	"IMOVEL.IMOV_QT_VISITAS, " +
	"IMOVEL.IMOV_DT_CADASTRO, " +
	"IMOVEL.IMOV_VL_TARIFA_BASICA ";
	
	
	private static final String SQL_IMOVEL_ESTADO_PAGINACAO = SQL_IMOVEL_ANUNCIO_PAGINACAO +
	"from IMOVEL " +
	"inner join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	"inner join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
	"inner join UF on UF_CIDADE_ITEM.UF_CD_ESTADO = UF.UF_CD_ESTADO " +
	"where UF_CIDADE_ITEM.UF_CD_ESTADO = ? " +
	"and IMOVEL.IMOV_IN_STATUS = ? " +
	"order by IMOVEL.IMOV_VL_TIPO_COLABORACAO DESC " ;
	
	private static final String SQL_IMOVEL_CIDADE_PAGINACAO = SQL_IMOVEL_ANUNCIO_PAGINACAO +
	"from IMOVEL " +
	"inner join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	"inner join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
	"inner join UF on UF_CIDADE_ITEM.UF_CD_ESTADO = UF.UF_CD_ESTADO " +
	"where UF_CIDADE_ITEM.CIDA_CD_CIDADE = ? " +
	"and IMOVEL.IMOV_IN_STATUS = ? " +
	"order by IMOVEL.IMOV_VL_TIPO_COLABORACAO DESC " ;

	private static final String SQL_COUNT_IMOVEL_ESTADO_PAGINACAO = "SELECT COUNT(*) " +
	"from IMOVEL " +
	"inner join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	"where UF_CIDADE_ITEM.UF_CD_ESTADO = ? " +
	"and IMOVEL.IMOV_IN_STATUS = ? " ;

	private static final String SQL_COUNT_IMOVEL_POR_CLIENTE = "SELECT COUNT(*) " +
	"from IMOVEL " +
	"where CLIE_CD_CLIENTE = ? " +
	"and IMOV_IN_STATUS = ? " ;

	private static final String SQL_COUNT_IMOVEL_CIDADE_PAGINACAO = "SELECT COUNT(*) " +
	"from IMOVEL " +
	"inner join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	"where UF_CIDADE_ITEM.CIDA_CD_CIDADE = ? " +
	"and IMOVEL.IMOV_IN_STATUS = ? " ;

	private static final String SQL_COUNT_IMOVEL_FILTRO_PAGINACAO = "SELECT COUNT(*) " +
	"from IMOVEL " +
	"inner join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	"where UF_CIDADE_ITEM.UF_CD_ESTADO = ? " +
	"and IMOVEL.IMOV_IN_TIPO_PROPRIEDADE = ? " +
	"and IMOVEL.IMOV_QT_QUARTOS = ? " +
	"and IMOVEL.IMOV_QT_SUITE = ? " +
	"and IMOVEL.IMOV_QT_BANHEIROS = ? " +
	"and IMOVEL.IMOV_QT_CAPACIDADE = ? " +
	"and IMOVEL.IMOV_IN_CONDOMINIO = ? " +
	"and IMOVEL.IMOV_IN_PERMITE_ALUGAR_FESTAS = ? " +
	"and IMOVEL.IMOV_IN_STATUS = ? " ;
	
	private static final String SQL_COUNT_PUBLICIDADE_PP = "SELECT COUNT(*) " +
	"from IMOVEL " +
	"where IMOV_IN_SORTEIO = ? " +
	"and   IMOV_IN_STATUS = ?";
	
	private static final String SQL_COUNT_PUBLICIDADE_PD = "SELECT COUNT(*) " +
	"from IMOVEL " +
	"where IMOV_IN_SORTEIO_PD = ? " +
	"and   IMOV_IN_STATUS = ?";
	
	private static final String SQL_COUNT_PUBLICIDADE_SB = "SELECT COUNT(*) " +
	"from IMOVEL " +
	"where IMOV_IN_SORTEIO_SB = ? " +
	"and   IMOV_IN_STATUS = ?";

	private static final String SQL_QTDE_IMOVEIS_POR_CIDADE_ESTADO = "select " + 
	"UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM, " +
	"UF_CIDADE_ITEM.UFCI_QT_IMOVEIS, " +
	"UF_CIDADE_ITEM.CIDA_CD_CIDADE, " +
	"UF_CIDADE_ITEM.UF_CD_ESTADO, " +
	"CIDADE.CIDA_NM_CIDADE " +
	"from UF_CIDADE_ITEM " +
	"inner join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
	"where UF_CIDADE_ITEM.UFCI_IN_STATUS = ? " +
	"and UF_CIDADE_ITEM.UFCI_QT_IMOVEIS > ?";
	
	private static final String UPD_VISITA_IMOVEL = "update IMOVEL " +
		"set IMOV_QT_VISITAS = IMOV_QT_VISITAS + 1 " +
		"where IMOV_CD_IMOVEL = ?";
	
	private static final String UPD_MODIFICA_STATUS_CONTATO_ANUNCIANTE = "update imovel_contato_anunciante " +
		"set IMCA_IN_STATUS = ? " +
		"where IMCA_CD_IMOVEL_CONTATO_ANU = ?";

	private static final String UPD_MODIFICA_STATUS_THREAD = "update imovel_contato_anunciante " +
		"set IMCA_IN_THREAD_STATUS = ? " +
		"where IMCA_CD_IMOVEL_CONTATO_ANU = ?";

	/**
	 * <h2>SQL_IMOVEL_UF_CIDADE</h2>
	 * <p>Query para retornar informações do imóvel + endereço completo</p>
	 */
	private static final String SQL_IMOVEL_UF_CIDADE = "select " +   
	   " IMOVEL.IMOV_CD_IMOVEL," +
	   " IMOVEL.CLIE_CD_CLIENTE," +
	   " IMOVEL.IMOV_DT_CADASTRO," +
	   " IMOVEL.IMOV_DT_ATUALIZACAO," +
	   " IMOVEL.IMOV_QT_QUARTOS," +
	   " IMOVEL.IMOV_QT_SUITE," +
	   " IMOVEL.IMOV_QT_BANHEIROS," +
	   " IMOVEL.IMOV_QT_CAPACIDADE," +
	   " IMOVEL.IMOV_TX_DESCRICAO_GERAL," +
	   " IMOVEL.IMOV_TX_DESCRICAO_QUARTOS," +
	   " IMOVEL.IMOV_TX_DESCRICAO_ARREDORES," +
	   " IMOVEL.IMOV_TX_TITULO_ANUNCIO," +
	   " IMOVEL.IMOV_IN_STATUS," +
	   " IMOVEL.IMOV_TX_OBSERVACOES," +
	   " IMOVEL.IMOV_IN_MOSTRA_TAB_PRECO," +
	   " IMOVEL.IMOV_DT_ULTIMA_VISITA," +
	   " IMOVEL.IMOV_QT_VISITAS," +
	   " IMOVEL.IMOV_IN_PERMITE_ALUGAR_FESTAS," +
	   " IMOVEL.IMOV_IN_CONDOMINIO," +
	   " IMOVEL.IMOV_IN_TIPO_PROPRIEDADE," +
	   " IMOVEL.IMOV_TX_ENDERECO," +
	   " IMOVEL.IMOV_TX_NUMERO," +
	   " IMOVEL.IMOV_TX_COMPLEMENTO," +
	   " IMOVEL.IMOV_TX_BAIRRO," + 
	   " IMOVEL.IMOV_TX_CEP," +
	   " IMOVEL.UFCI_CD_UF_CIDADE_ITEM," +
	   " UF_CIDADE_ITEM.UF_CD_ESTADO," +
	   " CIDADE.CIDA_NM_CIDADE, " +
	   " IMOVEL.IMOV_VL_TARIFA_BASICA " +
	   "from IMOVEL" + 
	   "     Inner Join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	   "     Inner Join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
	   "where IMOV_CD_IMOVEL = ?";

	private static final String SQL_IMOVEL_GEO_LOCALIZACAO = "select " +   
	   " IMOVEL.IMOV_NR_LATITUDE," +
	   " IMOVEL.IMOV_NR_LONGITUDE " +
	   "from IMOVEL " + 
	   "where IMOV_CD_IMOVEL = ?";

	private static final String SQL_IMOVEL_TIPO_COLABORACAO = "select " +   
	   " IMOVEL.IMOV_IN_TIPO_COLABORACAO, " +
	   " IMOVEL.IMOV_VL_TIPO_COLABORACAO " +
	   "from IMOVEL " + 
	   "where IMOV_CD_IMOVEL = ?";

	private static final String SQL_ULTIMOS_IMOVEIS_CADASTRADOS = "select first ? imov_cd_imovel from imovel where imov_in_status = ? order by imov_cd_imovel desc";
	
	private static final String SQL_IMOVEL_CLIENTE = "select " +   
	   " IMOVEL.IMOV_CD_IMOVEL," +
	   " IMOVEL.CLIE_CD_CLIENTE," +
	   " IMOVEL.IMOV_DT_CADASTRO," +
	   " IMOVEL.IMOV_DT_ATUALIZACAO," +
	   " IMOVEL.IMOV_QT_QUARTOS," +
	   " IMOVEL.IMOV_QT_SUITE," +
	   " IMOVEL.IMOV_QT_BANHEIROS," +
	   " IMOVEL.IMOV_QT_CAPACIDADE," +
	   " IMOVEL.IMOV_TX_DESCRICAO_GERAL," +
	   " IMOVEL.IMOV_TX_DESCRICAO_QUARTOS," +
	   " IMOVEL.IMOV_TX_DESCRICAO_ARREDORES," +
	   " IMOVEL.IMOV_TX_TITULO_ANUNCIO," +
	   " IMOVEL.IMOV_IN_STATUS," +
	   " IMOVEL.IMOV_TX_OBSERVACOES," +
	   " IMOVEL.IMOV_IN_MOSTRA_TAB_PRECO," +
	   " IMOVEL.IMOV_DT_ULTIMA_VISITA," +
	   " IMOVEL.IMOV_QT_VISITAS," +
	   " IMOVEL.IMOV_IN_PERMITE_ALUGAR_FESTAS," +
	   " IMOVEL.IMOV_IN_TIPO_PROPRIEDADE," +
	   " IMOVEL.IMOV_TX_ENDERECO," +
	   " IMOVEL.IMOV_TX_NUMERO," +
	   " IMOVEL.IMOV_TX_COMPLEMENTO," +
	   " IMOVEL.IMOV_TX_BAIRRO," + 
	   " IMOVEL.IMOV_TX_CEP," +
	   " IMOVEL.UFCI_CD_UF_CIDADE_ITEM," +
	   " UF_CIDADE_ITEM.UF_CD_ESTADO," +
	   " CIDADE.CIDA_NM_CIDADE," +
	   " IMOVEL.IMOV_VL_TARIFA_BASICA " +
	   "from IMOVEL" + 
	   "     Inner Join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	   "     Inner Join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
	   "where IMOVEL.CLIE_CD_CLIENTE = ?";

	private static final String SQL_TIPO_COLABORACAO_IMOVEL = "select " +   
	   " IMOVEL.IMOV_CD_IMOVEL," +
	   " IMOVEL.CLIE_CD_CLIENTE," +
	   " IMOVEL.IMOV_DT_CADASTRO," +
	   " IMOVEL.IMOV_DT_ATUALIZACAO," +
	   " IMOVEL.IMOV_QT_QUARTOS," +
	   " IMOVEL.IMOV_QT_SUITE," +
	   " IMOVEL.IMOV_QT_BANHEIROS," +
	   " IMOVEL.IMOV_QT_CAPACIDADE," +
	   " IMOVEL.IMOV_TX_DESCRICAO_GERAL," +
	   " IMOVEL.IMOV_TX_DESCRICAO_QUARTOS," +
	   " IMOVEL.IMOV_TX_DESCRICAO_ARREDORES," +
	   " IMOVEL.IMOV_TX_TITULO_ANUNCIO," +
	   " IMOVEL.IMOV_IN_STATUS," +
	   " IMOVEL.IMOV_TX_OBSERVACOES," +
	   " IMOVEL.IMOV_IN_MOSTRA_TAB_PRECO," +
	   " IMOVEL.IMOV_DT_ULTIMA_VISITA," +
	   " IMOVEL.IMOV_QT_VISITAS," +
	   " IMOVEL.IMOV_IN_PERMITE_ALUGAR_FESTAS," +
	   " IMOVEL.IMOV_IN_TIPO_PROPRIEDADE," +
	   " IMOVEL.IMOV_TX_ENDERECO," +
	   " IMOVEL.IMOV_TX_NUMERO," +
	   " IMOVEL.IMOV_TX_COMPLEMENTO," +
	   " IMOVEL.IMOV_TX_BAIRRO," + 
	   " IMOVEL.IMOV_TX_CEP," +
	   " IMOVEL.UFCI_CD_UF_CIDADE_ITEM," +
	   " UF_CIDADE_ITEM.UF_CD_ESTADO," +
	   " CIDADE.CIDA_NM_CIDADE," +
	   " IMOVEL.IMOV_VL_TARIFA_BASICA " +
	   "from IMOVEL" + 
	   "     Inner Join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM " +
	   "     Inner Join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE " +
	   "where IMOVEL.IMOV_IN_TIPO_COLABORACAO = ?";

	private static final String INS_CONTATO_COM_ANUNCIANTE = "insert into IMOVEL_CONTATO_ANUNCIANTE (" +
	"IMCA_CD_IMOVEL_CONTATO_ANU," + 
	"IMOV_CD_IMOVEL," +
	"IMCA_NM_PROPOSTO," +
	"IMCA_TX_EMAIL_PROPOSTO," + 
	"IMCA_TX_DDD," +
	"IMCA_TX_TELEFONE," +
	"IMCA_QT_ADULTOS," +
	"IMCA_QT_CRIANCAS," +
	"IMCA_TX_ADICIONAL," +
	"IMCA_TX_CIDADE," +
	"IMCA_TX_UF," +
	"IMCA_DT_PREV_CHEGADA," + 
	"IMCA_DT_PREV_PARTIDA," +
	"IMCA_TX_PAIS, " +
	"MOPU_CD_MODO_PUBLICIDADE," +
	"IMCA_SQ_OFERECIMENTO, " +
	"IMCA_CD_OMC_ANUNCIANTE, " +
	"IMCA_CD_OMC_VISITANTE, " +
	"IMCA_CD_OMC_ADMIN " +
	") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String SQL_IMOVEL_PLANO = "select " +
	"max(IMOVEL_PLANO.IMPL_CD_IMOVEL_PLANO)," +
	"IMOVEL_PLANO.PLAN_CD_PLANO," +
	"PLANO.PLAN_NM_PLANO " + 
	"from IMOVEL_PLANO " +
	"Inner Join PLANO on IMOVEL_PLANO.PLAN_CD_PLANO = PLANO.PLAN_CD_PLANO " +
	"where IMOVEL_PLANO.IMOV_CD_IMOVEL = ? " +
	"group by IMOVEL_PLANO.PLAN_CD_PLANO,PLANO.PLAN_NM_PLANO";
	
	private static final String INS_IMOVEL_PLANO = 	"insert into IMOVEL_PLANO (" +
	"IMPL_CD_IMOVEL_PLANO," + 
	"IMOV_CD_IMOVEL," + 
	"PLAN_CD_PLANO" +
	") values (?,?,?)";

	private static final String INS_IMOVEL_PLANO_FATURA = "INSERT INTO IMOVEL_PLANO_FATURA (" +
	"IMPF_CD_IMOVEL_PLANO_FATURA," +
	"IMPL_CD_IMOVEL_PLANO," +
	"IMPF_VL_FATURA," +
	"IMPF_VL_DESCONTO," +
	"IMPF_DT_VENCIMENTO" +
	") VALUES ( ?,?,?,?,? )";
	
	private static final String UPD_PAGAR_IMOVEL_PLANO_FATURA = "UPDATE IMOVEL_PLANO_FATURA SET " +
	"IMPF_DT_PGTO = CURRENT_TIMESTAMP, " +
	"IMPF_IN_STATUS = ? " +
	"WHERE IMPF_CD_IMOVEL_PLANO_FATURA = ?";
	
	private static final String SQL_SUMARIZAR_VISITAS_POR_ANO = "select IMVI_NR_MES, SUM(IMVI_QT_VISITA) " +
		"from IMOVEL_VISITAS " +
		"where IMOV_CD_IMOVEL = ? " +
		"and   IMVI_NR_ANO = ? " +
		"group by IMVI_NR_MES";
	
	private static final String SQL_IMOVEIS_PARA_SORTEIO = "select IMOV_CD_IMOVEL " +
	"from IMOVEL " +
	"where IMOV_IN_SORTEIO = ? " +
	"and IMOV_IN_STATUS = ?";

	private static final String SQL_IMOVEIS_PARA_SORTEIO_PD = "select IMOV_CD_IMOVEL " +
	"from IMOVEL " +
	"where IMOV_IN_SORTEIO_PD = ? " +
	"and IMOV_IN_STATUS = ?";

	private static final String SQL_MAIOR_DATA_PUBLICIDADE = "select max(PUBL_DT_FIM) " +
		"from PUBLICIDADE " +
		"where PUBL_IN_TIPO_PUBLICIDADE = ? " + 
		"and PUBL_DT_FIM > current_date";
	
	private static final String UPD_STATUS_LIBERACAO = "update PUBLICIDADE set " +
	"PUBL_IN_STATUS = ? " +
	"where PUBL_CD_PUBLICIDADE = ?";

	private static final String UPD_PUBLICIDADE_FATURA = "update PUBLICIDADE set " +
	"PUBL_IN_STATUS = ? " +
	"where IMPF_CD_IMOVEL_PLANO_FATURA = ?";

	private static final String UPD_IMOVEL_STATUS_SORTEIO = "update IMOVEL " +
		"set IMOV_IN_SORTEIO = ? " +
		"where IMOV_CD_IMOVEL = ?";

	private static final String UPD_IMOVEL_STATUS_SORTEIO_PD = "update IMOVEL " +
		"set IMOV_IN_SORTEIO_PD = ? " +
		"where IMOV_CD_IMOVEL = ?";
	
	private static final String SQL_ID_IMOVEL_OFERECIMENTO = "select " +
	"IMOV_CD_IMOVEL " +
	"from IMOVEL z   " +
	"where z.IMOV_SQ_OFERECIMENTO = ( 	select min(IMOV_SQ_OFERECIMENTO) " +
	"								from IMOVEL a " +
	"								inner join CLIENTE d on a.CLIE_CD_CLIENTE = d.CLIE_CD_CLIENTE " +
	"								inner join UF_CIDADE_ITEM b on a.UFCI_CD_UF_CIDADE_ITEM = b.UFCI_CD_UF_CIDADE_ITEM " + 
	"								inner join CIDADE c on c.CIDA_CD_CIDADE = b.CIDA_CD_CIDADE  " +
	"								where a.IMOV_IN_STATUS = ?  " +
	"								and d.CLIE_IN_STATUS = ? " +
	"								and b.UF_CD_ESTADO = ?  " +
	"								and c.CIDA_NM_CIDADE = ?  " +
	"							)";

		
	/**
	 * <h2>Atributo daofactory</h2> 
	 * <p>Objeto DAOFactory que contÃ©m um encapsulamento para uma sessÃ©o Hibernate</p>
	 */
	private DAOFactory daofactory = null;
	
	public FirebirdImovelDAO(DAOFactory daofactory) {
		this.daofactory = daofactory;
	}

	public void delete(ImovelDTO dto) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		
	}

	public ImovelDTO insert(ImovelDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_IMOVEL);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, dto.idCliente);
		qry.setInteger(i++, dto.qtdeQuartos);
		qry.setInteger(i++, dto.qtdeSuites);
		qry.setInteger(i++, dto.qtdeBanheiros);
		qry.setInteger(i++, dto.qtdeCapacidade );
		qry.setString(i++, dto.descricaoGeral);
		qry.setString(i++, dto.descricaoQuartos);
		qry.setString(i++, dto.descricaoArredores);
		qry.setString(i++, dto.descricaoTituloAnuncio);
		qry.setString(i++, dto.observacoes);
		qry.setString(i++, dto.indicadorMostraTabelaPreco);
		qry.setString(i++, dto.indicadorCondominio);
		qry.setString(i++, dto.indicadorPermiteAlugarFestas);
		qry.setString(i++, dto.indicadorTipoPropriedade);
		qry.setString(i++, dto.endereco.nome);
		qry.setString(i++, dto.endereco.numero);
		qry.setString(i++, dto.endereco.complemento);
		qry.setString(i++, dto.endereco.bairro);
		qry.setString(i++, dto.endereco.cep);
		qry.setLong(i++, dto.endereco.codigoUfCidadeItem);
		qry.setInteger(i++, dto.valorTarifaBasica);
		qry.executeUpdate();

		Query qryufci = session.createSQLQuery(UPD_QTD_IMOVEIS_UFCI);
		int j = 0;
		qryufci.setLong(j++, dto.endereco.codigoUfCidadeItem);
		qryufci.executeUpdate();
		
		return dto;
	}

	public List<ImovelDTO> list() throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImovelDTO load(ImovelDTO dtoimovel) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_UF_CIDADE); 
		qry.setLong(0, dtoimovel.id);
		List resultado = qry.list();
		ImovelDTO imoveldto = null;
		if (resultado.size() > 0) {
			Object dto[] = (Object[])resultado.get(0);
			
			int i = -1;
			imoveldto = new ImovelDTO();
			imoveldto.id = Long.valueOf(dto[++i].toString());
			imoveldto.idCliente = Long.valueOf(dto[++i].toString());
			imoveldto.dataCadastro = (dto[++i] == null ? null : (Timestamp) dto[i]);
			imoveldto.dataAtualizacao = (dto[++i] == null ? null : (Timestamp) dto[i]);
			imoveldto.qtdeQuartos = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
			imoveldto.qtdeSuites = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
			imoveldto.qtdeBanheiros = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
			imoveldto.qtdeCapacidade = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
			imoveldto.descricaoGeral = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.descricaoQuartos = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.descricaoArredores = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.descricaoTituloAnuncio = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.indicadorStatus = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.observacoes = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.indicadorMostraTabelaPreco = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.dataUltimaVisita = (dto[++i] == null ? null : (Date) dto[i]);
			imoveldto.qtdeVisitas = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
			imoveldto.indicadorPermiteAlugarFestas = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.indicadorCondominio = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.indicadorTipoPropriedade = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.endereco = new EnderecoDTO();
			imoveldto.endereco.nome = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.endereco.numero = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.endereco.complemento = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.endereco.bairro = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.endereco.cep = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.endereco.codigoUfCidadeItem = (dto[++i] == null ? null : Long.valueOf(dto[i].toString()));
			imoveldto.endereco.uf = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.endereco.cidade = (dto[++i] == null ? null : dto[i].toString());
			imoveldto.valorTarifaBasica = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
		}
		return imoveldto;
	}

	@Deprecated
	private void load(long idImovel, ImovelPlanoDTO imovelPlano) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_PLANO);
		qry.setLong(0, idImovel);
		List resultado = qry.list();
		if (resultado.size() > 0) {
			Object dto[] = (Object[])resultado.get(0);
			
			int i = -1;
			imovelPlano.id = Long.valueOf(dto[++i].toString());
			imovelPlano.plano = new PlanoDTO();
			imovelPlano.plano.id = Long.valueOf(dto[++i].toString());
			imovelPlano.plano.nome = (dto[++i] == null ? null : dto[i].toString());
		}
	}

	@Deprecated
	public ImovelDTO update(ImovelDTO dto) throws AlugueRelaxeException {
		return null;
	}

	public List<ImovelDTO> list(int pagina) throws AlugueRelaxeException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Long> listImoveisPorCidadeUf(long idCidadeUf, int pagina, int regPagina) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_CIDADE_UF);
		int k = 0;
		qry.setInteger(k++, regPagina);
		qry.setInteger(k++, (pagina - 1) * regPagina);
		qry.setLong(k++, idCidadeUf);
		qry.setString(k++, "A");
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}

	public long countImoveisPorCidadeUf(long idCidadeUf) throws AlugueRelaxeException{
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_COUNT_IMOVEL_CIDADE_UF);
		int k = 0;
		qry.setLong(k++, idCidadeUf);
		qry.setString(k++, "A");
		List resultado = qry.list();
		
		long retorno = 0;
		if (resultado.size() > 0) {
			retorno = Long.valueOf(resultado.get(0).toString());
		}
		
		return retorno;
	}

	
	/**
	 * <h2>list</h2>
	 * <p>Listar de forma paginada os registros dos imóveis na localização
	 * da UF informada.</p>
	 * @param 
	 * @param
	 * 
	 * @return List<ImovelFichaCompletaDTO> 
	 * 
	 */
	public List<ImovelFichaCompletaDTO> list(String uf, int pagina, int regPagina)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_ESTADO_PAGINACAO);
		qry.setInteger(0, regPagina);
		qry.setInteger(1, (pagina - 1) * regPagina);
		qry.setString(2, uf.toUpperCase());
		qry.setString(3, "A");
		List resultado = qry.list();
		
		List<ImovelFichaCompletaDTO> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<ImovelFichaCompletaDTO>();
			for (int i = 0; i < resultado.size(); i++){
				Object dto[] = (Object[])resultado.get(i);
				
				int j = -1;
				ImovelFichaCompletaDTO ifcdto = new ImovelFichaCompletaDTO();
				ifcdto.imovel = new ImovelDTO();
				ifcdto.imovel.id = Long.valueOf(dto[++j].toString());
				ifcdto.cliente = new ClienteDTO();
				ifcdto.cliente.id = Long.valueOf(dto[++j].toString());
				ifcdto.imovel.indicadorPermiteAlugarFestas = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.indicadorCondominio = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.indicadorTipoPropriedade = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.qtdeQuartos = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.qtdeSuites = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.qtdeBanheiros = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.qtdeCapacidade = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.descricaoGeral = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.descricaoTituloAnuncio = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.endereco = new EnderecoDTO();
				ifcdto.imovel.endereco.codigoUfCidadeItem = (dto[++j] == null ? 0 : Long.valueOf(dto[j].toString()));
				ifcdto.imovel.endereco.uf = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.endereco.cidade = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.endereco.nomeuf = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.qtdeVisitas = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.dataCadastro = (dto[++j] == null ? null : (Timestamp) dto[j]);
				ifcdto.imovel.valorTarifaBasica = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				retorno.add(ifcdto);
			}
		}
		
		return retorno;
	}

	/**
	 * <h2>list</h2>
	 * <p>Listar de forma paginada os registros dos imóveis na localização
	 * da customizada informada.</p>
	 * @param Map
	 * @param int
	 * @param int
	 * 
	 * @return List<ImovelFichaCompletaDTO> 
	 * 
	 */
	public List<ImovelFichaCompletaDTO> list(Map<String,String> param, int pagina, int regPagina)
			throws AlugueRelaxeException {

		//---------------------------------------------------------------
		// recupera informações do mapa para montar a clausula where da
		// query
		//---------------------------------------------------------------
		int cidade =  Integer.valueOf(param.get(Constantes.CONTEXTO_BP_CIDADE));
		String tipoPropriedade =  param.get(Constantes.CONTEXTO_PROPRIEDADE);
		int qtdeQuartos = Integer.valueOf(param.get(Constantes.CONTEXTO_QUARTO));
		int qtdeSuite = Integer.valueOf(param.get(Constantes.CONTEXTO_SUITE));
		int qtdeBanheiro = Integer.valueOf(param.get(Constantes.CONTEXTO_BANHEIRO));
		int qtdeCapacidade = Integer.valueOf(param.get(Constantes.CONTEXTO_CAPACIDADE));
		String indCondominio = param.get(Constantes.CONTEXTO_CONDOMINIO);
		String indAlugarFesta = param.get(Constantes.CONTEXTO_ALUGA_FESTA);
		
		//---------------------------------------------------------------
		// Monta query
		//---------------------------------------------------------------
		StringBuilder sql = new StringBuilder();
		sql.append(SQL_IMOVEL_ANUNCIO_PAGINACAO);
		sql.append("from IMOVEL ");
		sql.append("inner join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM ");
		sql.append("inner join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE ");
		sql.append("inner join UF on UF_CIDADE_ITEM.UF_CD_ESTADO = UF.UF_CD_ESTADO ");
		sql.append("where UF_CIDADE_ITEM.UF_CD_ESTADO = ? ");
		
		if (cidade > -1) {
			sql.append("and UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM = ? ");
		}
		
		if (! tipoPropriedade.equals("I")) {
			sql.append("and IMOVEL.IMOV_IN_TIPO_PROPRIEDADE = ? ");
		}
		
		if (qtdeQuartos > -1) {
			sql.append("and IMOVEL.IMOV_QT_QUARTOS = ? ");
		}
		
		if (qtdeSuite > -1) {
			sql.append("and IMOVEL.IMOV_QT_SUITE = ? ");
		}
		
		if (qtdeBanheiro > -1) {
			sql.append("and IMOVEL.IMOV_QT_BANHEIROS = ? ");
		}

		if (qtdeCapacidade > -1) {
			sql.append("and IMOVEL.IMOV_QT_CAPACIDADE >= ? ");
		}

		if (! indCondominio.equals("I")) {
			sql.append("and IMOVEL.IMOV_IN_CONDOMINIO = ? ");
		}

		if (! indAlugarFesta.equals("I")) {
			sql.append("and IMOVEL.IMOV_IN_PERMITE_ALUGAR_FESTAS = ? ");
		}

		sql.append("and IMOVEL.IMOV_IN_STATUS = ? ");
		sql.append("order by IMOVEL.IMOV_VL_TIPO_COLABORACAO DESC " );
		
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(sql.toString());
		int k = 0;
		
		qry.setInteger(k++, regPagina);
		qry.setInteger(k++, (pagina - 1) * regPagina);
		qry.setString(k++, param.get(Constantes.CONTEXTO_BP_UF));

		if (cidade > -1) {
			qry.setString(k++, param.get(Constantes.CONTEXTO_BP_CIDADE));
		}
		
		if (! tipoPropriedade.equals("I")) {
			qry.setString(k++, param.get(Constantes.CONTEXTO_PROPRIEDADE));
		}
		
		if (qtdeQuartos > -1) {
			qry.setInteger(k++, Integer.valueOf(param.get(Constantes.CONTEXTO_QUARTO)));
		}
		
		if (qtdeSuite > -1) {
			qry.setInteger(k++, Integer.valueOf(param.get(Constantes.CONTEXTO_SUITE)));
		}
		
		if (qtdeBanheiro > -1) {
			qry.setInteger(k++, Integer.valueOf(param.get(Constantes.CONTEXTO_BANHEIRO)));
		}

		if (qtdeCapacidade > -1) {
			qry.setInteger(k++, Integer.valueOf(param.get(Constantes.CONTEXTO_CAPACIDADE)));
		}

		if (! indCondominio.equals("I")) {
			qry.setString(k++, param.get(Constantes.CONTEXTO_CONDOMINIO));
		}

		if (! indAlugarFesta.equals("I")) {
			qry.setString(k++, param.get(Constantes.CONTEXTO_ALUGA_FESTA));
		}
		
		qry.setString(k++, "A");
		
		List resultado = qry.list();
		
		List<ImovelFichaCompletaDTO> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<ImovelFichaCompletaDTO>();
			for (int i = 0; i < resultado.size(); i++){
				Object dto[] = (Object[])resultado.get(i);
				
				int j = -1;
				ImovelFichaCompletaDTO ifcdto = new ImovelFichaCompletaDTO();
				ifcdto.imovel = new ImovelDTO();
				ifcdto.imovel.id = Long.valueOf(dto[++j].toString());
				ifcdto.cliente = new ClienteDTO();
				ifcdto.cliente.id = Long.valueOf(dto[++j].toString());
				ifcdto.imovel.indicadorPermiteAlugarFestas = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.indicadorCondominio = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.indicadorTipoPropriedade = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.qtdeQuartos = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.qtdeSuites = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.qtdeBanheiros = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.qtdeCapacidade = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.descricaoGeral = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.descricaoTituloAnuncio = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.endereco = new EnderecoDTO();
				ifcdto.imovel.endereco.codigoUfCidadeItem = (dto[++j] == null ? 0 : Long.valueOf(dto[j].toString()));
				ifcdto.imovel.endereco.uf = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.endereco.cidade = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.endereco.nomeuf = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.qtdeVisitas = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.dataCadastro = (dto[++j] == null ? null : (Timestamp) dto[j]);
				retorno.add(ifcdto);
			}
		}
		
		return retorno;
	}
	
	public void atualizaVisitas(long codigoImovel) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_VISITA_IMOVEL);
		qry.setLong(0, codigoImovel);
		int n = qry.executeUpdate();
		session.flush();
	}

	public List<CidadeUFDTO> listarQtdeImoveisCidadeUF()
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_QTDE_IMOVEIS_POR_CIDADE_ESTADO);
		qry.setString(0, "A");
		qry.setLong(1, 0);
		List resultado = qry.list();
		
		List<CidadeUFDTO> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<CidadeUFDTO>();
			for (int i = 0; i < resultado.size(); i++){
				Object dto[] = (Object[])resultado.get(i);
				
				int j = -1;
				CidadeUFDTO ifcdto = new CidadeUFDTO();
				ifcdto.id = Long.valueOf(dto[++j].toString());
				ifcdto.qtdeImoveis = Long.valueOf(dto[++j].toString());
				ifcdto.cidade = new CidadeDTO();
				ifcdto.cidade.id = (dto[++j] == null ? 0 : Long.valueOf(dto[j].toString()));
				ifcdto.uf = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.cidade.nome = (dto[++j] == null ? null : dto[j].toString());
				retorno.add(ifcdto);
			}
		}
		
		return retorno;
	}

	/* (non-Javadoc)
	 * @see br.com.jcv.backend.interfacesdao.ImovelDAO#gravarContatoComAnunciante(br.com.jcv.backend.imovel.ContatoClienteDTO)
	 */
	public ContatoClienteDTO gravarContatoComAnunciante(ContatoClienteDTO ccdto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_CONTATO_COM_ANUNCIANTE);
		int i = 0;
		qry.setString(i++, ccdto.id);
		qry.setLong(i++, ccdto.idImovel);
		qry.setString(i++, ccdto.proposto);
		qry.setString(i++, ccdto.email);
		qry.setString(i++, ccdto.ddd);
		qry.setString(i++, ccdto.telefone);
		qry.setInteger(i++, ccdto.qtdeAdultos);
		qry.setInteger(i++, ccdto.qtdeCriancas);
		qry.setString(i++, ccdto.informacoesAdicionais);
		qry.setString(i++, ccdto.cidade);
		qry.setString(i++, ccdto.uf);
		qry.setDate(i++, ccdto.chegadaPrevista);
		qry.setDate(i++, ccdto.partidaPrevista);
		qry.setString(i++, ccdto.pais);
		qry.setLong(i++, ccdto.modopublicidade.id);
		qry.setLong(i++, ccdto.idOferecimento);
		qry.setString(i++, ccdto.codigoOMCThreadAnunciante);
		qry.setString(i++, ccdto.codigoOMCThreadVisitante);
		qry.setString(i++, ccdto.codigoOMCThreadAdmin);
		int n = qry.executeUpdate();
		session.flush();
		
		return this.procurarContatoAnunciante(session, ccdto.id);
		
	}
	
	public List<ContatoClienteDTO> listarContatosAnunciantesPorStatus(
			String status) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_CONTATO_ANUNCIANTE_STATUS);
		qry.setString(0, status);
		List resultado = qry.list();
		
		List<ContatoClienteDTO> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<ContatoClienteDTO>();
			for (int i = 0; i < resultado.size(); i++){
				Object dto[] = (Object[])resultado.get(i);
				
				int j = -1;
				ContatoClienteDTO ifcdto = new ContatoClienteDTO();
				ifcdto.id = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.idImovel = Long.valueOf(dto[++j].toString());
				ifcdto.proposto = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.email = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.ddd = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.telefone = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.qtdeAdultos = Integer.valueOf(dto[++j].toString());
				ifcdto.qtdeCriancas = Integer.valueOf(dto[++j].toString());
				ifcdto.informacoesAdicionais = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.cidade = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.uf = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.chegadaPrevista = (dto[++j] == null ? null : (Date)dto[j]);
				ifcdto.partidaPrevista = (dto[++j] == null ? null : (Date)dto[j]);
				ifcdto.pais = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.dataCadastro = (dto[++j] == null ? null : new Date(((java.sql.Timestamp)dto[j]).getTime()));
				retorno.add(ifcdto);
			}
		}
		
		return retorno;
	}

	public ContatoClienteDTO procurarContatoAnunciante(String id)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_CONTATO_ANUNCIANTE_ID);
		qry.setString(0, id);
		List resultado = qry.list();
		
		ContatoClienteDTO retorno = null;
		if (resultado.size() > 0) {
			retorno = new ContatoClienteDTO();
			Object dto[] = (Object[])resultado.get(0);
			
			int j = -1;
			ContatoClienteDTO ifcdto = new ContatoClienteDTO();
			ifcdto.id = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.idImovel = Long.valueOf(dto[++j].toString());
			ifcdto.proposto = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.email = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.ddd = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.telefone = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.qtdeAdultos = Integer.valueOf(dto[++j].toString());
			ifcdto.qtdeCriancas = Integer.valueOf(dto[++j].toString());
			ifcdto.informacoesAdicionais = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.cidade = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.uf = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.chegadaPrevista = (dto[++j] == null ? null : (Date)dto[j]);
			ifcdto.partidaPrevista = (dto[++j] == null ? null : (Date)dto[j]);
			ifcdto.pais = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.dataCadastro = (dto[++j] == null ? null : new Date(((java.sql.Timestamp)dto[j]).getTime()));
		}
		
		return retorno;
	}
	
	private ContatoClienteDTO procurarContatoAnunciante(Session session, String id)
		throws AlugueRelaxeException {
		Query qry = session.createSQLQuery(SQL_CONTATO_ANUNCIANTE_ID);
		qry.setString(0, id);
		List resultado = qry.list();
		
		ContatoClienteDTO ifcdto = null;
		if (resultado.size() > 0) {
			Object dto[] = (Object[])resultado.get(0);
			
			int j = -1;
			ifcdto = new ContatoClienteDTO();
			ifcdto.id = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.idImovel = Long.valueOf(dto[++j].toString());
			ifcdto.proposto = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.email = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.ddd = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.telefone = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.qtdeAdultos = Integer.valueOf(dto[++j].toString());
			ifcdto.qtdeCriancas = Integer.valueOf(dto[++j].toString());
			ifcdto.informacoesAdicionais = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.cidade = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.uf = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.chegadaPrevista = (dto[++j] == null ? null : (Date)dto[j]);
			ifcdto.partidaPrevista = (dto[++j] == null ? null : (Date)dto[j]);
			ifcdto.pais = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.dataCadastro = (dto[++j] == null ? null : new Date(((java.sql.Timestamp)dto[j]).getTime()));
		}
		
		return ifcdto;
	}

	public void mudaStatusContatoAnunciante(String id, String acao)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_MODIFICA_STATUS_CONTATO_ANUNCIANTE);
		int i = 0;
		qry.setString(i++, acao);
		qry.setString(i++, id);
		int n = qry.executeUpdate();
		session.flush();
	}

	public void mudaStatusThread(String id, String status)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_MODIFICA_STATUS_THREAD);
		int i = 0;
		qry.setString(i++, status);
		qry.setString(i++, id);
		int n = qry.executeUpdate();
		session.flush();
	}

	public List<ImovelDTO> list(ClienteDTO clientedto)
			throws AlugueRelaxeException {
		List<ImovelDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_CLIENTE);
		qry.setLong(0, clientedto.id);
		List resultado = qry.list();
		if (resultado.size() > 0) {
			lst = new ArrayList<ImovelDTO>();
			for(int j = 0; j < resultado.size(); j++){
				Object dto[] = (Object[])resultado.get(j);
				
				int i = -1;
				ImovelDTO imoveldto = new ImovelDTO();
				imoveldto.id = Long.valueOf(dto[++i].toString());
				imoveldto.idCliente = Long.valueOf(dto[++i].toString());
				imoveldto.dataCadastro = (dto[++i] == null ? null : (Timestamp) dto[i]);
				imoveldto.dataAtualizacao = (dto[++i] == null ? null : (Timestamp) dto[i]);
				imoveldto.qtdeQuartos = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.qtdeSuites = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.qtdeBanheiros = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.qtdeCapacidade = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.descricaoGeral = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.descricaoQuartos = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.descricaoArredores = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.descricaoTituloAnuncio = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.indicadorStatus = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.observacoes = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.indicadorMostraTabelaPreco = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.dataUltimaVisita = (dto[++i] == null ? null : (Date) dto[i]);
				imoveldto.qtdeVisitas = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.indicadorPermiteAlugarFestas = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.indicadorTipoPropriedade = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco = new EnderecoDTO();
				imoveldto.endereco.nome = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.numero = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.complemento = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.bairro = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.cep = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.codigoUfCidadeItem = (dto[++i] == null ? null : Long.valueOf(dto[i].toString()));
				imoveldto.endereco.uf = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.cidade = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.valorTarifaBasica = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				lst.add(imoveldto);
			}
		}
		return lst;
	}

	public List<ImovelDTO> list(String indPatrocinadorColaborador)
			throws AlugueRelaxeException {
		List<ImovelDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_TIPO_COLABORACAO_IMOVEL);
		qry.setString(0, indPatrocinadorColaborador);
		List resultado = qry.list();
		if (resultado.size() > 0) {
			lst = new ArrayList<ImovelDTO>();
			for(int j = 0; j < resultado.size(); j++){
				Object dto[] = (Object[])resultado.get(j);
				
				int i = -1;
				ImovelDTO imoveldto = new ImovelDTO();
				imoveldto.id = Long.valueOf(dto[++i].toString());
				imoveldto.idCliente = Long.valueOf(dto[++i].toString());
				imoveldto.dataCadastro = (dto[++i] == null ? null : (Timestamp) dto[i]);
				imoveldto.dataAtualizacao = (dto[++i] == null ? null : (Timestamp) dto[i]);
				imoveldto.qtdeQuartos = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.qtdeSuites = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.qtdeBanheiros = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.qtdeCapacidade = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.descricaoGeral = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.descricaoQuartos = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.descricaoArredores = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.descricaoTituloAnuncio = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.indicadorStatus = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.observacoes = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.indicadorMostraTabelaPreco = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.dataUltimaVisita = (dto[++i] == null ? null : (Date) dto[i]);
				imoveldto.qtdeVisitas = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				imoveldto.indicadorPermiteAlugarFestas = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.indicadorTipoPropriedade = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco = new EnderecoDTO();
				imoveldto.endereco.nome = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.numero = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.complemento = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.bairro = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.cep = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.codigoUfCidadeItem = (dto[++i] == null ? null : Long.valueOf(dto[i].toString()));
				imoveldto.endereco.uf = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.endereco.cidade = (dto[++i] == null ? null : dto[i].toString());
				imoveldto.valorTarifaBasica = (dto[++i] == null ? 0 : Integer.valueOf(dto[i].toString()));
				lst.add(imoveldto);
			}
		}
		return lst;
	}

	
	public void update(long idImovel, List<TabelaPrecoDTO> lst) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		
		//----------------------------------------------
		// Atualizacao tabela de precos 
		//----------------------------------------------
		if ( (lst != null) && 
			 (lst.size() > 0) ) {
			TabelaPrecoDAO<TabelaPrecoDTO> daotp = this.daofactory.getTabelaPrecoDAO(this.daofactory);
			daotp.delete(idImovel);
			for (TabelaPrecoDTO dtoc : lst) {
				dtoc.id = this.daofactory.getNextSequence(daofactory, "SEQ_TABELA_PRECO");
				daotp.insert(idImovel, dtoc);
			}
		}
		session.flush();
	}

	public ImovelFichaCompletaDTO update(ImovelFichaCompletaDTO dto) throws AlugueRelaxeException {
		Session session = daofactory.getSession();

		//-------------------------------------------
		// Atualizacao dos dados do imovel
		//-------------------------------------------
		Query qry = session.createSQLQuery(UPD_IMOVEL);
		int i = 0;
		qry.setLong(i++, dto.imovel.qtdeQuartos);
		qry.setLong(i++, dto.imovel.qtdeSuites);
		qry.setLong(i++, dto.imovel.qtdeBanheiros);
		qry.setLong(i++, dto.imovel.qtdeCapacidade);
		qry.setString(i++, dto.imovel.descricaoGeral);
		qry.setString(i++, dto.imovel.descricaoQuartos);
		qry.setString(i++, dto.imovel.descricaoArredores);
		qry.setString(i++, dto.imovel.descricaoTituloAnuncio);
		qry.setString(i++, dto.imovel.observacoes);
		qry.setString(i++, dto.imovel.indicadorMostraTabelaPreco);
		qry.setString(i++, dto.imovel.indicadorCondominio);
		qry.setString(i++, dto.imovel.indicadorPermiteAlugarFestas);
		qry.setString(i++, dto.imovel.indicadorTipoPropriedade);
		qry.setString(i++, dto.imovel.endereco.nome);
		qry.setString(i++, dto.imovel.endereco.numero);
		qry.setString(i++, dto.imovel.endereco.complemento);
		qry.setString(i++, dto.imovel.endereco.bairro);
		qry.setString(i++, dto.imovel.endereco.cep);
		qry.setLong(i++, dto.imovel.endereco.codigoUfCidadeItem);
		qry.setInteger(i++, dto.imovel.valorTarifaBasica);
		qry.setLong(i++, dto.imovel.id);
		int n = qry.executeUpdate();
		
		//----------------------------------------------
		// Atualizacao dos caracteristicas do condominio
		//----------------------------------------------
		if ( (dto.caracteristicaCondominio != null) && 
			 (dto.caracteristicaCondominio.size() > 0) ) {
			ImovelCaracteristicaDAO<ImovelCaracteristicaDTO> daocc = this.daofactory.getImovelCaracteristicaDAO(this.daofactory);
			daocc.delete(dto.imovel.id, "C");
			for (ImovelCaracteristicaDTO dtoc : dto.caracteristicaCondominio) {
				dtoc.id = this.daofactory.getNextSequence(daofactory, "SEQ_IMCA_CD_CARACTERISTICA");
				dtoc.imovel = new ImovelDTO();
				dtoc.imovel.id = dto.imovel.id;
				dtoc.indicadorCondominio = "C";
				daocc.insert(dtoc);
			}
		}
		
		//----------------------------------------------
		// Atualizacao dos caracteristicas do imovel 
		//----------------------------------------------
		if ( (dto.caracteristicaImovel != null) && 
			 (dto.caracteristicaImovel.size() > 0) ) {
			ImovelCaracteristicaDAO<ImovelCaracteristicaDTO> daoci = this.daofactory.getImovelCaracteristicaDAO(this.daofactory);
			daoci.delete(dto.imovel.id, "I");
			for (ImovelCaracteristicaDTO dtoc : dto.caracteristicaImovel) {
				dtoc.id = this.daofactory.getNextSequence(daofactory, "SEQ_IMCA_CD_CARACTERISTICA");
				dtoc.imovel = new ImovelDTO();
				dtoc.imovel.id = dto.imovel.id;
				dtoc.indicadorCondominio = "I";
				daoci.insert(dtoc);
			}
				
		}
		
		session.flush();
		return dto;
	}

	/**
	 * Insere o relacionamento entre IMOVEL e IMOVEL_PLANO
	 * @see br.com.jcv.backend.interfacesdao.ImovelDAO#insert(long, br.com.jcv.backend.imovel.ImovelPlanoDTO)
	 */
	public ImovelPlanoDTO insert(long codigoImovel, ImovelPlanoDTO dto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_IMOVEL_PLANO);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, codigoImovel);
		qry.setLong(i++, dto.plano.id);
		int n = qry.executeUpdate();
		return dto;
	}

	/**
	 * Insere o relacionamento entre IMOVEL_PLANO e IMOVEL_PLANO_FATURA
	 * @see br.com.jcv.backend.interfacesdao.ImovelDAO#insert(long, br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO)
	 */
	public ImovelPlanoFaturaDTO insert(long idImovelPlano, ImovelPlanoFaturaDTO dto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_IMOVEL_PLANO_FATURA);
		int i = 0;
		qry.setLong(i++, dto.id);
		qry.setLong(i++, idImovelPlano);
		qry.setDouble(i++, dto.valorFatura);
		qry.setDouble(i++, dto.valorDesconto);
		qry.setDate(i++, dto.dataVencimento);
		int n = qry.executeUpdate();
		return dto;
	}

	public ImovelPlanoDTO procurarPlanoFinanceiro(long codigoImovel)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_PLANO_FINANCEIRO);
		qry.setLong(0, codigoImovel);
		List resultado = qry.list();
		
		ImovelPlanoDTO ifcdto = null;
		if (resultado.size() > 0) {
			Object dto[] = (Object[])resultado.get(0);
			
			int j = -1;
			ifcdto = new ImovelPlanoDTO();
			ifcdto.plano = new PlanoDTO();
			ifcdto.id = Long.valueOf(dto[++j].toString());
			ifcdto.plano.id = Long.valueOf(dto[++j].toString());
			ifcdto.dataCadastro = (dto[++j] == null ? null : (Timestamp)dto[j]);
			ifcdto.plano.nome = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.plano.descricao = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.plano.valor = Double.valueOf(dto[++j].toString());
			ifcdto.plano.indicadorStatus = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.plano.dataCadastro = (dto[++j] == null ? null : (Timestamp)dto[j]);
			ifcdto.plano.dataAtivacao = (dto[++j] == null ? null : (Timestamp)dto[j]);
			ifcdto.plano.dataCancelamento = (dto[++j] == null ? null : (Timestamp)dto[j]);
			ifcdto.plano.indicadorPeriodicidade = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.plano.limiteFotos = Integer.valueOf(dto[++j].toString());
		}
		
		return ifcdto;
	}

	public List<ImovelDTO> listarUltimosImoveisCadastrados(int qtdeAnuncios)
			throws AlugueRelaxeException {
		List<ImovelDTO> lst = null;
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_ULTIMOS_IMOVEIS_CADASTRADOS);
		qry.setInteger(0, qtdeAnuncios);
		qry.setString(1, "A");
		List resultado = qry.list();
		if (resultado.size() > 0) {
			lst = new ArrayList<ImovelDTO>();
			for(int j = 0; j < resultado.size(); j++){
				ImovelDTO imoveldto = new ImovelDTO();
				imoveldto.id = ((Integer) resultado.get(j)).longValue();
				lst.add(imoveldto);
			}
		}
		return lst;
	}
	/**
	 * <h2>count</h2>
	 * <p>Contar os registros dos imóveis de um determinado cliente.</p>
	 * @param long
	 * @return long 
	 */
	public int count(long idCliente) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_COUNT_IMOVEL_POR_CLIENTE);
		qry.setLong(0, idCliente);
		qry.setString(1, "A");
		List resultado = qry.list();
		
		int retorno = 0;
		if (resultado.size() > 0) {
			retorno = Integer.valueOf(resultado.get(0).toString());
		}
		
		return retorno;
	}

	
	/**
	 * <h2>count</h2>
	 * <p>Contar os registros dos imóveis na localização da UF informada.</p>
	 * @param String
	 * @return long 
	 */
	public long count(String uf) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_COUNT_IMOVEL_ESTADO_PAGINACAO);
		qry.setString(0, uf.toUpperCase());
		qry.setString(1, "A");
		List resultado = qry.list();
		
		long retorno = 0;
		if (resultado.size() > 0) {
			retorno = Long.valueOf(resultado.get(0).toString());
		}
		
		return retorno;
	}

	/**
	 * <h2>count</h2>
	 * <p>Contar os registros dos imóveis na localização da UF informada.</p>
	 * @param Map
	 * @return long 
	 */
	public long count(Map<String,String> param) throws AlugueRelaxeException {
		//---------------------------------------------------------------
		// recupera informações do mapa para montar a clausula where da
		// query
		//---------------------------------------------------------------
		int cidade =  Integer.valueOf(param.get(Constantes.CONTEXTO_BP_CIDADE));
		String tipoPropriedade =  param.get(Constantes.CONTEXTO_PROPRIEDADE);
		int qtdeQuartos = Integer.valueOf(param.get(Constantes.CONTEXTO_QUARTO));
		int qtdeSuite = Integer.valueOf(param.get(Constantes.CONTEXTO_SUITE));
		int qtdeBanheiro = Integer.valueOf(param.get(Constantes.CONTEXTO_BANHEIRO));
		int qtdeCapacidade = Integer.valueOf(param.get(Constantes.CONTEXTO_CAPACIDADE));
		String indCondominio = param.get(Constantes.CONTEXTO_CONDOMINIO);
		String indAlugarFesta = param.get(Constantes.CONTEXTO_ALUGA_FESTA);
		
		//---------------------------------------------------------------
		// Monta query
		//---------------------------------------------------------------
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("from IMOVEL ");
		sql.append("inner join UF_CIDADE_ITEM on IMOVEL.UFCI_CD_UF_CIDADE_ITEM = UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM ");
		sql.append("inner join CIDADE on UF_CIDADE_ITEM.CIDA_CD_CIDADE = CIDADE.CIDA_CD_CIDADE ");
		sql.append("inner join UF on UF_CIDADE_ITEM.UF_CD_ESTADO = UF.UF_CD_ESTADO ");
		sql.append("where UF_CIDADE_ITEM.UF_CD_ESTADO = ? ");
		
		if (cidade > -1) {
			sql.append("and UF_CIDADE_ITEM.UFCI_CD_UF_CIDADE_ITEM = ? ");
		}
		
		if (! tipoPropriedade.equals("I")) {
			sql.append("and IMOVEL.IMOV_IN_TIPO_PROPRIEDADE = ? ");
		}
		
		if (qtdeQuartos > -1) {
			sql.append("and IMOVEL.IMOV_QT_QUARTOS = ? ");
		}
		
		if (qtdeSuite > -1) {
			sql.append("and IMOVEL.IMOV_QT_SUITE = ? ");
		}
		
		if (qtdeBanheiro > -1) {
			sql.append("and IMOVEL.IMOV_QT_BANHEIROS = ? ");
		}

		if (qtdeCapacidade > -1) {
			sql.append("and IMOVEL.IMOV_QT_CAPACIDADE >= ? ");
		}

		if (! indCondominio.equals("I")) {
			sql.append("and IMOVEL.IMOV_IN_CONDOMINIO = ? ");
		}

		if (! indAlugarFesta.equals("I")) {
			sql.append("and IMOVEL.IMOV_IN_PERMITE_ALUGAR_FESTAS = ? ");
		}

		sql.append("and IMOVEL.IMOV_IN_STATUS = ? ");

		
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(sql.toString());
		int k = 0;
		
		qry.setString(k++, param.get(Constantes.CONTEXTO_BP_UF));

		if (cidade > -1) {
			qry.setString(k++, param.get(Constantes.CONTEXTO_BP_CIDADE));
		}
		
		if (! tipoPropriedade.equals("I")) {
			qry.setString(k++, param.get(Constantes.CONTEXTO_PROPRIEDADE));
		}
		
		if (qtdeQuartos > -1) {
			qry.setInteger(k++, Integer.valueOf(param.get(Constantes.CONTEXTO_QUARTO)));
		}
		
		if (qtdeSuite > -1) {
			qry.setInteger(k++, Integer.valueOf(param.get(Constantes.CONTEXTO_SUITE)));
		}
		
		if (qtdeBanheiro > -1) {
			qry.setInteger(k++, Integer.valueOf(param.get(Constantes.CONTEXTO_BANHEIRO)));
		}

		if (qtdeCapacidade > -1) {
			qry.setInteger(k++, Integer.valueOf(param.get(Constantes.CONTEXTO_CAPACIDADE)));
		}

		if (! indCondominio.equals("I")) {
			qry.setString(k++, param.get(Constantes.CONTEXTO_CONDOMINIO));
		}

		if (! indAlugarFesta.equals("I")) {
			qry.setString(k++, param.get(Constantes.CONTEXTO_ALUGA_FESTA));
		}
		
		qry.setString(k++, "A");

		List resultado = qry.list();
		
		long retorno = 0;
		if (resultado.size() > 0) {
			retorno = Long.valueOf(resultado.get(0).toString());
		}
		
		return retorno;
	}

	public void update(long codigoImovel, GeoLocalizacaoDTO dto)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();

		//-------------------------------------------
		// Atualizacao dos dados da GeoLocalizacao do imovel
		//-------------------------------------------
		Query qry = session.createSQLQuery(UPD_IMOVEL_GEO_LOCALIZACAO);
		int i = 0;
		qry.setDouble(i++, dto.latitude);
		qry.setDouble(i++, dto.longitude);
		qry.setLong(i++, codigoImovel);
		int n = qry.executeUpdate();
		
		session.flush();
	}

	public GeoLocalizacaoDTO procurarGeoLocalizacao(long codigoImovel)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_GEO_LOCALIZACAO);
		qry.setLong(0, codigoImovel);
		List resultado = qry.list();
		
		GeoLocalizacaoDTO ifcdto = null;
		if (resultado.size() > 0) {
			Object dto[] = (Object[])resultado.get(0);
			
			int j = -1;
			ifcdto = new GeoLocalizacaoDTO();
			ifcdto.latitude = Double.valueOf(dto[++j].toString());
			ifcdto.longitude = Double.valueOf(dto[++j].toString());
		}
		
		return ifcdto;
	}

	public long count(int cidade) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_COUNT_IMOVEL_CIDADE_PAGINACAO);
		qry.setInteger(0, cidade);
		qry.setString(1, "A");
		List resultado = qry.list();
		
		long retorno = 0;
		if (resultado.size() > 0) {
			retorno = Long.valueOf(resultado.get(0).toString());
		}
		
		return retorno;
	}

	public List<ImovelFichaCompletaDTO> list(int cidade, int pagina,
			int regPagina) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_CIDADE_PAGINACAO);
		qry.setInteger(0, regPagina);
		qry.setInteger(1, (pagina - 1) * regPagina);
		qry.setInteger(2, cidade);
		qry.setString(3, "A");
		List resultado = qry.list();
		
		List<ImovelFichaCompletaDTO> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<ImovelFichaCompletaDTO>();
			for (int i = 0; i < resultado.size(); i++){
				Object dto[] = (Object[])resultado.get(i);
				
				int j = -1;
				ImovelFichaCompletaDTO ifcdto = new ImovelFichaCompletaDTO();
				ifcdto.imovel = new ImovelDTO();
				ifcdto.imovel.id = Long.valueOf(dto[++j].toString());
				ifcdto.cliente = new ClienteDTO();
				ifcdto.cliente.id = Long.valueOf(dto[++j].toString());
				ifcdto.imovel.indicadorPermiteAlugarFestas = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.indicadorCondominio = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.indicadorTipoPropriedade = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.qtdeQuartos = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.qtdeSuites = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.qtdeBanheiros = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.qtdeCapacidade = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.descricaoGeral = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.descricaoTituloAnuncio = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.endereco = new EnderecoDTO();
				ifcdto.imovel.endereco.codigoUfCidadeItem = (dto[++j] == null ? 0 : Long.valueOf(dto[j].toString()));
				ifcdto.imovel.endereco.uf = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.endereco.cidade = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.endereco.nomeuf = (dto[++j] == null ? null : dto[j].toString());
				ifcdto.imovel.qtdeVisitas = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				ifcdto.imovel.dataCadastro = (dto[++j] == null ? null : (Timestamp) dto[j]);
				ifcdto.imovel.valorTarifaBasica = (dto[++j] == null ? 0 : Integer.valueOf(dto[j].toString()));
				
				retorno.add(ifcdto);
			}
		}
		
		return retorno;
	}

	public void updateDataPgtoFatura(long idFatura)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();

		//-------------------------------------------
		// Atualizacao dos dados da GeoLocalizacao do imovel
		//-------------------------------------------
		Query qry = session.createSQLQuery(UPD_PAGAR_IMOVEL_PLANO_FATURA);
		int i = 0;
		qry.setString(i++, Constantes.IMPF_STATUS_LIBERADO);
		qry.setLong(i++, idFatura);
		int n = qry.executeUpdate();
		
		session.flush();
	}

	public List<CharterSequence> listarSumarizadoImoveis(
			ImovelFichaCompletaDTO ifcdto, int ano)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_SUMARIZAR_VISITAS_POR_ANO);
		qry.setLong(0, ifcdto.imovel.id);
		qry.setInteger(1, ano);
		List resultado = qry.list();
		
		List<CharterSequence> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<CharterSequence>();
			for (int i = 0; i < resultado.size(); i++){
				Object dto[] = (Object[])resultado.get(i);
				
				int j = -1;
				String legenda = (dto[++j] == null ? null : dto[j].toString());
				double valor = (dto[++j] == null ? 0 : Long.valueOf(dto[j].toString()));
				CharterSequence cs = new CharterSequence(legenda,valor);
				retorno.add(cs);
			}
		}
		
		return retorno;
	}

	@Deprecated
	public List<Long> listarImoveisParaSorteio(int maxImoveisSorteio)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEIS_PARA_SORTEIO);
		int k = 0;
		qry.setInteger(k++, maxImoveisSorteio);
		qry.setString(k++, "N");
		qry.setString(k++, "A");
		List resultado = qry.list();
		
		List<Long> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++){
				Integer dto = (Integer) resultado.get(i);
				
				int j = -1;
				retorno.add(Long.valueOf(dto));
			}
		}
		
		return retorno;
	}

	public List<Long> listarImoveisParaSorteio() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEIS_PARA_SORTEIO); 
		int k = 0;
		qry.setString(k++, "N");
		qry.setString(k++, "A");
		List resultado = qry.list();
		
		List<Long> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++){
				Integer dto = (Integer) resultado.get(i);
				
				int j = -1;
				retorno.add(Long.valueOf(dto));
			}
		}
		
		return retorno;
	}


	
	public List<Long> listarImoveisParaSorteioPD()
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEIS_PARA_SORTEIO_PD);
		int k = 0;
		qry.setString(k++, "N");
		qry.setString(k++, "A");
		List resultado = qry.list();
		
		List<Long> retorno = null;
		if (resultado.size() > 0) {
			retorno = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++){
				Integer dto = (Integer) resultado.get(i);
				
				int j = -1;
				retorno.add(Long.valueOf(dto));
			}
		}
		
		return retorno;
	}

	
	
	
	public java.util.Date obterMaiorDataPublicidade(String tipo)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_MAIOR_DATA_PUBLICIDADE);
		int k = 0;
		qry.setString(k++, tipo);
		List resultado = qry.list();
		
		java.util.Date retorno = null;
		if (resultado != null) {
			for (int i = 0; i < resultado.size(); i++){
				try{
					retorno = (java.util.Date) resultado.get(i);
				} catch (Exception e) {
					retorno = new java.util.Date();
				}
			}
		} else {
			retorno = new java.util.Date();
		}
			
		
		return retorno;
	}

	public void insert(PublicidadeImovelDTO pub) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(INS_IMOVEL_PUBLICIDADE);
		int i = 0;
		qry.setLong(i++, pub.publicidade.idPublicidade);
		qry.setDate(i++, pub.publicidade.dataInicio);
		qry.setDate(i++, pub.publicidade.dataFim);
		qry.setString(i++, pub.publicidade.tipoPublicidade);
		qry.setLong(i++, pub.fichaImovel.imovel.id);
		qry.setLong(i++, pub.publicidade.idFatura);
		qry.executeUpdate();
	}

	public void update(PublicidadeImovelDTO pub) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_STATUS_LIBERACAO);
		int i = 0;
		qry.setString(i++, "L");
		qry.setLong(i++, pub.publicidade.idPublicidade);
		qry.executeUpdate();
	}

	public void update(long idFatura) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_PUBLICIDADE_FATURA);
		int i = 0;
		qry.setString(i++, "L");
		qry.setLong(i++, idFatura);
		qry.executeUpdate();
	}

	public void update(long idImovel, String novoStatus)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();

		//-------------------------------------------
		// Atualizacao dos dados da GeoLocalizacao do imovel
		//-------------------------------------------
		Query qry = session.createSQLQuery(UPD_IMOVEL_STATUS_SORTEIO);
		int i = 0;
		qry.setString(i++, novoStatus);
		qry.setLong(i++, idImovel);
		int n = qry.executeUpdate();
		
		session.flush();
	}


	public void updatePD(long idImovel, String novoStatus)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();

		//-------------------------------------------
		// Atualizacao dos dados da GeoLocalizacao do imovel
		//-------------------------------------------
		Query qry = session.createSQLQuery(UPD_IMOVEL_STATUS_SORTEIO_PD);
		int i = 0;
		qry.setString(i++, novoStatus);
		qry.setLong(i++, idImovel);
		int n = qry.executeUpdate();
		
		session.flush();
	}

	public TipoColaboracaoDTO loadIndicador(long idImovel) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_TIPO_COLABORACAO);
		qry.setLong(0, idImovel);
		List resultado = qry.list();
		
		TipoColaboracaoDTO ifcdto = null;
		if (resultado.size() > 0) {
			Object dto[] = (Object[])resultado.get(0);
			
			int j = -1;
			ifcdto = new TipoColaboracaoDTO();
			ifcdto.indicadorTipoColaboracao = (dto[++j] == null ? null : dto[j].toString());
			ifcdto.valor = (dto[++j] == null ? 0 : Double.valueOf(dto[j].toString()));
		}
		
		return ifcdto;
	}

	public void updateStatus(long codigoImovel, String status)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();

		//-------------------------------------------
		// Atualizacao dos dados do imovel
		//-------------------------------------------
		Query qry = session.createSQLQuery(UPD_IMOVEL_STATUS);
		int i = 0;
		qry.setString(i++, status);
		qry.setLong(i++, codigoImovel);
		int n = qry.executeUpdate();
	}

	public List<Long> listImoveisParaNegativacao() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_NEGATIVACAO);
		qry.setString(0, "A");
		qry.setLong(1, 0);
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;

	}

	public List<Long> listImoveisFuraFila() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_FURA_FILA);
		qry.setString(0, "C");
		qry.setDouble(1, -1);
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}

	public List<Long> listImoveisColaborador(String tipo) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_FURA_FILA);
		qry.setString(0, tipo);
		qry.setDouble(1, -1);
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}

	public List<Long> listImoveisTipoColaboracao(String tipo) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_ID_TIPO_COLABORACAO);
		qry.setString(0, tipo);
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;

	}

	public void updateNotaAnuncioImovel(long idImovel, double pesoNegativacao)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();

		//-------------------------------------------
		// Atualizacao dos dados do imovel
		//-------------------------------------------
		Query qry = session.createSQLQuery(UPD_NOTA_ANUNCIO);
		int i = 0;
		qry.setDouble(i++, pesoNegativacao);
		qry.setLong(i++, idImovel);
		int n = qry.executeUpdate();
	}

	public void updateSaldoFuraFila(long idImovel, double vlr)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();

		//-------------------------------------------
		// Atualizacao do saldo do fura fila
		//-------------------------------------------
		Query qry = session.createSQLQuery(UPD_SALDO_FURA_FILA);
		int i = 0;
		qry.setDouble(i++, vlr);
		qry.setLong(i++, idImovel);
		int n = qry.executeUpdate();
	}
	
	public long countImoveisSorteioSB(String statusSorteio) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_COUNT_PUBLICIDADE_SB);
		qry.setString(0, statusSorteio);
		qry.setString(1, "A");
		List resultado = qry.list();
		
		long retorno = 0;
		if (resultado.size() > 0) {
			retorno = Long.valueOf(resultado.get(0).toString());
		}
		
		return retorno;
	}
	
	public long countImoveisSorteioPD(String statusSorteio) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_COUNT_PUBLICIDADE_PD);
		qry.setString(0, statusSorteio);
		qry.setString(1, "A");
		List resultado = qry.list();
		
		long retorno = 0;
		if (resultado.size() > 0) {
			retorno = Long.valueOf(resultado.get(0).toString());
		}
		
		return retorno;
	}
	
	public long countImoveisSorteioPP(String statusSorteio) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_COUNT_PUBLICIDADE_PP);
		qry.setString(0, statusSorteio);
		qry.setString(1, "A");
		List resultado = qry.list();
		
		long retorno = 0;
		if (resultado.size() > 0) {
			retorno = Long.valueOf(resultado.get(0).toString());
		}
		
		return retorno;
	}

	public List<Long> listImoveis(String status) throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEIS_STATUS);
		qry.setString(0, status);
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());

				lst.add(lngId);
			}
		}
		return lst;
	}

	public void updateStatusColaborador(long codigoImovel, String status, double valor)
		throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		
		//-------------------------------------------
		// Atualizacao dos dados do imovel
		//-------------------------------------------
		Query qry = session.createSQLQuery(UPD_IMOVEL_STATUS_COLABORADOR);
		int i = 0;
		qry.setString(i++, status);
		qry.setDouble(i++, valor);
		qry.setLong(i++, codigoImovel);
		int n = qry.executeUpdate();
	}


	public List<Long> listColaboradorInconsistente() throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_IMOVEL_COLABORADOR_INCONS);
		qry.setString(0, "C");
		qry.setString(1, "P");
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());
		
				lst.add(lngId);
			}
		}
		return lst;
	}
	
	public List<Long> listProximoOferecimento(String uf, String cidade)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_ID_IMOVEL_OFERECIMENTO);
		int idx = 0;
		qry.setString(idx++, "A");
		qry.setString(idx++, "A");
		qry.setString(idx++, uf);
		qry.setString(idx++, cidade);
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());
		
				lst.add(lngId);
			}
		}
		return lst;
	}

	public List<Long> listIDImoveis(String uf, String cidade)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_ID_IMOVEL_UF_CIDADE);
		int idx = 0;
		qry.setString(idx++, "A");
		qry.setString(idx++, "A");
		qry.setString(idx++, uf);
		qry.setString(idx++, cidade);
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());
		
				lst.add(lngId);
			}
		}
		return lst;
	}

	public long loadIDProximoOferecimento(String uf, String cidade)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(SQL_PROXIMO_OFERECIMENTO);
		int idx = 0;
		qry.setString(idx++, "A");
		qry.setString(idx++, "A");
		qry.setString(idx++, uf);
		qry.setString(idx++, cidade);
		List resultado = qry.list();
		List<Long> lst = null;
		
		if ((resultado != null) && (resultado.size() > 0)) {
			lst = new ArrayList<Long>();
			for (int i = 0; i < resultado.size(); i++) {
				Object dtor = (Object)resultado.get(i);
			
				int j = -1;
				Long lngId = (long) Integer.valueOf(dtor.toString());
		
				lst.add(lngId);
			}
		}
		return (lst == null ? -1 : lst.get(0));
	}

	public void updateOferecimento(long id, long seq)
			throws AlugueRelaxeException {
		Session session = daofactory.getSession();
		Query qry = session.createSQLQuery(UPD_SEQ_OFERECIMENTO);
		int i = 0;
		qry.setLong(i++, seq);
		qry.setLong(i++, id);
		qry.executeUpdate();
	}

}
