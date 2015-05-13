package es.uji.ei1027.naturAdventure.service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Booking;
import es.uji.ei1027.naturAdventure.domain.Profile;

public class EmailSender {
	
	public static void sendEmail( EmailType emailType, Profile profile, Booking booking, Activity activity ) {
		String messageBody = "";
		String messageSubject = "";
		switch( emailType ) {
			case book:
				messageBody = getBookMessageBody( profile, booking, activity );
				messageSubject = getBookMessageSubject( activity.getName() );
				break;
			case deny:
				messageBody = getDenyMessageBody( profile, booking, activity );
				messageSubject = getDenyMessageSubject( activity.getName() );
				break;
			case accept:
				messageBody = getAcceptMessageBody( profile, booking, activity );
				messageSubject = getAcceptMessageSubject( activity.getName());
				break;
		}
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
			msg.setText( messageBody );
			
			Transport.send( msg );
		}
		catch( MessagingException e ) {
			e.printStackTrace();
		}
	}
	
	private static String getBookMessageBody( Profile profile, Booking booking, Activity activity) {
		String nombre = profile.getName();
		String fecha = booking.getProposalPerformingDateString();
		int numParticipantes = booking.getNumPartakers();
		String hora = booking.getStartHour().toString();
		String nombreActividad = activity.getName();
		
		String msgBody = "Hola " + nombre + ":\n"
				+ "Te confirmamos que acabas de realizar una reserva\n"
				+ "de una actividad en NaturAdventure.\n"
				+ "Los datos de la reserva son:\n"
				+ "\t-Nombre de la actividad: " + nombreActividad + "\n"
				+ "\t-Fecha de realización propuesta: " + fecha + "\n"
				+ "\t-Hora de realización propuesta: " + hora + "\n"
				+ "\t-Número de participantes: " + numParticipantes + "\n"
				+ "Pronto se te enviará un email de confirmación o rechazo de la reserva.\n"
				+ "Un saludo.\n"
				+ "---------------------------------------\n"
				+ "El equipo de NaturAdventure." ;
		return msgBody;
	}
	
	private static String getDenyMessageBody( Profile profile, Booking booking, Activity activity ) {
		String nombre = profile.getName();
		String fecha = booking.getProposalPerformingDateString();
		int numParticipantes = booking.getNumPartakers();
		String hora = booking.getStartHour().toString();
		String nombreActividad = activity.getName();
		
		String msgBody = "Hola " + nombre + ":\n"
				+ "Sintiéndolo mucho nos hemos visto obligados a rechazar "
				+ "tu propuesta de reserva por distintos motivos con los siguientes datos:\n"
				+ "\t-Nombre de la actividad: " + nombreActividad + "\n"
				+ "\t-Fecha de realización propuesta: " + fecha + "\n"
				+ "\t-Hora de realización propuesta: " + hora + "\n"
				+ "\t-Número de participantes: " + numParticipantes + "\n"
				+ "Te pedimos disculpas por las molestias y te invitamos a realizar la reserva "
				+ "para un día distinto.\n"
				+ "Un saludo.\n"
				+ "---------------------------------------\n"
				+ "El equipo de NaturAdventure." ;
		return msgBody;
	}
	
	private static String getAcceptMessageBody( Profile profile, Booking booking, Activity activity ) {
		String nombre = profile.getName();
		String fecha = booking.getProposalPerformingDateString();
		int numParticipantes = booking.getNumPartakers();
		String hora = booking.getStartHour().toString();
		String nombreActividad = activity.getName();
		
		String msgBody = "Hola " + nombre + ":\n"
				+ "Acabamos de confirmar la reserva que previamente realizaste con los siguientes datos:\n"
				+ "\t-Nombre de la actividad: " + nombreActividad + "\n"
				+ "\t-Fecha de realización propuesta: " + fecha + "\n"
				+ "\t-Hora de realización propuesta: " + hora + "\n"
				+ "\t-Número de participantes: " + numParticipantes + "\n"
				+ "Gracias por confiar en nosotros."
				+ "Un saludo.\n"
				+ "---------------------------------------\n"
				+ "El equipo de NaturAdventure." ;
		return msgBody;
	}
	
	private static String getBookMessageSubject( String activityName ) {
		return "Reserva de actividad " + activityName + "en NaturAdventure";
	}
	
	private static String getDenyMessageSubject( String activityName ) {
		return "Rechazo de reserva de actividad " + activityName + " en NaturAdventure";
	}
	
	private static String getAcceptMessageSubject( String activityName ) {
		return "Confirmación de reserva de actividad " + activityName + " en NaturAdventure";
	}

}
