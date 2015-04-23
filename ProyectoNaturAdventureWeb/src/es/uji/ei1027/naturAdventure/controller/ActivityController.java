package es.uji.ei1027.naturAdventure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.naturAdventure.dao.ActivityDao;
import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Level;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	private ActivityDao activityDao;
	
	@Autowired
	public void setActivityDao( ActivityDao activityDao ) {
		this.activityDao = activityDao;
	}
	
	@RequestMapping("/list")
	public String listActivities( Model model ) {
		model.addAttribute( "activities", activityDao.getActivities() );
		return "activity/list";
	}
	
	@RequestMapping(value="/add")
	public String addActivity( Model model ) {
		model.addAttribute( "activity" , new Activity() );
		model.addAttribute( "levels", Level.getStringValues() );
		return "activity/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( @ModelAttribute("activity") Activity activity, BindingResult bindingResult ) {
		if( bindingResult.hasErrors() ) {
			return "activity/add";
		}
		activityDao.addActivity( activity );
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{codActivity}")
	public String updateActivity( Model model, @PathVariable int codActivity ) {
		model.addAttribute( "activity", activityDao.getActivity( codActivity ) );
		model.addAttribute( "levels", Level.getStringValues() );
		return "activity/update";
	}
	
	@RequestMapping(value="/update/{codActivity}", method=RequestMethod.POST)
	public String processUpdateSubmit( @PathVariable int codActivity, @ModelAttribute("activity") Activity activity, BindingResult bindingResult ) {
		if( bindingResult.hasErrors() ) {
			return "activity/update";
		}
		activityDao.updateActivity( activity );
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{codActivity}")
	public String deleteActivity( @PathVariable int codActivity ) {
		Activity activity = new Activity();
		activity.setCodActivity( codActivity );
		activityDao.deleteActivity(activity);
		return "redirect:../list.html";
	}

}
