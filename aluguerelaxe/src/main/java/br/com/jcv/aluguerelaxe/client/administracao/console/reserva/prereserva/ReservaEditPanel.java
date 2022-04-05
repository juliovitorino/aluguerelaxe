package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.prereserva;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.EmailVO;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.LocatarioVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaRPCAsync;
import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.login.SessaoVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractFormEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.form.EnderecoFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.vo.EnderecoVO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;

public class ReservaEditPanel extends
		AbstractFormEditPanel<ReservaToolbar , WindowPanel, ReservaVO> 
		implements ReservaToolbarListener {
		
	private static final int ORDEM_EMAIL_RESERVA = 0;
	private static final int ORDEM_LOCATARIO = 1;
	private static final int ORDEM_ENDERECO = 2;
	private static final int ORDEM_RESERVA = 3;
	private static final int ORDEM_IMOVEL_RESERVA = 4;
	
	private LocatarioFormComposite lfc = new LocatarioFormComposite();
	private EnderecoLocatarioReservaFormComposite efc = new EnderecoLocatarioReservaFormComposite();
	private ReservaFormComposite rfc = new ReservaFormComposite();
	private EmailReservaFormComposite erfc = new EmailReservaFormComposite();
	private ImovelReservaFormComposite irfc = new ImovelReservaFormComposite();

	private static final String RESERVA_FORM_TEMA = "orkut";
	private SessaoVO sessaovo = null;

	public ReservaEditPanel(SessaoVO sessao, ReservaToolbar toolbar) {
		super(toolbar);
		
		this.sessaovo = sessao;

		WindowPanel wpemail = new WindowPanel("Email do locat\u00e1rio",RESERVA_FORM_TEMA,false,false,false);
		wpemail.setWidth("960px");
		wpemail.setComponenteCenter(erfc);

		WindowPanel wpCliente = new WindowPanel("Dados Cadastrais",RESERVA_FORM_TEMA,false,false,false);
		wpCliente.setWidth("960px");
		wpCliente.setComponenteCenter(lfc);

		WindowPanel wpEC = new WindowPanel("Endere\u00e7o Cadastral",RESERVA_FORM_TEMA,false,false,false);
		wpEC.setWidth("960px");
		wpEC.setComponenteCenter(efc);

		WindowPanel wpreserva = new WindowPanel("Reserva",RESERVA_FORM_TEMA,false,false,false);
		wpreserva.setWidth("960px");
		wpreserva.setComponenteCenter(rfc);
		
		WindowPanel wpimovel = new WindowPanel("Im\u00f3vel para reserva",RESERVA_FORM_TEMA,false,false,false);
		wpimovel.setWidth("960px");
		wpimovel.setComponenteCenter(irfc);
		
		addObjetoCompositeToPanel(wpemail);
		addObjetoCompositeToPanel(wpCliente);
		addObjetoCompositeToPanel(wpEC);
		addObjetoCompositeToPanel(wpreserva);
		addObjetoCompositeToPanel(wpimovel);
		
		// Registra os ouvintes de EmailReservaFormComposite
		this.erfc.addListener(lfc);
		this.erfc.addListener(efc);
	}
 
	@Override
	public ReservaVO getVO(List<WindowPanel> composite) {
		WindowPanel wpCliente = composite.get(ORDEM_LOCATARIO);
		WindowPanel wpEC = composite.get(ORDEM_ENDERECO);
		WindowPanel wpreserva = composite.get(ORDEM_RESERVA);
		WindowPanel wpemail = composite.get(ORDEM_EMAIL_RESERVA);
		WindowPanel wpimovel = composite.get(ORDEM_IMOVEL_RESERVA);
		
		LocatarioFormComposite cfc = (LocatarioFormComposite) wpCliente.getComponenteCenter();
		EnderecoFormComposite efc = (EnderecoFormComposite) wpEC.getComponenteCenter();
		ReservaFormComposite rfc = (ReservaFormComposite) wpreserva.getComponenteCenter();
		EmailReservaFormComposite erfc = (EmailReservaFormComposite) wpemail.getComponenteCenter();
		ImovelReservaFormComposite irfc = (ImovelReservaFormComposite) wpimovel.getComponenteCenter();
		
		ImovelFichaCompletaVO ifcvo = irfc.getVO();
		EmailVO emailvo = erfc.getVO();
		LocatarioVO locatariovo = cfc.getVO();
		
		if ((locatariovo.email == null) || ((locatariovo.email != null) && (locatariovo.email.length() == 0))){
			locatariovo.email = emailvo.email;
		}
		
		EnderecoVO endvo = efc.getVO();
		locatariovo.endereco = endvo;
		
		ReservaVO vo = rfc.getVO();
		vo.locatario = locatariovo;
		vo.ifcdto = ifcvo;

		return vo;
	}


	public void onCriarReservaClick() {
		ReservaVO vo = this.getVO(getListObjetoComposite());
		ReservaRPCAsync rpc = ServicosRPC.getReservaRPC();
		AsyncCallback<ReservaVO> callback = new AsyncCallback<ReservaVO>() {

		public void onFailure(Throwable caught) {
		     try {
		       throw caught;
		     } catch (IncompatibleRemoteServiceException e) {
				getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		     } catch (InvocationException e) {
				getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		     } catch (AlugueRelaxeFrontException e) {
		    	 if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
		    		 getAreaNotificacao().setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
		    	 } else {
		    		 getAreaNotificacao().setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
		    	 }
		     } catch (Throwable e) {
				getAreaNotificacao().setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
		     }
		 }

		 public void onSuccess(ReservaVO result) {
			 getAreaNotificacao().setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
		 }
		};
		
		rpc.criarPreReserva(vo, callback);
	}

	@Override
	public ReservaVO getVO(WindowPanel composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
