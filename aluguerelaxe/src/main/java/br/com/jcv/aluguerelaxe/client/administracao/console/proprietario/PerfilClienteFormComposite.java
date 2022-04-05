package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;

import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.imagem.ImovelImagemVideoVO;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class PerfilClienteFormComposite extends FormComposite<ClienteVO> {

	private static final String PATH_IMAGEM_NOFACE = "images/noface.png";
	private static final String PATH_IMAGEM = "images/24x24/";
	
	private static final int TAGID_MEMBRO_VERIFICADO = 0;
	private static final int TAGID_TAXA_RESPOSTA = 1;
	private static final int TAGID_ANUNCIANTE_DESDE = 2;
	private static final String GWT_STYLE = "gwt-pcfc";
	

	//private HTML primeiroNome;
	//private Image fotoPerfil;
	private FotoPerfil fotoPerfil;

	private VerticalPanel vp;
	
	public PerfilClienteFormComposite(){
		super();
		this.setStyleName(GWT_STYLE);
	}
	
	@Override
	public ClienteVO getVO() {
		return null;
	}

	@Override
	public void update(ClienteVO vo) {
		fotoPerfil.getImg().setUrl(vo.pathPerfil + vo.fotoPerfil);
		fotoPerfil.addTitulo(new HTML("<h2>" + vo.primeiroNome.toUpperCase() + "</h2>"));
		
		if (vo.indicadorVerificado.equals("S")){
			TagFormComposite tfcVerificado = new TagFormComposite();
			TagVO tvoVerificado = new TagVO();
			tvoVerificado.idtag = TAGID_MEMBRO_VERIFICADO;
			tvoVerificado.img = new Image(PATH_IMAGEM + "check.png");
			tvoVerificado.texto = new HTML("<b>Anunciante Verificado</b>");
			vp.remove(tfcVerificado);
			this.update(tfcVerificado, tvoVerificado);
		}
		
		if(vo.taxaResposta > 0){
			TagFormComposite tfcTxResposta = new TagFormComposite();
			TagVO tvoTxResposta = new TagVO();
			tvoTxResposta.idtag = TAGID_TAXA_RESPOSTA;
			tvoTxResposta.img = new Image(PATH_IMAGEM + "note_edit.png");
			tvoTxResposta.texto = new HTML("Taxa de Resposta: <b>" + vo.taxaResposta + "%</b>");
			vp.remove(tfcTxResposta);
			this.update(tfcTxResposta, tvoTxResposta);
		}
		
		TagFormComposite tfcAfiliadoDesde = new TagFormComposite();
		TagVO tvoAfiliadoDesde = new TagVO();
		tvoAfiliadoDesde.idtag = TAGID_ANUNCIANTE_DESDE;
		tvoAfiliadoDesde.img = new Image(PATH_IMAGEM + "handshake.png");
		tvoAfiliadoDesde.texto = new HTML("Membro desde " + vo.membroDesde);
		vp.remove(tfcAfiliadoDesde);
		this.update(tfcAfiliadoDesde, tvoAfiliadoDesde);

	}

	private void update(TagFormComposite tfc, TagVO vo) {
		tfc.update(vo);
		vp.add(tfc);
	}

	@Override
	public void notifier(ClienteVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Widget render() {
		vp = new VerticalPanel();
		//dp.add(primeiroNome);
		vp.add(fotoPerfil);
		return vp;
	}

	@Override
	public void init() {
		//primeiroNome = new HTML();
		//fotoPerfil = new Image();
		
		ImovelImagemVideoVO iivvo = new ImovelImagemVideoVO();
		iivvo.nome = PATH_IMAGEM_NOFACE;
		fotoPerfil = new FotoPerfil(iivvo);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}


}
