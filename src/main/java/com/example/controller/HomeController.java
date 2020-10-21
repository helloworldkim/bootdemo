package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	// http://127.0.0.1:8080/
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home() {
		 return "home";  //home.jsp가 표시됨.
	}
		
	// http://127.0.0.1:8080/home
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home1() {
		return "home";  //home.jsp가 표시됨.
	}
	
}
