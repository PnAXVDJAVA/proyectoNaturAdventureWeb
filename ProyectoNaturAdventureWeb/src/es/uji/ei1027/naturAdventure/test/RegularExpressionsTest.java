package es.uji.ei1027.naturAdventure.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import es.uji.ei1027.naturAdventure.service.RegularExpression;


@RunWith( Parameterized.class )
public class RegularExpressionsTest {
	
	private String str;
	private boolean expectedResult;
	private String regularExpression;
	
	public RegularExpressionsTest(  String str, boolean expectedResult, String regularExpression ) {
		this.str = str;
		this.expectedResult = expectedResult;
		this.regularExpression = regularExpression;
	}
	
	@Parameters
	public static Collection<Object[]> datos() {
		return Arrays.asList( new Object[][] {
				{ "654556817", true, RegularExpression.TELEPHONE_RE },
				{ "754556817", true , RegularExpression.TELEPHONE_RE },
				{ "954556817", true , RegularExpression.TELEPHONE_RE },
				{ "+34 954556817", true , RegularExpression.TELEPHONE_RE },
				{ "+500 954556817", true , RegularExpression.TELEPHONE_RE },
				{ "+34 954556817", true , RegularExpression.TELEPHONE_RE },
				{ "+500 954556817", true , RegularExpression.TELEPHONE_RE },
				{ "+500954556817", false , RegularExpression.TELEPHONE_RE },
				{ "(+34) 954556817", true , RegularExpression.TELEPHONE_RE },
				{ "(+500) 954556817", true , RegularExpression.TELEPHONE_RE },
				{ "(+500)954556817", true, RegularExpression.TELEPHONE_RE },
				{ "154556817", true , RegularExpression.TELEPHONE_RE },
				{ "254556817", true , RegularExpression.TELEPHONE_RE },
				{ "12254556817", true , RegularExpression.TELEPHONE_RE },
				{ "12345678", false , RegularExpression.TELEPHONE_RE },
				{ "123456789012", true , RegularExpression.TELEPHONE_RE },
				{ "1234567890123", false , RegularExpression.TELEPHONE_RE },
				{ "1234567_9", false , RegularExpression.TELEPHONE_RE },
				
				{ "@gmail.com", false, RegularExpression.EMAIL_RE },
				{ "@.com", false, RegularExpression.EMAIL_RE },
				{ "@a.com", false, RegularExpression.EMAIL_RE },
				{ "@do.com", false, RegularExpression.EMAIL_RE },
				{ "@gmail2.com", false, RegularExpression.EMAIL_RE },
				{ ".com2", false, RegularExpression.EMAIL_RE },
				{ "david.lopez.castellote@gmail.com", true, RegularExpression.EMAIL_RE },
				{ "david_lopez_castellote@gmail.com", true, RegularExpression.EMAIL_RE },
				{ "david-lopez-castellote@gmail.com", true, RegularExpression.EMAIL_RE },
				{ "david?lopez?castellote@gmail.com", false, RegularExpression.EMAIL_RE },
				{ "david,lopez,castellote@gmail.com", false, RegularExpression.EMAIL_RE },
				{ "d@gmail.com", false, RegularExpression.EMAIL_RE },
				{ "d.d@gmail.com", true, RegularExpression.EMAIL_RE },
				{ "david@gmail.com", true, RegularExpression.EMAIL_RE },
				
				{ "20488603L", true, RegularExpression.NIF_RE },
				{ "2048860L", false, RegularExpression.NIF_RE },
				{ "204886031", false, RegularExpression.NIF_RE },
				{ "204886031L", false, RegularExpression.NIF_RE },
				{ "2048860LL", false, RegularExpression.NIF_RE },
				{ "X04886031", true, RegularExpression.NIF_RE },
				{ "X048860312", false, RegularExpression.NIF_RE },
				{ "X0488603", false, RegularExpression.NIF_RE },
				{ "X0488601L", true, RegularExpression.NIF_RE }

		});
	}
	
	@Test
	public void test() {
		assertEquals( this.expectedResult, RegularExpression.matches( this.str, this.regularExpression ) );
	}
	

}
