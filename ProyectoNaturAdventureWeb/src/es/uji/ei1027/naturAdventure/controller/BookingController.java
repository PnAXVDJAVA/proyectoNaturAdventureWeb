package es.uji.ei1027.naturAdventure.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.icu.text.SimpleDateFormat;

import es.uji.ei1027.naturAdventure.dao.ActivityDao;
import es.uji.ei1027.naturAdventure.dao.BookingDao;
import es.uji.ei1027.naturAdventure.dao.CustomerDao;
import es.uji.ei1027.naturAdventure.dao.InstructorDao;
import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Booking;
import es.uji.ei1027.naturAdventure.domain.BookingStatus;
import es.uji.ei1027.naturAdventure.domain.Customer;
import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.domain.Profile;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.StartHour;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;
import es.uji.ei1027.naturAdventure.service.DateService;
import es.uji.ei1027.naturAdventure.service.EmailSender;
import es.uji.ei1027.naturAdventure.service.EmailType;
import es.uji.ei1027.naturAdventure.service.ListsDifference;
import es.uji.ei1027.naturAdventure.validator.BookingValidator;

@Controller
@RequestMapping("/booking")
public class BookingController {

	private BookingDao bookingDao;
	private ActivityDao activityDao;
	private CustomerDao customerDao;
	private InstructorDao instructorDao;
	private BookingValidator bookingValidator;
	
	@Autowired
	public void setBookingDao( BookingDao bookingDao ) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setActivityDao( ActivityDao activityDao ) {
		this.activityDao = activityDao;
	}
	
	@Autowired
	public void setCustomerDao( CustomerDao customerDao ) {
		this.customerDao = customerDao;
	}
	
	@Autowired
	public void setInstructorDao( InstructorDao instructorDao  ) {
		this.instructorDao = instructorDao;
	}
	
	@Autowired
	public void setBookingValidator( BookingValidator bookingValidator ) {
		this.bookingValidator = bookingValidator;
	}
	
