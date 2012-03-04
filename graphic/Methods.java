package graphic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 
 * @author Rudi
 *
 */

public class Methods {
	
	/**
	 * Eine Methode zum Ausgeben von einem Array aus Daten,
	 * welche zwischen strat und end liegen und eine Taktung haben.
	 * @param start Start-Datum
	 * @param end End-Datum
	 * @param interval Taktung der Daten im Array
	 * @return Array mit Daten zwischen Start und End in einer Taktung
	 * @throws ParseException
	 */
	public static Date[] dater(String start, String end, int  interval) throws ParseException{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date s = 	df.parse(start);
		Date e =    df.parse(end);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(s);
		
		ArrayList<Date> temp = new ArrayList<Date>();
		while(s.before(e)){
			temp.add(s);
			calendar.add(Calendar.DAY_OF_MONTH, interval);
			Date a = calendar.getTime();
			s=df.parse(df.format(a));
		}
		Date[] d= new Date[temp.size()];
		for (int i=0;i<d.length;i++){
			d[i]=temp.get(i);
		}
		return d;
	}
	
	public static boolean checkDate(Date s, Date e){
		if(s.before(e)){
			return true;
		}
		else return false;
	}
	public static Date stringToDate(String s) throws ParseException{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date x=df.parse(s);
		return x;
	}
}
