package edu.auk.java_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JavaProjApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(JavaProjApplication.class, args);
		
		// String[] beanNames = applicationContext.getBeanDefinitionNames();
		// Arrays.sort(beanNames);
		// for (String beanName : beanNames)
		// System.out.println(beanName);
	}

};
