package br.com.jcv.aluguerelaxe.client.administracao.console.uploadwizard;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.vo.VOPadrao;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class InstrucoesUploadFormComposite extends FormComposite<VOPadrao> {

	private static final String INSTRUCOES_UPLOAD = "<p>" +
			"1. Clicar no bot\u00e3o adicionar.</p>" +
			"<p>2. Clicar no bot\u00e3o Procurar. Selecione a foto desejada e clique em Abrir ou OK.</p>"+
			"<p>3. Repita os passos 1 e 2 para quantas forem as fotos que voc\u00ea deseja.</p>"+
			"<p>4. Clicar no \u00edcone 'Enviar' para suas fotos serem enviadas para sua galeria de imagens do im\u00f3vel.</p>" +
			"<p>Aguarde a transmiss\u00e3o, poder\u00e1 levar alguns minutos dependendo da quantidade de fotos e tamanho das fotos.<br/>"+
			"Quando for apresentada a mensagem 'Imagem Transferida!' para cada imagem, o processo estar\u00e1 encerrado.</p>" +
			"<br/><p>CLIQUE NA IMAGEM DO SEU IM\u00d3VEL PARA RECARREGAR AS IMAGENS ENVIADAS.</p><br/>" +
			"<p>* Tamanho m\u00e1ximo de cada imagem deve ser de 4MB. Envie imagens do tipo JPG.</p>" +
			"<p>\u00c9 expressamente proibido:</p>" +
			"<p>a) Enviar fotos contendo marcas d'agua;</p>" +
			"<p>b) Fotos que podem fazer apologia \u00e0 pedofilia e/ou sexo;</p>" +
			"<p>c) Fotos que contenham e-mail, telefones e URL para sites;</p>" +
			"<p>Todos as fotos que se enquadrarem nos crit\u00e9rios acima, ser\u00e3o REMOVIDAS de nossa base de dados sem pr\u00e9vio aviso ao anunciante.</p>" ;		

	private HTML instrucoes = null;
				
	@Override
	public VOPadrao getVO() {
		// Nao Aplicavel
		return null;
	}

	@Override
	public void update(VOPadrao vo) {
		// Nao Aplicavel
	}

	@Override
	public Widget render() {
		VerticalPanel vp = new VerticalPanel();
		vp.add(instrucoes);
		return vp;
	}

	@Override
	public void init() {
		instrucoes = new HTML();
		instrucoes.setHTML(INSTRUCOES_UPLOAD);
	}

	@Override
	public void clear() {
		// Nao aplicavel
		
	}

	@Override
	public void notifier(VOPadrao vo) {
		// TODO Auto-generated method stub
		
	}

}
