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




import es.uji.ei1027.naturAdventure.dao.InstructorDao;
import es.uji.ei1027.naturAdventure.dao.UserDetailsDao;
import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.domain.InstructorUserDetailsModel;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.validator.InstructorValidator;
import es.uji.ei1027.naturAdventure.validator.PasswordValidator;


@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	private InstructorDao instructorDao;
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	public void setInstructorDao( InstructorDao instructorDao ) {
		this.instructorDao = instructorDao;
	}
	
	@Autowired
	public void setUserDetailsDao( UserDetailsDao udd ) {
		this.userDetailsDao = udd;
	}
	
	@RequestMapping("/list")
	public String listInstructors( Model model, HttpSession session ) {
		if( checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {			
			model.addAttribute( "instructors", instructorDao.getInstructors() );
			return "instructor/list";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/list.html" );
		return "login";
		
	}
	
	@RequestMapping(value="/add")
	public String addInstructor( Model model, HttpSession session ) {
		if( checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "instructorUser", new InstructorUserDetailsModel() );
			return "instructor/add";	
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/add.html" );
		return "login";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( @ModelAttribute("instructorUser") InstructorUserDetailsModel instructorUser,
			BindingResult bindingResult, HttpSession session, Model model ) {
		if( !checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/add.html" );
			return "login";
		}
		
		InstructorValidator validator = new InstructorValidator();
		validator.validate(instructorUser.getInstructor(), bindingResult);
		
		if( bindingResult.hasErrors() ) {
			return "instructor/add";
		}
		Instructor instructor = instructorUser.getInstructor();
		UserDetails user = instructorUser.getUserDetails();
		/*ystem.out.println( instructor.getDateTry() );
		try {
			Date dateTry = new SimpleDateFormat( "MMM d, yyyy" ).parse( instructor.getDateTry() );
			System.out.println( dateTry.toString() );
		} catch (ParseException e) {
			System.out.println( "error" );
		}*/
		instructor.setUserID( user.getUsername() );
		userDetailsDao.addUser( user, Roles.INSTRUCTOR.getLevel() );
		instructorDao.addInstructor( instructor );
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{nif}")
	public String updateInstructor( Model model, @PathVariable String nif, HttpSession session ) {
		if( checkAuthentificationByNif( session, Roles.INSTRUCTOR.getLevel(), nif ) ) {
			model.addAttribute( "instructor" , instructorDao.getInstructor( nif ) );
			return "instructor/update";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/update/" + nif + ".html" );
		return "login";
	}
	
	@RequestMapping(value="/update/{nif}", method=RequestMethod.POST)
	public String processUpdateSubmit( @PathVariable String nif, @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session, Model model ) {
		if( !checkAuthentificationByNif( session, Roles.INSTRUCTOR.getLevel(), nif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/update/" + instructor.getNif() + ".html" );
			return "login";
		}
		if( bindingResult.hasErrors() ) {
			return "instructor/update";
		}
		instructorDao.updateInstructor( instructor );
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{nif}")
	public String deleteInstructor( @PathVariable String nif, HttpSession session, Model model ) {
		if( !checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/delete/" + nif + ".html" );
			return "login";
		}
		Instructor instructor = instructorDao.getInstructor( nif );
		instructorDao.deleteInstructor( instructor );
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/changePwd/{username}")
	public String changePassword( Model model, @PathVariable String username, HttpSession session ) {
		if( checkAuthentificationByUsername( session, Roles.INSTRUCTOR.getLevel(), username ) ) {
			UserDetails user = userDetailsDao.getUser( username );
			user.setPassword( null );
			model.addAttribute( "user" , user );
			return "instructor/changePwd";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/changePwd/" + username + ".html" );
		return "login";
	}
	
	@RequestMapping(value="/changePwd/{username}", method=RequestMethod.POST)
	public String processChangePwdSubmit( @PathVariable String username, Model model, @ModelAttribute("user") UserDetails user, 
											BindingResult bindingResult, HttpSession session ) {
		if( !checkAuthentificationByUsername( session, Roles.INSTRUCTOR.getLevel(), username ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/changePwd/" +user.getUsername() + ".html" );
			return "login";
		}
		PasswordValidator pwdValidator = new PasswordValidator();
		pwdValidator.validate( user , bindingResult );
		if( bindingResult.hasErrors() ) {
			return "instructor/changePwd";
		}
		userDetailsDao.updateUser( user, Roles.INSTRUCTOR.getLevel() );
		return "redirect:../../index.jsp";
	}
	
	private boolean checkAuthentification( HttpSession session, int securityLevel ) {
		UserDetails user = (UserDetails) session.getAttribute( "user" );
		if( user == null || ( user.getRole() != Roles.ADMIN.getLevel() && user.getRole() < securityLevel ) ) {
			return false;
		}
		return true;
	}
	
	private boolean checkAuthentificationByUsername( HttpSession session, int securityLevel, String username ) {
		UserDetails user = (UserDetails) session.getAttribute( "user" );
		if( user == null || ( user.getRole() != Roles.ADMIN.getLevel() && ( user.getRole() < securityLevel || !user.getUsername().equals( username ) ) ) ) {
			return false;
		}
		return true;
	}
	
	private boolean checkAuthentificationByNif( HttpSession session, int securityLevel, String nif ) {
		UserDetails user = (UserDetails) session.getAttribute( "user" );
		Instructor instructor = (Instructor) session.getAttribute( "profile" );
		if( user == null || ( user.getRole() != Roles.ADMIN.getLevel() && ( user.getRole() < securityLevel || !instructor.getNif().equals( nif ) ) ) ) {
			return false;
		}
		return true;
	}
	
	

}
