package com.edu.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//	@GetMapping("/")
//	public String login() {
//		return "redirect:/login";
//	}
//	
	@GetMapping("/login") // cunado te conectas a esta direcciones te devuelve la vista de login.html
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
}
