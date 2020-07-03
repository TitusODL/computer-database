package com.excilys.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = ConfigurationSpring.class)
public class ConfigurationWebController implements WebApplicationInitializer,WebMvcConfigurer{

	@Override

	public void onStartup(ServletContext sc) throws ServletException {
		AnnotationConfigWebApplicationContext root =  new AnnotationConfigWebApplicationContext();
		root.register(ConfigurationWebController.class, ConfigurationSpring.class);
		root.setServletContext(sc);



		DispatcherServlet dv = new DispatcherServlet(root);
		ServletRegistration.Dynamic appServlet = sc.addServlet("Dashboard", dv);
		appServlet.setLoadOnStartup(1);
		appServlet.addMapping("/");
		
	}

	 @Bean
	   public ViewResolver getViewLocation() {
	      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	 
	      viewResolver.setViewClass(JstlView.class);
	      viewResolver.setPrefix("/WEB-INF/views/");
	      viewResolver.setSuffix(".jsp");
	 
	      return viewResolver;
	   }
	   
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry
	          .addResourceHandler("/resources/**")
	          .addResourceLocations("/resources/"); 
	    }


  
}
