package br.com.jcv.aluguerelaxe.client.doacao;

import br.com.jcv.aluguerelaxe.client.componente.indicadorgauge.AbstractIndicadorGauge;
import br.com.jcv.aluguerelaxe.client.componente.indicadorgauge.LimitesGauge;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h1>Medidor para doacoes</h1>
 * @author Julio
 *
 */
public class DoacaoGauge extends AbstractIndicadorGauge<DoacaoVO> {
	
	private final static int META_MENSAL_DOACAO = 1768;

	public DoacaoGauge() {
		super();
	}

	@Override
	public void init() {
		super.setLimiteMaximo(META_MENSAL_DOACAO);
		super.setValor(0); // valor hipotetico... esse valor sera lido da base
		super.setTitulo("Total");

		lstLimiteGauge.add(new LimitesGauge(0,500));
		lstLimiteGauge.add(new LimitesGauge(500,1200));
		lstLimiteGauge.add(new LimitesGauge(1200,META_MENSAL_DOACAO));
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("Precisamos R$ 1.768"));
		
		return vp;
	}

	@Override
	public void update(DoacaoVO vo) {
		super.setValor(vo.totalGeral);
		super.criaGauge();
	}

}
