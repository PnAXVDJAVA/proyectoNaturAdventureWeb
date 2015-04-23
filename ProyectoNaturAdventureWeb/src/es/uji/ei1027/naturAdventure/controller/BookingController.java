package es.uji.ei1027.naturAdventure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.naturAdventure.dao.BookingDao;
import es.uji.ei1027.naturAdventure.domain.Booking;
import es.uji.ei1027.naturAdventure.domain.BookingStatus;
import es.uji.ei1027.naturAdventure.domain.StartHour;

@Controller
@RequestMapping("/booking")
public class BookingController {

	private BookingDao bookingDao;
	
	@Autowired
	public void setBookingDao( BookingDao bookingDao ) {
		this.bookingDao = bookingDao;
	}
	
	@RequestMapping("/list")
	public String listBookings( Model model ) {
		model.addAttribute( "bookings", bookingDao.getBookings() );
		return "booking/list";
	}
	
	@RequestMapping("/add")
	public String addBooking( Model model ) {
		model.addAttribute( "booking", new Booking() );
		model.addAttribute( "hours", StartHour.getStringValues() );
		model.addAttribute( "statusS", BookingStatus.getStringValues() );
		return "booking/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( @ModelAttribute("booking") Booking booking, BindingResult bindingResult ) {
		if( bindingResult.hasErrors() ) {
			return "booking/add";
		}
		bookingDao.addBooking( booking );
		return "redirect: list.html";
	}
}
