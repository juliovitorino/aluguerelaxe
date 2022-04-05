package br.com.jcv.backend.datemanager;

public class DateManagerES extends DateManagerBase 
{

	public DateManagerES()
	{
		super();
	}

	public String getDayOfWeekShort() 
	{
		String daysofweek = "DomLunMarMieJueVieSab";
		int start = super.getDayOfWeek() * 3 - 3;
		return daysofweek.substring(start, start + 3);
	}

	public String getDayOfWeekLong() 
	{
		String daysofweek = "Domingo  Lunes    Martes   MiércolesJueves   Viernes  Sábado   ";
		int start = super.getDayOfWeek() * 9 - 9;
		return daysofweek.substring(start, start + 8).trim();
	}

	public String getMonthName() 
	{
		String monthsofyear = "Enero     Febrero   Marzo     Abril     Mayo      Junio     Julio     Agosto    SeptiembreOctubre   Noviembre Deciembre ";
		int start = Integer.parseInt(super.getMonth()) * 10 - 10;
		return monthsofyear.substring(start, start + 9).trim();
	}
	
}
