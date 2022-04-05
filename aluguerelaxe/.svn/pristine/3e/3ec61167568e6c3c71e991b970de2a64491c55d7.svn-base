package br.com.jcv.aluguerelaxe.client.componente.upload;



import java.util.Date;
import java.util.Random;

import br.com.jcv.aluguerelaxe.client.componente.chat.ChatFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;
import br.com.jcv.aluguerelaxe.client.componente.progressbar.BarraProgressoFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.progressbar.BarraProgressoVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractUploadFotoFormComposite extends FormComposite<VOPadrao> 
	implements SubmitCompleteHandler, SubmitHandler {
	
	private static final String PATH_BOTOES = "images/botoes/";

	private FormPanel form;
	protected Hidden idUploadPK;
	private Hidden pid;
	private HorizontalPanel hpUpload;
	private ChatFormComposite chatExplicacao;
	private BarraProgressoFormComposite bpfc;

	// Implementacoes nas classes concretas para informacoes ao form de upload
	public abstract String getTextoChat();
	public abstract String getUrlImagemChat();
	public abstract String getUrlServletUpload();
	public abstract String getUrlServletHostedMode();
	public abstract String getTituloChat();
	public abstract boolean isHostedMode();
	public abstract void updateUploadPK(String pk);

	public AbstractUploadFotoFormComposite() {
		super();
		update();
	}
	
	public FormPanel getFormPanel(){
		return form;
	}
	
	private void update(){
		ChatVO chatvo = new ChatVO();
		chatvo.urlFoto = this.getUrlImagemChat();
		chatvo.chat = this.getTextoChat();
		chatvo.titulo = this.getTituloChat();
		chatvo.dataPost = (new Date()).toString();
		chatExplicacao.update(chatvo);
		
		montaUploadForm();
	}
/*
	public void setProgressBar(ProgressBar pb){
		hpUpload.add(pb);
	}
*/	
	@Override
	public void init() {
		form = new FormPanel();
		
		//Campo livre para colocar qualquer chave. Ex: pk do cliente; pk do imovel
		idUploadPK = new Hidden();
		
		// Campo para identificar a leitura da barra de progresso
		pid = new Hidden();
		pid.setValue(this.gerarCodigo(40));
		
		chatExplicacao = new ChatFormComposite();
		
		// Cria a barra de progresso e a esconde para ser acionada somente no momento
		// do clique do botao enviar
		bpfc = new BarraProgressoFormComposite();
		BarraProgressoVO bpvo = new BarraProgressoVO();
		bpvo.pid = pid.getValue();
		bpfc.update(bpvo);
		bpfc.setVisible(false);

	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		
		hpUpload = new HorizontalPanel();
		DockPanel dp = new DockPanel();
		dp.add(form, DockPanel.CENTER);
		hpUpload.add(dp);
		
		vp.add(chatExplicacao);
		vp.add(hpUpload);

		// Botao para ativar o submit do formulario e enviar o post para servlet
		Image imgEnviar = new Image(PATH_BOTOES + "enviar.png");
		imgEnviar.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				AbstractUploadFotoFormComposite.this.form.submit();
			}
		});
		
		Image imgCancelar = new Image(PATH_BOTOES + "cancelar.png");
		imgCancelar.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) { 
				if (AbstractUploadFotoFormComposite.this.listener != null){
					for (FormCompositeListener fcl : listener) {
						((AbstractUploadFotoFormCompositeListener) fcl).onCancelarClick();
					}
				}
			}
		});
		
		HorizontalPanel hpbtn = new HorizontalPanel();
		hpbtn.add(imgEnviar);
		hpbtn.add(imgCancelar);
		vp.add(hpbtn);
		vp.add(bpfc);
		
		return vp;
	}
	
	private void montaUploadForm() {
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		HorizontalPanel holder = new HorizontalPanel();

		// Campos que ficarao hidden
		idUploadPK.setName("idUploadPK");
		holder.add(idUploadPK);
		
		pid.setName("pid");
		holder.add(pid);
		
		FileUpload upload = new FileUpload();
		upload.setName("upload");
		holder.add(upload);
		
		form.add(holder);

		if (isHostedMode()) {
			//ATENCAO: Action em GWT Hosted Mode (modo debug)
			form.setAction(getUrlServletHostedMode());
		} else {
			// Informa qual servlet irá executar
			form.setAction(getUrlServletUpload());
		}
		
		// Informa os handler de acao para submit
		form.addSubmitCompleteHandler(this);
		form.addSubmitHandler(this);

		//return form;			
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VOPadrao getVO() {
		return null;
	}

	@Override
	public void update(VOPadrao vo) {
	}
	
	public void update(ContatoClienteVO ccvo){
		idUploadPK.setValue(ccvo.id);
		
	}

	public void onSubmit(SubmitEvent event) {
		bpfc.setVisible(true);
	}

	public void onSubmitComplete(SubmitCompleteEvent event) {
		this.notifier(null);
	}

	@Override
	public void notifier(VOPadrao vo) {
		if (this.listener != null){
			for (FormCompositeListener fcl : listener) {
				((AbstractUploadFotoFormCompositeListener) fcl).onUploadFinished();
			}
		}
	}

	private String gerarCodigo(int posicoes) {
		String alimentador = "AB56FGHIijklNOPQLMabcdeRSTrstuvwUVCDE34WXYZmnopqxyz012789JKfgh";
		StringBuilder novoCodigo = new StringBuilder(); 
		
		Random rndgen = new Random();
		for( int i = 0; i < posicoes; i++) {
			int randomInt = rndgen.nextInt(alimentador.length());
			novoCodigo.append(alimentador.substring(randomInt,randomInt+1));
		}
		return novoCodigo.toString();
	}

}
