package br.com.jcv.aluguerelaxe.client.administracao.console.proprietario;


import br.com.jcv.aluguerelaxe.client.cliente.ClienteVO;
import br.com.jcv.aluguerelaxe.client.componente.upload.AbstractUploadFotoFormComposite;

public class UploadFotoPerfilFormComposite extends AbstractUploadFotoFormComposite {
	
	private static final String TEXTO_CHAT = "<p>Enviar sua imagem passa maior confian\u00e7a ao visitante.</p> " +
	"</p>Isso tornar\u00e1 a conversa mais pr\u00f3xima e haver\u00e1 um estreitamento no relacionamento entre voc\u00eas.</p> " +
	"</p>Ele realmente entender\u00e1 que tem outra pessoa do outro lado.</p>" ;
	
	private static final String PATH_IMAGEM = "/stream/ar/picture_jcv_128x128.jpg";
	private static final String URL_SERVLET_UPLOAD = "/arweb/uploaderFotoPerfil.svlt";
	private static final String URL_SERVLET_HOSTED_MODE = "/uploaderFotoPerfil.svlt";

	public UploadFotoPerfilFormComposite() {
		super();
	}
	public String getTextoChat() {
		return TEXTO_CHAT;
	}
	
	public String getUrlImagemChat() {
		return PATH_IMAGEM;
	}
	
	public String getUrlServletUpload() {
		return URL_SERVLET_UPLOAD;
	}
	
	public String getUrlServletHostedMode() {
		return URL_SERVLET_HOSTED_MODE;
	}
	
	public String getTituloChat() {
		return "POR QUE ENVIAR UMA FOTO?" ;
	}
	
	public boolean isHostedMode() {
		// se quiser hosted mode, basta trocar por true
		return false;
	}
	
	public void updateUploadPK(String pk) {
	}

	// Alimenta campo que sera enviado a servlet de upload
	public void update(ClienteVO vo) {
		idUploadPK.setValue(String.valueOf(vo.id));
	}
	

}
