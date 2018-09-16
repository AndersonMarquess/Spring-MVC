package com.andersonmarques.configs;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
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
		Class<?>[] configs = {AppWebConfiguration.class, JPAConfiguration.class};
		return configs;
	}

	@Override
	protected String[] getServletMappings() {
		String[] endpoints = {"/"};
		return endpoints;
	}

	/* Encode padrão para comunicação */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encode = new CharacterEncodingFilter("UTF-8");
		Filter[] filtros = {encode};
		
		return filtros;
	}
	
	/*Para configurar os arquivos de múltiplos formatos*/
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
		
	}
}
