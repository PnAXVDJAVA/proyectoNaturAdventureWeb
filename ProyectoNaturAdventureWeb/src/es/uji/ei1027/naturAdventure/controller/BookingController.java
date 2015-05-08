package es.uji.ei1027.naturAdventure.controller;

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
import es.uji.ei1027.naturAdventure.domain.StartHour;

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
	public String listBookings( Model model ) {
		model.addAttribute( "bookings", bookingDao.getBookings() );
		return "booking/list";
	}
	
	@RequestMapping("/book/{codActivity}")
	public String addBooking( Model model, @PathVariable int codActivity ) {
		Booking booking = new Booking();
		booking.setCodActivity( codActivity );
		model.addAttribute( "booking", booking );
		model.addAttribute( "activity", this.activityDao.getActivity( codActivity ) );
		model.addAttribute( "hours", StartHour.getStringValues() );
		model.addAttribute( "statusS", BookingStatus.getStringValues() );
		return "booking/book";
	}
	
	@RequestMapping(value="/book/{codActivity}", method=RequestMethod.POST)
	public String processAddSubmit( @PathVariable int codActivity, @ModelAttribute("booking") Booking booking, BindingResult bindingResult ) {
		System.out.println( booking.getCodActivity() );
		if( bindingResult.hasErrors() ) {
			return "booking/book";
		}
		bookingDao.addBooking( booking );
		return "redirect: list.html";
	}
}
