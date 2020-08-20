package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * 
 * @author arcliu
 *
 */
@SpringBootApplication
public class FrontApplication extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FrontApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(FrontApplication.class, args);
	}

}
