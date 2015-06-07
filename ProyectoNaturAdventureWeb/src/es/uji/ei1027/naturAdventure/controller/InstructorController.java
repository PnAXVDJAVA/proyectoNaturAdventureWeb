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









import org.springframework.web.bind.annotation.RequestParam;

import es.uji.ei1027.naturAdventure.dao.DegreeDao;
import es.uji.ei1027.naturAdventure.dao.InstructorDao;
import es.uji.ei1027.naturAdventure.dao.UserDetailsDao;
import es.uji.ei1027.naturAdventure.domain.Degree;
import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.domain.InstructorUserDetailsModel;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;
import es.uji.ei1027.naturAdventure.service.ListsDifference;
import es.uji.ei1027.naturAdventure.validator.InstructorUpdateValidator;
import es.uji.ei1027.naturAdventure.validator.InstructorValidator;
import es.uji.ei1027.naturAdventure.validator.PasswordValidator;
import es.uji.ei1027.naturAdventure.validator.UserDetailsValidator;


@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	private InstructorDao instructorDao;
	private DegreeDao degreeDao;
	private UserDetailsDao userDetailsDao;
	private UserDetailsValidator userDetailsValidator;
	
	@Autowired
	public void setInstructorDao( InstructorDao instructorDao ) {
		this.instructorDao = instructorDao;
	}
	
	@Autowired
	public void setUserDetailsDao( UserDetailsDao udd ) {
		this.userDetailsDao = udd;
	}
	
	@Autowired
	public void setUserDetailsValidator( UserDetailsValidator udv) {
		this.userDetailsValidator = udv;
	}
	
	@Autowired
	public void setDegreeDao( DegreeDao degreeDao ) {
		this.degreeDao = degreeDao;
	}
	
	@RequestMapping("/list")
	public String listInstructors( Model model, HttpSession session ) {
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {			
			model.addAttribute( "instructors", instructorDao.getInstructors() );
			return "instructor/list";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/list.html" );
		return "login";
		
	}
	
	@RequestMapping(value="/add")
	public String addInstructor( Model model, HttpSession session ) {
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
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
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/add.html" );
			return "login";
		}
		
		InstructorValidator validator = new InstructorValidator();
		validator.validate(instructorUser.getInstructor(), bindingResult );
		this.userDetailsValidator.validate( instructorUser.getUserDetails() ,  bindingResult  );
		
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
		if( Authentification.checkAuthentificationByNif( session, Roles.INSTRUCTOR.getLevel(), nif ) ) {
			model.addAttribute( "instructor" , instructorDao.getInstructor( nif ) );
			return "instructor/update";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/update/" + nif + ".html" );
		return "login";
	}
	
	@RequestMapping(value="/update/{nif}", method=RequestMethod.POST)
	public String processUpdateSubmit( @PathVariable String nif, @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session, Model model ) {
		if( !Authentification.checkAuthentificationByNif( session, Roles.INSTRUCTOR.getLevel(), nif ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/update/" + instructor.getNif() + ".html" );
			return "login";
		}
		InstructorUpdateValidator validator = new InstructorUpdateValidator();
		validator.validate( instructor, bindingResult );
		
		if( bindingResult.hasErrors() ) {
			return "instructor/update";
		}
		instructorDao.updateInstructor( instructor );
		return "redirect:../../index.jsp";
	}
	
	@RequestMapping(value="/delete/{nif}")
	public String deleteInstructor( @PathVariable String nif, HttpSession session, Model model ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
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
		if( Authentification.checkAuthentificationByUsername( session, Roles.INSTRUCTOR.getLevel(), username ) ) {
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
		if( !Authentification.checkAuthentificationByUsername( session, Roles.INSTRUCTOR.getLevel(), username ) ) {
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
	
	@RequestMapping(value="/instructorDetails/{nif}")
	public String showInstructorDetails( @PathVariable String nif, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/instructorDetails/" + nif + ".html" );
			return "login";
		}
		Instructor instructor = this.instructorDao.getInstructor( nif );
		model.addAttribute( "instructor", instructor );
		refreshInstructorDegreeModel( model, nif );
		return "instructor/instructorDetails";
	}
	
	@RequestMapping(value="/addTitle.html")
	public String addTitle( @RequestParam("codDegree") int codDegree, @RequestParam("instructorNif") String instructorNif, 
							Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/instructorDetails/" + instructorNif + ".html" );
			return "login";
		}
		this.degreeDao.addDegreeToInstructor( codDegree, instructorNif );
		Instructor instructor = this.instructorDao.getInstructor( instructorNif );
		model.addAttribute( "instructor", instructor );
		refreshInstructorDegreeModel( model, instructorNif );
		return "redirect:instructorDetails/" + instructorNif + ".html";
	}
	
	@RequestMapping(value="/removeTitle.html")
	public String removeTitle( @RequestParam("codDegree") int codDegree, @RequestParam("instructorNif") String instructorNif, 
								Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/instructorDetails/" + instructorNif + ".html" );
			return "login";
		}
		this.degreeDao.removeDegreeFromInstructor( codDegree, instructorNif );
		Instructor instructor = this.instructorDao.getInstructor( instructorNif );
		model.addAttribute( "instructor", instructor );
		refreshInstructorDegreeModel( model, instructorNif );
		return "redirect:instructorDetails/" + instructorNif + ".html";
	}
	
	
	private void refreshInstructorDegreeModel( Model model, String nif ) {
		
		List<Degree> degrees = this.degreeDao.getDegrees();
		
		List<Degree> instructorDegrees = this.degreeDao.getInstructorDegrees( nif );
		
		System.out.println( "Asignadas: " );
		for( Degree degree: instructorDegrees ) {
			System.out.println( degree.getName() ); 
		}
		
		model.addAttribute( "instructorDegrees", instructorDegrees );
		
		List<Degree> difference = ListsDifference.listsDifference( degrees, instructorDegrees );
		
		System.out.println( "Diferencia: " );
		for( Degree degree: difference ) {
			System.out.println( degree.getName() ); 
		}
		
		model.addAttribute( "degrees", difference );
	}

}
