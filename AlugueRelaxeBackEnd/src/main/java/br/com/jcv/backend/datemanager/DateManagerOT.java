package br.com.jcv.backend.datemanager;

public class DateManagerOT extends DateManagerBase 
{

	public DateManagerOT()
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
		return daysofweek.substring(start, start + 7);
	}

	public String getMonthName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
