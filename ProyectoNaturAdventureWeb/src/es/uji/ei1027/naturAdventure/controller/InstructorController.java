package es.uji.ei1027.naturAdventure.controller;

import javax.servlet.http.HttpSession;

import org.jasypt.util.password.BasicPasswordEncryptor;
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
import es.uji.ei1027.naturAdventure.domain.InstructorUser;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	private InstructorDao instructorDao;
	private UserDetailsDao userDetailsDao;
	private static final int SECURITY_LEVEL = Roles.ADMIN.getLevel();
	
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
		if( checkAuthentified( session ) ) {			
			model.addAttribute( "instructors", instructorDao.getInstructors() );
			return "instructor/list";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/list.html" );
		return "login";
		
	}
	
	@RequestMapping(value="/add")
	public String addInstructor( Model model, HttpSession session ) {
		if( checkAuthentified( session ) ) {
			model.addAttribute( "instructorUser", new InstructorUser() );
			return "instructor/add";	
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/add.html" );
		return "login";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( @ModelAttribute("instructorUser") InstructorUser instructorUser,
			BindingResult bindingResult, HttpSession session, Model model ) {
		if( !checkAuthentified( session ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/add.html" );
			return "login";
		}
		if( bindingResult.hasErrors() ) {
			return "instructor/add";
		}
		instructorUserConversion( instructorUser );
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{nif}")
	public String updateInstructor( Model model, @PathVariable String nif, HttpSession session ) {
		if( checkAuthentified( session ) ) {
			model.addAttribute( "instructor" , instructorDao.getInstructor( nif ) );
			return "instructor/update";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/update/" + nif + ".html" );
		return "login";
	}
	
	@RequestMapping(value="/update/{nif}", method=RequestMethod.POST)
	public String processUpdateSubmit( @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session, Model model ) {
		if( !checkAuthentified( session ) ) {
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
		if( !checkAuthentified( session ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/delete/" + nif + ".html" );
			return "login";
		}
		Instructor instructor = instructorDao.getInstructor( nif );
		instructorDao.deleteInstructor( instructor );
		return "redirect:../list.html";
	}
	
	private boolean checkAuthentified( HttpSession session ) {
		UserDetails user = (UserDetails) session.getAttribute( "user" );
		if( user == null || user.getRole() < SECURITY_LEVEL ) {
			return false;
		}
		return true;
	}
	
	private void instructorUserConversion( InstructorUser instructorUser ) {
		Instructor instructor = new Instructor();
		UserDetails user = new UserDetails();
		instructor.setNif( instructorUser.getNif() );
		instructor.setName( instructorUser.getName() );
		instructor.setFirstSurname( instructorUser.getFirstSurname() );
		instructor.setSecondSurname( instructorUser.getSecondSurname() );
		instructor.setAddress( instructorUser.getAddress() );
		instructor.setTelephone( instructorUser.getTelephone() );
		instructor.setDateOfBirth( instructorUser.getDateOfBirth() );
		instructor.setEmail( instructorUser.getEmail() );
		instructor.setBankAccount( instructorUser.getBankAccount() );
		instructor.setUserID( instructorUser.getUsername() );
		user.setUsername( instructorUser.getUsername() );
		String password = instructorUser.getPassword();
		BasicPasswordEncryptor pwdEncryptor = new BasicPasswordEncryptor();
		String pwdEncriptada = pwdEncryptor.encryptPassword( password );
		user.setPassword( pwdEncriptada );
		user.setRole( Roles.INSTRUCTOR.getLevel() );
		userDetailsDao.addUser( user );
		instructorDao.addInstructor( instructor );
	}

}
