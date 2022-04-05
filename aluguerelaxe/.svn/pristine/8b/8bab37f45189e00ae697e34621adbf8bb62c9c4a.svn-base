package br.com.jcv.aluguerelaxe.client.imovel.listagem.uf;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ImovelVO;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class FiltroImovelIDComposite extends FormComposite<ImovelFichaCompletaVO> {
	
	private TextBox idImovel;
	
	public FiltroImovelIDComposite() {
		super();
		this.setStylePrimaryName("gwt-FormComposite-FiltroImovelIDComposite");
	}

	public TextBox getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(TextBox idImovel) {
		this.idImovel = idImovel;
	}

	@Override
	public ImovelFichaCompletaVO getVO() {
		ImovelFichaCompletaVO vo = new ImovelFichaCompletaVO();
		vo.imovel = new ImovelVO();
		vo.imovel.id = Long.valueOf(this.idImovel.getValue());
		return vo;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		// Nao Aplicavel neste contexto
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("Propriedade #"));
		vp.add(this.idImovel);
		return vp;
	}

	@Override
	public void init() {
		idImovel = new TextBox();
	}

	@Override
	public void clear() {
		this.getIdImovel().setText("");
	}

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}

}
