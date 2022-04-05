package br.com.jcv.backend.datemanager;

public class DateManagerBR extends DateManagerBase 
{

	public DateManagerBR()
	{
		super();
	}

	public String getDayOfWeekShort() 
	{
		String daysofweek = "DomSegTerQuaQuiSexSab";
		int start = super.getDayOfWeek() * 3 - 3;
		return daysofweek.substring(start, start + 3);
	}

	public String getDayOfWeekLong() 
	{                        
		String daysofweek = "Domingo Segunda Terça   Quarta  Quinta  Sexta   Sábado  ";
		int start = super.getDayOfWeek() * 8 - 8;
		return daysofweek.substring(start, start + 7).trim();
	}

	public String getMonthName() 
	{
		String monthsofyear = "Janeiro   Fevereiro Março     Abril     Maio      Junho     Julho     Agosto    Setembro  Outubro   Novembro  Dezembro  ";
		int start = Integer.parseInt(super.getMonth()) * 10 - 10;
		return monthsofyear.substring(start, start + 9).trim();
	}
	
}
