package br.com.jcv.aluguerelaxe.client.imovel.ficha;

import java.util.List;

import br.com.jcv.aluguerelaxe.client.ServicosRPC;
import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.componente.grid.GridClassificacao;
import br.com.jcv.aluguerelaxe.client.componente.grid.GridLocal;
import br.com.jcv.aluguerelaxe.client.imovel.local.ClassificacaoVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalItemVO;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalMarker;
import br.com.jcv.aluguerelaxe.client.imovel.local.LocalVO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LocalizacaoFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {

	private static final String PATH_IMAGES_BASE = "images/";
	private static final String PATH_IMAGES = "images/32x32/";
	private Widget widget;
	private DockPanel dp;
	private Icon baseIcon;
	private HTML endereco;
	private VerticalPanel vpEndereco;
	private VerticalPanel vpLocalClassificacao;
	private GridLocal grdLocal;
	private GridClassificacao grdClassificacao;
	private ImovelFichaCompletaVO ifcvo;
	
	public LocalizacaoFormComposite() {
		super();
		this.setStyleName("gwt-LocalizacaoFormComposite");
		// Create a base icon for all of our markers that specifies the
	    // shadow, icon dimensions, etc.
	    baseIcon = Icon.newInstance();
	    //baseIcon.setShadowURL("http://www.google.com/mapfiles/shadow50.png");
	    baseIcon.setIconSize(Size.newInstance(32, 32));
	    //baseIcon.setShadowSize(Size.newInstance(37, 34));
	    baseIcon.setIconAnchor(Point.newInstance(9, 34));
	    baseIcon.setInfoWindowAnchor(Point.newInstance(9, 2));
	}
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		return null;
	}
	
	public void update(List<LocalItemVO> lst) {
		// Recria o map overlay
		((MapWidget)widget).clearOverlays();
		update(this.ifcvo);
		
		// cria os demais icones em suas localidades
		for (LocalItemVO livo : lst){
			LatLng point = LatLng.newInstance(livo.latitude, livo.longitude);

			Icon icon = Icon.newInstance(baseIcon);
		    icon.setImageURL(livo.urlIconeLocal);	    
		    MarkerOptions options = MarkerOptions.newInstance();
		    options.setIcon(icon);
			((MapWidget)widget).addOverlay(new LocalMarker(point, options, (MapWidget) widget, livo));
		}
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
		if ((vo != null) && (vo.imovel != null) && (vo.imovel.endereco != null)) {
			this.ifcvo = vo;
			StringBuilder enderecohtml = new StringBuilder();

			enderecohtml.append("<b>Endere\u00e7o:")
			.append(vo.imovel.endereco.logradouro).append(" ")
			.append(vo.imovel.endereco.nome).append(", ")
			.append(vo.imovel.endereco.numero).append(" - ")
			.append(vo.imovel.endereco.complemento == null ? "" : vo.imovel.endereco.complemento + " - ")
			.append(vo.imovel.endereco.bairro).append(" - ")
			.append(vo.imovel.endereco.cidade).append("/")
			.append(vo.imovel.endereco.uf).append("</b>");
			endereco.setHTML(enderecohtml.toString());
		}
		if ( 
				( vo.geolocalizacao.latitude != -1000) &&
				( vo.geolocalizacao.longitude != -1000) 
			) {
			dp.remove(widget);
			widget = renderMapa(vo);
			dp.add(widget,DockPanel.CENTER);
		}
	}

	@Override
	public Widget render() {
		dp = new DockPanel();
		vpEndereco.add(endereco);
		dp.add(widget, DockPanel.CENTER);
		dp.add(vpEndereco, DockPanel.NORTH);
		
		vpLocalClassificacao.add(new Image(PATH_IMAGES_BASE + "vejaatracao.png"));
		vpLocalClassificacao.add(grdLocal);
		vpLocalClassificacao.add(grdClassificacao);
		Button btnLocalClassificacao = new Button("Clique aqui para ver os locais no mapa");
		vpLocalClassificacao.add(btnLocalClassificacao);
		dp.add(vpLocalClassificacao, DockPanel.SOUTH);
		
		btnLocalClassificacao.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				// Obtem os itens selecionados dos grids
				List<LocalVO> lstlocal = LocalizacaoFormComposite.this.grdLocal.getList();
				List<ClassificacaoVO> lstclass = LocalizacaoFormComposite.this.grdClassificacao.getList();
				
				FichaImovelRPCAsync rpc = ServicosRPC.getFichaImovelRPC();
				AsyncCallback<List<LocalItemVO>> callback = new AsyncCallback<List<LocalItemVO>>() {

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					public void onSuccess(List<LocalItemVO> result) {
						if ((result != null) && (result.size() > 0)){
							LocalizacaoFormComposite.this.update(result);
						}
					}
				};
				
				rpc.listarLocalItem(LocalizacaoFormComposite.this.ifcvo.imovel.endereco.codigoUfCidadeItem,
									lstlocal, 
									lstclass,
									LocalizacaoFormComposite.this.ifcvo.geolocalizacao, 
									callback);
			}});
		
		
		return dp;
	}

	@Override
	public void clear() {
	}

	@Override
	public void init() {
		dp = new DockPanel();
		widget = new HorizontalPanel();
		endereco = new HTML();
		vpEndereco = new VerticalPanel();
		vpLocalClassificacao = new VerticalPanel();
		grdLocal = new GridLocal(9);
		grdClassificacao = new GridClassificacao(9);

		vpEndereco.setWidth("260px");
	}

	public Widget renderMapa(ImovelFichaCompletaVO vo) {
		MapWidget mapWiget = new MapWidget(LatLng.newInstance(vo.geolocalizacao.latitude, vo.geolocalizacao.longitude), 12);
		mapWiget.setSize("910px", "480px");

	    mapWiget.addControl(new SmallMapControl());
	    mapWiget.addControl(new MapTypeControl());
	    LatLng point = LatLng.newInstance(vo.geolocalizacao.latitude, vo.geolocalizacao.longitude);
	    mapWiget.addOverlay(createMarker(point, 0));
	    return mapWiget;
	}
	
	/**
	   * Creates a marker whose info window displays the letter corresponding to the
	   * given index.
	   */
	  private Marker createMarker(LatLng point, int index) {
	    // Create a lettered icon for this point using our icon class
	    //final char letter = (char) ('A' + index);
	    Icon icon = Icon.newInstance(baseIcon);
	    icon.setImageURL(PATH_IMAGES + "house_pointer.png");	    
	    //icon.setImageURL("http://www.google.com/mapfiles/marker" + letter + ".png");
	    MarkerOptions options = MarkerOptions.newInstance();
	    options.setIcon(icon);
	    final Marker marker = new Marker(point, options);

//	    marker.addMarkerClickHandler(new MarkerClickHandler() {
//
//	      public void onClick(MarkerClickEvent event) {
//	        InfoWindow info = map.getInfoWindow();
//	        info.open(event.getSender(), new InfoWindowContent("Marker <b>"
//	            + letter + "</b>"));
//	      }
//
//	    });

	    return marker;
	  }

	@Override
	public void notifier(ImovelFichaCompletaVO vo) {
		// TODO Auto-generated method stub
		
	}	

}
