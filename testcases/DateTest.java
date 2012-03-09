package testcases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;


public class DateTest {
	
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@SuppressWarnings("deprecation")
	@Test
	public void testDatum1() throws ParseException{
		String start = "01/01/1990",
				  end = "31/01/1990";
		Date[] daten = graphic.Methods.dater(start, end, 1);
		Assert.assertEquals(new Date("01/01/1990"), daten[0]);
		Assert.assertEquals(new Date("01/05/1990"), daten[4]);
		Assert.assertEquals(start,df.format(daten[0]));
		Assert.assertEquals("23/01/1990", df.format(daten[22]));
		Assert.assertEquals(30, daten.length);
	}
	
	@Test
	public void testDatum2() throws ParseException{
		String start = "23/12/2023",
					end = "05/01/2024";
		Date daten[]	= graphic.Methods.dater(start, end, 4);
		Assert.assertEquals(4, daten.length);
	}
}
