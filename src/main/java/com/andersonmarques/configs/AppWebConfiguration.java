package com.andersonmarques.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.andersonmarques.controllers.HomeController;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class})//Classes de controle
public class AppWebConfiguration {
	
	/*Informa onde procurar as páginas*/
	@Bean//Bean para informar que este método é gerenciado pelo Spring
	public InternalResourceViewResolver internalResourceViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");//Seta o padrão das extensões das páginas
		
		return resolver;
	}
}
