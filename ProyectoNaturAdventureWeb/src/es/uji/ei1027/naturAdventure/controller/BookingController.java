package es.uji.ei1027.naturAdventure.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.naturAdventure.dao.ActivityDao;
import es.uji.ei1027.naturAdventure.dao.BookingDao;
import es.uji.ei1027.naturAdventure.domain.Booking;
import es.uji.ei1027.naturAdventure.domain.BookingStatus;
import es.uji.ei1027.naturAdventure.domain.Profile;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.StartHour;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;
import es.uji.ei1027.naturAdventure.service.DateService;

@Controller
@RequestMapping("/booking")
public class BookingController {

	private BookingDao bookingDao;
	private ActivityDao activityDao;
	
	@Autowired
	public void setBookingDao( BookingDao bookingDao ) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setActivityDao( ActivityDao activityDao ) {
		this.activityDao = activityDao;
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
		if( bindingResult.hasErrors() ) {
			return "booking/book";
		}
		Profile profile = ( Profile ) session.getAttribute( "profile" );
		String customerNif = profile.getNif();
		booking.setCustomerNif( customerNif );
		booking.setCodActivity( codActivity );
		booking.setBookingDate( DateService.getTodaysDate() );
		booking.setStatus( BookingStatus.pending );
		bookingDao.addBooking( booking );
		return "redirect:../../index.jsp";
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
}
