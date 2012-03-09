package testcases;

import junit.framework.Assert;

import org.junit.Test;

import angebote.kriterien.Land;


public class LaenderTest {
	
	@Test
	public void testOrte(){
		Land country = new Land("France");
		String[] list = Land.getOrte(country.getWert());
		Assert.assertTrue(list.length!=0);
	}
}
