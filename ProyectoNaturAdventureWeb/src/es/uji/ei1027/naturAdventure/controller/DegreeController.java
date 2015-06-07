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

import es.uji.ei1027.naturAdventure.dao.DegreeDao;
import es.uji.ei1027.naturAdventure.domain.Activity;
import es.uji.ei1027.naturAdventure.domain.Degree;
import es.uji.ei1027.naturAdventure.domain.Instructor;
import es.uji.ei1027.naturAdventure.domain.Roles;
import es.uji.ei1027.naturAdventure.domain.UserDetails;
import es.uji.ei1027.naturAdventure.service.Authentification;
import es.uji.ei1027.naturAdventure.service.ListsDifference;

@Controller
@RequestMapping("/degree")
public class DegreeController {

	private DegreeDao degreeDao;
	
	@Autowired
	public void setDegreeDao( DegreeDao degreeDao ) {
		this.degreeDao = degreeDao;
	}
	
	@RequestMapping("/list")
	public String listDegrees( Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/degree/list.html" );
			return "login";
		}
		model.addAttribute( "degrees", degreeDao.getDegrees() );
		return "degree/list";
	}
	
	@RequestMapping("/add")
	public String addDegree( Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/degree/add.html" );
			return "login";
		}
	
		model.addAttribute( "degree", new Degree() );
		return "degree/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( Model model, @ModelAttribute("degree") Degree degree, BindingResult bindingResult, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/degree/add.html" );
			return "login";
		}
		if( bindingResult.hasErrors() ) {
			return "degree/add";
		}
		degreeDao.addDegree( degree );
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{codDegree}")
	public String updateDegree( @PathVariable int codDegree, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/degree/update/" + codDegree + ".html" );
			return "login";
		}
		model.addAttribute( "degree", degreeDao.getDegree( codDegree ) );
		return "degree/update";
	}
	
	@RequestMapping(value="/update/{codDegree}", method=RequestMethod.POST)
	public String processUpdateSubmit( Model model, HttpSession session, @PathVariable int codDegree, @ModelAttribute("degree") Degree degree, BindingResult bindingResult ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/degree/update/" + codDegree + ".html" );
			return "login";
		}
		if( bindingResult.hasErrors() ) {
			return "degree/update";
		}
		degree.setCodDegree( codDegree );
		degreeDao.updateDegree( degree );
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{codDegree}")
	public String deleteDegree( @PathVariable int codDegree, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/degree/list.html" );
			return "login";
		}
		Degree degree = new Degree();
		degree.setCodDegree( codDegree );
		degreeDao.deleteDegree( degree );
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/degreeDetails/{codDegree}")
	public String showDegreeDetails( @PathVariable int codDegree, Model model, HttpSession session ) {
		if( !Authentification.checkAuthentification( session, Roles.ADMIN.getLevel() ) ) {
			model.addAttribute( "user", new UserDetails() );
			session.setAttribute( "nextURL", "/degree/degreeDetails/" + codDegree + ".html" );
			return "login";
		}
		Degree degree = this.degreeDao.getDegree( codDegree );
		model.addAttribute( "degree", degree );
		
		return "degree/degreeDetails";
	}
	
}
