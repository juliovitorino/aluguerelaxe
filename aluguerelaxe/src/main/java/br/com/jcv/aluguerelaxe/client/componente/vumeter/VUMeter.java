package br.com.jcv.aluguerelaxe.client.componente.vumeter;

import java.math.BigDecimal;

import br.com.jcv.aluguerelaxe.client.componente.led.AbstractLed;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VUMeter extends Composite {	
	private final static String PATH_IMAGEM = "images/24x24/";

	public static int DIRECAO_VERTICAL = 0;
	public static int DIRECAO_HORIZONTAL = 1;
	
	private static String VUMETER_FOOTER = "sun_and_cloud.png";
	private static String VUMETER_HEADER = "sun.png";
	
	private static String STYLE = "gwt-VUMeter";
	
	private int direcao;
	private ComplexPanel containerLed;
	private int contadorLed;
	
	public VUMeter(){
		this(DIRECAO_VERTICAL);
	}

	public VUMeter(int direcao){
		init(direcao != DIRECAO_HORIZONTAL ? DIRECAO_VERTICAL : direcao);
		initWidget(render());
		//this.setStyleName(STYLE);
	}
	
	private void init(int direcao) {
		this.direcao = direcao;
		this.contadorLed = 0;
	}
	
	private Widget render(){
		Widget w = null;
		if (this.direcao == DIRECAO_VERTICAL) {
			w = renderVertical();
		} else {
			w = renderHorizontal();
		}
		return w;
	}
	
	private Widget renderVertical(){
		DockPanel dp = new DockPanel();
		containerLed = new VerticalPanel();
		dp.add(containerLed, DockPanel.CENTER);
		dp.add(renderHeader(), DockPanel.NORTH);
		dp.add(renderFooter(), DockPanel.SOUTH);
		return dp;
	}

	private Widget renderHorizontal(){
		DockPanel dp = new DockPanel();
		containerLed = new HorizontalPanel();
		dp.add(containerLed, DockPanel.CENTER);
		dp.add(renderHeader(), DockPanel.EAST);
		dp.add(renderFooter(), DockPanel.WEST);
		return dp;
	}
	
	public void addLed(AbstractLed led){
		contadorLed++;
		this.containerLed.add(led);
	}
	
	protected Widget renderHeader() {
		return new Image(PATH_IMAGEM + VUMETER_HEADER);
	}
	
	protected Widget renderFooter() {
		return new Image(PATH_IMAGEM + VUMETER_FOOTER);
	}
	
	public void update(double pesoMaximo, double pesoAtual){
		if (pesoAtual <= pesoMaximo){
			double leds = arredondar((pesoAtual * contadorLed) / pesoMaximo,0,0); 
			
			int ledCount = containerLed.getWidgetCount();
			
			// Desliga todos os leds
			for(int index = 0; index < ledCount; index++){
				Widget led = containerLed.getWidget(index);
				((AbstractLed) led).ledOff();
			}
			
			// Liga a quantidade de leds necessaria
			if (direcao == DIRECAO_VERTICAL){
				for(int index = 0; index < leds; index++){
					Widget led = containerLed.getWidget(ledCount - index - 1);
					((AbstractLed) led).ledOn();
				}
			} else {
				for(int index = 0; index < leds; index++){
					Widget led = containerLed.getWidget(index);
					((AbstractLed) led).ledOn();
				}
				
			}

		}
	}
	
	//Parâmetros:   
	/**  
	 *  1 - Valor a arredondar.  
	 *  2 - Quantidade de casas depois da vírgula.  
	 *  3 - Arredondar para cima ou para baixo?  
	 *          Para cima = 0 (ceil)  
	 *          Para baixo = 1 ou qualquer outro inteiro (floor)  
	 **/  
	private double arredondar(double valor, int casas, int ceilOrFloor) {   
		double arredondado = valor;   
		arredondado *= (Math.pow(10, casas));   
		if (ceilOrFloor == 0) {   
			arredondado = Math.ceil(arredondado);              
		} else {   
			arredondado = Math.floor(arredondado);   
		}   
		arredondado /= (Math.pow(10, casas));   
		return arredondado;   
	} 
	
	private double arredondar(double valor, int casas) {   
        BigDecimal bd = new BigDecimal(valor);   
        bd = bd.setScale(casas,BigDecimal.ROUND_HALF_UP);   
        return bd.doubleValue(); 
	} 
	
	
}