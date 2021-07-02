package edu.auk.java_proj;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JavaProjApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(JavaProjApplication.class, args);
	}

};
