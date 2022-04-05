package br.com.jcv.aluguerelaxe.client.superpanel.superbanner;

import br.com.jcv.aluguerelaxe.client.componente.anuncio.AbstractAnuncio;
import br.com.jcv.aluguerelaxe.client.componente.anuncio.AnuncioListener;
import br.com.jcv.aluguerelaxe.client.componente.superpanel.AbstractSuperPanel;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

public class ImovelSuperBannerSuperPanel extends AbstractSuperPanel<SuperBannerVO>
	implements 	AnuncioListener {
	
	public ImovelSuperBannerSuperPanel() {
		super();
	}
	
	public void update(SuperBannerVO vo){
		if ((vo.lstipvo != null) && (vo.lstipvo.size() > 0)) {
			this.setHTML(montaAnuncioImovelSuperBanner(vo.lstifcvo.get(0)));
		}
	}

	private AbstractAnuncio montaAnuncioImovelSuperBanner(ImovelFichaCompletaVO ifcvo){
		AbstractAnuncio asb = new AnuncioSuperBanner(ifcvo);
		asb.addAnuncioListener(this);
		return asb;
	}

	public void onAnuncioClick(AbstractAnuncio anuncio) {
		redirect("/arweb/JXControllerSmartInterface?cmd=dtgFichaImovel&id="+anuncio.getImovelFichaCompletaVO().imovel.id + "&o=SB");
	}

	public static native void redirect(String url)/*-{
		$wnd.location = url;
	}-*/;

}
