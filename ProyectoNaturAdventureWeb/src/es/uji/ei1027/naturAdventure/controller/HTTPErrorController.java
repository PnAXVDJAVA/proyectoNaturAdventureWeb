package es.uji.ei1027.naturAdventure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class HTTPErrorController {
	
	@RequestMapping(value="/404.html")
	public String handle404() {
		return "errors/404";
	}

}
