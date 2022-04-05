package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;


import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.dialog.AbstractDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;

public class UploadFotoPerfilDialogModal extends AbstractDialogModal<UploadFotoPerfilFormComposite> {

	private UploadFotoPerfilFormComposite ufpfc;
	
	public UploadFotoPerfilDialogModal(){
		super();
	}
	
	public void updateHandler(FormCompositeListener fc) {
		ufpfc.addListener(fc);
	}
	
	public void update(ClienteVO catvo) {
		ufpfc.update(catvo);
	}
	
	@Override
	public UploadFotoPerfilFormComposite render() {
		return ufpfc;
	}
	
	@Override
	public void init() {
		ufpfc = new UploadFotoPerfilFormComposite();
	}

}
