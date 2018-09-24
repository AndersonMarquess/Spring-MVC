package com.andersonmarques.configs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.andersonmarques.controllers.HomeController;
import com.andersonmarques.daos.ProdutoDao;
import com.andersonmarques.infra.FileSaver;
import com.andersonmarques.models.CarrinhoCompras;
import com.google.common.cache.CacheBuilder;

@EnableCaching
@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, ProdutoDao.class,
		FileSaver.class, CarrinhoCompras.class }) // Classes componentes/bean
public class AppWebConfiguration implements WebMvcConfigurer {

	/* Informa onde procurar as páginas */
	@Bean // Bean para informar que este método é gerenciado pelo Spring
	public InternalResourceViewResolver internalResourceViewResolver() {

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");// Seta o padrão das extensões das páginas

		// O Bean fica disponível na JSP, nome da classe com a primeira letra em minúsculo.
		resolver.setExposedContextBeanNames("carrinhoCompras");

		return resolver;
	}

	/* Mensagens de feedback */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);

		return messageSource;
	}

	/* Define o formato de data padrão */
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
		formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatterRegistrar.registerFormatters(conversionService);

		return conversionService;
	}

	/* Para configurar os arquivos de múltiplos formatos */
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	/* Habilita a pasta resources com os arquivos CSS, JS, IMG... */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/* Responsável por fazer as requisições externas. */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/* Gerencia o cache */
	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()
				.maximumSize(100)
				.expireAfterAccess(5,TimeUnit.MINUTES);
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(builder);

		return manager;
	}
	
	/*Retorna HTML ou JSON*/
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(new JsonViewResolver());
		
		
		ContentNegotiatingViewResolver contentResolver = new ContentNegotiatingViewResolver();
		contentResolver.setViewResolvers(viewResolvers);
		contentResolver.setContentNegotiationManager(manager);
		
		return contentResolver;
	}
	
	/* Altera o locale */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	/* Salva a configuração de locale escolhida */
	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}
	
	/* Configuração para envio de e-mail */
	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost("smtp.gmail.com");
		sender.setUsername("email_de_origem@gmail.com");
		sender.setPassword("senha-escolhida");
		sender.setPort(587);
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		
		sender.setJavaMailProperties(properties);
		
		return sender;
	}
}
