package br.com.jcv.aluguerelaxe.client.administracao.console.geolocalizacaowizard;

import br.com.jcv.aluguerelaxe.client.componente.form.FormComposite;
import br.com.jcv.aluguerelaxe.client.imovel.ficha.ImovelFichaCompletaVO;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class VisualizarGeoLocalizacaoFormComposite extends
		FormComposite<ImovelFichaCompletaVO> {

	private Widget widget;
	private DockPanel dp;
	private Icon baseIcon;
	
	public VisualizarGeoLocalizacaoFormComposite() {
		super();
		 // Create a base icon for all of our markers that specifies the
	    // shadow, icon dimensions, etc.
	    baseIcon = Icon.newInstance();
	    baseIcon.setShadowURL("http://www.google.com/mapfiles/shadow50.png");
	    baseIcon.setIconSize(Size.newInstance(20, 34));
	    baseIcon.setShadowSize(Size.newInstance(37, 34));
	    baseIcon.setIconAnchor(Point.newInstance(9, 34));
	    baseIcon.setInfoWindowAnchor(Point.newInstance(9, 2));
	}
	
	@Override
	public ImovelFichaCompletaVO getVO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ImovelFichaCompletaVO vo) {
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
		dp.add(widget, DockPanel.CENTER);
		return dp;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		dp = new DockPanel();
		widget = new HorizontalPanel();
	}

	public Widget renderMapa(ImovelFichaCompletaVO vo) {
		MapWidget mapWiget = new MapWidget(LatLng.newInstance(vo.geolocalizacao.latitude, vo.geolocalizacao.longitude), 15);
		mapWiget.setSize("640px", "480px");

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
	    final char letter = (char) ('A' + index);
	    Icon icon = Icon.newInstance(baseIcon);
	    icon.setImageURL("http://www.google.com/mapfiles/marker" + letter + ".png");
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
