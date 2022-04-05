package br.com.jcv.aluguerelaxe.client.administracao.console.desktop;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.charter.CharterVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.listbox.UltimosCincoAnosListBox;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.FichaImovelRPCAsync;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CharterDistribuicaoVisitasFormComposite extends
		FormComposite<CharterVO> {
	
	private UltimosCincoAnosListBox ucalb;
	private Image chart;
	private ImovelFichaCompletaVO ifcvo = null;
	
	@Override
	public CharterVO getVO() {
		// Nao aplicavel neste contexto
		return null;
	}

	public void update(ImovelFichaCompletaVO result){
		this.ifcvo = result;
		this.updateGraficoDistribuicao();
		
	}

	@Override
	public void update(CharterVO vo) {
		// Nao Aplicavel neste contexto
	}
	
	private void updateGraficoDistribuicao() {
		FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
		AsyncCallback<CharterVO> callback = new AsyncCallback<CharterVO>() {

			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			public void onSuccess(CharterVO result) {
				if ((result != null) && (result.url != null)){
					chart.setUrl(result.url);
				}
			}
		};
		rpc.charterDistribuicaoVisitasImovel(CharterDistribuicaoVisitasFormComposite.this.ifcvo, 
			 Integer.valueOf(CharterDistribuicaoVisitasFormComposite.this.ucalb.getValue(CharterDistribuicaoVisitasFormComposite.this.ucalb.getSelectedIndex())), 
				callback);
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		
		HorizontalPanel hp = new HorizontalPanel();
		
		hp.add(ucalb);
		Button btn = new Button("Atualizar");
		btn.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				CharterDistribuicaoVisitasFormComposite.this.updateGraficoDistribuicao();
			}
		});
		hp.add(btn);
		
		vp.add(hp);
		vp.add(chart);
		return vp;
	}

	@Override
	public void init() {
		chart = new Image();
		ucalb = new UltimosCincoAnosListBox();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(CharterVO vo) {
		// TODO Auto-generated method stub
		
	}

}
