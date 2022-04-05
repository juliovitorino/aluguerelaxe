package br.com.jcv.aluguerelaxe.client.parameter;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractDataGridParameter;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;

public class PublicidadeAVencerParameter extends AbstractDataGridParameter<PlanosPagosAVencerFormComposite> {

	public PublicidadeAVencerParameter(
			PlanosPagosAVencerFormComposite filtroComposite) {
		super(filtroComposite);
	}

	@Override
	public List<CondicaoVO> getListVO() {
		ArrayList<CondicaoVO> lst = null;
		ClienteVO vo = this.getFiltroComposite().getVO();
		if (vo != null){
			lst = new ArrayList<CondicaoVO>();
			
			// ClienteParameterFormComposite tem apenas 1 campos populados
			lst.add(new CondicaoVO("ID_CLIENTE", "=", String.valueOf(vo.id) ));
			
		}
		
		return lst;
	}

}
