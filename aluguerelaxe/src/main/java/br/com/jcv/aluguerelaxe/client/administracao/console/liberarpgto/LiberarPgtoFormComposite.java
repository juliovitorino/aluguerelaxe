package br.com.jcv.aluguerelaxe.client.administracao.console.liberarpgto;

import br.com.jcv.aluguerelaxe.client.administracao.console.reserva.ReservaVO;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormCompositeListener;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LiberarPgtoFormComposite 
		extends FormComposite<ReservaVO> 
		implements TrackerCodigoFormCompositeListener {
	private Label banco;
	private Label agencia;
	private Label contacorrente;
	private Label favorecido;
	
	@Override
	public ReservaVO getVO() {
		
		return null;
	}
	
	@Override
	public void update(ReservaVO vo) {
		banco.setText(vo.ifcdto.cliente.db.banco);
		agencia.setText(vo.ifcdto.cliente.db.agencia);
		contacorrente.setText(vo.ifcdto.cliente.db.contacorrente);
		favorecido.setText(vo.ifcdto.cliente.nome);
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		Grid grid = new Grid(4,2);
		
		// Posiciona campos no grid
		int linha = -1;

		grid.setWidget(++linha, 0, new Label("Banco:"));
		grid.setWidget(linha, 1, banco);
		
		grid.setWidget(++linha, 0, new Label("Ag\u00eancia:"));
		grid.setWidget(linha, 1, agencia);
		
		grid.setWidget(++linha, 0, new Label("No. Conta Corrente:"));
		grid.setWidget(linha, 1, contacorrente);
		
		grid.setWidget(++linha, 0, new Label("Favorecido:"));
		grid.setWidget(linha, 1, favorecido);

		// Adiciona a grid ao VerticalPanel
		vp.add(grid);
		
		return vp;
	}
	@Override
	public void init() {
		banco = new Label();
		agencia = new Label();
		contacorrente = new Label();
		favorecido = new Label();
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
		update(vo);
	}
	
	
	
	
	
	
}
