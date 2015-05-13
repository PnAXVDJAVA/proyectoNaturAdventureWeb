package es.uji.ei1027.naturAdventure.service;

public enum EmailType {
	
	book,
	deny,
	accept;
	
	public static EmailType getOpcion( String type ) {
		return valueOf( type );
	}

}
