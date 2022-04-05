
package br.com.jcv.aluguerelaxe.client.componente.notificacao;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <H2>AreaNotificacao</H2>
 * <p>Cria um componente de área de identificação
 * </p>
 * <H2>CSS Style Rules</H2>
 * <ul>
 * <li>.gwt-AreaNotificacao-warning {CSS para notificações de WARNING}</li>
 * <li>.gwt-AreaNotificacao-info {CSS para notificações de INFO}</li>
 * <li>.gwt-AreaNotificacao-erro {CSS para notificações de ERRO}</li>
 * </ul>
 * @author Julio Vitorino
 */
public class AreaNotificacao extends Composite {
	private static final String PATH_IMAGES = "images/16x16/";
	
	private static final String STYLE_WARNING = "gwt-AreaNotificacao-warning";
	private static final String STYLE_INFO = "gwt-AreaNotificacao-info";
	private static final String STYLE_ERRO = "gwt-AreaNotificacao-erro";
	
	public static final int NOTIFICACAO_WARNING = 0;
	public static final int NOTIFICACAO_ERRO = 1;
	public static final int NOTIFICACAO_INFO = 2;

	private VerticalPanel vp = new VerticalPanel();
	private Label lblMensagem = new Label();
	private Image imgAdvertencia = new Image();

	public AreaNotificacao() {
		initWidget(montaFormularioAcaoNotificacao());
		this.setVisible(false);
	}
	
	private AreaNotificacao getAreaNotificacao() {
		return this;
	}
	
	private Widget montaFormularioAcaoNotificacao() {
		vp = new VerticalPanel();
		vp.setStylePrimaryName(STYLE_INFO);
		
		// Cria um icone com comportamento de fechamento
		HorizontalPanel hpFecharIcone = new HorizontalPanel();
		Label lblFechar = new Label("[X]");
		lblFechar.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				getAreaNotificacao().setVisible(false);
			}
		});
		
		hpFecharIcone.add(lblFechar);
		vp.add(hpFecharIcone);
		
		return vp;
	}
	
	public void setMensagem(String lblMensagem, int tipo) {
		List<String> lst = new ArrayList<String>();
		lst.add(lblMensagem);
		this.setMensagem(lst, tipo);
	}

	public void setMensagem(List<String> lstErros, int tipo) {
		for (int t = vp.getWidgetCount()-1; t > 0; t--){
			vp.remove(t);
		}
		
		for (String descricaoErro : lstErros) {
			HorizontalPanel hp = new HorizontalPanel();
			
			Image imgAdvertencia = new Image();
			switch (tipo) {
			case NOTIFICACAO_ERRO:
				vp.setStylePrimaryName(STYLE_ERRO);
				imgAdvertencia.setUrl(PATH_IMAGES + "error.png");
				break;
			case NOTIFICACAO_INFO:
				vp.setStylePrimaryName(STYLE_INFO);
				imgAdvertencia.setUrl(PATH_IMAGES + "info.png");
				break;
			case NOTIFICACAO_WARNING:
				vp.setStylePrimaryName(STYLE_WARNING);
				imgAdvertencia.setUrl(PATH_IMAGES + "warning.png");
				break;

			default:
				break;
			}
			hp.add(imgAdvertencia);
			hp.add(new Label(descricaoErro));
			vp.add(hp);
		}
		this.setVisible(true);
	}

}

