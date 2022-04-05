package br.com.jcv.aluguerelaxe.client.administracao.console.reserva;

import br.com.jcv.aluguerelaxe.client.Constantes;
import br.com.jcv.aluguerelaxe.client.DateParser;
import br.com.jcv.aluguerelaxe.client.administracao.console.tracker.TrackerCodigoFormCompositeListener;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ReservaViewFormComposite extends FormComposite<ReservaVO> implements TrackerCodigoFormCompositeListener{
	private Label lblWho;
	private Label lblWhen;
	private Label lblWhere;
	private Label lblWhat;
	private Label lblHow;
	
	private static final String GWT_STYLE = "gwt-ReservaViewFormComposite";
	private static final String GWT_STYLE_VIEW = GWT_STYLE + "-view";
	
	public ReservaViewFormComposite() {
		super();
		this.setStyleName(GWT_STYLE);
	}
	
	@Override
	public ReservaVO getVO() {
		return null;
	}
	
	@Override
	public void update(ReservaVO vo) {
		// Atualiza Who
		lblWho.setText(obtemWho(vo));

		// Atualiza What
		lblWhat.setText(obtemWhat(vo));
		
		// Atualiza When
		lblWhen.setText(obtemWhen(vo));
		
		// Atualiza Where
		lblWhere.setText(obtemWhere(vo));
		
		// Atualiza How
		lblHow.setText(obtemHow(vo));
	}
	
	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("Quem \u00e9:"));
		vp.add(lblWho);
		vp.add(new Label("(*) A informa\u00e7\u00e3o acima ficar\u00e1 dispon\u00edvel SOMENTE ap\u00f3s a fase de confirma\u00e7\u00e3o do pagamento da reserva e o contrato de aluguel de temporada formalizados."));
		
		vp.add(new Label("O que o cliente deseja:"));
		vp.add(lblWhat);
		
		vp.add(new Label("Quando pretende fazer:"));
		vp.add(lblWhen);
		
		vp.add(new Label("Onde:"));
		vp.add(lblWhere);
		
		vp.add(new Label("Como estamos negociando:"));
		vp.add(lblHow);
		
		return vp;
	}
	@Override
	public void init() {
		lblWho = new Label();
		lblWhen = new Label();
		lblWhere = new Label();
		lblWhat = new Label();
		lblHow = new Label();
		
		// Configura folha de estilos
		lblWho.setStyleName(GWT_STYLE_VIEW);
		lblWhen.setStyleName(GWT_STYLE_VIEW);
		lblWhere.setStyleName(GWT_STYLE_VIEW);
		lblWhat.setStyleName(GWT_STYLE_VIEW);
		lblHow.setStyleName(GWT_STYLE_VIEW);
	}
	
	@Override
	public void clear() {
		lblWho.setText("");
		lblWhat.setText("");
		lblWhen.setText("");
		lblWhere.setText("");
		lblHow.setText("");
	}

	public void onPesquisarTrackerClick(ReservaVO vo) {
		clear();
		update(vo);
	}

	@Override
	public void notifier(ReservaVO vo) {
		// TODO Auto-generated method stub
		
	}
	
		
	//--------------------------------------
	// metodos de apoio
	//--------------------------------------
	
	private String obtemWho(ReservaVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb.append( vo.dataRealDeposito != null ? vo.locatario.nome : "*** (acesso n\u00e3o permitido) ***" );
//		sb.append( ", Telefone (" );
//		sb.append( vo.dataRealDeposito != null ? vo.locatario.telefone.ddd : "**" );
//		sb.append( ") " );
//		sb.append( vo.dataRealDeposito != null ? vo.locatario.telefone.telefone : "****-****" );
		return sb.toString();
	}
	
	private String obtemWhat(ReservaVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb.append( "Contrata\u00e7\u00e3o de reserva para temporada do im\u00f3vel #" );
		sb.append( vo.ifcdto.imovel.id );
		return sb.toString();
	}
	
	private String obtemWhen(ReservaVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb.append( "Chegada ao im\u00f3vel pretendida na data " );
		sb.append( DateParser.formatador(vo.dataCheckin) );
		sb.append( " at\u00e9 " );
		sb.append( DateParser.formatador(vo.dataCheckout) );
		return sb.toString();
	}

	private String obtemWhere(ReservaVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb.append( "Im\u00f3vel #" );
		sb.append( vo.ifcdto.imovel.id );
		sb.append( " cadastrado sob o t\u00edtulo de an\u00fancio >> " );
		sb.append( vo.ifcdto.imovel.descricaoTituloAnuncio);
		sb.append( " <<, domiciliado em " );
		sb.append( vo.ifcdto.imovel.endereco.toString() );
		return sb.toString();
	}
	
	private String obtemHow(ReservaVO vo) {
		StringBuilder sb = new StringBuilder();
		
		sb.append( vo.formaPagamento.equals(Constantes.FORMA_PGTO_PARCIAL) ? Constantes.FORMA_PGTO_PARCIAL_STR : Constantes.FORMA_PGTO_TOTAL_STR );
		sb.append( ". O valor autorizado pelo anunciante do im\u00f3vel para negocia\u00e7\u00e3o \u00e9 de R$ " );
		sb.append( String.valueOf(vo.valorAluguelNegociado) );
		sb.append( " acrescido de taxa de servi\u00e7o no valor de R$ " );
		sb.append( String.valueOf(vo.valorTaxaServico) );
		
		if (vo.valorCaucao > 0) {
			sb.append( ", para esta reserva existir\u00e1 um dep\u00f3sito a t\u00edtulo de cau\u00e7\u00e3o de R$ " );
			sb.append( String.valueOf(vo.valorCaucao) );
		}
		sb.append( ", estaremos aguardando o dep\u00f3sito do valor combinado de R$ " );
		sb.append( String.valueOf(vo.valorPrevistoDeposito) );
		sb.append( " at\u00e9 a data combinada com o preposto a locat\u00e1rio em " );
		sb.append( DateParser.formatador(vo.dataPrevistaDeposito) );
		return sb.toString();
	}

}	
