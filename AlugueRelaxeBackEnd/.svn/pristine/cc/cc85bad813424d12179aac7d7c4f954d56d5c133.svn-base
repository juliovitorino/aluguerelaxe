package br.com.jcv.backend.factory;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.jcv.backend.alerta24h.FirebirdAlertaDAO;
import br.com.jcv.backend.assinantes.AssinantesDTO;
import br.com.jcv.backend.assinantes.FirebirdAssinantesDAO;
import br.com.jcv.backend.caracteristicas.CaracteristicaDTO;
import br.com.jcv.backend.caracteristicas.FirebirdCaracteristicaDAO;
import br.com.jcv.backend.chat.ChatDTO;
import br.com.jcv.backend.chat.FirebirdChatDAO;
import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.cidade.FirebirdCidadeDAO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.cliente.FirebirdClienteDAO;
import br.com.jcv.backend.contatoanunciante.FirebirdContatoAnuncianteDAO;
import br.com.jcv.backend.depoimento.DepoimentoDTO;
import br.com.jcv.backend.depoimento.FirebirdDepoimentoDAO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.filasms.FirebirdFilaSMSDAO;
import br.com.jcv.backend.imovel.FirebirdImovelDAO;
import br.com.jcv.backend.imovel.FirebirdImovelHistoricoVisitaDAO;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelHistoricoVisitaDTO;
import br.com.jcv.backend.imovel.caracteristica.FirebirdImovelCaracteristicaDAO;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.faturamento.FirebirdImovelPlanoFaturaDAO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.imagem.FirebirdImovelImagemVideoDAO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.plano.FirebirdImovelPlanoDAO;
import br.com.jcv.backend.imovel.plano.ImovelPlanoRelacaoDTO;
import br.com.jcv.backend.imovel.publicidade.FirebirdImovelPublicidadeDAO;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.imovel.tabelapreco.FirebirdTabelaPrecoDAO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
import br.com.jcv.backend.imovel.thread.FirebirdContatoAnuncianteThreadDAO;
import br.com.jcv.backend.interfacesdao.AlertaDAO;
import br.com.jcv.backend.interfacesdao.AssinantesDAO;
import br.com.jcv.backend.interfacesdao.CaracteristicaDAO;
import br.com.jcv.backend.interfacesdao.ChatDAO;
import br.com.jcv.backend.interfacesdao.CidadeDAO;
import br.com.jcv.backend.interfacesdao.ClienteDAO;
import br.com.jcv.backend.interfacesdao.ContatoAnuncianteDAO;
import br.com.jcv.backend.interfacesdao.ContatoAnuncianteThreadDAO;
import br.com.jcv.backend.interfacesdao.DepoimentoDAO;
import br.com.jcv.backend.interfacesdao.FilaSMSDAO;
import br.com.jcv.backend.interfacesdao.ImovelCaracteristicaDAO;
import br.com.jcv.backend.interfacesdao.ImovelDAO;
import br.com.jcv.backend.interfacesdao.ImovelHistoricoVisitaDAO;
import br.com.jcv.backend.interfacesdao.ImovelImagemVideoDAO;
import br.com.jcv.backend.interfacesdao.ImovelPlanoDAO;
import br.com.jcv.backend.interfacesdao.ImovelPlanoFaturaDAO;
import br.com.jcv.backend.interfacesdao.ImovelPublicidadeDAO;
import br.com.jcv.backend.interfacesdao.LocalDAO;
import br.com.jcv.backend.interfacesdao.LocatarioDAO;
import br.com.jcv.backend.interfacesdao.MensagemDAO;
import br.com.jcv.backend.interfacesdao.ModoPublicidadeDAO;
import br.com.jcv.backend.interfacesdao.ModoPublicidadeVisitasDAO;
import br.com.jcv.backend.interfacesdao.PaginacaoDAO;
import br.com.jcv.backend.interfacesdao.PainelControleDAO;
import br.com.jcv.backend.interfacesdao.PlanoDAO;
import br.com.jcv.backend.interfacesdao.ReservaDAO;
import br.com.jcv.backend.interfacesdao.TabelaPrecoDAO;
import br.com.jcv.backend.interfacesdao.TemplateDAO;
import br.com.jcv.backend.interfacesdao.TipoAlertaDAO;
import br.com.jcv.backend.interfacesdao.UFDAO;
import br.com.jcv.backend.interfacesdao.VariavelDAO;
import br.com.jcv.backend.local.FirebirdLocalDAO;
import br.com.jcv.backend.local.LocalItemDTO;
import br.com.jcv.backend.locatario.FirebirdLocatarioDAO;
import br.com.jcv.backend.locatario.LocatarioDTO;
import br.com.jcv.backend.mensagem.FirebirdMensagemDAO;
import br.com.jcv.backend.mensagem.MensagemDTO;
import br.com.jcv.backend.modopublicidade.FirebirdModoPublicidadeDAO;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;
import br.com.jcv.backend.modopublicidadevisitas.FirebirdModoPublicidadeVisitasDAO;
import br.com.jcv.backend.modopublicidadevisitas.ModoPublicidadeVisitasDTO;
import br.com.jcv.backend.paginacao.FirebirdPaginacaoDAO;
import br.com.jcv.backend.painelcontrole.FirebirdPainelControleDAO;
import br.com.jcv.backend.plano.FirebirdPlanoDAO;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.reserva.FirebirdReservaDAO;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.template.FirebirdTemplateDAO;
import br.com.jcv.backend.tipoalerta.FirebirdTipoAlertaDAO;
import br.com.jcv.backend.uf.FirebirdUFDAO;
import br.com.jcv.backend.uf.UFDTO;
import br.com.jcv.backend.variavel.FirebirdVariavelDAO;
import br.com.jcv.backend.variavel.VariavelDTO;

