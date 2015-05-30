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
	
	@RequestMapping(value="/408.html")
	public String handle408() {
		return "errors/408";
	}
	
	@RequestMapping(value="/500.html")
	public String handle500() {
		return "errors/500";
	}
	
	@RequestMapping(value="/503.html")
	public String handle503() {
		return "errors/503";
	}
	
	@RequestMapping(value="/509.html")
	public String handle509() {
		return "errors/509";
	}
}
