package br.com.jcv.aluguerelaxe.client.componente.wizard;

import java.util.ArrayList;
import java.util.List;

import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *<h2>AbstractWizard</h2>
 *<p>Classe responsável pelo comportamento padrão das composições de wizard. Ela usa uma implementação de @link{FormComposite}
 * para encapsular e navegar entre as composições. Faz uso de uma interface @link{WizardListener} para enviar os métodos
 * <code>onVoltar()</code>, <code>onProximo()</code> e <code>onConcluir()</code>
 * do filtro informado através de um <code>getVO()</code>.
 *</p>
 * <h2>CSS Style Rules</h2>
 * <ul>
 * <li>.gwt-AbstractWizard {estilo primario}</li>
 * </ul>
 * @author Julio Vitorino
 */
public abstract class AbstractWizard<C extends Composite, V> extends Composite {

	private static final String PATH_IMAGEM = "images/48x48/";
	private static final String PATH_BOTOES = "images/botoes/";
	private static final String IMG_CONCLUIR = "concluir.png";
	private static final String IMG_PROXIMO = "proximo.png";
	private static final String IMG_ANTERIOR = "anterior.png";
	
	public static final int BOTAO_VOLTAR = 0;
	public static final int BOTAO_CONCLUIR = 1;
	public static final int BOTAO_AVANCAR = 2;
	

	private List<C> lstComposite;
	private List<Widget> lstCompositeHeader;
	private List<WizardListener> listeners;
	private int idx = 0;
	private int idxh = 0;
	private VerticalPanel vpWizard;
	private VerticalPanel vpHeader;
	private Image btnVoltar;
	private Image btnProximo;
	private Image btnConcluir;
	private AreaNotificacao an;
	
	public AbstractWizard() {
		initComposites();
		initWidget(render());
		this.setStylePrimaryName("gwt-AbstractWizard");
	}
	
	public abstract void initComposites();
	
	public void showCompositeFinal(C compositeFinal, Widget compositeFinalHeader) {
		if ( (compositeFinal != null) && (compositeFinalHeader != null) ){
			vpWizard.clear();
			vpHeader.clear();
			vpWizard.add(compositeFinal);
			vpHeader.add(compositeFinalHeader);
			forcarVisibilidadeBotao(BOTAO_VOLTAR, false);
			forcarVisibilidadeBotao(BOTAO_AVANCAR, false);
			forcarVisibilidadeBotao(BOTAO_CONCLUIR, false);
		}
	}
	
	public void forcarVisibilidadeBotao(int btn, boolean estado) {
		switch (btn) {
		case BOTAO_VOLTAR:
			btnVoltar.setVisible(estado);
			break;
		case BOTAO_AVANCAR:
			btnProximo.setVisible(estado);
			break;
		case BOTAO_CONCLUIR:
			btnConcluir.setVisible(estado);
			break;

		default:
			break;
		}
	}
	
	public List<C> getLstComposite() {
		return this.lstComposite;
	}
	
	public abstract V getVO();
	
	protected void addWizard(C composite, Widget compositeHeader) {
		if(this.lstComposite == null) {
			this.lstComposite = new ArrayList<C>();
		}
		this.lstComposite.add(composite);
		this.vpWizard.add(composite);
		
		if(this.lstCompositeHeader == null) {
			this.lstCompositeHeader = new ArrayList<Widget>();
		}
		this.lstCompositeHeader.add(compositeHeader);
		this.vpHeader.add(compositeHeader);

	}
	
	public Widget montaHeaderPasso(String img, String strheader) {
		DockPanel hp = new DockPanel();
		Image imgheader = new Image(PATH_IMAGEM + img );
		String strh2 = "<h2>"+ strheader +"</h2>";
		HTML lblheader = new HTML(strh2);
		hp.add(imgheader, DockPanel.WEST);
		hp.add(lblheader, DockPanel.CENTER);
		return hp;
	}
	
