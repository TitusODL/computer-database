package com.excilys.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan(basePackages = { "com.excilys.service", "com.excilys.persistence", "com.excilys.servlet", "com.excilys.mapper", "com.excilys.ui" })
@PropertySource("classpath:hikari.properties")
public class ConfigurationSpring extends AbstractContextLoaderInitializer {

	

    @Autowired
    Environment environment;


	private final String DRIVER = "driverClassName";
	private final String URL = "JdbcUrl";
	private final String USER = "dataSource.user";
	private final String PASSWORD = "dataSource.password";
	
	@Bean
	public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();;
    dataSource.setDriverClassName(environment.getRequiredProperty(DRIVER));
    dataSource.setUrl(environment.getRequiredProperty(URL));
    dataSource.setUsername(environment.getRequiredProperty(USER));
    dataSource.setPassword(environment.getRequiredProperty(PASSWORD));
    return dataSource;
}
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ConfigurationSpring.class);
		return rootContext;

	}
}
