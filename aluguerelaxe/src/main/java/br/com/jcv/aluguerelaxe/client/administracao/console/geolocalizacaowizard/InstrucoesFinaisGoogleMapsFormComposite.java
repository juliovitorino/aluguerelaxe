package br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;



public class InstrucoesFinaisGoogleMapsFormComposite extends FormComposite<ImovelFichaCompletaVO> {
	
	private static final String INSTR_HTML = "<h3>Passo Final</h3>";
	private static final String INSTR_HTML1 = "<p>Voc\u00ea acaba de encerrar o processo de Geo-Localiza\u00e7\u00e3o para o im\u00f3vel:</p>";
	private static final String INSTR_HTML2 = "<p>Para concluir com sucesso sua opera\u00e7\u00e3o de cadastro no AlugueRelaxe da Geo-Localiza\u00e7\u00e3o do seu im\u00f3vel, voc\u00ea precisa clicar no bot\u00e3o <b>Concluir</b>.</p>";
	
	private HTML descricao;
	private ImovelFichaCompletaVO ifcvo;
	
	public InstrucoesFinaisGoogleMapsFormComposite() {
		super();
	}

	@Override
	public ImovelFichaCompletaVO getVO() {
		// Nao Aplicada neste contexto
		return this.ifcvo;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		this.descricao.setHTML("<b>" + vo.imovel.descricaoTituloAnuncio +"</b>");
		this.ifcvo = vo;
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new HTML(INSTR_HTML));
		vp.add(new HTML(INSTR_HTML1));
		vp.add(descricao);
		vp.add(new HTML(INSTR_HTML2));
		return vp;
	}

	@Override
	public void init() {
		this.descricao = new HTML();
	}

	@Override
	public void clear() {
		// Nao Aplicada neste contexto
	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
