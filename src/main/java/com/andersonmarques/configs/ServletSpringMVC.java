package com.andersonmarques.configs;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Configurações do SpringMVC
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		//Classes que são carregando durante a inicialização
		Class<?>[] configs = {SecurityConfiguration.class, 
				AppWebConfiguration.class, JPAConfiguration.class};
		return configs;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//Classes de configuração
		Class<?>[] configs = {};
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
		//OpenEntity mantém a sessão com a db ativa até ser exibida na jsp, evitando o Lazy Initialization Exception
		Filter[] filtros = {encode, new OpenEntityManagerInViewFilter()};
		
		return filtros;
	}
	
	/*Para configurar os arquivos de múltiplos formatos*/
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
		
	}
	
	/* Configura o profile ativo inicial da aplicação */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter("spring.profiles.active", "dev");//dev é o profile do banco de dados normal
	}
}
