package es.uji.ei1027.naturAdventure.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import es.uji.ei1027.naturAdventure.domain.Booking;
import es.uji.ei1027.naturAdventure.domain.BookingStatus;
import es.uji.ei1027.naturAdventure.domain.StartHour;
import es.uji.ei1027.naturAdventure.service.CodeGetter;


@Repository
public class BookingDao {

	private JdbcTemplate jdbcTemplate;
	private CodeGetter codeGetter;
	
	@Autowired
	public void setDataSource( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	@Autowired
	public void setCodeGetter( CodeGetter codeGetter ) {
		this.codeGetter = codeGetter;
	}
	
	private static final class BookingMapper implements RowMapper<Booking> {
		@Override
		public Booking mapRow( ResultSet rs, int rowNum ) throws SQLException {
			Booking booking = new Booking();
			booking.setCodBooking( rs.getInt( "codBooking" ) );
			booking.setProposalPerformingDate( rs.getDate( "proposalPerformingDate" ) );
			booking.setNumPartakers( rs.getInt( "numPartakers" ) );
			booking.setBookingDate( rs.getDate( "bookingDate" ) );
			booking.setCustomerNif( rs.getString( "customerNif" ) );
			booking.setCodActivity( rs.getInt( "codActivity" ) );
			booking.setStartHour( StartHour.getOpcion( rs.getString( "startHour" ) ) );
			booking.setStatus( BookingStatus.getOpcion( rs.getString( "status" ) ) );
			booking.setActivityName( rs.getString( "activityName" ) );
			booking.setCustomerName( rs.getString( "customerName" ) );
			return booking;
		}
	}
	
	public List<Booking> getBookings() {
		return this.jdbcTemplate.query( "SELECT b.*, a.name AS activityName, c.name AS customerName FROM Booking AS b "
				+ "JOIN Activity AS a USING (codActivity) JOIN Customer AS c ON (b.customerNif = c.nif) "
				+ "ORDER BY b.proposalPerformingDate", new BookingMapper() );
	}
	
	public Booking getBooking( int codBooking ) {
		return this.jdbcTemplate.queryForObject( "SELECT b.*, a.name AS activityName, c.name AS customerName FROM Booking AS b "
				+ "JOIN Activity AS a USING (codActivity) JOIN Customer AS c ON (b.customerNif = c.nif) WHERE b.codBooking = ?" ,  
				new Object[] { codBooking }, new BookingMapper() );
	}
	
	public void addBooking( Booking booking ) {
		int nextCodBooking = this.codeGetter.getNextCode( "codBooking" , "Booking" );
		this.jdbcTemplate.update( "INSERT INTO Booking ( codBooking, proposalPerformingDate, numPartakers, bookingDate, customerNif, codActivity, startHour, status ) "
								+ "VALUES ( ?, ?, ?, ?, ?, ?, cast(? as StartHour), cast(? as BookingStatus) )", nextCodBooking, booking.getProposalPerformingDate(), booking.getNumPartakers(),
								booking.getBookingDate(), booking.getCustomerNif(), booking.getCodActivity(), booking.getStartHour().toString(), booking.getStatus().toString() );
	}
	
	public void updateBooking( Booking booking ) {
		this.jdbcTemplate.update( "UPDATE Booking SET proposalPerformingDate = ?, numPartakers = ?, bookingDate = ?, customerNif = ?, codActivity = ?, "
								+ "startHour = cast(? as StartHour), status = cast(? as BookingStatus) WHERE codBooking = ?",
								booking.getProposalPerformingDate(), booking.getNumPartakers(), booking.getBookingDate(), booking.getCustomerNif(), 
								booking.getCodActivity(), booking.getStartHour().toString(), booking.getStatus().toString(), booking.getCodBooking() );
	}
	
	public void deleteBooking( Booking booking ) {
		this.jdbcTemplate.update( "DELETE FROM Booking WHERE codBooking = ?", booking.getCodBooking() );
	}
	
	public List<Booking> getCustomerBookings( String nif ) {
		return this.jdbcTemplate.query( "SELECT b.*, a.name AS activityName, c.name AS customerName FROM Booking AS b "
				+ "JOIN Activity AS a USING (codActivity) JOIN Customer AS c ON (b.customerNif = c.nif) "
				+ "WHERE b.customerNif = ? ORDER BY b.proposalPerformingDate", new Object[] { nif }, new BookingMapper() );
	}
	
	public void assignInstructor( int codBooking, String instructorNif ) {
		this.jdbcTemplate.update( "INSERT INTO Booking_Assigns ( codBooking, instructorNif ) "
										+ "VALUES ( ?, ? )", codBooking, instructorNif );
	}
	
	public void removeAssignedInstructor( int codBooking, String instructorNif ) {
		this.jdbcTemplate.update( "DELETE FROM Booking_Assigns WHERE codBooking = ? AND instructorNif = ?", codBooking, instructorNif );
	}

	public List<Booking> getInstructorBookings( String nif ) {
		return this.jdbcTemplate.query( "SELECT b.*, a.name AS activityName, c.name AS customerName FROM Booking AS b "
				+ "JOIN Booking_Assigns AS ba USING (codBooking) JOIN Activity AS a USING (codActivity) JOIN Customer AS c "
				+ "ON (b.customerNif = c.nif) WHERE ba.instructorNif = ? ORDER BY b.proposalPerformingDate", 
				new Object[] { nif }, new BookingMapper() );
	}
	
}
