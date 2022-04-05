package br.com.jcv.aluguerelaxe.client.administracao.console.migrarplano;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard.InstrucoesAssistenteNovoImovelFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.console.imovelwizard.PlanoFormComposite;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

/**
 *<h2>AssistenteNovoImovel</h2>
 *<p>Classe concreta para cria\u00e7\u00e4o de nova conta.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
 public class AssistenteMigrarPlano extends AbstractWizard<FormComposite<?>, ImovelPlanoRelacaoVO > implements WizardListener, AsyncCallback<VOPadrao>  {
	 
	private InstrucoesAssistenteNovoImovelFormComposite ianifc;
	private PlanoFormComposite pfc;
	private SessaoVO sesssaovo;
	private ImovelFichaCompletaVO ifcvo = null;	
		
	public AssistenteMigrarPlano() {
		super();
		this.setStylePrimaryName("gwt-AssistenteNovoImovel");
		addWizardListener(this);
		
		addWizard(ianifc, montaHeaderPasso("house.png", "Bem vindo ao Assistente de Migra\u00e7\u00e3o"));
		addWizard(pfc, montaHeaderPasso("house.png", "Selecione o novo Plano de Pagamento"));
		
		init();
	}
	
	public void update(ImovelFichaCompletaVO ifcvo) {
		this.ifcvo = ifcvo;
	}

	@Override
	public ImovelPlanoRelacaoVO getVO() {
		ImovelPlanoVO ipvo = this.pfc.getVO();
		
		ImovelPlanoRelacaoVO vo = new ImovelPlanoRelacaoVO();
		vo.idImovel = this.ifcvo.imovel.id;
		vo.idPlano = ipvo.plano.id;
		return vo;
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<VOPadrao> callback = this;
		ImovelPlanoRelacaoVO vo = this.getVO();
		rpc.migrarPlanoImovel(vo.idImovel, vo.idPlano, callback);
	}

	public void onFailure(Throwable caught) {
		try {
			this.forcarVisibilidadeBotao(BOTAO_CONCLUIR, true);
			throw caught;
		} catch (IncompatibleRemoteServiceException e) {
			this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (InvocationException e) {
			this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		} catch (AlugueRelaxeFrontException e) {
			if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
				this.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
			} else {
				this.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
			}
		} catch (Throwable e) {
			this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		}	
	}

	public void onSuccess(VOPadrao result) {
		this.setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
		
	}
	
	public void setSessao(SessaoVO vo) {
		this.sesssaovo = vo;
	}

	@Override
	public void initComposites() {
		ianifc = new InstrucoesAssistenteNovoImovelFormComposite();
		pfc = new PlanoFormComposite();
	}
}
