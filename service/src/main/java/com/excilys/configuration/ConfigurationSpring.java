package com.excilys.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan(basePackages = { "com.excilys.service", "com.excilys.persistence", "com.excilys.controller", "com.excilys.mapper", "com.excilys.ui","com.excilys.model" })
@PropertySource("classpath:app.properties")
@EnableJpaRepositories("com.excilys.persistence")
@EnableTransactionManagement
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
	
	
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ConfigurationWebController.class,ConfigurationSpring.class);
		return rootContext;

	}
	@Bean
	public PlatformTransactionManager transactionManager() {
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
		
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.excilys.model" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");

		return properties;
	}
	
}
