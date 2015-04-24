package es.uji.ei1027.naturAdventure.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.naturAdventure.dao.UserDao;
import es.uji.ei1027.naturAdventure.domain.UserDetails;

class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return UserDetails.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserDetails user = (UserDetails) obj;
		if( user.getUsername().trim().equals( "" ) ) {
			errors.rejectValue( "username" , "obligatorio", "Hay que introducir el usuario" );
		}
		if( user.getPassword().trim().equals("") ) {
			errors.rejectValue( "password" , "obligatorio", "Hay que introducir la contraseņa" );
		}
	}
	
}

@Controller
public class LoginController {
	
	private UserDao userDao;
	
	@Autowired
	public void setUserDao( UserDao userDao ) {
		this.userDao = userDao;
	}
	
	@RequestMapping("/login")
	public String login( Model model ) {
		model.addAttribute( "user", new UserDetails() );
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String checkLogin( @ModelAttribute("user") UserDetails user, BindingResult bindingResult, HttpSession session ) {
		UserValidator userValidator = new UserValidator();
		userValidator.validate( user , bindingResult );
		if( bindingResult.hasErrors() ) {
			return "login";
		}
		
		user = userDao.loadByUsername( user.getUsername(), user.getPassword() );
		if( user == null ) {
			bindingResult.rejectValue( "password" , "badpw", "Contraseņa incorrecta" );
			return "login";
		}
		
		session.setAttribute( "user" , user );
		
		String nextUrl = (String) session.getAttribute( "nextURL" );
		
		if( nextUrl != null ) {
			session.removeAttribute( "nextURL" );
			return "redirect:" + nextUrl;
		}
		
		return "redirect:index.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout( HttpSession session ) {
		session.invalidate();
		return "redirect:index.jsp";
	}

}
