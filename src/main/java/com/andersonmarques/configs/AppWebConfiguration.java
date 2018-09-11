package com.andersonmarques.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.andersonmarques.controllers.HomeController;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class})//Classes de controle
public class AppWebConfiguration {
	
}
