package es.uji.ei1027.naturAdventure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {

	@RequestMapping("/contact")
	public String showContact() {
		return "info/contact";
	}
	
	@RequestMapping("/sugerencias")
	public String sugerencias() {
		return "info/sugerencias";
	}
	
}
