package br.com.jcv.backend.charter;

public class MapaBrasilCharter extends AbstractStaticCharter<CharterSequence> {

	private String chtt;
	
	public MapaBrasilCharter(int urlTipo, int width, int height) {
		super(urlTipo, width, height);
	}
	
	public void setchtt(String tituloGrafico){
		this.chtt = tituloGrafico;
	}

	@Override
	public String getUrl() {
		String chs;
		StringBuilder chld = new StringBuilder();
		StringBuilder chco = new StringBuilder();
		StringBuilder chm = new StringBuilder();
		StringBuilder urlCompleta = new StringBuilder();
		
		// Dimensoes
		chs = String.valueOf(getWidth()) + "x" + String.valueOf(getHeight());
		
		// Inicializa Cor dos mapas fora do Brasil
		chco.append("FFFFFF|");
		
		// Inicializa parametrizacao do Brasil
		MapInfo brasil = UfMapBrasilInfo.mapInfo.get(UfMapBrasilInfo.BR);
		chld.append(brasil.uf)
			.append("|");
		chco.append(brasil.chco)
			.append("|");
		chm.append(brasil.getChm(0))
			.append("|");
		
		// Sequencia e Legendas
		int i = 1;
		for( CharterSequence csItem : getCharterSequence() ) {
			
			//Obtem informacoes da UF que esta no mapa estatico
			MapInfo mi = UfMapBrasilInfo.mapInfo.get(csItem.legenda);
			
			chld.append(mi.chld).append("|");
			chco.append(mi.chco).append("|");
			chm.append(mi.getChm(i++)).append("|");
		}
		
		// monta a URL completa
		urlCompleta.append(AbstractStaticCharter.URL_GOOGLE_CHARTER)
				.append(getUrlTipo())
				.append("&chs=")
				.append(chs);
		if (chtt != null){
			urlCompleta.append("&chtt=")
					.append(chtt);
		}
		
		if (chld.toString().length() > 0){
			urlCompleta.append("&chld=")
				.append(chld.toString().substring(0,chld.toString().length()-1));
		}
		
		if (chco.toString().length() > 0){
			urlCompleta.append("&chco=")
				.append(chco.toString().substring(0,chco.toString().length()-1));
		}
		
		if (chm.toString().length() > 0){
			urlCompleta.append("&chm=")
				.append(chm.toString().substring(0,chm.toString().length()-1));
		}
		
		return urlCompleta.toString();
	}

}
