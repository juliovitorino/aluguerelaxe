package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.AlugueRelaxeFrontException;
import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.classificador.Classificador;
import br.com.jcv.aluguerelaxe.client.componente.classificador.ClassificadorListener;
import br.com.jcv.aluguerelaxe.client.componente.classificador.ClassificadorVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.windowpanel.WindowPanel;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class AvaliacaoAnuncioImovelFormComposite extends FormComposite<AvaliacaoAnuncioImovelVO> implements ClassificadorListener {

	private static final String GWT_STYLE = "gwt-AvaliacaoAnuncioImovelFormComposite";
	private Classificador classFotografia;
	private Classificador classQualidadeTexto;
	private Classificador classInformacaoRelevante;
	
	private Label lblAvaliaFotografia;
	private Label lblAvaliaQT;
	private Label lblAvaliaIR;
	
	private long idImovel;
	private AreaNotificacao an;
	private VerticalPanel vpAvaliacao;
	
	public AvaliacaoAnuncioImovelFormComposite() {
		this.setStylePrimaryName(GWT_STYLE);
	}
	
	@Override
	public AvaliacaoAnuncioImovelVO getVO() {
		// Monta VO com os resultados da avalia\u00e7\u00e3o
		AvaliacaoAnuncioImovelVO vo = new AvaliacaoAnuncioImovelVO();
		vo.idImovelAvaliado = this.idImovel;
		vo.classFotografia = classFotografia.getNotaClassificador();
		vo.classQualidadeTexto = classQualidadeTexto.getNotaClassificador();
		vo.classInformacaoRelevante = classInformacaoRelevante.getNotaClassificador();
		return vo;
	}

	@Override
	public void update(AvaliacaoAnuncioImovelVO vo) {
		//Nao utilizado neste contexto
	}
	
	public void setVisible(boolean b) {
		this.vpAvaliacao.setVisible(b);
		this.an.setVisible(false);
	}
	
	public void update(long idImovel){
		this.idImovel = idImovel;
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(this.an);
		
		vp.add(this.vpAvaliacao);
		this.vpAvaliacao.add(new Label("Avalie as informa\u00e7\u00f5es do anunciante"));
		
		HorizontalPanel dp = new HorizontalPanel();
		
		VerticalPanel vpfoto = new VerticalPanel();
		vpfoto.add(classFotografia);
		vpfoto.add(lblAvaliaFotografia);
		WindowPanel wp1 = new WindowPanel("Avalia\u00e7\u00e3o de Fotos", "orkut");
		wp1.setComponenteCenter(vpfoto);
		dp.add(wp1);
		
		VerticalPanel vpQT = new VerticalPanel();
		vpQT.add(classQualidadeTexto);
		vpQT.add(lblAvaliaQT);
		WindowPanel wp2 = new WindowPanel("Avalia\u00e7\u00e3o do Texto", "orkut");
		wp2.setComponenteCenter(vpQT);
		dp.add(wp2);
		
		VerticalPanel vpIR = new VerticalPanel();
		vpIR.add(classInformacaoRelevante);
		vpIR.add(lblAvaliaIR);
		WindowPanel wp3 = new WindowPanel("Avalia\u00e7\u00e3o da Informa\u00e7\u00e3o", "orkut");
		wp3.setComponenteCenter(vpIR);
		dp.add(wp3);
		
		this.vpAvaliacao.add(dp);
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new Label("Quero enviar minha avalia\u00e7\u00e3o sobre este an\u00fancio agora."));
		Button btnSubmit = new Button("Enviar avalia\u00e7\u00e3o");
		btnSubmit.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				AvaliacaoAnuncioImovelFormComposite.this.an.setMensagem("Enviando sua avalia\u00e7\u00e3o ...", AreaNotificacao.NOTIFICACAO_INFO);
				AvaliacaoAnuncioImovelVO aaivo = AvaliacaoAnuncioImovelFormComposite.this.getVO();
				FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
				AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {

					public void onFailure(Throwable caught) {
						try {
							throw caught;
						} catch (IncompatibleRemoteServiceException e) {
							AvaliacaoAnuncioImovelFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (InvocationException e) {
							AvaliacaoAnuncioImovelFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						} catch (AlugueRelaxeFrontException e) {
							if ((e.getListaErros() != null) && (e.getListaErros().size() > 0)) {
								AvaliacaoAnuncioImovelFormComposite.this.an.setMensagem(e.getListaErros(), AreaNotificacao.NOTIFICACAO_ERRO);
							} else {
								AvaliacaoAnuncioImovelFormComposite.this.an.setMensagem(e.getMensagem(), AreaNotificacao.NOTIFICACAO_ERRO);
							}
						} catch (Throwable e) {
							AvaliacaoAnuncioImovelFormComposite.this.an.setMensagem(e.getMessage(), AreaNotificacao.NOTIFICACAO_ERRO);
						}					
					}

					public void onSuccess(VOPadrao result) {
						AvaliacaoAnuncioImovelFormComposite.this.an.setMensagem(result.msgcodeString, AreaNotificacao.NOTIFICACAO_INFO);
						AvaliacaoAnuncioImovelFormComposite.this.vpAvaliacao.setVisible(false);
					}
				};
				rpc.avaliarAnuncio(aaivo, callback);
				// ... invoca evento no backend para enviar e-mail ao dono deste imovel
			}
		});
		hp.add(btnSubmit);
		
		this.vpAvaliacao.add(hp);
		return vp;
	}

	@Override
	public void init() {
		// cria o avaliador de fotografias
		classFotografia = new Classificador("Fotografias");
		classFotografia.addClassificadorListener(this);
		classFotografia.setLabelClassificador_1("P\u00e9ssimas. Emba\u00e7adas. Fora de foco. Baix\u00edssima qualidade.");
		classFotografia.setLabelClassificador_2("Ruins. Baixa qualidade. N\u00e3o expressam o que diz no an\u00fancio.");
		classFotografia.setLabelClassificador_3("Regulares. Algumas est\u00e3o sem foco e/ou repetidas. Poderiam ser melhoradas.");
		classFotografia.setLabelClassificador_4("Boas.");
		classFotografia.setLabelClassificador_5("Excelentes. Consegue demonstrar o lugar s\u00f3 pelas imagens.");
		lblAvaliaFotografia = new Label();
		
		//cria o avaliador de qualidade do texto do anuncio
		classQualidadeTexto = new Classificador("Qualidade do texto do an\u00fancio");
		classQualidadeTexto.addClassificadorListener(this);
		classQualidadeTexto.setLabelClassificador_1("P\u00e9ssimo. N\u00e3o explica nada. Carece de conte\u00fado.");
		classQualidadeTexto.setLabelClassificador_2("Ruim. Muito embolado.");
		classQualidadeTexto.setLabelClassificador_3("Regular. Poderia ser melhorado.");
		classQualidadeTexto.setLabelClassificador_4("Bom.");
		classQualidadeTexto.setLabelClassificador_5("Excelente. Consegue demonstrar em riqueza de detalhes o im\u00f3vel.");
		lblAvaliaQT = new Label();

		//cria o avaliador de informa\u00e7\u00e3o relevante
		classInformacaoRelevante = new Classificador("Informa\u00e7\u00f5es relevantes do an\u00fancio");
		classInformacaoRelevante.addClassificadorListener(this);
		classInformacaoRelevante.setLabelClassificador_1("As informa\u00e7\u00f5es parecem n\u00e3o ajudar na escolha do im\u00f3vel.");
		classInformacaoRelevante.setLabelClassificador_2("A informa\u00e7\u00e3o encontra-se desatualizada.");
		classInformacaoRelevante.setLabelClassificador_3("Parcialmente orientadora.");
		classInformacaoRelevante.setLabelClassificador_4("Boa.");
		classInformacaoRelevante.setLabelClassificador_5("\u00datil. Vou usar para tomar a decis\u00e3o de escolha do im\u00f3vel.");
		lblAvaliaIR = new Label();
		
		an = new AreaNotificacao();
		vpAvaliacao = new VerticalPanel();
	}

	@Override
	public void clear() {
	}

	public void onClassificadorClick(ClassificadorVO vo) {
		if (vo.titulo.equals("Fotografias")){
			this.lblAvaliaFotografia.setText(vo.comentarioClassificador);
		}
		if (vo.titulo.equals("Qualidade do texto do an\u00fancio")){
			this.lblAvaliaQT.setText(vo.comentarioClassificador);
		}
		if (vo.titulo.equals("Informa\u00e7\u00f5es relevantes do an\u00fancio")){
			this.lblAvaliaIR.setText(vo.comentarioClassificador);
		}
		
	}

	public void onClearClassificadorClick() {
		// TODO Auto-generated method stub
		
	}

	public void onMouseOverEvent(String msgClassificador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(AvaliacaoAnuncioImovelVO vo) {
		// TODO Auto-generated method stub
		
	}

}
