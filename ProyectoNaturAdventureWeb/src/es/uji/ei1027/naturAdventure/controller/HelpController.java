package es.uji.ei1027.naturAdventure.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;

@Controller
@RequestMapping("/help")
public class HelpController {

	@RequestMapping("/userHelp")
	public String userHelp() {
		return "help/userHelp";
	}
	
	@RequestMapping("/customerHelp")
	public String customerHelp( Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/help/customerHelp.html" );
			return "login";
		}
		return "help/customerHelp";
	}
	
	@RequestMapping("/instructorHelp")
	public String instructorHelp( Model model , HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.INSTRUCTOR.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/help/instructorHelp.html" );
			return "login";
		}
		return "help/instructorHelp";
	}
	
	@RequestMapping("/adminHelp")
	public String adminHelp( Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/help/adminHelp.html" );
			return "login";
		}
		return "help/adminHelp";
	}
	
}
