package es.uji.ei1027.naturAdventure.service;

public class RegularExpression {
	
	public final static String TELEPHONE_RE = "(^(\\()?\\+\\d{2,3}(\\))?( ))?\\d{9,12}$";
	public final static String EMAIL_RE = "^[a-zA-Z0-9._-]{2,}\\@[a-zA-Z]{2,}\\.[a-zA-Z]{2,3}$";
	
	public static boolean matches( String str, String re ) {
		return str.matches( re );
	}
	
}
