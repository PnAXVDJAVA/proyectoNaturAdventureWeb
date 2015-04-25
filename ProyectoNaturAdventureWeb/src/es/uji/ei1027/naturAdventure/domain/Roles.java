package es.uji.ei1027.naturAdventure.domain;

public enum Roles {
	
	CUSTOMER( "Customer", 0 ),
	INSTRUCTOR( "Instructor", 1 ),
	ADMIN( "Admin", 2 );
	
	private final String type;
	private final int level;
	
	private Roles( String type, int level ) {
		this.type = type;
		this.level = level;
	}
	
	public String getType() {
		return this.type;
	}

	public int getLevel() {
		return this.level;
	}
}
