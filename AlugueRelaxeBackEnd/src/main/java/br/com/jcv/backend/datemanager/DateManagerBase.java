/*
 * Created on 19/08/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.com.jcv.backend.datemanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author elmt
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class DateManagerBase
{
	private Date date;
	private SimpleDateFormat df;
	private Calendar cal = new GregorianCalendar();
	
	protected DateManagerBase()
	{
		// get current system date
		date = new Date(System.currentTimeMillis());
		df = new SimpleDateFormat();
		cal.setTime(date);
	}

	protected DateManagerBase(Date date)
	{
		// get current system date
		this.date = date;
		df = new SimpleDateFormat();
		cal.setTime(date);
	}
	protected DateManagerBase(long milisecs)
	{
		// get current system date
		df = new SimpleDateFormat();
		cal.setTimeInMillis(milisecs);
		this.date = cal.getTime();
	}
	
	public static DateManagerBase getDateManagerInstance()
	{
		return new DateManager();
	}

	public static DateManagerBase getDateManagerInstance(Date date)
	{
		return new DateManager(date);
	}
	public static DateManagerBase getDateManagerInstance(long milisecs)
	{
		return new DateManager(milisecs);
	}
	public static DateManagerBase getDateManagerInstance(String language)
	{
		if (language.equals("US")) return new DateManagerUS();
		if (language.equals("BR")) return new DateManagerBR();
		if (language.equals("ES")) return new DateManagerES();
		if (language.equals("FR")) return new DateManagerFR();
		if (language.equals("OT")) return new DateManagerOT();
		return null;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public Date add(int dias){
		this.cal.add(Calendar.DAY_OF_MONTH, dias);
		this.date = cal.getTime();
		return this.cal.getTime();
	}
	
	public long getTimeInMillis()
	{
		return cal.getTimeInMillis();
	}
	
	private String dateFormat()
	{
		return df.format(date);
	}
	
	public String getYear()
	{
		df.applyPattern("yyyy");
		return dateFormat();
	}
	
	public String getMonth()
	{
		df.applyPattern("MM");
		return dateFormat();
	}
	
	public String getDay()
	{
		df.applyPattern("dd");
		return dateFormat();
	}

	public int getDayOfWeek()
	{
		return cal.get(Calendar.DAY_OF_WEEK); // 1=Sunday, 2=Monday, ...
	}

	public String getHour()
	{
		df.applyPattern("HH");
		return dateFormat();
	}
	
	public String getMinutes()
	{
		df.applyPattern("mm");
		return dateFormat();
	}
	
	public String getSeconds()
	{
		df.applyPattern("ss");
		return dateFormat();
	}
	
	/*
	 * Veja SimpleDateFormat para mais detalhes 
	 */
	public String getDateCustom(String pattern) throws ParseException
	{
		df.applyPattern(pattern);
		return dateFormat();
	}
	
	public String getDateTimeFull() throws ParseException
	{
		df.applyPattern("dd/MM/yyyy HH:mm:ss");
		return dateFormat();
	}
	
	public int getTeste()
	{
		return 0;
	}
	
	/** 
	  * Método para comparar as das e retornar o numero de dias de diferença entre elas 
	  * 
	  * Compare two date and return the difference between them in days. 
	  * 
	  * @param dataLow The lowest date 
	  * @param dataHigh The highest date 
	  * 
	  * @return int 
	  */  
	public int dataDiff(java.util.Date dataLow, java.util.Date dataHigh){  
	  
	     GregorianCalendar startTime = new GregorianCalendar();  
	     GregorianCalendar endTime = new GregorianCalendar();  
	       
	     GregorianCalendar curTime = new GregorianCalendar();  
	     GregorianCalendar baseTime = new GregorianCalendar();  
	  
	     startTime.setTime(dataLow);  
	     endTime.setTime(dataHigh);  
	       
	     int dif_multiplier = 1;  
	       
	     // Verifica a ordem de inicio das datas  
	     if( dataLow.compareTo( dataHigh ) < 0 ){  
	         baseTime.setTime(dataHigh);  
	         curTime.setTime(dataLow);  
	         dif_multiplier = 1;  
	     }else{  
	         baseTime.setTime(dataLow);  
	         curTime.setTime(dataHigh);  
	         dif_multiplier = -1;  
	     }  
	       
	     int result_years = 0;  
	     int result_months = 0;  
	     int result_days = 0;  
	  
	     // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando  
	     // no total de dias. Ja leva em consideracao ano bissesto  
	     while( curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR) ||  
	            curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)  )  
	     {  
	           
	         int max_day = curTime.getActualMaximum( GregorianCalendar.DAY_OF_MONTH );  
	         result_months += max_day;  
	         curTime.add(GregorianCalendar.MONTH, 1);  
	           
	     }  
	       
	     // Marca que é um saldo negativo ou positivo  
	     result_months = result_months*dif_multiplier;  
	       
	       
	     // Retirna a diferenca de dias do total dos meses  
	     result_days += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));  
	       
	     return result_years+result_months+result_days;  
	}  	
	
	public abstract String getDayOfWeekShort();
	public abstract String getDayOfWeekLong();
	public abstract String getMonthName();
	
}
