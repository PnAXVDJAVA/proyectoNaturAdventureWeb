package es.uji.ei1027.naturAdventure.controller;

import java.util.List;

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

import es.uji.ei1027.naturAdventure.dao.ActivityDao;
import es.uji.ei1027.naturAdventure.dao.BookingDao;
import es.uji.ei1027.naturAdventure.dao.CustomerDao;
import es.uji.ei1027.naturAdventure.dao.InstructorDao;
import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Booking;
import es.uji.ei1027.naturAdventure.domain.BookingStatus;
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
	
	@RequestMapping("/customerBookingList/{nif}")
	public String customerListBookings( Model model, HttpSession session, @PathVariable String nif ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.CUSTOMER.getLevel(), nif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/customerBookingList/" + nif + ".html" );
			return "login";
		}
		model.addAttribute( "bookings", bookingDao.getCustomerBookings( nif ) );
		return "booking/customerBookingList";
	}
	
	@RequestMapping("/book/{codActivity}")
	public String addBooking( Model model, @PathVariable int codActivity, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/book/" + codActivity + ".html" );
			return "login";
		}
		Booking booking = new Booking();
		model.addAttribute( "booking", booking );
		model.addAttribute( "activity", this.activityDao.getActivity( codActivity ) );
		model.addAttribute( "hours", StartHour.getStringValues() );
		return "booking/book";
	}
	
	@RequestMapping(value="/book/{codActivity}", method=RequestMethod.POST)
	public String processAddSubmit( Model model, @PathVariable int codActivity, @ModelAttribute("booking") Booking booking, 
									BindingResult bindingResult, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/book/" + codActivity + ".html" );
			return "login";
		}
		
		booking.setCodActivity( codActivity );
		model.addAttribute( "activity", this.activityDao.getActivity( codActivity ) );
		
		this.bookingValidator.validate( booking ,  bindingResult );
		
		if( bindingResult.hasErrors() ) {
			return "booking/book";
		}
		Profile profile = ( Profile ) session.getAttribute( "profile" );
		String customerNif = profile.getNif();
		booking.setCustomerNif( customerNif );
		booking.setBookingDate( DateService.getTodaysDate() );
		booking.setStatus( BookingStatus.pending );
		bookingDao.addBooking( booking );
		Activity activity = activityDao.getActivity( codActivity );
		EmailSender.sendEmail( EmailType.book,  profile, booking, activity, null );
		return "redirect:../customerBookingList/" + profile.getNif() + ".html";
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
	
	@RequestMapping("/deny/{codBooking}")
	public String denyBooking( @PathVariable int codBooking, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/list.html" );
			return "login";
		}
		Booking booking = bookingDao.getBooking( codBooking );
		booking.setStatus( BookingStatus.denied );
		bookingDao.updateBooking( booking );
		Activity activity = activityDao.getActivity( booking.getCodActivity() );
		Profile profile = customerDao.getCustomer( booking.getCustomerNif() );
		EmailSender.sendEmail( EmailType.deny, profile, booking, activity, null );
		return "redirect:../list.html";
	}
	
	@RequestMapping("/accept/{codBooking}")
	public String acceptBooking( @PathVariable int codBooking, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/accept/" + codBooking + ".html" );
			return "login";
		}
		refreshAssignInstructorModel( model, codBooking );
		return "booking/accept";
	}
	
	@RequestMapping("/assignInstructor.html")
	public String instructorAssigned( @RequestParam("nif") String nif, @RequestParam("codBooking") int codBooking,
										Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/accept/" + codBooking + ".html" );
			return "login";
		}
		bookingDao.assignInstructor( codBooking, nif );
		refreshAssignInstructorModel( model, codBooking );
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
		refreshAssignInstructorModel( model, codBooking );
		return "redirect:bookingDetails/" + codBooking + ".html";
	}
	
	@RequestMapping("/confirmBooking/{codBooking}")
	public String confirmBooking( @PathVariable int codBooking,  HttpSession session, Model model ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ){
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/booking/accept/" + codBooking + ".html" );
			return "login";
		}
		Booking booking = bookingDao.getBooking( codBooking );
		booking.setStatus( BookingStatus.accepted );
		bookingDao.updateBooking( booking );
		Activity activity = activityDao.getActivity( booking.getCodActivity() );
		Profile profile = customerDao.getCustomer( booking.getCustomerNif() );
		EmailSender.sendEmail( EmailType.accept, profile, booking, activity, null );
		return "redirect:../list.html";
	}
	
	@RequestMapping("/bookingDetails/{codBooking}")
	public String showBookingDetails( @PathVariable int codBooking, HttpSession session, Model model ) {
		if( !Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "booking/bookingDetails/" + codBooking + ".html" );
			return "login";
		}
		model.addAttribute( "booking", bookingDao.getBooking( codBooking ) );
		refreshAssignInstructorModel( model, codBooking );
		return "booking/bookingDetails";
	}
	
	@RequestMapping("/send")
	public String send() {
		bookingDao.sendPdf();
		return "booking/send";
	}
	
	private void refreshAssignInstructorModel( Model model, int codBooking ) {
		model.addAttribute( "codBooking", codBooking );
		List<Instructor> instructors = instructorDao.getInstructors();
		List<Instructor> assignedInstructors = instructorDao.getAssignedInstructors( codBooking );
		model.addAttribute( "assignedInstructors", assignedInstructors );
		List<Instructor> difference = ListsDifference.listsDifference( instructors, assignedInstructors );
		model.addAttribute( "availableInstructors", difference );
	}
}
