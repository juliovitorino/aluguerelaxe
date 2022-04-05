package br.com.jcv.aluguerelaxe.client.componente.classificador;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


/**
 * <h1>Classificador</h1>
 * <p>Composite para apresentação um classificador (Rate) para ser utilizado em pesquisas p/ exemplo.
 * A pontuação indicada por <code>notaClassificador</code> varia sempre de 1 a 5,  se <code>notaClassificador</code> tiver o valor 0
 * indica que nenhuma classificador foi escolhido.</p>
 * <p>Utiliza a interface {@link ClassificadorListener} para enviar eventos para as classes que a implementam.</p>
 * @author Julio Vitorino
 */

public class Classificador extends Composite {

	private final static String GWT_STYLE = "gwt-classificador";
	private final static String PATH = "images/16x16/";
	public  final static int SEM_CLASSIFICACAO = 0;
	private final static String ESTRELA_DESABILITADA = "star_grey.png";
	private final static String ESTRELA_HABILITADA = "star_red.png";
	private final static String LIXEIRA = "garbage_empty.png";

	private int notaClassificador = 0;
	private String titulo;
	
	private Image imgClass1;
	private Image imgClass2;
	private Image imgClass3;
	private Image imgClass4;
	private Image imgClass5;
	
	private String strClass1;
	private String strClass2;
	private String strClass3;
	private String strClass4;
	private String strClass5;
	
	private List<ClassificadorListener> lstListeners = new ArrayList<ClassificadorListener>();
	
	private Image imgLixeira;
	
	private Label tituloClassificador;

	public Classificador(String titulo) {
		//this.setStylePrimaryName(GWT_STYLE);
		this.titulo =  titulo;
		init();
		initWidget(render());
	}
	
	public void setNotaClassificador(int nota){
		this.notaClassificador = nota;
		switch (this.notaClassificador) {
		case 1:
			Classificador.this.resetClassificadores();
			Classificador.this.ClassificadorPressionado_1();
			break;
		case 2:
			Classificador.this.resetClassificadores();
			Classificador.this.ClassificadorPressionado_2();
			break;
		case 3:
			Classificador.this.resetClassificadores();
			Classificador.this.ClassificadorPressionado_3();
			break;
		case 4:
			Classificador.this.resetClassificadores();
			Classificador.this.ClassificadorPressionado_4();
			break;
		case 5:
			Classificador.this.resetClassificadores();
			Classificador.this.ClassificadorPressionado_5();
			break;

		default:
			Classificador.this.resetClassificadores();
			break;
		}
	}
	
	private Widget render() {
		VerticalPanel vp = new VerticalPanel();
		
		HorizontalPanel hp1 = new HorizontalPanel();
		hp1.add(new Image(PATH + "help2.png"));
		hp1.add(this.tituloClassificador);
		
		HorizontalPanel hp2 = new HorizontalPanel();
		hp2.add(this.imgClass1);
		hp2.add(this.imgClass2);
		hp2.add(this.imgClass3);
		hp2.add(this.imgClass4);
		hp2.add(this.imgClass5);
		
		vp.add(hp1);
		vp.add(hp2);
		
		return vp;
	}
	
