package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPCAsync;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.form.EnderecoFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.griddataentry.TelefoneGridDataEntry;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ManterClienteToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.ManterClienteToolbarListener;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.vo.DadosBancariosVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class ProprietarioEditPanel extends
		AbstractFormEditPanel<ManterClienteToolbar, WindowPanel, ClienteVO>
		implements ManterClienteToolbarListener {

	private static final String CLIENTE_FORM_TEMA = "orkut";
	private SessaoVO sessaovo = null;

	public ProprietarioEditPanel(SessaoVO sessao, ManterClienteToolbar toolbar) {
		super(toolbar);

		this.sessaovo = sessao;

		WindowPanel wpCliente = new WindowPanel("Dados Cadastrais",
				CLIENTE_FORM_TEMA, true, true, true);
		wpCliente.setSize("960px", "330px");
		wpCliente.setComponenteCenter(new ClienteFormComposite());

		WindowPanel wpEC = new WindowPanel("Endere\u00e7o Cadastral",
				CLIENTE_FORM_TEMA, true, true, true);
		wpEC.setSize("960px", "200px");
		wpEC.setComponenteCenter(new EnderecoFormComposite());

		WindowPanel wpDB = new WindowPanel("Dados Banc\u00e1rios",
				CLIENTE_FORM_TEMA, true, true, true);
		wpDB.setSize("960px", "200px");
		wpDB.setComponenteCenter(new DadosBancariosFormComposite());

		WindowPanel wpfones2 = new WindowPanel("Telefones de Contato",
				CLIENTE_FORM_TEMA, true, true, true);
		wpfones2.setWidth("960px");
		wpfones2.setComponenteCenter(new TelefoneGridDataEntry());

		addObjetoCompositeToPanel(wpCliente);
		addObjetoCompositeToPanel(wpEC);
		addObjetoCompositeToPanel(wpDB);
		addObjetoCompositeToPanel(wpfones2);

		update();
	}

	@Override
	public ClienteVO getVO(List<WindowPanel> composite) {
		WindowPanel wpCliente = composite.get(0);
		WindowPanel wpEC = composite.get(1);
		WindowPanel wpDB = composite.get(2);
		WindowPanel wpfones2 = composite.get(3);

		ClienteFormComposite cfc = (ClienteFormComposite) wpCliente
				.getComponenteCenter();
		EnderecoFormComposite efc = (EnderecoFormComposite) wpEC
				.getComponenteCenter();
		DadosBancariosFormComposite dbfc = (DadosBancariosFormComposite) wpDB
				.getComponenteCenter();
		TelefoneGridDataEntry tgde = (TelefoneGridDataEntry) wpfones2
				.getComponenteCenter();

		ClienteVO vo = cfc.getVO();
		EnderecoVO endvo = efc.getVO();
		DadosBancariosVO dbvo = dbfc.getVO();
		vo.endereco = endvo;
		vo.db = dbvo;
		vo.telefones = tgde.getListVO();
		return vo;
	}

	@Override
	public ClienteVO getVO(WindowPanel composite) {
		return null;
	}

	public void onSalvarClick() {
		ClienteVO vo = this.getVO(getListObjetoComposite());
		vo.id = sessaovo.clientevo.id;
		ClienteRPCAsync rpc = ServicosRPC.getClienteRPC();
		AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {

			public void onFailure(Throwable caught) {
				try {
					throw caught;
				} catch (IncompatibleRemoteServiceException e) {
					getAreaNotificacao().setMensagem(e.getMessage(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (InvocationException e) {
					getAreaNotificacao().setMensagem(e.getMessage(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (AlugueRelaxeFrontException e) {
					if ((e.getListaErros() != null)
							&& (e.getListaErros().size() > 0)) {
						getAreaNotificacao().setMensagem(e.getListaErros(),
								AreaNotificacao.NOTIFICACAO_ERRO);
					} else {
						getAreaNotificacao().setMensagem(e.getMensagem(),
								AreaNotificacao.NOTIFICACAO_ERRO);
					}
				} catch (Throwable e) {
					getAreaNotificacao().setMensagem(e.getMessage(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				}
			}

			public void onSuccess(VOPadrao result) {
				getAreaNotificacao().setMensagem(result.msgcodeString,
						AreaNotificacao.NOTIFICACAO_INFO);
			}
		};

		rpc.atualizarFichaCadastro(vo, callback);
	}

	public void onSairClick() {
		// TODO Auto-generated method stub

	}

	public void update() {
		ClienteRPCAsync rpc = ServicosRPC.getClienteRPC();
		AsyncCallback<ClienteVO> callback = new AsyncCallback<ClienteVO>() {

			public void onFailure(Throwable caught) {
				try {
					throw caught;
				} catch (IncompatibleRemoteServiceException e) {
					getAreaNotificacao().setMensagem(e.getMessage(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (InvocationException e) {
					getAreaNotificacao().setMensagem(e.getMessage(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (AlugueRelaxeFrontException e) {
					getAreaNotificacao().setMensagem(e.getMensagem(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (Throwable e) {
					getAreaNotificacao().setMensagem(e.getMessage(),
							AreaNotificacao.NOTIFICACAO_ERRO);
				}
			}

			public void onSuccess(ClienteVO result) {
				List<WindowPanel> composite = getListObjetoComposite();
				WindowPanel wpCliente = composite.get(0);
				ClienteFormComposite cfc = (ClienteFormComposite) wpCliente
						.getComponenteCenter();

				WindowPanel wpEC = composite.get(1);
				EnderecoFormComposite efc = (EnderecoFormComposite) wpEC
						.getComponenteCenter();

				WindowPanel wpDB = composite.get(2);
				DadosBancariosFormComposite dbfc = (DadosBancariosFormComposite) wpDB
						.getComponenteCenter();

				WindowPanel wpfones2 = composite.get(3);
				TelefoneGridDataEntry tgde = (TelefoneGridDataEntry) wpfones2
						.getComponenteCenter();

				cfc.update(result);
				efc.update(result.endereco);
				dbfc.update(result.db);
				tgde.update(result.telefones);

			}
		};

		rpc.pesquisaRegistro(this.sessaovo.clientevo, callback);
	}

	public void onRefreshClick() {
		// TODO Auto-generated method stub

	}

	public void onRemoverClick() {
		// Nao implementado pois Toolbar desta classe não possui render de
		// Remover

	}

}
