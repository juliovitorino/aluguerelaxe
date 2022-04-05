package br.com.jcv.backend.datemanager;

public class DateManagerUS extends DateManagerBase 
{

	public DateManagerUS()
	{
		super();
	}

	public String getDayOfWeekShort() 
	{
		String daysofweek = "SunMonTueWedThuFriSat";
		int start = super.getDayOfWeek() * 3 - 3;
		return daysofweek.substring(start, start + 3);
	}

	public String getDayOfWeekLong() 
	{
		String daysofweek = "Sunday   Monday   Tuesday  WednesdayThursday Friday   Saturday ";
		int start = super.getDayOfWeek() * 9 - 9;
		return daysofweek.substring(start, start + 8).trim();
	}

	public String getMonthName() 
	{
		String monthsofyear = "January   February  March     April     May       June      July      August    September October   November  December  ";
		int start = Integer.parseInt(super.getMonth()) * 10 - 10;
		return monthsofyear.substring(start, start + 9).trim();
	}
	
}
