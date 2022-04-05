package br.com.jcv.aluguerelaxe.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
* <h2>DateParser</h2>
* <p>Parser para data com GWT</p>
* @author Julio Vitorino
*/
public class DateParser {

	public static String formatador(Date date) {
		DateTimeFormat formatador = DateTimeFormat.getFormat("dd/MM/yyyy");
		return formatador.format(date);
	}

	public static String getDataHora(Date date) {
		DateTimeFormat formatador = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");
		return formatador.format(date);
	}

	public static String getDataHoraCheia(Date date) {
		DateTimeFormat formatador = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
		return formatador.format(date);
	}
}
