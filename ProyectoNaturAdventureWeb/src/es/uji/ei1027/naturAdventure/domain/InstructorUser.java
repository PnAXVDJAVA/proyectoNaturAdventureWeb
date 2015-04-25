package es.uji.ei1027.naturAdventure.domain;

import java.sql.Date;
import java.util.List;

public class InstructorUser {

	//Profile data
	private String nif;
	private String name;
	private String firstSurname;
	private String secondSurname;
	private String address;
	private int telephone;
	private String dateOfBirthString;
	private Date dateOfBirth;
	private String email;
	private String bankAccount;
	private List<Degree> degrees;
	private List<Booking> bookings;
	private List<Activity> activities;
	
	//User data
	private String username;
	private String password;
	
	public InstructorUser() {
		this.nif = null;
		this.name = null;
		this.firstSurname = null;
		this.secondSurname = null;
		this.address = null;
		this.telephone = -1;
		this.dateOfBirth = null;
		this.email = null;
		this.bankAccount = null;
		this.degrees = null;
		this.bookings = null;
		this.activities = null;
		this.dateOfBirthString = null;
		this.username = null;
		this.password = null;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public String getSecondSurname() {
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getDateOfBirthString() {
		return dateOfBirthString;
	}

	public void setDateOfBirthString( String dateOfBirth ) {
		this.dateOfBirthString = dateOfBirth;
		//Cuando se modifique el dateString también tiene que modificarse el sql.date
		String [] dateList = dateOfBirth.split( "/" );
		String formattedString = dateList[2] + "-" + dateList[1] + "-" + dateList[0];
		 this.dateOfBirth = Date.valueOf( formattedString );
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		//Cuando se modifique el sql.date también tiene que modificarse el dateString
		String [] dateList = dateOfBirth.toString().split( "-" );
		String formattedString = dateList[2] + "/" + dateList[1] + "/" + dateList[0];
		this.dateOfBirthString = formattedString;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
