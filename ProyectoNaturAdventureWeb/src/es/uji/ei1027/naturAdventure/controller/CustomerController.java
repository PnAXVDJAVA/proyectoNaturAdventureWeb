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

import es.uji.ei1027.naturAdventure.dao.CustomerDao;
import es.uji.ei1027.naturAdventure.dao.UserDetailsDao;
import es.uji.ei1027.naturAdventure.domain.Customer;
import es.uji.ei1027.naturAdventure.domain.CustomerUserDetailsModel;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private CustomerDao customerDao;
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	public void setCustomerDao( CustomerDao customerDao ) {
		this.customerDao = customerDao;
	}
	
	@Autowired
	public void setUserDetailsDao( UserDetailsDao udd ) {
		this.userDetailsDao = udd;
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
	public String processAddSubmit( @ModelAttribute("customerUser") CustomerUserDetailsModel customerUDM, BindingResult bindingResult, HttpSession session ) {
		
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
		return "redirect:login.html";
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
		if( bindingResult.hasErrors() ) {
			return "customer/update";
		}
		customerDao.updateCustomer( customer );
		UserDetails user = ( UserDetails ) session.getAttribute( "user" );
		if( user.getRole() == Roles.ADMIN.getLevel() ) {
			return "redirect:../list.html";
		}
		return "redirect:../../index.jsp";
	}
	
	
}