	@RequestMapping("/list")
	public String listBookings( Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/list.html" );
			return "login";
		}
		model.addAttribute( "bookings", bookingDao.getBookings() );
		return "booking/list";
	}
	
	@RequestMapping("/listSearch.html")
	public String listSearch( Model model, HttpSession session, @RequestParam("criterioBusqueda") String criterio, 
								@RequestParam("valor") String valor ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "booking/list.html" );
			return "login";
		}
		
		List<Booking> bookings = this.bookingDao.getBookings();
		DateService dateService = null;
		String year = null;
		String month = null;
		Date valorDate = null;
		Date date = null;
		
		switch( criterio ) {
			case "todas":
				model.addAttribute( "bookings", bookings );
				break;
			case "anyo":
				List<Booking> bookingsByYear = new LinkedList<>();
				for( Booking booking: bookings ) {
					dateService = new DateService( booking.getProposalPerformingDate() );
					year = "" + dateService.getYear();
					if( year.equals( valor ) ) {
						bookingsByYear.add( booking );
					}
				}
				model.addAttribute( "bookings", bookingsByYear );
				break;
			case "mes":
				List<Booking> bookingsByMonth = new LinkedList<>();
				for( Booking booking: bookings ) {
					dateService = new DateService( booking.getProposalPerformingDate() );
					month = "" + dateService.getMonth();
					if( month.equals( valor ) ) {
						bookingsByMonth.add( booking );
					}
				}
				model.addAttribute( "bookings", bookingsByMonth );
				break;
			case "dia":
				List<Booking> bookingsDay = new LinkedList<>();
				SimpleDateFormat format = new SimpleDateFormat( "d/M/yyyy" );
				try {
					valorDate = new Date( format.parse( valor ).getTime() );
				}
				catch( ParseException e ) {}
				
				for( Booking booking: bookings ) {
					date = booking.getProposalPerformingDate();
					if( date.equals( valorDate ) ) {
						bookingsDay.add( booking );
					}
				}
				model.addAttribute( "bookings", bookingsDay );
				break;
		}
		
		model.addAttribute( "criterioBusqueda", criterio );
		model.addAttribute( "valor", valor );
		
		return "booking/list";
	}
	
	@RequestMapping("/customerBookingList/{nif}")
	public String customerListBookings( Model model, HttpSession session, @PathVariable String nif ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.CUSTOMER.getLevel(), nif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/customerBookingList/" + nif + ".html" );
			return "login";
		}
		model.addAttribute( "customerBookings", bookingDao.getCustomerBookings( nif ) );
		return "booking/customerBookingList";
	}
	
	@RequestMapping("/customerBookingListSearch.html")
	public String customerBookingListSearch( Model model, HttpSession session, @RequestParam("nif") String nif, 
												@RequestParam("criterioBusqueda") String criterio, @RequestParam("valor") String valor ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.CUSTOMER.getLevel(), nif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "booking/instructorBookingList/" + nif + ".html" );
			return "login";
		}
		
		List<Booking> customerBookings = this.bookingDao.getCustomerBookings( nif );
		DateService dateService = null;
		String year = null;
		String month = null;
		Date valorDate = null;
		Date date = null;
		
		switch( criterio ) {
			case "todas":
				model.addAttribute( "customerBookings", customerBookings );
				break;
			case "anyo":
				List<Booking> customerBookingsByYear = new LinkedList<>();
				for( Booking booking: customerBookings ) {
					dateService = new DateService( booking.getProposalPerformingDate() );
					year = "" + dateService.getYear();
					if( year.equals( valor ) ) {
						customerBookingsByYear.add( booking );
					}
				}
				model.addAttribute( "customerBookings", customerBookingsByYear );
				break;
			case "mes":
				List<Booking> customerBookingsByMonth = new LinkedList<>();
				for( Booking booking: customerBookings ) {
					dateService = new DateService( booking.getProposalPerformingDate() );
					month = "" + dateService.getMonth();
					if( month.equals( valor ) ) {
						customerBookingsByMonth.add( booking );
					}
				}
				model.addAttribute( "customerBookings", customerBookingsByMonth );
				break;
			case "dia":
				List<Booking> customerBookingsDay = new LinkedList<>();
				SimpleDateFormat format = new SimpleDateFormat( "d/M/yyyy" );
				try {
					valorDate = new Date( format.parse( valor ).getTime() );
				}
				catch( ParseException e ) {}
				
				for( Booking booking: customerBookings ) {
					date = booking.getProposalPerformingDate();
					if( date.equals( valorDate ) ) {
						customerBookingsDay.add( booking );
					}
				}
				model.addAttribute( "customerBookings", customerBookingsDay );
				break;
		}
		
		model.addAttribute( "criterioBusqueda", criterio );
		model.addAttribute( "valor", valor );
		
		return "booking/customerBookingList";
	}
	
	@RequestMapping("/book/{codActivity}")
	public String addBooking( Model model, @PathVariable int codActivity, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/book/" + codActivity + ".html" );
			return "login";
		}
		
		session.setAttribute( "contadorReserva" , 1 );
		
		Booking booking = new Booking();
		model.addAttribute( "booking", booking );
		model.addAttribute( "activity", this.activityDao.getActivity( codActivity ) );
		model.addAttribute( "hours", StartHour.getStringValues() );
		return "booking/book";
	}
	
	@RequestMapping(value="/confirmBooking/{codActivity}")
	public String confirmBooking( Model model, @PathVariable int codActivity, @ModelAttribute("booking") Booking booking, 
			BindingResult bindingResult, HttpSession session ) {
		
		if( !Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/confirmBooking/" + codActivity + ".html" );
			return "login";
		}
		
		booking.setCodActivity( codActivity );
		booking.setBookingDate( DateService.getTodaysDate() );
		model.addAttribute( "activity", this.activityDao.getActivity( codActivity ) );
		
		Activity activity = this.activityDao.getActivity( codActivity );
		double precio = booking.getNumPartakers() * activity.getPricePerPerson() ;
		model.addAttribute( "precio", precio );
		
		this.bookingValidator.validate( booking ,  bindingResult );
		
		if( bindingResult.hasErrors() ) {
			return "booking/book";
		}
		
		int contador = (int) session.getAttribute( "contadorReserva" );

		if( contador > 1 ) {
			contador = 1;
		}
		else if( contador != 1 ) {
			return "redirect:../../index.jsp";
		}
		contador++;
		session.setAttribute( "contadorReserva" , contador );
		
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			List<Customer> customerList = this.customerDao.getCustomers();		
			model.addAttribute( "customerList", customerList );
			return "booking/chooseCustomer";
		}
		else {
			return "booking/confirmBooking";			
		}
	}
	
	@RequestMapping(value="/acceptBooking/{codActivity}")
	public String acceptBooking( Model model, @PathVariable int codActivity, @ModelAttribute("booking") Booking booking, 
									BindingResult bindingResult, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/book/" + codActivity + ".html" );
			return "login";
		}
		
		int contador = (int) session.getAttribute( "contadorReserva" );
		if( contador != 2 ) {
			return "redirect:../../index.jsp";
		}
		contador++;
		session.setAttribute( "contadorReserva" , contador );
				
		booking.setCodActivity( codActivity );
		
		Map<String, Object> objetos = new HashMap<String, Object>();

		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			Customer customer = this.customerDao.getCustomer( booking.getCustomerNif() );
			objetos.put( "profile" , customer );
		}
		else {
			Profile profile = ( Profile ) session.getAttribute( "profile" );
			String customerNif = profile.getNif();
			booking.setCustomerNif( customerNif );
			objetos.put( "profile" , profile );
		}
		
		booking.setStatus( BookingStatus.pending );
		bookingDao.addBooking( booking );
		Activity activity = activityDao.getActivity( codActivity );
		objetos.put( "booking", booking );
		objetos.put( "activity" , activity );
		EmailSender.sendEmail( EmailType.book,  objetos );
		return "redirect:../bookingAccepted.html";
	}
	
	@RequestMapping("/bookingAccepted")
	public String bookingAccepted( Model model, HttpSession session ) {
				
		if( !Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			return "login";
		}
		
		int contador = (int) session.getAttribute( "contadorReserva" );
		if( contador != 3 ) {
			return "redirect:../index.jsp";
		}
		contador = 0;
		session.setAttribute( "contadorReserva" , contador );
		
		return "booking/bookingAccepted";
	}
	
	@RequestMapping(value="/delete/{codBooking}")
	public String processDeleteSubmit( Model model, @PathVariable int codBooking, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/list.html" );
			return "login";
		}
		Booking booking = bookingDao.getBooking( codBooking );
		bookingDao.deleteBooking( booking );
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/update/{codBooking}")
	public String updateBooking( Model model, @PathVariable int codBooking, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/update/" + codBooking + ".html" );
			return "login";
		}
		Booking booking = bookingDao.getBooking( codBooking );
		model.addAttribute( "booking", booking );
		model.addAttribute( "hours", StartHour.getStringValues() );
		List<Activity> activityList = activityDao.getActivities();
		model.addAttribute( "activityList", activityList );
		model.addAttribute( "activityName", booking.getActivityName() );
		return "booking/update";
	}
	
	@RequestMapping(value="/update/{codBooking}", method=RequestMethod.POST)
	public String processUpdateSubmit( @PathVariable int codBooking, HttpSession session, Model model, 
										@ModelAttribute("booking") Booking booking, BindingResult bindingResult ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/update/" + codBooking + ".html" );
			return "login";
		}
		
		Booking newBooking = this.bookingDao.getBooking( codBooking );
		newBooking.setProposalPerformingDateString( booking.getProposalPerformingDateString() );
		newBooking.setNumPartakers( booking.getNumPartakers() );
		newBooking.setStartHour( booking.getStartHour() );
		newBooking.setCodActivity( booking.getCodActivity() );
		
		List<Activity> activityList = activityDao.getActivities();
		model.addAttribute( "activityList", activityList );
		model.addAttribute( "activityName", newBooking.getActivityName() );
		
		this.bookingValidator.validate( newBooking ,  bindingResult );
				
		if( bindingResult.hasErrors() ) {
			return "booking/update";
		}
		
		bookingDao.updateBooking( newBooking );
		return "redirect:../list.html";
	}
	
	@RequestMapping("/deny/{codBooking}")
	public String denyBooking( @PathVariable int codBooking, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/bookingDetails/" + codBooking + ".html" );
			return "login";
		}
		Booking booking = bookingDao.getBooking( codBooking );
		booking.setStatus( BookingStatus.denied );
		bookingDao.updateBooking( booking );
		Activity activity = activityDao.getActivity( booking.getCodActivity() );
		Profile profile = customerDao.getCustomer( booking.getCustomerNif() );
		Map<String, Object> objetos = new HashMap<String, Object>();
		objetos.put( "profile", profile );
		objetos.put( "booking", booking );
		objetos.put( "activity", activity );
		EmailSender.sendEmail( EmailType.deny, objetos );
		model.addAttribute( "booking", bookingDao.getBooking( codBooking ) );
		return "booking/bookingDetails";
	}
	
	/*@RequestMapping("/accept/{codBooking}")
	public String acceptBooking( @PathVariable int codBooking, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/accept/" + codBooking + ".html" );
			return "login";
		}
		refreshAssignInstructorModel( model, codBooking );
		return "booking/accept";
	}*/
	
	@RequestMapping("/assignInstructor.html")
	public String instructorAssigned( @RequestParam("nif") String nif, @RequestParam("codBooking") int codBooking,
										Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/accept/" + codBooking + ".html" );
			return "login";
		}
		bookingDao.assignInstructor( codBooking, nif );
		Booking booking = this.bookingDao.getBooking( codBooking );
		refreshAssignInstructorModel( model, codBooking, booking.getCodActivity() );
		return "redirect:bookingDetails/" + codBooking + ".html";
	}
	
	@RequestMapping("/removeInstructor.html")
	public String instructorRemoved( @RequestParam("nif") String nif, @RequestParam("codBooking") int codBooking,
										Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/accept/" + codBooking + ".html" );
			return "login";
		}
		bookingDao.removeAssignedInstructor( codBooking, nif );
		Booking booking = this.bookingDao.getBooking( codBooking );
		refreshAssignInstructorModel( model, codBooking, booking.getCodActivity() );
		return "redirect:bookingDetails/" + codBooking + ".html";
	}
	
	@RequestMapping("/accept/{codBooking}")
	public String confirmBooking( @PathVariable int codBooking,  HttpSession session, Model model ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/bookingDetails/" + codBooking + ".html" );
			return "login";
		}
		Booking booking = bookingDao.getBooking( codBooking );
		
		List<Instructor> assignedInstructors = this.instructorDao.getAssignedInstructors( codBooking );
		
		if( assignedInstructors.size() == 0 ) {
			model.addAttribute( "booking", bookingDao.getBooking( codBooking ) );
			refreshAssignInstructorModel( model, codBooking, booking.getCodActivity() );
			model.addAttribute( "acceptResult", false );
			model.addAttribute( "codBooking", codBooking );
		}
		else {
			booking.setStatus( BookingStatus.accepted );
			bookingDao.updateBooking( booking );
			Activity activity = activityDao.getActivity( booking.getCodActivity() );
			Profile profile = customerDao.getCustomer( booking.getCustomerNif() );
			Map<String, Object> objetos = new HashMap<String, Object>();
			objetos.put( "profile" , profile );
			objetos.put( "booking", booking );
			objetos.put( "activity" , activity );
			String [] instructorEmails = new String[ assignedInstructors.size() ];
			int i = 0;
			for(  Instructor instructor: assignedInstructors ) {
				instructorEmails[i] = instructor.getEmail();
				i++;
			}
			objetos.put( "instructorEmails" , instructorEmails );
			EmailSender.sendEmail( EmailType.accept, objetos );
			model.addAttribute( "acceptResult", true );
		}
		return "booking/acceptResult";
	}	
	
	@RequestMapping("/bookingDetails/{codBooking}")
	public String showBookingDetails( @PathVariable int codBooking, HttpSession session, Model model ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "booking/bookingDetails/" + codBooking + ".html" );
			return "login";
		}
		model.addAttribute( "booking", bookingDao.getBooking( codBooking ) );
		Booking booking = this.bookingDao.getBooking( codBooking );
		refreshAssignInstructorModel( model, codBooking, booking.getCodActivity() );
		return "booking/bookingDetails";
	}
	
	@RequestMapping("/customerBookingDetails.html")
	public String showCustomerBookingDetails( @RequestParam("nif") String nif, @RequestParam("codBooking") int codBooking, 
												Model model, HttpSession session ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.CUSTOMER.getLevel(), nif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "booking/customerBookingDetails.html?nif=" + nif + "&codBooking=" + codBooking );
			return "login";
		}
		Booking booking = this.bookingDao.getBooking( codBooking );
		model.addAttribute( "booking", booking );
		return "booking/customerBookingDetails";
	}
	
	@RequestMapping("/instructorBookingDetails.html")
	public String showInstructorBookingDetails( @RequestParam("instructorNif") String instructorNif, 
												@RequestParam("codBooking") int codBooking, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.INSTRUCTOR.getLevel(), instructorNif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "booking/customerBookingDetails.html?nif=" + instructorNif + "&codBooking=" + codBooking );
			return "login";
		}
		Booking booking = this.bookingDao.getBooking( codBooking );
		model.addAttribute( "booking", booking );
		return "booking/instructorBookingDetails";
	}
	
	@RequestMapping("/instructorBookingList/{nif}")
	public String instructorBookingList( @PathVariable String nif, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.INSTRUCTOR.getLevel(), nif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "booking/instructorBookingList/" + nif + ".html" );
			return "login";
		}
		
		List<Booking> instructorBookings = this.bookingDao.getInstructorBookings( nif );
		model.addAttribute( "instructorBookings", instructorBookings );
		
		return "booking/instructorBookingList";
	}
	
	@RequestMapping("/instructorBookingListSearch.html")
	public String instructorBookingListSearch( Model model, HttpSession session, @RequestParam("nif") String nif, 
												@RequestParam("criterioBusqueda") String criterio, @RequestParam("valor") String valor ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.INSTRUCTOR.getLevel(), nif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "booking/instructorBookingList/" + nif + ".html" );
			return "login";
		}
		
		List<Booking> instructorBookings = this.bookingDao.getInstructorBookings( nif );
		DateService dateService = null;
		String year = null;
		String month = null;
		Date valorDate = null;
		Date date = null;
		
		switch( criterio ) {
			case "todas":
				model.addAttribute( "instructorBookings", instructorBookings );
				break;
			case "anyo":
				List<Booking> instructorBookingsByYear = new LinkedList<>();
				for( Booking booking: instructorBookings ) {
					dateService = new DateService( booking.getProposalPerformingDate() );
					year = "" + dateService.getYear();
					if( year.equals( valor ) ) {
						instructorBookingsByYear.add( booking );
					}
				}
				model.addAttribute( "instructorBookings", instructorBookingsByYear );
				break;
			case "mes":
				List<Booking> instructorBookingsByMonth = new LinkedList<>();
				for( Booking booking: instructorBookings ) {
					dateService = new DateService( booking.getProposalPerformingDate() );
					month = "" + dateService.getMonth();
					if( month.equals( valor ) ) {
						instructorBookingsByMonth.add( booking );
					}
				}
				model.addAttribute( "instructorBookings", instructorBookingsByMonth );
				break;
			case "dia":
				List<Booking> instructorBookingsDay = new LinkedList<>();
				SimpleDateFormat format = new SimpleDateFormat( "d/M/yyyy" );
				try {
					valorDate = new Date( format.parse( valor ).getTime() );
				}
				catch( ParseException e ) {}
				
				for( Booking booking: instructorBookings ) {
					date = booking.getProposalPerformingDate();
					if( date.equals( valorDate ) ) {
						instructorBookingsDay.add( booking );
					}
				}
				model.addAttribute( "instructorBookings", instructorBookingsDay );
				break;
		}
		
		model.addAttribute( "criterioBusqueda", criterio );
		model.addAttribute( "valor", valor );
		
		return "booking/instructorBookingList";
	}
	
	private void refreshAssignInstructorModel( Model model, int codBooking, int codActivity ) {
		model.addAttribute( "codBooking", codBooking );
		List<Instructor> specializedInstructors = instructorDao.getSpecializedInstructors( codActivity );
		List<Instructor> assignedInstructors = instructorDao.getAssignedInstructors( codBooking );
		model.addAttribute( "assignedInstructors", assignedInstructors );
		List<Instructor> difference = ListsDifference.listsDifference( specializedInstructors, assignedInstructors );
		model.addAttribute( "availableInstructors", difference );
	}
}
