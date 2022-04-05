package br.com.jcv.aluguerelaxe.client.imovel.thread;

import br.com.jcv.aluguerelaxe.client.componente.dialog.AbstractDialogModal;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ContatoClienteVO;

public class UploadFotoThreadDialogModal extends AbstractDialogModal<UploadFotoThreadFormComposite> {

	private UploadFotoThreadFormComposite uftfc;
	
	public UploadFotoThreadDialogModal(){
		super();
	}
	
	public void updateHandler(FormCompositeListener fc) {
		uftfc.addListener(fc);
	}
	
	
	public void update(ContatoClienteVO ccvo) {
		uftfc.update(ccvo);
	}
	
	@Override
	public UploadFotoThreadFormComposite render() {
		return uftfc;
	}
	
	@Override
	public void init() {
		uftfc = new UploadFotoThreadFormComposite();
	}

}
