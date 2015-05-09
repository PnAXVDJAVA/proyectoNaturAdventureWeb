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
import es.uji.ei1027.naturAdventure.domain.Profile;
import es.uji.ei1027.naturAdventure.domain.ProfileAuthentificationTestStub;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;

@RunWith( Parameterized.class )
public class AuthentificationNifTest {

	private HttpSession session;
	private int userRole;
	private String userNif;
	private int securityLevel;
	private String nif;
	private boolean expectedResult;
	
	public AuthentificationNifTest( int userRole, String userNif, int securityLevel, String nif,  boolean expectedResult ) {
		this.userRole = userRole;
		this.userNif = userNif;
		this.securityLevel = securityLevel;
		this.nif = nif;
		this.expectedResult = expectedResult;
	}
	
	@Parameters
	public static Collection<Object[]> datos() {
		return Arrays.asList( new Object[][] {
			//No afecta el nif
			{ -1, "12345", Roles.ADMIN.getLevel(), "12345", false },
			{ -1, "12345", Roles.INSTRUCTOR.getLevel(), "12345", false },
			{ -1, "12345", Roles.CUSTOMER.getLevel(), "12345", false },
			{ Roles.CUSTOMER.getLevel(), "12345", Roles.ADMIN.getLevel(), "12345", false },
			{ Roles.CUSTOMER.getLevel(), "12345", Roles.INSTRUCTOR.getLevel(), "12345", false },
			{ Roles.CUSTOMER.getLevel(), "12345", Roles.CUSTOMER.getLevel(), "12345", true },
			{ Roles.INSTRUCTOR.getLevel(), "12345", Roles.ADMIN.getLevel(), "12345", false },
			{ Roles.INSTRUCTOR.getLevel(), "12345", Roles.INSTRUCTOR.getLevel(), "12345", true },
			{ Roles.INSTRUCTOR.getLevel(), "12345", Roles.CUSTOMER.getLevel(), "12345", false },
			{ Roles.ADMIN.getLevel(), "12345", Roles.ADMIN.getLevel(), "12345", true },
			{ Roles.ADMIN.getLevel(), "12345", Roles.INSTRUCTOR.getLevel(), "12345", true },
			{ Roles.ADMIN.getLevel(), "12345", Roles.CUSTOMER.getLevel(), "12345", true },
			//Afecta el nif
			{ Roles.CUSTOMER.getLevel(), null, Roles.CUSTOMER.getLevel(), "12345", false },
			{ Roles.INSTRUCTOR.getLevel(), null, Roles.INSTRUCTOR.getLevel(), "12345", false },
			{ Roles.ADMIN.getLevel(), null, Roles.ADMIN.getLevel(), "12345", false },
			{ Roles.CUSTOMER.getLevel(), "12345", Roles.ADMIN.getLevel(), "12345", false },
			{ Roles.CUSTOMER.getLevel(), "12345", Roles.CUSTOMER.getLevel(), "12346", false },
			{ Roles.INSTRUCTOR.getLevel(), "12345", Roles.INSTRUCTOR.getLevel(), "12346", false },
			{ Roles.ADMIN.getLevel(), "12345", Roles.ADMIN.getLevel(), "12346", true },
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
		Profile profile = new ProfileAuthentificationTestStub();
		profile.setNif( this.userNif );
		if( this.userNif == null ) {
			profile = null;
		}
		this.session.setAttribute( "user" , user );
		this.session.setAttribute( "profile" , profile );
	}

	@Test
	public void test() {
		assertEquals( this.expectedResult, Authentification.checkAuthentificationByNif( this.session, this.securityLevel, this.nif ) );
	}

}