	protected void init() {
		int i = 0;
		if (this.lstComposite != null) {
			for (C composite: lstComposite) {
				if ( i++ > 0) {
					composite.setVisible(false);
				} else {
					composite.setVisible(true);
				}
			}
		}
		
		i = 0;
		if (this.lstCompositeHeader != null) {
			for (Widget composite: lstCompositeHeader) {
				if ( i++ > 0) {
					composite.setVisible(false);
				} else {
					composite.setVisible(true);
				}
			}
		}
		this.controlarBotoes();

	}
	

	public void addWizardListener(WizardListener listener) {
		if (this.listeners == null) {
			this.listeners = new ArrayList<WizardListener>();
		}
		this.listeners.add(listener);
	}
	
	private Widget render() {
		DockPanel dp = new DockPanel();
		dp.add(renderHeader(), DockPanel.NORTH);
		dp.add(renderRodape(), DockPanel.SOUTH);
		dp.add(renderComposite(), DockPanel.CENTER);
		return dp;
	}
	
	private Widget renderComposite() {
		vpWizard = new VerticalPanel();
		return vpWizard;
	}
	
	private Widget renderHeader() {
		vpHeader = new VerticalPanel();
		an = new AreaNotificacao();
		vpHeader.add(an);
		return vpHeader;
	}
	
	private void controlarBotoes() {
		if (lstComposite != null){
			if(lstComposite.size() == 1){
				btnVoltar.setVisible(false);
				btnProximo.setVisible(false);
			} else {
				btnVoltar.setVisible(true);
				btnProximo.setVisible(true);
				if (idx + 1 == lstComposite.size()){
					btnVoltar.setVisible(true);
					btnProximo.setVisible(false);
					btnConcluir.setVisible(true);
				} else if (idx > 0){
					btnVoltar.setVisible(true);
					btnProximo.setVisible(true);
					btnConcluir.setVisible(false);
				} else {
					btnVoltar.setVisible(false);
					btnProximo.setVisible(true);
					btnConcluir.setVisible(false);
				}
			}
		}
	}
	
	private Widget renderRodape() {
		HorizontalPanel hp = new HorizontalPanel();
		btnVoltar = new Image(PATH_BOTOES + IMG_ANTERIOR);
		btnProximo = new Image(PATH_BOTOES + IMG_PROXIMO);
		btnConcluir = new Image(PATH_BOTOES + IMG_CONCLUIR);
		
		btnVoltar.addClickHandler( new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					// Movimenta-se para o composite anterior
					if (idx > 0){
						lstCompositeHeader.get(idxh).setVisible(false);
						lstCompositeHeader.get(--idxh).setVisible(true);

						lstComposite.get(idx).setVisible(false);
						lstComposite.get(--idx).setVisible(true);
						controlarBotoes();
					}
					
					// Executa metodos da interface
					if (listeners != null) {
						for(WizardListener listener: listeners) {
							listener.onVoltarClick();
						}
					}
				}
			}
		);

		btnProximo.addClickHandler( new ClickHandler() {

				public void onClick(ClickEvent event) {

					// Movimenta-se para o próximo composite
					if (idx < lstComposite.size()-1){
						lstCompositeHeader.get(idxh).setVisible(false);
						lstCompositeHeader.get(++idxh).setVisible(true);

						lstComposite.get(idx).setVisible(false);
						lstComposite.get(++idx).setVisible(true);
						controlarBotoes();
					}
					
					// Executa metodos da interface
					if (listeners != null) {
						for(WizardListener listener: listeners) {
							listener.onProximoClick();
						}
					}
				}
			}
		);
		
		btnConcluir.addClickHandler( new ClickHandler() {

				public void onClick(ClickEvent event) {
					controlarBotoes();

					// Executa metodos da interface
					if (listeners != null) {
						for(WizardListener listener: listeners) {
							listener.onConcluirClick();
						}
					}
				}
			}
		);
		
		hp.add(btnVoltar);
		hp.add(btnProximo);
		hp.add(btnConcluir);
		
		return hp;
	}
	
	public void setMensagem(String lblMensagem, int tipo) {
		an.setMensagem(lblMensagem, tipo);
	}

	public void setMensagem(List<String> lstErros, int tipo) {
		an.setMensagem(lstErros, tipo);
	}
	
}