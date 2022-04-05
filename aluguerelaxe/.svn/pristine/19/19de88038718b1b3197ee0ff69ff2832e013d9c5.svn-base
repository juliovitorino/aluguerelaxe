package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.progressbar.ProgressBar;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UploadFormComposite extends FormComposite<ImovelImagemVideoVO> 
	implements SubmitCompleteHandler, SubmitHandler {

	private FormPanel form;
	private Hidden txtIdImovel;
	private Hidden txtIdCliente;
	private Hidden txtIdSessao;
	private HorizontalPanel hpUpload;

	public UploadFormComposite() {
		super();
	}
	
	public UploadFormComposite(long idCliente, long idImovel) {
		this();
		txtIdCliente.setValue(String.valueOf(idCliente));
		txtIdImovel.setValue(String.valueOf(idImovel));
		txtIdSessao.setValue(String.valueOf(System.currentTimeMillis()));
	}
	
	public void setProgressBar(ProgressBar pb){
		hpUpload.add(pb);
	}
	
	@Override
	public void init() {
		form = new FormPanel();
		txtIdImovel = new Hidden();
		txtIdCliente = new Hidden();
		txtIdSessao = new Hidden();
	}
	
	@Override
	public Widget render() {
		hpUpload = new HorizontalPanel();
		DockPanel dp = new DockPanel();
		dp.add(montaUploadForm(), DockPanel.CENTER);
		hpUpload.add(dp);
		return hpUpload;
	}

	private Widget montaUploadForm() {
		//final FormPanel form = new FormPanel();
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		form.setWidth("400px");

		HorizontalPanel holder = new HorizontalPanel();

		// Campos que ficarão hidden
		txtIdImovel.setName("idImovel");
		holder.add(txtIdImovel);
		
		txtIdCliente.setName("idCliente");
		holder.add(txtIdCliente);

		txtIdSessao.setName("idSessao");
		holder.add(txtIdSessao);
		
		
		FileUpload upload = new FileUpload();
		upload.setName("upload");
		holder.add(upload);

		
		//holder.setHorizontalAlignment(HasAlignment.ALIGN_RIGHT);
		
		form.add(holder);
		
		// Informa qual servlet irá executar
		form.setAction("/arweb/uploader.svlt");
		
		//ATENCAO: Action em GWT Hosted Mode
		//form.setAction("/uploader.svlt");
		
		// Informa os handler de acao para submit
		form.addSubmitCompleteHandler(this);
		form.addSubmitHandler(this);

		return form;			
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	public UploadVO getUploadCampos() {
		UploadVO vo = new UploadVO();
		vo.IdImovel = Long.valueOf(this.txtIdImovel.getValue());
		vo.IdCliente = Long.valueOf(this.txtIdCliente.getValue());
		vo.IdSessao = this.txtIdSessao.getValue();
		return vo;
	}

	@Override
	public ImovelImagemVideoVO getVO() {
		return null;
	}

	@Override
	public void update(ImovelImagemVideoVO vo) {
		
	}

	public FormPanel getFp() {
		return form;
	}

	public void onSubmit(SubmitEvent event) {
	}

	public void onSubmitComplete(SubmitCompleteEvent event) {
	}

	@Override
	public void notifier(ImovelImagemVideoVO vo) {
		// TODO Auto-generated method stub
		
	}



}
