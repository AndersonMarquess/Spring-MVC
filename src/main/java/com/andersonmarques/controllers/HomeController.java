package com.andersonmarques.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String Index() {
		System.out.println("Acessando o Index");
		return "home";//Não precisa do .jsp, ele já é o padrão escolhido na nossa AppWebConfiguration
	}
}
