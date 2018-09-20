package com.andersonmarques.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";//o caminho e a extensão é adicionado pelo AppWebConfiguration
	}

}
