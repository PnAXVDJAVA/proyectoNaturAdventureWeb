package es.uji.ei1027.naturAdventure.domain;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Instructor {
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
	private String userID;
	private List<Degree> degrees;
	private List<Booking> bookings;
	private List<Activity> activities;
	public static final int ROLE = 1;
	
	public Instructor() {
		this.nif = null;
		this.name = null;
		this.firstSurname = null;
		this.secondSurname = null;
		this.address = null;
		this.telephone = -1;
		this.dateOfBirth = null;
		this.email = null;
		this.bankAccount = null;
		this.userID = null;
		this.degrees = null;
		this.bookings = null;
		this.activities = null;
		this.dateOfBirthString = null;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		//Cuando se modifique el sql.date tambi�n tiene que modificarse el dateString
		String [] dateList = dateOfBirth.toString().split( "-" );
		String formattedString = dateList[2] + "/" + dateList[1] + "/" + dateList[0];
		this.dateOfBirthString = formattedString;
	}
	
	public String getDateOfBirthString() {
		return this.dateOfBirthString;
	}
	
	public void setDateOfBirthString( String dateOfBirth ) {
		this.dateOfBirthString = dateOfBirth;
		//Cuando se modifique el dateString tambi�n tiene que modificarse el sql.date
		String [] dateList = dateOfBirth.split( "/" );
		String formattedString = dateList[2] + "-" + dateList[1] + "-" + dateList[0];
		 this.dateOfBirth = Date.valueOf( formattedString );
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}
	
	public void addDegree( Degree degree ) { ////////////////////////////////////////////////////////
		if( degrees == null ) {
			this.degrees = new LinkedList<>();
		}
		this.degrees.add( degree );
	}
	
	public List<Booking> getBookings() {
		return this.bookings;
	}
	
	public void setBookings( List<Booking> list ) {
		this.bookings = list;
	}
	
	public void addBooking( Booking booking ) {
		if( this.bookings == null ) {
			this.bookings = new LinkedList<Booking>();
		}
		this.bookings.add( booking );
	}
	
	public List<Activity> getActivities() {
		return this.activities;
	}
	
	public void setActivities( List<Activity> list ) {
		this.activities = list;
	}
	
	public void addActivity( Activity activity ) {
		if( this.activities == null ) {
			this.activities = new LinkedList<Activity>();
		}
		this.activities.add( activity );
	}
 	
	public String toString() { ////////////////////////////////////////////////////////////////////////
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + " || Surname: " + firstSurname + " " + secondSurname + " || Adress: " + address 
				+ "\nTelephone: " + telephone + " || Email: " + email + " || DateOfBirth: " + dateOfBirth 
				+ "\nBancAccount: " + bankAccount + " || UserID: " + userID
				+ "\nDegrees:\n ");
		for (Degree d: degrees)
			sb.append("\t"+d.toString()+"\n");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		Instructor other = (Instructor) obj;
		return this.nif.equals( other.getNif() );
	}
}
