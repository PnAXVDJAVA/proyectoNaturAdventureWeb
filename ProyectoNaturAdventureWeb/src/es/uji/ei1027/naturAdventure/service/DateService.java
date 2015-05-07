package es.uji.ei1027.naturAdventure.service;

import java.sql.Date;
import java.util.Calendar;

public class DateService {
	
	private Date date;
	private Calendar calendar;
	
	public DateService( long date ) {
		this.date = new Date( date );
		this.calendar = Calendar.getInstance();
		this.calendar.setTime( this.date );
	}
	
	public DateService( String date ) {
		this.date = Date.valueOf( date );
		this.calendar = Calendar.getInstance();
		this.calendar.setTime( this.date );
	}
	
	public DateService( Date date ) {
		this.date = date;
		this.calendar = Calendar.getInstance();
		this.calendar.setTime( this.date );
	}
	
	public DateService( int day, int month, int year ) {
		String dateString = year + "-" + month + "-" + day;
		this.date = Date.valueOf( dateString );
		this.calendar = Calendar.getInstance();
		this.calendar.setTime( this.date );
	}
	
	public int getDay() {
		return this.calendar.get( Calendar.DAY_OF_MONTH );
	}
	
	public int getMonth() {
		return this.calendar.get( Calendar.MONTH ) + 1 ;
	}
	
	public int getYear() {
		return this.calendar.get( Calendar.YEAR );
	}
	
	public void setDate( Date date ) {
		this.date = date;
	}
	
	public Date getDate() {
		return this.date;
	}
	

}
