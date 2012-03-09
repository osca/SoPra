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
 * @author Rudolf
 *
 */

public class Methods {
	

	/**
	 * Diese Methode parst Strings zu Date im dd/MM/yyyy-Format.
	 * @param s String welches die zu parsende Eingabe representiert.
	 * @return x Das Date im dd/MM/yyyy-Format.
	 * @throws ParseException
	 */
	public static Date stringToDate(String s) throws ParseException{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date x=df.parse(s);
		return x;
	}
	/**
	 * Diese Methode gibt das Date, welches dem heutigen Datum um Null Uhr entspricht.
	 * @return Das heutige Datum Null-Uhr
	 */
	public static Date getHeuteNullUhr() {
		Date heute = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(heute);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	/**
	 * Die Methode ueberprueft, ob ein String-Date ein gueltiges datum im 
	 * dd/MM/yyyy-Format ist.
	 * @param date String-Date im dd/MM/yyyy-Format.
	 * @return boolean ob das Datum gueltig ist
	 */
	public static boolean isValidDatestring(String date) {
		String[] datearr = date.split("/");
		
		if(Integer.parseInt(datearr[1]) > 12 || Integer.parseInt(datearr[1]) == 0)
			return false;
		
		if(Integer.parseInt(datearr[1]) == 1 || Integer.parseInt(datearr[1]) == 3
			|| Integer.parseInt(datearr[1]) == 5 || Integer.parseInt(datearr[1]) == 7
			|| Integer.parseInt(datearr[1]) == 8 || Integer.parseInt(datearr[1]) == 10
			|| Integer.parseInt(datearr[1]) == 12) {
			if(Integer.parseInt(datearr[0]) > 31)
				return false;
		}
		
		if(Integer.parseInt(datearr[1]) == 4 || Integer.parseInt(datearr[1]) == 6
			|| Integer.parseInt(datearr[1]) == 9 || Integer.parseInt(datearr[1]) == 11) {
			if(Integer.parseInt(datearr[0]) > 30)
					return false;
		}
		
		if(Integer.parseInt(datearr[1]) == 2) {
			if(Integer.parseInt(datearr[2])%4 == 0) {
				if(Integer.parseInt(datearr[0]) > 28)
					return false;
		
			}
			else {
				if(Integer.parseInt(datearr[0]) > 29)
					return false;
			}
		}
		
		return true;
	}
}
