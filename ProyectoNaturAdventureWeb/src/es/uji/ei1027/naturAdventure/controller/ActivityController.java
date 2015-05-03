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

import es.uji.ei1027.naturAdventure.dao.ActivityDao;
import es.uji.ei1027.naturAdventure.dao.InstructorDao;
import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.domain.Level;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.ListsDifference;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	private ActivityDao activityDao;
	private InstructorDao instructorDao;
	
	@Autowired
	public void setActivityDao( ActivityDao activityDao ) {
		this.activityDao = activityDao;
	}
	
	@Autowired
	public void setInstructorDao( InstructorDao instructorDao ) {
		this.instructorDao = instructorDao;
	}
	
	@RequestMapping("/list")
	public String listActivities( Model model, HttpSession session ) {
		if( checkAuthentification( session, Roles.ADMIN.getLevel()) ) {			
			model.addAttribute( "activities", activityDao.getActivities() );
			return "activity/list";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/activity/list.html" );
		return "login";
	}
	
	@RequestMapping(value="/add")
	public String addActivity( Model model, HttpSession session ) {
		if( checkAuthentification( session, Roles.ADMIN.getLevel()) ) {		
			model.addAttribute( "activity" , new Activity() );
			model.addAttribute( "levels", Level.getStringValues() );
			return "activity/add";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/activity/add.html" );
		return "login";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( Model model, @ModelAttribute("activity") Activity activity, BindingResult bindingResult, HttpSession session ) {
		if( !checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/activity/add.html" );
			return "login";
		}
		if( bindingResult.hasErrors() ) {
			return "activity/add";
		}
		activityDao.addActivity( activity );
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{codActivity}")
	public String updateActivity( Model model, @PathVariable int codActivity, HttpSession session ) {
		if( checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "activity", activityDao.getActivity( codActivity ) );
			model.addAttribute( "levels", Level.getStringValues() );
			return "activity/update";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/activity/update/" + codActivity + ".html" );
		return "login";
	}
	
	@RequestMapping(value="/update/{codActivity}", method=RequestMethod.POST)
	public String processUpdateSubmit( Model model, @PathVariable int codActivity, @ModelAttribute("activity") Activity activity, BindingResult bindingResult, HttpSession session ) {
		if( !checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/activity/update/" + codActivity + ".html" );
			return "login";
		}
		if( bindingResult.hasErrors() ) {
			return "activity/update";
		}
		activity.setCodActivity( codActivity );
		activityDao.updateActivity( activity );
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{codActivity}")
	public String deleteActivity( Model model, @PathVariable int codActivity, HttpSession session ) {
		if( checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			Activity activity = new Activity();
			activity.setCodActivity( codActivity );
			activityDao.deleteActivity(activity);
			return "redirect:../list.html";	
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/activity/list.html" );
		return "login";
	}
	
	@RequestMapping(value="/addSpecializedInstructor/{codActivity}")
	public String addSpecializedInstructor( Model model, @PathVariable int codActivity, HttpSession session ) {
		if( checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			//Creamos el modelo para la vista, calculando los monitores añadidos a esa actividad
			//y los que quedan disponibles por añadir
			refreshSpecializedInstructorModel( model, codActivity );
			return "activity/addSpecializedInstructor";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/activity/addSpecializedInstructor/" + codActivity + ".html" );
		return "login";
	}
	
	@RequestMapping(value="/addsp.html")
	public String specializedInstructorAdded( @RequestParam("nif") String nif, @RequestParam("codActivity") int codActivity,
												Model model, HttpSession session ) {
		if( !checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/activity/addSpecializedInstructor/" + codActivity + ".html" );
			return "login";
		}
		activityDao.addSpecializedInstructor( codActivity, nif );
		refreshSpecializedInstructorModel( model, codActivity );
		return "redirect:addSpecializedInstructor/" + codActivity + ".html";
	}
	
	@RequestMapping(value="/rmsp.html")
	public String specializedInstructorRemoved( @RequestParam("nif") String nif, @RequestParam("codActivity") int codActivity, 
												Model model, HttpSession session ) {
		if( !checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/activity/addSpecializedInstructor/" + codActivity + ".html" );
			return "login";
		}
		activityDao.removeSpecializedInstructor( codActivity, nif );
		refreshSpecializedInstructorModel( model, codActivity );
		return "redirect:addSpecializedInstructor/" + codActivity + ".html";
	}
	
	private boolean checkAuthentification( HttpSession session, int securityLevel ) {
		UserDetails user = (UserDetails) session.getAttribute( "user" );
		if( user == null || ( user.getRole() != Roles.ADMIN.getLevel() && user.getRole() < securityLevel ) ) {
			return false;
		}
		return true;
	}
	
	//refactorizar
	private void refreshSpecializedInstructorModel( Model model, int codActivity ) {
		//Añadimos el código de la actividad
		model.addAttribute( "codActivity", codActivity );
		
		Activity activity = activityDao.getActivity( codActivity );
		//Añadimos el nombre de la actividad
		model.addAttribute( "activityName", activity.getName() );
		
		//Monitores totales disponibles
		List<Instructor> instructors = instructorDao.getInstructors();
		
		//Monitores ya añadidos
		List<Instructor> addedInstructors = this.instructorDao.getSpecializedInstructors( codActivity );
		model.addAttribute( "addedInstructors", addedInstructors );
		
		//Los que aún no están añadidos
		List<Instructor> difference = ListsDifference.listsDifference( instructors, addedInstructors );
		
		model.addAttribute( "availableInstructors", difference );
	}

}
