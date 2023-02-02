package com.edu.cibertec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//	@GetMapping("/")
//	public String irLogin() {
//		return "redirect:/login";
//	}
	
	@GetMapping("/login")
	public String irLogin() {
		return "login";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
}
