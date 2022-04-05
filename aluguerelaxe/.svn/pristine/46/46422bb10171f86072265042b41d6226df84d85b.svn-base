package br.com.jcv.aluguerelaxe.client.parameter;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.AbstractDataGridParameter;
import br.com.jcv.aluguerelaxe.client.componente.datagrid.CondicaoVO;


/**
 * <h2>ClienteParameter</h2>
 * <p>Classe concreta para criar um Formulário de captacao de criterios de pesquisa
 * de cliente
 * </p>
 *
 * @author Julio Vitorino
 *
 */
public class ClienteParameter extends AbstractDataGridParameter<StatusFormComposite> {

	
	public ClienteParameter(StatusFormComposite cpfc){
		super(cpfc);
	}
	
	@Override
	public List<CondicaoVO> getListVO() {
		ArrayList<CondicaoVO> lst = null;
		ClienteVO vo = this.getFiltroComposite().getVO();
		if (vo != null){
			lst = new ArrayList<CondicaoVO>();
			
			// ClienteParameterFormComposite tem apenas 1 campos populados
			lst.add(new CondicaoVO("CLIE_IN_STATUS", "=", "'" + vo.indicadorStatus + "'"));
			
		}
		
		return lst;
	}

}