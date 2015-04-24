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
import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.domain.UserDetails;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	private InstructorDao instructorDao;
	
	@Autowired
	public void setInstructorDao( InstructorDao instructorDao ) {
		this.instructorDao = instructorDao;
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
			model.addAttribute( "instructor", new Instructor() );
			return "instructor/add";	
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/instructor/add.html" );
		return "login";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult, HttpSession session, Model model ) {
		if( !checkAuthentified( session ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/instructor/add.html" );
			return "login";
		}
		if( bindingResult.hasErrors() ) {
			return "instructor/add";
		}
		instructorDao.addInstructor( instructor );
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
		Instructor instructor = new Instructor();
		instructor.setNif( nif );
		instructorDao.deleteInstructor( instructor );
		return "redirect:../list.html";
	}
	
	private boolean checkAuthentified( HttpSession session ) {
		if( session.getAttribute( "user" ) == null ) {
			return false;
		}
		return true;
	}

}
