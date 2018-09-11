package com.andersonmarques.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public void Index() {
		System.out.println("Acessando o Index");
	}
}
