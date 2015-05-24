package es.uji.ei1027.naturAdventure.service;

public enum EmailType {
	
	book,
	deny,
	accept,
	pwdRecovery;
	
	public static EmailType getOpcion( String type ) {
		return valueOf( type );
	}

}
