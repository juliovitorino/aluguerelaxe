package br.com.jcv.aluguerelaxe.client.componente.datagrid;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.form.FormCompositeListener;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;



public class NavegadorFormComposite extends FormComposite<NavegadorVO> {

	private static final String PATH_IMAGEM = "images/16x16/";
	private static final String IMG_PRIMEIRO = "primeiro.png";
	private static final String IMG_ULTIMO = "ultimo.png";
	private static final String IMG_ANTERIOR = "anterior.png";
	private static final String IMG_PROXIMO = "proximo.png";
	private static final String STYLE = "gwt-NavegadorFormComposite";
	

	private Label paginaAtual;
	private Label totalPaginas;
	private Label registros;
	private Image btnPrimeiro;
	private Image btnUltimo;
	private Image btnAnterior;
	private Image btnProximo;
	
	public NavegadorFormComposite() {
		super();
		this.setStyleName(STYLE);
	}
	
	@Override
	public NavegadorVO getVO() {
		return null;
	}

	@Override
	public void update(NavegadorVO vo) {
		paginaAtual.setText(String.valueOf(vo.indicePagina));
		totalPaginas.setText(String.valueOf(vo.totalPaginas));
		registros.setText(String.valueOf(vo.totalRegistros).concat(" registro(s) encontrado(s)"));
	}

	@Override
	public Widget render() {
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(btnPrimeiro);
		hp.add(btnAnterior);
		hp.add(paginaAtual);
		hp.add(new Label(" de "));
		hp.add(totalPaginas);
		hp.add(btnProximo);
		hp.add(btnUltimo);
		hp.add(registros);
		return hp;
	}

	@Override
	public void init() {
		// Inicializa widgets
		paginaAtual = new Label();
		totalPaginas = new Label();
		registros = new Label();
		
		btnPrimeiro = new Image(PATH_IMAGEM + IMG_PRIMEIRO);
		btnUltimo = new Image(PATH_IMAGEM + IMG_ULTIMO);
		btnAnterior = new Image(PATH_IMAGEM + IMG_ANTERIOR);
		btnProximo = new Image(PATH_IMAGEM + IMG_PROXIMO);
		
		// Configura eventos para botoes de navegacao
		btnPrimeiro.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (NavegadorFormComposite.this.listener != null) {
					for (FormCompositeListener lstnr : NavegadorFormComposite.this.listener){
						((NavegadorFormCompositeListener)lstnr).onPrimeiroClick();
					}
				}
			}
		});
		
		btnUltimo.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (NavegadorFormComposite.this.listener != null) {
					for (FormCompositeListener lstnr : NavegadorFormComposite.this.listener){
						((NavegadorFormCompositeListener)lstnr).onUltimoClick();
					}
				}
			}
		});
		
		btnProximo.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (NavegadorFormComposite.this.listener != null) {
					for (FormCompositeListener lstnr : NavegadorFormComposite.this.listener){
						((NavegadorFormCompositeListener)lstnr).onProximoClick();
					}
				}
			}
		});
		
		btnAnterior.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if (NavegadorFormComposite.this.listener != null) {
					for (FormCompositeListener lstnr : NavegadorFormComposite.this.listener){
						((NavegadorFormCompositeListener)lstnr).onAnteriorClick();
					}
				}
			}
		});
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(NavegadorVO vo) {
		// TODO Auto-generated method stub
		
	}

}
