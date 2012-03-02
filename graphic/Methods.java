package graphic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Methods {
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
	
	public static String format4long(int length){
		String s ="";
		for(int i=0;i<length;i++){
			s += "*";
		}
		return s;
	}

}
