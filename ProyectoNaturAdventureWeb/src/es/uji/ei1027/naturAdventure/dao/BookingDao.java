package es.uji.ei1027.naturAdventure.dao;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.internal.ws.util.ByteArrayDataSource;

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
		return this.jdbcTemplate.query( "SELECT * FROM Booking ORDER BY proposalPerformingDate", new BookingMapper() );
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
								booking.getCodActivity(), booking.getStartHour().toString(), booking.getStatus().toString(), booking.getCodBooking() );
	}
	
	public void deleteBooking( Booking booking ) {
		this.jdbcTemplate.update( "DELETE FROM Booking WHERE codBooking = ?", booking.getCodBooking() );
	}
	
	public List<Booking> getCustomerBookings( String nif ) {
		return this.jdbcTemplate.query( "SELECT * FROM Booking WHERE customerNif = ?", new Object[] { nif }, new BookingMapper() );
	}
	
	public void assignInstructor( int codBooking, String instructorNif ) {
		this.jdbcTemplate.update( "INSERT INTO Booking_Assigns ( codBooking, instructorNif ) "
										+ "VALUES ( ?, ? )", codBooking, instructorNif );
	}
	
	public void removeAssignedInstructor( int codBooking, String instructorNif ) {
		this.jdbcTemplate.update( "DELETE FROM Booking_Assigns WHERE codBooking = ? AND instructorNif = ?", codBooking, instructorNif );
	}
	
	public void sendPdf() {
		final String username = "david.lopez.castellote@gmail.com";
		final String password = "1blqu6ma";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getDefaultInstance( props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication( username, password );
			}
		} );
		
		System.out.println( "1" );
		
		String msgBody = "Hola esto es un mensaje de prueba con PDF";
		
		try {
			Message msg = new MimeMessage( session );
			System.out.println( "2" );
			msg.setFrom( new InternetAddress( "nada@nada.com" ) );
			System.out.println( "3" );
			msg.setRecipients( Message.RecipientType.TO , InternetAddress.parse( "david_cs_94@hotmail.com" ) );
			System.out.println( "4" );
			msg.setSubject( "Esto es una prueba 11" );
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText( msgBody );
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart( messageBodyPart );
			
			messageBodyPart = new MimeBodyPart();
			
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			try {
				writePdf( outputStream );				
			}
			catch( Exception e ) {
				e.printStackTrace();
			}
			byte[] bytes = outputStream.toByteArray();
			
			javax.activation.DataSource dataSource = new ByteArrayDataSource( bytes , "application/pdf" );
			messageBodyPart.setDataHandler( new DataHandler( dataSource ) );
			messageBodyPart.setFileName( "test.pdf" );
			
			multipart.addBodyPart( messageBodyPart );
			
			msg.setContent( multipart );
			
			Transport.send( msg );
			System.out.println( "Done" );
		}
		catch( MessagingException e ) {
			e.printStackTrace();
		}
	}
	
	public static void writePdf(OutputStream outputStream) throws Exception {
	    Document document = new Document();
	    PdfWriter.getInstance(document, outputStream);
	     
	    document.open();
	     
	    document.addTitle("Test PDF");
	    document.addSubject("Testing email PDF");
	    document.addKeywords("iText, email");
	    document.addAuthor("David López");
	    document.addCreator("David López");
	     
	    Paragraph paragraph = new Paragraph();
	    paragraph.add(new Chunk("hello!"));
	    document.add(paragraph);
	     
	    document.close();
	}
	
}