/**
 * <h1>FirebirdDAOFactory</h1>
 * <p>Implementações para Abstract DAO Factory para Banco de Dados Firebird.</p>
 *
 * @author Julio Cesar Vitorino
 * @version 1.0
 * @since 30 May 2009
 *
 */
public final class FirebirdDAOFactory extends DAOFactory {
	
	private static final String SQL_NEXT_SEQUENCE = "SELECT GEN_ID(?, 1) FROM RDB$DATABASE";

	public FirebirdDAOFactory() throws AlugueRelaxeException {
	}
	
	public long getNextSequence(DAOFactory daofactory, String sequence) throws AlugueRelaxeException{
		Session session = daofactory.getSession();
		String SQL_NEXT_SEQUENCE = "SELECT GEN_ID(" + sequence + ", 1) FROM RDB$DATABASE";
		Query qry = session.createSQLQuery(SQL_NEXT_SEQUENCE);
		//qry.setString(0, sequence);
		List resultado = qry.list();
		if (resultado.size() > 0){
			return ((BigInteger) resultado.get(0)).longValue();
		} else {
			return -1;
		}
	}

	/**
	 * <h2>getDepoimentoDAO()</h2>
	 * <p>Retorna uma Implementação para a interface DepoimentoDAO.</p>
	 * @return DepoimentoDAO
	 */
	public final DepoimentoDAO<DepoimentoDTO> getDepoimentoDAO(DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdDepoimentoDAO(daofactory);
	}
	/**
	 * <h2>getMensagemDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return DAO
	 */
	public MensagemDAO<MensagemDTO> getMensagemDAO(DAOFactory daofactory)
		throws AlugueRelaxeException {
		return new FirebirdMensagemDAO(daofactory);
	}
	/**
	 * <h2>getVariavelDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return DAO
	 */
	public VariavelDAO<VariavelDTO> getVariavelDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdVariavelDAO(daofactory);
	}
	/**
	 * <h2>getUFDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return DAO
	 */
	public UFDAO<UFDTO> getUFDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdUFDAO(daofactory);
	}
	/**
	 * <h2>getCidadeDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return DAO
	 */
	public CidadeDAO<CidadeDTO> getCidadeDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdCidadeDAO(daofactory);
	}
	/**
	 * <h2>getCaracteristicaDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return DAO
	 */
	public CaracteristicaDAO<CaracteristicaDTO> getCaracteristicaDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdCaracteristicaDAO(daofactory);
	}
	/**
	 * <h2>getImovelDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return DAO
	 */
	public ImovelDAO<ImovelDTO> getImovelDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdImovelDAO(daofactory);
	}
	/**
	 * <h2>getImovelPublicidadeDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return ImovelPublicidadeDAO
	 */
	public ImovelPublicidadeDAO<ImovelPublicidadeDTO> getImovelPublicidadeDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdImovelPublicidadeDAO(daofactory);
	}
	/**
	 * <h2>getClienteDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return ClienteDAO
	 */
	public ClienteDAO<ClienteDTO> getClienteDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdClienteDAO(daofactory);
	}

	/**
	 * <h2>getImovelImagemVideoDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return ImovelImagemVideoDAO
	 */
	public ImovelImagemVideoDAO<ImovelImagemVideoDTO> getImovelImagemVideoDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdImovelImagemVideoDAO(daofactory);
	}

	/**
	 * <h2>getTabelaPrecoDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return TabelaPrecoDAO
	 */
	public TabelaPrecoDAO<TabelaPrecoDTO> getTabelaPrecoDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdTabelaPrecoDAO(daofactory);
	}

	/**
	 * <h2>getImovelCaracteristicaDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return ImovelCaracteristicaDAO
	 */
	public ImovelCaracteristicaDAO<ImovelCaracteristicaDTO> getImovelCaracteristicaDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdImovelCaracteristicaDAO(daofactory);
	}

	/**
	 * <h2>getPlanoDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return PlanoDAO
	 */
	public PlanoDAO<PlanoDTO> getPlanoDAO(DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdPlanoDAO(daofactory);
	}

	/**
	 * <h2>getContatoAnuncianteDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return ContatoAnuncianteDAO
	 */
	public ContatoAnuncianteDAO getContatoAnuncianteDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdContatoAnuncianteDAO(daofactory);
	}

	/**
	 * <h2>getImovelHistoricoVisitaDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return ContatoAnuncianteDAO
	 */
	@Override
	public ImovelHistoricoVisitaDAO<ImovelHistoricoVisitaDTO> getImovelHistoricoVisitaDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdImovelHistoricoVisitaDAO(daofactory);
	}

	/**
	 * <h2>getPainelControleDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return PainelControleDAO
	 */
	@Override
	public PainelControleDAO getPainelControleDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdPainelControleDAO(daofactory);
	}
	
	/**
	 * <h2>getAssinantesDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return AssinantesDAO
	 */
	@Override
	public AssinantesDAO<AssinantesDTO> getAssinantesDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdAssinantesDAO(daofactory);
	}
	
	/**
	 * <h2>getImovelPlanoFaturaDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return ImovelPlanoFaturaDAO
	 */
	@Override
	public ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> getImovelPlanoFaturaDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdImovelPlanoFaturaDAO(daofactory);
	}

	/**
	 * <h2>getChatDAO()</h2>
	 * <p>Retorna uma implementação para a interface DAO.</p>
	 * @return ChatDAO
	 */
	@Override
	public ChatDAO<ChatDTO> getChatDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdChatDAO(daofactory);
	}

	@Override
	public ImovelPlanoDAO<ImovelPlanoRelacaoDTO> getImovelPlanoDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdImovelPlanoDAO(daofactory);
	}

	@Override
	public LocalDAO<LocalItemDTO> getLocalDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdLocalDAO(daofactory);
	}

	@Override
	public ReservaDAO<ReservaDTO> getReservaDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdReservaDAO(daofactory);
	}

	@Override
	public LocatarioDAO<LocatarioDTO> getLocatarioDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdLocatarioDAO(daofactory);
	}

	@Override
	public ModoPublicidadeDAO<ModoPublicidadeDTO> getModoPublicidadeDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdModoPublicidadeDAO(daofactory);
	}

	@Override
	public ModoPublicidadeVisitasDAO<ModoPublicidadeVisitasDTO> getModoPublicidadeVisitasDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdModoPublicidadeVisitasDAO(daofactory);
	}

	@Override
	public PaginacaoDAO getPaginacaoDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdPaginacaoDAO(daofactory);
	}

	@Override
	public FilaSMSDAO getFilaSMSDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdFilaSMSDAO(daofactory);
	}

	@Override
	public TipoAlertaDAO getTipoAlertaDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdTipoAlertaDAO(daofactory);
	}

	@Override
	public AlertaDAO getAlertaDAO(DAOFactory daofactory)
			throws AlugueRelaxeException {
		return new FirebirdAlertaDAO(daofactory);
	}

	@Override
	public ContatoAnuncianteThreadDAO getContatoAnuncianteThreadDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdContatoAnuncianteThreadDAO(daofactory);
	}

	@Override
	public TemplateDAO getTemplateDAO(
			DAOFactory daofactory) throws AlugueRelaxeException {
		return new FirebirdTemplateDAO(daofactory);
	}

}