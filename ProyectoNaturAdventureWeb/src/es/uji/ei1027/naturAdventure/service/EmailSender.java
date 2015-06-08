package es.uji.ei1027.naturAdventure.service;


import java.io.ByteArrayOutputStream;
import java.util.Map;
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

import com.sun.xml.internal.ws.util.ByteArrayDataSource;

import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Booking;
import es.uji.ei1027.naturAdventure.domain.Profile;

public class EmailSender {
	private static boolean isAcceptBooking = false;
	
	public static void sendEmail( EmailType emailType, Map<String,Object> objetos ) {
		String messageBody = "";
		String messageSubject = "";
		Activity activity = null;
		Profile profile = null;
		switch( emailType ) {
			case book:
				messageBody = getBookMessageBody( objetos );
				activity = ( Activity ) objetos.get( "activity" );
				messageSubject = getBookMessageSubject( activity.getName() );
				profile = ( Profile ) objetos.get( "profile" );
				isAcceptBooking = false;
				break;
			case deny:
				messageBody = getDenyMessageBody( objetos );
				activity = ( Activity ) objetos.get( "activity" );
				messageSubject = getDenyMessageSubject( activity.getName() );
				profile = ( Profile ) objetos.get( "profile" );
				isAcceptBooking = false;
				break;
			case accept:
				messageBody = getAcceptMessageBody( objetos );
				activity = ( Activity ) objetos.get( "activity" );
				messageSubject = getAcceptMessageSubject( activity.getName());
				String [] emails = ( String [] ) objetos.get( "instructorEmails" );
				String instructorMessageBody = getInstructorMessageBody( objetos );
				String instructorMessageSubject = getInstructorMessageSubject( objetos );
				sendMultipleEmails( instructorMessageBody , instructorMessageSubject, emails );
				isAcceptBooking = true;
				break;
			case pwdRecovery:
				messageBody = getPwdRecoveryBody( objetos );
				messageSubject = getPwdRecoverySubject();
				isAcceptBooking = false;
				break;
		}
		profile = ( Profile ) objetos.get( "profile" );
		sendEmail( messageBody, messageSubject,  profile.getEmail() );
	}
	

	private static void sendEmail( String messageBody, String messageSubject, String email ) {
		
		final String username = "naturadventure.xvd@gmail.com";
		final String password = "xavivaleriudavid";
		
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
		
		try {
			Message msg = new MimeMessage( session );
			msg.setFrom( new InternetAddress( username ) );
			msg.setRecipients( Message.RecipientType.TO , InternetAddress.parse( email ) );
			msg.setSubject( messageSubject );
			
			if( isAcceptBooking ) {
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setText( messageBody );
				
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart( messageBodyPart );
				
				messageBodyPart = new MimeBodyPart();
				
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				try {
					EmailSenderCreatePDF.writePDF( outputStream );
				}
				catch( Exception e ) {
					e.printStackTrace();
				}
				byte[] bytes = outputStream.toByteArray();
				
				javax.activation.DataSource dataSource = new ByteArrayDataSource( bytes , "application/pdf" );
				messageBodyPart.setDataHandler( new DataHandler( dataSource ) );
				messageBodyPart.setFileName( "Booking.pdf" );
				
				multipart.addBodyPart( messageBodyPart );
				
				msg.setContent( multipart );
			}
			else
				msg.setText( messageBody );			
			Transport.send( msg );
		}
		catch( MessagingException e ) {
			e.printStackTrace();
		}
	}
	
	private static void sendMultipleEmails( String messageBody, String messageSubject, String [] emails ) {
		
		final String username = "naturadventure.xvd@gmail.com";
		final String password = "xavivaleriudavid";
		
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
		
		try {
			Message msg = new MimeMessage( session );
			msg.setFrom( new InternetAddress( username ) );
			String addresses = "";
			for( int i = 0; i < emails.length; i++ ) {
				if( i != emails.length - 1 ) {
					addresses += emails[i] + ",";					
				}
				else {
					addresses += emails[i];
				}
			}
			msg.setRecipients( Message.RecipientType.CC , InternetAddress.parse( addresses ) );
			msg.setSubject( messageSubject );
			msg.setText( messageBody );
			
			Transport.send( msg );
		}
		catch( MessagingException e ) {
			e.printStackTrace();
		}
	}
	
	private static String getBookMessageBody( Map<String,Object> objetos ) {
		Profile profile = ( Profile ) objetos.get( "profile" );
		Booking booking = ( Booking ) objetos.get( "booking" );
		Activity activity = ( Activity ) objetos.get( "activity" );
		String nombre = profile.getName();
		String fecha = booking.getProposalPerformingDateString();
		int numParticipantes = booking.getNumPartakers();
		String hora = booking.getStartHour().toString();
		String nombreActividad = activity.getName();
		double totalAPagar = numParticipantes * activity.getPricePerPerson();
		
		String msgBody = "Hola " + nombre + ":\n"
				+ "Le confirmamos que acaba de realizar una reserva\n"
				+ "de una actividad en NaturAdventure.\n"
				+ "Los datos de la reserva son:\n"
				+ "\t-Nombre de la actividad: " + nombreActividad + "\n"
				+ "\t-Fecha de realización propuesta: " + fecha + "\n"
				+ "\t-Hora de realización propuesta: " + hora + "\n"
				+ "\t-Número de participantes: " + numParticipantes + "\n"
				+ "\t-Total a pagar: " + totalAPagar + " €\n"
				+ "Pronto se le enviará un email de confirmación o rechazo de la reserva.\n"
				+ "Un saludo.\n"
				+ "---------------------------------------\n"
				+ "El equipo de NaturAdventure." ;
		return msgBody;
	}
	
