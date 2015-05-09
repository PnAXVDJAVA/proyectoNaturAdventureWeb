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
			return booking;
		}
	}
	
	public List<Booking> getBookings() {
		return this.jdbcTemplate.query( "SELECT * FROM Booking", new BookingMapper() );
	}
	
	public Booking getBooking( int codBooking ) {
		return this.jdbcTemplate.queryForObject( "SELECT * FROM Booking WHERE codBooking = ?" ,  new Object[] { codBooking }, new BookingMapper() );
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
								booking.getCodActivity(), booking.getStartHour(), booking.getStatus() );
	}
	
	public void deleteBooking( Booking booking ) {
		this.jdbcTemplate.update( "DELETE FROM Booking WHERE codBooking = ?", booking.getCodBooking() );
	}
	
}
