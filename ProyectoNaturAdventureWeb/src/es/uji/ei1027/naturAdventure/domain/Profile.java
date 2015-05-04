package es.uji.ei1027.naturAdventure.domain;

public interface Profile {
	
	public void setNif( String nif );
	public String getNif();
	
	public void setName( String name );
	public String getName();
	
	public void setFirstSurname( String firstSurname );
	public String getFirstSurname();
	
	public void setSecondSurname( String secondSurname );
	public String getSecondSurname();
	
	public void setEmail( String email );
	public String getEmail();
	
	public void setTelephone( int telephone );
	public int getTelephone();
	
	public void setUsername( String username );
	public String getUsername();

}
