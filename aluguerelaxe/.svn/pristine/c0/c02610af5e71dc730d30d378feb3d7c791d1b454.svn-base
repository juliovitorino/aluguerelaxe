package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.led.HLedAmarelo;
import br.com.jcv.aluguerelaxe.client.componente.led.HLedVerde;
import br.com.jcv.aluguerelaxe.client.componente.led.HLedVermelho;
import br.com.jcv.aluguerelaxe.client.componente.vumeter.VUMeter;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class QualidadeAnuncioFormComposite extends FormComposite<VOPadrao> {

	private VUMeter vuqa;
	
	public QualidadeAnuncioFormComposite(){
		super();
	}
	
	@Override
	public VOPadrao getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(double pesoMaximo, double pesoAtual){
		vuqa.update(pesoMaximo, pesoAtual);
	}

	@Override
	public void notifier(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		DockPanel dp = new DockPanel();
		VerticalPanel vp = new VerticalPanel();
		vp.add(vuqa);
		dp.add(vp, DockPanel.WEST);
		return dp;
	}

	@Override
	public void init() {
		vuqa = new VUMeter();
		vuqa.addLed(new HLedVerde());
		vuqa.addLed(new HLedVerde());
		vuqa.addLed(new HLedVerde());
		vuqa.addLed(new HLedVerde());
		vuqa.addLed(new HLedVerde());
		vuqa.addLed(new HLedVerde());
		vuqa.addLed(new HLedAmarelo());
		vuqa.addLed(new HLedAmarelo());
		vuqa.addLed(new HLedAmarelo());
		vuqa.addLed(new HLedAmarelo());
		vuqa.addLed(new HLedAmarelo());
		vuqa.addLed(new HLedAmarelo());
		vuqa.addLed(new HLedVermelho());
		vuqa.addLed(new HLedVermelho());
		vuqa.addLed(new HLedVermelho());
		vuqa.addLed(new HLedVermelho());
		vuqa.addLed(new HLedVermelho());
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
