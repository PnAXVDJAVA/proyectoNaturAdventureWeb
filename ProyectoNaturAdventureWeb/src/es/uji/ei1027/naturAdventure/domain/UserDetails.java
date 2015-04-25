package es.uji.ei1027.naturAdventure.domain;

public class UserDetails {
	
	private String username;
	private String password;
	private int role;
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername( String username ) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	

}
