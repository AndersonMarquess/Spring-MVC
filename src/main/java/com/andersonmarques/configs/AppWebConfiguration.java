package com.andersonmarques.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.andersonmarques.controllers.HomeController;
import com.andersonmarques.daos.ProdutoDao;
import com.andersonmarques.infra.FileSaver;
import com.andersonmarques.models.CarrinhoCompras;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, 
		ProdutoDao.class, FileSaver.class, CarrinhoCompras.class})//Classes componentes/bean
public class AppWebConfiguration implements WebMvcConfigurer {
	
	/*Informa onde procurar as páginas*/
	@Bean//Bean para informar que este método é gerenciado pelo Spring
	public InternalResourceViewResolver internalResourceViewResolver() {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");//Seta o padrão das extensões das páginas
		
		//O Bean fica disponível na JSP, nome da classe com a primeira letra em minúsculo. 
		resolver.setExposedContextBeanNames("carrinhoCompras");
		
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
	
	/*Para configurar os arquivos de múltiplos formatos*/
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
	/*Habilita a pasta resources com os arquivos CSS, JS, IMG...*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations(
                "/resources/");
    }
}
