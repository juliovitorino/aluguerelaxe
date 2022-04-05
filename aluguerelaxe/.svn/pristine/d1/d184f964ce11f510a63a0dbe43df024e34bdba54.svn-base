package br.com.jcv.aluguerelaxe.client.administracao.console.trocasenha;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.cliente.ClienteRPCAsync;
import br.com.jcv.aluguerelaxe.client.componente.dialog.ConfirmarDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.wizard.AbstractWizard;
import br.com.jcv.aluguerelaxe.client.componente.wizard.WizardListener;
import br.com.jcv.aluguerelaxe.client.novaconta.ClienteCadastroVO;
import br.com.jcv.aluguerelaxe.client.novaconta.NovaSenhaFormComposite;
import br.com.jcv.aluguerelaxe.client.novaconta.NovaSenhaVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class AssistenteTrocaSenha extends AbstractWizard<NovaSenhaFormComposite, ClienteCadastroVO> implements WizardListener{
	
	private NovaSenhaFormComposite nsfc;
	private SessaoVO sessaovo;
	
	public AssistenteTrocaSenha() {
		super();
		this.setStylePrimaryName("gwt-AssistenteTrocaSenha");
		addWizardListener(this);
		addWizard(nsfc, montaHeaderPasso("house.png", "Bem vindo ao troca senha"));
		init();
	}

	@Override
	public ClienteCadastroVO getVO() {
		ClienteCadastroVO vo = new ClienteCadastroVO();
		NovaSenhaVO nsvo = nsfc.getVO();
		vo.id = sessaovo.clientevo.id;
		vo.senha = nsvo.senha;
		vo.senhaConfirmacao = nsvo.contrasenha;
		return vo;
	}

	public void onVoltarClick() {
		// TODO Auto-generated method stub
		
	}

	public void onProximoClick() {
		// TODO Auto-generated method stub
		
	}

	public void onConcluirClick() {
		ClienteRPCAsync rpc = ServicosRPC.getClienteRPC();
		AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {

			public void onFailure(Throwable caught) {
				try {
					AssistenteTrocaSenha.this.forcarVisibilidadeBotao(BOTAO_CONCLUIR, true);
					throw caught;
				} catch (IncompatibleRemoteServiceException e) {
					AssistenteTrocaSenha.this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (InvocationException e) {
					AssistenteTrocaSenha.this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
				} catch (AlugueRelaxeFrontException e) {
					if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
						AssistenteTrocaSenha.this.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
					} else {
						AssistenteTrocaSenha.this.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
					}
				} catch (Throwable e) {
					AssistenteTrocaSenha.this.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
				}
			}

			public void onSuccess(VOPadrao result) {
				ConfirmarDialogModal cdm = new ConfirmarDialogModal("Troca Senha",
						"Sua senha foi alterada com sucesso!", ConfirmarDialogModal.TIPO_MODAL_INFO);				
			}
		};
		
		rpc.trocarSenha(this.getVO(), callback);
	}

	@Override
	public void initComposites() {
		nsfc = new NovaSenhaFormComposite();
	}
	
	public void setSessao(SessaoVO vo) {
		this.sessaovo = vo;
	}



}
