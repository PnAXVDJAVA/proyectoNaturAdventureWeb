package es.uji.ei1027.naturAdventure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.naturAdventure.dao.DegreeDao;
import es.uji.ei1027.naturAdventure.domain.Degree;

@Controller
@RequestMapping("/degree")
public class DegreeController {

	private DegreeDao degreeDao;
	
	@Autowired
	public void setDegreeDao( DegreeDao degreeDao ) {
		this.degreeDao = degreeDao;
	}
	
	@RequestMapping("/list")
	public String listDegrees( Model model ) {
		model.addAttribute( "degrees", degreeDao.getDegrees() );
		return "degree/list";
	}
	
	@RequestMapping("/add")
	public String addDegree( Model model ) {
		model.addAttribute( "degree", new Degree() );
		return "degree/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit( @ModelAttribute("degree") Degree degree, BindingResult bindingResult ) {
		if( bindingResult.hasErrors() ) {
			return "degree/add";
		}
		degreeDao.addDegree( degree );
		return "redirect: list.html";
	}
	
	@RequestMapping(value="/update/{codDegree}")
	public String updateDegree( @PathVariable int codDegree, Model model ) {
		model.addAttribute( "degree", degreeDao.getDegree( codDegree ) );
		return "degree/update";
	}
	
}
