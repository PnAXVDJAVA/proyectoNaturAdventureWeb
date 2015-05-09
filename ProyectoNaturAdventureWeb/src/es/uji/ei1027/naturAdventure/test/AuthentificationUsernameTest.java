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
public class AuthentificationUsernameTest {

	private HttpSession session;
	private int userRole;
	private String userUsername;
	private int securityLevel;
	private String username;
	private boolean expectedResult;
	
	public AuthentificationUsernameTest( int userRole, String userUsername, int securityLevel, String username,  boolean expectedResult ) {
		this.userRole = userRole;
		this.userUsername = userUsername;
		this.securityLevel = securityLevel;
		this.username = username;
		this.expectedResult = expectedResult;
	}
	
	@Parameters
	public static Collection<Object[]> datos() {
		return Arrays.asList( new Object[][] {
			//No afecta el username
			{ -1, "david", Roles.ADMIN.getLevel(), "david", false },
			{ -1, "david", Roles.INSTRUCTOR.getLevel(), "david", false },
			{ -1, "david", Roles.CUSTOMER.getLevel(), "david", false },
			{ Roles.CUSTOMER.getLevel(), "david", Roles.ADMIN.getLevel(), "david", false },
			{ Roles.CUSTOMER.getLevel(), "david", Roles.INSTRUCTOR.getLevel(), "david", false },
			{ Roles.CUSTOMER.getLevel(), "david", Roles.CUSTOMER.getLevel(), "david", true },
			{ Roles.INSTRUCTOR.getLevel(), "david", Roles.ADMIN.getLevel(), "david", false },
			{ Roles.INSTRUCTOR.getLevel(), "david", Roles.INSTRUCTOR.getLevel(), "david", true },
			{ Roles.INSTRUCTOR.getLevel(), "david", Roles.CUSTOMER.getLevel(), "david", false },
			{ Roles.ADMIN.getLevel(), "david", Roles.ADMIN.getLevel(), "david", true },
			{ Roles.ADMIN.getLevel(), "david", Roles.INSTRUCTOR.getLevel(), "david", true },
			{ Roles.ADMIN.getLevel(), "david", Roles.CUSTOMER.getLevel(), "david", true },
			//Afecta el username
			{ Roles.CUSTOMER.getLevel(), null, Roles.CUSTOMER.getLevel(), "david", false },
			{ Roles.INSTRUCTOR.getLevel(), null, Roles.INSTRUCTOR.getLevel(), "david", false },
			{ Roles.ADMIN.getLevel(), null, Roles.ADMIN.getLevel(), "david", false },
			{ Roles.CUSTOMER.getLevel(), "xavi", Roles.ADMIN.getLevel(), "david", false },
			{ Roles.CUSTOMER.getLevel(), "valeriu", Roles.CUSTOMER.getLevel(), "david", false },
			{ Roles.INSTRUCTOR.getLevel(), "java", Roles.INSTRUCTOR.getLevel(), "david", false },
			{ Roles.ADMIN.getLevel(), "distinto", Roles.ADMIN.getLevel(), "david", true },
		});
	}
	
	@Before
	public void setSession() {
		this.session = new MyHttpSessionAdapterStub();
		UserDetails user = new UserDetails();
		user.setRole( this.userRole );
		user.setUsername( this.userUsername );
		if( this.userRole == -1 || this.userUsername == null ) {
			user = null;
		}
		this.session.setAttribute( "user" , user );
	}

	@Test
	public void test() {
		assertEquals( this.expectedResult, Authentification.checkAuthentificationByUsername( this.session, this.securityLevel, this.username ) );
	}

}
