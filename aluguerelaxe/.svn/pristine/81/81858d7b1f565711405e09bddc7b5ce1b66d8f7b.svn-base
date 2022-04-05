package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.foto.FotoFD;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GaleriaFotosFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {

	private Grid grid;
	private HTML avisoSemImagem;
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		grid.setVisible(false);
		avisoSemImagem.setVisible(false);
		if (vo.imagensImovelXG != null){
			grid.setVisible(true);
			int j = 0;
			int linha = 1;
			for (ImovelImagemVideoVO iivvo: vo.imagensImovelXG){
				FotoFD ffd = new FotoFD(iivvo);
				grid.setWidget(linha, j++, ffd);
				if (j > 1) {
					j = 0;
					grid.resizeRows(++linha + 1);
				}
			}
		} else {
			avisoSemImagem.setVisible(true);
			avisoSemImagem.setHTML("<h3>Estamos sem imagens para este im\u00f3vel. Enviaremos uma notifica\u00e7\u00e3o para o propriet\u00e1rio para que possa corrigir a galeria de fotos.</h3>");
			
			//-------------------------------------------
			// enviamos uma notificacao ao anunciante
			// para regularizar as imagens
			//-------------------------------------------
			FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
			AsyncCallback<VOPadrao> callback = new AsyncCallback<VOPadrao>() {

				public void onFailure(Throwable caught) {
					// Não aplicado neste contexto
					
				}

				public void onSuccess(VOPadrao result) {
					// Não aplicado neste contexto
					
				}
			};
			
			rpc.notificacaoGaleriaFotoVazia(vo, callback);
		}
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(avisoSemImagem);
		vp.add(grid);
		return vp;
	}

	@Override
	public void init() {
		grid = new Grid(2,2);
		avisoSemImagem = new HTML();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
