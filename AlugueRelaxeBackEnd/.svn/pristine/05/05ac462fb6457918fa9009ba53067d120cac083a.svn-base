package br.com.jcv.backend.charter;

public class PizzaCharter extends AbstractStaticCharter<CharterSequence> {

	private PizzaCharter(int tipo, int width, int height) {
		super(tipo, width, height);
	}
	
	public static PizzaCharter getPizzaCharter3D(int width, int height){
		return new PizzaCharter(AbstractStaticCharter.TIPO_CHARTER_PIZZA_3D, width, height);
	}

	public static PizzaCharter getPizzaCharter2D(int width, int height){
		return new PizzaCharter(AbstractStaticCharter.TIPO_CHARTER_PIZZA_2D, width, height);
	}
	
	public String getUrl() {
		String chs;
		StringBuilder chd = new StringBuilder();
		StringBuilder chl = new StringBuilder();
		StringBuilder urlCompleta = new StringBuilder();
		
		// Dimensoes
		chs = String.valueOf(getWidth()) + "x" + String.valueOf(getHeight());
		
		// Sequencia e Legendas
		chd.append("t:");
		for( CharterSequence csItem : getCharterSequence() ) {
			chd.append(String.valueOf(csItem.valor)).append(",");
			chl.append(csItem.legenda).append("|");
		}
		
		// monta a URL completa
		urlCompleta.append(AbstractStaticCharter.URL_GOOGLE_CHARTER)
				.append(getUrlTipo())
				.append("&chd=")
				.append(chd.toString().substring(0,chd.toString().length()-1))
				.append("&chs=")
				.append(chs)
				.append("&chl=")
				.append(chl.toString().substring(0,chl.toString().length()-1));
		
		return urlCompleta.toString();
		
		// retornando uma url de teste (O resultado de urlCompleta deve ser parecido com o mostrado abaixo)
		//return "https://chart.googleapis.com/chart?cht=p3&chd=t:10,30,40,20&chs=350x100&chl=Preventiva(10%)|Corretiva(30%)|Preditiva(40%)|Outros(20%)";
	}
	
}
