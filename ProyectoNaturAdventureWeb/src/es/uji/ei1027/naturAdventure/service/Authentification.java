package es.uji.ei1027.naturAdventure.service;

import javax.servlet.http.HttpSession;

import es.uji.ei1027.naturAdventure.domain.Profile;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;

public class Authentification {
	
	public static boolean checkAuthentification( HttpSession session, int securityLevel ) {
		UserDetails user = (UserDetails) session.getAttribute( "user" );
		if( user == null || ( user.getRole() != Roles.ADMIN.getLevel() && user.getRole() != securityLevel ) ) {
			return false;
		}
		return true;
	}
	
	public static boolean checkAuthentificationByUsername( HttpSession session, int securityLevel, String username ) {
		UserDetails user = (UserDetails) session.getAttribute( "user" );
		if( user == null || ( user.getRole() != Roles.ADMIN.getLevel() && ( user.getRole() != securityLevel || !user.getUsername().equals( username ) ) ) ) {
			return false;
		}
		return true;
	}
	
	public static boolean checkAuthentificationByNif( HttpSession session, int securityLevel, String nif ) {
		UserDetails user = (UserDetails) session.getAttribute( "user" );
		Profile profile= ( Profile ) session.getAttribute( "profile" );
		if( user == null || ( user.getRole() != Roles.ADMIN.getLevel() && ( profile == null | user.getRole() != securityLevel || !profile.getNif().equals( nif ) ) ) ) {
			return false;
		}
		return true;
	} 
	

}
