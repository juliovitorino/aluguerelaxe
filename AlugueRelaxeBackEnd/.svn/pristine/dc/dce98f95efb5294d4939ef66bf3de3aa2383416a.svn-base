package br.com.jcv.backend.factory;

import org.hibernate.Session;

import br.com.jcv.backend.assinantes.AssinantesDTO;
import br.com.jcv.backend.caracteristicas.CaracteristicaDTO;
import br.com.jcv.backend.chat.ChatDTO;
import br.com.jcv.backend.cidade.CidadeDTO;
import br.com.jcv.backend.cliente.ClienteDTO;
import br.com.jcv.backend.constantes.Constantes;
import br.com.jcv.backend.depoimento.DepoimentoDTO;
import br.com.jcv.backend.exception.AlugueRelaxeException;
import br.com.jcv.backend.imovel.ImovelDTO;
import br.com.jcv.backend.imovel.ImovelHistoricoVisitaDTO;
import br.com.jcv.backend.imovel.caracteristica.ImovelCaracteristicaDTO;
import br.com.jcv.backend.imovel.faturamento.ImovelPlanoFaturaDTO;
import br.com.jcv.backend.imovel.imagem.ImovelImagemVideoDTO;
import br.com.jcv.backend.imovel.plano.ImovelPlanoRelacaoDTO;
import br.com.jcv.backend.imovel.publicidade.ImovelPublicidadeDTO;
import br.com.jcv.backend.imovel.tabelapreco.TabelaPrecoDTO;
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
import br.com.jcv.backend.local.LocalItemDTO;
import br.com.jcv.backend.locatario.LocatarioDTO;
import br.com.jcv.backend.mensagem.MensagemDTO;
import br.com.jcv.backend.modopublicidade.ModoPublicidadeDTO;
import br.com.jcv.backend.modopublicidadevisitas.ModoPublicidadeVisitasDTO;
import br.com.jcv.backend.plano.PlanoDTO;
import br.com.jcv.backend.reserva.ReservaDTO;
import br.com.jcv.backend.uf.UFDTO;
import br.com.jcv.backend.utility.GlobalStartup;
import br.com.jcv.backend.variavel.VariavelDTO;

/**
 * Fabrica de DAO.
 *
 * @author Julio Cesar Vitorino
 *
 */
public abstract class DAOFactory {

    protected Session session = null;
    private boolean isOpen = false;

	/**
	 * Retorna a instancia das fabricas DAO de acordo com a configuracao em
	 * properties.
	 * @return fabrica DAO.
	 * @throws SigemException Caso ocorra algum erro ao adquirir a fabrica.
	 */
	public static final DAOFactory getDAOFactory() throws AlugueRelaxeException {
		GlobalStartup gc;
		
		try {
			gc = GlobalStartup.getInstance();
		} catch (Exception e) {
			AlugueRelaxeException se = new AlugueRelaxeException();
			throw se;
		}
		switch (gc.getDatabase()) {
		case Constantes.DATABASE_FIREBIRD:
			return new FirebirdDAOFactory();
		case Constantes.DATABASE_INTERBASE:
		case Constantes.DATABASE_ORACLE:
		case Constantes.DATABASE_SQLSERVER:
		case Constantes.DATABASE_POSTGRESQL:
		case Constantes.DATABASE_DB2:
		case Constantes.DATABASE_MYSQL:
		case Constantes.DATABASE_INFORMIX:
		default:
			return null;
		}
	}

	/**
	 * @return Instancia da classe DAO que gerencia informacoes
	 *  pertinentes a empresa.
	 * @throws SigemDAOException Caso ocorra erro de banco.
	 */
	public abstract long getNextSequence(DAOFactory daofactory, String sequence) throws AlugueRelaxeException;
	public abstract DepoimentoDAO<DepoimentoDTO> getDepoimentoDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract MensagemDAO<MensagemDTO> getMensagemDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract VariavelDAO<VariavelDTO> getVariavelDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract UFDAO<UFDTO> getUFDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract CidadeDAO<CidadeDTO> getCidadeDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract CaracteristicaDAO<CaracteristicaDTO> getCaracteristicaDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ImovelDAO<ImovelDTO> getImovelDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ClienteDAO<ClienteDTO> getClienteDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ImovelImagemVideoDAO<ImovelImagemVideoDTO> getImovelImagemVideoDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ImovelPublicidadeDAO<ImovelPublicidadeDTO> getImovelPublicidadeDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract TabelaPrecoDAO<TabelaPrecoDTO> getTabelaPrecoDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract PlanoDAO<PlanoDTO> getPlanoDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ImovelCaracteristicaDAO<ImovelCaracteristicaDTO> getImovelCaracteristicaDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ContatoAnuncianteDAO getContatoAnuncianteDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ImovelHistoricoVisitaDAO<ImovelHistoricoVisitaDTO> getImovelHistoricoVisitaDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract PainelControleDAO getPainelControleDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract AssinantesDAO<AssinantesDTO> getAssinantesDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ImovelPlanoFaturaDAO<ImovelPlanoFaturaDTO> getImovelPlanoFaturaDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ChatDAO<ChatDTO> getChatDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ImovelPlanoDAO<ImovelPlanoRelacaoDTO> getImovelPlanoDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract LocalDAO<LocalItemDTO> getLocalDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ReservaDAO<ReservaDTO> getReservaDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract LocatarioDAO<LocatarioDTO> getLocatarioDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ModoPublicidadeDAO<ModoPublicidadeDTO> getModoPublicidadeDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ModoPublicidadeVisitasDAO<ModoPublicidadeVisitasDTO> getModoPublicidadeVisitasDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract PaginacaoDAO getPaginacaoDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract FilaSMSDAO getFilaSMSDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract TipoAlertaDAO getTipoAlertaDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract AlertaDAO getAlertaDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract ContatoAnuncianteThreadDAO getContatoAnuncianteThreadDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	public abstract TemplateDAO getTemplateDAO(DAOFactory daofactory) throws AlugueRelaxeException;
	
	public Session getSession() {
		return this.session;
	}
	
	public final Session open() {
		this.session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		this.isOpen = true;
		return this.session;
	}
	
	public void beginTransaction () {
		this.session.beginTransaction();
	}
	
	public void commit() {
		this.session.getTransaction().commit();
	}
	
	public void rollback() {
		this.session.getTransaction().rollback();
	}
	
	/**
	 * Fecha a conexao com o BD.
	 * @throws Exception Caso ocorra algum erro
	 * durante a operacao.
	 */
	public final void close() throws Exception {
		if (this.session != null && this.isOpen && this.session.isOpen()) {
			this.session.close();
		}
	}

}