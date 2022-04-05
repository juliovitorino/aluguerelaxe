package br.com.jcv.aluguerelaxe.client.superpanel.superbanner;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;
import br.com.jcv.aluguerelaxe.client.publicidade.ImovelPublicidadeVO;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SuperBannerVO extends VOPadrao implements IsSerializable {
	public boolean indSuperBanner = false;
	public List<ImovelPublicidadeVO> lstipvo;
	public List<ImovelFichaCompletaVO> lstifcvo;
	public String html;
}
