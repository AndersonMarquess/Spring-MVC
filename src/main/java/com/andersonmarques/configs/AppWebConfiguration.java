package com.andersonmarques.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.andersonmarques.controllers.HomeController;
import com.andersonmarques.daos.ProdutoDao;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDao.class})//Classes componentes/bean
public class AppWebConfiguration {
	
	/*Informa onde procurar as páginas*/
	@Bean//Bean para informar que este método é gerenciado pelo Spring
	public InternalResourceViewResolver internalResourceViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");//Seta o padrão das extensões das páginas
		
		return resolver;
	}
	
	/*Mensagens de feedback*/
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
			= new ReloadableResourceBundleMessageSource();
		
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		
		return messageSource;
	}
	
	/*Define o formato de data padrão*/
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService
			= new DefaultFormattingConversionService();
		
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
	    formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
	    formatterRegistrar.registerFormatters(conversionService);
		
		return conversionService;
	}
}
