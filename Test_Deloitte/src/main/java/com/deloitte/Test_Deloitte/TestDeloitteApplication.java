package com.deloitte.Test_Deloitte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestDeloitteApplication extends SpringBootServletInitializer{

	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(TestDeloitteApplication.class);
	   }
	
	public static void main(String[] args)  {
		SpringApplication.run(TestDeloitteApplication.class, args);
	}

}
