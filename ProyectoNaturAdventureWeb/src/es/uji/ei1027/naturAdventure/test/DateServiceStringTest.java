package es.uji.ei1027.naturAdventure.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import es.uji.ei1027.naturAdventure.service.DateService;

@RunWith( Parameterized.class )
public class DateServiceStringTest {

	private String dateString;
	private int expectedDay;
	private int expectedMonth;
	private int expectedYear;
	private DateService dateService;
	
	public DateServiceStringTest( String dateString, int expectedDay, int expectedMonth, int expectedYear ) {
		this.dateString = dateString;
		this.expectedDay = expectedDay;
		this.expectedMonth = expectedMonth;
		this.expectedYear = expectedYear;
	}
	
	@Parameters
	public static Collection<Object[]> datos() {
		return Arrays.asList( new Object[][] { 
			{ "2015-05-07", 7, 5, 2015 },
			{ "2010-01-01", 1, 1, 2010 },
			{ "2010-5-5", 5, 5, 2010 },
		});
	}
	
	@Before
	public void setDateService() {
		this.dateService = new DateService( dateString );
	}

	@Test
	public void test() {
		assertEquals( this.expectedDay, this.dateService.getDay() );
		assertEquals( this.expectedMonth, this.dateService.getMonth() );
		assertEquals( this.expectedYear, this.dateService.getYear() );
	}

}
