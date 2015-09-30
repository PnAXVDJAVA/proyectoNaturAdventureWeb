package es.uji.ei1027.naturAdventure.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.codec.Base64;

import es.uji.ei1027.naturAdventure.dao.ActivityDao;
import es.uji.ei1027.naturAdventure.dao.InstructorDao;
import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.ActivityPicture;
import es.uji.ei1027.naturAdventure.domain.ActivityWithPicture;
import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.domain.Level;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;
import es.uji.ei1027.naturAdventure.service.ListsDifference;
import es.uji.ei1027.naturAdventure.validator.ActivityValidator;

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
	
	public void prueba() {
		System.out.println( "prueba" );
	}
	
	
	@RequestMapping("/list")
	public String listActivities( Model model, HttpSession session ) {
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {			
			List<Activity> activities = activityDao.getActivities();
			for( Activity activity: activities ) {
				String encoded = Base64.encodeBytes( activity.getPicture() );
				activity.setPictureString( encoded );
			}			
			model.addAttribute( "activities", activities );
			return "activity/list";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/activity/list.html" );
		return "login";
	}
	
	@RequestMapping(value="/customerList")
	public String listCustomerActivities( Model model ) {
		List<Activity> activities = activityDao.getActivities();
		for( Activity activity: activities ) {
			String encoded = Base64.encodeBytes( activity.getPicture() );
			activity.setPictureString( encoded );
		}
		model.addAttribute( "activities", activities );
		return "activity/customerList";
	}
	
	@RequestMapping(value="/add")
	public String addActivity( Model model, HttpSession session ) {
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {		
			model.addAttribute( "activity" , new ActivityWithPicture() );
			model.addAttribute( "levels", Level.getStringValues() );
			return "activity/add";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/activity/add.html" );
		return "login";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( Model model, @ModelAttribute("activity") ActivityWithPicture activityWithPicture, BindingResult bindingResult, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/activity/add.html" );
			return "login";
		}
		
		if( bindingResult.hasErrors() ) {
			return "activity/add";
		}
		
		ActivityValidator activityValidator = new ActivityValidator();
		activityValidator.validate( activityWithPicture.getActivity() , bindingResult );
		
		Activity activity = activityWithPicture.getActivity();
		ActivityPicture picture = activityWithPicture.getActivityPicture();
		MultipartFile file = picture.getFile();
		try {
			activity.setPicture( file.getBytes() );
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if( bindingResult.hasErrors() ) {
			return "activity/add";
		}
		activityDao.addActivity( activity );
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{codActivity}")
	public String updateActivity( Model model, @PathVariable int codActivity, HttpSession session ) {
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			Activity activity = activityDao.getActivity( codActivity );
			ActivityWithPicture activityWithPicture = new ActivityWithPicture();
			activityWithPicture.setActivity( activity );
			activityWithPicture.setActivityPicture( new ActivityPicture() );
			model.addAttribute( "activity", activityWithPicture );
			model.addAttribute( "levels", Level.getStringValues() );
			return "activity/update";
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/activity/update/" + codActivity + ".html" );
		return "login";
	}
	
	@RequestMapping(value="/update/{codActivity}", method=RequestMethod.POST)
	public String processUpdateSubmit( Model model, @PathVariable int codActivity, @ModelAttribute("activity") ActivityWithPicture activityWithPicture, BindingResult bindingResult, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/activity/update/" + codActivity + ".html" );
			return "login";
		}
		if( bindingResult.hasErrors() ) {
			return "activity/update";
		}
		
		Activity activity = activityWithPicture.getActivity();
		ActivityPicture activityPicture = activityWithPicture.getActivityPicture();
		if( activityPicture != null ) {
			MultipartFile file = activityPicture.getFile();
			if( !file.isEmpty() ) {
				try {
					activity.setPicture( file.getBytes() );
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				Activity oldActivity = activityDao.getActivity( codActivity );
				byte [] picture = oldActivity.getPicture();
				activity.setPicture( picture );
			}
		}
		else {
			Activity oldActivity = activityDao.getActivity( codActivity );
			byte [] picture = oldActivity.getPicture();
			activity.setPicture( picture );
		}
		
		activity.setCodActivity( codActivity );
		activityDao.updateActivity( activity );
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{codActivity}")
	public String deleteActivity( Model model, @PathVariable int codActivity, HttpSession session ) {
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
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
		if( Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
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
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/activity/activityDetails/" + codActivity + ".html" );
			return "login";
		}
		activityDao.addSpecializedInstructor( codActivity, nif );
		refreshSpecializedInstructorModel( model, codActivity );
		return "redirect:activityDetails/" + codActivity + ".html";
	}
	
	@RequestMapping(value="/rmsp.html")
	public String specializedInstructorRemoved( @RequestParam("nif") String nif, @RequestParam("codActivity") int codActivity, 
												Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/activity/activityDetails/" + codActivity + ".html" );
			return "login";
		}
		activityDao.removeSpecializedInstructor( codActivity, nif );
		refreshSpecializedInstructorModel( model, codActivity );
		return "redirect:activityDetails/" + codActivity + ".html";
	}
	
	@RequestMapping(value="/activityDetails/{codActivity}")
	public String showActivityDetails( @PathVariable int codActivity, HttpSession session, Model model ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel()) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/activity/activityDetails/" + codActivity + ".html" );
			return "login";
		}
		Activity activity = activityDao.getActivity( codActivity );
		String encoded = Base64.encodeBytes( activity.getPicture() );
		activity.setPictureString( encoded );
		model.addAttribute( activity );
		refreshSpecializedInstructorModel( model, codActivity );
		return "activity/activityDetails";
	}
	
	@RequestMapping(value="/activityCustomerDetails/{codActivity}")
	public String showActivityCustomerDetails( @PathVariable int codActivity, HttpSession session, Model model ) {
		if( Authentification.checkAuthentification( session, Roles.INSTRUCTOR.getLevel() ) || 
				Authentification.checkAuthentification( session, Roles.CUSTOMER.getLevel() ) ) {
				Activity activity = activityDao.getActivity( codActivity );
				String encoded = Base64.encodeBytes( activity.getPicture() );
				activity.setPictureString( encoded );
				model.addAttribute( activity );
				return "activity/activityCustomerDetails";	 
		}
		model.addAttribute( "user", new UserDetails() );
		session.setAttribute( "nextURL", "/activity/activityCustomerDetails/" + codActivity + ".html" );
		return "login";
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
