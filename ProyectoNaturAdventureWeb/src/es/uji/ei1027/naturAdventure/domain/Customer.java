package es.uji.ei1027.naturAdventure.domain;

public class Customer implements Profile {

	private String nif;
	private String name;
	private String firstSurname;
	private String secondSurname;
	private String email;
	private long telephone;
	private String username;
	
	@Override
	public String getNif() {
		return nif;
	}
	
	@Override
	public void setNif(String nif) {
		this.nif = nif;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getFirstSurname() {
		return firstSurname;
	}

	@Override
	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	@Override
	public String getSecondSurname() {
		return secondSurname;
	}

	@Override
	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public long getTelephone() {
		return telephone;
	}

	@Override
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	public String toString() {
		return "Nif: " + nif + "\tName Surname Lastname: " + name + " " + firstSurname + " " + secondSurname 
				+ "\nEmail: " + email + "\tTelephone: " + telephone;
	}
}