	private static String getDenyMessageBody( Map<String,Object> objetos ) {
		Profile profile = ( Profile ) objetos.get( "profile" );
		Booking booking = ( Booking ) objetos.get( "booking" );
		Activity activity = ( Activity ) objetos.get( "activity" );
		String nombre = profile.getName();
		String fecha = booking.getProposalPerformingDateString();
		int numParticipantes = booking.getNumPartakers();
		String hora = booking.getStartHour().toString();
		String nombreActividad = activity.getName();
		
		String msgBody = "Hola " + nombre + ":\n"
				+ "Sintiéndolo mucho nos hemos visto obligados a rechazar "
				+ "su propuesta de reserva. En la fecha que nos ha propuesto, no es posible realizar la actividad.\n\n"
				+ "Los datos introducidos en su reserva son los siguientes:\n"
				+ "\t-Nombre de la actividad: " + nombreActividad + "\n"
				+ "\t-Fecha de realización propuesta: " + fecha + "\n"
				+ "\t-Hora de realización propuesta: " + hora + "\n"
				+ "\t-Número de participantes: " + numParticipantes + "\n"
				+ "\nLe pedimos disculpas por las molestias y le invitamos a realizar la reserva "
				+ "para un día distinto.\n"
				+ "Un saludo.\n"
				+ "---------------------------------------\n"
				+ "El equipo de NaturAdventure." ;
		return msgBody;
	}
	
	private static String getAcceptMessageBody( Map<String,Object> objetos ) {
		Profile profile = ( Profile ) objetos.get( "profile" );
		Booking booking = ( Booking ) objetos.get( "booking" );
		Activity activity = ( Activity ) objetos.get( "activity" );
		String nombre = profile.getName();
		String fecha = booking.getProposalPerformingDateString();
		int numParticipantes = booking.getNumPartakers();
		String hora = booking.getStartHour().toString();
		String nombreActividad = activity.getName();
		
		double totalAPagar = numParticipantes * activity.getPricePerPerson();
		
		String[] info = {booking.getBookingDateString(), nombreActividad, booking.getCodBooking() + "",
						 fecha, hora, numParticipantes + "", totalAPagar + ""};
		
		EmailSenderCreatePDF.setInfo(info);
		
		String msgBody = "Hola " + nombre + ":\n"
				+ "Acabamos de confirmar la reserva que previamente realizó con los siguientes datos:\n"
				+ "\t-Nombre de la actividad: " + nombreActividad + "\n"
				+ "\t-Fecha de realización propuesta: " + fecha + "\n"
				+ "\t-Hora de realización propuesta: " + hora + "\n"
				+ "\t-Número de participantes: " + numParticipantes + "\n"
				+ "\t-Total a pagar: " + totalAPagar + " €\n"
				+ "Gracias por confiar en nosotros."
				+ "Un saludo.\n"
				+ "---------------------------------------\n"
				+ "El equipo de NaturAdventure." ;
		return msgBody;
	}
	
	private static String getPwdRecoveryBody( Map<String,Object> objetos ) {
		Profile profile = ( Profile ) objetos.get( "profile" );
		String newPwd = ( String ) objetos.get( "newPwd" );
		String nombre = profile.getName();
		String username = profile.getUsername();
		
		String msgBody = "Hola " + nombre + ":\n"
				+ "Hemos recibido una solicitud de recuperación de contraseña su cuenta de NaturAdventure.\n"
				+ "En base a ello, hemos cambiado la contraseña de su cuenta. Los nuevos datos de su cuenta son:\n"
				+ "\t-Nombre de usuario: " + username + "\n"
				+ "\t-Nueva contraseña: " + newPwd + "\n"
				+ "Para cualquier duda no dude en contactar con nosotros.\n"
				+ "Un saludo.\n"
				+ "---------------------------------------\n"
				+ "El equipo de NaturAdventure.";
		return msgBody;
	}
	
	private static String getInstructorMessageBody( Map<String, Object> objetos ) {
		Booking booking = ( Booking ) objetos.get( "booking" );
		String fecha = booking.getProposalPerformingDateString();
		
		String msgBody = "Le ha sido asignada la reserva de una actividad para el día " + fecha + ".\n"
				+ "Por favor, consulte la página web para ver la reserva con más detalle.\n"
				+ "Un saludo.\n"
				+ "---------------------------------------\n"
				+ "El equipo de NaturAdventure.";
		
		return msgBody;
	}
	
	private static String getBookMessageSubject( String activityName ) {
		return "Reserva de actividad " + activityName + " en NaturAdventure";
	}
	
	private static String getDenyMessageSubject( String activityName ) {
		return "Rechazo de reserva de actividad " + activityName + " en NaturAdventure";
	}
	
	private static String getAcceptMessageSubject( String activityName ) {
		return "Confirmación de reserva de actividad " + activityName + " en NaturAdventure";
	}
	
	private static String getPwdRecoverySubject() {
		return "Solicitud de recuperación de contraseña en NaturAdventure";
	}
	
	private static String getInstructorMessageSubject(
			Map<String, Object> objetos) {
		Booking booking = ( Booking ) objetos.get( "booking" );
		String fecha = booking.getProposalPerformingDateString();
		return "Reserva asignada para el día " + fecha;
	}
	

}
