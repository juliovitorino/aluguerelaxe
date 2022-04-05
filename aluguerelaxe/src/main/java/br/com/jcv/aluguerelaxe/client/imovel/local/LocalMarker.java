package br.com.jcv.aluguerelaxe.client.imovel.local;

import java.util.Date;

import br.com.jcv.aluguerelaxe.client.componente.chat.ChatFormComposite;
import br.com.jcv.aluguerelaxe.client.componente.chat.ChatVO;

import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.HTML;

/**
 * <h2>LocalMarker</h2>
 *
 * <p>Cria um objeto marcador simples com uma imagem
 * e armazena as informacoes do local no marcador</p>
 * @author Julio Vitorino
 */
public class LocalMarker extends Marker {

	private LocalItemVO localInfo;
	private MapWidget map;
	
	/**
	 * Cria o marcador em uma determinada geolocalizacao, coloca uma imagem customizada no pin-point
	 * e armazena as informacoes do local para ser usada em caso de disparo de eventos.
	 * @param LatLng - Ponto geografico do marcador
	 * @param String - URL da imagem do marcador
	 * @param LocalItemVO - Informacoes sobre o local que o marcador esta apontando
	 */
	public LocalMarker(LatLng point, MarkerOptions options, MapWidget map, LocalItemVO localInfo) {
		super(point, options);
		this.localInfo = localInfo;
		this.map = map;
		
	    this.addMarkerClickHandler(new MarkerClickHandler() {

	      public void onClick(MarkerClickEvent event) {
	    	StringBuilder chat = new StringBuilder();
	    	chat.append(LocalMarker.this.localInfo.descricao);
	    	chat.append("<p>Dist\u00e2ncias a partir do im\u00f3vel:" + LocalMarker.this.localInfo.distancia.distanciaKm + " Km"+"</p>");
	    	chat.append("<p>Tempo gasto a partir do im\u00f3vel:</p>");
	    	chat.append("<img src=\"images/24x24/schoolboy.png\"/>");
	    	chat.append("A P\u00e9: " + LocalMarker.this.localInfo.distancia.tempoGastoPe + " (min)<br/>");
	    	chat.append("<img src=\"images/24x24/car.png\"/>");
	    	chat.append("De carro: " + LocalMarker.this.localInfo.distancia.tempoGastoCarro + " (min)<br/>");
	    	chat.append("<br/>Veja os links para mais detalhes<br/>");
	    	if (!LocalMarker.this.localInfo.urlRef_1.equals("null")){
	    		chat.append("<a href=\"javascript: void(0)\" onclick=\"window.open('" +LocalMarker.this.localInfo.urlRef_1 + "','windowname2','directories=no,location=no,menubar=no,resizable=no,scrollbars=1,status=no,toolbar=no');return false;\">"+LocalMarker.this.localInfo.urlRef_1+"</a><br/>");
	    	}
	    	if (!LocalMarker.this.localInfo.urlRef_2.equals("null")){
	    		chat.append("<a href=\"javascript: void(0)\" onclick=\"window.open('" +LocalMarker.this.localInfo.urlRef_2 + "','windowname2','directories=no,location=no,menubar=no,resizable=no,scrollbars=1,status=no,toolbar=no');return false;\">"+LocalMarker.this.localInfo.urlRef_2+"</a><br/>");
	    	}
	    	if (!LocalMarker.this.localInfo.urlRef_3.equals("null")){
	    		chat.append("<a href=\"javascript: void(0)\" onclick=\"window.open('" +LocalMarker.this.localInfo.urlRef_3 + "','windowname2','directories=no,location=no,menubar=no,resizable=no,scrollbars=1,status=no,toolbar=no');return false;\">"+LocalMarker.this.localInfo.urlRef_3+"</a><br/>");
	    	}
	    	if (!LocalMarker.this.localInfo.urlRef_4.equals("null")){
	    		chat.append("<a href=\"javascript: void(0)\" onclick=\"window.open('" +LocalMarker.this.localInfo.urlRef_4 + "','windowname2','directories=no,location=no,menubar=no,resizable=no,scrollbars=1,status=no,toolbar=no');return false;\">"+LocalMarker.this.localInfo.urlRef_4+"</a><br/>");
	    	}
	    	if (!LocalMarker.this.localInfo.urlRef_5.equals("null")){
	    		chat.append("<a href=\"javascript: void(0)\" onclick=\"window.open('" +LocalMarker.this.localInfo.urlRef_5 + "','windowname2','directories=no,location=no,menubar=no,resizable=no,scrollbars=1,status=no,toolbar=no');return false;\">"+LocalMarker.this.localInfo.urlRef_5+"</a><br/>");
	    	}
	    	
	        InfoWindow info = LocalMarker.this.map.getInfoWindow();
	    	ChatFormComposite cfc = new ChatFormComposite();
	    	ChatVO chatvo = new ChatVO();
	    	chatvo.urlFoto = LocalMarker.this.localInfo.urlIconeLocalItem;
	    	chatvo.titulo = LocalMarker.this.localInfo.descricaoBase;
	    	chatvo.chat = chat.toString();
	    	chatvo.dataPost = (new Date()).toString();
	    	cfc.update(chatvo);
	    	
//	        info.open(event.getSender(), new InfoWindowContent("Marker <b>"
//		            + LocalMarker.this.localInfo.descricao + "</b>"));
	    	InfoWindowContent iwc = new InfoWindowContent(LocalMarker.this.localInfo.descricao);
	        iwc.setMaxTitle(new HTML("<i>" + LocalMarker.this.localInfo.descricao + "</i>"));
	        iwc.setMaxContent(new HTML(cfc.toString()));
	        info.open(event.getSender(), iwc);
	      }

	    });		
	}
	
	/**
	 * devolve as informacoes previamente armazenadas no marcador
	 * @return LocalItemVO - Informacoes sobre o local que o marcador esta apontando
	 */
	public LocalItemVO getLocalInfo() {
		return this.localInfo;
	}
	
}
