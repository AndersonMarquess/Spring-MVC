package com.andersonmarques.configs;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe que configura a conexão com o banco de dados
 * 
 * @author Anderson Marques
 * @version 1.0
 */
@EnableTransactionManagement//Habilita o gerenciamento de transação
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean containerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		
		containerFactoryBean.setDataSource(dataSource);
		
		JpaVendorAdapter vendorAdaptor = new HibernateJpaVendorAdapter();
		containerFactoryBean.setJpaVendorAdapter(vendorAdaptor);
		containerFactoryBean.setJpaProperties(getConfiguracaoHibernate());
		
		containerFactoryBean.setPackagesToScan("com.andersonmarques.models");
		return containerFactoryBean;
	}

	/**
	 * Retorna as configurações do hibernate
	 * @return
	 */
	private Properties getConfiguracaoHibernate() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update"); //update | create-drop 
		return properties;
	}

	/**
	 * Retorna o caminho do banco de dados já configurado com usuário e senha.
	 * @return dataSource
	 */
	@Bean
	@Profile("dev")
	private DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost/projeto-spring-mvc?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSource;
	}
	
	@Bean
	public JpaTransactionManager transactionalManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
}
