package br.com.jcv.aluguerelaxe.client.mobile.main;

import br.com.jcv.aluguerelaxe.client.mobile.vo.EnderecoVO;
import br.com.jcv.ui.client.componente.filtro.AbstractFiltro;
import br.com.jcv.ui.client.componente.filtro.FiltroListener;

import com.google.gwt.event.dom.client.ClickEvent;


public class FiltroBase 
	extends AbstractFiltro<EnderecoVO,FiltroImovelComposite> 
	implements FiltroListener<EnderecoVO>{
	
	public FiltroBase() {
		super();
		this.addFiltroListener(this);
	}

	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FiltroImovelComposite getFiltroComposite() {
		return new FiltroImovelComposite();
	}

	public void onFiltroAplicar(AbstractFiltro filtroImpl,
			EnderecoVO vofiltro) {
		
		if (vofiltro.codigoUfCidadeItem == -1){
			redirect("/armob/resultado.html?atvd=0&uf="+vofiltro.uf+"&cidade=-1");
		} else {
			redirect("/armob/resultado.html?atvd=1&uf=XX&cidade=" + vofiltro.codigoUfCidadeItem);
		}
	}

	public static native void redirect(String url)/*-{
	      $wnd.location = url;
	  }-*/;


}
