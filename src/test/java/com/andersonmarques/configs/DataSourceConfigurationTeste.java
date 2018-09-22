package com.andersonmarques.configs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class DataSourceConfigurationTeste {
	
	@Bean
	@Profile("teste")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost/projeto-spring-mvc-teste?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return dataSource;
	}
}
