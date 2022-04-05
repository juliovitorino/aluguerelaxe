package br.com.jcv.aluguerelaxe.client.componente.tracker;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * <h2>Fase</h2>
 * <p>Implementa um componente do tipo semaforo</p>
 * <p>CSS: gwt-fase</p>
 * @author Julio
 *
 */
public class Fase extends Composite {
	
	private static final String GWT_STYLE = "gwt-Fase";
	private static final String GWT_STYLE_NOME = GWT_STYLE + "-nome";
	private static final String GWT_STYLE_STATUS = GWT_STYLE + "-status";
	private static final String GWT_STYLE_RODAPE = GWT_STYLE + "-rodape";
	
	private static final String PATH_IMAGEM = "images/48x48/";
	
	private static final String IMAGEM_NAO_INICIADO = "bullet_ball_grey.png";
	private static final String IMAGEM_EM_ANDAMENATO = "bullet_ball_yellow.png";
	private static final String IMAGEM_AGUARDANDO = "bullet_ball_yellow.png";
	private static final String IMAGEM_FINALIZADO = "bullet_ball_green.png";
	private static final String IMAGEM_ERRO = "bullet_ball_red.png";

	//status validos
	public final static int STATUS_ERRO = -1;
	public final static int STATUS_NAO_INICIADO = 0;
	public final static int STATUS_EM_ANDAMENTO = 1;
	public final static int STATUS_FINALIZADO = 2;
	public final static int STATUS_AGUARDANDO = 3;

	public final static String STATUS_ERRO_STR = "cancelado";
	public final static String STATUS_NAO_INICIADO_STR = "n\u00c3o iniciado";
	public final static String STATUS_EM_ANDAMENTO_STR = "em andamento";
	public final static String STATUS_FINALIZADO_STR  = "finalizado";
	public final static String STATUS_AGUARDANDO_STR = "aguardando";
	
	// variaveis de apoio
	private int status;
	private String faseNome;
	
	private List<FaseListener> listener;
	
	// variaveis de site
	private Label nome;
	private Image imgFase;
	private Label lblStatus;
	private Label lblRodape;
	
	public Fase(String faseNome, int statusInicial) {
		this.faseNome = faseNome;
		this.status = ((statusInicial > STATUS_AGUARDANDO) || (statusInicial < STATUS_ERRO) ?  STATUS_NAO_INICIADO : statusInicial);
		init();
		initWidget(render());
		this.setStyleName(GWT_STYLE);
	}
	
	public Fase(String nome){
		this(nome, STATUS_NAO_INICIADO);
	}
	
	public void setRodape(String rodape){
		this.lblRodape.setText(rodape);
	}
	
	public void clear() {
		this.imgFase.setUrl(PATH_IMAGEM + IMAGEM_NAO_INICIADO );
		this.status = STATUS_NAO_INICIADO;
		this.lblStatus.setText(STATUS_NAO_INICIADO_STR);
		this.lblRodape.setText(" ");
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		switch(status){
		case STATUS_EM_ANDAMENTO:
			this.imgFase.setUrl(PATH_IMAGEM + IMAGEM_EM_ANDAMENATO );
			this.status = status;
			this.lblStatus.setText(STATUS_EM_ANDAMENTO_STR);
			break;
		case STATUS_NAO_INICIADO:
			this.imgFase.setUrl(PATH_IMAGEM + IMAGEM_NAO_INICIADO );
			this.status = status;
			this.lblStatus.setText(STATUS_NAO_INICIADO_STR);
			break;
		case STATUS_ERRO:
			this.imgFase.setUrl(PATH_IMAGEM + IMAGEM_ERRO );
			this.status = status;
			this.lblStatus.setText(STATUS_ERRO_STR);
			break;
		case STATUS_FINALIZADO:
			this.imgFase.setUrl(PATH_IMAGEM + IMAGEM_FINALIZADO );
			this.status = status;
			this.lblStatus.setText(STATUS_FINALIZADO_STR);
			break;
		case STATUS_AGUARDANDO:
			this.imgFase.setUrl(PATH_IMAGEM + IMAGEM_AGUARDANDO );
			this.status = status;
			this.lblStatus.setText(STATUS_AGUARDANDO_STR);
			break;
		default: 
			this.imgFase.setUrl(PATH_IMAGEM + IMAGEM_ERRO );
			this.status = STATUS_ERRO;
			this.lblStatus.setText(STATUS_ERRO_STR);
			break;
		}
	}
	
	public void addListener(FaseListener listener){
		if (listener != null){
			if (this.listener == null){
				this.listener = new ArrayList<FaseListener>();
			}
			
			this.listener.add(listener);
		}
	}
	
	public void init(){
		this.status = STATUS_NAO_INICIADO;
		
		// Inicializa componente visuais
		nome = new Label(faseNome);
		imgFase = new Image(PATH_IMAGEM + IMAGEM_NAO_INICIADO );
		lblStatus = new Label();
		lblRodape = new Label();
		
		// Configura CSS
		nome.setStyleName(GWT_STYLE_NOME);
		lblStatus.setStyleName(GWT_STYLE_STATUS);
		lblRodape.setStyleName(GWT_STYLE_RODAPE);
		
	}
	
	protected Widget render(){
		VerticalPanel vp = new VerticalPanel();
		vp.add(this.imgFase);
		vp.add(this.nome);
		vp.add(this.lblStatus);
		vp.add(this.lblRodape);
		return vp;
	}

}
