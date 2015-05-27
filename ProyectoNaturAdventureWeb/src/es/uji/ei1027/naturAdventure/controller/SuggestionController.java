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

import es.uji.ei1027.naturAdventure.dao.SuggestionDao;
import es.uji.ei1027.naturAdventure.domain.MessageType;
import es.uji.ei1027.naturAdventure.domain.Profile;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.Suggestion;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;
import es.uji.ei1027.naturAdventure.validator.SuggestionValidator;

@Controller
@RequestMapping("/suggestion")
public class SuggestionController {
	
	private SuggestionDao suggestionDao;
	
	@Autowired
	public void setSuggestionDao( SuggestionDao suggestionDao ) {
		this.suggestionDao = suggestionDao;
	}
	
	@RequestMapping("/add")
	public String addSuggestion( Model model, HttpSession session ) {
		Suggestion suggestion = new Suggestion();
		if( Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel() ) || 
			Authentification.checkAuthentification( session, Roles.INSTRUCTOR.getLevel() ) ) {
			UserDetails user = ( UserDetails ) session.getAttribute( "user" );
			int role = user.getRole();
			if( role == Roles.ADMIN.getLevel() ) {
				suggestion.setName( "Admin" );
				suggestion.setEmail( "naturadventure.xvd@gmail.com" );
			}
			else {
				Profile profile = ( Profile ) session.getAttribute( "profile" );
				suggestion.setName( profile.getName() );
				suggestion.setEmail( profile.getEmail() );
			}
		}
		
		model.addAttribute( "suggestion", suggestion );
		model.addAttribute( "messageTypes", MessageType.getStringValues() );
		
		return "suggestion/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST )
	public String processAddSubmit( Model model, HttpSession session, 
									@ModelAttribute("suggestion") Suggestion suggestion, BindingResult bindingResult ) {
		
		SuggestionValidator suggestionValidator = new SuggestionValidator();
		suggestionValidator.validate( suggestion ,  bindingResult );
		
		if( bindingResult.hasErrors() ) {
			return "suggestion/add";
		}
		
		this.suggestionDao.addSuggestion( suggestion );
		session.setAttribute( "suggestionSent" ,  true );
		
		return "redirect:suggestionResult.html";
	}
	
	@RequestMapping("/suggestionResult")
	public String suggestionResult( HttpSession session ) {
		try {
			boolean suggestionResult = ( boolean ) session.getAttribute( "suggestionSent" );
			if( suggestionResult ) {
				session.setAttribute( "suggestionSent" , null );
				return "suggestion/suggestionResult";
			}
		}
		catch( NullPointerException e ) { }
		return "redirect:../index.jsp";
		
	}
	
	@RequestMapping("/list")
	public String listSuggestions( Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/suggestion/list.html" );
			return "login";			
		}
		
		List<Suggestion> suggestions = this.suggestionDao.getSuggestions();
		
		model.addAttribute( "suggestions", suggestions );
		
		return "suggestion/list";
	}
	
	@RequestMapping("/suggestionDetails/{suggestion_code}")
	public String suggestionDetails( @PathVariable int suggestion_code, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/suggestion/suggestionDetails/" + suggestion_code + ".html" );
			return "login";			
		}
		
		Suggestion suggestion = this.suggestionDao.getSuggestion( suggestion_code );
		
		model.addAttribute( "suggestion", suggestion );
		
		return "suggestion/suggestionDetails";
	}
	
}
