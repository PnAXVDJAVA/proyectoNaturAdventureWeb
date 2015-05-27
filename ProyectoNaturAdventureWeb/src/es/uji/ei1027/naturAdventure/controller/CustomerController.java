package es.uji.ei1027.naturAdventure.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.naturAdventure.dao.BookingDao;
import es.uji.ei1027.naturAdventure.dao.CustomerDao;
import es.uji.ei1027.naturAdventure.dao.UserDetailsDao;
import es.uji.ei1027.naturAdventure.domain.Customer;
import es.uji.ei1027.naturAdventure.domain.CustomerUserDetailsModel;
import es.uji.ei1027.naturAdventure.domain.PasswordRecoveryDetails;
import es.uji.ei1027.naturAdventure.domain.Profile;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;
import es.uji.ei1027.naturAdventure.service.EmailSender;
import es.uji.ei1027.naturAdventure.service.EmailType;
import es.uji.ei1027.naturAdventure.service.RandomString;
import es.uji.ei1027.naturAdventure.validator.CustomerValidator;
import es.uji.ei1027.naturAdventure.validator.CustomerUpdateValidator;
import es.uji.ei1027.naturAdventure.validator.PasswordValidator;
import es.uji.ei1027.naturAdventure.validator.UserDetailsValidator;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private CustomerDao customerDao;
	private UserDetailsDao userDetailsDao;
	private BookingDao bookingDao;
	private UserDetailsValidator userDetailsValidator;
	private CustomerValidator customerValidator;
	
	@Autowired
	public void setCustomerDao( CustomerDao customerDao ) {
		this.customerDao = customerDao;
	}
	
	@Autowired
	public void setUserDetailsDao( UserDetailsDao udd ) {
		this.userDetailsDao = udd;
	}
	
	@Autowired
	public void setBookingDao( BookingDao bookingDao ) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setUserDetailsValidator( UserDetailsValidator userDetailsValidator ) {
		this.userDetailsValidator = userDetailsValidator;
	}
	
	@Autowired
	public void setCustomerValidator( CustomerValidator customerValidator ) {
		this.customerValidator = customerValidator;
	}
	
	
	@RequestMapping("/list")
	public String listCustomers( Model model, HttpSession session ) {
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			List<Customer> customers = customerDao.getCustomers();
			model.addAttribute( "customers", customers );
			return "customer/list";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/customer/list.html" );
		return "login";
	}
	
	@RequestMapping("/add")
	public String addCustomer( Model model ) {
		model.addAttribute( "customerUser", new CustomerUserDetailsModel() );
		return "customer/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( Model model, @ModelAttribute("customerUser") CustomerUserDetailsModel customerUDM, BindingResult bindingResult, HttpSession session ) {
		
		this.customerValidator.validate(customerUDM.getCustomer(), bindingResult);
		userDetailsValidator.validate(customerUDM.getUserDetails(), bindingResult);
		
		if( bindingResult.hasErrors() ) {
			return "customer/add";
		}
		
		Customer customer = customerUDM.getCustomer();
		UserDetails userDetails = customerUDM.getUserDetails();
		customer.setUsername( userDetails.getUsername() );
		userDetailsDao.addUser( userDetails, Roles.CUSTOMER.getLevel() );
		customerDao.addCustomer( customer );
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			return "redirect:list.html";
		}
		model.addAttribute( "user", new UserDetails() );
		return "login";
	}
	
	@RequestMapping("/update/{nif}")
	public String updateCustomer( Model model, @PathVariable String nif, HttpSession session ) {
		if( Authentification.checkAuthentificationByNif( session, Roles.CUSTOMER.getLevel(), nif ) ) {
			model.addAttribute( "customer", customerDao.getCustomer( nif ) );
			return "customer/update";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/customer/update/" + nif + ".html" );
		return "login";
	}
	
	@RequestMapping(value="/update/{nif}", method=RequestMethod.POST)
	public String processUpdateSubmit( Model model, @PathVariable String nif, HttpSession session, 
										@ModelAttribute("customer") Customer customer, BindingResult bindingResult ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.CUSTOMER.getLevel(), nif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/customer/update/" + nif + ".html" );
			return "login";
		}
		
		CustomerUpdateValidator cuValidator = new CustomerUpdateValidator();
		cuValidator.validate( customer , bindingResult );
		
		if( bindingResult.hasErrors() ) {
			return "customer/update";
		}
		customerDao.updateCustomer( customer );
		UserDetails user = ( UserDetails ) session.getAttribute( "user" );
		if( user.getRole() == Roles.ADMIN.getLevel() ) {
			return "redirect:../customerDetails/" + nif + ".html";
		}
		return "redirect:../../index.jsp";
	}
	
	@RequestMapping("/delete/{nif}")
	public String deleteCustomer( @PathVariable String nif, HttpSession session, Model model ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/customer/delete/" + nif + ".html" );
			return "login";
		}
		Customer customer = customerDao.getCustomer( nif );
		customerDao.deleteCustomer( customer );
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/changePwd/{username}")
	public String changePassword( Model model, @PathVariable String username, HttpSession session ) {
		if( Authentification.checkAuthentificationByUsername( session, Roles.CUSTOMER.getLevel(), username ) ) {
			UserDetails user = userDetailsDao.getUser( username );
			user.setPassword( null );
			model.addAttribute( "user", user );
			return "customer/changePwd";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL" , "customer/changePwd/" + username + ".html" );
		return "login";
	}
	
	@RequestMapping(value="/changePwd/{username}", method=RequestMethod.POST)
	public String processChangePwdSubmit( @PathVariable String username, Model model, @ModelAttribute("user") UserDetails user, 
											BindingResult bindingResult, HttpSession session ) {
		if( !Authentification.checkAuthentificationByUsername( session, Roles.CUSTOMER.getLevel(), username) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "customer/changePwd/" + username + ".html" );
			return "login";
		}
		PasswordValidator pwdValidator = new PasswordValidator();
		pwdValidator.validate( user , bindingResult );
		if( bindingResult.hasErrors() ) {
			return "customer/changePwd";
		}
		userDetailsDao.updateUser( user, Roles.CUSTOMER.getLevel() );
		return "redirect:../../index.jsp";
	}
	
	@RequestMapping(value="/customerDetails/{nif}")
	public String showCustomerDetails( @PathVariable String nif, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.CUSTOMER.getLevel() , nif) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL" , "customer/customerDetails/" + nif + ".html" );
			return "login";
		}
		model.addAttribute( "customer", customerDao.getCustomer( nif ) );
		model.addAttribute( "bookings", bookingDao.getCustomerBookings( nif ) );
		return "customer/customerDetails";
	}
	
	@RequestMapping(value="/pwdRecovery")
	public String pwdRecovery( Model model ) {
		model.addAttribute( "pwdRecoveryDetails", new PasswordRecoveryDetails() );
		return "customer/pwdRecovery";
	}
	
	@RequestMapping(value="/pwdRecovery", method=RequestMethod.POST)
	public String pwdRecoverySubmit( Model model, @ModelAttribute("pwdRecoveryDetails") PasswordRecoveryDetails pwdRecoveryDetails, BindingResult bindingResult ) {
		
		if( bindingResult.hasErrors() ) {
			return "customer/pwdRecovery";
		}
		
		Profile profile = null;
		try {
			profile = customerDao.getProfileByUsername( pwdRecoveryDetails.getUsername() );
		}
		catch( EmptyResultDataAccessException e ) {
			model.addAttribute( "recoveryResult", false );
			return "customer/pwdRecoveryResult";
		}
		
		String rightEmail = profile.getEmail();
		
		if( !rightEmail.equals( pwdRecoveryDetails.getEmail() ) ) {
			model.addAttribute( "recoveryResult", false );
			return "customer/pwdRecoveryResult";
		}
		
		String newPwd = RandomString.randomString();
		
		this.userDetailsDao.updatePassword( profile.getUsername() , newPwd );
		EmailSender.sendEmail( EmailType.pwdRecovery , profile, null, null, newPwd );
		
		model.addAttribute( "recoveryResult", true );
		return "customer/pwdRecoveryResult";
	}
	
}
