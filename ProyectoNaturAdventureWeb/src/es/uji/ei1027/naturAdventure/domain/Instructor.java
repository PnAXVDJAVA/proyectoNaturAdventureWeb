package es.uji.ei1027.naturAdventure.domain;

import java.sql.Date;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import com.ibm.icu.text.SimpleDateFormat;

public class Instructor implements Profile {
	private String nif;
	private String name;
	private String firstSurname;
	private String secondSurname;
	private String address;
	private long telephone;
	private String dateOfBirthString;
	private Date dateOfBirth;
	private String email;
	private String bankAccount;
	private String userID;
	private List<Degree> degrees;
	private List<Booking> bookings;
	private List<Activity> activities;
	public static final int ROLE = 1;
	
	private Integer dayOfBirth;
	private Integer monthOfBirth;
	private Integer yearOfBirth;
	
	public Instructor() {
		this.dateOfBirth = null;
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

	public long getTelephone() {
		return telephone;
	}

	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	
	/*** ***/
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	public void setDateOfBirth( Date dateOfBirth ) {
		this.dateOfBirth = dateOfBirth;
		java.util.Date utilDate = new java.util.Date( this.dateOfBirth.getTime() );
		this.dateOfBirthString = utilDate.toString();
	}
	
	public String getDateOfBirthString( String dateOfBirth ) {
		return this.dateOfBirthString;
	}
	
	public void setDateOfBirthString( String dateOfBirthString ) {
		this.dateOfBirthString = dateOfBirthString;
		try {
			this.dateOfBirth = new Date( new SimpleDateFormat( "MMM d, yyyy" ).parse( dateOfBirthString ).getTime() );
		} catch (ParseException e) {
			//
		}
	}
	/*** ***/
	
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
	
	@Override
	public void setUsername(String username) {
		this.userID = username;
		
	}

	@Override
	public String getUsername() {
		return this.userID;
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

	public Integer getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Integer dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public Integer getMonthOfBirth() {
		return monthOfBirth;
	}

	public void setMonthOfBirth(Integer monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}

	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
}
