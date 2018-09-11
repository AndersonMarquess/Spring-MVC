package com.andersonmarques.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Configurações do SpringMVC
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//Classes de configuração
		Class<?>[] configs = {AppWebConfiguration.class};
		return configs;
	}

	@Override
	protected String[] getServletMappings() {
		String[] endpoints = {"/"};
		return endpoints;
	}

}
