package br.com.jcv.backend;

import junit.framework.TestCase;
import br.com.jcv.backend.charter.AbstractStaticCharter;
import br.com.jcv.backend.charter.CharterSequence;
import br.com.jcv.backend.charter.MapaBrasilCharter;
import br.com.jcv.backend.charter.PizzaCharter;

public class CharterTestCase extends TestCase {
	
	public void testCharterPizza3D() {
		AbstractStaticCharter<CharterSequence> asc = PizzaCharter.getPizzaCharter3D(250, 100);
		asc.addCharterSequence(new CharterSequence("RJ",10));
		asc.addCharterSequence(new CharterSequence("PE",5));
		asc.addCharterSequence(new CharterSequence("SP",20));
		asc.addCharterSequence(new CharterSequence("DF",30));
		asc.addCharterSequence(new CharterSequence("RS",80));
		asc.addCharterSequence(new CharterSequence("TO",85));
		asc.addCharterSequence(new CharterSequence("AM",90));
		String url = asc.getUrl();
		assertNotNull(url);
	}
	
	public void testCharterPizza2D() {
		AbstractStaticCharter<CharterSequence> asc = PizzaCharter.getPizzaCharter2D(250, 100);
		asc.addCharterSequence(new CharterSequence("RJ",10));
		asc.addCharterSequence(new CharterSequence("PE",5));
		asc.addCharterSequence(new CharterSequence("SP",20));
		asc.addCharterSequence(new CharterSequence("DF",30));
		asc.addCharterSequence(new CharterSequence("RS",80));
		asc.addCharterSequence(new CharterSequence("TO",85));
		asc.addCharterSequence(new CharterSequence("AM",90));
		String url = asc.getUrl();
		assertNotNull(url);
	}

	public void testCharterMapaBrasilSozinho() {
		AbstractStaticCharter<CharterSequence> asc = new MapaBrasilCharter(AbstractStaticCharter.TIPO_CHARTER_MAPA_BRASIL,600, 300);
		String url = asc.getUrl();
		assertNotNull(url);
	}

	public void testCharterMapaBrasilRJ() {
		AbstractStaticCharter<CharterSequence> asc = new MapaBrasilCharter(AbstractStaticCharter.TIPO_CHARTER_MAPA_BRASIL,600, 300);
		asc.addCharterSequence(new CharterSequence("RJ",0));
		String url = asc.getUrl();
		assertNotNull(url);
	}
	public void testCharterMapaBrasilRJSP() {
		AbstractStaticCharter<CharterSequence> asc = new MapaBrasilCharter(AbstractStaticCharter.TIPO_CHARTER_MAPA_BRASIL,600, 300);
		asc.addCharterSequence(new CharterSequence("RJ",0));
		asc.addCharterSequence(new CharterSequence("SP",0));
		String url = asc.getUrl();
		assertNotNull(url);
	}

}