	private void init() {
		// Inicializa objetos e membros da classe
		this.notaClassificador = SEM_CLASSIFICACAO;

		// Inicializa titulo do classificador
		this.tituloClassificador = new Label(this.titulo);
		
		// Inicializa os classificadores com seus desenhos desabilitados
		this.imgClass1 = new Image( PATH + ESTRELA_DESABILITADA);
		this.imgClass2 = new Image( PATH + ESTRELA_DESABILITADA);
		this.imgClass3 = new Image( PATH + ESTRELA_DESABILITADA);
		this.imgClass4 = new Image( PATH + ESTRELA_DESABILITADA);
		this.imgClass5 = new Image( PATH + ESTRELA_DESABILITADA);
		
		// Inicializa o manipulador de evento onClick e MouseOverpara cada classificador
		this.imgClass1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Classificador.this.notaClassificador = 1;
				Classificador.this.resetClassificadores();
				Classificador.this.ClassificadorPressionado_1();
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onClassificadorClick(new ClassificadorVO(Classificador.this.notaClassificador, 
																Classificador.this.tituloClassificador.getText(),
																Classificador.this.strClass1));
				}
			}
		});
		
		this.imgClass1.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onMouseOverEvent(Classificador.this.strClass1);
				}
			}
		});

		this.imgClass2.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Classificador.this.notaClassificador = 2;
				Classificador.this.resetClassificadores();
				Classificador.this.ClassificadorPressionado_2();
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onClassificadorClick(new ClassificadorVO(Classificador.this.notaClassificador, 
																Classificador.this.tituloClassificador.getText(),
																Classificador.this.strClass2));
				}
			}
		});

		this.imgClass2.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onMouseOverEvent(Classificador.this.strClass2);
				}
			}
		});

		this.imgClass3.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Classificador.this.notaClassificador = 3;
				Classificador.this.resetClassificadores();
				Classificador.this.ClassificadorPressionado_3();
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onClassificadorClick(new ClassificadorVO(Classificador.this.notaClassificador, 
																Classificador.this.tituloClassificador.getText(),
																Classificador.this.strClass3));
				}
			}
		});

		this.imgClass3.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onMouseOverEvent(Classificador.this.strClass3);
				}
			}
		});

		this.imgClass4.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Classificador.this.notaClassificador = 4;
				Classificador.this.resetClassificadores();
				Classificador.this.ClassificadorPressionado_4();
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onClassificadorClick(new ClassificadorVO(Classificador.this.notaClassificador, 
																Classificador.this.tituloClassificador.getText(),
																Classificador.this.strClass4));
				}
			}
		});

		this.imgClass4.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onMouseOverEvent(Classificador.this.strClass4);
				}
			}
		});

		this.imgClass5.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Classificador.this.notaClassificador = 5;
				Classificador.this.resetClassificadores();
				Classificador.this.ClassificadorPressionado_5();
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onClassificadorClick(new ClassificadorVO(Classificador.this.notaClassificador, 
																Classificador.this.tituloClassificador.getText(),
																Classificador.this.strClass5));
				}
			}
		});

		this.imgClass5.addMouseOverHandler(new MouseOverHandler() {
			public void onMouseOver(MouseOverEvent event) {
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onMouseOverEvent(Classificador.this.strClass5);
				}
			}
		});

		// Inicializa imagem da lixeira e seu evento onClick
		this.imgLixeira = new Image( PATH + LIXEIRA);
		this.imgLixeira.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Classificador.this.notaClassificador = SEM_CLASSIFICACAO;
				Classificador.this.resetClassificadores();
				
				for (ClassificadorListener cl : Classificador.this.lstListeners){
					cl.onClearClassificadorClick();
				}
			}
		});
		
		
	}

	private void resetClassificadores() {
		// Inicializa os classificadores com seus desenhos desabilitados
		this.imgClass1.setUrl(PATH + ESTRELA_DESABILITADA);
		this.imgClass2.setUrl(PATH + ESTRELA_DESABILITADA);
		this.imgClass3.setUrl(PATH + ESTRELA_DESABILITADA);
		this.imgClass4.setUrl(PATH + ESTRELA_DESABILITADA);
		this.imgClass5.setUrl(PATH + ESTRELA_DESABILITADA);
	}

	private void ClassificadorPressionado_1() {
		this.imgClass1.setUrl(PATH + ESTRELA_HABILITADA);
	}
	
	private void ClassificadorPressionado_2() {
		this.ClassificadorPressionado_1();
		this.imgClass2.setUrl(PATH + ESTRELA_HABILITADA);
	}
	
	private void ClassificadorPressionado_3() {
		this.ClassificadorPressionado_1();
		this.ClassificadorPressionado_2();
		this.imgClass3.setUrl(PATH + ESTRELA_HABILITADA);
	}
	
	private void ClassificadorPressionado_4() {
		this.ClassificadorPressionado_1();
		this.ClassificadorPressionado_2();
		this.ClassificadorPressionado_3();
		this.imgClass4.setUrl(PATH + ESTRELA_HABILITADA);
	}
	
	private void ClassificadorPressionado_5() {
		this.ClassificadorPressionado_1();
		this.ClassificadorPressionado_2();
		this.ClassificadorPressionado_3();
		this.ClassificadorPressionado_4();
		this.imgClass5.setUrl(PATH + ESTRELA_HABILITADA);
	}
	
	public void addClassificadorListener(ClassificadorListener cl) {
		this.lstListeners.add(cl);
	}
	
	public ClassificadorVO getNotaClassificador() {
		ClassificadorVO vo = null;
		if (this.notaClassificador != SEM_CLASSIFICACAO) {
			vo = new ClassificadorVO(SEM_CLASSIFICACAO,null,null);
			vo.notaClassificador = this.notaClassificador;
			vo.titulo = this.titulo;
			switch(this.notaClassificador) {
			case 1:
					vo.comentarioClassificador = strClass1;
					break;
			case 2:
					vo.comentarioClassificador = strClass2;
					break;
			case 3:
					vo.comentarioClassificador = strClass3;
					break;
			case 4:
					vo.comentarioClassificador = strClass4;
					break;
			case 5:
					vo.comentarioClassificador = strClass5;
					break;
			}
		}
		
		return vo;
	}
	
	public void setLabelClassificador_1(String texto) {
		this.strClass1 = texto;
	}

	public void setLabelClassificador_2(String texto) {
		this.strClass2 = texto;
	}
	public void setLabelClassificador_3(String texto) {
		this.strClass3 = texto;
	}
	public void setLabelClassificador_4(String texto) {
		this.strClass4 = texto;
	}
	public void setLabelClassificador_5(String texto) {
		this.strClass5 = texto;
	}
}
