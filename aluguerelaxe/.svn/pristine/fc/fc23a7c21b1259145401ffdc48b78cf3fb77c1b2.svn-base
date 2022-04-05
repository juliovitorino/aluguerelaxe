package br.com.jcv.aluguerelaxe.client.administracao.console.imovelpropriedade;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.editpanel.AbstractGridCheckEditPanel;
import br.com.jcv.aluguerelaxe.client.componente.notificacao.AreaNotificacao;
import br.com.jcv.aluguerelaxe.client.componente.progressbar.ProgressBar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.UploadToolbar;
import br.com.jcv.aluguerelaxe.client.componente.toolbar.UploadToolbarListener;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.ui.FormPanel;

public class UploadEditPanel extends AbstractGridCheckEditPanel<UploadToolbar, UploadFormComposite, ImovelImagemVideoVO> 
		implements UploadToolbarListener {

	private long idImovel;
	private long idCliente;
	private ImovelFichaCompletaVO ifcvo;
	
	public UploadEditPanel(UploadToolbar toolbar) {
		super(toolbar);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ImovelImagemVideoVO getVO(UploadFormComposite composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImovelImagemVideoVO getVO(List<UploadFormComposite> composite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// Nada a fazer!

	}
	
	public void update(ImovelVO imovelvo, ClienteVO clientevo) {
		this.idCliente = clientevo.id;
		this.idImovel = imovelvo.id;
	}
	
	public void update(ImovelFichaCompletaVO vo) {
		this.ifcvo = vo;
	}
	
	public void update(List<ImovelImagemVideoVO> l) {
		removeTodosObjetoCompositeFromPanel();
		if ((l != null) && 
			(l.size() > 0) ) {
			for (ImovelImagemVideoVO vo : l) {
				UploadFormComposite tpfc = new UploadFormComposite();
				tpfc.update(vo);
				addObjetoCompositeToPanel(tpfc);
			}
		}
	}

	public void onAdicionarClick() {
		addObjetoCompositeToPanel(new UploadFormComposite(this.idCliente, this.idImovel));
	}

	public void onRemoverClick() {
	}

	public void onUploadClick() {
		
		boolean erro = false;
		int qtdPermTransf = 0;
		getAreaNotificacao().setMensagem("Seu plano " + ifcvo.imovelPlano.plano.nome + " permite uma galeria de " + ifcvo.imovelPlano.plano.limiteFotos + " foto(s). Iniciando a validação.",AreaNotificacao.NOTIFICACAO_WARNING);
		
		// Verifica se tem alguma imagem na galeria
		if (ifcvo.imagensImovelMI != null) {
			if (ifcvo.imagensImovelMI.size() >= ifcvo.imovelPlano.plano.limiteFotos) {
				getAreaNotificacao().setMensagem("Limite m\u00e1ximo de fotos foi atingido de acordo com o plano " + ifcvo.imovelPlano.plano.nome + 
						". Seu plano permite o upload m\u00e1ximo de " +  ifcvo.imovelPlano.plano.limiteFotos  + 
						" foto(s). Para colocar mais imagens realize uma migra\u00e7\u00e3o do seu plano para outro." +
						" Para migrar para planos que oferecem mais recursos utilize as fun\u00e7\u00f5es acima FINANCEIRO >> MIGRAR PLANO.",AreaNotificacao.NOTIFICACAO_ERRO);
				erro = true;
			}
			qtdPermTransf = ifcvo.imovelPlano.plano.limiteFotos - ifcvo.imagensImovelMI.size();
		} else {
			qtdPermTransf = ifcvo.imovelPlano.plano.limiteFotos;
		}

		if (!erro){
			getAreaNotificacao().setMensagem("Processo liberado para envio de " + qtdPermTransf + " foto(s) com sucesso." ,AreaNotificacao.NOTIFICACAO_INFO);
			// Obtendo a lista de Composite dentro do EditPanel
			List<UploadFormComposite> lst = getListObjetoComposite();
			if ((lst != null) && (lst.size() > 0)) {
				for (UploadFormComposite iifc : lst) {
					UploadVO vo = iifc.getUploadCampos();
					iifc.setProgressBar(new ProgressBar(vo.IdSessao));
					FormPanel fp = iifc.getFp();
					fp.submit();
					
					if( --qtdPermTransf == 0 ) {
						break;
					}
					
				}
			}
		}
	}

}
