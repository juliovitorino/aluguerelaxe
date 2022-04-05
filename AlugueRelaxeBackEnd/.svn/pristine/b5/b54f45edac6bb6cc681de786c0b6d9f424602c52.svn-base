package br.com.jcv.backend.datemanager;

public class DateManagerFR extends DateManagerBase 
{

	public DateManagerFR()
	{
		super();
	}

	public String getDayOfWeekShort() 
	{
		String daysofweek = "DimLunMarMerJeuVenSam";
		int start = super.getDayOfWeek() * 3 - 3;
		return daysofweek.substring(start, start + 3);
	}

	public String getDayOfWeekLong() 
	{
		String daysofweek = "Dimanche Lundi    Mardi    Mercredi Jeudi    Vendredi Samedi   ";
		int start = super.getDayOfWeek() * 9 - 9;
		return daysofweek.substring(start, start + 8).trim();
	}

	public String getMonthName() 
	{
		String monthsofyear = "Janvier   Février   Mars      Avril     Mai       Juin      Juillet   Août      Septembre Octobre   Novembre  Décembre  ";
		int start = Integer.parseInt(super.getMonth()) * 10 - 10;
		return monthsofyear.substring(start, start + 9).trim();
	}
	
}
