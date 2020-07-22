package com.excilys.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.excilys.service.UserService;
@Configuration
@EnableJpaRepositories("com.excilys.persistence")
@EnableWebSecurity
public class ConfigurationWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	UserService userservice;
	@Autowired
	public void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userservice)
		.passwordEncoder(passwordEncoder());
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.csrf()
		.disable();

		httpSecurity
		.authorizeRequests()
		.antMatchers("/login","/register")
		.permitAll();

		httpSecurity
		.authorizeRequests()
		.antMatchers("/Dashboard")
		.access("hasAnyRole('USER', 'ADMIN')");

		httpSecurity
		.authorizeRequests()
		.antMatchers("/EditComputer", "/AddComputer","/DeleteComputer")
		.access("hasRole('ADMIN')");

		httpSecurity
		.authorizeRequests()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/WEB-INF/views/403.jsp");


		httpSecurity
		.authorizeRequests()
		.and()
		.formLogin()
		.loginProcessingUrl("/j_spring_security_check")
		.loginPage("/login")
		.defaultSuccessUrl("/Dashboard")
		.failureUrl("/login?error=true")
		.usernameParameter("username")
		.passwordParameter("password")
		.and()
		.logout()
		.logoutUrl("/logout")
		.permitAll();
	}



}