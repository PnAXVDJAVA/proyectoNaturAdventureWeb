package es.uji.ei1027.naturAdventure.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.naturAdventure.dao.LoginDao;
import es.uji.ei1027.naturAdventure.dao.UserDao;
import es.uji.ei1027.naturAdventure.domain.Profile;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.LoginDaoFactory;
import es.uji.ei1027.naturAdventure.validator.UserValidator;


@Controller
public class LoginController {
	
	private UserDao userDao;
	private LoginDaoFactory loginDaoFactory;
	
	@Autowired
	public void setUserDao( UserDao userDao ) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setLoginDaoFactory( LoginDaoFactory loginDaoFactory ) {
		this.loginDaoFactory = loginDaoFactory;
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
			bindingResult.rejectValue( "password" , "badpw", "Contraseña incorrecta" );
			return "login";
		}
		
		session.setAttribute( "user" , user );
		
		//Los admin no tienen perfil
		if( user.getRole() < Roles.ADMIN.getLevel() ) {
			LoginDao loginDao = loginDaoFactory.getLoginDaoByUserRole( user.getRole() );
			Profile profile = loginDao.getProfileByUsername( user.getUsername() );
			session.setAttribute( "profile" , profile );
		}	
		
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
