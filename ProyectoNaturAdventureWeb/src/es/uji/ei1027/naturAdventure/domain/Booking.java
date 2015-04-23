package es.uji.ei1027.naturAdventure.domain;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Booking {
	private int codBooking;
	private Date proposalPerformingDate;
	private String proposalPerformingDateString;
	private int numPartakers;
	private Date bookingDate;
	private String bookingDateString;
	private StartHour startHour;
	private BookingStatus status;
	private int codActivity;
	private String customerNif;
	private List<Instructor> assignedInstructors;
	
	public Booking() {
		this.codBooking = -1;
		this.proposalPerformingDate = null;
		this.numPartakers = -1;
		this.bookingDate = null;
		this.customerNif = null;
		this.startHour = null;
		this.codActivity = -1;
		this.status = null;
		this.assignedInstructors = null;
		this.proposalPerformingDateString = null;
		this.bookingDateString = null;
	}

	public int getCodBooking() {
		return codBooking;
	}

	public void setCodBooking(int codBooking) {
		this.codBooking = codBooking;
	}

	public Date getProposalPerformingDate() {
		return proposalPerformingDate;
	}

	public void setProposalPerformingDate(Date proposalPerformingDate) {
		this.proposalPerformingDate = proposalPerformingDate;
		this.proposalPerformingDateString = proposalPerformingDateString.toString();
	}
	
	public String getProposalPerformingDateString() {
		return this.proposalPerformingDateString;
	}
	
	public void setProposalPerformingDateString( String date ) {
		this.proposalPerformingDateString = date;
		this.proposalPerformingDate = Date.valueOf( date );
	}

	public int getNumPartakers() {
		return numPartakers;
	}

	public void setNumPartakers(int numPartakers) {
		this.numPartakers = numPartakers;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
		this.bookingDateString = bookingDate.toString();
	}
	
	public String getBookingDateString() {
		return this.bookingDateString;
	}
	
	public void setBookingDateString( String bookingDate ) {
		this.bookingDateString = bookingDate;
		this.bookingDate = Date.valueOf( bookingDate );
	}

	public String getCustomerNif() {
		return customerNif;
	}

	public void setCustomerNif(String customerNif) {
		this.customerNif = customerNif;
	}

	public StartHour getStartHour() {
		return startHour;
	}

	public void setStartHour(StartHour startHour) {
		this.startHour = startHour;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public int getCodActivity() {
		return codActivity;
	}

	public void setCodActivity(int codActivity) {
		this.codActivity = codActivity;
	}
	
	public List<Instructor> getAssignedInstructors() {	
		return this.assignedInstructors;
	}
	
	public void setAssignedInstructors( List<Instructor> list ) {
		this.assignedInstructors = list;
	}
	
	public void assignInstructor( Instructor instructor ) {
		if( this.assignedInstructors == null ) {
			this.assignedInstructors = new LinkedList<>();
		}
		this.assignedInstructors.add( instructor );
	}
	
	@Override
	public String toString() {
		return "CodBooking: " + codBooking + " || CodActivity: " + codActivity + " || Date: " + bookingDate.toString()
				+ " || Nif: " + customerNif + "\nProposalDate: " + proposalPerformingDate.toString() + " || StartHour: " 
				+ startHour + " || Status: " +status;
	}
}
