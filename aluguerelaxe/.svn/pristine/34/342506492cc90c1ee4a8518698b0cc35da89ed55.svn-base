package br.com.jcv.aluguerelaxe.client.administracao.console.reserva.deposito;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormCompositeListener;
import br.com.jcv.aluguerelaxe.client.componente.date.DateBoxWidget;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ConfirmarDepositoFormComposite 
		extends FormComposite<ReservaVO> 
		implements TrackerCodigoFormCompositeListener {
	private DateBoxWidget dataRealDeposito;
	private TextBox valorRealDeposito;
	private Label dataPrevistaDeposito;
	private Label valorPrevistoDeposito;
	private Label valorCaucao;
	
	private ReservaVO voSalvo;
	
	@Override
	public ReservaVO getVO() {
		ReservaVO vo = new ReservaVO();
		vo.chaveTracker = voSalvo.chaveTracker;
		vo.dataRealDeposito = dataRealDeposito.getValue(); 
		try {
			vo.valorRealDeposito = Double.parseDouble(valorRealDeposito.getValue()); 
		} catch (NumberFormatException e) {
			vo.valorRealDeposito = 0;
		}
		return vo;
	}
	
	@Override
	public void update(ReservaVO vo) {
		dataPrevistaDeposito.setText(vo.dataPrevistaDeposito.toString());
		valorPrevistoDeposito.setText(String.valueOf(vo.valorPrevistoDeposito));
		valorCaucao.setText(String.valueOf(vo.valorCaucao));
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(5,2);
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, new Label("Data Prevista:"));
		grid.setWidget(linha, 1, dataPrevistaDeposito);
		
		grid.setWidget(++linha, 0, new Label("Valor Previsto:"));
		grid.setWidget(linha, 1, valorPrevistoDeposito);
		
		grid.setWidget(++linha, 0, new Label("Cau\u00e7\u00e3o:"));
		grid.setWidget(linha, 1, valorCaucao);
		
		grid.setWidget(++linha, 0, new Label("Data efetiva:"));
		grid.setWidget(linha, 1, dataRealDeposito);

		grid.setWidget(++linha, 0, new Label("Valor depositado:"));
		grid.setWidget(linha, 1, valorRealDeposito);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	@Override
	public void init() {
		dataRealDeposito = new DateBoxWidget();
		valorRealDeposito = new TextBox();
		dataPrevistaDeposito = new Label();
		valorPrevistoDeposito = new Label();
		valorCaucao = new Label();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifier(ReservaVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void onPesquisarTrackerClick(ReservaVO vo) {
		voSalvo = vo;
		update(vo);
	}
	
	
	
	
	
	
}
