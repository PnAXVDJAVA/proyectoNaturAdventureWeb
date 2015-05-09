package es.uji.ei1027.naturAdventure.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import es.uji.ei1027.naturAdventure.domain.MyHttpSessionAdapterStub;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;

@RunWith( Parameterized.class )
public class AuthentificationTest {

	private HttpSession session;
	private int userRole;
	private int securityLevel;
	private boolean expectedResult;
	
	public AuthentificationTest( int userRole, int securityLevel, boolean expectedResult ) {
		this.userRole = userRole;
		this.securityLevel = securityLevel;
		this.expectedResult = expectedResult;
	}
	
	@Parameters
	public static Collection<Object[]> datos() {
		return Arrays.asList( new Object[][] { 
			{ -1, Roles.ADMIN.getLevel(), false },
			{ -1, Roles.INSTRUCTOR.getLevel(), false },
			{ -1, Roles.CUSTOMER.getLevel(), false },
			{ Roles.CUSTOMER.getLevel(), Roles.ADMIN.getLevel(), false },
			{ Roles.CUSTOMER.getLevel(), Roles.INSTRUCTOR.getLevel(), false },
			{ Roles.CUSTOMER.getLevel(), Roles.CUSTOMER.getLevel(), true },
			{ Roles.INSTRUCTOR.getLevel(), Roles.ADMIN.getLevel(), false },
			{ Roles.INSTRUCTOR.getLevel(), Roles.INSTRUCTOR.getLevel(), true },
			{ Roles.INSTRUCTOR.getLevel(), Roles.CUSTOMER.getLevel(), false },
			{ Roles.ADMIN.getLevel(), Roles.ADMIN.getLevel(), true },
			{ Roles.ADMIN.getLevel(), Roles.INSTRUCTOR.getLevel(), true },
			{ Roles.ADMIN.getLevel(), Roles.CUSTOMER.getLevel(), true },
		});
	}
	
	@Before
	public void setSession() {
		this.session = new MyHttpSessionAdapterStub();
		UserDetails user = new UserDetails();
		user.setRole( this.userRole );
		if( this.userRole == -1 ) {
			user = null;
		}
		this.session.setAttribute( "user" , user );
	}

	@Test
	public void test() {
		assertEquals( this.expectedResult, Authentification.checkAuthentification( this.session, this.securityLevel ) );
	}

}
