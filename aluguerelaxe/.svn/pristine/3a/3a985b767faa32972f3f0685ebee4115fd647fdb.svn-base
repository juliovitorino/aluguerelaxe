package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.DateParser;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.tracker.Fase;
import br.com.jcv.aluguerelaxe.client.componente.tracker.Tracker;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelPlanoFaturaVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.ui.Widget;

/**
 * <h2>Componente para apresentacao das fases da fatura em forma de semaforos</<h2>
 * @author Julio
 *
 */
public class TrackerSituacaoFinanceiraAnuncioFormComposite extends FormComposite<ImovelPlanoFaturaVO> {
	
	private static final String GWT_STYLE = "gwt-TrackerSituacaoFinanceiraAnuncioFormComposite";
	private Tracker tracker;
	private Fase fsCadastroImv;
	private Fase fsCadastro;
	private Fase fsPgto;
	private Fase fsVencimento;
	
	public TrackerSituacaoFinanceiraAnuncioFormComposite(){
		super();
		this.setStyleName(GWT_STYLE);
	}

	@Override
	public ImovelPlanoFaturaVO getVO() {
		return null;
	}

	@Override
	public void update(ImovelPlanoFaturaVO vo) {
		// nao faz nada
	}
	
	public void update(ImovelFichaCompletaVO ifcvo, ImovelPlanoFaturaVO vo) {
		clear();
		
		// Atualiza sinalizador de cadastro do imovel
		if (ifcvo.imovel.dataCadastro != null) {
			fsCadastroImv.setStatus(Fase.STATUS_FINALIZADO);
			fsCadastroImv.setRodape(DateParser.getDataHoraCheia(ifcvo.imovel.dataCadastro));

		} else {
			fsCadastroImv.setStatus(Fase.STATUS_NAO_INICIADO);
		}	
		
		// Atualiza sinalizador de compra do anuncio
		if (vo.dataCadastro != null) {
			fsCadastro.setStatus(Fase.STATUS_FINALIZADO);
			fsCadastro.setRodape(DateParser.getDataHoraCheia(vo.dataCadastro));

		} else {
			fsCadastro.setStatus(Fase.STATUS_NAO_INICIADO);
		}	
		
		// Atualiza sinalizador de pagamento
		if((vo.indicadorStatus.equals("V")) && (vo.dataPagamento == null)) {
			fsPgto.setStatus(Fase.STATUS_ERRO);
			fsVencimento.setStatus(Fase.STATUS_NAO_INICIADO);
		} else if((vo.dataCadastro != null) && (vo.dataPagamento == null)){
			fsPgto.setStatus(Fase.STATUS_AGUARDANDO);
			fsVencimento.setStatus(Fase.STATUS_NAO_INICIADO);
		} else if ((vo.dataCadastro != null) && (vo.dataPagamento != null)){
			fsPgto.setStatus(Fase.STATUS_FINALIZADO);
			fsPgto.setRodape(DateParser.getDataHoraCheia(vo.dataPagamento));
			fsVencimento.setStatus(Fase.STATUS_EM_ANDAMENTO);
		} else {
			fsPgto.setStatus(Fase.STATUS_NAO_INICIADO);
		}	

		// Atualiza sinalizador de vencimento
		Date hoje = new Date();
		if((fsPgto.getStatus() == Fase.STATUS_AGUARDANDO) || (fsPgto.getStatus() == Fase.STATUS_ERRO)){
			fsVencimento.setStatus(Fase.STATUS_NAO_INICIADO);
		} else if((vo.dataPagamento != null) && ( hoje.getTime() <= vo.dataVencimento.getTime())){
			fsVencimento.setStatus(Fase.STATUS_EM_ANDAMENTO);
			fsVencimento.setRodape(DateParser.getDataHoraCheia(vo.dataVencimento));
		} else {
			fsVencimento.setStatus(Fase.STATUS_ERRO);
			fsVencimento.setRodape(DateParser.getDataHoraCheia(vo.dataVencimento));
		}	
	}

	@Override
	public Widget render() {
		return tracker;
	}

	@Override
	public void init() {
		this.tracker = new Tracker();
		fsCadastroImv = new Fase("Cadastro Im\u00f3vel");
		fsCadastro = new Fase("Compra An\u00fancio");
		fsPgto = new Fase("Pagamento Fatura");
		fsVencimento = new Fase("Vencimento An\u00fancio");
		tracker.add(fsCadastroImv);
		tracker.add(fsCadastro);
		tracker.add(fsPgto);
		tracker.add(fsVencimento);
	}

	@Override
	public void clear() {
		fsCadastroImv.clear();
		fsCadastro.clear();
		fsPgto.clear();
		fsVencimento.clear();
	}

	@Override
	public void notifier(ImovelPlanoFaturaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
